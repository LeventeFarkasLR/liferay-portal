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

package com.liferay.osb.koroneiki.trunk.service.impl;

import com.liferay.osb.koroneiki.trunk.constants.TrunkActionKeys;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.permission.ProductEntryPermission;
import com.liferay.osb.koroneiki.trunk.service.base.ProductEntryServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"json.web.service.context.name=koroneiki",
		"json.web.service.context.path=ProductEntry"
	},
	service = AopService.class
)
public class ProductEntryServiceImpl extends ProductEntryServiceBaseImpl {

	public ProductEntry addProductEntry(String name) throws PortalException {
		_productEntryPermission.check(
			getPermissionChecker(), TrunkActionKeys.ADD_PRODUCT_ENTRY);

		return productEntryLocalService.addProductEntry(getUserId(), name);
	}

	@Override
	public ProductEntry deleteProductEntry(long productEntryId)
		throws PortalException {

		_productEntryPermission.check(
			getPermissionChecker(), productEntryId, ActionKeys.DELETE);

		return productEntryLocalService.deleteProductEntry(productEntryId);
	}

	public ProductEntry updateProductEntry(long productEntryId, String name)
		throws PortalException {

		_productEntryPermission.check(
			getPermissionChecker(), productEntryId, ActionKeys.UPDATE);

		return productEntryLocalService.updateProductEntry(
			productEntryId, name);
	}

	@Reference
	private ProductEntryPermission _productEntryPermission;

}