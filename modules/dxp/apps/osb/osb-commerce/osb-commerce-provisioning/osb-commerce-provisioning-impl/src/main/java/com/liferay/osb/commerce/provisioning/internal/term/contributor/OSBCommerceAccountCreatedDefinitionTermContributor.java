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

package com.liferay.osb.commerce.provisioning.internal.term.contributor;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.constants.CommerceDefinitionTermConstants;
import com.liferay.commerce.order.CommerceDefinitionTermContributor;
import com.liferay.osb.commerce.provisioning.constants.OSBCommerceNotificationConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(
	immediate = true,
	property = {
		"commerce.definition.term.contributor.key=" + OSBCommerceAccountCreatedDefinitionTermContributor.KEY,
		"commerce.notification.type.key=" + OSBCommerceNotificationConstants.ACCOUNT_CREATED
	},
	service = CommerceDefinitionTermContributor.class
)
public class OSBCommerceAccountCreatedDefinitionTermContributor
	implements CommerceDefinitionTermContributor {

	public static final String KEY =
		CommerceDefinitionTermConstants.
			BODY_AND_SUBJECT_DEFINITION_TERMS_CONTRIBUTOR;

	@Override
	public Map<String, String> getDefinitionTerms(Locale locale) {
		Map<String, String> map = new HashMap<>();

		List<String> terms = getTerms();

		for (String term : terms) {
			map.put(term, getLabel(term, locale));
		}

		return map;
	}

	@Override
	public String getFilledTerm(String term, Object object, Locale locale)
		throws PortalException {

		if (!(object instanceof CommerceAccount)) {
			return term;
		}

		CommerceAccount commerceAccount = (CommerceAccount)object;

		if (term.equals(_PROVISIONING_SITE_URL)) {
			Company company = _companyLocalService.getCompany(
				commerceAccount.getCompanyId());

			return _portal.getPortalURL(
				company.getVirtualHostname(),
				_portal.getPortalServerPort(false), false);
		}

		if (term.equals(_ACCOUNT_NAME)) {
			List<CommerceAccountUserRel> commerceAccountUserRels =
				commerceAccount.getCommerceAccountUserRels();

			CommerceAccountUserRel commerceAccountUserRel =
				commerceAccountUserRels.get(0);

			User user = commerceAccountUserRel.getUser();

			return HtmlUtil.escape(user.getPasswordUnencrypted());
		}

		return term;
	}

	@Override
	public String getLabel(String term, Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle, _commerceOrderDefinitionTermsMap.get(term));
	}

	@Override
	public List<String> getTerms() {
		return new ArrayList<>(_commerceOrderDefinitionTermsMap.keySet());
	}

	private static final String _ACCOUNT_NAME = "[%ACCOUNT_NAME%]";

	private static final String _PROVISIONING_SITE_URL =
		"[%PROVISIONING_SITE_URL%]";

	private static final Map<String, String> _commerceOrderDefinitionTermsMap =
		new HashMap<String, String>() {
			{
				put(_ACCOUNT_NAME, "account-name-definition-term");
				put(
					_PROVISIONING_SITE_URL,
					"provisioning-site-url-definition-term");
			}
		};

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private Portal _portal;

}