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

package com.liferay.osb.koroneiki.taproot.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.koroneiki.taproot.service.AccountServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>AccountServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountServiceSoap
 * @generated
 */
@ProviderType
public class AccountServiceHttp {

	public static com.liferay.osb.koroneiki.taproot.model.Account addAccount(
			HttpPrincipal httpPrincipal, String name, String description,
			long logoId, long addressId, String contactEmailAddress,
			String profileEmailAddress, String phoneNumber, String faxNumber,
			String website, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountServiceUtil.class, "addAccount",
				_addAccountParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, name, description, logoId, addressId,
				contactEmailAddress, profileEmailAddress, phoneNumber,
				faxNumber, website, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.osb.koroneiki.taproot.model.Account)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.Account deleteAccount(
			HttpPrincipal httpPrincipal, long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountServiceUtil.class, "deleteAccount",
				_deleteAccountParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.osb.koroneiki.taproot.model.Account)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.Account getAccount(
			HttpPrincipal httpPrincipal, long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountServiceUtil.class, "getAccount",
				_getAccountParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.osb.koroneiki.taproot.model.Account)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.Account updateAccount(
			HttpPrincipal httpPrincipal, long accountId, String name,
			String description, long logoId, long addressId,
			String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountServiceUtil.class, "updateAccount",
				_updateAccountParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId, name, description, logoId, addressId,
				contactEmailAddress, profileEmailAddress, phoneNumber,
				faxNumber, website, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.osb.koroneiki.taproot.model.Account)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AccountServiceHttp.class);

	private static final Class<?>[] _addAccountParameterTypes0 = new Class[] {
		String.class, String.class, long.class, long.class, String.class,
		String.class, String.class, String.class, String.class, int.class
	};
	private static final Class<?>[] _deleteAccountParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _getAccountParameterTypes2 = new Class[] {
		long.class
	};
	private static final Class<?>[] _updateAccountParameterTypes3 =
		new Class[] {
			long.class, String.class, String.class, long.class, long.class,
			String.class, String.class, String.class, String.class,
			String.class, int.class
		};

}