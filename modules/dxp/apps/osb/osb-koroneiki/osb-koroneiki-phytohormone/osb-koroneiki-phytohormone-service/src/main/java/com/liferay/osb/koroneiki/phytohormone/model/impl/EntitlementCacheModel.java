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

package com.liferay.osb.koroneiki.phytohormone.model.impl;

import com.liferay.osb.koroneiki.phytohormone.model.Entitlement;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Entitlement in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EntitlementCacheModel
	implements CacheModel<Entitlement>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EntitlementCacheModel)) {
			return false;
		}

		EntitlementCacheModel entitlementCacheModel =
			(EntitlementCacheModel)obj;

		if (entitlementId == entitlementCacheModel.entitlementId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, entitlementId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{entitlementId=");
		sb.append(entitlementId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", entitlementDefinitionId=");
		sb.append(entitlementDefinitionId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Entitlement toEntityModel() {
		EntitlementImpl entitlementImpl = new EntitlementImpl();

		entitlementImpl.setEntitlementId(entitlementId);
		entitlementImpl.setCompanyId(companyId);
		entitlementImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			entitlementImpl.setCreateDate(null);
		}
		else {
			entitlementImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			entitlementImpl.setModifiedDate(null);
		}
		else {
			entitlementImpl.setModifiedDate(new Date(modifiedDate));
		}

		entitlementImpl.setEntitlementDefinitionId(entitlementDefinitionId);
		entitlementImpl.setClassNameId(classNameId);
		entitlementImpl.setClassPK(classPK);

		if (name == null) {
			entitlementImpl.setName("");
		}
		else {
			entitlementImpl.setName(name);
		}

		entitlementImpl.resetOriginalValues();

		return entitlementImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		entitlementId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		entitlementDefinitionId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(entitlementId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(entitlementDefinitionId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long entitlementId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long entitlementDefinitionId;
	public long classNameId;
	public long classPK;
	public String name;

}