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
ViewAccountLiferayWorkersDisplayContext viewAccountLiferayWorkersDisplayContext = ProvisioningWebComponentProvider.getViewAccountLiferayWorkersDisplayContext(renderRequest, renderResponse, request);
%>

<div class="details-table">
	<liferay-ui:search-container
		id="liferay-workers"
		searchContainer="<%= viewAccountLiferayWorkersDisplayContext.getSearchContainer() %>"
	>
		<clay:management-toolbar
			clearResultsURL="<%= viewAccountLiferayWorkersDisplayContext.getClearResultsURL() %>"
			filterDropdownItems="<%= viewAccountLiferayWorkersDisplayContext.getFilterDropdownItems() %>"
			filterLabelItems="<%= viewAccountLiferayWorkersDisplayContext.getFilterLabelItems() %>"
			itemsTotal="<%= searchContainer.getTotal() %>"
			searchActionURL="<%= viewAccountLiferayWorkersDisplayContext.getCurrentURL() %>"
			searchContainerId="liferay-workers"
			selectable="<%= true %>"
			showSearch="<%= true %>"
			supportsBulkActions="<%= true %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.ContactDisplay"
			escapedModel="<%= true %>"
			modelVar="contactDisplay"
		>
			<liferay-ui:search-container-column-text
				name="name-email"
			>
				<%= contactDisplay.getFullName() %>

				<div class="secondary-information">
					<%= contactDisplay.getEmailAddress() %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="role"
			>
				<%= StringUtil.merge(contactDisplay.getContactRoleNames(), "<br />") %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="status"
			>
				<span class="label"><%= contactDisplay.getStatus() %></span>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				path="/accounts/contact_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>