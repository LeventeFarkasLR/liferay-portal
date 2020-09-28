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
Contact koroneikiContact = (Contact)renderRequest.getAttribute(ProvisioningWebKeys.CONTACT);

List<Entitlement> entitlements = Arrays.asList(koroneikiContact.getEntitlements());
%>

<div class="main-content-body">
	<div class="container-fluid-1280">
		<liferay-ui:search-container
			emptyResultsMessage="no-entitlements-were-found"
			headerNames="name"
			total="<%= entitlements.size() %>"
		>
			<liferay-ui:search-container-results
				results="<%= entitlements %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement"
				keyProperty="entitlementId"
				modelVar="entitlement"
			>
				<liferay-ui:search-container-column-text
					name="name"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
				paginate="<%= false %>"
			/>
		</liferay-ui:search-container>
	</div>
</div>