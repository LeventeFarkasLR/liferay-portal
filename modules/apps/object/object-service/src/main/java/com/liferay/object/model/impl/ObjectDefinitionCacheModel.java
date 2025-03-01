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

package com.liferay.object.model.impl;

import com.liferay.object.model.ObjectDefinition;
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
 * The cache model class for representing ObjectDefinition in entity cache.
 *
 * @author Marco Leo
 * @generated
 */
public class ObjectDefinitionCacheModel
	implements CacheModel<ObjectDefinition>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ObjectDefinitionCacheModel)) {
			return false;
		}

		ObjectDefinitionCacheModel objectDefinitionCacheModel =
			(ObjectDefinitionCacheModel)object;

		if ((objectDefinitionId ==
				objectDefinitionCacheModel.objectDefinitionId) &&
			(mvccVersion == objectDefinitionCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, objectDefinitionId);

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
		sb.append(", objectDefinitionId=");
		sb.append(objectDefinitionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", dbTableName=");
		sb.append(dbTableName);
		sb.append(", name=");
		sb.append(name);
		sb.append(", pkObjectFieldDBColumnName=");
		sb.append(pkObjectFieldDBColumnName);
		sb.append(", pkObjectFieldName=");
		sb.append(pkObjectFieldName);
		sb.append(", system=");
		sb.append(system);
		sb.append(", version=");
		sb.append(version);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ObjectDefinition toEntityModel() {
		ObjectDefinitionImpl objectDefinitionImpl = new ObjectDefinitionImpl();

		objectDefinitionImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			objectDefinitionImpl.setUuid("");
		}
		else {
			objectDefinitionImpl.setUuid(uuid);
		}

		objectDefinitionImpl.setObjectDefinitionId(objectDefinitionId);
		objectDefinitionImpl.setCompanyId(companyId);
		objectDefinitionImpl.setUserId(userId);

		if (userName == null) {
			objectDefinitionImpl.setUserName("");
		}
		else {
			objectDefinitionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			objectDefinitionImpl.setCreateDate(null);
		}
		else {
			objectDefinitionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			objectDefinitionImpl.setModifiedDate(null);
		}
		else {
			objectDefinitionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (dbTableName == null) {
			objectDefinitionImpl.setDBTableName("");
		}
		else {
			objectDefinitionImpl.setDBTableName(dbTableName);
		}

		if (name == null) {
			objectDefinitionImpl.setName("");
		}
		else {
			objectDefinitionImpl.setName(name);
		}

		if (pkObjectFieldDBColumnName == null) {
			objectDefinitionImpl.setPKObjectFieldDBColumnName("");
		}
		else {
			objectDefinitionImpl.setPKObjectFieldDBColumnName(
				pkObjectFieldDBColumnName);
		}

		if (pkObjectFieldName == null) {
			objectDefinitionImpl.setPKObjectFieldName("");
		}
		else {
			objectDefinitionImpl.setPKObjectFieldName(pkObjectFieldName);
		}

		objectDefinitionImpl.setSystem(system);
		objectDefinitionImpl.setVersion(version);

		objectDefinitionImpl.resetOriginalValues();

		return objectDefinitionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		objectDefinitionId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		dbTableName = objectInput.readUTF();
		name = objectInput.readUTF();
		pkObjectFieldDBColumnName = objectInput.readUTF();
		pkObjectFieldName = objectInput.readUTF();

		system = objectInput.readBoolean();

		version = objectInput.readInt();
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

		objectOutput.writeLong(objectDefinitionId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (dbTableName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dbTableName);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (pkObjectFieldDBColumnName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(pkObjectFieldDBColumnName);
		}

		if (pkObjectFieldName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(pkObjectFieldName);
		}

		objectOutput.writeBoolean(system);

		objectOutput.writeInt(version);
	}

	public long mvccVersion;
	public String uuid;
	public long objectDefinitionId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String dbTableName;
	public String name;
	public String pkObjectFieldDBColumnName;
	public String pkObjectFieldName;
	public boolean system;
	public int version;

}