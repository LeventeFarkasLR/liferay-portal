/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.product.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CPDefinitionLinkService}.
 *
 * @author Marco Leo
 * @see CPDefinitionLinkService
 * @generated
 */
@ProviderType
public class CPDefinitionLinkServiceWrapper implements CPDefinitionLinkService,
	ServiceWrapper<CPDefinitionLinkService> {
	public CPDefinitionLinkServiceWrapper(
		CPDefinitionLinkService cpDefinitionLinkService) {
		_cpDefinitionLinkService = cpDefinitionLinkService;
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinitionLink addCPDefinitionLink(
		long cpDefinitionId1, long cpDefinitionId2, int displayOrder, int type,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpDefinitionLinkService.addCPDefinitionLink(cpDefinitionId1,
			cpDefinitionId2, displayOrder, type, serviceContext);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinitionLink deleteCPDefinitionLink(
		com.liferay.commerce.product.model.CPDefinitionLink cpDefinitionLink)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpDefinitionLinkService.deleteCPDefinitionLink(cpDefinitionLink);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinitionLink deleteCPDefinitionLink(
		long cpDefinitionLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpDefinitionLinkService.deleteCPDefinitionLink(cpDefinitionLinkId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _cpDefinitionLinkService.getOSGiServiceIdentifier();
	}

	@Override
	public CPDefinitionLinkService getWrappedService() {
		return _cpDefinitionLinkService;
	}

	@Override
	public void setWrappedService(
		CPDefinitionLinkService cpDefinitionLinkService) {
		_cpDefinitionLinkService = cpDefinitionLinkService;
	}

	private CPDefinitionLinkService _cpDefinitionLinkService;
}