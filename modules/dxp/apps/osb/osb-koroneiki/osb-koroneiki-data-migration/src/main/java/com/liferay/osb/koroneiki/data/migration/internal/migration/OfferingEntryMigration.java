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

package com.liferay.osb.koroneiki.data.migration.internal.migration;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchase.Status;
import com.liferay.osb.koroneiki.taproot.constants.WorkflowConstants;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = OfferingEntryMigration.class)
public class OfferingEntryMigration {

	public void migrate(long userId) throws Exception {
		ProductEntry productEntry =
			_productEntryLocalService.getProductEntryByName(_NAME_GOLD);

		_goldProductEntryId = productEntry.getProductEntryId();

		productEntry = _productEntryLocalService.getProductEntryByName(
			_NAME_LIMITED);

		_limitedProductEntryId = productEntry.getProductEntryId();

		productEntry = _productEntryLocalService.getProductEntryByName(
			_NAME_PLATINUM);

		_platinumProductEntryId = productEntry.getProductEntryId();

		StringBundler sb = new StringBundler(11);

		sb.append("select corpProjectId, OSB_ProductEntry.name, ");
		sb.append("supportResponseId, startDate, supportEndDate, quantity, ");
		sb.append("OSB_OfferingEntry.status, licenses, sizing, version from ");
		sb.append("OSB_OfferingEntry inner join OSB_AccountEntry on ");
		sb.append("OSB_OfferingEntry.accountEntryId = ");
		sb.append("OSB_AccountEntry.accountEntryId inner join ");
		sb.append("OSB_ProductEntry on OSB_OfferingEntry.productEntryId = ");
		sb.append("OSB_ProductEntry.productEntryId inner join OSB_OrderEntry ");
		sb.append("on OSB_OfferingEntry.orderEntryId = ");
		sb.append("OSB_OrderEntry.orderEntryId where OSB_AccountEntry.status ");
		sb.append("!= 500");

		try (Connection connection = DataAccess.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			ResultSetMetaData metaData = resultSet.getMetaData();

			Map<Long, ProductPurchase> accountSLASubscriptions =
				new HashMap<>();

			while (resultSet.next()) {
				long corpProjectId = resultSet.getLong(1);

				Account account = _accountLocalService.fetchAccount(
					corpProjectId);

				if (account == null) {
					_log.error(
						"Unable to find account with accountId " +
							corpProjectId);

					continue;
				}

				String productEntryName = resultSet.getString(2);
				Date startDate = resultSet.getDate(4);
				Date endDate = resultSet.getDate(5);
				int quantity = resultSet.getInt(6);

				int status = resultSet.getInt(7);

				if (status != 2) {
					status = WorkflowConstants.STATUS_APPROVED;
				}
				else {
					status = WorkflowConstants.STATUS_CANCELLED;
				}

				List<ProductField> productFields = new ArrayList<>();

				for (int i = 8; i < 11; i++) {
					ProductField productField =
						_productFieldLocalService.createProductField(0);

					String value = resultSet.getString(i);

					if (Validator.isNull(value)) {
						continue;
					}

					String name = metaData.getColumnName(i);

					if (name.equals("version") && !value.equals("0")) {
						value = _liferayVersionMap.get(value);
					}

					productField.setName(name);

					productField.setValue(value);

					productFields.add(productField);
				}

				if (_isImported(
						corpProjectId, productEntryName, startDate, endDate,
						quantity, status, productFields)) {

					continue;
				}

				long supportResponseId = resultSet.getLong(3);

				_updateSLASubscription(
					accountSLASubscriptions, account.getAccountId(),
					supportResponseId, startDate, endDate);

				try {
					ProductEntry curProductEntry =
						_productEntryLocalService.getProductEntryByName(
							productEntryName);

					_productPurchaseLocalService.addProductPurchase(
						userId, account.getAccountId(),
						curProductEntry.getProductEntryId(), startDate, endDate,
						endDate, quantity, status, productFields);
				}
				catch (Exception exception) {
					_log.error(exception, exception);
				}
			}

			for (Map.Entry<Long, ProductPurchase> entry :
					accountSLASubscriptions.entrySet()) {

				long accountId = entry.getKey();
				ProductPurchase productPurchase = entry.getValue();

				try {
					_productPurchaseLocalService.addProductPurchase(
						userId, accountId, productPurchase.getProductEntryId(),
						productPurchase.getStartDate(),
						productPurchase.getEndDate(),
						productPurchase.getOriginalEndDate(),
						productPurchase.getQuantity(),
						productPurchase.getStatus(), Collections.emptyList());
				}
				catch (Exception exception) {
					_log.error(exception, exception);
				}
			}
		}
	}

	private long _getProductEntryId(long supportResponseId) {
		if (supportResponseId == _SUPPORT_RESPONSE_PLATINUM_ID) {
			return _platinumProductEntryId;
		}
		else if (supportResponseId == _SUPPORT_RESPONSE_GOLD_ID) {
			return _goldProductEntryId;
		}

		return _limitedProductEntryId;
	}

	private boolean _isHigherSLA(long productEntryId, long newProductEntryId) {
		if (newProductEntryId == _platinumProductEntryId) {
			if (productEntryId != _platinumProductEntryId) {
				return true;
			}
		}
		else if (newProductEntryId == _goldProductEntryId) {
			if ((productEntryId != _platinumProductEntryId) &&
				(productEntryId != _goldProductEntryId)) {

				return true;
			}
		}

		return false;
	}

	private boolean _isImported(
		long corpProjectId, String productEntryName, Date startDate,
		Date endDate, int quantity, int status,
		List<ProductField> productFields) {

		StringBundler sb = new StringBundler();

		sb.append(corpProjectId);
		sb.append(productEntryName);
		sb.append(startDate.getTime());
		sb.append(endDate.getTime());
		sb.append(quantity);
		sb.append(status);

		for (ProductField productField : productFields) {
			sb.append(productField.getName());
			sb.append(productField.getValue());
		}

		String key = sb.toString();

		if (_importedOfferingEntries.contains(key)) {
			return true;
		}

		_importedOfferingEntries.add(key);

		return false;
	}

	private void _updateSLASubscription(
		Map<Long, ProductPurchase> accountSLASubscriptions, long accountId,
		long supportResponseId, Date startDate, Date endDate) {

		if (supportResponseId == _SUPPORT_RESPONSE_FLOATING_ID) {
			return;
		}

		ProductPurchase productPurchase = accountSLASubscriptions.get(
			accountId);

		if (productPurchase != null) {
			long productEntryId = _getProductEntryId(supportResponseId);

			if (_isHigherSLA(
					productPurchase.getProductEntryId(), supportResponseId)) {

				productPurchase.setProductEntryId(
					_getProductEntryId(supportResponseId));
				productPurchase.setStartDate(startDate);
				productPurchase.setEndDate(endDate);
				productPurchase.setOriginalEndDate(endDate);
			}
			else if (productEntryId == productPurchase.getProductEntryId()) {
				if (startDate.before(productPurchase.getStartDate())) {
					productPurchase.setStartDate(startDate);
				}

				if (endDate.after(productPurchase.getEndDate())) {
					productPurchase.setEndDate(endDate);
					productPurchase.setOriginalEndDate(endDate);
				}
			}
		}
		else {
			productPurchase =
				_productPurchaseLocalService.createProductPurchase(0);

			productPurchase.setProductEntryId(
				_getProductEntryId(supportResponseId));
			productPurchase.setStartDate(startDate);
			productPurchase.setEndDate(endDate);
			productPurchase.setOriginalEndDate(endDate);
			productPurchase.setQuantity(1);
			productPurchase.setStatus(
				WorkflowConstants.getLabelStatus(Status.APPROVED.toString()));

			accountSLASubscriptions.put(accountId, productPurchase);
		}
	}

	private static final String _NAME_GOLD = "Gold Subscription";

	private static final String _NAME_LIMITED = "Limited Subscription";

	private static final String _NAME_PLATINUM = "Platinum Subscription";

	private static final long _SUPPORT_RESPONSE_FLOATING_ID = 91578207;

	private static final long _SUPPORT_RESPONSE_GOLD_ID = 77672072;

	private static final long _SUPPORT_RESPONSE_PLATINUM_ID = 77672068;

	private static final Log _log = LogFactoryUtil.getLog(
		OfferingEntryMigration.class);

	private static final Set<String> _importedOfferingEntries = new HashSet<>();
	private static final Map<String, String> _liferayVersionMap =
		new HashMap<String, String>() {
			{
				put("21000", "5");
				put("21001", "6");
				put("22002", "6.0");
				put("22003", "6.1");
				put("22004", "6.2");
				put("42000", "7");
			}
		};

	@Reference
	private AccountLocalService _accountLocalService;

	private long _goldProductEntryId;
	private long _limitedProductEntryId;
	private long _platinumProductEntryId;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductFieldLocalService _productFieldLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

}