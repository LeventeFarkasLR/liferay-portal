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

package com.liferay.osb.koroneiki.trunk.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProductPurchaseService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProductPurchaseService
 * @generated
 */
@ProviderType
public class ProductPurchaseServiceWrapper
	implements ProductPurchaseService, ServiceWrapper<ProductPurchaseService> {

	public ProductPurchaseServiceWrapper(
		ProductPurchaseService productPurchaseService) {

		_productPurchaseService = productPurchaseService;
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			addProductPurchase(
				long accountId, long projectId, long productEntryId,
				java.util.Date startDate, java.util.Date endDate, int quantity)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.addProductPurchase(
			accountId, projectId, productEntryId, startDate, endDate, quantity);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			deleteProductPurchase(long productPurchaseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.deleteProductPurchase(productPurchaseId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _productPurchaseService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			updateProductPurchase(
				long productPurchaseId, java.util.Date startDate,
				java.util.Date endDate, int quantity)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.updateProductPurchase(
			productPurchaseId, startDate, endDate, quantity);
	}

	@Override
	public ProductPurchaseService getWrappedService() {
		return _productPurchaseService;
	}

	@Override
	public void setWrappedService(
		ProductPurchaseService productPurchaseService) {

		_productPurchaseService = productPurchaseService;
	}

	private ProductPurchaseService _productPurchaseService;

}