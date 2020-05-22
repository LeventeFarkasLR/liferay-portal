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

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/product_purchases/edit_product_purchase"
	},
	service = MVCActionCommand.class
)
public class EditProductPurchaseMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			updateProductPurchase(actionRequest);

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	protected void updateProductPurchase(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		String productPurchaseKey = ParamUtil.getString(
			actionRequest, "productPurchaseKey");

		int startDateMonth = ParamUtil.getInteger(
			actionRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(actionRequest, "startDateDay");
		int startDateYear = ParamUtil.getInteger(
			actionRequest, "startDateYear");

		Date startDate = _portal.getDate(
			startDateMonth, startDateDay, startDateYear,
			themeDisplay.getTimeZone(), null);

		int endDateMonth = ParamUtil.getInteger(actionRequest, "endDateMonth");
		int endDateDay = ParamUtil.getInteger(actionRequest, "endDateDay");
		int endDateYear = ParamUtil.getInteger(actionRequest, "endDateYear");

		Date endDate = _portal.getDate(
			endDateMonth, endDateDay, endDateYear, themeDisplay.getTimeZone(),
			null);

		int originalEndDateMonth = ParamUtil.getInteger(
			actionRequest, "originalEndDateMonth");
		int originalEndDateDay = ParamUtil.getInteger(
			actionRequest, "originalEndDateDay");
		int originalEndDateYear = ParamUtil.getInteger(
			actionRequest, "originalEndDateYear");

		Date originalEndDate = _portal.getDate(
			originalEndDateMonth, originalEndDateDay, originalEndDateYear,
			themeDisplay.getTimeZone(), null);

		int quantity = ParamUtil.getInteger(actionRequest, "quantity");
		int sizing = ParamUtil.getInteger(actionRequest, "sizing");

		Map<String, String> properties = new HashMap<>();

		properties.put("sizing", String.valueOf(sizing));

		ProductPurchase productPurchase = new ProductPurchase();

		productPurchase.setStartDate(startDate);
		productPurchase.setEndDate(endDate);
		productPurchase.setOriginalEndDate(originalEndDate);
		productPurchase.setQuantity(quantity);
		productPurchase.setProperties(properties);

		_productPurchaseWebService.updateProductPurchase(
			user.getFullName(), StringPool.BLANK, productPurchaseKey,
			productPurchase);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditProductPurchaseMVCActionCommand.class);

	@Reference
	private Portal _portal;

	@Reference
	private ProductPurchaseWebService _productPurchaseWebService;

}