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

package com.liferay.osb.koroneiki.trunk.internal.permission;

import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.permission.ProductConsumptionPermission;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ProductConsumptionPermission.class)
public class ProductConsumptionPermissionImpl
	implements ProductConsumptionPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker, long productConsumptionId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, productConsumptionId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, ProductConsumption.class.getName(),
				productConsumptionId, actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker,
			ProductConsumption productConsumption, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, productConsumption, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, ProductConsumption.class.getName(),
				productConsumption.getProductConsumptionId(), actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long productConsumptionId,
			String actionId)
		throws PortalException {

		ProductConsumption productConsumption =
			_productConsumptionLocalService.getProductConsumption(
				productConsumptionId);

		if (contains(permissionChecker, productConsumption, actionId)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long[] productConsumptionIds,
			String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(productConsumptionIds)) {
			return false;
		}

		for (long productConsumptionId : productConsumptionIds) {
			if (!contains(permissionChecker, productConsumptionId, actionId)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			ProductConsumption productConsumption, String actionId)
		throws PortalException {

		if (permissionChecker.hasOwnerPermission(
				productConsumption.getCompanyId(),
				ProductConsumption.class.getName(),
				productConsumption.getProductConsumptionId(),
				productConsumption.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			0, ProductConsumption.class.getName(),
			productConsumption.getProductConsumptionId(), actionId);
	}

	@Reference
	private ProductConsumptionLocalService _productConsumptionLocalService;

}