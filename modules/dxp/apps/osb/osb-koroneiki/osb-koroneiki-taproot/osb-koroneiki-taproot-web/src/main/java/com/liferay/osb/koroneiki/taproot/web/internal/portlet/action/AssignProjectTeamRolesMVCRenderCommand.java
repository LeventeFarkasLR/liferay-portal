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

package com.liferay.osb.koroneiki.taproot.web.internal.portlet.action;

import com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys;
import com.liferay.osb.koroneiki.taproot.constants.TaprootWebKeys;
import com.liferay.osb.koroneiki.taproot.model.Project;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.ProjectLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"javax.portlet.name=" + TaprootPortletKeys.PROJECTS_ADMIN,
		"mvc.command.name=/projects_admin/assign_project_team_roles"
	},
	service = MVCRenderCommand.class
)
public class AssignProjectTeamRolesMVCRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			long projectId = ParamUtil.getLong(renderRequest, "projectId");
			long teamId = ParamUtil.getLong(renderRequest, "teamId");

			Project project = _projectLocalService.getProject(projectId);

			renderRequest.setAttribute(TaprootWebKeys.PROJECT, project);

			Team team = _teamLocalService.getTeam(teamId);

			renderRequest.setAttribute(TaprootWebKeys.TEAM, team);

			return "/projects_admin/assign_project_team_roles.jsp";
		}
		catch (Exception e) {
			SessionErrors.add(renderRequest, e.getClass());

			return "/projects_admin/error.jsp";
		}
	}

	@Reference
	private ProjectLocalService _projectLocalService;

	@Reference
	private TeamLocalService _teamLocalService;

}