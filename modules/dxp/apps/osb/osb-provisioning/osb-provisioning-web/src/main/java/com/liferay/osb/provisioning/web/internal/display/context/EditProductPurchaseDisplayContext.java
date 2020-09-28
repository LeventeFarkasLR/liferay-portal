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

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;

/**
 * @author Yuanyuan Huang
 */
public class EditProductPurchaseDisplayContext
	extends ViewAccountDisplayContext {

	public EditProductPurchaseDisplayContext() {
	}

	@Override
	public void doInit() throws Exception {
		super.doInit();

		_productPurchase = (ProductPurchase)httpServletRequest.getAttribute(
			ProvisioningWebKeys.PRODUCT_PURCHASE);

		if (_productPurchase != null) {
			StringBundler sb = new StringBundler(3);

			sb.append("productPurchaseKey eq '");
			sb.append(_productPurchase.getKey());
			sb.append("'");

			long productConsumptionsCount =
				productConsumptionWebService.searchCount(sb.toString());

			_productPurchaseDisplay = new ProductPurchaseDisplay(
				httpServletRequest, _productPurchase, productConsumptionsCount);
		}
	}

	public String getAccountKey() {
		return account.getKey();
	}

	public String getAccountName() {
		return account.getName();
	}

	public ProductPurchase getProductPurchase() {
		return _productPurchase;
	}

	public ProductPurchaseDisplay getProductPurchaseDisplay() {
		return _productPurchaseDisplay;
	}

	public String getProductPurchaseProductClearResultsURL() {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName",
			"/accounts/select_product_purchase_product");
		portletURL.setParameter("accountKey", getAccountKey());

		return portletURL.toString();
	}

	public SearchContainer getProductPurchaseProductSearchContainer()
		throws Exception {

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, currentURLObj, Collections.emptyList(),
			"no-products-were-found");

		String keywords = ParamUtil.getString(renderRequest, "keywords");

		List<Product> products = productWebService.getProducts(
			keywords, StringPool.BLANK, searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(), "name");

		searchContainer.setResults(
			TransformUtil.transform(
				products,
				product -> new ProductDisplay(
					renderRequest, renderResponse, product)));

		int count = (int)productWebService.getProductsCount(
			keywords, StringPool.BLANK);

		searchContainer.setTotal(count);

		return searchContainer;
	}

	private ProductPurchase _productPurchase;
	private ProductPurchaseDisplay _productPurchaseDisplay;

}