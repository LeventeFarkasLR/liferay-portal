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
Account koroneikiAccount = (Account)request.getAttribute(TaprootWebKeys.ACCOUNT);

renderResponse.setTitle(koroneikiAccount.getName());
%>

<liferay-util:include page="/accounts_admin/edit_account_tabs.jsp" servletContext="<%= application %>" />

<div class="container-fluid-1280">
	<liferay-ui:search-container
		emptyResultsMessage="no-consumptions-were-found"
		headerNames="name,type"
		total="<%= ProductConsumptionLocalServiceUtil.getAccountProductConsumptionsCount(koroneikiAccount.getAccountId()) %>"
	>
		<liferay-ui:search-container-results
			results="<%= ProductConsumptionLocalServiceUtil.getAccountProductConsumptions(koroneikiAccount.getAccountId(), searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.trunk.model.ProductConsumption"
			escapedModel="<%= true %>"
			keyProperty="productConsumptionId"
			modelVar="productConsumption"
		>

			<%
			ProductEntry productEntry = productConsumption.getProductEntry();
			%>

			<liferay-portlet:renderURL portletName="<%= TrunkPortletKeys.PRODUCTS_ADMIN %>" var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/products_admin/edit_product_consumption" />
				<portlet:param name="productConsumptionId" value="<%= String.valueOf(productConsumption.getProductConsumptionId()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="account"
			>
				<span class="lfr-portal-tooltip" data-title="<liferay-ui:message key="account" />">
					<aui:icon cssClass="icon-monospaced" image="users" markupView="lexicon" />
				</span>

				<%= HtmlUtil.escape(koroneikiAccount.getName()) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="product"
				value="<%= HtmlUtil.escape(productEntry.getName()) %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>