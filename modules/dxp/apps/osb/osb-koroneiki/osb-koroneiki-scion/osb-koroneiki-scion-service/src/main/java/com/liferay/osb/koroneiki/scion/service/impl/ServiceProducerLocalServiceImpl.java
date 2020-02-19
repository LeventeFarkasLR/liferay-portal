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

package com.liferay.osb.koroneiki.scion.service.impl;

import com.liferay.osb.koroneiki.scion.exception.ServiceProducerNameException;
import com.liferay.osb.koroneiki.scion.model.ServiceProducer;
import com.liferay.osb.koroneiki.scion.service.base.ServiceProducerLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 * @author Amos Fong
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.scion.model.ServiceProducer",
	service = AopService.class
)
public class ServiceProducerLocalServiceImpl
	extends ServiceProducerLocalServiceBaseImpl {

	public ServiceProducer addServiceProducer(
			long userId, String name, String description)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		validate(name);

		long serviceProducerId = counterLocalService.increment();

		// Service producer

		ServiceProducer serviceProducer = serviceProducerPersistence.create(
			serviceProducerId);

		serviceProducer.setCompanyId(user.getCompanyId());
		serviceProducer.setUserId(userId);
		serviceProducer.setName(name);
		serviceProducer.setDescription(description);

		// User

		String emailAddress = getIdentifier(serviceProducerId) + "@liferay.com";

		User authorizationUser = userLocalService.addUser(
			userId, user.getCompanyId(), false, name, name, false,
			String.valueOf(serviceProducerId), emailAddress, 0,
			StringPool.BLANK, LocaleUtil.US, name, StringPool.BLANK,
			"Service Producer", 0, 0, true, Calendar.JANUARY, 1, 1970,
			StringPool.BLANK, null, null, null, null, false,
			new ServiceContext());

		serviceProducer.setAuthorizationUserId(authorizationUser.getUserId());

		// Role

		Map<Locale, String> titleMap = new HashMap<Locale, String>() {
			{
				put(LocaleUtil.US, name + " Service Producer");
			}
		};

		Map<Locale, String> descriptionMap = new HashMap<Locale, String>() {
			{
				put(
					LocaleUtil.US,
					"This is an autogenerated Service Producer role.");
			}
		};

		Role role = _roleLocalService.addRole(
			userId, StringPool.BLANK, 0, getIdentifier(serviceProducerId),
			titleMap, descriptionMap, RoleConstants.TYPE_REGULAR,
			"service-producer", new ServiceContext());

		_roleLocalService.addUserRole(
			authorizationUser.getUserId(), role.getRoleId());

		return serviceProducerPersistence.update(serviceProducer);
	}

	public ServiceProducer deleteServiceProducer(long serviceProducerId)
		throws PortalException {

		ServiceProducer serviceProducer =
			serviceProducerPersistence.findByPrimaryKey(serviceProducerId);

		return deleteServiceProducer(serviceProducer);
	}

	public ServiceProducer deleteServiceProducer(
		ServiceProducer serviceProducer) {

		// Authentication tokens

		authenticationTokenPersistence.removeByServiceProducerId(
			serviceProducer.getServiceProducerId());

		// Role

		try {
			Role role = _roleLocalService.getRole(
				serviceProducer.getCompanyId(),
				getIdentifier(serviceProducer.getServiceProducerId()));

			_roleLocalService.deleteUserRole(
				serviceProducer.getAuthorizationUserId(), role.getRoleId());

			_roleLocalService.deleteRole(role);
		}
		catch (Exception exception) {
			_log.error(
				"Unable to remove associated role " +
					getIdentifier(serviceProducer.getServiceProducerId()),
				exception);
		}

		// User

		try {
			userLocalService.updateStatus(
				serviceProducer.getAuthorizationUserId(),
				WorkflowConstants.STATUS_INACTIVE, new ServiceContext());
		}
		catch (Exception exception) {
			_log.error(
				"Unable to deactivate user " +
					serviceProducer.getAuthorizationUserId(),
				exception);
		}

		return serviceProducerPersistence.remove(serviceProducer);
	}

	public ServiceProducer fetchAuthorizedServiceProducer(
		long authorizationUserId) {

		return serviceProducerPersistence.fetchByAuthorizationUserId(
			authorizationUserId);
	}

	public ServiceProducer getAuthorizedServiceProducer(
			long authorizationUserId)
		throws PortalException {

		return serviceProducerPersistence.findByAuthorizationUserId(
			authorizationUserId);
	}

	public ServiceProducer updateServiceProducer(
			long serviceProducerId, String name, String description)
		throws PortalException {

		validate(name);

		ServiceProducer serviceProducer =
			serviceProducerPersistence.findByPrimaryKey(serviceProducerId);

		serviceProducer.setName(name);
		serviceProducer.setDescription(description);

		serviceProducer = serviceProducerPersistence.update(serviceProducer);

		// User

		User user = userLocalService.getUser(
			serviceProducer.getAuthorizationUserId());

		user.setFirstName(name);

		userLocalService.updateUser(user);

		// Role

		Role role = _roleLocalService.getRole(
			serviceProducer.getCompanyId(), getIdentifier(serviceProducerId));

		role.setTitleMap(
			new HashMap<Locale, String>() {
				{
					put(LocaleUtil.US, name + " Service Producer");
				}
			});

		_roleLocalService.updateRole(role);

		return serviceProducer;
	}

	protected String getIdentifier(long serviceProducerId) {
		return "service_producer." + serviceProducerId;
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new ServiceProducerNameException();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ServiceProducerLocalServiceImpl.class);

	@Reference
	private RoleLocalService _roleLocalService;

}