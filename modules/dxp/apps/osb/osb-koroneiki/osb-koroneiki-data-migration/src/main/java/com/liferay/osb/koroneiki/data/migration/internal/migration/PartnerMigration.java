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

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Note;
import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.AccountNoteLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactAccountRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactTeamRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamAccountRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamRoleLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = PartnerMigration.class)
public class PartnerMigration {

	public void migrate(long userId) throws Exception {
		ContactRole contactRole = _contactRoleLocalService.getMemberContactRole(
			com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.
				ACCOUNT_CUSTOMER.toString());

		_customerMemberContactRoleId = contactRole.getContactRoleId();

		TeamRole flsTeamRole = _teamRoleLocalService.addTeamRole(
			userId, "First Line Support", StringPool.BLANK,
			com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole.Type.
				ACCOUNT.toString());

		_flsTeamRoleId = flsTeamRole.getTeamRoleId();

		TeamRole partnerTeamRole = _teamRoleLocalService.addTeamRole(
			userId, "Partner", StringPool.BLANK,
			com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole.Type.
				ACCOUNT.toString());

		_partnerTeamRoleId = partnerTeamRole.getTeamRoleId();

		try (Connection connection = DataAccess.getConnection()) {
			_migratePartnerEntries(connection, userId);

			_migratePartnerWorkers(connection, userId);
		}
	}

	private void _assignTeam(
			Connection connection, long partnerEntryId, long teamId)
		throws Exception {

		StringBundler sb = new StringBundler(3);

		sb.append("select corpProjectId, partnerManagedSupport from ");
		sb.append("OSB_AccountEntry where partnerEntryId = ");
		sb.append(partnerEntryId);

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				long corpProjectId = resultSet.getLong(1);

				Account account = _accountLocalService.fetchAccount(
					corpProjectId);

				if (account == null) {
					_log.error(
						"Unable to find account with account id " +
							corpProjectId);

					continue;
				}

				_teamAccountRoleLocalService.addTeamAccountRole(
					teamId, account.getAccountId(), _partnerTeamRoleId);

				boolean partnerManagedSupport = resultSet.getBoolean(2);

				if (partnerManagedSupport) {
					_teamAccountRoleLocalService.addTeamAccountRole(
						teamId, account.getAccountId(), _flsTeamRoleId);
				}
			}
		}
	}

	private Account _getAccount(String dossieraAccountKey)
		throws PortalException {

		List<ExternalLink> externalLinks =
			_externalLinkLocalService.getExternalLinks(
				_classNameLocalService.getClassNameId(Account.class),
				ExternalLinkDomain.DOSSIERA,
				ExternalLinkEntityName.DOSSIERA_ACCOUNT, dossieraAccountKey, 0,
				1);

		if (externalLinks.isEmpty()) {
			return null;
		}

		ExternalLink externalLink = externalLinks.get(0);

		return _accountLocalService.getAccount(externalLink.getClassPK());
	}

	private void _migratePartnerEntries(Connection connection, long userId)
		throws Exception {

		ProductEntry productEntry =
			_productEntryLocalService.fetchProductEntryByName(
				"Service Partnership");

		if (productEntry == null) {
			productEntry = _productEntryLocalService.addProductEntry(
				userId, "Service Partnership", new ArrayList<>());
		}

		StringBundler sb = new StringBundler(2);

		sb.append("select dossieraAccountKey, code_, notes, partnerEntryId ");
		sb.append("from OSB_PartnerEntry");

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String dossieraAccountKey = resultSet.getString(1);

				Account account = _getAccount(dossieraAccountKey);

				if (account == null) {
					_log.error(
						"Unable to find account with dossiera account key " +
							dossieraAccountKey);

					continue;
				}

				_productPurchaseLocalService.addProductPurchase(
					userId, account.getAccountId(),
					productEntry.getProductEntryId(), null, null, null, 1, 0,
					new ArrayList<>());

				_accountLocalService.updateAccount(account);

				_accountNoteLocalService.addAccountNote(
					userId, StringPool.BLANK, StringPool.BLANK,
					account.getAccountId(), Note.Type.GENERAL.toString(), 2,
					resultSet.getString(3), Note.Format.PLAIN.toString(),
					Note.Status.APPROVED.toString());

				String code = resultSet.getString(2) + " FLS";

				Team team = _teamLocalService.addTeam(
					userId, account.getAccountId(), code, false);

				long partnerEntryId = resultSet.getLong(4);

				_assignTeam(connection, partnerEntryId, team.getTeamId());
			}
		}
	}

	private void _migratePartnerWorkers(Connection connection, long userId)
		throws Exception {

		StringBundler sb = new StringBundler(10);

		sb.append("select role, dossieraAccountKey, CUSTOMER_User.uuid_, ");
		sb.append("CUSTOMER_User.firstName, CUSTOMER_User.middleName, ");
		sb.append("CUSTOMER_User.lastName, CUSTOMER_User.emailAddress, ");
		sb.append("CUSTOMER_User.languageId, ");
		sb.append("CUSTOMER_User.emailAddressVerified from OSB_PartnerWorker ");
		sb.append("inner join CUSTOMER_User on CUSTOMER_User.userId = ");
		sb.append("OSB_PartnerWorker.userId inner join OSB_PartnerEntry on ");
		sb.append("OSB_PartnerEntry.partnerEntryId = ");
		sb.append("OSB_PartnerWorker.partnerEntryId where dossieraAccountKey ");
		sb.append("!= ''");

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String dossieraAccountKey = resultSet.getString(2);

				Account account = _getAccount(dossieraAccountKey);

				if (account == null) {
					_log.error(
						"Unable to find account with dossiera account key " +
							dossieraAccountKey);

					continue;
				}

				String contactUuid = resultSet.getString(3);

				Contact contact = _contactLocalService.fetchContactByUuid(
					contactUuid);

				if (contact == null) {
					String contactFirstName = resultSet.getString(4);
					String contactMiddleName = resultSet.getString(5);
					String contactLastName = resultSet.getString(6);
					String contactEmailAddress = resultSet.getString(7);
					String contactLanguageId = resultSet.getString(8);
					boolean emailAddressVerified = resultSet.getBoolean(9);

					contact = _contactLocalService.addContact(
						contactUuid, userId, StringPool.BLANK, contactFirstName,
						contactMiddleName, contactLastName, contactEmailAddress,
						contactLanguageId, emailAddressVerified);
				}

				int role = resultSet.getInt(1);

				Long contactRoleId =
					_roleMigration.getPartnerWorkerContactRoleId(role);

				if (contactRoleId != null) {
					_contactAccountRoleLocalService.addContactAccountRole(
						contact.getContactId(), account.getAccountId(),
						contactRoleId);
				}
				else {
					_log.error(
						"Unable to find contactRoleId with partner role = " +
							role);
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PartnerMigration.class);

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private AccountNoteLocalService _accountNoteLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactAccountRoleLocalService _contactAccountRoleLocalService;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	@Reference
	private ContactTeamRoleLocalService _contactTeamRoleLocalService;

	private long _customerMemberContactRoleId;

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	private long _flsTeamRoleId;
	private long _partnerTeamRoleId;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

	@Reference
	private RoleMigration _roleMigration;

	@Reference
	private TeamAccountRoleLocalService _teamAccountRoleLocalService;

	@Reference
	private TeamLocalService _teamLocalService;

	@Reference
	private TeamRoleLocalService _teamRoleLocalService;

}