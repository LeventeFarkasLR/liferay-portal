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
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.exception.HttpException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/accounts/edit_team"
	},
	service = MVCActionCommand.class
)
public class EditTeamMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteTeam(ActionRequest actionRequest, User user)
		throws Exception {

		String teamKey = ParamUtil.getString(actionRequest, "teamKey");

		_teamWebService.deleteTeam(
			user.getFullName(), StringPool.BLANK, teamKey);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			User user = themeDisplay.getUser();

			if (cmd.equals(Constants.DELETE)) {
				deleteTeam(actionRequest, user);

				sendRedirect(actionRequest, actionResponse);
			}
			else {
				String teamKey = updateTeam(actionRequest, user);

				sendRedirect(
					actionRequest, actionResponse,
					getRedirect(actionResponse, teamKey));
			}
		}
		catch (HttpException httpException) {
			SessionErrors.add(
				actionRequest, httpException.getClass(), httpException);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	protected String getRedirect(ActionResponse actionResponse, String teamKey)
		throws Exception {

		LiferayPortletResponse liferayPortletResponse =
			_portal.getLiferayPortletResponse(actionResponse);

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/accounts/view_team");
		portletURL.setParameter("teamKey", teamKey);

		return portletURL.toString();
	}

	protected String updateTeam(ActionRequest actionRequest, User user)
		throws Exception {

		String teamKey = ParamUtil.getString(actionRequest, "teamKey");

		String name = ParamUtil.getString(actionRequest, "name");

		if (Validator.isNotNull(name)) {
			Team team = new Team();

			team.setName(name);

			if (Validator.isNotNull(teamKey)) {
				team = _teamWebService.updateTeam(
					user.getFullName(), StringPool.BLANK, teamKey, team);
			}
			else {
				String accountKey = ParamUtil.getString(
					actionRequest, "accountKey");

				team = _teamWebService.addTeam(
					user.getFullName(), StringPool.BLANK, accountKey, team);
			}

			teamKey = team.getKey();
		}

		String[] addEmailAddresses = ParamUtil.getStringValues(
			actionRequest, "addEmailAddresses");
		String[] deleteEmailAddresses = ParamUtil.getStringValues(
			actionRequest, "deleteEmailAddresses");

		if (!ArrayUtil.isEmpty(addEmailAddresses)) {
			_teamWebService.assignContacts(
				user.getFullName(), StringPool.BLANK, teamKey,
				addEmailAddresses);
		}

		if (!ArrayUtil.isEmpty(deleteEmailAddresses)) {
			_teamWebService.unassignContacts(
				user.getFullName(), StringPool.BLANK, teamKey,
				deleteEmailAddresses);
		}

		return teamKey;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditTeamMVCActionCommand.class);

	@Reference
	private Portal _portal;

	@Reference
	private TeamWebService _teamWebService;

}