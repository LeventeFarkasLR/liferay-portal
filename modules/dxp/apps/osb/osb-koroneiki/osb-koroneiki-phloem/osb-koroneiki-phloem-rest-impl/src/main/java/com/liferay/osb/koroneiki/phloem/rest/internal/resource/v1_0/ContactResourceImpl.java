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

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.ContactUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactResource;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactService;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/contact.properties",
	scope = ServiceScope.PROTOTYPE, service = ContactResource.class
)
public class ContactResourceImpl extends BaseContactResourceImpl {

	@Override
	public void deleteContactByOkta(String oktaId) throws Exception {
		com.liferay.osb.koroneiki.taproot.model.Contact contact =
			_contactLocalService.getContactByOktaId(oktaId);

		_contactService.deleteContact(contact.getContactId());
	}

	@Override
	public void deleteContactByUuid(String uuid) throws Exception {
		com.liferay.osb.koroneiki.taproot.model.Contact contact =
			_contactLocalService.getContactByUuid(uuid);

		_contactService.deleteContact(contact.getContactId());
	}

	@Override
	public Page<Contact> getAccountAccountKeyContactsPage(
			String accountKey, String[] includes, Pagination pagination)
		throws Exception {

		Map<String, Object> includesContext = new HashMap<String, Object>() {
			{
				put("accountKey", accountKey);
				put("includes", includes);
			}
		};

		return Page.of(
			transform(
				_contactService.getAccountContacts(
					accountKey, pagination.getStartPosition(),
					pagination.getEndPosition()),
				contact -> ContactUtil.toContact(contact, includesContext)),
			pagination, _contactService.getAccountContactsCount(accountKey));
	}

	@Override
	public Contact getContactByOkta(String oktaId) throws Exception {
		return ContactUtil.toContact(
			_contactService.getContactByOktaId(oktaId), null);
	}

	@Override
	public Contact getContactByUuid(String uuid) throws Exception {
		return ContactUtil.toContact(
			_contactService.getContactByUuid(uuid), null);
	}

	@Override
	public Page<Contact> getProjectProjectKeyContactsPage(
			String projectKey, Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_contactService.getProjectContacts(
					projectKey, pagination.getStartPosition(),
					pagination.getEndPosition()),
				contact -> ContactUtil.toContact(contact, null)),
			pagination, _contactService.getProjectContactsCount(projectKey));
	}

	@Override
	public Contact postContact(Contact contact) throws Exception {
		return ContactUtil.toContact(
			_contactService.addContact(
				contact.getUuid(), contact.getOktaId(), contact.getFirstName(),
				contact.getMiddleName(), contact.getLastName(),
				contact.getEmailAddress(), contact.getLanguageId()),
			null);
	}

	@Override
	public Contact putContactByOkta(String oktaId, Contact contact)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Contact curContact =
			_contactLocalService.getContactByOktaId(oktaId);

		String middleName = contact.getMiddleName();

		if (Validator.isNull(middleName)) {
			middleName = curContact.getMiddleName();
		}

		String languageId = contact.getLanguageId();

		if (Validator.isNull(languageId)) {
			languageId = curContact.getLanguageId();
		}

		return ContactUtil.toContact(
			_contactService.updateContact(
				curContact.getContactId(), curContact.getUuid(),
				curContact.getOktaId(), contact.getFirstName(), middleName,
				contact.getLastName(), contact.getEmailAddress(), languageId),
			null);
	}

	@Override
	public Contact putContactByUuid(String uuid, Contact contact)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Contact curContact =
			_contactLocalService.getContactByUuid(uuid);

		String middleName = contact.getMiddleName();

		if (Validator.isNull(middleName)) {
			middleName = curContact.getMiddleName();
		}

		String languageId = contact.getLanguageId();

		if (Validator.isNull(languageId)) {
			languageId = curContact.getLanguageId();
		}

		return ContactUtil.toContact(
			_contactService.updateContact(
				curContact.getContactId(), curContact.getUuid(),
				curContact.getOktaId(), contact.getFirstName(), middleName,
				contact.getLastName(), contact.getEmailAddress(), languageId),
			null);
	}

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ContactService _contactService;

}