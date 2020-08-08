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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;

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

		long productConsumptionsCount =
			productConsumptionWebService.searchCount(
				"productPurchaseKey eq '" + _productPurchase.getKey() + "'");

		_productPurchaseDisplay = new ProductPurchaseDisplay(
			httpServletRequest, _productPurchase, productConsumptionsCount);
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

	private ProductPurchase _productPurchase;
	private ProductPurchaseDisplay _productPurchaseDisplay;

}