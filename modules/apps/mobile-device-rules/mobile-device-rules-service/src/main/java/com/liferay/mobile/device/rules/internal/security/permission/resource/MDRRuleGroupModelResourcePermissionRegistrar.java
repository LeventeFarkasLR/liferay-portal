/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.mobile.device.rules.internal.security.permission.resource;

import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.mobile.device.rules.constants.MDRPortletKeys;
import com.liferay.mobile.device.rules.model.MDRRuleGroup;
import com.liferay.mobile.device.rules.service.MDRRuleGroupLocalService;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.StagedModelPermissionLogic;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class MDRRuleGroupModelResourcePermissionRegistrar {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceRegistration = bundleContext.registerService(
			(Class<ModelResourcePermission<MDRRuleGroup>>)
				(Class<?>)ModelResourcePermission.class,
			ModelResourcePermissionFactory.create(
				MDRRuleGroup.class, MDRRuleGroup::getRuleGroupId,
				_mdrRuleGroupLocalService::getMDRRuleGroup,
				_portletResourcePermission,
				(modelResourcePermission, consumer) -> consumer.accept(
					new StagedModelPermissionLogic<>(
						_stagingPermission, MDRPortletKeys.MOBILE_DEVICE_RULES,
						MDRRuleGroup::getRuleGroupId))),
			HashMapDictionaryBuilder.<String, Object>put(
				"model.class.name", MDRRuleGroup.class.getName()
			).build());
	}

	@Deactivate
	protected void deactivate() {
		_serviceRegistration.unregister();
	}

	@Reference
	private MDRRuleGroupLocalService _mdrRuleGroupLocalService;

	@Reference
	private PortletResourcePermission _portletResourcePermission;

	private ServiceRegistration<ModelResourcePermission<MDRRuleGroup>>
		_serviceRegistration;

	@Reference
	private StagingPermission _stagingPermission;

}