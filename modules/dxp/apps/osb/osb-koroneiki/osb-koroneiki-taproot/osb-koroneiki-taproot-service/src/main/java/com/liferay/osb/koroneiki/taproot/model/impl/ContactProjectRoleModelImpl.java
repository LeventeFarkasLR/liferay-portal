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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.koroneiki.taproot.model.ContactProjectRole;
import com.liferay.osb.koroneiki.taproot.model.ContactProjectRoleModel;
import com.liferay.osb.koroneiki.taproot.model.ContactProjectRoleSoap;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactProjectRolePK;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the ContactProjectRole service. Represents a row in the &quot;Koroneiki_ContactProjectRole&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>ContactProjectRoleModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ContactProjectRoleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContactProjectRoleImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class ContactProjectRoleModelImpl
	extends BaseModelImpl<ContactProjectRole>
	implements ContactProjectRoleModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a contact project role model instance should use the <code>ContactProjectRole</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_ContactProjectRole";

	public static final Object[][] TABLE_COLUMNS = {
		{"contactId", Types.BIGINT}, {"projectId", Types.BIGINT},
		{"contactRoleId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("contactId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("projectId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("contactRoleId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_ContactProjectRole (contactId LONG not null,projectId LONG not null,contactRoleId LONG not null,primary key (contactId, projectId, contactRoleId))";

	public static final String TABLE_SQL_DROP =
		"drop table Koroneiki_ContactProjectRole";

	public static final String ORDER_BY_JPQL =
		" ORDER BY contactProjectRole.id.contactId ASC, contactProjectRole.id.projectId ASC, contactProjectRole.id.contactRoleId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_ContactProjectRole.contactId ASC, Koroneiki_ContactProjectRole.projectId ASC, Koroneiki_ContactProjectRole.contactRoleId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long CONTACTID_COLUMN_BITMASK = 1L;

	public static final long PROJECTID_COLUMN_BITMASK = 2L;

	public static final long CONTACTROLEID_COLUMN_BITMASK = 4L;

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
	public static ContactProjectRole toModel(ContactProjectRoleSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		ContactProjectRole model = new ContactProjectRoleImpl();

		model.setContactId(soapModel.getContactId());
		model.setProjectId(soapModel.getProjectId());
		model.setContactRoleId(soapModel.getContactRoleId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<ContactProjectRole> toModels(
		ContactProjectRoleSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<ContactProjectRole> models = new ArrayList<ContactProjectRole>(
			soapModels.length);

		for (ContactProjectRoleSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public ContactProjectRoleModelImpl() {
	}

	@Override
	public ContactProjectRolePK getPrimaryKey() {
		return new ContactProjectRolePK(_contactId, _projectId, _contactRoleId);
	}

	@Override
	public void setPrimaryKey(ContactProjectRolePK primaryKey) {
		setContactId(primaryKey.contactId);
		setProjectId(primaryKey.projectId);
		setContactRoleId(primaryKey.contactRoleId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new ContactProjectRolePK(_contactId, _projectId, _contactRoleId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((ContactProjectRolePK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return ContactProjectRole.class;
	}

	@Override
	public String getModelClassName() {
		return ContactProjectRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ContactProjectRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ContactProjectRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ContactProjectRole, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ContactProjectRole)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ContactProjectRole, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ContactProjectRole, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ContactProjectRole)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ContactProjectRole, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ContactProjectRole, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<ContactProjectRole, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ContactProjectRole, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ContactProjectRole, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<ContactProjectRole, Object>>();
		Map<String, BiConsumer<ContactProjectRole, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<ContactProjectRole, ?>>();

		attributeGetterFunctions.put(
			"contactId", ContactProjectRole::getContactId);
		attributeSetterBiConsumers.put(
			"contactId",
			(BiConsumer<ContactProjectRole, Long>)
				ContactProjectRole::setContactId);
		attributeGetterFunctions.put(
			"projectId", ContactProjectRole::getProjectId);
		attributeSetterBiConsumers.put(
			"projectId",
			(BiConsumer<ContactProjectRole, Long>)
				ContactProjectRole::setProjectId);
		attributeGetterFunctions.put(
			"contactRoleId", ContactProjectRole::getContactRoleId);
		attributeSetterBiConsumers.put(
			"contactRoleId",
			(BiConsumer<ContactProjectRole, Long>)
				ContactProjectRole::setContactRoleId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getContactId() {
		return _contactId;
	}

	@Override
	public void setContactId(long contactId) {
		_columnBitmask |= CONTACTID_COLUMN_BITMASK;

		if (!_setOriginalContactId) {
			_setOriginalContactId = true;

			_originalContactId = _contactId;
		}

		_contactId = contactId;
	}

	public long getOriginalContactId() {
		return _originalContactId;
	}

	@JSON
	@Override
	public long getProjectId() {
		return _projectId;
	}

	@Override
	public void setProjectId(long projectId) {
		_columnBitmask |= PROJECTID_COLUMN_BITMASK;

		if (!_setOriginalProjectId) {
			_setOriginalProjectId = true;

			_originalProjectId = _projectId;
		}

		_projectId = projectId;
	}

	public long getOriginalProjectId() {
		return _originalProjectId;
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

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ContactProjectRole toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ContactProjectRole)ProxyUtil.newProxyInstance(
				_classLoader, _escapedModelInterfaces,
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ContactProjectRoleImpl contactProjectRoleImpl =
			new ContactProjectRoleImpl();

		contactProjectRoleImpl.setContactId(getContactId());
		contactProjectRoleImpl.setProjectId(getProjectId());
		contactProjectRoleImpl.setContactRoleId(getContactRoleId());

		contactProjectRoleImpl.resetOriginalValues();

		return contactProjectRoleImpl;
	}

	@Override
	public int compareTo(ContactProjectRole contactProjectRole) {
		ContactProjectRolePK primaryKey = contactProjectRole.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContactProjectRole)) {
			return false;
		}

		ContactProjectRole contactProjectRole = (ContactProjectRole)obj;

		ContactProjectRolePK primaryKey = contactProjectRole.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
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
		ContactProjectRoleModelImpl contactProjectRoleModelImpl = this;

		contactProjectRoleModelImpl._originalContactId =
			contactProjectRoleModelImpl._contactId;

		contactProjectRoleModelImpl._setOriginalContactId = false;

		contactProjectRoleModelImpl._originalProjectId =
			contactProjectRoleModelImpl._projectId;

		contactProjectRoleModelImpl._setOriginalProjectId = false;

		contactProjectRoleModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ContactProjectRole> toCacheModel() {
		ContactProjectRoleCacheModel contactProjectRoleCacheModel =
			new ContactProjectRoleCacheModel();

		contactProjectRoleCacheModel.contactProjectRolePK = getPrimaryKey();

		contactProjectRoleCacheModel.contactId = getContactId();

		contactProjectRoleCacheModel.projectId = getProjectId();

		contactProjectRoleCacheModel.contactRoleId = getContactRoleId();

		return contactProjectRoleCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ContactProjectRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ContactProjectRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ContactProjectRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((ContactProjectRole)this));
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
		Map<String, Function<ContactProjectRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ContactProjectRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ContactProjectRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ContactProjectRole)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader =
		ContactProjectRole.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
		ContactProjectRole.class, ModelWrapper.class
	};
	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _contactId;
	private long _originalContactId;
	private boolean _setOriginalContactId;
	private long _projectId;
	private long _originalProjectId;
	private boolean _setOriginalProjectId;
	private long _contactRoleId;
	private long _columnBitmask;
	private ContactProjectRole _escapedModel;

}