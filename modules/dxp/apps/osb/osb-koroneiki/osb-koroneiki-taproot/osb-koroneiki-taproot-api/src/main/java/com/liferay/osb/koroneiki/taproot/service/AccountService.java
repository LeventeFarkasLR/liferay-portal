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

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for Account. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AccountServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface AccountService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.osb.koroneiki.taproot.service.impl.AccountServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the account remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link AccountServiceUtil} if injection and service tracking are not available.
	 */
	public Account addAccount(
			long parentAccountId, String name, String code, String description,
			long logoId, String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website, String tier,
			String region, boolean internal, String status)
		throws PortalException;

	public Account deleteAccount(long accountId) throws PortalException;

	public Account deleteAccount(String accountKey) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Account getAccount(long accountId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Account getAccount(String accountKey) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Account> getAccounts(long parentAccountId, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Account> getAccounts(
			String domain, String entityName, String entityId, int start,
			int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAccountsCount(long parentAccountId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAccountsCount(
			String domain, String entityName, String entityId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Account> getContactAccounts(long contactId, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getContactAccountsCount(long contactId) throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Account> getTeamAccounts(long teamId, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTeamAccountsCount(long teamId) throws PortalException;

	public Account updateAccount(
			long accountId, long parentAccountId, String name, String code,
			String description, long logoId, String contactEmailAddress,
			String profileEmailAddress, String phoneNumber, String faxNumber,
			String website, String tier, String region, boolean internal,
			String status)
		throws PortalException;

	public Account updateAccount(
			String accountKey, long parentAccountId, String name, String code,
			String description, long logoId, String contactEmailAddress,
			String profileEmailAddress, String phoneNumber, String faxNumber,
			String website, String tier, String region, boolean internal,
			String status)
		throws PortalException;

}