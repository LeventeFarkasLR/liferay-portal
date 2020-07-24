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

package com.liferay.osb.koroneiki.taproot.service.impl;

import com.liferay.osb.koroneiki.taproot.exception.TeamRoleTypeException;
import com.liferay.osb.koroneiki.taproot.model.TeamAccountRole;
import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.osb.koroneiki.taproot.service.base.TeamAccountRoleLocalServiceBaseImpl;
import com.liferay.osb.koroneiki.taproot.service.persistence.TeamAccountRolePK;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.taproot.model.TeamAccountRole",
	service = AopService.class
)
public class TeamAccountRoleLocalServiceImpl
	extends TeamAccountRoleLocalServiceBaseImpl {

	public TeamAccountRole addTeamAccountRole(
			long teamId, long accountId, long teamRoleId)
		throws PortalException {

		validate(teamId, accountId, teamRoleId);

		TeamAccountRolePK teamAccountRolePK = new TeamAccountRolePK(
			teamId, accountId, teamRoleId);

		TeamAccountRole teamAccountRole =
			teamAccountRolePersistence.fetchByPrimaryKey(teamAccountRolePK);

		if (teamAccountRole == null) {
			teamAccountRole = teamAccountRolePersistence.create(
				teamAccountRolePK);

			teamAccountRole = teamAccountRolePersistence.update(
				teamAccountRole);

			_teamLocalService.reindex(teamId);
		}

		return teamAccountRole;
	}

	public TeamAccountRole deleteTeamAccountRole(
			long teamId, long accountId, long teamRoleId)
		throws PortalException {

		TeamAccountRolePK teamAccountRolePK = new TeamAccountRolePK(
			teamId, accountId, teamRoleId);

		TeamAccountRole teamAccountRole =
			teamAccountRolePersistence.fetchByPrimaryKey(teamAccountRolePK);

		if (teamAccountRole != null) {
			deleteTeamAccountRole(teamAccountRole);

			_teamLocalService.reindex(teamId);
		}

		return teamAccountRole;
	}

	public void deleteTeamAccountRoles(long teamId, long accountId) {
		teamAccountRolePersistence.removeByTI_AI(teamId, accountId);
	}

	public List<TeamAccountRole> getTeamAccountRoles(long teamId) {
		return teamAccountRolePersistence.findByTeamId(teamId);
	}

	public List<TeamAccountRole> getTeamAccountRolesByAccountId(
		long accountId) {

		return teamAccountRolePersistence.findByAccountId(accountId);
	}

	protected void validate(long teamId, long accountId, long teamRoleId)
		throws PortalException {

		teamPersistence.findByPrimaryKey(teamId);

		accountPersistence.findByPrimaryKey(accountId);

		TeamRole teamRole = teamRolePersistence.findByPrimaryKey(teamRoleId);

		String type = teamRole.getType();

		if (!type.equals(
				com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole.Type.
					ACCOUNT.toString())) {

			throw new TeamRoleTypeException();
		}
	}

	@Reference
	private TeamLocalService _teamLocalService;

}