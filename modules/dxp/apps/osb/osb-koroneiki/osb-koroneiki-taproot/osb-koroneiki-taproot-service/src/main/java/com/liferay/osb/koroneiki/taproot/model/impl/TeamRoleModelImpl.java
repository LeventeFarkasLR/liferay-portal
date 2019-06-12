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
import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.osb.koroneiki.taproot.model.TeamRoleModel;
import com.liferay.osb.koroneiki.taproot.model.TeamRoleSoap;
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

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the TeamRole service. Represents a row in the &quot;Koroneiki_TeamRole&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>TeamRoleModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link TeamRoleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeamRoleImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class TeamRoleModelImpl
	extends BaseModelImpl<TeamRole> implements TeamRoleModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a team role model instance should use the <code>TeamRole</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_TeamRole";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"teamRoleId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR}, {"description", Types.VARCHAR},
		{"type_", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("teamRoleId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_TeamRole (uuid_ VARCHAR(75) null,teamRoleId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,description VARCHAR(75) null,type_ INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table Koroneiki_TeamRole";

	public static final String ORDER_BY_JPQL = " ORDER BY teamRole.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_TeamRole.name ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long NAME_COLUMN_BITMASK = 2L;

	public static final long TYPE_COLUMN_BITMASK = 4L;

	public static final long UUID_COLUMN_BITMASK = 8L;

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
	public static TeamRole toModel(TeamRoleSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		TeamRole model = new TeamRoleImpl();

		model.setUuid(soapModel.getUuid());
		model.setTeamRoleId(soapModel.getTeamRoleId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setType(soapModel.getType());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<TeamRole> toModels(TeamRoleSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<TeamRole> models = new ArrayList<TeamRole>(soapModels.length);

		for (TeamRoleSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public TeamRoleModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _teamRoleId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setTeamRoleId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _teamRoleId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return TeamRole.class;
	}

	@Override
	public String getModelClassName() {
		return TeamRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<TeamRole, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<TeamRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<TeamRole, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((TeamRole)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<TeamRole, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<TeamRole, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(TeamRole)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<TeamRole, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<TeamRole, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, TeamRole>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			TeamRole.class.getClassLoader(), TeamRole.class,
			ModelWrapper.class);

		try {
			Constructor<TeamRole> constructor =
				(Constructor<TeamRole>)proxyClass.getConstructor(
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

	private static final Map<String, Function<TeamRole, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<TeamRole, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<TeamRole, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<TeamRole, Object>>();
		Map<String, BiConsumer<TeamRole, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<TeamRole, ?>>();

		attributeGetterFunctions.put("uuid", TeamRole::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<TeamRole, String>)TeamRole::setUuid);
		attributeGetterFunctions.put("teamRoleId", TeamRole::getTeamRoleId);
		attributeSetterBiConsumers.put(
			"teamRoleId", (BiConsumer<TeamRole, Long>)TeamRole::setTeamRoleId);
		attributeGetterFunctions.put("companyId", TeamRole::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<TeamRole, Long>)TeamRole::setCompanyId);
		attributeGetterFunctions.put("userId", TeamRole::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<TeamRole, Long>)TeamRole::setUserId);
		attributeGetterFunctions.put("createDate", TeamRole::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate", (BiConsumer<TeamRole, Date>)TeamRole::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", TeamRole::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<TeamRole, Date>)TeamRole::setModifiedDate);
		attributeGetterFunctions.put("name", TeamRole::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<TeamRole, String>)TeamRole::setName);
		attributeGetterFunctions.put("description", TeamRole::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<TeamRole, String>)TeamRole::setDescription);
		attributeGetterFunctions.put("type", TeamRole::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<TeamRole, Integer>)TeamRole::setType);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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
	public long getTeamRoleId() {
		return _teamRoleId;
	}

	@Override
	public void setTeamRoleId(long teamRoleId) {
		_teamRoleId = teamRoleId;
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

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(TeamRole.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), TeamRole.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public TeamRole toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, TeamRole>
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
		TeamRoleImpl teamRoleImpl = new TeamRoleImpl();

		teamRoleImpl.setUuid(getUuid());
		teamRoleImpl.setTeamRoleId(getTeamRoleId());
		teamRoleImpl.setCompanyId(getCompanyId());
		teamRoleImpl.setUserId(getUserId());
		teamRoleImpl.setCreateDate(getCreateDate());
		teamRoleImpl.setModifiedDate(getModifiedDate());
		teamRoleImpl.setName(getName());
		teamRoleImpl.setDescription(getDescription());
		teamRoleImpl.setType(getType());

		teamRoleImpl.resetOriginalValues();

		return teamRoleImpl;
	}

	@Override
	public int compareTo(TeamRole teamRole) {
		int value = 0;

		value = getName().compareTo(teamRole.getName());

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

		if (!(obj instanceof TeamRole)) {
			return false;
		}

		TeamRole teamRole = (TeamRole)obj;

		long primaryKey = teamRole.getPrimaryKey();

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
		TeamRoleModelImpl teamRoleModelImpl = this;

		teamRoleModelImpl._originalUuid = teamRoleModelImpl._uuid;

		teamRoleModelImpl._originalCompanyId = teamRoleModelImpl._companyId;

		teamRoleModelImpl._setOriginalCompanyId = false;

		teamRoleModelImpl._setModifiedDate = false;

		teamRoleModelImpl._originalName = teamRoleModelImpl._name;

		teamRoleModelImpl._originalType = teamRoleModelImpl._type;

		teamRoleModelImpl._setOriginalType = false;

		teamRoleModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<TeamRole> toCacheModel() {
		TeamRoleCacheModel teamRoleCacheModel = new TeamRoleCacheModel();

		teamRoleCacheModel.uuid = getUuid();

		String uuid = teamRoleCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			teamRoleCacheModel.uuid = null;
		}

		teamRoleCacheModel.teamRoleId = getTeamRoleId();

		teamRoleCacheModel.companyId = getCompanyId();

		teamRoleCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			teamRoleCacheModel.createDate = createDate.getTime();
		}
		else {
			teamRoleCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			teamRoleCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			teamRoleCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		teamRoleCacheModel.name = getName();

		String name = teamRoleCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			teamRoleCacheModel.name = null;
		}

		teamRoleCacheModel.description = getDescription();

		String description = teamRoleCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			teamRoleCacheModel.description = null;
		}

		teamRoleCacheModel.type = getType();

		return teamRoleCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<TeamRole, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<TeamRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<TeamRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((TeamRole)this));
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
		Map<String, Function<TeamRole, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<TeamRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<TeamRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((TeamRole)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, TeamRole>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private String _uuid;
	private String _originalUuid;
	private long _teamRoleId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _originalName;
	private String _description;
	private int _type;
	private int _originalType;
	private boolean _setOriginalType;
	private long _columnBitmask;
	private TeamRole _escapedModel;

}