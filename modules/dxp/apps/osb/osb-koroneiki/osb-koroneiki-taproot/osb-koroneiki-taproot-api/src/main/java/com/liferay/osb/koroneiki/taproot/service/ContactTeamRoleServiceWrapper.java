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

package com.liferay.osb.koroneiki.taproot.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContactTeamRoleService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContactTeamRoleService
 * @generated
 */
@ProviderType
public class ContactTeamRoleServiceWrapper
	implements ContactTeamRoleService, ServiceWrapper<ContactTeamRoleService> {

	public ContactTeamRoleServiceWrapper(
		ContactTeamRoleService contactTeamRoleService) {

		_contactTeamRoleService = contactTeamRoleService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _contactTeamRoleService.getOSGiServiceIdentifier();
	}

	@Override
	public ContactTeamRoleService getWrappedService() {
		return _contactTeamRoleService;
	}

	@Override
	public void setWrappedService(
		ContactTeamRoleService contactTeamRoleService) {

		_contactTeamRoleService = contactTeamRoleService;
	}

	private ContactTeamRoleService _contactTeamRoleService;

}