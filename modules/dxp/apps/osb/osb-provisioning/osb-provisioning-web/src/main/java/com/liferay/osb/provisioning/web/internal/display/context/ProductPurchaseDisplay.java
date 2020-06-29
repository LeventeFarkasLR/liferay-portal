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

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.text.Format;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ProductPurchaseDisplay {

	public ProductPurchaseDisplay(
		HttpServletRequest httpServletRequest, ProductPurchase productPurchase,
		List<ProductConsumption> productConsumptions) {

		_httpServletRequest = httpServletRequest;
		_productPurchase = productPurchase;

		_dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy");

		if (productConsumptions != null) {
			_provisionedCount = productConsumptions.size();
		}
		else {
			_provisionedCount = 0;
		}

		ExternalLink externalLink = _getSalesforceopportunityExternalLink(
			productPurchase);

		if (externalLink != null) {
			_salesforceOpportunityKey = externalLink.getEntityId();
			_salesforceOpportunityURL = externalLink.getUrl();
		}
		else {
			_salesforceOpportunityKey = StringPool.BLANK;
			_salesforceOpportunityURL = StringPool.BLANK;
		}

		Map<String, String> properties = productPurchase.getProperties();

		if (properties != null) {
			_sizing = GetterUtil.getInteger(properties.get("sizing"));
		}
		else {
			_sizing = 0;
		}
	}

	public String getGracePeriod() {
		if ((_productPurchase.getStartDate() == null) ||
			(_productPurchase.getOriginalEndDate() == null)) {

			return LanguageUtil.get(_httpServletRequest, "perpetual");
		}

		StringBundler sb = new StringBundler(3);

		sb.append(_dateFormat.format(_productPurchase.getOriginalEndDate()));
		sb.append(" - ");

		if (_productPurchase.getEndDate() != null) {
			sb.append(_dateFormat.format(_productPurchase.getEndDate()));
		}
		else {
			sb.append(LanguageUtil.get(_httpServletRequest, "perpetual"));
		}

		return sb.toString();
	}

	public String getKey() {
		return _productPurchase.getKey();
	}

	public String getProvisionedCount() {
		return String.valueOf(_provisionedCount);
	}

	public String getQuantity() {
		return String.valueOf(_productPurchase.getQuantity());
	}

	public String getSalesforceOpportunityKey() {
		return _salesforceOpportunityKey;
	}

	public String getSalesforceOpportunityURL() {
		return _salesforceOpportunityURL;
	}

	public String getSizing() {
		if (_sizing > 0) {
			return String.valueOf(_sizing);
		}

		return StringPool.DASH;
	}

	public String getStatus() {
		ProductPurchase.Status status = _productPurchase.getStatus();

		return status.toString();
	}

	public String getStatusStyle() {
		ProductPurchase.Status status = _productPurchase.getStatus();

		if (status == ProductPurchase.Status.APPROVED) {
			return "label-success";
		}

		return "label-danger";
	}

	public String getSupportLife() {
		if (_productPurchase.getStartDate() == null) {
			return LanguageUtil.get(_httpServletRequest, "perpetual");
		}

		StringBundler sb = new StringBundler(3);

		sb.append(_dateFormat.format(_productPurchase.getStartDate()));
		sb.append(" - ");

		if (_productPurchase.getEndDate() != null) {
			sb.append(_dateFormat.format(_productPurchase.getEndDate()));
		}
		else {
			sb.append(LanguageUtil.get(_httpServletRequest, "perpetual"));
		}

		return sb.toString();
	}

	private ExternalLink _getSalesforceopportunityExternalLink(
		ProductPurchase productPurchase) {

		ExternalLink[] externalLinks = productPurchase.getExternalLinks();

		if (externalLinks != null) {
			for (ExternalLink externalLink : externalLinks) {
				String domain = externalLink.getDomain();
				String entityName = externalLink.getEntityName();

				if (domain.equals(ExternalLinkDomain.SALESFORCE) &&
					entityName.equals(
						ExternalLinkEntityName.SALESFORCE_OPPORTUNITY)) {

					return externalLink;
				}
			}
		}

		return null;
	}

	private final Format _dateFormat;
	private final HttpServletRequest _httpServletRequest;
	private final ProductPurchase _productPurchase;
	private final int _provisionedCount;
	private final String _salesforceOpportunityKey;
	private final String _salesforceOpportunityURL;
	private final int _sizing;

}