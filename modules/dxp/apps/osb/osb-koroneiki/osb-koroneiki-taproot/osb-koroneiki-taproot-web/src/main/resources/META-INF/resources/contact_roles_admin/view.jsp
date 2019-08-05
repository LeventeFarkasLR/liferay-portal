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
ViewContactRolesManagementToolbarDisplayContext viewContactRolesManagementToolbarDisplayContext = new ViewContactRolesManagementToolbarDisplayContext(request, renderRequest, renderResponse);

SearchContainer searchContainer = viewContactRolesManagementToolbarDisplayContext.getSearchContainer();
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= viewContactRolesManagementToolbarDisplayContext.getNavigationItems() %>"
/>

<clay:management-toolbar
	clearResultsURL="<%= viewContactRolesManagementToolbarDisplayContext.getClearResultsURL() %>"
	creationMenu="<%= viewContactRolesManagementToolbarDisplayContext.getCreationMenu() %>"
	filterDropdownItems="<%= viewContactRolesManagementToolbarDisplayContext.getFilterDropdownItems() %>"
	itemsTotal="<%= searchContainer.getTotal() %>"
	searchActionURL="<%= viewContactRolesManagementToolbarDisplayContext.getSearchActionURL() %>"
	searchContainerId="contactRoleSearch"
	searchFormName="searchFm"
	selectable="<%= false %>"
	showSearch="<%= true %>"
	sortingOrder="<%= searchContainer.getOrderByType() %>"
	sortingURL="<%= viewContactRolesManagementToolbarDisplayContext.getSortingURL() %>"
/>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		searchContainer="<%= searchContainer %>"
		var="contactRoleSearch"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.taproot.model.ContactRole"
			escapedModel="<%= true %>"
			keyProperty="contactRoleId"
			modelVar="contactRole"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/contact_roles_admin/edit_contact_role" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="contactRoleId" value="<%= String.valueOf(contactRole.getContactRoleId()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
				value="<%= contactRole.getName() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="description"
				value="<%= contactRole.getDescription() %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/contact_roles_admin/contact_role_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>