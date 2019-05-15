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
 * Provides a wrapper for {@link ProductEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProductEntryService
 * @generated
 */
@ProviderType
public class ProductEntryServiceWrapper
	implements ProductEntryService, ServiceWrapper<ProductEntryService> {

	public ProductEntryServiceWrapper(ProductEntryService productEntryService) {
		_productEntryService = productEntryService;
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry addProductEntry(
			String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryService.addProductEntry(name);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry
			deleteProductEntry(long productEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryService.deleteProductEntry(productEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _productEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry
			updateProductEntry(long productEntryId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryService.updateProductEntry(productEntryId, name);
	}

	@Override
	public ProductEntryService getWrappedService() {
		return _productEntryService;
	}

	@Override
	public void setWrappedService(ProductEntryService productEntryService) {
		_productEntryService = productEntryService;
	}

	private ProductEntryService _productEntryService;

}