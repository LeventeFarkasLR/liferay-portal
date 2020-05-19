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

PortletURL searchURL = viewAccountDisplayContext.getPortletURL();
%>

<div class="details-table">
	<aui:form action="<%= searchURL.toString() %>" method="get" name="fm">
		<liferay-portlet:renderURLParams portletURL="<%= searchURL %>" />

		<aui:input label="" name="keywords" placeholder="search" />
	</aui:form>

	<liferay-ui:search-container
		searchContainer="<%= viewAccountDisplayContext.getTeamsSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.TeamDisplay"
			escapedModel="<%= true %>"
			modelVar="teamDisplay"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/accounts/view_team" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="teamKey" value="<%= teamDisplay.getKey() %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="team"
			>
				<%= teamDisplay.getName() %>

				<div class="secondary-information">
					<%= teamDisplay.getContactNames() %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="last-modified"
			>
				<%= teamDisplay.getDateModified() %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				path="/accounts/team_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>