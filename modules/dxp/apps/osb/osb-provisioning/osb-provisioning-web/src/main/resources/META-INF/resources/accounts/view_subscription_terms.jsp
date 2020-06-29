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
ViewSubscriptionDisplayContext viewSubscriptionDisplayContext = ProvisioningWebComponentProvider.getViewSubscriptionDisplayContext(renderRequest, renderResponse, request);

String productPurchaseKey = ParamUtil.getString(request, "productPurchaseKey");
%>

<liferay-ui:search-container
	searchContainer="<%= viewSubscriptionDisplayContext.getSearchContainer() %>"
>
	<liferay-ui:search-container-row
		className="com.liferay.osb.provisioning.web.internal.display.context.ProductPurchaseDisplay"
		escapedModel="<%= true %>"
		modelVar="productPurchaseDisplay"
	>

		<%
		if (productPurchaseKey.equals(productPurchaseDisplay.getKey())) {
			row.setCssClass("highlight-row");
		}
		%>

		<liferay-ui:search-container-column-text
			name="start-end-date"
			value="<%= productPurchaseDisplay.getSupportLife() %>"
		/>

		<liferay-ui:search-container-column-text
			name="grace-period"
			value="<%= productPurchaseDisplay.getGracePeriod() %>"
		/>

		<liferay-ui:search-container-column-text
			name="provisioned"
			value="<%= productPurchaseDisplay.getProvisionedCount() %>"
		/>

		<liferay-ui:search-container-column-text
			name="purchased"
			value="<%= productPurchaseDisplay.getQuantity() %>"
		/>

		<liferay-ui:search-container-column-text
			name="salesforce-opportunity-key"
		>
			<a href="<%= productPurchaseDisplay.getSalesforceOpportunityURL() %>"><%= productPurchaseDisplay.getSalesforceOpportunityKey() %></a>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="instance-size"
			value="<%= productPurchaseDisplay.getSizing() %>"
		/>

		<liferay-ui:search-container-column-text
			name="status"
		>
			<span class="label <%= productPurchaseDisplay.getStatusStyle() %>"><%= productPurchaseDisplay.getStatus() %></span>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			path="/accounts/product_purchase_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>