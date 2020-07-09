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

package com.liferay.osb.provisioning.service.persistence;

import com.liferay.osb.provisioning.model.ProductBundleProducts;
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
 * The persistence utility for the product bundle products service. This utility wraps <code>com.liferay.osb.provisioning.service.persistence.impl.ProductBundleProductsPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductBundleProductsPersistence
 * @generated
 */
public class ProductBundleProductsUtil {

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
	public static void clearCache(ProductBundleProducts productBundleProducts) {
		getPersistence().clearCache(productBundleProducts);
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
	public static Map<Serializable, ProductBundleProducts> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ProductBundleProducts> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProductBundleProducts> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProductBundleProducts> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProductBundleProducts> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProductBundleProducts update(
		ProductBundleProducts productBundleProducts) {

		return getPersistence().update(productBundleProducts);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProductBundleProducts update(
		ProductBundleProducts productBundleProducts,
		ServiceContext serviceContext) {

		return getPersistence().update(productBundleProducts, serviceContext);
	}

	/**
	 * Returns the product bundle products where productBundleId = &#63; or throws a <code>NoSuchProductBundleProductsException</code> if it could not be found.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the matching product bundle products
	 * @throws NoSuchProductBundleProductsException if a matching product bundle products could not be found
	 */
	public static ProductBundleProducts findByProductBundleId(
			long productBundleId)
		throws com.liferay.osb.provisioning.exception.
			NoSuchProductBundleProductsException {

		return getPersistence().findByProductBundleId(productBundleId);
	}

	/**
	 * Returns the product bundle products where productBundleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the matching product bundle products, or <code>null</code> if a matching product bundle products could not be found
	 */
	public static ProductBundleProducts fetchByProductBundleId(
		long productBundleId) {

		return getPersistence().fetchByProductBundleId(productBundleId);
	}

	/**
	 * Returns the product bundle products where productBundleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productBundleId the product bundle ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching product bundle products, or <code>null</code> if a matching product bundle products could not be found
	 */
	public static ProductBundleProducts fetchByProductBundleId(
		long productBundleId, boolean useFinderCache) {

		return getPersistence().fetchByProductBundleId(
			productBundleId, useFinderCache);
	}

	/**
	 * Removes the product bundle products where productBundleId = &#63; from the database.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the product bundle products that was removed
	 */
	public static ProductBundleProducts removeByProductBundleId(
			long productBundleId)
		throws com.liferay.osb.provisioning.exception.
			NoSuchProductBundleProductsException {

		return getPersistence().removeByProductBundleId(productBundleId);
	}

	/**
	 * Returns the number of product bundle productses where productBundleId = &#63;.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the number of matching product bundle productses
	 */
	public static int countByProductBundleId(long productBundleId) {
		return getPersistence().countByProductBundleId(productBundleId);
	}

	/**
	 * Caches the product bundle products in the entity cache if it is enabled.
	 *
	 * @param productBundleProducts the product bundle products
	 */
	public static void cacheResult(
		ProductBundleProducts productBundleProducts) {

		getPersistence().cacheResult(productBundleProducts);
	}

	/**
	 * Caches the product bundle productses in the entity cache if it is enabled.
	 *
	 * @param productBundleProductses the product bundle productses
	 */
	public static void cacheResult(
		List<ProductBundleProducts> productBundleProductses) {

		getPersistence().cacheResult(productBundleProductses);
	}

	/**
	 * Creates a new product bundle products with the primary key. Does not add the product bundle products to the database.
	 *
	 * @param productBundleProductsPK the primary key for the new product bundle products
	 * @return the new product bundle products
	 */
	public static ProductBundleProducts create(
		ProductBundleProductsPK productBundleProductsPK) {

		return getPersistence().create(productBundleProductsPK);
	}

	/**
	 * Removes the product bundle products with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productBundleProductsPK the primary key of the product bundle products
	 * @return the product bundle products that was removed
	 * @throws NoSuchProductBundleProductsException if a product bundle products with the primary key could not be found
	 */
	public static ProductBundleProducts remove(
			ProductBundleProductsPK productBundleProductsPK)
		throws com.liferay.osb.provisioning.exception.
			NoSuchProductBundleProductsException {

		return getPersistence().remove(productBundleProductsPK);
	}

	public static ProductBundleProducts updateImpl(
		ProductBundleProducts productBundleProducts) {

		return getPersistence().updateImpl(productBundleProducts);
	}

	/**
	 * Returns the product bundle products with the primary key or throws a <code>NoSuchProductBundleProductsException</code> if it could not be found.
	 *
	 * @param productBundleProductsPK the primary key of the product bundle products
	 * @return the product bundle products
	 * @throws NoSuchProductBundleProductsException if a product bundle products with the primary key could not be found
	 */
	public static ProductBundleProducts findByPrimaryKey(
			ProductBundleProductsPK productBundleProductsPK)
		throws com.liferay.osb.provisioning.exception.
			NoSuchProductBundleProductsException {

		return getPersistence().findByPrimaryKey(productBundleProductsPK);
	}

	/**
	 * Returns the product bundle products with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productBundleProductsPK the primary key of the product bundle products
	 * @return the product bundle products, or <code>null</code> if a product bundle products with the primary key could not be found
	 */
	public static ProductBundleProducts fetchByPrimaryKey(
		ProductBundleProductsPK productBundleProductsPK) {

		return getPersistence().fetchByPrimaryKey(productBundleProductsPK);
	}

	/**
	 * Returns all the product bundle productses.
	 *
	 * @return the product bundle productses
	 */
	public static List<ProductBundleProducts> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the product bundle productses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleProductsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundle productses
	 * @param end the upper bound of the range of product bundle productses (not inclusive)
	 * @return the range of product bundle productses
	 */
	public static List<ProductBundleProducts> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the product bundle productses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleProductsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundle productses
	 * @param end the upper bound of the range of product bundle productses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of product bundle productses
	 */
	public static List<ProductBundleProducts> findAll(
		int start, int end,
		OrderByComparator<ProductBundleProducts> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product bundle productses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleProductsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundle productses
	 * @param end the upper bound of the range of product bundle productses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of product bundle productses
	 */
	public static List<ProductBundleProducts> findAll(
		int start, int end,
		OrderByComparator<ProductBundleProducts> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the product bundle productses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of product bundle productses.
	 *
	 * @return the number of product bundle productses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static ProductBundleProductsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ProductBundleProductsPersistence, ProductBundleProductsPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ProductBundleProductsPersistence.class);

		ServiceTracker
			<ProductBundleProductsPersistence, ProductBundleProductsPersistence>
				serviceTracker =
					new ServiceTracker
						<ProductBundleProductsPersistence,
						 ProductBundleProductsPersistence>(
							 bundle.getBundleContext(),
							 ProductBundleProductsPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}