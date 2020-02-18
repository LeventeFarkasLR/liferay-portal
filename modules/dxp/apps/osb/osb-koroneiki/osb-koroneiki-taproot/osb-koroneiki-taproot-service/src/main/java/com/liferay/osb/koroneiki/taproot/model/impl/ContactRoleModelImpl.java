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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.ContactRoleModel;
import com.liferay.osb.koroneiki.taproot.model.ContactRoleSoap;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the ContactRole service. Represents a row in the &quot;Koroneiki_ContactRole&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>ContactRoleModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ContactRoleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContactRoleImpl
 * @generated
 */
@JSON(strict = true)
public class ContactRoleModelImpl
	extends BaseModelImpl<ContactRole> implements ContactRoleModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a contact role model instance should use the <code>ContactRole</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_ContactRole";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"contactRoleId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"contactRoleKey", Types.VARCHAR},
		{"name", Types.VARCHAR}, {"description", Types.VARCHAR},
		{"type_", Types.VARCHAR}, {"system_", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("contactRoleId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("contactRoleKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("system_", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_ContactRole (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,contactRoleId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,contactRoleKey VARCHAR(75) null,name VARCHAR(75) null,description STRING null,type_ VARCHAR(75) null,system_ BOOLEAN)";

	public static final String TABLE_SQL_DROP =
		"drop table Koroneiki_ContactRole";

	public static final String ORDER_BY_JPQL = " ORDER BY contactRole.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_ContactRole.name ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long CONTACTROLEKEY_COLUMN_BITMASK = 2L;

	public static final long NAME_COLUMN_BITMASK = 4L;

	public static final long TYPE_COLUMN_BITMASK = 8L;

	public static final long UUID_COLUMN_BITMASK = 16L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static ContactRole toModel(ContactRoleSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		ContactRole model = new ContactRoleImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setUuid(soapModel.getUuid());
		model.setContactRoleId(soapModel.getContactRoleId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setContactRoleKey(soapModel.getContactRoleKey());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setType(soapModel.getType());
		model.setSystem(soapModel.isSystem());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<ContactRole> toModels(ContactRoleSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<ContactRole> models = new ArrayList<ContactRole>(
			soapModels.length);

		for (ContactRoleSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public ContactRoleModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _contactRoleId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setContactRoleId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _contactRoleId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ContactRole.class;
	}

	@Override
	public String getModelClassName() {
		return ContactRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ContactRole, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ContactRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ContactRole, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ContactRole)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ContactRole, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ContactRole, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ContactRole)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ContactRole, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ContactRole, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, ContactRole>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			ContactRole.class.getClassLoader(), ContactRole.class,
			ModelWrapper.class);

		try {
			Constructor<ContactRole> constructor =
				(Constructor<ContactRole>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<ContactRole, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ContactRole, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ContactRole, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<ContactRole, Object>>();
		Map<String, BiConsumer<ContactRole, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ContactRole, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", ContactRole::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<ContactRole, Long>)ContactRole::setMvccVersion);
		attributeGetterFunctions.put("uuid", ContactRole::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<ContactRole, String>)ContactRole::setUuid);
		attributeGetterFunctions.put(
			"contactRoleId", ContactRole::getContactRoleId);
		attributeSetterBiConsumers.put(
			"contactRoleId",
			(BiConsumer<ContactRole, Long>)ContactRole::setContactRoleId);
		attributeGetterFunctions.put("companyId", ContactRole::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<ContactRole, Long>)ContactRole::setCompanyId);
		attributeGetterFunctions.put("userId", ContactRole::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<ContactRole, Long>)ContactRole::setUserId);
		attributeGetterFunctions.put("createDate", ContactRole::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<ContactRole, Date>)ContactRole::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", ContactRole::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<ContactRole, Date>)ContactRole::setModifiedDate);
		attributeGetterFunctions.put(
			"contactRoleKey", ContactRole::getContactRoleKey);
		attributeSetterBiConsumers.put(
			"contactRoleKey",
			(BiConsumer<ContactRole, String>)ContactRole::setContactRoleKey);
		attributeGetterFunctions.put("name", ContactRole::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<ContactRole, String>)ContactRole::setName);
		attributeGetterFunctions.put(
			"description", ContactRole::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<ContactRole, String>)ContactRole::setDescription);
		attributeGetterFunctions.put("type", ContactRole::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<ContactRole, String>)ContactRole::setType);
		attributeGetterFunctions.put("system", ContactRole::getSystem);
		attributeSetterBiConsumers.put(
			"system", (BiConsumer<ContactRole, Boolean>)ContactRole::setSystem);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
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
		_columnBitmask |= UUID_COLUMN_BITMASK;

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
	public long getContactRoleId() {
		return _contactRoleId;
	}

	@Override
	public void setContactRoleId(long contactRoleId) {
		_contactRoleId = contactRoleId;
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
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
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
	public String getContactRoleKey() {
		if (_contactRoleKey == null) {
			return "";
		}
		else {
			return _contactRoleKey;
		}
	}

	@Override
	public void setContactRoleKey(String contactRoleKey) {
		_columnBitmask |= CONTACTROLEKEY_COLUMN_BITMASK;

		if (_originalContactRoleKey == null) {
			_originalContactRoleKey = _contactRoleKey;
		}

		_contactRoleKey = contactRoleKey;
	}

	public String getOriginalContactRoleKey() {
		return GetterUtil.getString(_originalContactRoleKey);
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
		_columnBitmask = -1L;

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
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@JSON
	@Override
	public String getType() {
		if (_type == null) {
			return "";
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		_columnBitmask |= TYPE_COLUMN_BITMASK;

		if (_originalType == null) {
			_originalType = _type;
		}

		_type = type;
	}

	public String getOriginalType() {
		return GetterUtil.getString(_originalType);
	}

	@JSON
	@Override
	public boolean getSystem() {
		return _system;
	}

	@JSON
	@Override
	public boolean isSystem() {
		return _system;
	}

	@Override
	public void setSystem(boolean system) {
		_system = system;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(ContactRole.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), ContactRole.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ContactRole toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ContactRole>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ContactRoleImpl contactRoleImpl = new ContactRoleImpl();

		contactRoleImpl.setMvccVersion(getMvccVersion());
		contactRoleImpl.setUuid(getUuid());
		contactRoleImpl.setContactRoleId(getContactRoleId());
		contactRoleImpl.setCompanyId(getCompanyId());
		contactRoleImpl.setUserId(getUserId());
		contactRoleImpl.setCreateDate(getCreateDate());
		contactRoleImpl.setModifiedDate(getModifiedDate());
		contactRoleImpl.setContactRoleKey(getContactRoleKey());
		contactRoleImpl.setName(getName());
		contactRoleImpl.setDescription(getDescription());
		contactRoleImpl.setType(getType());
		contactRoleImpl.setSystem(isSystem());

		contactRoleImpl.resetOriginalValues();

		return contactRoleImpl;
	}

	@Override
	public int compareTo(ContactRole contactRole) {
		int value = 0;

		value = getName().compareTo(contactRole.getName());

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

		if (!(obj instanceof ContactRole)) {
			return false;
		}

		ContactRole contactRole = (ContactRole)obj;

		long primaryKey = contactRole.getPrimaryKey();

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
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public void resetOriginalValues() {
		ContactRoleModelImpl contactRoleModelImpl = this;

		contactRoleModelImpl._originalUuid = contactRoleModelImpl._uuid;

		contactRoleModelImpl._originalCompanyId =
			contactRoleModelImpl._companyId;

		contactRoleModelImpl._setOriginalCompanyId = false;

		contactRoleModelImpl._setModifiedDate = false;

		contactRoleModelImpl._originalContactRoleKey =
			contactRoleModelImpl._contactRoleKey;

		contactRoleModelImpl._originalName = contactRoleModelImpl._name;

		contactRoleModelImpl._originalType = contactRoleModelImpl._type;

		contactRoleModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ContactRole> toCacheModel() {
		ContactRoleCacheModel contactRoleCacheModel =
			new ContactRoleCacheModel();

		contactRoleCacheModel.mvccVersion = getMvccVersion();

		contactRoleCacheModel.uuid = getUuid();

		String uuid = contactRoleCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			contactRoleCacheModel.uuid = null;
		}

		contactRoleCacheModel.contactRoleId = getContactRoleId();

		contactRoleCacheModel.companyId = getCompanyId();

		contactRoleCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			contactRoleCacheModel.createDate = createDate.getTime();
		}
		else {
			contactRoleCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			contactRoleCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			contactRoleCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		contactRoleCacheModel.contactRoleKey = getContactRoleKey();

		String contactRoleKey = contactRoleCacheModel.contactRoleKey;

		if ((contactRoleKey != null) && (contactRoleKey.length() == 0)) {
			contactRoleCacheModel.contactRoleKey = null;
		}

		contactRoleCacheModel.name = getName();

		String name = contactRoleCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			contactRoleCacheModel.name = null;
		}

		contactRoleCacheModel.description = getDescription();

		String description = contactRoleCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			contactRoleCacheModel.description = null;
		}

		contactRoleCacheModel.type = getType();

		String type = contactRoleCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			contactRoleCacheModel.type = null;
		}

		contactRoleCacheModel.system = isSystem();

		return contactRoleCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ContactRole, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ContactRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ContactRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((ContactRole)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<ContactRole, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ContactRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ContactRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ContactRole)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, ContactRole>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private long _contactRoleId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _contactRoleKey;
	private String _originalContactRoleKey;
	private String _name;
	private String _originalName;
	private String _description;
	private String _type;
	private String _originalType;
	private boolean _system;
	private long _columnBitmask;
	private ContactRole _escapedModel;

}