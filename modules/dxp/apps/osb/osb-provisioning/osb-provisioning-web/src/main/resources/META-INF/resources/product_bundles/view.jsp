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

<portlet:renderURL var="editProductBundleURL">
	<portlet:param name="mvcRenderCommandName" value="/product_bundles/edit_product_bundle" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:renderURL>

<div class="title-bar">
	<h3><liferay-ui:message key="product-bundles" /></h3>

	<a aria-label="<%= LanguageUtil.get(request, "new-product-bundle") %>" class="btn btn-primary nav-btn nav-btn-monospaced" href="<%= editProductBundleURL %>" title="<%= LanguageUtil.get(request, "new-product-bundle") %>">
		<svg class="lexicon-icon lexicon-icon-plus" focusable="false" role="presentation">
			<use xlink:href="#plus" />
		</svg>
	</a>
</div>

<div class="container-fluid home">
	<clay:management-toolbar
		elementClasses="full-width"
		id="productBundlesManagementToolbar"
		selectable="<%= false %>"
		showSearch="<%= false %>"
	/>

	<liferay-ui:search-container
		cssClass="table-hover"
		emptyResultsMessage="no-product-bundles-were-found"
		headerNames="name"
		iteratorURL="<%= renderResponse.createRenderURL() %>"
		total="<%= ProductBundleLocalServiceUtil.getProductBundlesCount() %>"
	>
		<liferay-ui:search-container-results
			results="<%= ProductBundleLocalServiceUtil.getProductBundles(searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.model.ProductBundle"
			escapedModel="<%= true %>"
			keyProperty="productBundleId"
			modelVar="productBundle"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/product_bundles/edit_product_bundle" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="productBundleId" value="<%= String.valueOf(productBundle.getProductBundleId()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
				value="<%= productBundle.getName() %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/product_bundles/product_bundle_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>