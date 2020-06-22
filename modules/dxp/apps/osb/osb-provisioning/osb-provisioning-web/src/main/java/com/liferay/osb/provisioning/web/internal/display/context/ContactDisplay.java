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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ContactDisplay {

	public ContactDisplay(
		HttpServletRequest httpServletRequest, long accountsCount,
		Contact contact, List<ContactRole> contactRoles) {

		_httpServletRequest = httpServletRequest;
		_accountsCount = accountsCount;
		_contact = contact;
		_contactRoles = contactRoles;
	}

	public String getAccountsCount() {
		return String.valueOf(_accountsCount);
	}

	public List<String> getContactRoleNames() {
		if (_contactRoles == null) {
			return Collections.emptyList();
		}

		List<String> contactRoleNames = new ArrayList<>();

		for (ContactRole contactRole : _contactRoles) {
			contactRoleNames.add(contactRole.getName());
		}

		return contactRoleNames;
	}

	public String getEmailAddress() {
		return _contact.getEmailAddress();
	}

	public String getEntitlements() {
		Entitlement[] entitlements = _contact.getEntitlements();

		if (ArrayUtil.isEmpty(entitlements)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler((entitlements.length * 3) - 2);

		for (int i = 0; i < entitlements.length; i++) {
			if (sb.length() > 75) {
				return StringUtil.shorten(sb.toString(), 75);
			}

			sb.append(entitlements[i].getName());

			if (i < (entitlements.length - 1)) {
				sb.append(StringPool.COMMA_AND_SPACE);
			}
		}

		return sb.toString();
	}

	public String getFullName() {
		StringBundler sb = new StringBundler(5);

		if (Validator.isNotNull(_contact.getFirstName())) {
			sb.append(_contact.getFirstName());
		}

		if (Validator.isNotNull(_contact.getMiddleName())) {
			if (sb.length() > 0) {
				sb.append(StringPool.SPACE);
			}

			sb.append(_contact.getMiddleName());
		}

		if (Validator.isNotNull(_contact.getLastName())) {
			if (sb.length() > 0) {
				sb.append(StringPool.SPACE);
			}

			sb.append(_contact.getLastName());
		}

		return sb.toString();
	}

	public String getKey() {
		return _contact.getKey();
	}

	public String getStatus() {
		if(_contact.getEmailAddressVerified()) {
			return "verified";
		}
		else {
			return "unverified";
		}
	}

	public String getStatusStyle() {
		String status = getStatus();
		
		if(status.equals("verified")) {
			return "label label-success";
		}
		else {
			return "label label-danger";
		}
	}

	private final long _accountsCount;
	private final Contact _contact;
	private final List<ContactRole> _contactRoles;
	private final HttpServletRequest _httpServletRequest;

}