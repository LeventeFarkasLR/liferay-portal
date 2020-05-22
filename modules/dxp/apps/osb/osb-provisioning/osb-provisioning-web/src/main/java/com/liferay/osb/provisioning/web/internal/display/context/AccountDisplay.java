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
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.provisioning.koroneiki.constants.ProductConstants;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.text.Format;

import java.util.Collections;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class AccountDisplay {

	public AccountDisplay(
			PortletRequest portletRequest, PortletResponse portletResponse,
			AccountReader accountReader, Account account)
		throws Exception {

		_portletRequest = portletRequest;
		_portletResponse = portletResponse;

		_accountReader = accountReader;
		_account = account;

		_dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy");
		_dateTimeFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy hh:mm a z");
		_firstLineSupportTeam = _accountReader.getFirstLineSupportTeam(
			_account);
		_httpServletRequest = PortalUtil.getHttpServletRequest(portletRequest);
		_liferayPortletResponse = PortalUtil.getLiferayPortletResponse(
			portletResponse);
		_partnerTeam = _accountReader.getPartnerTeam(_account);
	}

	public String addPostalAddressURL() {
		PortletURL addPostalAddressURL =
			_liferayPortletResponse.createActionURL();

		addPostalAddressURL.setParameter(
			ActionRequest.ACTION_NAME, "/edit_postal_address");
		addPostalAddressURL.setParameter("accountKey", _account.getKey());

		return addPostalAddressURL.toString();
	}

	public String getCode() {
		if (Validator.isNotNull(_account.getCode())) {
			return _account.getCode();
		}

		return StringPool.DASH;
	}

	public String getDateCreated() {
		return _dateTimeFormat.format(_account.getDateCreated());
	}

	public String getDateModified() {
		return _dateTimeFormat.format(_account.getDateModified());
	}

	public String getDeveloperContactUsage() {
		StringBundler sb = new StringBundler(5);

		sb.append(_accountReader.getDeveloperCount(_account));
		sb.append(" / ");
		sb.append(_accountReader.getMaxDeveloperCount(_account));
		sb.append(" ");
		sb.append(LanguageUtil.get(_httpServletRequest, "filled"));

		return sb.toString();
	}

	public String getDossieraAccountKey() {
		return _getExternalLinkEntityId("dossiera", "account");
	}

	public String getDossieraProjectKey() {
		return _getExternalLinkEntityId("dossiera", "project");
	}

	public String getEditAccountURL() {
		PortletURL editAccountURL = _liferayPortletResponse.createActionURL();

		editAccountURL.setParameter(ActionRequest.ACTION_NAME, "/edit_account");
		editAccountURL.setParameter("accountKey", _account.getKey());

		return editAccountURL.toString();
	}

	public String getEWSA() throws Exception {
		if (isEWSA()) {
			return LanguageUtil.get(_httpServletRequest, "yes");
		}

		return LanguageUtil.get(_httpServletRequest, "no");
	}

	public String getFirstLineSupportTeamName() throws Exception {
		if (_firstLineSupportTeam != null) {
			return _firstLineSupportTeam.getName();
		}

		return StringPool.DASH;
	}

	public String getKey() {
		return _account.getKey();
	}

	public String getName() {
		return _account.getName();
	}

	public String getParentAccountKey() {
		return _account.getParentAccountKey();
	}

	public String getPartnerTeamName() throws Exception {
		if (_partnerTeam != null) {
			return _partnerTeam.getName();
		}

		return StringPool.DASH;
	}

	public List<PostalAddressDisplay> getPostalAddressDisplays() {
		if (_account.getPostalAddresses() == null) {
			return Collections.emptyList();
		}

		return TransformUtil.transformToList(
			_account.getPostalAddresses(),
			postalAddress -> new PostalAddressDisplay(
				_portletRequest, _portletResponse, postalAddress));
	}

	public String getPrimaryCountry() {
		PostalAddress[] postalAddresses = _account.getPostalAddresses();

		if (postalAddresses != null) {
			for (PostalAddress postalAddress : postalAddresses) {
				if ((postalAddress.getPrimary() != null) &&
					postalAddress.getPrimary()) {

					return postalAddress.getAddressCountry();
				}
			}
		}

		return StringPool.BLANK;
	}

	public String getRegion() {
		Account.Region region = _account.getRegion();

		if (region != null) {
			return region.toString();
		}

		return StringPool.DASH;
	}

	public String getSalesforceProjectKey() {
		return _getExternalLinkEntityId("salesforce", "project");
	}

	public String getSLAName() {
		ProductPurchase slaProductPurchase = _getSLAProductPurchase();

		if (slaProductPurchase != null) {
			Product product = slaProductPurchase.getProduct();

			return StringUtil.removeSubstring(
				product.getName(), " Subscription");
		}

		return StringPool.DASH;
	}

	public String getStatus() {
		Account.Status status = _account.getStatus();

		if (status != null) {
			return status.toString();
		}

		return StringPool.DASH;
	}

	public String getStatusStyle() {
		Account.Status status = _account.getStatus();

		if (status == Account.Status.APPROVED) {
			return "label-success";
		}
		else if (status == Account.Status.CLOSED) {
			return "label-secondary";
		}
		else if (status == Account.Status.INACTIVE) {
			return "label-warning";
		}
		else {
			return "label-danger";
		}
	}

	public String getSupportEndDate() {
		ProductPurchase slaProductPurchase = _getSLAProductPurchase();

		if (slaProductPurchase != null) {
			if (slaProductPurchase.getPerpetual()) {
				return LanguageUtil.get(_httpServletRequest, "perpetual");
			}

			return _dateFormat.format(slaProductPurchase.getEndDate());
		}

		return StringPool.BLANK;
	}

	public String getTier() {
		Account.Tier tier = _account.getTier();

		if (tier != null) {
			return tier.toString();
		}

		return StringPool.DASH;
	}

	public boolean isEWSA() throws Exception {
		if (_ancestorAccounts == null) {
			_ancestorAccounts = _accountReader.getAncestorAccounts(_account);
		}

		for (Account account : _ancestorAccounts) {
			if (_isEWSA(account)) {
				return true;
			}
		}

		return false;
	}

	private String _getExternalLinkEntityId(String domain, String entityName) {
		ExternalLink[] externalLinks = _account.getExternalLinks();

		if (externalLinks != null) {
			for (ExternalLink externalLink : externalLinks) {
				if (domain.equals(externalLink.getDomain()) &&
					entityName.equals(externalLink.getEntityName())) {

					return externalLink.getEntityId();
				}
			}
		}

		return StringPool.DASH;
	}

	private ProductPurchase _getSLAProductPurchase() {
		if (_slaProductPurchase != null) {
			return _slaProductPurchase;
		}

		return _accountReader.getSLAProductPurchase(_account);
	}

	private boolean _isEWSA(Account account) throws Exception {
		ProductPurchase[] productPurchases = account.getProductPurchases();

		if (productPurchases != null) {
			for (ProductPurchase productPurchase : productPurchases) {
				Product product = productPurchase.getProduct();

				String name = product.getName();

				if (name.equals(ProductConstants.NAME_DXP_EWSA) ||
					name.equals(ProductConstants.NAME_PORTAL_EWSA)) {

					return true;
				}
			}
		}

		return false;
	}

	private final Account _account;
	private final AccountReader _accountReader;
	private List<Account> _ancestorAccounts;
	private final Format _dateFormat;
	private final Format _dateTimeFormat;
	private final Team _firstLineSupportTeam;
	private final HttpServletRequest _httpServletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private final Team _partnerTeam;
	private final PortletRequest _portletRequest;
	private final PortletResponse _portletResponse;
	private ProductPurchase _slaProductPurchase;

}