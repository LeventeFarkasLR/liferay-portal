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
String teamKey = ParamUtil.getString(request, "teamKey");

AssignAccountTeamDisplayContext assignAccountTeamDisplayContext = ProvisioningWebComponentProvider.getAssignAccountTeamDisplayContext(renderRequest, renderResponse, request);

SearchContainer searchContainer = assignAccountTeamDisplayContext.getSearchContainer();
%>

<clay:management-toolbar
	clearResultsURL="<%= assignAccountTeamDisplayContext.getClearResultsURL() %>"
	elementClasses="full-width"
	itemsTotal="<%= searchContainer.getTotal() %>"
	searchActionURL="<%= assignAccountTeamDisplayContext.getCurrentURL() %>"
	searchContainerId="teamContainer"
	searchFormName="searchFm"
	showSearch="<%= true %>"
/>

<div class="container-fluid container-fluid-max-xl">
	<liferay-ui:search-container
		cssClass="details-search-container"
		id="teamContainer"
		searchContainer="<%= searchContainer %>"
		var="teamsSearchContainer"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.TeamDisplay"
			keyProperty="teamKey"
			modelVar="teamDisplay"
		>

			<%
			Map<String, Object> teamData = new HashMap<String, Object>();

			teamData.put("key", teamDisplay.getKey());

			row.setData(teamData);

			if (teamKey.equals(teamDisplay.getKey())) {
				row.setCssClass("active");
			}
			%>

			<liferay-ui:search-container-column-text
				name="team-name"
			>
				<%= HtmlUtil.escape(teamDisplay.getName()) %>
			</liferay-ui:search-container-column-text>

			<%
			Account teamAccount = teamDisplay.getAccount();
			%>

			<liferay-ui:search-container-column-text
				name="account-name"
			>
				<%= HtmlUtil.escape(teamAccount.getName()) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="account-code"
			>
				<%= HtmlUtil.escape(teamAccount.getCode()) %>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<aui:script>
	var searchContainer = document.getElementById(
		'<portlet:namespace />teamContainerSearchContainer'
	);

	var table = searchContainer.querySelector('tbody');

	if (table) {
		var tableRows = table.querySelectorAll('tr');

		tableRows.forEach(<portlet:namespace />selectRow);
	}

	var anchors = document.querySelectorAll('a');

	anchors.forEach(<portlet:namespace />resetAnchor);

	var searchForm = document.querySelector('form');

	if (searchForm) {
		searchForm.addEventListener('submit', <portlet:namespace />resetData);
	}

	function <portlet:namespace />resetAnchor(element) {
		if (!element.classList.contains('dropdown-toggle')) {
			element.addEventListener('click', <portlet:namespace />resetData);
		}
	}

	function <portlet:namespace />resetData() {
		Liferay.Util.getOpener().Liferay.fire('selectedItemChange', {
			data: ''
		});
	}

	function <portlet:namespace />resetRow(row) {
		row.classList.remove('active');
	}

	function <portlet:namespace />selectRow(row) {
		row.addEventListener('click', function() {
			var activeRows = Array.from(document.getElementsByClassName('active'));

			activeRows.forEach(<portlet:namespace />resetRow);

			this.classList.add('active');

			var rowData = this.dataset;

			if (rowData) {
				Liferay.Util.getOpener().Liferay.fire('selectedItemChange', {
					data: rowData.key
				});
			}
		});
	}
</aui:script>