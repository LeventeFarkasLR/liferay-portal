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
String redirect = ParamUtil.getString(request, "redirect");

EditProductPurchaseDisplayContext editProductPurchaseDisplayContext = ProvisioningWebComponentProvider.getEditProductPurchaseDisplayContext(renderRequest, renderResponse, request);

ProductPurchase productPurchase = editProductPurchaseDisplayContext.getProductPurchase();

ProductPurchaseDisplay productPurchaseDisplay = editProductPurchaseDisplayContext.getProductPurchaseDisplay();

String productPurchaseKey = BeanParamUtil.getString(productPurchase, request, "key");
long quantity = BeanParamUtil.getLong(productPurchase, request, "quantity", 1);
boolean perpetual = BeanParamUtil.getBoolean(productPurchase, request, "perpetual");

int sizing = 0;

if (productPurchase != null) {
	Map<String, String> properties = productPurchase.getProperties();

	if (properties != null) {
		sizing = GetterUtil.getInteger(properties.get("sizing"));
	}
}
%>

<div class="account-add-items provisioning-accounts">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title='<%= (productPurchase != null) ? "edit-subscription" : "add-subscription" %>'
	/>

	<liferay-ui:error exception="<%= HttpException.class %>">

		<%
		HttpException httpException = (HttpException)errorException;
		%>

		<%= httpException.getMessage() %>
	</liferay-ui:error>

	<div class="main-content-body">
		<c:if test="<%= productPurchase == null %>">
			<table class="table table-autofit table-list table-nowrap">
				<thead>
					<tr>
						<th class="table-cell-expand">
							<h4>
								<liferay-ui:message key="subscription" />
							</h4>
						</th>
						<th>
							<aui:button onClick='<%= renderResponse.getNamespace() + "selectProduct();" %>' value="select" />
						</th>
					</tr>
				</thead>
			</table>

			<div class="sheet taglib-empty-result-message" id="<portlet:namespace />emptyContent">
				<div class="taglib-empty-result-message-header"></div>

				<div class="sheet-text text-center">
					<liferay-ui:message key="select-subscription-to-fill-in-details" /><br /> <br />

					<aui:button onClick='<%= renderResponse.getNamespace() + "selectProduct();" %>' value="select" />
				</div>
			</div>
		</c:if>

		<portlet:actionURL name="/accounts/edit_product_purchase" var="editProductPurchaseURL">
			<portlet:param name="mvcRenderCommandName" value="/accounts/edit_product_purchase" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="productPurchaseKey" value="<%= productPurchaseKey %>" />
			<portlet:param name="accountKey" value="<%= editProductPurchaseDisplayContext.getAccountKey() %>" />
		</portlet:actionURL>

		<aui:form action="<%= editProductPurchaseURL.toString() %>" method="post" name="fm">
			<aui:input name="productKey" type="hidden" />

			<div id="<portlet:namespace />subscriptionContent" hidden>
				<table class="table table-autofit table-list table-nowrap">
					<thead>
						<tr>
							<th class="table-cell-expand">
								<liferay-ui:message key="product" />
							</th>

							<c:if test="<%= productPurchase == null %>">
								<th class="table-cell-expand">
									<liferay-ui:message key="salesforce-opportunity-key" />
								</th>
							</c:if>

							<th class="table-cell-expand">
								<liferay-ui:message key="purchased" />
							</th>
							<th class="table-cell-expand">
								<liferay-ui:message key="perpetual" />
							</th>
							<th class="table-cell-expand">
								<liferay-ui:message key="start-date" />
							</th>
							<th class="table-cell-expand">
								<liferay-ui:message key="end-date" />
							</th>
							<th class="table-cell-expand">
								<liferay-ui:message key="instance-size" />
							</th>

							<c:if test="<%= productPurchase != null %>">
								<th class="table-cell-expand">
									<liferay-ui:message key="grace-period-end-date" />
								</th>
								<th class="table-cell-expand">
									<liferay-ui:message key="status" />
								</th>
								<th class="table-cell-expand">
									<liferay-ui:message key="subscription-term" />
								</th>
								<th class="table-cell-expand">
									<liferay-ui:message key="salesforce-opportunity-key" />
								</th>
							</c:if>

							<th class="table-cell-expand">
								<liferay-ui:message key="account-name" />
							</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td class="table-cell-expand" id="<portlet:namespace />productName">
								<%= (productPurchase != null) ? productPurchaseDisplay.getProductName() : "" %>
							</td>

							<c:if test="<%= productPurchase == null %>">
								<td class="table-cell-expand">
									<aui:input cssClass="account-edit-subscription" label="" name="salesforceOpportunityKey">
										<aui:validator name="required" />
									</aui:input>
								</td>
							</c:if>

							<td class="table-cell-expand">
								<aui:input cssClass="account-edit-subscription" label="" name="quantity" value="<%= quantity %>" />
							</td>

							<%
							String taglibOnClick = renderResponse.getNamespace() + "toggleDate(this.checked, 'startDate');" + renderResponse.getNamespace() + "toggleDate(this.checked, 'endDate');" + renderResponse.getNamespace() + "toggleDate(this.checked, 'gracePeriodEndDate');";
							%>

							<td class="table-cell-expand">
								<aui:input checked="<%= perpetual %>" cssClass="account-edit-subscription" label="" name="perpetual" onClick="<%= taglibOnClick %>" type="checkbox" />
							</td>

							<%
							Calendar startCal = CalendarFactoryUtil.getCalendar(timeZone, locale);

							if ((productPurchase != null) && (productPurchase.getStartDate() != null)) {
								startCal.setTime(productPurchase.getStartDate());
							}
							%>

							<td class="table-cell-expand">
								<liferay-ui:input-date
									dayParam="startDateDay"
									dayValue="<%= startCal.get(Calendar.DATE) %>"
									disabled="<%= perpetual %>"
									monthParam="startDateMonth"
									monthValue="<%= startCal.get(Calendar.MONTH) %>"
									name="startDate"
									yearParam="startDateYear"
									yearValue="<%= startCal.get(Calendar.YEAR) %>"
								/>
							</td>

							<%
							Calendar endCal = CalendarFactoryUtil.getCalendar(timeZone, locale);

							if ((productPurchase != null) && (productPurchase.getOriginalEndDate() != null)) {
								endCal.setTime(productPurchase.getOriginalEndDate());
							}
							%>

							<td class="table-cell-expand">
								<liferay-ui:input-date
									dayParam="endDateDay"
									dayValue="<%= endCal.get(Calendar.DATE) %>"
									disabled="<%= perpetual %>"
									monthParam="endDateMonth"
									monthValue="<%= endCal.get(Calendar.MONTH) %>"
									name="endDate"
									yearParam="endDateYear"
									yearValue="<%= endCal.get(Calendar.YEAR) %>"
								/>
							</td>
							<td class="table-cell-expand">
								<aui:input cssClass="account-edit-subscription" label="" name="sizing" value="<%= sizing %>" />
							</td>

							<c:if test="<%= productPurchase != null %>">

								<%
								Calendar gracePeriodEndCal = CalendarFactoryUtil.getCalendar(timeZone, locale);

								if (productPurchase.getEndDate() != null) {
									gracePeriodEndCal.setTime(productPurchase.getEndDate());
								}
								%>

								<td class="table-cell-expand">
									<liferay-ui:input-date
										dayParam="gracePeriodEndDateDay"
										dayValue="<%= gracePeriodEndCal.get(Calendar.DATE) %>"
										disabled="<%= perpetual %>"
										monthParam="gracePeriodEndDateMonth"
										monthValue="<%= gracePeriodEndCal.get(Calendar.MONTH) %>"
										name="gracePeriodEndDate"
										yearParam="gracePeriodEndDateYear"
										yearValue="<%= gracePeriodEndCal.get(Calendar.YEAR) %>"
									/>
								</td>
								<td class="table-cell-expand">
									<aui:select cssClass="account-edit-subscription" label="" name="status">

										<%
										for (ProductPurchase.Status status : ProductPurchase.Status.values()) {
										%>

											<aui:option label="<%= status.toString() %>" selected="<%= productPurchase.getStatus() == status %>" value="<%= status.toString() %>" />

										<%
										}
										%>

									</aui:select>
								</td>
								<td class="table-cell-expand">
									<%= productPurchaseDisplay.getSupportLife() %>
								</td>
								<td class="table-cell-expand">
									<%= productPurchaseDisplay.getSalesforceOpportunityKey() %>
								</td>
							</c:if>

							<td class="table-cell-expand">
								<%= editProductPurchaseDisplayContext.getAccountName() %>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<aui:button-row>
				<aui:button disabled="<%= productPurchase == null %>" name="submit" type="submit" />

				<aui:button href="<%= redirect %>" type="cancel" />
			</aui:button-row>
		</aui:form>
	</div>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />selectProduct',
		function() {
			<portlet:renderURL var="selectProductURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcRenderCommandName" value="/accounts/select_product_purchase_product" />
				<portlet:param name="accountKey" value="<%= editProductPurchaseDisplayContext.getAccountKey() %>" />
			</portlet:renderURL>

			var url = Liferay.Util.PortletURL.createPortletURL(
				'<%= selectProductURL.toString() %>',
				{
					productKey: document.getElementById(
						'<portlet:namespace />productKey'
					).value
				}
			);

			var A = AUI();

			var itemSelectorDialog = new A.LiferayItemSelectorDialog({
				eventName: '<portlet:namespace />selectProduct',
				title: '<liferay-ui:message key="select-subscription" />',
				url: url.toString()
			});

			itemSelectorDialog.on('selectedItemChange', function(event) {
				var selectedItem = event.newVal;

				if (selectedItem) {
					<portlet:namespace />toggleContent(selectedItem);
				}
			});

			itemSelectorDialog.open();
		},
		['aui-base', 'liferay-item-selector-dialog']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />toggleContent',
		function(selectedItem) {
			var emptyContent = document.getElementById(
				'<portlet:namespace />emptyContent'
			);

			if (emptyContent) {
				emptyContent.setAttribute('hidden', true);
			}

			var subscriptionContent = document.getElementById(
				'<portlet:namespace />subscriptionContent'
			);

			if (subscriptionContent) {
				subscriptionContent.removeAttribute('hidden');
			}

			var productName = document.getElementById(
				'<portlet:namespace />productName'
			);

			if (productName) {
				productName.innerHTML = selectedItem.name;
			}

			var productKey = document.getElementById(
				'<portlet:namespace />productKey'
			);

			if (productKey) {
				productKey.value = selectedItem.key;
			}

			var submit = document.getElementById('<portlet:namespace />submit');

			if (submit) {
				submit.removeAttribute('disabled');
				submit.classList.remove('disabled');
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />toggleDate',
		function(checked, dateParam) {
			var A = AUI();

			if (checked) {
				A.one('#<portlet:namespace />perpetual').val(true);
			}
			else {
				A.one('#<portlet:namespace />perpetual').val(false);
			}

			var dayField = A.one('#<portlet:namespace />' + dateParam + 'Day');

			if (dayField) {
				dayField.attr('disabled', checked);
			}

			var inputDateField = A.one('#<portlet:namespace />' + dateParam);

			if (inputDateField) {
				inputDateField.attr('disabled', checked);

				if (checked) {
					inputDateField.addClass('disabled');
				}
				else {
					inputDateField.removeClass('disabled');
				}
			}

			var monthField = A.one('#<portlet:namespace />' + dateParam + 'Month');

			if (monthField) {
				monthField.attr('disabled', checked);
			}

			var yearField = A.one('#<portlet:namespace />' + dateParam + 'Year');

			if (yearField) {
				yearField.attr('disabled', checked);
			}
		},
		['aui-base']
	);

	var emptyContent = document.getElementById('<portlet:namespace />emptyContent');

	if (!emptyContent) {
		var subscriptionContent = document.getElementById(
			'<portlet:namespace />subscriptionContent'
		);

		if (subscriptionContent) {
			subscriptionContent.removeAttribute('hidden');
		}
	}
</aui:script>