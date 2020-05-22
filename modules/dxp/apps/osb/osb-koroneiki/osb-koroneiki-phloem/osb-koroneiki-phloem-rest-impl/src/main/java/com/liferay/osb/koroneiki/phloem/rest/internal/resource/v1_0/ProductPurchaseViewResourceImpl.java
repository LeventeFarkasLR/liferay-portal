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

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.ProductPurchaseViewUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0.ProductPurchaseViewEntityModel;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductPurchaseViewResource;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountService;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionService;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Kyle Bischof
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product-purchase-view.properties",
	scope = ServiceScope.PROTOTYPE, service = ProductPurchaseViewResource.class
)
public class ProductPurchaseViewResourceImpl
	extends BaseProductPurchaseViewResourceImpl implements EntityModelResource {

	@Override
	public ProductPurchaseView
			getAccountAccountKeyProductProductKeyProductPurchaseView(
				String accountKey, String productKey)
		throws Exception {

		Account account = _accountService.getAccount(accountKey);
		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			productKey);

		List<ProductPurchase> productPurchases =
			_productPurchaseService.getAccountProductEntryProductPurchases(
				account.getAccountId(), productEntry.getProductEntryId(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		if (productPurchases.isEmpty()) {
			return null;
		}

		return ProductPurchaseViewUtil.toProductPurchaseView(
			productEntry,
			_productConsumptionService.
				getAccountProductEntryProductConsumptions(
					account.getAccountId(), productEntry.getProductEntryId()),
			productPurchases);
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return new ProductPurchaseViewEntityModel();
	}

	@Override
	public Page<ProductPurchaseView> getProductPurchaseViewsPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
			},
			filter,
			com.liferay.osb.koroneiki.trunk.model.view.ProductPurchaseView.
				class,
			search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				"accountId", "productEntryId"),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> ProductPurchaseViewUtil.toProductPurchaseView(
				_productEntryLocalService.getProductEntry(
					GetterUtil.getLong(document.get("productEntryId"))),
				_productConsumptionService.
					getAccountProductEntryProductConsumptions(
						GetterUtil.getLong(document.get("accountId")),
						GetterUtil.getLong(document.get("productEntryId"))),
				_productPurchaseService.getAccountProductEntryProductPurchases(
					GetterUtil.getLong(document.get("accountId")),
					GetterUtil.getLong(document.get("productEntryId")),
					QueryUtil.ALL_POS, QueryUtil.ALL_POS)),
			sorts);
	}

	@Reference
	private AccountService _accountService;

	@Reference
	private ProductConsumptionService _productConsumptionService;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductEntryService _productEntryService;

	@Reference
	private ProductPurchaseService _productPurchaseService;

}