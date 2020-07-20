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

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class PostalAddressDisplay {

	public PostalAddressDisplay(
		PortletRequest portletRequest, PortletResponse portletResponse,
		Account account, PostalAddress postalAddress) {

		_portletRequest = portletRequest;
		_portletResponse = portletResponse;
		_account = account;
		_postalAddress = postalAddress;

		_httpServletRequest = PortalUtil.getHttpServletRequest(portletRequest);
		_liferayPortletResponse = PortalUtil.getLiferayPortletResponse(
			portletResponse);
	}

	public String getAddressCountry() {
		if (Validator.isNotNull(_postalAddress.getAddressCountry())) {
			return _postalAddress.getAddressCountry();
		}

		return StringPool.DASH;
	}

	public String getAddressLocality() {
		if (Validator.isNotNull(_postalAddress.getAddressLocality())) {
			return _postalAddress.getAddressLocality();
		}

		return StringPool.DASH;
	}

	public String getAddressRegion() {
		if (Validator.isNotNull(_postalAddress.getAddressRegion())) {
			return _postalAddress.getAddressRegion();
		}

		return StringPool.DASH;
	}

	public String getAddressType() {
		if (Validator.isNotNull(_postalAddress.getAddressType())) {
			return _postalAddress.getAddressType();
		}

		return StringPool.DASH;
	}

	public String getDeletePostalAddressURL() {
		PortletURL deletePostalAddressURL =
			_liferayPortletResponse.createActionURL();

		deletePostalAddressURL.setParameter(
			ActionRequest.ACTION_NAME, "/accounts/edit_postal_address");
		deletePostalAddressURL.setParameter(Constants.CMD, Constants.DELETE);

		PortletURL portletURL = _getPortletURL(
			"/accounts/view_account", "details");

		deletePostalAddressURL.setParameter("redirect", portletURL.toString());

		deletePostalAddressURL.setParameter(
			"postalAddressId", String.valueOf(_postalAddress.getId()));

		return deletePostalAddressURL.toString();
	}

	public String getEditPostalAddressURL() {
		PortletURL editPostalAddressURL =
			_liferayPortletResponse.createActionURL();

		editPostalAddressURL.setParameter(
			ActionRequest.ACTION_NAME, "/accounts/edit_postal_address");

		PortletURL portletURL = _getPortletURL(
			"/accounts/view_account", "details");

		editPostalAddressURL.setParameter("redirect", portletURL.toString());

		editPostalAddressURL.setParameter(
			"postalAddressId", String.valueOf(_postalAddress.getId()));

		return editPostalAddressURL.toString();
	}

	public long getId() {
		return _postalAddress.getId();
	}

	public Boolean getMailing() {
		if ((_postalAddress.getMailing() != null) &&
			_postalAddress.getMailing()) {

			return true;
		}

		return false;
	}

	public String getPostalCode() {
		if (Validator.isNotNull(_postalAddress.getPostalCode())) {
			return _postalAddress.getPostalCode();
		}

		return StringPool.DASH;
	}

	public Boolean getPrimary() {
		if ((_postalAddress.getPrimary() != null) &&
			_postalAddress.getPrimary()) {

			return true;
		}

		return false;
	}

	public String getStreetAddressLine1() {
		if (Validator.isNotNull(_postalAddress.getStreetAddressLine1())) {
			return _postalAddress.getStreetAddressLine1();
		}

		return StringPool.DASH;
	}

	public String getStreetAddressLine2() {
		if (Validator.isNotNull(_postalAddress.getStreetAddressLine2())) {
			return _postalAddress.getStreetAddressLine2();
		}

		return StringPool.DASH;
	}

	public String getStreetAddressLine3() {
		if (Validator.isNotNull(_postalAddress.getStreetAddressLine3())) {
			return _postalAddress.getStreetAddressLine3();
		}

		return StringPool.DASH;
	}

	private PortletURL _getPortletURL(String mvcRenderCommandName, String tab) {
		PortletURL portletURL = _liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", mvcRenderCommandName);
		portletURL.setParameter("tabs1", tab);
		portletURL.setParameter("accountKey", _account.getKey());

		return portletURL;
	}

	private final Account _account;
	private final HttpServletRequest _httpServletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private final PortletRequest _portletRequest;
	private final PortletResponse _portletResponse;
	private final PostalAddress _postalAddress;

}