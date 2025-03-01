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

package com.liferay.account.rest.resource.v1_0.test;

import com.liferay.account.constants.AccountConstants;
import com.liferay.account.model.AccountEntry;
import com.liferay.account.model.AccountEntryModel;
import com.liferay.account.rest.client.dto.v1_0.Account;
import com.liferay.account.service.AccountEntryLocalService;
import com.liferay.account.service.AccountEntryOrganizationRelLocalService;
import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.test.rule.DataGuard;
import com.liferay.portal.kernel.test.util.OrganizationTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Drew Brokke
 */
@DataGuard(scope = DataGuard.Scope.METHOD)
@RunWith(Arquillian.class)
public class AccountResourceTest extends BaseAccountResourceTestCase {

	@After
	@Override
	public void tearDown() throws Exception {
	}

	@Override
	@Test
	public void testDeleteOrganizationAccounts() throws Exception {
		List<AccountEntry> accountEntries = Arrays.asList(
			_addAccountEntry(), _addAccountEntry(), _addAccountEntry());

		Organization organization = OrganizationTestUtil.addOrganization();

		for (AccountEntry accountEntry : accountEntries) {
			_accountEntryOrganizationRelLocalService.
				addAccountEntryOrganizationRel(
					accountEntry.getAccountEntryId(),
					organization.getOrganizationId());
		}

		Assert.assertEquals(
			3,
			_accountEntryOrganizationRelLocalService.
				getAccountEntryOrganizationRelsByOrganizationIdCount(
					organization.getOrganizationId()));

		Long[] accountEntryIds = ListUtil.toArray(
			accountEntries.subList(1, accountEntries.size()),
			AccountEntry.ACCOUNT_ENTRY_ID_ACCESSOR);

		accountResource.deleteOrganizationAccounts(
			organization.getOrganizationId(), accountEntryIds);

		Assert.assertEquals(
			1,
			_accountEntryOrganizationRelLocalService.
				getAccountEntryOrganizationRelsByOrganizationIdCount(
					organization.getOrganizationId()));

		AccountEntry accountEntry = accountEntries.get(0);

		Assert.assertTrue(
			_accountEntryOrganizationRelLocalService.
				hasAccountEntryOrganizationRel(
					accountEntry.getAccountEntryId(),
					organization.getOrganizationId()));
	}

	@Override
	@Test
	public void testDeleteOrganizationAccountsByExternalReferenceCode()
		throws Exception {

		List<AccountEntry> accountEntries = Arrays.asList(
			_addAccountEntry(), _addAccountEntry(), _addAccountEntry());

		Organization organization = OrganizationTestUtil.addOrganization();

		for (AccountEntry accountEntry : accountEntries) {
			_accountEntryOrganizationRelLocalService.
				addAccountEntryOrganizationRel(
					accountEntry.getAccountEntryId(),
					organization.getOrganizationId());
		}

		Assert.assertEquals(
			3,
			_accountEntryOrganizationRelLocalService.
				getAccountEntryOrganizationRelsByOrganizationIdCount(
					organization.getOrganizationId()));

		String[] externalReferenceCodes = TransformUtil.transformToArray(
			accountEntries.subList(1, accountEntries.size()),
			AccountEntryModel::getExternalReferenceCode, String.class);

		accountResource.deleteOrganizationAccountsByExternalReferenceCode(
			organization.getOrganizationId(), externalReferenceCodes);

		Assert.assertEquals(
			1,
			_accountEntryOrganizationRelLocalService.
				getAccountEntryOrganizationRelsByOrganizationIdCount(
					organization.getOrganizationId()));

		AccountEntry accountEntry = accountEntries.get(0);

		Assert.assertTrue(
			_accountEntryOrganizationRelLocalService.
				hasAccountEntryOrganizationRel(
					accountEntry.getAccountEntryId(),
					organization.getOrganizationId()));
	}

	@Override
	@Test
	public void testPatchOrganizationMoveAccounts() throws Exception {
		List<AccountEntry> accountEntries = Arrays.asList(
			_addAccountEntry(), _addAccountEntry(), _addAccountEntry());

		Organization organization1 = OrganizationTestUtil.addOrganization();

		for (AccountEntry accountEntry : accountEntries) {
			_accountEntryOrganizationRelLocalService.
				addAccountEntryOrganizationRel(
					accountEntry.getAccountEntryId(),
					organization1.getOrganizationId());
		}

		Assert.assertEquals(
			3,
			_accountEntryOrganizationRelLocalService.
				getAccountEntryOrganizationRelsByOrganizationIdCount(
					organization1.getOrganizationId()));

		Organization organization2 = OrganizationTestUtil.addOrganization();

		Assert.assertEquals(
			0,
			_accountEntryOrganizationRelLocalService.
				getAccountEntryOrganizationRelsByOrganizationIdCount(
					organization2.getOrganizationId()));

		accountResource.patchOrganizationMoveAccounts(
			organization1.getOrganizationId(),
			organization2.getOrganizationId(),
			ListUtil.toArray(
				accountEntries, AccountEntry.ACCOUNT_ENTRY_ID_ACCESSOR));

		Assert.assertEquals(
			0,
			_accountEntryOrganizationRelLocalService.
				getAccountEntryOrganizationRelsByOrganizationIdCount(
					organization1.getOrganizationId()));

		Assert.assertEquals(
			3,
			_accountEntryOrganizationRelLocalService.
				getAccountEntryOrganizationRelsByOrganizationIdCount(
					organization2.getOrganizationId()));
	}

	@Override
	@Test
	public void testPatchOrganizationMoveAccountsByExternalReferenceCode()
		throws Exception {

		List<AccountEntry> accountEntries = Arrays.asList(
			_addAccountEntry(), _addAccountEntry(), _addAccountEntry());

		Organization organization1 = OrganizationTestUtil.addOrganization();

		for (AccountEntry accountEntry : accountEntries) {
			_accountEntryOrganizationRelLocalService.
				addAccountEntryOrganizationRel(
					accountEntry.getAccountEntryId(),
					organization1.getOrganizationId());
		}

		Assert.assertEquals(
			3,
			_accountEntryOrganizationRelLocalService.
				getAccountEntryOrganizationRelsByOrganizationIdCount(
					organization1.getOrganizationId()));

		Organization organization2 = OrganizationTestUtil.addOrganization();

		Assert.assertEquals(
			0,
			_accountEntryOrganizationRelLocalService.
				getAccountEntryOrganizationRelsByOrganizationIdCount(
					organization2.getOrganizationId()));

		String[] externalReferenceCodes = TransformUtil.transformToArray(
			accountEntries, AccountEntryModel::getExternalReferenceCode,
			String.class);

		accountResource.patchOrganizationMoveAccountsByExternalReferenceCode(
			organization1.getOrganizationId(),
			organization2.getOrganizationId(), externalReferenceCodes);

		Assert.assertEquals(
			0,
			_accountEntryOrganizationRelLocalService.
				getAccountEntryOrganizationRelsByOrganizationIdCount(
					organization1.getOrganizationId()));

		Assert.assertEquals(
			3,
			_accountEntryOrganizationRelLocalService.
				getAccountEntryOrganizationRelsByOrganizationIdCount(
					organization2.getOrganizationId()));
	}

	@Override
	@Test
	public void testPostOrganizationAccounts() throws Exception {
		Organization organization = OrganizationTestUtil.addOrganization();

		Assert.assertEquals(
			0,
			_accountEntryOrganizationRelLocalService.
				getAccountEntryOrganizationRelsByOrganizationIdCount(
					organization.getOrganizationId()));

		List<AccountEntry> accountEntries = Arrays.asList(
			_addAccountEntry(), _addAccountEntry(), _addAccountEntry());

		Long[] accountEntryIds = ListUtil.toArray(
			accountEntries, AccountEntry.ACCOUNT_ENTRY_ID_ACCESSOR);

		accountResource.postOrganizationAccounts(
			organization.getOrganizationId(), accountEntryIds);

		for (Long accountEntryId : accountEntryIds) {
			Assert.assertTrue(
				_accountEntryOrganizationRelLocalService.
					hasAccountEntryOrganizationRel(
						accountEntryId, organization.getOrganizationId()));
		}
	}

	@Override
	@Test
	public void testPostOrganizationAccountsByExternalReferenceCode()
		throws Exception {

		Organization organization = OrganizationTestUtil.addOrganization();

		Assert.assertEquals(
			0,
			_accountEntryOrganizationRelLocalService.
				getAccountEntryOrganizationRelsByOrganizationIdCount(
					organization.getOrganizationId()));

		List<AccountEntry> accountEntries = Arrays.asList(
			_addAccountEntry(), _addAccountEntry(), _addAccountEntry());

		String[] externalReferenceCodes = TransformUtil.transformToArray(
			accountEntries, AccountEntryModel::getExternalReferenceCode,
			String.class);

		accountResource.postOrganizationAccountsByExternalReferenceCode(
			organization.getOrganizationId(), externalReferenceCodes);

		for (AccountEntry accountEntry : accountEntries) {
			Assert.assertTrue(
				_accountEntryOrganizationRelLocalService.
					hasAccountEntryOrganizationRel(
						accountEntry.getAccountEntryId(),
						organization.getOrganizationId()));
		}
	}

	@Override
	protected String[] getAdditionalAssertFieldNames() {
		return new String[] {"name"};
	}

	@Override
	protected Account randomAccount() throws Exception {
		Account account = super.randomAccount();

		account.setParentAccountId(AccountConstants.ACCOUNT_ENTRY_ID_DEFAULT);
		account.setStatus(WorkflowConstants.STATUS_APPROVED);

		return account;
	}

	@Override
	protected Account testDeleteAccount_addAccount() throws Exception {
		return _postAccount();
	}

	@Override
	protected Account testDeleteAccountByExternalReferenceCode_addAccount()
		throws Exception {

		return _postAccount();
	}

	@Override
	protected Account testGetAccount_addAccount() throws Exception {
		return _postAccount();
	}

	@Override
	protected Account testGetAccountByExternalReferenceCode_addAccount()
		throws Exception {

		return _postAccount();
	}

	@Override
	protected Account testGetAccountsPage_addAccount(Account account)
		throws Exception {

		return _postAccount(account);
	}

	@Override
	protected Account testGraphQLAccount_addAccount() throws Exception {
		return _postAccount();
	}

	@Override
	protected Account testPatchAccount_addAccount() throws Exception {
		return _postAccount();
	}

	@Override
	protected Account testPatchAccountByExternalReferenceCode_addAccount()
		throws Exception {

		return _postAccount();
	}

	@Override
	protected Account testPostAccount_addAccount(Account account)
		throws Exception {

		return _postAccount(account);
	}

	@Override
	protected Account testPutAccount_addAccount() throws Exception {
		return _postAccount();
	}

	@Override
	protected Account testPutAccountByExternalReferenceCode_addAccount()
		throws Exception {

		return _postAccount();
	}

	private AccountEntry _addAccountEntry() throws Exception {
		AccountEntry accountEntry = _accountEntryLocalService.addAccountEntry(
			TestPropsValues.getUserId(),
			AccountConstants.ACCOUNT_ENTRY_ID_DEFAULT,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(), null,
			null, null, null, AccountConstants.ACCOUNT_ENTRY_TYPE_BUSINESS,
			WorkflowConstants.STATUS_APPROVED,
			ServiceContextTestUtil.getServiceContext());

		accountEntry.setExternalReferenceCode(RandomTestUtil.randomString());

		return _accountEntryLocalService.updateAccountEntry(accountEntry);
	}

	private Account _postAccount() throws Exception {
		return _postAccount(randomAccount());
	}

	private Account _postAccount(Account account) throws Exception {
		return accountResource.postAccount(account);
	}

	@Inject
	private AccountEntryLocalService _accountEntryLocalService;

	@Inject
	private AccountEntryOrganizationRelLocalService
		_accountEntryOrganizationRelLocalService;

}