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

package com.liferay.comment.upgrade;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.message.boards.model.MBDiscussion;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.subscription.model.Subscription;
import com.liferay.subscription.service.SubscriptionLocalService;

import java.io.IOException;

import java.sql.SQLException;

import java.util.List;

/**
 * @author Roberto Díaz
 */
public class UpgradeDiscussionSubscriptionClassName extends UpgradeProcess {

	public UpgradeDiscussionSubscriptionClassName(
		AssetEntryLocalService assetEntryLocalService,
		ClassNameLocalService classNameLocalService,
		SubscriptionLocalService subscriptionLocalService,
		String oldSubscriptionClassName, DeletionMode deletionMode) {

		_assetEntryLocalService = assetEntryLocalService;
		_classNameLocalService = classNameLocalService;
		_subscriptionLocalService = subscriptionLocalService;
		_oldSubscriptionClassName = oldSubscriptionClassName;
		_deletionMode = deletionMode;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x)
	 */
	@Deprecated
	public UpgradeDiscussionSubscriptionClassName(
		SubscriptionLocalService subscriptionLocalService,
		String oldSubscriptionClassName, DeletionMode deletionMode) {

		this(
			AssetEntryLocalServiceUtil.getService(),
			ClassNameLocalServiceUtil.getService(), subscriptionLocalService,
			oldSubscriptionClassName, deletionMode);
	}

	public enum DeletionMode {

		/**
		 * @deprecated As of Athanasius (7.3.x), replaced by {@link #UPDATE}
		 */
		@Deprecated
		ADD_NEW,
		DELETE_OLD, UPDATE

	}

	@Override
	protected void doUpgrade() throws Exception {
		if (_deletionMode == DeletionMode.DELETE_OLD) {
			_deleteSubscriptions();
		}
		else {
			_updateSubscriptions();
		}
	}

	private void _deleteSubscriptions() throws PortalException {
		ActionableDynamicQuery actionableDynamicQuery =
			_subscriptionLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> dynamicQuery.add(
				RestrictionsFactoryUtil.eq(
					"classNameId",
					_classNameLocalService.getClassNameId(
						_oldSubscriptionClassName))));
		actionableDynamicQuery.setPerformActionMethod(
			(Subscription subscription) ->
				_subscriptionLocalService.deleteSubscription(
					subscription.getSubscriptionId()));

		actionableDynamicQuery.performActions();
	}

	private void _updateSubscriptions() throws IOException, SQLException {
		String newSubscriptionClassName =
			MBDiscussion.class.getName() + StringPool.UNDERLINE +
				_oldSubscriptionClassName;

		long newClassNameId = ClassNameLocalServiceUtil.getClassNameId(
			newSubscriptionClassName);

		long oldClassNameId = ClassNameLocalServiceUtil.getClassNameId(
			_oldSubscriptionClassName);

		List<Subscription> subscriptions =
			_subscriptionLocalService.getSubscriptions(
				_oldSubscriptionClassName);

		for (Subscription subscription : subscriptions) {
			AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(
				newSubscriptionClassName, subscription.getClassPK());

			if (assetEntry == null) {
				_assetEntryLocalService.updateEntry(
					subscription.getUserId(), subscription.getGroupId(),
					subscription.getCreateDate(),
					subscription.getModifiedDate(), newSubscriptionClassName,
					subscription.getClassPK(), null, 0, null, null, true, false,
					null, null, null, null, null,
					String.valueOf(subscription.getGroupId()), null, null, null,
					null, 0, 0, null);
			}
		}

		if (!subscriptions.isEmpty()) {
			runSQL(
				StringBundler.concat(
					"update Subscription set classNameId = ", newClassNameId,
					" where classNameId = ", oldClassNameId));
		}
	}

	private final AssetEntryLocalService _assetEntryLocalService;
	private final ClassNameLocalService _classNameLocalService;
	private final DeletionMode _deletionMode;
	private final String _oldSubscriptionClassName;
	private final SubscriptionLocalService _subscriptionLocalService;

}