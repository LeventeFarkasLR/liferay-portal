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

package com.liferay.osb.provisioning.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ProductBundleProducts. This utility wraps
 * <code>com.liferay.osb.provisioning.service.impl.ProductBundleProductsLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ProductBundleProductsLocalService
 * @generated
 */
public class ProductBundleProductsLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.provisioning.service.impl.ProductBundleProductsLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.provisioning.model.ProductBundleProducts
			addProductBundleProducts(long productBundleId, String productKey)
		throws Exception {

		return getService().addProductBundleProducts(
			productBundleId, productKey);
	}

	/**
	 * Adds the product bundle products to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductBundleProductsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productBundleProducts the product bundle products
	 * @return the product bundle products that was added
	 */
	public static com.liferay.osb.provisioning.model.ProductBundleProducts
		addProductBundleProducts(
			com.liferay.osb.provisioning.model.ProductBundleProducts
				productBundleProducts) {

		return getService().addProductBundleProducts(productBundleProducts);
	}

	/**
	 * Creates a new product bundle products with the primary key. Does not add the product bundle products to the database.
	 *
	 * @param productBundleProductsPK the primary key for the new product bundle products
	 * @return the new product bundle products
	 */
	public static com.liferay.osb.provisioning.model.ProductBundleProducts
		createProductBundleProducts(
			com.liferay.osb.provisioning.service.persistence.
				ProductBundleProductsPK productBundleProductsPK) {

		return getService().createProductBundleProducts(
			productBundleProductsPK);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.osb.provisioning.model.ProductBundleProducts
			deleteProductBundleProducts(long productBundleId, String productKey)
		throws Exception {

		return getService().deleteProductBundleProducts(
			productBundleId, productKey);
	}

	/**
	 * Deletes the product bundle products from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductBundleProductsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productBundleProducts the product bundle products
	 * @return the product bundle products that was removed
	 */
	public static com.liferay.osb.provisioning.model.ProductBundleProducts
		deleteProductBundleProducts(
			com.liferay.osb.provisioning.model.ProductBundleProducts
				productBundleProducts) {

		return getService().deleteProductBundleProducts(productBundleProducts);
	}

	/**
	 * Deletes the product bundle products with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductBundleProductsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productBundleProductsPK the primary key of the product bundle products
	 * @return the product bundle products that was removed
	 * @throws PortalException if a product bundle products with the primary key could not be found
	 */
	public static com.liferay.osb.provisioning.model.ProductBundleProducts
			deleteProductBundleProducts(
				com.liferay.osb.provisioning.service.persistence.
					ProductBundleProductsPK productBundleProductsPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteProductBundleProducts(
			productBundleProductsPK);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.model.impl.ProductBundleProductsModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.model.impl.ProductBundleProductsModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.osb.provisioning.model.ProductBundleProducts
		fetchProductBundleProducts(
			com.liferay.osb.provisioning.service.persistence.
				ProductBundleProductsPK productBundleProductsPK) {

		return getService().fetchProductBundleProducts(productBundleProductsPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product>
				getProductBundleAssignedProducts(long productBundleId)
			throws Exception {

		return getService().getProductBundleAssignedProducts(productBundleId);
	}

	public static java.util.List
		<com.liferay.osb.provisioning.model.ProductBundleProducts>
			getProductBundleProducts(long productBundleId) {

		return getService().getProductBundleProducts(productBundleId);
	}

	/**
	 * Returns the product bundle products with the primary key.
	 *
	 * @param productBundleProductsPK the primary key of the product bundle products
	 * @return the product bundle products
	 * @throws PortalException if a product bundle products with the primary key could not be found
	 */
	public static com.liferay.osb.provisioning.model.ProductBundleProducts
			getProductBundleProducts(
				com.liferay.osb.provisioning.service.persistence.
					ProductBundleProductsPK productBundleProductsPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getProductBundleProducts(productBundleProductsPK);
	}

	public static java.util.List
		<com.liferay.osb.provisioning.model.ProductBundleProducts>
			getProductBundleProducts(String productKey) {

		return getService().getProductBundleProducts(productKey);
	}

	public static int getProductBundleProductsCount(long productBundleId) {
		return getService().getProductBundleProductsCount(productBundleId);
	}

	public static int getProductBundleProductsCount(String productKey) {
		return getService().getProductBundleProductsCount(productKey);
	}

	/**
	 * Returns a range of all the product bundle productses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.model.impl.ProductBundleProductsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundle productses
	 * @param end the upper bound of the range of product bundle productses (not inclusive)
	 * @return the range of product bundle productses
	 */
	public static java.util.List
		<com.liferay.osb.provisioning.model.ProductBundleProducts>
			getProductBundleProductses(int start, int end) {

		return getService().getProductBundleProductses(start, end);
	}

	/**
	 * Returns the number of product bundle productses.
	 *
	 * @return the number of product bundle productses
	 */
	public static int getProductBundleProductsesCount() {
		return getService().getProductBundleProductsesCount();
	}

	/**
	 * Updates the product bundle products in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductBundleProductsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productBundleProducts the product bundle products
	 * @return the product bundle products that was updated
	 */
	public static com.liferay.osb.provisioning.model.ProductBundleProducts
		updateProductBundleProducts(
			com.liferay.osb.provisioning.model.ProductBundleProducts
				productBundleProducts) {

		return getService().updateProductBundleProducts(productBundleProducts);
	}

	public static ProductBundleProductsLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ProductBundleProductsLocalService, ProductBundleProductsLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ProductBundleProductsLocalService.class);

		ServiceTracker
			<ProductBundleProductsLocalService,
			 ProductBundleProductsLocalService> serviceTracker =
				new ServiceTracker
					<ProductBundleProductsLocalService,
					 ProductBundleProductsLocalService>(
						 bundle.getBundleContext(),
						 ProductBundleProductsLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}