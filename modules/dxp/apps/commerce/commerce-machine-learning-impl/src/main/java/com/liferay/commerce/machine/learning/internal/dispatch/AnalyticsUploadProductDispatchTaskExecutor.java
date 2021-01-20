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

package com.liferay.commerce.machine.learning.internal.dispatch;

import com.liferay.commerce.machine.learning.internal.batch.engine.mapper.BatchEngineTaskItemDelegateResourceMapper;
import com.liferay.dispatch.executor.BaseDispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorOutput;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.headless.commerce.admin.catalog.constants.v1_0.ProductBatchEngineTaskItemDelegateConstants;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.IOException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"dispatch.task.executor.name=" + AnalyticsUploadProductDispatchTaskExecutor.KEY,
		"dispatch.task.executor.type=" + AnalyticsUploadProductDispatchTaskExecutor.KEY
	},
	service = DispatchTaskExecutor.class
)
public class AnalyticsUploadProductDispatchTaskExecutor
	extends BaseDispatchTaskExecutor {

	public static final String KEY = "analytics-upload-product";

	@Override
	public void doExecute(
			DispatchTrigger dispatchTrigger,
			DispatchTaskExecutorOutput dispatchTaskExecutorOutput)
		throws IOException, PortalException {

		_analyticsDispatchTaskExecutor.uploadResources(
			dispatchTrigger, dispatchTaskExecutorOutput,
			_EXPORT_RESOURCE_NAMES);
	}

	@Override
	public String getName() {
		return KEY;
	}

	private static final BatchEngineTaskItemDelegateResourceMapper[]
		_EXPORT_RESOURCE_NAMES = {
			new BatchEngineTaskItemDelegateResourceMapper(
				Product.class.getName(), null,
				ProductBatchEngineTaskItemDelegateConstants.COMMERCE_ML_PRODUCT)
		};

	@Reference
	private AnalyticsDispatchTaskExecutor _analyticsDispatchTaskExecutor;

}