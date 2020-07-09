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

<div class="container-fluid-1280">
	<liferay-ui:search-container
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
			<liferay-ui:search-container-column-text
				name="name"
				value="<%= productBundle.getName() %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>