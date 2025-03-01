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

package com.liferay.subscription.service.impl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.social.SocialActivityManagerUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.subscription.constants.SubscriptionConstants;
import com.liferay.subscription.model.Subscription;
import com.liferay.subscription.service.base.SubscriptionLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * Provides the local service for accessing, adding, and deleting notification
 * subscriptions to entities. It handles subscriptions to entities found in many
 * different places in the portal, including message boards, blogs, and
 * documents and media.
 *
 * @author Charles May
 * @author Zsolt Berentey
 */
@Component(
	property = "model.class.name=com.liferay.subscription.model.Subscription",
	service = AopService.class
)
public class SubscriptionLocalServiceImpl
	extends SubscriptionLocalServiceBaseImpl {

	/**
	 * Subscribes the user to the entity, notifying him the instant the entity
	 * is created, deleted, or modified.
	 *
	 * <p>
	 * If there is no asset entry with the class name and class PK a new asset
	 * entry is created.
	 * </p>
	 *
	 * <p>
	 * A social activity for the subscription is created using the asset entry
	 * associated with the class name and class PK, or the newly created asset
	 * entry.
	 * </p>
	 *
	 * @param  userId the primary key of the user
	 * @param  groupId the primary key of the entity's group
	 * @param  className the entity's class name
	 * @param  classPK the primary key of the entity's instance
	 * @return the subscription
	 */
	@Override
	public Subscription addSubscription(
			long userId, long groupId, String className, long classPK)
		throws PortalException {

		return addSubscription(
			userId, groupId, className, classPK,
			SubscriptionConstants.FREQUENCY_INSTANT);
	}

	/**
	 * Subscribes the user to the entity, notifying him at the given frequency.
	 *
	 * <p>
	 * If there is no asset entry with the class name and class PK a new asset
	 * entry is created.
	 * </p>
	 *
	 * <p>
	 * A social activity for the subscription is created using the asset entry
	 * associated with the class name and class PK, or the newly created asset
	 * entry.
	 * </p>
	 *
	 * @param  userId the primary key of the user
	 * @param  groupId the primary key of the entity's group
	 * @param  className the entity's class name
	 * @param  classPK the primary key of the entity's instance
	 * @param  frequency the frequency for notifications
	 * @return the subscription
	 */
	@Override
	public Subscription addSubscription(
			long userId, long groupId, String className, long classPK,
			String frequency)
		throws PortalException {

		// Subscription

		User user = userLocalService.getUser(userId);
		long classNameId = classNameLocalService.getClassNameId(className);

		Subscription subscription = subscriptionPersistence.fetchByC_U_C_C(
			user.getCompanyId(), userId, classNameId, classPK);

		if (subscription == null) {
			long subscriptionId = counterLocalService.increment();

			subscription = subscriptionPersistence.create(subscriptionId);

			subscription.setGroupId(groupId);
			subscription.setCompanyId(user.getCompanyId());
			subscription.setUserId(user.getUserId());
			subscription.setUserName(user.getFullName());
			subscription.setClassNameId(classNameId);
			subscription.setClassPK(classPK);
			subscription.setFrequency(frequency);

			subscription = subscriptionPersistence.update(subscription);
		}

		if (groupId > 0) {

			// Social

			AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
				className, classPK);

			if (assetEntry != null) {
				JSONObject extraDataJSONObject = JSONUtil.put(
					"title", assetEntry.getTitle());

				SocialActivityManagerUtil.addActivity(
					userId, assetEntry, SocialActivityConstants.TYPE_SUBSCRIBE,
					extraDataJSONObject.toString(), 0);
			}
		}

		return subscription;
	}

	@Override
	public void deleteGroupSubscriptions(long groupId) {
		subscriptionPersistence.removeByGroupId(groupId);
	}

	/**
	 * Deletes the subscription with the primary key. A social activity with the
	 * unsubscribe action is created.
	 *
	 * @param  subscriptionId the primary key of the subscription
	 * @return the subscription that was removed
	 */
	@Override
	public Subscription deleteSubscription(long subscriptionId)
		throws PortalException {

		Subscription subscription = subscriptionPersistence.findByPrimaryKey(
			subscriptionId);

		return deleteSubscription(subscription);
	}

	/**
	 * Deletes the user's subscription to the entity. A social activity with the
	 * unsubscribe action is created.
	 *
	 * @param userId the primary key of the user
	 * @param className the entity's class name
	 * @param classPK the primary key of the entity's instance
	 */
	@Override
	public void deleteSubscription(long userId, String className, long classPK)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		Subscription subscription = subscriptionPersistence.fetchByC_U_C_C(
			user.getCompanyId(), userId,
			classNameLocalService.getClassNameId(className), classPK);

		if (subscription != null) {
			deleteSubscription(subscription);
		}
	}

	/**
	 * Deletes the subscription. A social activity with the unsubscribe action
	 * is created.
	 *
	 * @param  subscription the subscription
	 * @return the subscription that was removed
	 */
	@Override
	public Subscription deleteSubscription(Subscription subscription)
		throws PortalException {

		// Subscription

		subscriptionPersistence.remove(subscription);

		// Social

		ClassName className = classNameLocalService.getClassName(
			subscription.getClassNameId());

		AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
			className.getClassName(), subscription.getClassPK());

		if (assetEntry != null) {
			JSONObject extraDataJSONObject = JSONUtil.put(
				"title", assetEntry.getTitle());

			SocialActivityManagerUtil.addActivity(
				subscription.getUserId(), subscription,
				SocialActivityConstants.TYPE_UNSUBSCRIBE,
				extraDataJSONObject.toString(), 0);
		}

		return subscription;
	}

	/**
	 * Deletes all the subscriptions of the user.
	 *
	 * @param userId the primary key of the user
	 */
	@Override
	public void deleteSubscriptions(long userId) throws PortalException {
		List<Subscription> subscriptions = subscriptionPersistence.findByUserId(
			userId);

		for (Subscription subscription : subscriptions) {
			deleteSubscription(subscription);
		}
	}

	@Override
	public void deleteSubscriptions(long userId, long groupId)
		throws PortalException {

		List<Subscription> subscriptions = subscriptionPersistence.findByG_U(
			groupId, userId);

		for (Subscription subscription : subscriptions) {
			deleteSubscription(subscription);
		}
	}

	/**
	 * Deletes all the subscriptions to the entity.
	 *
	 * @param companyId the primary key of the company
	 * @param className the entity's class name
	 * @param classPK the primary key of the entity's instance
	 */
	@Override
	public void deleteSubscriptions(
			long companyId, String className, long classPK)
		throws PortalException {

		List<Subscription> subscriptions = subscriptionPersistence.findByC_C_C(
			companyId, classNameLocalService.getClassNameId(className),
			classPK);

		for (Subscription subscription : subscriptions) {
			deleteSubscription(subscription);
		}
	}

	@Override
	public Subscription fetchSubscription(
		long companyId, long userId, String className, long classPK) {

		return subscriptionPersistence.fetchByC_U_C_C(
			companyId, userId, classNameLocalService.getClassNameId(className),
			classPK);
	}

	/**
	 * Returns the subscription of the user to the entity.
	 *
	 * @param  companyId the primary key of the company
	 * @param  userId the primary key of the user
	 * @param  className the entity's class name
	 * @param  classPK the primary key of the entity's instance
	 * @return the subscription of the user to the entity
	 */
	@Override
	public Subscription getSubscription(
			long companyId, long userId, String className, long classPK)
		throws PortalException {

		return subscriptionPersistence.findByC_U_C_C(
			companyId, userId, classNameLocalService.getClassNameId(className),
			classPK);
	}

	/**
	 * Returns all the subscriptions of the user to the entities.
	 *
	 * @param  companyId the primary key of the company
	 * @param  userId the primary key of the user
	 * @param  className the entity's class name
	 * @param  classPKs the primary key of the entities
	 * @return the subscriptions of the user to the entities
	 */
	@Override
	public List<Subscription> getSubscriptions(
		long companyId, long userId, String className, long[] classPKs) {

		return subscriptionPersistence.findByC_U_C_C(
			companyId, userId, classNameLocalService.getClassNameId(className),
			classPKs);
	}

	/**
	 * Returns all the subscriptions to the entity.
	 *
	 * @param  companyId the primary key of the company
	 * @param  className the entity's class name
	 * @param  classPK the primary key of the entity's instance
	 * @return the subscriptions to the entity
	 */
	@Override
	public List<Subscription> getSubscriptions(
		long companyId, String className, long classPK) {

		return subscriptionPersistence.findByC_C_C(
			companyId, classNameLocalService.getClassNameId(className),
			classPK);
	}

	/**
	 * @param      className the entity's class name
	 * @return     the subscriptions to the class name
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public List<Subscription> getSubscriptions(String className) {
		DynamicQuery dynamicQuery = dynamicQuery();

		Property classNameIdProperty = PropertyFactoryUtil.forName(
			"classNameId");

		dynamicQuery.add(
			classNameIdProperty.eq(
				classNameLocalService.getClassNameId(className)));

		return dynamicQuery(dynamicQuery);
	}

	/**
	 * @param      className the entity's class name
	 * @return     the subscriptions to the class name
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public int getSubscriptionsCount(String className) {
		DynamicQuery dynamicQuery = dynamicQuery();

		Property classNameIdProperty = PropertyFactoryUtil.forName(
			"classNameId");

		dynamicQuery.add(
			classNameIdProperty.eq(
				classNameLocalService.getClassNameId(className)));

		Long count = dynamicQueryCount(dynamicQuery);

		return count.intValue();
	}

	/**
	 * Returns an ordered range of all the subscriptions of the user.
	 *
	 * @param  userId the primary key of the user
	 * @param  start the lower bound of the range of results
	 * @param  end the upper bound of the range of results (not inclusive)
	 * @param  orderByComparator the comparator to order the subscriptions
	 * @return the range of subscriptions of the user
	 */
	@Override
	public List<Subscription> getUserSubscriptions(
		long userId, int start, int end,
		OrderByComparator<Subscription> orderByComparator) {

		return subscriptionPersistence.findByUserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns all the subscriptions of the user to the entities with the class
	 * name.
	 *
	 * @param  userId the primary key of the user
	 * @param  className the entity's class name
	 * @return the subscriptions of the user to the entities with the class name
	 */
	@Override
	public List<Subscription> getUserSubscriptions(
		long userId, String className) {

		return subscriptionPersistence.findByU_C(
			userId, classNameLocalService.getClassNameId(className));
	}

	/**
	 * Returns the number of subscriptions of the user.
	 *
	 * @param  userId the primary key of the user
	 * @return the number of subscriptions of the user
	 */
	@Override
	public int getUserSubscriptionsCount(long userId) {
		return subscriptionPersistence.countByUserId(userId);
	}

	/**
	 * Returns <code>true</code> if the user is subscribed to the entity.
	 *
	 * @param  companyId the primary key of the company
	 * @param  userId the primary key of the user
	 * @param  className the entity's class name
	 * @param  classPK the primary key of the entity's instance
	 * @return <code>true</code> if the user is subscribed to the entity;
	 *         <code>false</code> otherwise
	 */
	@Override
	public boolean isSubscribed(
		long companyId, long userId, String className, long classPK) {

		long classNameId = classNameLocalService.getClassNameId(className);

		if (subscriptionPersistence.countByU_C(userId, classNameId) == 0) {
			return false;
		}

		Subscription subscription = subscriptionPersistence.fetchByC_U_C_C(
			companyId, userId, classNameId, classPK);

		if (subscription != null) {
			return true;
		}

		return false;
	}

	/**
	 * Returns <code>true</code> if the user is subscribed to any of the
	 * entities.
	 *
	 * @param  companyId the primary key of the company
	 * @param  userId the primary key of the user
	 * @param  className the entity's class name
	 * @param  classPKs the primary key of the entities
	 * @return <code>true</code> if the user is subscribed to any of the
	 *         entities; <code>false</code> otherwise
	 */
	@Override
	public boolean isSubscribed(
		long companyId, long userId, String className, long[] classPKs) {

		long classNameId = classNameLocalService.getClassNameId(className);

		if (subscriptionPersistence.countByU_C(userId, classNameId) == 0) {
			return false;
		}

		int count = subscriptionPersistence.countByC_U_C_C(
			companyId, userId, classNameId, classPKs);

		if (count > 0) {
			return true;
		}

		return false;
	}

}