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

package com.liferay.commerce.product.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.model.CPOptionModel;
import com.liferay.commerce.product.model.CPOptionSoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * The base model implementation for the CPOption service. Represents a row in the &quot;CPOption&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CPOptionModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CPOptionImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CPOptionImpl
 * @see CPOption
 * @see CPOptionModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CPOptionModelImpl extends BaseModelImpl<CPOption>
	implements CPOptionModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cp option model instance should use the {@link CPOption} interface instead.
	 */
	public static final String TABLE_NAME = "CPOption";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "CPOptionId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "name", Types.VARCHAR },
			{ "title", Types.VARCHAR },
			{ "description", Types.VARCHAR },
			{ "DDMFormFieldTypeName", Types.VARCHAR },
			{ "facetable", Types.BOOLEAN },
			{ "required", Types.BOOLEAN },
			{ "skuContributor", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CPOptionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("DDMFormFieldTypeName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("facetable", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("required", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("skuContributor", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table CPOption (uuid_ VARCHAR(75) null,CPOptionId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,title STRING null,description STRING null,DDMFormFieldTypeName VARCHAR(75) null,facetable BOOLEAN,required BOOLEAN,skuContributor BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table CPOption";
	public static final String ORDER_BY_JPQL = " ORDER BY cpOption.title DESC";
	public static final String ORDER_BY_SQL = " ORDER BY CPOption.title DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.product.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.commerce.product.model.CPOption"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.product.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.commerce.product.model.CPOption"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.product.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.commerce.product.model.CPOption"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long UUID_COLUMN_BITMASK = 4L;
	public static final long TITLE_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CPOption toModel(CPOptionSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CPOption model = new CPOptionImpl();

		model.setUuid(soapModel.getUuid());
		model.setCPOptionId(soapModel.getCPOptionId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setTitle(soapModel.getTitle());
		model.setDescription(soapModel.getDescription());
		model.setDDMFormFieldTypeName(soapModel.getDDMFormFieldTypeName());
		model.setFacetable(soapModel.getFacetable());
		model.setRequired(soapModel.getRequired());
		model.setSkuContributor(soapModel.getSkuContributor());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CPOption> toModels(CPOptionSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<CPOption> models = new ArrayList<CPOption>(soapModels.length);

		for (CPOptionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.commerce.product.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.commerce.product.model.CPOption"));

	public CPOptionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _CPOptionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCPOptionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _CPOptionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CPOption.class;
	}

	@Override
	public String getModelClassName() {
		return CPOption.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("CPOptionId", getCPOptionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("DDMFormFieldTypeName", getDDMFormFieldTypeName());
		attributes.put("facetable", getFacetable());
		attributes.put("required", getRequired());
		attributes.put("skuContributor", getSkuContributor());

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

		Long CPOptionId = (Long)attributes.get("CPOptionId");

		if (CPOptionId != null) {
			setCPOptionId(CPOptionId);
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

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String DDMFormFieldTypeName = (String)attributes.get(
				"DDMFormFieldTypeName");

		if (DDMFormFieldTypeName != null) {
			setDDMFormFieldTypeName(DDMFormFieldTypeName);
		}

		Boolean facetable = (Boolean)attributes.get("facetable");

		if (facetable != null) {
			setFacetable(facetable);
		}

		Boolean required = (Boolean)attributes.get("required");

		if (required != null) {
			setRequired(required);
		}

		Boolean skuContributor = (Boolean)attributes.get("skuContributor");

		if (skuContributor != null) {
			setSkuContributor(skuContributor);
		}
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
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
	public long getCPOptionId() {
		return _CPOptionId;
	}

	@Override
	public void setCPOptionId(long CPOptionId) {
		_CPOptionId = CPOptionId;
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
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
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
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@JSON
	@Override
	public String getTitle() {
		if (_title == null) {
			return StringPool.BLANK;
		}
		else {
			return _title;
		}
	}

	@Override
	public String getTitle(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId);
	}

	@Override
	public String getTitle(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId, useDefault);
	}

	@Override
	public String getTitle(String languageId) {
		return LocalizationUtil.getLocalization(getTitle(), languageId);
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getTitle(), languageId,
			useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return _titleCurrentLanguageId;
	}

	@JSON
	@Override
	public String getTitleCurrentValue() {
		Locale locale = getLocale(_titleCurrentLanguageId);

		return getTitle(locale);
	}

	@Override
	public Map<Locale, String> getTitleMap() {
		return LocalizationUtil.getLocalizationMap(getTitle());
	}

	@Override
	public void setTitle(String title) {
		_columnBitmask = -1L;

		_title = title;
	}

	@Override
	public void setTitle(String title, Locale locale) {
		setTitle(title, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setTitle(String title, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(title)) {
			setTitle(LocalizationUtil.updateLocalization(getTitle(), "Title",
					title, languageId, defaultLanguageId));
		}
		else {
			setTitle(LocalizationUtil.removeLocalization(getTitle(), "Title",
					languageId));
		}
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		_titleCurrentLanguageId = languageId;
	}

	@Override
	public void setTitleMap(Map<Locale, String> titleMap) {
		setTitleMap(titleMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale) {
		if (titleMap == null) {
			return;
		}

		setTitle(LocalizationUtil.updateLocalization(titleMap, getTitle(),
				"Title", LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return StringPool.BLANK;
		}
		else {
			return _description;
		}
	}

	@Override
	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	@Override
	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	@Override
	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getDescription(), languageId,
			useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	@JSON
	@Override
	public String getDescriptionCurrentValue() {
		Locale locale = getLocale(_descriptionCurrentLanguageId);

		return getDescription(locale);
	}

	@Override
	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@Override
	public void setDescription(String description, Locale locale) {
		setDescription(description, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDescription(String description, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(description)) {
			setDescription(LocalizationUtil.updateLocalization(
					getDescription(), "Description", description, languageId,
					defaultLanguageId));
		}
		else {
			setDescription(LocalizationUtil.removeLocalization(
					getDescription(), "Description", languageId));
		}
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		setDescriptionMap(descriptionMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale) {
		if (descriptionMap == null) {
			return;
		}

		setDescription(LocalizationUtil.updateLocalization(descriptionMap,
				getDescription(), "Description",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getDDMFormFieldTypeName() {
		if (_DDMFormFieldTypeName == null) {
			return StringPool.BLANK;
		}
		else {
			return _DDMFormFieldTypeName;
		}
	}

	@Override
	public void setDDMFormFieldTypeName(String DDMFormFieldTypeName) {
		_DDMFormFieldTypeName = DDMFormFieldTypeName;
	}

	@JSON
	@Override
	public boolean getFacetable() {
		return _facetable;
	}

	@JSON
	@Override
	public boolean isFacetable() {
		return _facetable;
	}

	@Override
	public void setFacetable(boolean facetable) {
		_facetable = facetable;
	}

	@JSON
	@Override
	public boolean getRequired() {
		return _required;
	}

	@JSON
	@Override
	public boolean isRequired() {
		return _required;
	}

	@Override
	public void setRequired(boolean required) {
		_required = required;
	}

	@JSON
	@Override
	public boolean getSkuContributor() {
		return _skuContributor;
	}

	@JSON
	@Override
	public boolean isSkuContributor() {
		return _skuContributor;
	}

	@Override
	public void setSkuContributor(boolean skuContributor) {
		_skuContributor = skuContributor;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				CPOption.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			CPOption.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> titleMap = getTitleMap();

		for (Map.Entry<Locale, String> entry : titleMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> descriptionMap = getDescriptionMap();

		for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getTitle();

		if (xml == null) {
			return StringPool.BLANK;
		}

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(CPOption.class.getName(),
				getPrimaryKey(), defaultLocale, availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {
		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String title = getTitle(defaultLocale);

		if (Validator.isNull(title)) {
			setTitle(getTitle(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setTitle(getTitle(defaultLocale), defaultLocale, defaultLocale);
		}

		String description = getDescription(defaultLocale);

		if (Validator.isNull(description)) {
			setDescription(getDescription(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setDescription(getDescription(defaultLocale), defaultLocale,
				defaultLocale);
		}
	}

	@Override
	public CPOption toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CPOption)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CPOptionImpl cpOptionImpl = new CPOptionImpl();

		cpOptionImpl.setUuid(getUuid());
		cpOptionImpl.setCPOptionId(getCPOptionId());
		cpOptionImpl.setGroupId(getGroupId());
		cpOptionImpl.setCompanyId(getCompanyId());
		cpOptionImpl.setUserId(getUserId());
		cpOptionImpl.setUserName(getUserName());
		cpOptionImpl.setCreateDate(getCreateDate());
		cpOptionImpl.setModifiedDate(getModifiedDate());
		cpOptionImpl.setName(getName());
		cpOptionImpl.setTitle(getTitle());
		cpOptionImpl.setDescription(getDescription());
		cpOptionImpl.setDDMFormFieldTypeName(getDDMFormFieldTypeName());
		cpOptionImpl.setFacetable(getFacetable());
		cpOptionImpl.setRequired(getRequired());
		cpOptionImpl.setSkuContributor(getSkuContributor());

		cpOptionImpl.resetOriginalValues();

		return cpOptionImpl;
	}

	@Override
	public int compareTo(CPOption cpOption) {
		int value = 0;

		value = getTitle().compareTo(cpOption.getTitle());

		value = value * -1;

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

		if (!(obj instanceof CPOption)) {
			return false;
		}

		CPOption cpOption = (CPOption)obj;

		long primaryKey = cpOption.getPrimaryKey();

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
		CPOptionModelImpl cpOptionModelImpl = this;

		cpOptionModelImpl._originalUuid = cpOptionModelImpl._uuid;

		cpOptionModelImpl._originalGroupId = cpOptionModelImpl._groupId;

		cpOptionModelImpl._setOriginalGroupId = false;

		cpOptionModelImpl._originalCompanyId = cpOptionModelImpl._companyId;

		cpOptionModelImpl._setOriginalCompanyId = false;

		cpOptionModelImpl._setModifiedDate = false;

		cpOptionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CPOption> toCacheModel() {
		CPOptionCacheModel cpOptionCacheModel = new CPOptionCacheModel();

		cpOptionCacheModel.uuid = getUuid();

		String uuid = cpOptionCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			cpOptionCacheModel.uuid = null;
		}

		cpOptionCacheModel.CPOptionId = getCPOptionId();

		cpOptionCacheModel.groupId = getGroupId();

		cpOptionCacheModel.companyId = getCompanyId();

		cpOptionCacheModel.userId = getUserId();

		cpOptionCacheModel.userName = getUserName();

		String userName = cpOptionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			cpOptionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			cpOptionCacheModel.createDate = createDate.getTime();
		}
		else {
			cpOptionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			cpOptionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			cpOptionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		cpOptionCacheModel.name = getName();

		String name = cpOptionCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			cpOptionCacheModel.name = null;
		}

		cpOptionCacheModel.title = getTitle();

		String title = cpOptionCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			cpOptionCacheModel.title = null;
		}

		cpOptionCacheModel.description = getDescription();

		String description = cpOptionCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			cpOptionCacheModel.description = null;
		}

		cpOptionCacheModel.DDMFormFieldTypeName = getDDMFormFieldTypeName();

		String DDMFormFieldTypeName = cpOptionCacheModel.DDMFormFieldTypeName;

		if ((DDMFormFieldTypeName != null) &&
				(DDMFormFieldTypeName.length() == 0)) {
			cpOptionCacheModel.DDMFormFieldTypeName = null;
		}

		cpOptionCacheModel.facetable = getFacetable();

		cpOptionCacheModel.required = getRequired();

		cpOptionCacheModel.skuContributor = getSkuContributor();

		return cpOptionCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", CPOptionId=");
		sb.append(getCPOptionId());
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
		sb.append(", title=");
		sb.append(getTitle());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", DDMFormFieldTypeName=");
		sb.append(getDDMFormFieldTypeName());
		sb.append(", facetable=");
		sb.append(getFacetable());
		sb.append(", required=");
		sb.append(getRequired());
		sb.append(", skuContributor=");
		sb.append(getSkuContributor());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(49);

		sb.append("<model><model-name>");
		sb.append("com.liferay.commerce.product.model.CPOption");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>CPOptionId</column-name><column-value><![CDATA[");
		sb.append(getCPOptionId());
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
			"<column><column-name>title</column-name><column-value><![CDATA[");
		sb.append(getTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>DDMFormFieldTypeName</column-name><column-value><![CDATA[");
		sb.append(getDDMFormFieldTypeName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>facetable</column-name><column-value><![CDATA[");
		sb.append(getFacetable());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>required</column-name><column-value><![CDATA[");
		sb.append(getRequired());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>skuContributor</column-name><column-value><![CDATA[");
		sb.append(getSkuContributor());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CPOption.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CPOption.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _CPOptionId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _title;
	private String _titleCurrentLanguageId;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private String _DDMFormFieldTypeName;
	private boolean _facetable;
	private boolean _required;
	private boolean _skuContributor;
	private long _columnBitmask;
	private CPOption _escapedModel;
}