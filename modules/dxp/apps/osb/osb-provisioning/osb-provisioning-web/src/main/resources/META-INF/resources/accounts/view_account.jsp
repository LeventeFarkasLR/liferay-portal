<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
ViewAccountDisplayContext viewAccountDisplayContext = ProvisioningWebComponentProvider.getViewAccountDisplayContext(renderRequest, renderResponse, request);

viewAccountDisplayContext.addPortletBreadcrumbEntries();

String tabs1 = ParamUtil.getString(request, "tabs1");
%>

<liferay-util:include page="/accounts/view_account_header.jsp" servletContext="<%= application %>" />

<div class="account" id="account">
	<div class="account-details">
		<liferay-ui:tabs
			names="subscriptions,details,contacts,liferay-workers,teams,related-accounts,support,history,opportunities"
			portletURL="<%= viewAccountDisplayContext.getPortletURL() %>"
		/>

		<c:choose>
			<c:when test='<%= tabs1.equals("contacts") %>'>
				<liferay-util:include page="/accounts/view_account_contacts.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test='<%= tabs1.equals("details") %>'>
				<liferay-util:include page="/accounts/view_account_details.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test='<%= tabs1.equals("history") %>'>
				<div class="history-details" id="historyDetails">
					<liferay-util:include page="/accounts/view_account_history.jsp" servletContext="<%= application %>" />
				</div>
			</c:when>
			<c:when test='<%= tabs1.equals("liferay-workers") %>'>
				<liferay-util:include page="/accounts/view_account_liferay_workers.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test='<%= tabs1.equals("opportunities") %>'>
				<liferay-util:include page="/accounts/view_account_opportunities.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test='<%= tabs1.equals("related-accounts") %>'>
				<liferay-util:include page="/accounts/view_account_related_accounts.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test='<%= tabs1.equals("support") %>'>
				<liferay-util:include page="/accounts/view_account_support.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test='<%= tabs1.equals("teams") %>'>
				<liferay-util:include page="/accounts/view_account_teams.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/accounts/view_account_subscriptions.jsp" servletContext="<%= application %>" />
			</c:otherwise>
		</c:choose>
	</div>

	<div class="side-panel" id="sidePanel">
		<react:component
			data="<%= viewAccountDisplayContext.getPanelData() %>"
			module="js/SidePanelApp"
		/>
	</div>
</div>