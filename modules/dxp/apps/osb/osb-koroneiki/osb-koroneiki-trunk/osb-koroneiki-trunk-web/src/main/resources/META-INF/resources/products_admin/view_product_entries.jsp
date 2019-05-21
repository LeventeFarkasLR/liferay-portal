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
String tabs1 = ParamUtil.getString(request, "tabs1");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);
%>

<liferay-ui:search-container
	emptyResultsMessage="no-products-were-found"
	headerNames="name,"
	iteratorURL="<%= portletURL %>"
	total="<%= ProductEntryLocalServiceUtil.getProductEntriesCount() %>"
>
	<liferay-ui:search-container-results
		results="<%= ProductEntryLocalServiceUtil.getProductEntries(searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.osb.koroneiki.trunk.model.ProductEntry"
		escapedModel="<%= true %>"
		keyProperty="productEntryId"
		modelVar="productEntry"
	>
		<portlet:renderURL var="rowURL">
			<portlet:param name="mvcRenderCommandName" value="/products_admin/edit_product_entry" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="productEntryId" value="<%= String.valueOf(productEntry.getProductEntryId()) %>" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="name"
			value="<%= productEntry.getName() %>"
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/products_admin/product_entry_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>