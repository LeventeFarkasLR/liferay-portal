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
ViewContactDisplayContext viewContactDisplayContext = ProvisioningWebComponentProvider.getViewContactDisplayContext(renderRequest, renderResponse, request);

ContactDisplay contactDisplay = viewContactDisplayContext.getContactDisplay();
%>

<div class="autofit-row contact-header">
	<svg class="autofit-col header-icon">
		<use xlink:href="#contact-icon" />
	</svg>

	<div class="autofit-col autofit-col-expand">
		<liferay-ui:breadcrumb
			showCurrentGroup="<%= false %>"
			showGuestGroup="<%= false %>"
			showLayout="<%= true %>"
			showParentGroups="<%= false %>"
		/>

		<h3 class="contact-name">
			<span class="email-address">
				<%= HtmlUtil.escape(contactDisplay.getEmailAddress()) %>
			</span>

			<%= HtmlUtil.escape(contactDisplay.getFullName()) %>
		</h3>

		<ul class="header-details">
			<li>
				<div class="header-label">
					<liferay-ui:message key="status" />
				</div>

				<span class="label <%= contactDisplay.getStatusStyle() %>"><%= contactDisplay.getStatus() %></span>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="no.-of-accounts" />
				</div>

				<%= contactDisplay.getAccountsCount() %>
			</li>
		</ul>
	</div>
</div>