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

package com.liferay.osb.provisioning.distributed.messaging.internal.subscribing;

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.distributed.messaging.internal.configuration.ProvisioningDistributedMessagingConfigurationUtil;
import com.liferay.osb.provisioning.distributed.messaging.internal.configuration.ProvisioningDistributedMessagingConfigurationValues;
import com.liferay.osb.provisioning.distributed.messaging.internal.constants.SalesforceConstants;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.zendesk.model.ZendeskTicket;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.petra.content.ContentUtil;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.portal.kernel.util.Validator;

import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "topic.pattern=dossiera.provisioning.create",
	service = DossieraCreateMessageSubscriber.class
)
public class DossieraCreateMessageSubscriber extends BaseMessageSubscriber {

	protected void checkWarnings(
		String accountKey, Account account, int contactCount,
		String salesforceOpportunityTypeName, int salesforceOpportunityType) {

		if (Validator.isNull(accountKey) &&
			(salesforceOpportunityType ==
				SalesforceConstants.OPPORTUNITY_TYPE_EXISTING_BUSINESS)) {

			_logWarning(
				"The opportunity type is " + salesforceOpportunityTypeName +
					" and the project does not exists");
		}

		if (Validator.isNotNull(accountKey) &&
			((salesforceOpportunityType ==
				SalesforceConstants.OPPORTUNITY_TYPE_NEW_BUSINESS) ||
			 (salesforceOpportunityType ==
				 SalesforceConstants.
					 OPPORTUNITY_TYPE_NEW_PROJECT_EXISTING_BUSINESS))) {

			_logWarning(
				"The opportunity type is " + salesforceOpportunityTypeName +
					" and the project already exists");
		}

		int maxDeveloperCount = _accountReader.getMaxDeveloperCount(account);

		if (contactCount > maxDeveloperCount) {
			_logWarning(
				"Maximum contacts is " + maxDeveloperCount + " but there are " +
					contactCount + " contacts");
		}
	}

	protected Account createAccount(
			PostalAddress postalAddress, Contact[] contacts,
			ExternalLink[] externalLinks, ProductPurchase[] productPurchases,
			JSONObject jsonObject)
		throws Exception {

		JSONObject accountJSONObject = jsonObject.getJSONObject("_account");

		Account account = new Account();

		String accountName = accountJSONObject.getString("_name");

		account.setName(accountName);

		List<Account> duplicateAccounts = _accountWebService.search(
			StringPool.BLANK, "name eq '" + accountName + "'", 0, 1, null);

		if (!duplicateAccounts.isEmpty()) {
			_logWarning("Account name must be unique");
		}

		if (accountName.contains(StringPool.PIPE)) {
			_logWarning("Account name must not contain the | character");
		}

		JSONObject projectJSONObject = jsonObject.getJSONObject("_project");

		String projectName = null;

		if (projectJSONObject != null) {
			projectName = projectJSONObject.getString("_name");
		}

		account.setCode(_getCode(accountName, projectName));

		JSONObject ownerJSONObject = jsonObject.getJSONObject("_owner");

		if (ownerJSONObject != null) {
			account.setContactEmailAddress(
				ownerJSONObject.getString("_emailAddress"));
		}

		account.setContacts(contacts);
		account.setExternalLinks(externalLinks);
		account.setPostalAddresses(new PostalAddress[] {postalAddress});
		account.setProductPurchases(productPurchases);

		String soldBy = jsonObject.getString("_salesforceOpportunitySoldBy");

		Account.Region region = getSupportRegion(
			soldBy, postalAddress.getAddressCountry());

		account.setRegion(region);

		return _accountWebService.addAccount(
			StringPool.BLANK, StringPool.BLANK, account);
	}

	protected void createZendeskTicket(
			Account account, PostalAddress postalAddress,
			String salesforceOpportunityTypeName,
			String salesforceOpportunityKey)
		throws PortalException {

		ZendeskTicket zendeskTicket = new ZendeskTicket();

		Map<Long, String> customFields = new HashMap<>();

		customFields.put(
			ProvisioningDistributedMessagingConfigurationValues.
				ZENDESK_CUSTOM_FIELD_OPPORTUNITY_OWNER_ID,
			account.getContactEmailAddress());
		customFields.put(
			ProvisioningDistributedMessagingConfigurationValues.
				ZENDESK_CUSTOM_FIELD_PRIMARY_ADDRESS_COUNTRY_ID,
			postalAddress.getAddressCountry());
		customFields.put(
			ProvisioningDistributedMessagingConfigurationValues.
				ZENDESK_CUSTOM_FIELD_PRODUCT_ID,
			"Provisioning Request");
		customFields.put(
			ProvisioningDistributedMessagingConfigurationValues.
				ZENDESK_CUSTOM_FIELD_SUPPORT_REGION_ID,
			account.getRegionAsString());

		zendeskTicket.setCustomFields(customFields);

		StringBundler sb = new StringBundler(14);

		sb.append("Account Name: ");
		sb.append(account.getName());
		sb.append("<br />Account Code: ");
		sb.append(account.getCode());
		sb.append("<br />Opportunity Type: ");
		sb.append(salesforceOpportunityTypeName);
		sb.append("<br />Date Created: ");
		sb.append(account.getDateCreated());
		sb.append("<br />Provisioning Account Link: <a href='");

		Group group = _groupLocalService.getFriendlyURLGroup(
			_portal.getDefaultCompanyId(), "/control_panel");

		Map<String, String[]> params = new LinkedHashMap<>();

		params.put(
			StringPool.UNDERLINE + ProvisioningPortletKeys.ACCOUNTS +
				"_mvcRenderCommandName",
			new String[] {"/accounts/view_account"});
		params.put(
			StringPool.UNDERLINE + ProvisioningPortletKeys.ACCOUNTS +
				"_accountKey",
			new String[] {account.getKey()});

		sb.append(
			_portal.getControlPanelFullURL(
				group.getGroupId(), ProvisioningPortletKeys.ACCOUNTS, params));

		sb.append("'>Provisioning Account</a><br />Salesforce Opportunity ");
		sb.append("Link: <a href='https://login.salesforce.com/");
		sb.append(salesforceOpportunityKey);
		sb.append("'>Salesforce Opportunity</a>");

		String subject = "New Subscription for " + account.getName();

		List<String> warningMessages = _warningMessagesThreadLocal.get();

		if (!warningMessages.isEmpty()) {
			sb.append("<br /><br />Warnings: ");

			for (String warningMessage : warningMessages) {
				sb.append("<br />");
				sb.append(warningMessage);
			}

			subject = StringUtil.insert(subject, "[Warning] ", 0);
		}

		zendeskTicket.setDescription(sb.toString());

		zendeskTicket.setRequesterId(
			ProvisioningDistributedMessagingConfigurationValues.
				PROVISIONING_ZENDESK_REQUESTER_ID);
		zendeskTicket.setSubject(subject);
		zendeskTicket.setZendeskOrganizationId(
			ProvisioningDistributedMessagingConfigurationValues.
				PROVISIONING_ZENDESK_ORGANIZATION_ID);

		_zendeskTicketWebService.createZendeskTicket(zendeskTicket);
	}

	@Override
	protected void doParse(JSONObject jsonObject) throws Exception {
		if (!hasOpportunityProductFamily(jsonObject)) {
			return;
		}

		_warningMessagesThreadLocal.set(new ArrayList<String>());

		String salesforceOpportunityStageName = jsonObject.getString(
			"_salesforceOpportunityStageName");

		String salesforceOpportunityTypeName = jsonObject.getString(
			"_salesforceOpportunityType");

		int salesforceOpportunityType = getSalesforceOpportunityType(
			salesforceOpportunityTypeName);

		if (!_isValidOpportunity(
				salesforceOpportunityStageName, salesforceOpportunityType)) {

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Opportunity is not closed won or a renewal that is " +
						"closed lost");
			}

			return;
		}

		List<Contact> contacts = parseContacts(jsonObject);

		List<ProductPurchase> productPurchases = parseProductPurchases(
			jsonObject);

		boolean analyticsCloud = hasAnalyticsCloud(productPurchases);

		PostalAddress postalAddress = parseAddress(jsonObject);

		Account account = null;

		String accountKey = getAccountKey(jsonObject);

		if (Validator.isNotNull(accountKey)) {
			account = updateAccount(accountKey, contacts, productPurchases);
		}
		else {
			ExternalLink[] externalLinks = parseExternalLinks(jsonObject);

			account = createAccount(
				postalAddress, contacts.toArray(new Contact[0]), externalLinks,
				productPurchases.toArray(new ProductPurchase[0]), jsonObject);

			if (analyticsCloud) {
				sendAnalyticsCloudWelcomeEmail(contacts);
			}
		}

		checkWarnings(
			accountKey, account, contacts.size(), salesforceOpportunityTypeName,
			salesforceOpportunityType);

		String salesforceOpportunityKey = jsonObject.getString(
			"_salesforceOpportunityKey");

		createZendeskTicket(
			account, postalAddress, salesforceOpportunityTypeName,
			salesforceOpportunityKey);

		for (Contact contact : contacts) {

			// TODO
			// Okta Integration

			boolean oktaContact = true;

			if (!oktaContact) {
				sendUserCreationEmail(contact, account, analyticsCloud);
			}
		}
	}

	protected String getAccountKey(JSONObject jsonObject) throws Exception {
		JSONObject accountJSONObject = jsonObject.getJSONObject("_account");

		String dossieraAccountKey = accountJSONObject.getString(
			"_dossieraAccountKey");

		List<Account> accounts = _accountWebService.getAccounts(
			ExternalLinkDomain.DOSSIERA,
			ExternalLinkEntityName.DOSSIERA_ACCOUNT, dossieraAccountKey, 1, 1);

		if (!accounts.isEmpty()) {
			Account account = accounts.get(0);

			return account.getKey();
		}

		return null;
	}

	protected String getContactFullName(Contact contact) {
		StringBundler sb = new StringBundler(5);

		if (Validator.isNotNull(contact.getFirstName())) {
			sb.append(contact.getFirstName());
		}

		if (Validator.isNotNull(contact.getMiddleName())) {
			if (sb.length() > 0) {
				sb.append(StringPool.SPACE);
			}

			sb.append(contact.getMiddleName());
		}

		if (Validator.isNotNull(contact.getLastName())) {
			if (sb.length() > 0) {
				sb.append(StringPool.SPACE);
			}

			sb.append(contact.getLastName());
		}

		return sb.toString();
	}

	protected PostalAddress getPostalAddress(JSONObject jsonObject) {
		PostalAddress postalAddress = new PostalAddress();

		String city = jsonObject.getString("_city");

		city = ModelHintsUtil.trimString(Address.class.getName(), "city", city);

		postalAddress.setAddressLocality(city);

		String countryName = jsonObject.getString("_country");
		String regionName = jsonObject.getString("_region");

		if (Validator.isNotNull(countryName)) {
			postalAddress.setAddressCountry(countryName);
			postalAddress.setAddressRegion(regionName);
		}

		String street = jsonObject.getString("_street");

		String street1 = street;

		String street2 = StringPool.BLANK;
		String street3 = StringPool.BLANK;

		int maxLength = ModelHintsUtil.getMaxLength(
			Address.class.getName(), "street1");

		if (street1.length() > maxLength) {
			street1 = street1.substring(0, maxLength);

			street2 = street.substring(maxLength);

			if (street2.length() > maxLength) {
				street2 = street2.substring(0, maxLength);

				street3 = street.substring(maxLength * 2);

				if (street3.length() > maxLength) {
					street3 = street3.substring(0, maxLength);
				}
			}
		}

		postalAddress.setStreetAddressLine1(street1);
		postalAddress.setStreetAddressLine2(street2);
		postalAddress.setStreetAddressLine3(street3);

		postalAddress.setPostalCode(jsonObject.getString("_postalCode"));

		return postalAddress;
	}

	protected int getSalesforceOpportunityType(
		String salesforceOpportunityTypeName) {

		if (StringUtil.equalsIgnoreCase(
				salesforceOpportunityTypeName, "Existing Business")) {

			return SalesforceConstants.OPPORTUNITY_TYPE_EXISTING_BUSINESS;
		}
		else if (StringUtil.equalsIgnoreCase(
					salesforceOpportunityTypeName, "New Business")) {

			return SalesforceConstants.OPPORTUNITY_TYPE_NEW_BUSINESS;
		}
		else if (StringUtil.equalsIgnoreCase(
					salesforceOpportunityTypeName, "Renewal")) {

			return SalesforceConstants.OPPORTUNITY_TYPE_RENEWAL;
		}
		else if (StringUtil.equalsIgnoreCase(
					salesforceOpportunityTypeName,
					"New Project Existing Business")) {

			return SalesforceConstants.
				OPPORTUNITY_TYPE_NEW_PROJECT_EXISTING_BUSINESS;
		}
		else {
			return 0;
		}
	}

	protected Account.Region getSupportRegion(
		String soldBy, String countryName) {

		if (Validator.isNull(soldBy)) {
			_logWarning(
				"Sold by field is empty. Defaulting support region to global.");

			return Account.Region.GLOBAL;
		}

		if (soldBy.equals("Liferay Africa") ||
			soldBy.equals("Liferay France") ||
			soldBy.equals("Liferay Germany") ||
			soldBy.equals("Liferay Hungary") ||
			soldBy.equals("Liferay International") ||
			soldBy.equals("Liferay Middle East") ||
			soldBy.equals("Liferay Netherlands") ||
			soldBy.equals("Liferay Nordic") || soldBy.equals("Liferay UK")) {

			return Account.Region.HUNGARY;
		}
		else if (soldBy.equals("Liferay Australia")) {
			return Account.Region.AUSTRALIA;
		}
		else if (soldBy.equals("Liferay Brazil")) {
			return Account.Region.BRAZIL;
		}
		else if (soldBy.equals("Liferay Canada") ||
				 soldBy.equals("Liferay US")) {

			return Account.Region.UNITED_STATES;
		}
		else if (soldBy.equals("Liferay China") ||
				 soldBy.equals("Liferay Singapore")) {

			return Account.Region.CHINA;
		}
		else if (soldBy.equals("Liferay India")) {
			return Account.Region.INDIA;
		}
		else if (soldBy.equals("Liferay Japan")) {
			return Account.Region.JAPAN;
		}
		else if (soldBy.equals("Liferay Spain")) {
			if (Validator.isNotNull(countryName) &&
				(countryName.equals("Cypress") ||
				 countryName.equals("Greece") || countryName.equals("Italy"))) {

				return Account.Region.HUNGARY;
			}

			return Account.Region.SPAIN;
		}

		_logWarning(
			"Unable to find matching support region for " + soldBy + " and " +
				countryName + ". Defaulting support region to global.");

		return Account.Region.GLOBAL;
	}

	protected boolean hasAnalyticsCloud(
		List<ProductPurchase> productPurchases) {

		for (ProductPurchase productPurchase : productPurchases) {
			Product product = productPurchase.getProduct();

			String name = product.getName();

			if (name.equals(
					"Liferay Analytics Cloud Subscription - Business") ||
				name.equals(
					"Liferay Analytics Cloud Subscription - Enterprise")) {

				return true;
			}
		}

		return false;
	}

	protected boolean hasOpportunityProductFamily(JSONObject jsonObject) {
		String salesforceOpportunityProductFamily = jsonObject.getString(
			"_salesforceOpportunityProductFamily");

		if (Validator.isNull(salesforceOpportunityProductFamily)) {
			return false;
		}

		for (String productFamilyToken : _PRODUCT_FAMILY_TOKENS) {
			if (salesforceOpportunityProductFamily.contains(
					productFamilyToken)) {

				return true;
			}
		}

		return false;
	}

	protected PostalAddress parseAddress(JSONObject jsonObject) {
		JSONObject billingAddressJSONObject = jsonObject.getJSONObject(
			"_billingAddress");
		JSONObject shippingAddressJSONObject = jsonObject.getJSONObject(
			"_shippingAddress");

		PostalAddress postalAddress = null;

		if (shippingAddressJSONObject != null) {
			postalAddress = getPostalAddress(shippingAddressJSONObject);
		}
		else if (billingAddressJSONObject != null) {
			postalAddress = getPostalAddress(billingAddressJSONObject);
		}
		else {
			postalAddress = new PostalAddress();
		}

		if (Validator.isNull(postalAddress.getAddressLocality())) {
			postalAddress.setAddressLocality("N/A");
		}

		if (Validator.isNull(postalAddress.getStreetAddressLine1())) {
			postalAddress.setStreetAddressLine1("N/A");
		}

		if (Validator.isNull(postalAddress.getPostalCode())) {
			postalAddress.setPostalCode("N/A");
		}

		return postalAddress;
	}

	protected List<Contact> parseContacts(JSONObject jsonObject)
		throws PortalException {

		JSONArray contactsJSONArray = jsonObject.getJSONArray("_contacts");

		if (contactsJSONArray == null) {
			return Collections.emptyList();
		}

		ArrayList<Contact> contacts = new ArrayList<>(
			contactsJSONArray.length());

		for (int i = 0; i < contactsJSONArray.length(); i++) {
			JSONObject contactJSONObject = contactsJSONArray.getJSONObject(i);

			Contact contact = new Contact();

			contact.setFirstName(contactJSONObject.getString("_firstName"));
			contact.setLastName(contactJSONObject.getString("_lastName"));
			contact.setEmailAddress(
				contactJSONObject.getString("_emailAddress"));

			String role = contactJSONObject.getString("_role");

			if (Validator.isNull(role)) {
				role = ContactRoleConstants.NAME_MEMBER;
			}

			ContactRole contactRole = new ContactRole();

			contactRole.setName(role);
			contactRole.setType(ContactRole.Type.ACCOUNT_CUSTOMER);

			contact.setContactRoles(new ContactRole[] {contactRole});

			contacts.add(contact);
		}

		return contacts;
	}

	protected ExternalLink[] parseExternalLinks(JSONObject jsonObject) {
		String salesforceAccountKey = jsonObject.getString(
			"_salesforceAccountKey");

		ExternalLink accountExternalLink = new ExternalLink();

		accountExternalLink.setDomain(ExternalLinkDomain.SALESFORCE);
		accountExternalLink.setEntityName(
			ExternalLinkEntityName.SALESFORCE_ACCOUNT);
		accountExternalLink.setEntityId(salesforceAccountKey);

		JSONObject accountJSONObject = jsonObject.getJSONObject("_account");

		String dossieraAccountKey = accountJSONObject.getString(
			"_dossieraAccountKey");

		ExternalLink dossieraExternalLink = new ExternalLink();

		dossieraExternalLink.setDomain(ExternalLinkDomain.DOSSIERA);
		dossieraExternalLink.setEntityName(
			ExternalLinkEntityName.DOSSIERA_ACCOUNT);
		dossieraExternalLink.setEntityId(dossieraAccountKey);

		String salesforceProjectKey = jsonObject.getString(
			"_salesforceProjectKey");

		if (Validator.isNull(salesforceProjectKey)) {
			return new ExternalLink[] {
				accountExternalLink, dossieraExternalLink
			};
		}

		ExternalLink projectExternalLink = new ExternalLink();

		projectExternalLink.setDomain(ExternalLinkDomain.SALESFORCE);
		projectExternalLink.setEntityName(
			ExternalLinkEntityName.SALESFORCE_PROJECT);
		projectExternalLink.setEntityId(salesforceProjectKey);

		return new ExternalLink[] {
			accountExternalLink, dossieraExternalLink, projectExternalLink
		};
	}

	protected List<ProductPurchase> parseProductPurchases(JSONObject jsonObject)
		throws Exception {

		JSONArray bundledProductsJSONArray = jsonObject.getJSONArray(
			"_bundledProducts");

		if (bundledProductsJSONArray == null) {
			return Collections.emptyList();
		}

		List<ProductPurchase> productPurchases = new ArrayList<>();

		for (int i = 0; i < bundledProductsJSONArray.length(); i++) {
			JSONObject bundledProductJSONObject =
				bundledProductsJSONArray.getJSONObject(i);

			JSONArray purchasedProductsJSONArray =
				bundledProductJSONObject.getJSONArray("_purchasedProducts");

			for (int j = 0; j < purchasedProductsJSONArray.length(); j++) {
				JSONObject purchasedProductJSONObject =
					purchasedProductsJSONArray.getJSONObject(j);

				ProductPurchase productPurchase = new ProductPurchase();

				Date endDate = _portal.getDate(
					purchasedProductJSONObject.getInt("_endMonth") - 1,
					purchasedProductJSONObject.getInt("_endDay"),
					purchasedProductJSONObject.getInt("_endYear"));

				productPurchase.setEndDate(endDate);

				Date startDate = _portal.getDate(
					purchasedProductJSONObject.getInt("_startMonth") - 1,
					purchasedProductJSONObject.getInt("_startDay"),
					purchasedProductJSONObject.getInt("_startYear"));

				productPurchase.setStartDate(startDate);

				Product product = _getProduct(
					purchasedProductJSONObject.getString("_name"));

				productPurchase.setProduct(product);

				Map<String, String> properties = new HashMap<>();

				String environment = purchasedProductJSONObject.getString(
					"_environment");

				if (Validator.isNotNull(environment)) {
					properties.put("environment", environment);
				}

				String productType = purchasedProductJSONObject.getString(
					"_productType");

				if (Validator.isNotNull(productType)) {
					properties.put("productType", productType);
				}

				int quantity = purchasedProductJSONObject.getInt("_quantity");

				if (quantity > 0) {
					properties.put("quantity", String.valueOf(quantity));
				}

				String sizing = purchasedProductJSONObject.getString("_sizing");

				if (Validator.isNotNull(sizing)) {
					properties.put("sizing", sizing);
				}

				if (!properties.isEmpty()) {
					productPurchase.setProperties(properties);
				}

				productPurchases.add(productPurchase);
			}
		}

		return productPurchases;
	}

	protected void sendAnalyticsCloudWelcomeEmail(List<Contact> contacts)
		throws PortalException {

		for (Contact contact : contacts) {
			String body = _getEmailTemplate(
				"email_analytics_cloud_welcome_body_" +
					contact.getLanguageId() + ".tmpl",
				"email_analytics_cloud_welcome_body.tmpl");
			String subject = _getEmailTemplate(
				"email_analytics_cloud_welcome_subject_" +
					contact.getLanguageId() + ".tmpl",
				"email_analytics_cloud_welcome_subject.tmpl");

			SubscriptionSender subscriptionSender = new SubscriptionSender();

			subscriptionSender.setBody(body);
			subscriptionSender.setCompanyId(_portal.getDefaultCompanyId());
			subscriptionSender.setFrom(
				"no-reply@liferay.com", "Liferay Analytics Cloud");
			subscriptionSender.setHtmlFormat(true);
			subscriptionSender.setMailId(
				"analytics_cloud_welcome", contact.getKey());
			subscriptionSender.setReplyToAddress("no-reply@liferay.com");
			subscriptionSender.setSubject(subject);

			subscriptionSender.addRuntimeSubscribers(
				contact.getEmailAddress(), getContactFullName(contact));

			subscriptionSender.flushNotificationsAsync();
		}
	}

	protected void sendUserCreationEmail(
		Contact contact, Account account, boolean analyticsCloud) {

		StringBundler sb = new StringBundler(2);

		if (analyticsCloud) {
			sb.append("Analytics Cloud, ");
		}

		sb.append(
			"Customer Portal, all of our downloads, and our support system");

		String provisioningEmailAddress =
			ProvisioningDistributedMessagingConfigurationUtil.get(
				ProvisioningDistributedMessagingConfigurationValues.
					PROVISIONING_EMAIL_ADDRESS,
				new Filter(account.getRegionAsString()));

		if (Validator.isNull(provisioningEmailAddress)) {
			provisioningEmailAddress =
				ProvisioningDistributedMessagingConfigurationUtil.get(
					ProvisioningDistributedMessagingConfigurationValues.
						PROVISIONING_EMAIL_ADDRESS,
					new Filter("Global"));
		}

		String body = _getEmailTemplate(
			"email_provisioning_create_account_body_" +
				contact.getLanguageId() + ".tmpl",
			"email_provisioning_create_account_body.tmpl");
		String subject = _getEmailTemplate(
			"email_provisioning_create_account_subject_" +
				contact.getLanguageId() + ".tmpl",
			"email_provisioning_create_account_subject.tmpl");

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setBody(body);
		subscriptionSender.setCompanyId(_portal.getDefaultCompanyId());
		subscriptionSender.setContextAttributes(
			"[$ACCOUNT_ENTRY_NAME$]", account.getName(),
			"[$SUBSCRIPTION_SERVICES$]", sb.toString());
		subscriptionSender.setFrom(
			provisioningEmailAddress, "Liferay Provisioning");
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setMailId("provisioning");
		subscriptionSender.setReplyToAddress(provisioningEmailAddress);
		subscriptionSender.setSubject(subject);

		subscriptionSender.addRuntimeSubscribers(
			contact.getEmailAddress(), getContactFullName(contact));
		subscriptionSender.addRuntimeSubscribers(
			provisioningEmailAddress, getContactFullName(contact));

		subscriptionSender.flushNotificationsAsync();
	}

	protected Account updateAccount(
			String accountKey, List<Contact> contacts,
			List<ProductPurchase> productPurchases)
		throws Exception {

		for (Contact contact : contacts) {
			Contact curContact = _contactWebService.fetchContactByEmailAddress(
				contact.getEmailAddress());

			if (curContact == null) {
				_contactWebService.addContact(
					StringPool.BLANK, StringPool.BLANK, contact);
			}

			ContactRole[] contactRoles = contact.getContactRoles();

			String[] contactRoleKeys = new String[contactRoles.length];

			for (int i = 0; i < contactRoles.length; i++) {
				ContactRole contactRole = contactRoles[i];

				ContactRole.Type type = contactRole.getType();

				ContactRole curContactRole =
					_contactRoleWebService.fetchContactRole(
						type.toString(), contactRole.getName());

				if (curContactRole == null) {
					curContactRole = _contactRoleWebService.addContactRole(
						StringPool.BLANK, StringPool.BLANK, contactRole);
				}

				contactRoleKeys[i] = curContactRole.getKey();
			}

			_accountWebService.assignContactRoles(
				StringPool.BLANK, StringPool.BLANK, accountKey,
				contact.getEmailAddress(), contactRoleKeys);
		}

		for (ProductPurchase productPurchase : productPurchases) {
			_productPurchaseWebService.addProductPurchase(
				StringPool.BLANK, StringPool.BLANK, accountKey,
				productPurchase);
		}

		return _accountWebService.getAccount(accountKey);
	}

	private static String _getEmailTemplate(
		String templateName, String defaultTemplateName) {

		ClassLoader classLoader =
			DossieraCreateMessageSubscriber.class.getClassLoader();

		String templateDirName =
			"com/liferay/osb/provisioning/distributed/messaging/internal" +
				"/dependencies/";

		URL url = classLoader.getResource(templateDirName + templateName);

		if (url != null) {
			return ContentUtil.get(
				DossieraCreateMessageSubscriber.class.getClassLoader(),
				templateDirName + templateName);
		}

		return ContentUtil.get(
			DossieraCreateMessageSubscriber.class.getClassLoader(),
			templateDirName + defaultTemplateName);
	}

	private String _getCode(String parentAccountName, String accountName)
		throws Exception {

		String code = StringUtil.extractChars(parentAccountName);

		if (code.length() > 6) {
			code = code.substring(0, 6);
		}

		if (accountName != null) {
			code += StringUtil.extractChars(accountName);
		}

		if (code.length() > 12) {
			code = code.substring(0, 12);
		}

		code = StringUtil.toUpperCase(code);

		if (!_isDuplicateCode(code)) {
			return code;
		}

		int i = 1;

		while (true) {
			String tempCode = code + i;

			if (!_isDuplicateCode(tempCode)) {
				return tempCode;
			}

			i++;
		}
	}

	private Product _getProduct(String productName) throws Exception {
		List<Product> products = _productWebService.getProducts(
			ExternalLinkDomain.DOSSIERA,
			ExternalLinkEntityName.DOSSIERA_PRODUCT, productName, 1, 1);

		if (!products.isEmpty()) {
			return products.get(0);
		}

		Product product = new Product();

		product.setName(productName);

		ExternalLink externalLink = new ExternalLink();

		externalLink.setDomain(ExternalLinkDomain.DOSSIERA);
		externalLink.setEntityName(ExternalLinkEntityName.DOSSIERA_PRODUCT);
		externalLink.setEntityId(productName);

		product.setExternalLinks(new ExternalLink[] {externalLink});

		return product;
	}

	private boolean _isDuplicateCode(String code) throws Exception {
		List<Account> accounts = _accountWebService.search(
			StringPool.BLANK, "code eq '" + code + "'", 1, 1, null);

		if (!accounts.isEmpty()) {
			return true;
		}

		return false;
	}

	private boolean _isValidOpportunity(
		String salesforceOpportunityStageName, int salesforceOpportunityType) {

		if (salesforceOpportunityStageName.equals(
				SalesforceConstants.OPPORTUNITY_STAGE_CLOSED_LOST) &&
			(salesforceOpportunityType ==
				SalesforceConstants.OPPORTUNITY_TYPE_RENEWAL)) {

			return true;
		}

		if (salesforceOpportunityStageName.equals(
				SalesforceConstants.OPPORTUNITY_STAGE_CLOSED_WON) &&
			(salesforceOpportunityType !=
				SalesforceConstants.OPPORTUNITY_TYPE_RENEWAL)) {

			return true;
		}

		return false;
	}

	private void _logWarning(String s) {
		List<String> warningMessages = _warningMessagesThreadLocal.get();

		warningMessages.add(s);
	}

	private static final String[] _PRODUCT_FAMILY_TOKENS = {"E", "P", "S"};

	private static final Log _log = LogFactoryUtil.getLog(
		DossieraCreateMessageSubscriber.class);

	private static final ThreadLocal<ArrayList<String>>
		_warningMessagesThreadLocal = new CentralizedThreadLocal<>(
			DossieraCreateMessageSubscriber.class +
				"._warningMessagesThreadLocal");

	@Reference
	private AccountReader _accountReader;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private ProductPurchaseWebService _productPurchaseWebService;

	@Reference
	private ProductWebService _productWebService;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

}