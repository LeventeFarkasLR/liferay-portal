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

package com.liferay.osb.koroneiki.taproot.service.persistence;

import com.liferay.osb.koroneiki.taproot.model.ContactAccountRole;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the contact account role service. This utility wraps <code>com.liferay.osb.koroneiki.taproot.service.persistence.impl.ContactAccountRolePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContactAccountRolePersistence
 * @generated
 */
@ProviderType
public class ContactAccountRoleUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ContactAccountRole contactAccountRole) {
		getPersistence().clearCache(contactAccountRole);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, ContactAccountRole> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ContactAccountRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ContactAccountRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ContactAccountRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ContactAccountRole update(
		ContactAccountRole contactAccountRole) {

		return getPersistence().update(contactAccountRole);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ContactAccountRole update(
		ContactAccountRole contactAccountRole, ServiceContext serviceContext) {

		return getPersistence().update(contactAccountRole, serviceContext);
	}

	/**
	 * Returns all the contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @return the matching contact account roles
	 */
	public static List<ContactAccountRole> findByC_A(
		long contactId, long accountId) {

		return getPersistence().findByC_A(contactId, accountId);
	}

	/**
	 * Returns a range of all the contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @return the range of matching contact account roles
	 */
	public static List<ContactAccountRole> findByC_A(
		long contactId, long accountId, int start, int end) {

		return getPersistence().findByC_A(contactId, accountId, start, end);
	}

	/**
	 * Returns an ordered range of all the contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact account roles
	 */
	public static List<ContactAccountRole> findByC_A(
		long contactId, long accountId, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return getPersistence().findByC_A(
			contactId, accountId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching contact account roles
	 */
	public static List<ContactAccountRole> findByC_A(
		long contactId, long accountId, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByC_A(
			contactId, accountId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	 * Returns the first contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	public static ContactAccountRole findByC_A_First(
			long contactId, long accountId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactAccountRoleException {

		return getPersistence().findByC_A_First(
			contactId, accountId, orderByComparator);
	}

	/**
	 * Returns the first contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	public static ContactAccountRole fetchByC_A_First(
		long contactId, long accountId,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return getPersistence().fetchByC_A_First(
			contactId, accountId, orderByComparator);
	}

	/**
	 * Returns the last contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	public static ContactAccountRole findByC_A_Last(
			long contactId, long accountId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactAccountRoleException {

		return getPersistence().findByC_A_Last(
			contactId, accountId, orderByComparator);
	}

	/**
	 * Returns the last contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	public static ContactAccountRole fetchByC_A_Last(
		long contactId, long accountId,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return getPersistence().fetchByC_A_Last(
			contactId, accountId, orderByComparator);
	}

	/**
	 * Returns the contact account roles before and after the current contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactAccountRolePK the primary key of the current contact account role
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact account role
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	public static ContactAccountRole[] findByC_A_PrevAndNext(
			ContactAccountRolePK contactAccountRolePK, long contactId,
			long accountId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactAccountRoleException {

		return getPersistence().findByC_A_PrevAndNext(
			contactAccountRolePK, contactId, accountId, orderByComparator);
	}

	/**
	 * Removes all the contact account roles where contactId = &#63; and accountId = &#63; from the database.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 */
	public static void removeByC_A(long contactId, long accountId) {
		getPersistence().removeByC_A(contactId, accountId);
	}

	/**
	 * Returns the number of contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @return the number of matching contact account roles
	 */
	public static int countByC_A(long contactId, long accountId) {
		return getPersistence().countByC_A(contactId, accountId);
	}

	/**
	 * Caches the contact account role in the entity cache if it is enabled.
	 *
	 * @param contactAccountRole the contact account role
	 */
	public static void cacheResult(ContactAccountRole contactAccountRole) {
		getPersistence().cacheResult(contactAccountRole);
	}

	/**
	 * Caches the contact account roles in the entity cache if it is enabled.
	 *
	 * @param contactAccountRoles the contact account roles
	 */
	public static void cacheResult(
		List<ContactAccountRole> contactAccountRoles) {

		getPersistence().cacheResult(contactAccountRoles);
	}

	/**
	 * Creates a new contact account role with the primary key. Does not add the contact account role to the database.
	 *
	 * @param contactAccountRolePK the primary key for the new contact account role
	 * @return the new contact account role
	 */
	public static ContactAccountRole create(
		ContactAccountRolePK contactAccountRolePK) {

		return getPersistence().create(contactAccountRolePK);
	}

	/**
	 * Removes the contact account role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contactAccountRolePK the primary key of the contact account role
	 * @return the contact account role that was removed
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	public static ContactAccountRole remove(
			ContactAccountRolePK contactAccountRolePK)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactAccountRoleException {

		return getPersistence().remove(contactAccountRolePK);
	}

	public static ContactAccountRole updateImpl(
		ContactAccountRole contactAccountRole) {

		return getPersistence().updateImpl(contactAccountRole);
	}

	/**
	 * Returns the contact account role with the primary key or throws a <code>NoSuchContactAccountRoleException</code> if it could not be found.
	 *
	 * @param contactAccountRolePK the primary key of the contact account role
	 * @return the contact account role
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	public static ContactAccountRole findByPrimaryKey(
			ContactAccountRolePK contactAccountRolePK)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactAccountRoleException {

		return getPersistence().findByPrimaryKey(contactAccountRolePK);
	}

	/**
	 * Returns the contact account role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contactAccountRolePK the primary key of the contact account role
	 * @return the contact account role, or <code>null</code> if a contact account role with the primary key could not be found
	 */
	public static ContactAccountRole fetchByPrimaryKey(
		ContactAccountRolePK contactAccountRolePK) {

		return getPersistence().fetchByPrimaryKey(contactAccountRolePK);
	}

	/**
	 * Returns all the contact account roles.
	 *
	 * @return the contact account roles
	 */
	public static List<ContactAccountRole> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the contact account roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @return the range of contact account roles
	 */
	public static List<ContactAccountRole> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the contact account roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of contact account roles
	 */
	public static List<ContactAccountRole> findAll(
		int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the contact account roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of contact account roles
	 */
	public static List<ContactAccountRole> findAll(
		int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the contact account roles from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of contact account roles.
	 *
	 * @return the number of contact account roles
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static ContactAccountRolePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ContactAccountRolePersistence, ContactAccountRolePersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ContactAccountRolePersistence.class);

		ServiceTracker
			<ContactAccountRolePersistence, ContactAccountRolePersistence>
				serviceTracker =
					new ServiceTracker
						<ContactAccountRolePersistence,
						 ContactAccountRolePersistence>(
							 bundle.getBundleContext(),
							 ContactAccountRolePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}