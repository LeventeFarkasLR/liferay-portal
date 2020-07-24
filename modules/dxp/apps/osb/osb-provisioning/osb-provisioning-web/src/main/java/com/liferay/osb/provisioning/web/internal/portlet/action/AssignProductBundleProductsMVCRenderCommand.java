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

import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Arrays;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Yuanyuan Huang
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.PRODUCT_BUNDLES,
		"mvc.command.name=/product_bundles/assign_products"
	},
	service = MVCRenderCommand.class
)
public class AssignProductBundleProductsMVCRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			String[] productKeys = ParamUtil.getStringValues(
				renderRequest, "productKeys");

			if (Validator.isNotNull(productKeys)) {
				renderRequest.setAttribute(
					"productKeys", Arrays.asList(productKeys));
			}

			return "/product_bundles/assign_products.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass(), exception);

			return "/common/error.jsp";
		}
	}

}