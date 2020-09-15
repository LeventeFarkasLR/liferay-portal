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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.PostalAddressWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.exception.HttpException;
import com.liferay.portal.kernel.exception.NoSuchCountryException;
import com.liferay.portal.kernel.exception.NoSuchListTypeException;
import com.liferay.portal.kernel.exception.NoSuchRegionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.kernel.service.ListTypeService;
import com.liferay.portal.kernel.service.RegionService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/accounts/edit_postal_address"
	},
	service = MVCActionCommand.class
)
public class EditPostalAddressMVCActionCommand extends BaseMVCActionCommand {

	protected void deletePostalAddress(ActionRequest actionRequest, User user)
		throws Exception {

		long postalAddressId = ParamUtil.getLong(
			actionRequest, "postalAddressId");

		_postalAddressWebService.deletePostalAddress(
			user.getFullName(), user.getUuid(), postalAddressId);
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
				deletePostalAddress(actionRequest, user);
			}
			else {
				updatePostalAddress(actionRequest, user);
			}
		}
		catch (HttpException httpException) {
			_log.error(httpException, httpException);

			SessionErrors.add(
				actionRequest, httpException.getClass(), httpException);
		}
		catch (Exception exception) {
			if (exception instanceof NoSuchCountryException ||
				exception instanceof NoSuchListTypeException ||
				exception instanceof NoSuchRegionException) {

				SessionErrors.add(actionRequest, exception.getClass());
			}
			else {
				_log.error(exception, exception);

				throw exception;
			}
		}

		sendRedirect(actionRequest, actionResponse);
	}

	protected void updatePostalAddress(ActionRequest actionRequest, User user)
		throws Exception {

		long postalAddressId = ParamUtil.getLong(
			actionRequest, "postalAddressId");

		String accountKey = ParamUtil.getString(actionRequest, "accountKey");
		String streetAddressLine1 = ParamUtil.getString(
			actionRequest, "streetAddressLine1");
		String streetAddressLine2 = ParamUtil.getString(
			actionRequest, "streetAddressLine2");
		String streetAddressLine3 = ParamUtil.getString(
			actionRequest, "streetAddressLine3");
		String addressLocality = ParamUtil.getString(
			actionRequest, "addressLocality");
		String addressZip = ParamUtil.getString(actionRequest, "addressZip");
		long addressCountryId = ParamUtil.getLong(
			actionRequest, "addressCountryId");
		long addressRegionId = ParamUtil.getLong(
			actionRequest, "addressRegionId");

		boolean primary = ParamUtil.getBoolean(actionRequest, "addressPrimary");

		PostalAddress postalAddress = new PostalAddress();

		if (Validator.isNotNull(streetAddressLine1)) {
			postalAddress.setStreetAddressLine1(streetAddressLine1);
		}

		if (Validator.isNotNull(streetAddressLine2)) {
			postalAddress.setStreetAddressLine2(streetAddressLine2);
		}

		if (Validator.isNotNull(streetAddressLine3)) {
			postalAddress.setStreetAddressLine3(streetAddressLine3);
		}

		if (Validator.isNotNull(addressLocality)) {
			postalAddress.setAddressLocality(addressLocality);
		}

		if (Validator.isNotNull(addressZip)) {
			postalAddress.setPostalCode(addressZip);
		}

		if (addressRegionId > 0) {
			Region region = _regionService.getRegion(addressRegionId);

			postalAddress.setAddressRegion(region.getName());
		}

		if (addressCountryId > 0) {
			Country country = _countryService.getCountry(addressCountryId);

			postalAddress.setAddressCountry(country.getName(LocaleUtil.US));
		}

		postalAddress.setPrimary(primary);

		if (postalAddressId > 0) {
			_postalAddressWebService.updatePostalAddress(
				user.getFullName(), user.getUuid(), postalAddressId,
				postalAddress);
		}
		else {
			_postalAddressWebService.addPostalAddress(
				user.getFullName(), user.getUuid(), accountKey, postalAddress);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditPostalAddressMVCActionCommand.class);

	@Reference
	private CountryService _countryService;

	@Reference
	private ListTypeService _listTypeService;

	@Reference
	private PostalAddressWebService _postalAddressWebService;

	@Reference
	private RegionService _regionService;

}