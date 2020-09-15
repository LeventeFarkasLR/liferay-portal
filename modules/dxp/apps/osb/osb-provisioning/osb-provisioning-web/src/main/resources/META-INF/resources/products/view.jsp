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

<liferay-util:include page="/common/view_account_search_header.jsp" servletContext="<%= application %>" />

<%
ProductSearchDisplayContext productSearchDisplayContext = ProvisioningWebComponentProvider.getProductSearchDisplayContext(renderRequest, renderResponse, request);
%>

<portlet:renderURL var="editProductURL">
	<portlet:param name="mvcRenderCommandName" value="/products/edit_product" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:renderURL>

<div class="title-bar">
	<h3><liferay-ui:message key="products" /></h3>

	<a aria-label="<%= LanguageUtil.get(request, "new-product") %>" class="btn btn-primary nav-btn nav-btn-monospaced" href="<%= editProductURL %>" title="<%= LanguageUtil.get(request, "new-product") %>">
		<svg class="lexicon-icon lexicon-icon-plus" focusable="false" role="presentation">
			<use xlink:href="#plus" />
		</svg>
	</a>
</div>

<div class="container-fluid home">
	<portlet:actionURL name="/search" var="searchURL" />

	<liferay-ui:error key="<%= RequiredProductException.class.getName() %>" message="please-remove-the-product-from-all-product-bundles-before-deleting" />

	<liferay-ui:error exception="<%= HttpException.class %>">

		<%
		HttpException httpException = (HttpException)errorException;
		%>

		<%= httpException.getMessage() %>
	</liferay-ui:error>

	<clay:management-toolbar
		displayContext="<%= new ViewProductsManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, productSearchDisplayContext.getSearchContainer()) %>"
		elementClasses="full-width"
	/>

	<liferay-ui:search-container
		cssClass="table-hover"
		searchContainer="<%= productSearchDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.ProductDisplay"
			escapedModel="<%= true %>"
			keyProperty="productKey"
			modelVar="productDisplay"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/products/edit_product" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="productKey" value="<%= productDisplay.getKey() %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
				value="<%= productDisplay.getName() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="type"
				value="<%= productDisplay.getType() %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/products/product_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>