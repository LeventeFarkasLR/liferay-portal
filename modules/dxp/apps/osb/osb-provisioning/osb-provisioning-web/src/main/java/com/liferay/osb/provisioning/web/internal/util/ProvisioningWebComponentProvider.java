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

package com.liferay.osb.provisioning.web.internal.util;

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
import com.liferay.osb.provisioning.web.internal.display.context.AccountSearchDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.AssignTeamContactsDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewAccountContactsDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewAccountDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewAccountLiferayWorkersDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewAccountRelatedAccountsDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewAccountTeamsDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewSubscriptionDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewTeamDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = {})
public class ProvisioningWebComponentProvider {

	public static AccountSearchDisplayContext getAccountSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		return _provisioningWebComponentProvider.
			_getAccountSearchDisplayContext(
				renderRequest, renderResponse, httpServletRequest);
	}

	public static AssignTeamContactsDisplayContext
			getAssignTeamContactsDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			AssignTeamContactsDisplayContext.class, renderRequest,
			renderResponse, httpServletRequest);
	}

	public static ProvisioningWebComponentProvider
		getProvisioningWebComponentProvider() {

		return _provisioningWebComponentProvider;
	}

	public static ViewAccountContactsDisplayContext
			getViewAccountContactsDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			ViewAccountContactsDisplayContext.class, renderRequest,
			renderResponse, httpServletRequest);
	}

	public static ViewAccountDisplayContext getViewAccountDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			ViewAccountDisplayContext.class, renderRequest, renderResponse,
			httpServletRequest);
	}

	public static ViewAccountLiferayWorkersDisplayContext
			getViewAccountLiferayWorkersDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			ViewAccountLiferayWorkersDisplayContext.class, renderRequest,
			renderResponse, httpServletRequest);
	}

	public static ViewAccountRelatedAccountsDisplayContext
			getViewAccountRelatedAccountsDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			ViewAccountRelatedAccountsDisplayContext.class, renderRequest,
			renderResponse, httpServletRequest);
	}

	public static ViewAccountTeamsDisplayContext
			getViewAccountTeamsDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			ViewAccountTeamsDisplayContext.class, renderRequest, renderResponse,
			httpServletRequest);
	}

	public static ViewSubscriptionDisplayContext
			getViewSubscriptionDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			ViewSubscriptionDisplayContext.class, renderRequest, renderResponse,
			httpServletRequest);
	}

	public static ViewTeamDisplayContext getViewTeamDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			ViewTeamDisplayContext.class, renderRequest, renderResponse,
			httpServletRequest);
	}

	@Activate
	protected void activate() {
		_provisioningWebComponentProvider = this;
	}

	@Deactivate
	protected void deactivate() {
		_provisioningWebComponentProvider = null;
	}

	private AccountSearchDisplayContext _getAccountSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		return new AccountSearchDisplayContext(
			renderRequest, renderResponse, httpServletRequest, _accountReader,
			_accountWebService);
	}

	private <T extends ViewAccountDisplayContext> T
			_getViewAccountDisplayContext(
				Class<T> clazz, RenderRequest renderRequest,
				RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		T viewAccountDisplayContext = (T)httpServletRequest.getAttribute(
			clazz.getName());

		if (viewAccountDisplayContext != null) {
			return viewAccountDisplayContext;
		}

		viewAccountDisplayContext = clazz.newInstance();

		viewAccountDisplayContext.init(
			renderRequest, renderResponse, httpServletRequest, _accountReader,
			_accountEntryWebService, _accountWebService, _auditEntryWebService,
			_contactRoleWebService, _contactWebService, _externalLinkWebService,
			_noteWebService, _productPurchaseViewWebService, _teamWebService);

		httpServletRequest.setAttribute(
			clazz.getName(), viewAccountDisplayContext);

		return viewAccountDisplayContext;
	}

	private static ProvisioningWebComponentProvider
		_provisioningWebComponentProvider;

	@Reference
	private AccountEntryWebService _accountEntryWebService;

	@Reference
	private AccountReader _accountReader;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private AuditEntryWebService _auditEntryWebService;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private ExternalLinkWebService _externalLinkWebService;

	@Reference
	private NoteWebService _noteWebService;

	@Reference
	private ProductPurchaseViewWebService _productPurchaseViewWebService;

	@Reference
	private TeamWebService _teamWebService;

}