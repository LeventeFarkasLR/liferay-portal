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
String redirect = ParamUtil.getString(request, "redirect");

ViewAccountDisplayContext viewAccountDisplayContext = ProvisioningWebComponentProvider.getViewAccountDisplayContext(renderRequest, renderResponse, request);

AccountDisplay accountDisplay = viewAccountDisplayContext.getAccountDisplay();
%>

<portlet:actionURL name="/edit_postal_address" var="editPostalAddressURL" />

<aui:form action="<%= editPostalAddressURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="accountKey" type="hidden" value="<%= accountDisplay.getKey() %>" />

	<aui:model-context model="<%= Address.class %>" />

	<aui:fieldset-group>
		<aui:fieldset>
			<aui:input id="addressPrimary" label="primary" name="addressPrimary" type="checkbox" />

			<liferay-ui:error key="<%= NoSuchListTypeException.class.getName() + Account.class.getName() + ListTypeConstants.ADDRESS %>" message="please-select-a-type" />

			<aui:select label="type" listType='<%= "com.liferay.portal.kernel.model.Contact" + ListTypeConstants.ADDRESS %>' name="addressType" />

			<aui:input fieldParam="streetAddressLine1" id="streetAddressLine1" name="street1" required="<%= true %>" />

			<aui:input fieldParam="streetAddressLine2" id="streetAddressLine2" name="street2" />

			<aui:input fieldParam="streetAddressLine3" id="streetAddressLine3" name="street3" />

			<aui:input fieldParam="addressLocality" id="addressLocality" name="city" required="<%= true %>" />

			<liferay-ui:error exception="<%= NoSuchCountryException.class %>" message="please-select-a-country" />

			<aui:select label="country" name="addressCountryId" />

			<liferay-ui:error exception="<%= NoSuchRegionException.class %>" message="please-select-a-region" />

			<aui:select id="addressRegionId" label="region" name="addressRegionId" />

			<div class="form-group">
				<label class="control-label" for="<portlet:namespace />addressZip">
					<liferay-ui:message key="postal-code" />

					<span hidden id="<portlet:namespace />addressZipRequiredWrapper">
						<aui:icon cssClass="reference-mark text-warning" image="asterisk" markupView="lexicon" />

						<span class="hide-accessible"><liferay-ui:message key="required" /></span>
					</span>
				</label>

				<aui:input fieldParam="addressZip" id="addressZip" label="" name="zip" />
			</div>

			<aui:input cssClass="mailing-ctrl" name="mailing" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button primary="<%= true %>" type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>

	<aui:script use="liferay-address,liferay-dynamic-select">
		new Liferay.DynamicSelect([
			{
				select: '<portlet:namespace />addressCountryId',
				selectData: Liferay.Address.getCountries,
				selectDesc: 'nameCurrentValue',
				selectId: 'countryId',
				selectSort: '<%= true %>',
				selectVal: '0'
			},
			{
				select: '<portlet:namespace />addressRegionId',
				selectData: Liferay.Address.getRegions,
				selectDesc: 'name',
				selectId: 'regionId',
				selectVal: '0'
			}
		]);
	</aui:script>
</aui:form>

<aui:script use="liferay-form">
	/* eslint-env es6 */
	const addressCountry = document.getElementById(
		'<portlet:namespace />addressCountryId'
	);

	function checkCountry(countryId) {
		Liferay.Service(
			'/country/get-country',
			{
				countryId: countryId
			},
			function(response, err) {
				if (err) {
					console.error(err);
				}
				else {
					updateAddressZipRequired(response.zipRequired);
				}
			}
		);
	}

	function handleSelectChange(event) {
		const value = Number(event.currentTarget.value);

		if (value > 0) {
			checkCountry(value);
		}
		else {
			updateAddressZipRequired(false);
		}
	}

	function updateAddressZipRequired(required) {
		const addressZipRequiredWrapper = document.getElementById(
			'<portlet:namespace />addressZipRequiredWrapper'
		);
		const formValidator = Liferay.Form.get('<portlet:namespace />fm')
			.formValidator;

		const rules = formValidator._getAttr('rules');

		if (required) {
			addressZipRequiredWrapper.removeAttribute('hidden');
		}
		else {
			addressZipRequiredWrapper.setAttribute('hidden', true);
		}

		rules.<portlet:namespace />addressZip = {required: required};
	}

	if (addressCountry) {
		addressCountry.addEventListener('change', handleSelectChange);
	}
</aui:script>