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

package com.liferay.data.engine.rest.internal.resource.v1_0.factory;

import com.liferay.data.engine.rest.resource.v1_0.DataRecordResource;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Jeyvison Nascimento
 * @generated
 */
@Component(immediate = true, service = DataRecordResource.Factory.class)
@Generated("")
public class DataRecordResourceFactoryImpl
	implements DataRecordResource.Factory {

	@Override
	public DataRecordResource.Builder create() {
		return new DataRecordResource.Builder() {

			@Override
			public DataRecordResource build() {
				if (_user == null) {
					throw new IllegalArgumentException("User is not set");
				}

				return (DataRecordResource)ProxyUtil.newProxyInstance(
					DataRecordResource.class.getClassLoader(),
					new Class<?>[] {DataRecordResource.class},
					(proxy, method, arguments) -> _invoke(
						method, arguments, _checkPermissions,
						_httpServletRequest, _user));
			}

			@Override
			public DataRecordResource.Builder checkPermissions(
				boolean checkPermissions) {

				_checkPermissions = checkPermissions;

				return this;
			}

			@Override
			public DataRecordResource.Builder httpServletRequest(
				HttpServletRequest httpServletRequest) {

				_httpServletRequest = httpServletRequest;

				return this;
			}

			@Override
			public DataRecordResource.Builder user(User user) {
				_user = user;

				return this;
			}

			private boolean _checkPermissions = true;
			private HttpServletRequest _httpServletRequest;
			private User _user;

		};
	}

	@Activate
	protected void activate() {
		DataRecordResource.FactoryHolder.factory = this;
	}

	@Deactivate
	protected void deactivate() {
		DataRecordResource.FactoryHolder.factory = null;
	}

	private Object _invoke(
			Method method, Object[] arguments, boolean checkPermissions,
			HttpServletRequest httpServletRequest, User user)
		throws Throwable {

		String name = PrincipalThreadLocal.getName();

		PrincipalThreadLocal.setName(user.getUserId());

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (checkPermissions) {
			PermissionThreadLocal.setPermissionChecker(
				_defaultPermissionCheckerFactory.create(user));
		}
		else {
			PermissionThreadLocal.setPermissionChecker(
				_liberalPermissionCheckerFactory.create(user));
		}

		DataRecordResource dataRecordResource =
			_componentServiceObjects.getService();

		dataRecordResource.setContextAcceptLanguage(
			new AcceptLanguageImpl(user));

		Company company = _companyLocalService.getCompany(user.getCompanyId());

		dataRecordResource.setContextCompany(company);

		dataRecordResource.setContextHttpServletRequest(httpServletRequest);
		dataRecordResource.setContextUser(user);

		try {
			return method.invoke(dataRecordResource, arguments);
		}
		catch (InvocationTargetException invocationTargetException) {
			throw invocationTargetException.getTargetException();
		}
		finally {
			_componentServiceObjects.ungetService(dataRecordResource);

			PrincipalThreadLocal.setName(name);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);
		}
	}

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<DataRecordResource>
		_componentServiceObjects;

	@Reference
	private PermissionCheckerFactory _defaultPermissionCheckerFactory;

	@Reference(target = "(permission.checker.type=liberal)")
	private PermissionCheckerFactory _liberalPermissionCheckerFactory;

	@Reference
	private UserLocalService _userLocalService;

	private class AcceptLanguageImpl implements AcceptLanguage {

		public AcceptLanguageImpl(User user) {
			_user = user;
		}

		@Override
		public List<Locale> getLocales() {
			return Collections.emptyList();
		}

		@Override
		public String getPreferredLanguageId() {
			return LocaleUtil.toLanguageId(getPreferredLocale());
		}

		@Override
		public Locale getPreferredLocale() {
			List<Locale> locales = getLocales();

			if (ListUtil.isNotEmpty(locales)) {
				return locales.get(0);
			}

			return _user.getLocale();
		}

		@Override
		public boolean isAcceptAllLanguages() {
			return false;
		}

		private final User _user;

	}

}