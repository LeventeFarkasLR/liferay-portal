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

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Contact in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContactCacheModel
	implements CacheModel<Contact>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ContactCacheModel)) {
			return false;
		}

		ContactCacheModel contactCacheModel = (ContactCacheModel)object;

		if ((contactId == contactCacheModel.contactId) &&
			(mvccVersion == contactCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, contactId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", contactId=");
		sb.append(contactId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", contactKey=");
		sb.append(contactKey);
		sb.append(", oktaId=");
		sb.append(oktaId);
		sb.append(", firstName=");
		sb.append(firstName);
		sb.append(", middleName=");
		sb.append(middleName);
		sb.append(", lastName=");
		sb.append(lastName);
		sb.append(", emailAddress=");
		sb.append(emailAddress);
		sb.append(", languageId=");
		sb.append(languageId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Contact toEntityModel() {
		ContactImpl contactImpl = new ContactImpl();

		contactImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			contactImpl.setUuid("");
		}
		else {
			contactImpl.setUuid(uuid);
		}

		contactImpl.setContactId(contactId);
		contactImpl.setCompanyId(companyId);
		contactImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			contactImpl.setCreateDate(null);
		}
		else {
			contactImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			contactImpl.setModifiedDate(null);
		}
		else {
			contactImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (contactKey == null) {
			contactImpl.setContactKey("");
		}
		else {
			contactImpl.setContactKey(contactKey);
		}

		if (oktaId == null) {
			contactImpl.setOktaId("");
		}
		else {
			contactImpl.setOktaId(oktaId);
		}

		if (firstName == null) {
			contactImpl.setFirstName("");
		}
		else {
			contactImpl.setFirstName(firstName);
		}

		if (middleName == null) {
			contactImpl.setMiddleName("");
		}
		else {
			contactImpl.setMiddleName(middleName);
		}

		if (lastName == null) {
			contactImpl.setLastName("");
		}
		else {
			contactImpl.setLastName(lastName);
		}

		if (emailAddress == null) {
			contactImpl.setEmailAddress("");
		}
		else {
			contactImpl.setEmailAddress(emailAddress);
		}

		if (languageId == null) {
			contactImpl.setLanguageId("");
		}
		else {
			contactImpl.setLanguageId(languageId);
		}

		contactImpl.resetOriginalValues();

		return contactImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		contactId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		contactKey = objectInput.readUTF();
		oktaId = objectInput.readUTF();
		firstName = objectInput.readUTF();
		middleName = objectInput.readUTF();
		lastName = objectInput.readUTF();
		emailAddress = objectInput.readUTF();
		languageId = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(contactId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (contactKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(contactKey);
		}

		if (oktaId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(oktaId);
		}

		if (firstName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(firstName);
		}

		if (middleName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(middleName);
		}

		if (lastName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastName);
		}

		if (emailAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(emailAddress);
		}

		if (languageId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(languageId);
		}
	}

	public long mvccVersion;
	public String uuid;
	public long contactId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String contactKey;
	public String oktaId;
	public String firstName;
	public String middleName;
	public String lastName;
	public String emailAddress;
	public String languageId;

}