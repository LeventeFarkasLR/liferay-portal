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

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.PROVISIONING,
		"mvc.command.name=/accounts/assign_team_contacts"
	},
	service = MVCRenderCommand.class
)
public class AssignTeamContactsMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		String teamKey = ParamUtil.getString(renderRequest, "teamKey");

		try {
			Team team = _teamWebService.getTeam(teamKey);

			renderRequest.setAttribute(
				ProvisioningWebKeys.ACCOUNT,
				_accountWebService.getAccount(team.getAccountKey()));
			renderRequest.setAttribute(ProvisioningWebKeys.TEAM, team);

			return "/accounts/assign_team_contacts.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass(), exception);

			return "/accounts/error.jsp";
		}
	}

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private TeamWebService _teamWebService;

}