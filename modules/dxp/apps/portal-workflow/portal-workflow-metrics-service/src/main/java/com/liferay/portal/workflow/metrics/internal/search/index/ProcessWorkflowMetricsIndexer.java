/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.workflow.metrics.internal.search.index;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.search.engine.adapter.document.BulkDocumentRequest;
import com.liferay.portal.search.engine.adapter.document.IndexDocumentRequest;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.metrics.search.index.name.WorkflowMetricsIndexNameBuilder;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Inácio Nery
 */
@Component(
	immediate = true,
	service = {Indexer.class, ProcessWorkflowMetricsIndexer.class}
)
public class ProcessWorkflowMetricsIndexer extends BaseWorkflowMetricsIndexer {

	@Override
	public void addDocument(Document document) {
		if (searchEngineAdapter == null) {
			return;
		}

		BulkDocumentRequest bulkDocumentRequest = new BulkDocumentRequest();

		bulkDocumentRequest.addBulkableDocumentRequest(
			new IndexDocumentRequest(
				_instanceWorkflowMetricsIndexer.getIndexName(
					GetterUtil.getLong(document.get("companyId"))),
				_createWorkflowMetricsInstanceDocument(
					GetterUtil.getLong(document.get("companyId")),
					GetterUtil.getLong(document.get("processId")))) {

				{
					setType(_instanceWorkflowMetricsIndexer.getIndexType());
				}
			});
		bulkDocumentRequest.addBulkableDocumentRequest(
			new IndexDocumentRequest(
				_slaProcessResultWorkflowMetricsIndexer.getIndexName(
					GetterUtil.getLong(document.get("companyId"))),
				_creatWorkflowMetricsSLAProcessResultDocument(
					GetterUtil.getLong(document.get("companyId")),
					GetterUtil.getLong(document.get("processId")))) {

				{
					setType(
						_slaProcessResultWorkflowMetricsIndexer.getIndexType());
				}
			});
		bulkDocumentRequest.addBulkableDocumentRequest(
			new IndexDocumentRequest(
				getIndexName(GetterUtil.getLong(document.get("companyId"))),
				document) {

				{
					setType(getIndexType());
				}
			});

		searchEngineAdapter.execute(bulkDocumentRequest);
	}

	public Document createDocument(KaleoDefinition kaleoDefinition) {
		Document document = new DocumentImpl();

		document.addUID(
			"WorkflowMetricsProcess",
			digest(
				kaleoDefinition.getCompanyId(),
				kaleoDefinition.getKaleoDefinitionId()));
		document.addKeyword("active", kaleoDefinition.isActive());
		document.addKeyword("companyId", kaleoDefinition.getCompanyId());
		document.addDateSortable("createDate", kaleoDefinition.getCreateDate());
		document.addKeyword("deleted", false);
		document.addText("description", kaleoDefinition.getDescription());
		document.addDateSortable(
			"modifiedDate", kaleoDefinition.getModifiedDate());
		document.addKeyword("name", kaleoDefinition.getName());
		document.addKeyword(
			"processId", kaleoDefinition.getKaleoDefinitionId());
		document.addLocalizedKeyword(
			"title",
			LocalizationUtil.populateLocalizationMap(
				kaleoDefinition.getTitleMap(),
				kaleoDefinition.getDefaultLanguageId(),
				kaleoDefinition.getGroupId()),
			false, true);
		document.addKeyword("userId", kaleoDefinition.getUserId());
		document.addKeyword(
			"version",
			StringBundler.concat(
				kaleoDefinition.getVersion(), CharPool.PERIOD, 0));

		return document;
	}

	@Override
	protected String getIndexName(long companyId) {
		return _processWorkflowMetricsIndexNameBuilder.getIndexName(companyId);
	}

	@Override
	protected String getIndexType() {
		return "WorkflowMetricsProcessType";
	}

	@Override
	protected void reindex(long companyId) throws PortalException {
		ActionableDynamicQuery actionableDynamicQuery =
			kaleoDefinitionLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> {
				Property companyIdProperty = PropertyFactoryUtil.forName(
					"companyId");

				dynamicQuery.add(companyIdProperty.eq(companyId));
			});
		actionableDynamicQuery.setPerformActionMethod(
			(KaleoDefinition kaleoDefinition) ->
				workflowMetricsPortalExecutor.execute(
					() -> addDocument(createDocument(kaleoDefinition))));

		actionableDynamicQuery.performActions();
	}

	private Document _createWorkflowMetricsInstanceDocument(
		long companyId, long kaleoDefinitionId) {

		Document document = new DocumentImpl();

		document.addUID(
			"WorkflowMetricsInstance", digest(companyId, kaleoDefinitionId, 0));
		document.addKeyword("companyId", companyId);
		document.addKeyword("completed", false);
		document.addKeyword("deleted", false);
		document.addKeyword("instanceId", 0);
		document.addKeyword("processId", kaleoDefinitionId);

		return document;
	}

	private Document _creatWorkflowMetricsSLAProcessResultDocument(
		long companyId, long kaleoDefinitionId) {

		Document document = new DocumentImpl();

		document.addUID(
			"WorkflowMetricsSLAProcessResult",
			digest(companyId, 0, kaleoDefinitionId, 0));
		document.addKeyword("companyId", companyId);
		document.addKeyword("deleted", false);
		document.addKeyword("instanceId", 0);
		document.addKeyword("processId", kaleoDefinitionId);
		document.addKeyword("slaDefinitionId", 0);

		return document;
	}

	@Reference
	private InstanceWorkflowMetricsIndexer _instanceWorkflowMetricsIndexer;

	@Reference(target = "(workflow.metrics.index.entity.name=process)")
	private WorkflowMetricsIndexNameBuilder
		_processWorkflowMetricsIndexNameBuilder;

	@Reference
	private SLAProcessResultWorkflowMetricsIndexer
		_slaProcessResultWorkflowMetricsIndexer;

}