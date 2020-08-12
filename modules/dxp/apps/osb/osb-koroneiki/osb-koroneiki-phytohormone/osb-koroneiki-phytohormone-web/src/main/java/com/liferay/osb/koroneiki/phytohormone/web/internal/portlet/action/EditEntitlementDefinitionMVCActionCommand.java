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

package com.liferay.osb.koroneiki.phytohormone.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phytohormone.constants.PhytohormonePortletKeys;
import com.liferay.osb.koroneiki.phytohormone.exception.EntitlementDefinitionNameException;
import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementDefinitionLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
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
		"javax.portlet.name=" + PhytohormonePortletKeys.ENTITLEMENT_DEFINITIONS_ADMIN,
		"mvc.command.name=/entitlement_definitions_admin/edit_entitlement_definition"
	},
	service = MVCActionCommand.class
)
public class EditEntitlementDefinitionMVCActionCommand
	extends BaseMVCActionCommand {

	protected void deleteEntitlementDefinition(ActionRequest actionRequest)
		throws PortalException {

		long entitlementDefinitionId = ParamUtil.getLong(
			actionRequest, "entitlementDefinitionId");

		_entitlementDefinitionLocalService.deleteEntitlementDefinition(
			entitlementDefinitionId);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteEntitlementDefinition(actionRequest);
			}
			else {
				updateEntitlementDefinition(actionRequest, actionResponse);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			if (exception instanceof EntitlementDefinitionNameException) {
				SessionErrors.add(actionRequest, exception.getClass());

				actionResponse.setRenderParameter(
					"mvcRenderCommandName",
					"/entitlement_definitions_admin/edit_entitlement_" +
						"definition");
			}
			else {
				_log.error(exception, exception);

				throw exception;
			}
		}
	}

	protected void updateEntitlementDefinition(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		long entitlementDefinitionId = ParamUtil.getLong(
			actionRequest, "entitlementDefinitionId");

		String description = ParamUtil.getString(actionRequest, "description");
		String definition = ParamUtil.getString(actionRequest, "definition");
		int status = ParamUtil.getInteger(actionRequest, "status");

		EntitlementDefinition entitlementDefinition = null;

		if (entitlementDefinitionId <= 0) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long classNameId = ParamUtil.getLong(actionRequest, "classNameId");
			String name = ParamUtil.getString(actionRequest, "name");

			entitlementDefinition =
				_entitlementDefinitionLocalService.addEntitlementDefinition(
					themeDisplay.getUserId(), classNameId, name, description,
					definition, status);
		}
		else {
			entitlementDefinition =
				_entitlementDefinitionLocalService.updateEntitlementDefinition(
					entitlementDefinitionId, description, definition, status);
		}

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		LiferayPortletResponse liferayPortletResponse =
			_portal.getLiferayPortletResponse(actionResponse);

		PortletURL renderURL = liferayPortletResponse.createRenderURL();

		renderURL.setParameter(
			"mvcRenderCommandName",
			"/entitlement_definitions_admin/edit_entitlement_definition");
		renderURL.setParameter("redirect", redirect);
		renderURL.setParameter(
			"entitlementDefinitionId",
			String.valueOf(entitlementDefinition.getEntitlementDefinitionId()));

		actionRequest.setAttribute(WebKeys.REDIRECT, renderURL.toString());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditEntitlementDefinitionMVCActionCommand.class);

	@Reference
	private EntitlementDefinitionLocalService
		_entitlementDefinitionLocalService;

	@Reference
	private Portal _portal;

}