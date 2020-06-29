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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

ContactDisplay contactDisplay = (ContactDisplay)row.getObject();

ViewAccountDisplayContext viewAccountDisplayContext = ProvisioningWebComponentProvider.getViewAccountDisplayContext(renderRequest, renderResponse, request);

AccountDisplay accountDisplay = viewAccountDisplayContext.getAccountDisplay();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<portlet:renderURL var="editURL">
		<portlet:param name="mvcRenderCommandName" value="/accounts/assign_liferay_workers" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
		<portlet:param name="emailAddress" value="<%= contactDisplay.getEmailAddress() %>" />
		<portlet:param name="fullName" value="<%= contactDisplay.getFullName() %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		message="edit"
		url="<%= editURL %>"
	/>

	<portlet:actionURL name="/accounts/unassign_worker_contact" var="unassignURL">
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
		<portlet:param name="emailAddress" value="<%= contactDisplay.getEmailAddress() %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		confirmation="are-you-sure-you-want-to-unassign-this-liferay-worker"
		message="unassign"
		url="<%= unassignURL %>"
	/>
</liferay-ui:icon-menu>