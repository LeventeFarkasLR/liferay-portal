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

package com.liferay.osb.distributed.messaging.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for QueuedMessage. This utility wraps
 * <code>com.liferay.osb.distributed.messaging.service.impl.QueuedMessageLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see QueuedMessageLocalService
 * @generated
 */
public class QueuedMessageLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.distributed.messaging.service.impl.QueuedMessageLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the queued message to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect QueuedMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param queuedMessage the queued message
	 * @return the queued message that was added
	 */
	public static com.liferay.osb.distributed.messaging.model.QueuedMessage
		addQueuedMessage(
			com.liferay.osb.distributed.messaging.model.QueuedMessage
				queuedMessage) {

		return getService().addQueuedMessage(queuedMessage);
	}

	public static com.liferay.osb.distributed.messaging.model.QueuedMessage
			addQueuedMessage(
				String messageBrokerClassName, String topic,
				com.liferay.osb.distributed.messaging.Message message)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addQueuedMessage(
			messageBrokerClassName, topic, message);
	}

	/**
	 * Creates a new queued message with the primary key. Does not add the queued message to the database.
	 *
	 * @param queuedMessageId the primary key for the new queued message
	 * @return the new queued message
	 */
	public static com.liferay.osb.distributed.messaging.model.QueuedMessage
		createQueuedMessage(long queuedMessageId) {

		return getService().createQueuedMessage(queuedMessageId);
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

	/**
	 * Deletes the queued message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect QueuedMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param queuedMessageId the primary key of the queued message
	 * @return the queued message that was removed
	 * @throws PortalException if a queued message with the primary key could not be found
	 */
	public static com.liferay.osb.distributed.messaging.model.QueuedMessage
			deleteQueuedMessage(long queuedMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteQueuedMessage(queuedMessageId);
	}

	/**
	 * Deletes the queued message from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect QueuedMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param queuedMessage the queued message
	 * @return the queued message that was removed
	 */
	public static com.liferay.osb.distributed.messaging.model.QueuedMessage
		deleteQueuedMessage(
			com.liferay.osb.distributed.messaging.model.QueuedMessage
				queuedMessage) {

		return getService().deleteQueuedMessage(queuedMessage);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.distributed.messaging.model.impl.QueuedMessageModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.distributed.messaging.model.impl.QueuedMessageModelImpl</code>.
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

	public static com.liferay.osb.distributed.messaging.model.QueuedMessage
		fetchQueuedMessage(long queuedMessageId) {

		return getService().fetchQueuedMessage(queuedMessageId);
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

	public static com.liferay.osb.distributed.messaging.model.
		QueuedMessageMessageObjectBlobModel getMessageObjectBlobModel(
			java.io.Serializable primaryKey) {

		return getService().getMessageObjectBlobModel(primaryKey);
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

	/**
	 * Returns the queued message with the primary key.
	 *
	 * @param queuedMessageId the primary key of the queued message
	 * @return the queued message
	 * @throws PortalException if a queued message with the primary key could not be found
	 */
	public static com.liferay.osb.distributed.messaging.model.QueuedMessage
			getQueuedMessage(long queuedMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getQueuedMessage(queuedMessageId);
	}

	/**
	 * Returns a range of all the queued messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.distributed.messaging.model.impl.QueuedMessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of queued messages
	 * @param end the upper bound of the range of queued messages (not inclusive)
	 * @return the range of queued messages
	 */
	public static java.util.List
		<com.liferay.osb.distributed.messaging.model.QueuedMessage>
			getQueuedMessages(int start, int end) {

		return getService().getQueuedMessages(start, end);
	}

	public static java.util.List
		<com.liferay.osb.distributed.messaging.model.QueuedMessage>
			getQueuedMessages(String messageBrokerClassName) {

		return getService().getQueuedMessages(messageBrokerClassName);
	}

	/**
	 * Returns the number of queued messages.
	 *
	 * @return the number of queued messages
	 */
	public static int getQueuedMessagesCount() {
		return getService().getQueuedMessagesCount();
	}

	public static java.io.InputStream openMessageObjectInputStream(
		long queuedMessageId) {

		return getService().openMessageObjectInputStream(queuedMessageId);
	}

	/**
	 * Updates the queued message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect QueuedMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param queuedMessage the queued message
	 * @return the queued message that was updated
	 */
	public static com.liferay.osb.distributed.messaging.model.QueuedMessage
		updateQueuedMessage(
			com.liferay.osb.distributed.messaging.model.QueuedMessage
				queuedMessage) {

		return getService().updateQueuedMessage(queuedMessage);
	}

	public static QueuedMessageLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<QueuedMessageLocalService, QueuedMessageLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			QueuedMessageLocalService.class);

		ServiceTracker<QueuedMessageLocalService, QueuedMessageLocalService>
			serviceTracker =
				new ServiceTracker
					<QueuedMessageLocalService, QueuedMessageLocalService>(
						bundle.getBundleContext(),
						QueuedMessageLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}