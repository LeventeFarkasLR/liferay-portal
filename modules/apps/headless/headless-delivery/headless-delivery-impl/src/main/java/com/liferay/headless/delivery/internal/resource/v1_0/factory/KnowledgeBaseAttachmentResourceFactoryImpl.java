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

package com.liferay.headless.delivery.internal.resource.v1_0.factory;

import com.liferay.headless.delivery.resource.v1_0.KnowledgeBaseAttachmentResource;
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
 * @author Javier Gamarra
 * @generated
 */
@Component(
	immediate = true, service = KnowledgeBaseAttachmentResource.Factory.class
)
@Generated("")
public class KnowledgeBaseAttachmentResourceFactoryImpl
	implements KnowledgeBaseAttachmentResource.Factory {

	@Override
	public KnowledgeBaseAttachmentResource.Builder create() {
		return new KnowledgeBaseAttachmentResource.Builder() {

			@Override
			public KnowledgeBaseAttachmentResource build() {
				if (_user == null) {
					throw new IllegalArgumentException("User is not set");
				}

				return (KnowledgeBaseAttachmentResource)
					ProxyUtil.newProxyInstance(
						KnowledgeBaseAttachmentResource.class.getClassLoader(),
						new Class<?>[] {KnowledgeBaseAttachmentResource.class},
						(proxy, method, arguments) -> _invoke(
							method, arguments, _checkPermissions,
							_httpServletRequest, _user));
			}

			@Override
			public KnowledgeBaseAttachmentResource.Builder checkPermissions(
				boolean checkPermissions) {

				_checkPermissions = checkPermissions;

				return this;
			}

			@Override
			public KnowledgeBaseAttachmentResource.Builder httpServletRequest(
				HttpServletRequest httpServletRequest) {

				_httpServletRequest = httpServletRequest;

				return this;
			}

			@Override
			public KnowledgeBaseAttachmentResource.Builder user(User user) {
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
		KnowledgeBaseAttachmentResource.FactoryHolder.factory = this;
	}

	@Deactivate
	protected void deactivate() {
		KnowledgeBaseAttachmentResource.FactoryHolder.factory = null;
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

		KnowledgeBaseAttachmentResource knowledgeBaseAttachmentResource =
			_componentServiceObjects.getService();

		knowledgeBaseAttachmentResource.setContextAcceptLanguage(
			new AcceptLanguageImpl(user));

		Company company = _companyLocalService.getCompany(user.getCompanyId());

		knowledgeBaseAttachmentResource.setContextCompany(company);

		knowledgeBaseAttachmentResource.setContextHttpServletRequest(
			httpServletRequest);
		knowledgeBaseAttachmentResource.setContextUser(user);

		try {
			return method.invoke(knowledgeBaseAttachmentResource, arguments);
		}
		catch (InvocationTargetException invocationTargetException) {
			throw invocationTargetException.getTargetException();
		}
		finally {
			_componentServiceObjects.ungetService(
				knowledgeBaseAttachmentResource);

			PrincipalThreadLocal.setName(name);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);
		}
	}

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<KnowledgeBaseAttachmentResource>
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