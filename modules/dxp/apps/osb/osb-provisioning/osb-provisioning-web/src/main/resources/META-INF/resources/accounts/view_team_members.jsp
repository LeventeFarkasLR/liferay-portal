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
ViewTeamDisplayContext viewTeamDisplayContext = ProvisioningWebComponentProvider.getViewTeamDisplayContext(renderRequest, renderResponse, request);

PortletURL searchURL = viewTeamDisplayContext.getPortletURL();
TeamDisplay teamDisplay = viewTeamDisplayContext.getTeamDisplay();
%>

<div class="management-bar management-bar-light navbar navbar-expand-md">
	<div class="container-fluid container-fluid-max-xl">
		<div class="navbar-form navbar-form-autofit navbar-overlay navbar-overlay-sm-down">
			<div class="container-fluid container-fluid-max-xl">
				<aui:form action="<%= searchURL.toString() %>" method="get" name="fm">
					<liferay-portlet:renderURLParams portletURL="<%= searchURL %>" />

					<div class="input-group search-input">
						<aui:input cssClass="input-group-inset-after" label="" name="keywords" placeholder="search-for" wrapperCssClass="input-group-item" />

						<span class="input-group-inset-item input-group-inset-item-after">
							<aui:button cssClass="btn-unstyled" icon="icon-search" primary="<%= false %>" type="submit" value="" />
						</span>
					</div>
				</aui:form>
			</div>
		</div>

		<c:if test="<%= !teamDisplay.isSystem() %>">
			<portlet:actionURL name="/accounts/edit_team" var="editTeamURL" />

			<ul class="navbar-nav">
				<li class="nav-item">
					<aui:form action="<%= editTeamURL %>" method="post" name="assignContactsFm">
						<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
						<aui:input name="teamKey" type="hidden" value="<%= teamDisplay.getKey() %>" />
						<aui:input name="addEmailAddresses" type="hidden" />

						<liferay-ui:icon
							icon="plus"
							iconCssClass="btn btn-primary nav-btn nav-btn-monospaced"
							markupView="lexicon"
							message="assign-members"
							onClick='<%= renderResponse.getNamespace() + "assignContacts();" %>'
							url="javascript:;"
						/>
					</aui:form>
				</li>
			</ul>
		</c:if>
	</div>
</div>

<liferay-ui:search-container
	searchContainer="<%= viewTeamDisplayContext.getContactsSearchContainer() %>"
>
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
			name="status"
		>
			<span class="label <%= contactDisplay.getStatusStyle() %>"><%= contactDisplay.getStatus() %></span>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/accounts/team_contact_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />assignContacts',
		function() {
			<portlet:renderURL var="assignTeamContactsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcRenderCommandName" value="/accounts/assign_team_contacts" />
				<portlet:param name="teamKey" value="<%= teamDisplay.getKey() %>" />
			</portlet:renderURL>

			var A = AUI();

			var itemSelectorDialog = new A.LiferayItemSelectorDialog({
				eventName: '<portlet:namespace />assignContacts',
				title: '<liferay-ui:message key="select-team-members" />',
				url: '<%= assignTeamContactsURL %>'
			});

			itemSelectorDialog.on('selectedItemChange', function(event) {
				var selectedItems = event.newVal;

				if (selectedItems) {
					Liferay.Util.postForm(
						document.<portlet:namespace />assignContactsFm,
						{
							data: {
								addEmailAddresses: selectedItems
							}
						}
					);
				}
			});

			itemSelectorDialog.open();
		},
		['aui-base', 'liferay-item-selector-dialog']
	);
</aui:script>