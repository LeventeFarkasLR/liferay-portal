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

package com.liferay.object.service.persistence;

import com.liferay.object.model.ObjectRelationship;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the object relationship service. This utility wraps <code>com.liferay.object.service.persistence.impl.ObjectRelationshipPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see ObjectRelationshipPersistence
 * @generated
 */
public class ObjectRelationshipUtil {

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
	public static void clearCache(ObjectRelationship objectRelationship) {
		getPersistence().clearCache(objectRelationship);
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
	public static Map<Serializable, ObjectRelationship> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ObjectRelationship> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ObjectRelationship> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ObjectRelationship> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ObjectRelationship> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ObjectRelationship update(
		ObjectRelationship objectRelationship) {

		return getPersistence().update(objectRelationship);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ObjectRelationship update(
		ObjectRelationship objectRelationship, ServiceContext serviceContext) {

		return getPersistence().update(objectRelationship, serviceContext);
	}

	/**
	 * Returns all the object relationships where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching object relationships
	 */
	public static List<ObjectRelationship> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the object relationships where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of object relationships
	 * @param end the upper bound of the range of object relationships (not inclusive)
	 * @return the range of matching object relationships
	 */
	public static List<ObjectRelationship> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the object relationships where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of object relationships
	 * @param end the upper bound of the range of object relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching object relationships
	 */
	public static List<ObjectRelationship> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ObjectRelationship> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the object relationships where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of object relationships
	 * @param end the upper bound of the range of object relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching object relationships
	 */
	public static List<ObjectRelationship> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ObjectRelationship> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first object relationship in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object relationship
	 * @throws NoSuchRelationshipException if a matching object relationship could not be found
	 */
	public static ObjectRelationship findByUuid_First(
			String uuid,
			OrderByComparator<ObjectRelationship> orderByComparator)
		throws com.liferay.object.exception.NoSuchRelationshipException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first object relationship in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object relationship, or <code>null</code> if a matching object relationship could not be found
	 */
	public static ObjectRelationship fetchByUuid_First(
		String uuid, OrderByComparator<ObjectRelationship> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last object relationship in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object relationship
	 * @throws NoSuchRelationshipException if a matching object relationship could not be found
	 */
	public static ObjectRelationship findByUuid_Last(
			String uuid,
			OrderByComparator<ObjectRelationship> orderByComparator)
		throws com.liferay.object.exception.NoSuchRelationshipException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last object relationship in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object relationship, or <code>null</code> if a matching object relationship could not be found
	 */
	public static ObjectRelationship fetchByUuid_Last(
		String uuid, OrderByComparator<ObjectRelationship> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the object relationships before and after the current object relationship in the ordered set where uuid = &#63;.
	 *
	 * @param objectRelationshipId the primary key of the current object relationship
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next object relationship
	 * @throws NoSuchRelationshipException if a object relationship with the primary key could not be found
	 */
	public static ObjectRelationship[] findByUuid_PrevAndNext(
			long objectRelationshipId, String uuid,
			OrderByComparator<ObjectRelationship> orderByComparator)
		throws com.liferay.object.exception.NoSuchRelationshipException {

		return getPersistence().findByUuid_PrevAndNext(
			objectRelationshipId, uuid, orderByComparator);
	}

	/**
	 * Removes all the object relationships where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of object relationships where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching object relationships
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the object relationships where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching object relationships
	 */
	public static List<ObjectRelationship> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the object relationships where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of object relationships
	 * @param end the upper bound of the range of object relationships (not inclusive)
	 * @return the range of matching object relationships
	 */
	public static List<ObjectRelationship> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the object relationships where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of object relationships
	 * @param end the upper bound of the range of object relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching object relationships
	 */
	public static List<ObjectRelationship> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ObjectRelationship> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the object relationships where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of object relationships
	 * @param end the upper bound of the range of object relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching object relationships
	 */
	public static List<ObjectRelationship> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ObjectRelationship> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first object relationship in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object relationship
	 * @throws NoSuchRelationshipException if a matching object relationship could not be found
	 */
	public static ObjectRelationship findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<ObjectRelationship> orderByComparator)
		throws com.liferay.object.exception.NoSuchRelationshipException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first object relationship in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object relationship, or <code>null</code> if a matching object relationship could not be found
	 */
	public static ObjectRelationship fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<ObjectRelationship> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last object relationship in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object relationship
	 * @throws NoSuchRelationshipException if a matching object relationship could not be found
	 */
	public static ObjectRelationship findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<ObjectRelationship> orderByComparator)
		throws com.liferay.object.exception.NoSuchRelationshipException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last object relationship in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object relationship, or <code>null</code> if a matching object relationship could not be found
	 */
	public static ObjectRelationship fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<ObjectRelationship> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the object relationships before and after the current object relationship in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param objectRelationshipId the primary key of the current object relationship
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next object relationship
	 * @throws NoSuchRelationshipException if a object relationship with the primary key could not be found
	 */
	public static ObjectRelationship[] findByUuid_C_PrevAndNext(
			long objectRelationshipId, String uuid, long companyId,
			OrderByComparator<ObjectRelationship> orderByComparator)
		throws com.liferay.object.exception.NoSuchRelationshipException {

		return getPersistence().findByUuid_C_PrevAndNext(
			objectRelationshipId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the object relationships where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of object relationships where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching object relationships
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Caches the object relationship in the entity cache if it is enabled.
	 *
	 * @param objectRelationship the object relationship
	 */
	public static void cacheResult(ObjectRelationship objectRelationship) {
		getPersistence().cacheResult(objectRelationship);
	}

	/**
	 * Caches the object relationships in the entity cache if it is enabled.
	 *
	 * @param objectRelationships the object relationships
	 */
	public static void cacheResult(
		List<ObjectRelationship> objectRelationships) {

		getPersistence().cacheResult(objectRelationships);
	}

	/**
	 * Creates a new object relationship with the primary key. Does not add the object relationship to the database.
	 *
	 * @param objectRelationshipId the primary key for the new object relationship
	 * @return the new object relationship
	 */
	public static ObjectRelationship create(long objectRelationshipId) {
		return getPersistence().create(objectRelationshipId);
	}

	/**
	 * Removes the object relationship with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param objectRelationshipId the primary key of the object relationship
	 * @return the object relationship that was removed
	 * @throws NoSuchRelationshipException if a object relationship with the primary key could not be found
	 */
	public static ObjectRelationship remove(long objectRelationshipId)
		throws com.liferay.object.exception.NoSuchRelationshipException {

		return getPersistence().remove(objectRelationshipId);
	}

	public static ObjectRelationship updateImpl(
		ObjectRelationship objectRelationship) {

		return getPersistence().updateImpl(objectRelationship);
	}

	/**
	 * Returns the object relationship with the primary key or throws a <code>NoSuchRelationshipException</code> if it could not be found.
	 *
	 * @param objectRelationshipId the primary key of the object relationship
	 * @return the object relationship
	 * @throws NoSuchRelationshipException if a object relationship with the primary key could not be found
	 */
	public static ObjectRelationship findByPrimaryKey(long objectRelationshipId)
		throws com.liferay.object.exception.NoSuchRelationshipException {

		return getPersistence().findByPrimaryKey(objectRelationshipId);
	}

	/**
	 * Returns the object relationship with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param objectRelationshipId the primary key of the object relationship
	 * @return the object relationship, or <code>null</code> if a object relationship with the primary key could not be found
	 */
	public static ObjectRelationship fetchByPrimaryKey(
		long objectRelationshipId) {

		return getPersistence().fetchByPrimaryKey(objectRelationshipId);
	}

	/**
	 * Returns all the object relationships.
	 *
	 * @return the object relationships
	 */
	public static List<ObjectRelationship> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the object relationships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of object relationships
	 * @param end the upper bound of the range of object relationships (not inclusive)
	 * @return the range of object relationships
	 */
	public static List<ObjectRelationship> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the object relationships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of object relationships
	 * @param end the upper bound of the range of object relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of object relationships
	 */
	public static List<ObjectRelationship> findAll(
		int start, int end,
		OrderByComparator<ObjectRelationship> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the object relationships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of object relationships
	 * @param end the upper bound of the range of object relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of object relationships
	 */
	public static List<ObjectRelationship> findAll(
		int start, int end,
		OrderByComparator<ObjectRelationship> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the object relationships from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of object relationships.
	 *
	 * @return the number of object relationships
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ObjectRelationshipPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ObjectRelationshipPersistence, ObjectRelationshipPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ObjectRelationshipPersistence.class);

		ServiceTracker
			<ObjectRelationshipPersistence, ObjectRelationshipPersistence>
				serviceTracker =
					new ServiceTracker
						<ObjectRelationshipPersistence,
						 ObjectRelationshipPersistence>(
							 bundle.getBundleContext(),
							 ObjectRelationshipPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}