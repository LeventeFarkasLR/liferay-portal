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
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.text.Format;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ProductPurchaseDisplay {

	public ProductPurchaseDisplay(
		HttpServletRequest httpServletRequest, ProductPurchase productPurchase,
		long productConsumptionsCount) {

		_httpServletRequest = httpServletRequest;
		_productPurchase = productPurchase;

		_dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy");

		_provisionedCount = productConsumptionsCount;

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

	public Date getEndDate() {
		return _productPurchase.getEndDate();
	}

	public String getGracePeriod() {
		if (_productPurchase.getPerpetual()) {
			return StringPool.DASH;
		}

		StringBundler sb = new StringBundler(3);

		sb.append(_dateFormat.format(_productPurchase.getOriginalEndDate()));
		sb.append(" - ");
		sb.append(_dateFormat.format(_productPurchase.getEndDate()));

		return sb.toString();
	}

	public String getKey() {
		return _productPurchase.getKey();
	}

	public Date getOriginalEndDate() {
		return _productPurchase.getOriginalEndDate();
	}

	public String getProductName() {
		Product product = _productPurchase.getProduct();

		return product.getName();
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

	public Date getStartDate() {
		return _productPurchase.getStartDate();
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

	public boolean isPerpetual() {
		if (_productPurchase.getStartDate() == null) {
			return true;
		}

		return false;
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
	private final long _provisionedCount;
	private final String _salesforceOpportunityKey;
	private final String _salesforceOpportunityURL;
	private final int _sizing;

}