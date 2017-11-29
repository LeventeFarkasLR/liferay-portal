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

package com.liferay.commerce.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.model.CommerceCart;
import com.liferay.commerce.model.CommerceCartModel;
import com.liferay.commerce.model.CommerceCartSoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the CommerceCart service. Represents a row in the &quot;CommerceCart&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CommerceCartModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceCartImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceCartImpl
 * @see CommerceCart
 * @see CommerceCartModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CommerceCartModelImpl extends BaseModelImpl<CommerceCart>
	implements CommerceCartModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce cart model instance should use the {@link CommerceCart} interface instead.
	 */
	public static final String TABLE_NAME = "CommerceCart";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "commerceCartId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "name", Types.VARCHAR },
			{ "type_", Types.INTEGER },
			{ "billingAddressId", Types.BIGINT },
			{ "shippingAddressId", Types.BIGINT },
			{ "commerceShippingMethodId", Types.BIGINT },
			{ "commerceShippingOptionName", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceCartId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("billingAddressId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("shippingAddressId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceShippingMethodId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceShippingOptionName", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table CommerceCart (uuid_ VARCHAR(75) null,commerceCartId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,type_ INTEGER,billingAddressId LONG,shippingAddressId LONG,commerceShippingMethodId LONG,commerceShippingOptionName VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table CommerceCart";
	public static final String ORDER_BY_JPQL = " ORDER BY commerceCart.createDate ASC";
	public static final String ORDER_BY_SQL = " ORDER BY CommerceCart.createDate ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.commerce.model.CommerceCart"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.commerce.model.CommerceCart"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.commerce.model.CommerceCart"),
			true);
	public static final long BILLINGADDRESSID_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long GROUPID_COLUMN_BITMASK = 4L;
	public static final long NAME_COLUMN_BITMASK = 8L;
	public static final long SHIPPINGADDRESSID_COLUMN_BITMASK = 16L;
	public static final long TYPE_COLUMN_BITMASK = 32L;
	public static final long USERID_COLUMN_BITMASK = 64L;
	public static final long UUID_COLUMN_BITMASK = 128L;
	public static final long CREATEDATE_COLUMN_BITMASK = 256L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceCart toModel(CommerceCartSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CommerceCart model = new CommerceCartImpl();

		model.setUuid(soapModel.getUuid());
		model.setCommerceCartId(soapModel.getCommerceCartId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setType(soapModel.getType());
		model.setBillingAddressId(soapModel.getBillingAddressId());
		model.setShippingAddressId(soapModel.getShippingAddressId());
		model.setCommerceShippingMethodId(soapModel.getCommerceShippingMethodId());
		model.setCommerceShippingOptionName(soapModel.getCommerceShippingOptionName());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceCart> toModels(CommerceCartSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<CommerceCart> models = new ArrayList<CommerceCart>(soapModels.length);

		for (CommerceCartSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.commerce.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.commerce.model.CommerceCart"));

	public CommerceCartModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceCartId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceCartId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceCartId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceCart.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceCart.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("commerceCartId", getCommerceCartId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("type", getType());
		attributes.put("billingAddressId", getBillingAddressId());
		attributes.put("shippingAddressId", getShippingAddressId());
		attributes.put("commerceShippingMethodId", getCommerceShippingMethodId());
		attributes.put("commerceShippingOptionName",
			getCommerceShippingOptionName());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long commerceCartId = (Long)attributes.get("commerceCartId");

		if (commerceCartId != null) {
			setCommerceCartId(commerceCartId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Long billingAddressId = (Long)attributes.get("billingAddressId");

		if (billingAddressId != null) {
			setBillingAddressId(billingAddressId);
		}

		Long shippingAddressId = (Long)attributes.get("shippingAddressId");

		if (shippingAddressId != null) {
			setShippingAddressId(shippingAddressId);
		}

		Long commerceShippingMethodId = (Long)attributes.get(
				"commerceShippingMethodId");

		if (commerceShippingMethodId != null) {
			setCommerceShippingMethodId(commerceShippingMethodId);
		}

		String commerceShippingOptionName = (String)attributes.get(
				"commerceShippingOptionName");

		if (commerceShippingOptionName != null) {
			setCommerceShippingOptionName(commerceShippingOptionName);
		}
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getCommerceCartId() {
		return _commerceCartId;
	}

	@Override
	public void setCommerceCartId(long commerceCartId) {
		_commerceCartId = commerceCartId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask |= NAME_COLUMN_BITMASK;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@JSON
	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		_columnBitmask |= TYPE_COLUMN_BITMASK;

		if (!_setOriginalType) {
			_setOriginalType = true;

			_originalType = _type;
		}

		_type = type;
	}

	public int getOriginalType() {
		return _originalType;
	}

	@JSON
	@Override
	public long getBillingAddressId() {
		return _billingAddressId;
	}

	@Override
	public void setBillingAddressId(long billingAddressId) {
		_columnBitmask |= BILLINGADDRESSID_COLUMN_BITMASK;

		if (!_setOriginalBillingAddressId) {
			_setOriginalBillingAddressId = true;

			_originalBillingAddressId = _billingAddressId;
		}

		_billingAddressId = billingAddressId;
	}

	public long getOriginalBillingAddressId() {
		return _originalBillingAddressId;
	}

	@JSON
	@Override
	public long getShippingAddressId() {
		return _shippingAddressId;
	}

	@Override
	public void setShippingAddressId(long shippingAddressId) {
		_columnBitmask |= SHIPPINGADDRESSID_COLUMN_BITMASK;

		if (!_setOriginalShippingAddressId) {
			_setOriginalShippingAddressId = true;

			_originalShippingAddressId = _shippingAddressId;
		}

		_shippingAddressId = shippingAddressId;
	}

	public long getOriginalShippingAddressId() {
		return _originalShippingAddressId;
	}

	@JSON
	@Override
	public long getCommerceShippingMethodId() {
		return _commerceShippingMethodId;
	}

	@Override
	public void setCommerceShippingMethodId(long commerceShippingMethodId) {
		_commerceShippingMethodId = commerceShippingMethodId;
	}

	@JSON
	@Override
	public String getCommerceShippingOptionName() {
		if (_commerceShippingOptionName == null) {
			return "";
		}
		else {
			return _commerceShippingOptionName;
		}
	}

	@Override
	public void setCommerceShippingOptionName(String commerceShippingOptionName) {
		_commerceShippingOptionName = commerceShippingOptionName;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				CommerceCart.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			CommerceCart.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceCart toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CommerceCart)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceCartImpl commerceCartImpl = new CommerceCartImpl();

		commerceCartImpl.setUuid(getUuid());
		commerceCartImpl.setCommerceCartId(getCommerceCartId());
		commerceCartImpl.setGroupId(getGroupId());
		commerceCartImpl.setCompanyId(getCompanyId());
		commerceCartImpl.setUserId(getUserId());
		commerceCartImpl.setUserName(getUserName());
		commerceCartImpl.setCreateDate(getCreateDate());
		commerceCartImpl.setModifiedDate(getModifiedDate());
		commerceCartImpl.setName(getName());
		commerceCartImpl.setType(getType());
		commerceCartImpl.setBillingAddressId(getBillingAddressId());
		commerceCartImpl.setShippingAddressId(getShippingAddressId());
		commerceCartImpl.setCommerceShippingMethodId(getCommerceShippingMethodId());
		commerceCartImpl.setCommerceShippingOptionName(getCommerceShippingOptionName());

		commerceCartImpl.resetOriginalValues();

		return commerceCartImpl;
	}

	@Override
	public int compareTo(CommerceCart commerceCart) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), commerceCart.getCreateDate());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceCart)) {
			return false;
		}

		CommerceCart commerceCart = (CommerceCart)obj;

		long primaryKey = commerceCart.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		CommerceCartModelImpl commerceCartModelImpl = this;

		commerceCartModelImpl._originalUuid = commerceCartModelImpl._uuid;

		commerceCartModelImpl._originalGroupId = commerceCartModelImpl._groupId;

		commerceCartModelImpl._setOriginalGroupId = false;

		commerceCartModelImpl._originalCompanyId = commerceCartModelImpl._companyId;

		commerceCartModelImpl._setOriginalCompanyId = false;

		commerceCartModelImpl._originalUserId = commerceCartModelImpl._userId;

		commerceCartModelImpl._setOriginalUserId = false;

		commerceCartModelImpl._setModifiedDate = false;

		commerceCartModelImpl._originalName = commerceCartModelImpl._name;

		commerceCartModelImpl._originalType = commerceCartModelImpl._type;

		commerceCartModelImpl._setOriginalType = false;

		commerceCartModelImpl._originalBillingAddressId = commerceCartModelImpl._billingAddressId;

		commerceCartModelImpl._setOriginalBillingAddressId = false;

		commerceCartModelImpl._originalShippingAddressId = commerceCartModelImpl._shippingAddressId;

		commerceCartModelImpl._setOriginalShippingAddressId = false;

		commerceCartModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceCart> toCacheModel() {
		CommerceCartCacheModel commerceCartCacheModel = new CommerceCartCacheModel();

		commerceCartCacheModel.uuid = getUuid();

		String uuid = commerceCartCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commerceCartCacheModel.uuid = null;
		}

		commerceCartCacheModel.commerceCartId = getCommerceCartId();

		commerceCartCacheModel.groupId = getGroupId();

		commerceCartCacheModel.companyId = getCompanyId();

		commerceCartCacheModel.userId = getUserId();

		commerceCartCacheModel.userName = getUserName();

		String userName = commerceCartCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceCartCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceCartCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceCartCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceCartCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceCartCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceCartCacheModel.name = getName();

		String name = commerceCartCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			commerceCartCacheModel.name = null;
		}

		commerceCartCacheModel.type = getType();

		commerceCartCacheModel.billingAddressId = getBillingAddressId();

		commerceCartCacheModel.shippingAddressId = getShippingAddressId();

		commerceCartCacheModel.commerceShippingMethodId = getCommerceShippingMethodId();

		commerceCartCacheModel.commerceShippingOptionName = getCommerceShippingOptionName();

		String commerceShippingOptionName = commerceCartCacheModel.commerceShippingOptionName;

		if ((commerceShippingOptionName != null) &&
				(commerceShippingOptionName.length() == 0)) {
			commerceCartCacheModel.commerceShippingOptionName = null;
		}

		return commerceCartCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", commerceCartId=");
		sb.append(getCommerceCartId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", billingAddressId=");
		sb.append(getBillingAddressId());
		sb.append(", shippingAddressId=");
		sb.append(getShippingAddressId());
		sb.append(", commerceShippingMethodId=");
		sb.append(getCommerceShippingMethodId());
		sb.append(", commerceShippingOptionName=");
		sb.append(getCommerceShippingOptionName());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.liferay.commerce.model.CommerceCart");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>commerceCartId</column-name><column-value><![CDATA[");
		sb.append(getCommerceCartId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>billingAddressId</column-name><column-value><![CDATA[");
		sb.append(getBillingAddressId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>shippingAddressId</column-name><column-value><![CDATA[");
		sb.append(getShippingAddressId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>commerceShippingMethodId</column-name><column-value><![CDATA[");
		sb.append(getCommerceShippingMethodId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>commerceShippingOptionName</column-name><column-value><![CDATA[");
		sb.append(getCommerceShippingOptionName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CommerceCart.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CommerceCart.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _commerceCartId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _originalName;
	private int _type;
	private int _originalType;
	private boolean _setOriginalType;
	private long _billingAddressId;
	private long _originalBillingAddressId;
	private boolean _setOriginalBillingAddressId;
	private long _shippingAddressId;
	private long _originalShippingAddressId;
	private boolean _setOriginalShippingAddressId;
	private long _commerceShippingMethodId;
	private String _commerceShippingOptionName;
	private long _columnBitmask;
	private CommerceCart _escapedModel;
}