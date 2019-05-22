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

import com.liferay.osb.koroneiki.taproot.constants.TeamRoleType;
import com.liferay.osb.koroneiki.taproot.exception.TeamRoleNameException;
import com.liferay.osb.koroneiki.taproot.exception.TeamRoleTypeException;
import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.osb.koroneiki.taproot.service.base.TeamRoleLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.LinkedHashMap;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.taproot.model.TeamRole",
	service = AopService.class
)
public class TeamRoleLocalServiceImpl extends TeamRoleLocalServiceBaseImpl {

	public TeamRole addTeamRole(
			long userId, String name, String description, int type)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		validate(name, type);

		long teamRoleId = counterLocalService.increment();

		TeamRole teamRole = teamRolePersistence.create(teamRoleId);

		teamRole.setCompanyId(user.getCompanyId());
		teamRole.setUserId(userId);
		teamRole.setName(name);
		teamRole.setDescription(description);
		teamRole.setType(type);

		teamRolePersistence.update(teamRole);

		// Resources

		resourceLocalService.addResources(
			teamRole.getCompanyId(), 0, userId, TeamRole.class.getName(),
			teamRole.getTeamRoleId(), false, false, false);

		return teamRole;
	}

	@Override
	public TeamRole deleteTeamRole(long teamRoleId) throws PortalException {
		TeamRole teamRole = teamRoleLocalService.getTeamRole(teamRoleId);

		// Resources

		resourceLocalService.deleteResource(
			teamRole.getCompanyId(), TeamRole.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, teamRole.getTeamRoleId());

		return teamRolePersistence.remove(teamRoleId);
	}

	public List<TeamRole> getTeamProjectTeamRoles(long projectId, long teamId) {
		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("projectTeam", new Long[] {projectId, teamId});

		return teamRoleFinder.findByName(
			null, params, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<TeamRole> getTeamRoles(int type, int start, int end) {
		return teamRolePersistence.findByType(type, start, end);
	}

	public List<TeamRole> getTeamRoles(String name) throws PortalException {
		return teamRolePersistence.findByName(name);
	}

	public int getTeamRolesCount(int type) {
		return teamRolePersistence.countByType(type);
	}

	public TeamRole updateTeamRole(
			long userId, long teamRoleId, String name, String description)
		throws PortalException {

		TeamRole teamRole = teamRolePersistence.findByPrimaryKey(teamRoleId);

		validate(name, teamRole.getType());

		teamRole.setName(name);
		teamRole.setDescription(description);

		return teamRolePersistence.update(teamRole);
	}

	protected void validate(String name, int type) throws PortalException {
		if (Validator.isNull(name)) {
			throw new TeamRoleNameException();
		}

		if (!ArrayUtil.contains(TeamRoleType.VALUES, type)) {
			throw new TeamRoleTypeException();
		}
	}

}