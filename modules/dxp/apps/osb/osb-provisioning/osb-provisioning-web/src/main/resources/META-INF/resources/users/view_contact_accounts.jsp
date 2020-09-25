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
%>

<div class="details-table table-striped">
	<h3 class="panel-title">
		<liferay-ui:message key="contact" />
	</h3>

	<liferay-ui:search-container
		id="contacts"
		searchContainer='<%= viewContactDisplayContext.getContactAccountsSearchContainer("customer") %>'
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.AccountDisplay"
			modelVar="accountDisplay"
		>
			<liferay-ui:search-container-column-text
				name="account-name-code"
			>
				<%= HtmlUtil.escape(accountDisplay.getName()) %>

				<div class="secondary-information">
					<%= HtmlUtil.escape(accountDisplay.getCode()) %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="region"
			>
				<%= accountDisplay.getRegion() %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="roles"
			>
				<%= StringUtil.merge(viewContactDisplayContext.getCustomerContactRoleNames(accountDisplay.getKey()), "<br />") %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="teams"
			>

				<%
				List<String> teamNames = viewContactDisplayContext.getContactAccountTeamNames(accountDisplay.getKey());

				for (int i = 0; i < teamNames.size(); i++) {
					String teamName = teamNames.get(i);
				%>

					<%= HtmlUtil.escape(teamName) %><%= ((i + 1) < teamNames.size()) ? "<br />" : "" %>

				<%
				}
				%>

			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="account-status"
			>
				<span class="label <%= accountDisplay.getStatusStyle() %>"><%= accountDisplay.getStatus() %></span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<div class="details-table table-striped">
	<h3 class="panel-title">
		<liferay-ui:message key="liferay-workers" />
	</h3>

	<liferay-ui:search-container
		id="contacts"
		searchContainer='<%= viewContactDisplayContext.getContactAccountsSearchContainer("worker") %>'
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.AccountDisplay"
			modelVar="accountDisplay"
		>
			<liferay-ui:search-container-column-text
				name="account-name-code"
			>
				<%= HtmlUtil.escape(accountDisplay.getName()) %>

				<div class="secondary-information">
					<%= HtmlUtil.escape(accountDisplay.getCode()) %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="region"
			>
				<%= accountDisplay.getRegion() %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="roles"
			>
				<%= StringUtil.merge(viewContactDisplayContext.getWorkerContactRoleNames(accountDisplay.getKey()), "<br />") %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="account-status"
			>
				<span class="label <%= accountDisplay.getStatusStyle() %>"><%= accountDisplay.getStatus() %></span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>