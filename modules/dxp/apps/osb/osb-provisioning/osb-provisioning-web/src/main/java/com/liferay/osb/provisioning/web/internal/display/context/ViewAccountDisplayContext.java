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

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItemList;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.provisioning.constants.ProvisioningActionKeys;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.customer.model.AccountEntry;
import com.liferay.osb.provisioning.customer.web.service.AccountEntryWebService;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.AuditEntryWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ExternalLinkWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.NoteWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.text.Format;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ViewAccountDisplayContext {

	public ViewAccountDisplayContext() {
	}

	public void addPortletBreadcrumbEntries() throws Exception {
		PortletURL accountsPortletURL = renderResponse.createRenderURL();

		PortalUtil.addPortletBreadcrumbEntry(
			httpServletRequest,
			LanguageUtil.get(httpServletRequest, "accounts"),
			accountsPortletURL.toString());

		List<Account> ancestorAccounts = accountReader.getAncestorAccounts(
			account);

		for (int i = ancestorAccounts.size() - 1; i >= 0; i--) {
			Account ancestorAccount = ancestorAccounts.get(i);

			PortletURL portletURL = renderResponse.createRenderURL();

			portletURL.setParameter(
				"mvcRenderCommandName", "/accounts/view_account");
			portletURL.setParameter("accountKey", ancestorAccount.getKey());

			PortalUtil.addPortletBreadcrumbEntry(
				httpServletRequest, ancestorAccount.getName(),
				portletURL.toString());
		}

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/view_account");
		portletURL.setParameter("accountKey", account.getKey());

		PortalUtil.addPortletBreadcrumbEntry(
			httpServletRequest, account.getName(), portletURL.toString());
	}

	public String getAccountAttachmentURL(long accountAttachmentId)
		throws Exception {

		return accountEntryWebService.getAccountAttachmentURL(
			accountAttachmentId);
	}

	public Map<String, Object> getAccountDetailsData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		data.put("details", getAccountDisplay());
		data.put("parentAccountName", getParentAccountName());

		List<String> statusNames = new ArrayList<>();

		for (Account.Status status : Account.Status.values()) {
			statusNames.add(status.toString());
		}

		data.put("statusNames", statusNames);

		List<String> tierNames = new ArrayList<>();

		for (Account.Tier tier : Account.Tier.values()) {
			tierNames.add(tier.toString());
		}

		data.put("tierNames", tierNames);

		return data;
	}

	public AccountDisplay getAccountDisplay() {
		return accountDisplay;
	}

	public AccountEntry getAccountEntry() throws Exception {
		return accountEntryWebService.fetchAccountEntry(account.getKey());
	}

	public List<AuditEntryDisplay> getAuditEntryDisplays() throws Exception {
		return TransformUtil.transform(
			auditEntryWebService.getAccountAuditEntries(
				account.getKey(), 1, 1000),
			auditEntry -> new AuditEntryDisplay(
				httpServletRequest, auditEntry));
	}

	public String getClearResultsURL() {
		PortletURL portletURL = getPortletURL();

		return portletURL.toString();
	}

	public String getCurrentURL() {
		return currentURLObj.toString();
	}

	public String getDeleteExternalLinkURL(String externalLinkKey) {
		PortletURL deleteExternalLinkURL = renderResponse.createActionURL();

		deleteExternalLinkURL.setParameter(
			ActionRequest.ACTION_NAME, "/edit_external_link");
		deleteExternalLinkURL.setParameter(Constants.CMD, Constants.DELETE);
		deleteExternalLinkURL.setParameter("externalLinkKey", externalLinkKey);

		return deleteExternalLinkURL.toString();
	}

	public String getEditTeamURL(String teamKey) {
		PortletURL editTeamURL = renderResponse.createActionURL();

		editTeamURL.setParameter(
			ActionRequest.ACTION_NAME, "/accounts/edit_account");
		editTeamURL.setParameter("teamKey", teamKey);

		return editTeamURL.toString();
	}

	public List<DropdownItem> getFilterCustomerRoleDropdownItems()
		throws Exception {

		return new DropdownItemList() {
			{
				addGroup(
					dropdownGroupItem -> {
						dropdownGroupItem.setDropdownItems(
							_getFilterCustomerRoleDropdownItems());
						dropdownGroupItem.setLabel(
							LanguageUtil.get(
								httpServletRequest, "filter-by-role"));
					});
			}
		};
	}

	public List<LabelItem> getFilterCustomerRoleLabelItems() {
		return new LabelItemList() {
			{
				String[] contactRoleKeys = ParamUtil.getStringValues(
					renderRequest, "contactRoleKeys");

				for (String contactRoleKey : contactRoleKeys) {
					add(
						labelItem -> {
							PortletURL removeLabelURL = PortletURLUtil.clone(
								currentURLObj, renderResponse);

							String[] removeContactRoleKeys = ArrayUtil.remove(
								contactRoleKeys, contactRoleKey);

							removeLabelURL.setParameter(
								"contactRoleKeys",
								StringUtil.merge(removeContactRoleKeys));

							labelItem.putData(
								"removeLabelURL", removeLabelURL.toString());

							labelItem.setCloseable(true);

							ContactRole contactRole =
								contactRoleWebService.getContactRole(
									contactRoleKey);

							String label = String.format(
								"%s: %s",
								LanguageUtil.get(
									httpServletRequest, "contact-role"),
								contactRole.getName());

							labelItem.setLabel(label);
						});
				}
			}
		};
	}

	public List<DropdownItem> getHeaderActionDropdownItems() {
		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						PortletURL workflowURL =
							renderResponse.createActionURL();

						workflowURL.setParameter(
							ActionRequest.ACTION_NAME,
							"/accounts/edit_account");
						workflowURL.setParameter("redirect", getCurrentURL());
						workflowURL.setParameter(
							"accountKey", account.getKey());
						workflowURL.setParameter("name", account.getName());

						if (account.getStatus() == Account.Status.CLOSED) {
							workflowURL.setParameter(
								"status", Account.Status.APPROVED.toString());
						}
						else {
							workflowURL.setParameter(
								"status", Account.Status.CLOSED.toString());
						}

						workflowURL.setParameter(
							"updateAccount", Boolean.TRUE.toString());

						dropdownItem.setHref(
							"javascript:" + renderResponse.getNamespace() +
								"updateStatus('" +
									HtmlUtil.escapeJS(workflowURL.toString()) +
										"');");

						if (account.getStatus() == Account.Status.CLOSED) {
							dropdownItem.setLabel(
								LanguageUtil.get(
									httpServletRequest, "activate-account"));
						}
						else {
							dropdownItem.setLabel(
								LanguageUtil.get(
									httpServletRequest, "close-account"));
						}
					});
			}
		};
	}

	public List<DropdownItem> getHeaderAddDropdownItems() {
		return new DropdownItemList() {
			{
				addGroup(
					dropdownGroupItem -> {
						dropdownGroupItem.setDropdownItems(
							_getHeaderAddDropdownItems());
						dropdownGroupItem.setSeparator(true);
					});
				addGroup(
					dropdownGroupItem -> dropdownGroupItem.setDropdownItems(
						_getHeaderAddSubscriptionsDropdownItems()));
			}
		};
	}

	public Map<String, Object> getPanelData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		PortletURL addNoteURL = renderResponse.createActionURL();

		addNoteURL.setParameter(ActionRequest.ACTION_NAME, "/edit_note");
		addNoteURL.setParameter("accountKey", account.getKey());

		data.put("addNoteURL", addNoteURL.toString());

		data.put(
			"externalLinks",
			TransformUtil.transform(
				externalLinkWebService.getExternalLinks(
					account.getKey(), 1, 1000),
				externalLink -> new ExternalLinkDisplay(
					httpServletRequest, externalLink)));
		data.put(
			"notes",
			TransformUtil.transform(
				noteWebService.getNotes(
					account.getKey(), StringPool.BLANK, StringPool.BLANK, 1,
					1000),
				note -> new NoteDisplay(renderRequest, renderResponse, note)));

		return data;
	}

	public String getParentAccountName() throws Exception {
		String parentAccountKey = accountDisplay.getParentAccountKey();

		if (Validator.isNotNull(parentAccountKey)) {
			Account parentAccount = accountWebService.getAccount(
				parentAccountKey);

			if (parentAccount != null) {
				return parentAccount.getName();
			}
		}

		return StringPool.DASH;
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/view_account");
		portletURL.setParameter(
			"tabs1", ParamUtil.getString(renderRequest, "tabs1"));
		portletURL.setParameter("accountKey", account.getKey());

		return portletURL;
	}

	public SearchContainer getProductPurchaseViewsSearchContainer()
		throws Exception {

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, renderResponse.createRenderURL(),
			Collections.emptyList(), "no-subscriptions-were-found");

		String tabs2 = ParamUtil.getString(renderRequest, "tabs2", "active");

		String keywords = ParamUtil.getString(renderRequest, "keywords");
		String[] productKeys = ParamUtil.getStringValues(
			renderRequest, "productKeys");
		String[] states = ParamUtil.getStringValues(renderRequest, "states");
		int startDateMonth = ParamUtil.getInteger(
			renderRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(renderRequest, "startDateDay");
		int startDateYear = ParamUtil.getInteger(
			renderRequest, "startDateYear");
		int endDateMonth = ParamUtil.getInteger(renderRequest, "endDateMonth");
		int endDateDay = ParamUtil.getInteger(renderRequest, "endDateDay");
		int endDateYear = ParamUtil.getInteger(renderRequest, "endDateYear");
		String orderByCol = ParamUtil.getString(renderRequest, "orderByCol");
		String orderByType = ParamUtil.getString(
			renderRequest, "orderByType", "asc");

		StringBundler sb = new StringBundler(8);

		sb.append("(accountKey eq '");
		sb.append(account.getKey());
		sb.append("')");

		if (tabs2.equals("active")) {
			sb.append(" and (state eq 'active')");
		}
		else if (tabs2.equals("inactive") && (states.length == 0)) {
			sb.append(" and ((state eq 'cancelled') or (state eq 'expired') ");
			sb.append("or (state eq 'unactivated'))");
		}

		if (!tabs2.equals("active") && (states.length > 0)) {
			sb.append(_getStatesFilter(states));
		}

		if (productKeys.length > 0) {
			sb.append(_getProductKeysFilter(productKeys));
		}

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear, null);

		Date endDate = PortalUtil.getDate(
			endDateMonth, endDateDay, endDateYear, null);

		if ((startDate != null) && (endDate != null)) {
			sb.append(_getSupportLifeFilter(startDate, endDate));
		}

		String sorts = _getSorts(orderByCol, orderByType);

		List<ProductPurchaseView> productPurchaseViews =
			productPurchaseViewWebService.getProductPurchaseViews(
				keywords, sb.toString(), searchContainer.getCur(),
				searchContainer.getEnd() - searchContainer.getStart(), sorts);

		searchContainer.setResults(
			TransformUtil.transform(
				productPurchaseViews,
				productPurchaseView -> new ProductPurchaseViewDisplay(
					httpServletRequest, account, productPurchaseView)));

		int count =
			(int)productPurchaseViewWebService.getProductPurchaseViewsCount(
				keywords, sb.toString(), searchContainer.getCur(),
				searchContainer.getEnd() - searchContainer.getStart(), sorts);

		searchContainer.setTotal(count);

		return searchContainer;
	}

	public Map<String, Object> getSupportData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		data.put("account", getAccountDisplay());

		AccountEntry accountEntry = getAccountEntry();

		data.put("instructions", _getAccountEntryInstructions(accountEntry));

		data.put("language", _getAccountEntryLanguage(accountEntry));

		List<String> regionNames = new ArrayList<>();

		for (Account.Region region : Account.Region.values()) {
			regionNames.add(region.toString());
		}

		data.put("regionNames", regionNames);

		data.put("languageList", _getLanguageList());

		PortletURL updateAccountURL = renderResponse.createActionURL();

		updateAccountURL.setParameter(
			ActionRequest.ACTION_NAME, "/accounts/edit_account");
		updateAccountURL.setParameter("accountKey", account.getKey());
		updateAccountURL.setParameter("redirect", _getPortletURL("support"));

		data.put("updateAccountURL", updateAccountURL.toString());

		PortletURL updateLanguageIdURL = renderResponse.createActionURL();

		updateLanguageIdURL.setParameter(
			ActionRequest.ACTION_NAME, "/edit_account_entry");
		updateLanguageIdURL.setParameter(
			Constants.CMD, ProvisioningActionKeys.UPDATE_LANGUAGE_ID);
		updateLanguageIdURL.setParameter("accountKey", account.getKey());
		updateLanguageIdURL.setParameter("redirect", _getPortletURL("support"));

		data.put("updateLanguageIdURL", updateLanguageIdURL.toString());

		PortletURL updateInstructionsURL = renderResponse.createActionURL();

		updateInstructionsURL.setParameter(
			ActionRequest.ACTION_NAME, "/edit_account_entry");
		updateInstructionsURL.setParameter(
			Constants.CMD, ProvisioningActionKeys.UPDATE_INSTRUCTIONS);
		updateInstructionsURL.setParameter("accountKey", account.getKey());
		updateInstructionsURL.setParameter(
			"redirect", _getPortletURL("support"));

		data.put("updateInstructionsURL", updateInstructionsURL.toString());

		return data;
	}

	public String getUpdateAccountAttachmentURL() throws Exception {
		return accountEntryWebService.getUpdateAccountAttachmentURL();
	}

	public String getWorkflowStep() {
		if (account.getStatus() == Account.Status.CLOSED) {
			return "activate";
		}

		return "close";
	}

	public void init(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest, AccountReader accountReader,
			AccountEntryWebService accountEntryWebService,
			AccountWebService accountWebService,
			AuditEntryWebService auditEntryWebService,
			ContactRoleWebService contactRoleWebService,
			ContactWebService contactWebService,
			ExternalLinkWebService externalLinkWebService,
			NoteWebService noteWebService,
			ProductPurchaseViewWebService productPurchaseViewWebService,
			TeamWebService teamWebService)
		throws Exception {

		this.renderRequest = renderRequest;
		this.renderResponse = renderResponse;
		this.httpServletRequest = httpServletRequest;
		this.accountReader = accountReader;
		this.accountEntryWebService = accountEntryWebService;
		this.accountWebService = accountWebService;
		this.auditEntryWebService = auditEntryWebService;
		this.contactRoleWebService = contactRoleWebService;
		this.contactWebService = contactWebService;
		this.externalLinkWebService = externalLinkWebService;
		this.noteWebService = noteWebService;
		this.productPurchaseViewWebService = productPurchaseViewWebService;
		this.teamWebService = teamWebService;

		doInit();
	}

	protected void doInit() throws Exception {
		account = (Account)renderRequest.getAttribute(
			ProvisioningWebKeys.ACCOUNT);

		accountDisplay = new AccountDisplay(
			renderRequest, renderResponse, accountReader, account);

		currentURLObj = PortletURLUtil.getCurrent(
			renderRequest, renderResponse);
	}

	protected Account account;
	protected AccountDisplay accountDisplay;
	protected AccountEntryWebService accountEntryWebService;
	protected AccountReader accountReader;
	protected AccountWebService accountWebService;
	protected AuditEntryWebService auditEntryWebService;
	protected ContactRoleWebService contactRoleWebService;
	protected ContactWebService contactWebService;
	protected PortletURL currentURLObj;
	protected final Format dateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	protected ExternalLinkWebService externalLinkWebService;
	protected HttpServletRequest httpServletRequest;
	protected NoteWebService noteWebService;
	protected ProductPurchaseViewWebService productPurchaseViewWebService;
	protected RenderRequest renderRequest;
	protected RenderResponse renderResponse;
	protected TeamWebService teamWebService;

	private String _getAccountEntryInstructions(AccountEntry accountEntry)
		throws Exception {

		String instructions = accountEntry.getInstructions();

		if (Validator.isNotNull(instructions)) {
			return instructions;
		}

		return StringPool.DASH;
	}

	private JSONObject _getAccountEntryLanguage(AccountEntry accountEntry)
		throws Exception {

		String languageId = accountEntry.getLanguageId();

		if (Validator.isNotNull(languageId)) {
			Locale languageLocale = LocaleUtil.fromLanguageId(languageId);

			return JSONUtil.put(
				"id", languageId
			).put(
				"name", languageLocale.getDisplayLanguage()
			);
		}

		return JSONUtil.put(
			"id", StringPool.DASH
		).put(
			"name", StringPool.DASH
		);
	}

	private List<DropdownItem> _getFilterCustomerRoleDropdownItems()
		throws Exception {

		String[] contactRoleKeys = ParamUtil.getStringValues(
			renderRequest, "contactRoleKeys");

		return new DropdownItemList() {
			{
				List<ContactRole> contactRoles = contactRoleWebService.search(
					"type eq '" + ContactRole.Type.ACCOUNT_CUSTOMER.toString() +
						"'",
					1, 1000, "name");

				for (ContactRole contactRole : contactRoles) {
					add(
						dropdownItem -> {
							dropdownItem.setActive(
								ArrayUtil.contains(
									contactRoleKeys, contactRole.getKey()));

							PortletURL portletURL = PortletURLUtil.clone(
								currentURLObj, renderResponse);

							String[] newContactRoleKeys = ArrayUtil.append(
								contactRoleKeys, contactRole.getKey());

							dropdownItem.setHref(
								portletURL, "contactRoleKeys",
								StringUtil.merge(newContactRoleKeys));

							dropdownItem.setLabel(contactRole.getName());
						});
				}
			}
		};
	}

	private List<DropdownItem> _getHeaderAddDropdownItems() {
		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.setHref(
							renderResponse.createRenderURL(),
							"mvcRenderCommandName", "/accounts/assign_contacts",
							"redirect", getCurrentURL(), "accountKey",
							account.getKey());
						dropdownItem.setLabel(
							LanguageUtil.get(httpServletRequest, "contacts"));
					});
				add(
					dropdownItem -> {
						dropdownItem.setHref(
							renderResponse.createRenderURL(),
							"mvcRenderCommandName",
							"/accounts/assign_liferay_workers", "redirect",
							getCurrentURL(), "accountKey", account.getKey());
						dropdownItem.setLabel(
							LanguageUtil.get(
								httpServletRequest, "liferay-workers"));
					});
				add(
					dropdownItem -> {
						dropdownItem.setHref(
							renderResponse.createRenderURL(),
							"mvcRenderCommandName", "/accounts/edit_team",
							"redirect", getCurrentURL(), "accountKey",
							account.getKey());
						dropdownItem.setLabel(
							LanguageUtil.get(httpServletRequest, "team"));
					});
			}
		};
	}

	private List<DropdownItem> _getHeaderAddSubscriptionsDropdownItems() {
		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.setHref("/");
						dropdownItem.setLabel(
							LanguageUtil.get(
								httpServletRequest, "subscriptions"));
					});
			}
		};
	}

	private List<JSONObject> _getLanguageList() {
		List<JSONObject> languageList = new ArrayList<>();

		for (Locale languageLocale : LanguageUtil.getAvailableLocales()) {
			JSONObject jsonObject = JSONUtil.put(
				"id", languageLocale.toString()
			).put(
				"name", languageLocale.getDisplayLanguage()
			);

			languageList.add(jsonObject);
		}

		return languageList;
	}

	private String _getPortletURL(String tab) {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/view_account");
		portletURL.setParameter("accountKey", account.getKey());
		portletURL.setParameter("tabs1", tab);

		return portletURL.toString();
	}

	private String _getProductKeysFilter(String[] productKeys) {
		StringBundler sb = new StringBundler((5 * productKeys.length) + 1);

		for (int i = 0; i < productKeys.length; i++) {
			if (i == 0) {
				sb.append(" and (");
			}

			sb.append("(productKey eq '");
			sb.append(productKeys[i]);
			sb.append("')");

			if (i < (productKeys.length - 1)) {
				sb.append(" or ");
			}
		}

		sb.append(")");

		return sb.toString();
	}

	private String _getSorts(String orderByCol, String orderByType) {
		StringBundler sb = new StringBundler(4);

		sb.append("property_type:desc,");

		if (Validator.isNotNull(orderByCol)) {
			sb.append(orderByCol);
			sb.append(StringPool.COLON);
			sb.append(orderByType);
		}
		else {
			sb.append("name:asc");
		}

		return sb.toString();
	}

	private String _getStatesFilter(String[] states) {
		StringBundler sb = new StringBundler((4 * states.length) + 2);

		sb.append(" and (");

		for (int i = 0; i < states.length; i++) {
			String state = states[i];

			sb.append("(state eq '");
			sb.append(state);
			sb.append("')");

			if (i < (states.length - 1)) {
				sb.append(" or ");
			}
		}

		sb.append(")");

		if (sb.length() > 7) {
			return sb.toString();
		}

		return StringPool.BLANK;
	}

	private String _getSupportLifeFilter(Date startDate, Date endDate) {
		StringBundler sb = new StringBundler(5);

		sb.append(" and ((supportLifeStartDate ge ");
		sb.append(dateFormat.format(startDate));
		sb.append(") and (supportLifeEndDate le ");
		sb.append(dateFormat.format(endDate));
		sb.append("))");

		return sb.toString();
	}

}