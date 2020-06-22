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

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.ExternalLinkWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

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
		"javax.portlet.name=" + ProvisioningPortletKeys.PRODUCTS,
		"mvc.command.name=/products/edit_product"
	},
	service = MVCActionCommand.class
)
public class EditProductMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteProduct(ActionRequest actionRequest, User user)
		throws Exception {

		String productKey = ParamUtil.getString(actionRequest, "productKey");

		_productWebService.deleteProduct(
			user.getFullName(), StringPool.BLANK, productKey);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			User user = themeDisplay.getUser();

			if (cmd.equals(Constants.DELETE)) {
				deleteProduct(actionRequest, user);
			}
			else {
				updateProduct(actionRequest, user);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	protected void updateDossieraMapping(
			User user, String productKey, ExternalLink externalLink)
		throws Exception {

		Product oldProduct = _productWebService.getProduct(productKey);

		ExternalLink[] externalLinks = oldProduct.getExternalLinks();

		if (externalLinks != null) {
			for (ExternalLink curExternalLink : externalLinks) {
				String domain = curExternalLink.getDomain();
				String entityName = curExternalLink.getEntityName();

				if (domain.equals(ExternalLinkDomain.DOSSIERA) &&
					entityName.equals(
						ExternalLinkEntityName.DOSSIERA_PRODUCT)) {

					if (externalLink == null) {
						_externalLinkWebService.deleteExternalLink(
							user.getFullName(), StringPool.BLANK,
							curExternalLink.getKey());
					}
					else {
						_externalLinkWebService.updateExternalLink(
							user.getFullName(), StringPool.BLANK,
							curExternalLink.getKey(), externalLink);
					}

					return;
				}
			}
		}

		if (externalLink != null) {
			_externalLinkWebService.addProductExternalLink(
				user.getFullName(), StringPool.BLANK, productKey, externalLink);
		}
	}

	protected void updateProduct(ActionRequest actionRequest, User user)
		throws Exception {

		String productKey = ParamUtil.getString(actionRequest, "productKey");

		String name = ParamUtil.getString(actionRequest, "name");
		String type = ParamUtil.getString(actionRequest, "type");
		String dossieraIdMapping = ParamUtil.getString(
			actionRequest, "dossieraIdMapping");

		Product product = new Product();

		product.setName(name);

		Map<String, String> properties = new HashMap<>();

		if (Validator.isNotNull(type)) {
			properties.put("type", type);
		}

		ExternalLink externalLink = null;

		if (Validator.isNotNull(dossieraIdMapping)) {
			externalLink = new ExternalLink();

			externalLink.setDomain(ExternalLinkDomain.DOSSIERA);
			externalLink.setEntityId(dossieraIdMapping);
			externalLink.setEntityName(ExternalLinkEntityName.DOSSIERA_PRODUCT);

			product.setExternalLinks(new ExternalLink[] {externalLink});
		}

		product.setProperties(properties);

		if (Validator.isNull(productKey)) {
			_productWebService.addProduct(
				user.getFullName(), StringPool.BLANK, product);
		}
		else {
			updateDossieraMapping(user, productKey, externalLink);

			_productWebService.updateProduct(
				user.getFullName(), StringPool.BLANK, productKey, product);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditProductMVCActionCommand.class);

	@Reference
	private ExternalLinkWebService _externalLinkWebService;

	@Reference
	private ProductWebService _productWebService;

}