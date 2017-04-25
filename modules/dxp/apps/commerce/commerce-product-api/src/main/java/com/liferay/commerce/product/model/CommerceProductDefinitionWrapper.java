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

package com.liferay.commerce.product.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link CommerceProductDefinition}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceProductDefinition
 * @generated
 */
@ProviderType
public class CommerceProductDefinitionWrapper
	implements CommerceProductDefinition,
		ModelWrapper<CommerceProductDefinition> {
	public CommerceProductDefinitionWrapper(
		CommerceProductDefinition commerceProductDefinition) {
		_commerceProductDefinition = commerceProductDefinition;
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceProductDefinition.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceProductDefinition.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("commerceProductDefinitionId",
			getCommerceProductDefinitionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("productTypeName", getProductTypeName());
		attributes.put("availableIndividually", getAvailableIndividually());
		attributes.put("DDMStructureKey", getDDMStructureKey());
		attributes.put("baseSKU", getBaseSKU());
		attributes.put("displayDate", getDisplayDate());
		attributes.put("expirationDate", getExpirationDate());
		attributes.put("lastPublishDate", getLastPublishDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("defaultLanguageId", getDefaultLanguageId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long commerceProductDefinitionId = (Long)attributes.get(
				"commerceProductDefinitionId");

		if (commerceProductDefinitionId != null) {
			setCommerceProductDefinitionId(commerceProductDefinitionId);
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

		String productTypeName = (String)attributes.get("productTypeName");

		if (productTypeName != null) {
			setProductTypeName(productTypeName);
		}

		Boolean availableIndividually = (Boolean)attributes.get(
				"availableIndividually");

		if (availableIndividually != null) {
			setAvailableIndividually(availableIndividually);
		}

		String DDMStructureKey = (String)attributes.get("DDMStructureKey");

		if (DDMStructureKey != null) {
			setDDMStructureKey(DDMStructureKey);
		}

		String baseSKU = (String)attributes.get("baseSKU");

		if (baseSKU != null) {
			setBaseSKU(baseSKU);
		}

		Date displayDate = (Date)attributes.get("displayDate");

		if (displayDate != null) {
			setDisplayDate(displayDate);
		}

		Date expirationDate = (Date)attributes.get("expirationDate");

		if (expirationDate != null) {
			setExpirationDate(expirationDate);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}

		String defaultLanguageId = (String)attributes.get("defaultLanguageId");

		if (defaultLanguageId != null) {
			setDefaultLanguageId(defaultLanguageId);
		}
	}

	@Override
	public CommerceProductDefinition toEscapedModel() {
		return new CommerceProductDefinitionWrapper(_commerceProductDefinition.toEscapedModel());
	}

	@Override
	public CommerceProductDefinition toUnescapedModel() {
		return new CommerceProductDefinitionWrapper(_commerceProductDefinition.toUnescapedModel());
	}

	/**
	* Returns the available individually of this commerce product definition.
	*
	* @return the available individually of this commerce product definition
	*/
	@Override
	public boolean getAvailableIndividually() {
		return _commerceProductDefinition.getAvailableIndividually();
	}

	/**
	* Returns <code>true</code> if this commerce product definition is approved.
	*
	* @return <code>true</code> if this commerce product definition is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _commerceProductDefinition.isApproved();
	}

	/**
	* Returns <code>true</code> if this commerce product definition is available individually.
	*
	* @return <code>true</code> if this commerce product definition is available individually; <code>false</code> otherwise
	*/
	@Override
	public boolean isAvailableIndividually() {
		return _commerceProductDefinition.isAvailableIndividually();
	}

	@Override
	public boolean isCachedModel() {
		return _commerceProductDefinition.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this commerce product definition is denied.
	*
	* @return <code>true</code> if this commerce product definition is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _commerceProductDefinition.isDenied();
	}

	/**
	* Returns <code>true</code> if this commerce product definition is a draft.
	*
	* @return <code>true</code> if this commerce product definition is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _commerceProductDefinition.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _commerceProductDefinition.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this commerce product definition is expired.
	*
	* @return <code>true</code> if this commerce product definition is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _commerceProductDefinition.isExpired();
	}

	/**
	* Returns <code>true</code> if this commerce product definition is in the Recycle Bin.
	*
	* @return <code>true</code> if this commerce product definition is in the Recycle Bin; <code>false</code> otherwise
	*/
	@Override
	public boolean isInTrash() {
		return _commerceProductDefinition.isInTrash();
	}

	/**
	* Returns <code>true</code> if the parent of this commerce product definition is in the Recycle Bin.
	*
	* @return <code>true</code> if the parent of this commerce product definition is in the Recycle Bin; <code>false</code> otherwise
	*/
	@Override
	public boolean isInTrashContainer() {
		return _commerceProductDefinition.isInTrashContainer();
	}

	@Override
	public boolean isInTrashExplicitly() {
		return _commerceProductDefinition.isInTrashExplicitly();
	}

	@Override
	public boolean isInTrashImplicitly() {
		return _commerceProductDefinition.isInTrashImplicitly();
	}

	/**
	* Returns <code>true</code> if this commerce product definition is inactive.
	*
	* @return <code>true</code> if this commerce product definition is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _commerceProductDefinition.isInactive();
	}

	/**
	* Returns <code>true</code> if this commerce product definition is incomplete.
	*
	* @return <code>true</code> if this commerce product definition is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _commerceProductDefinition.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _commerceProductDefinition.isNew();
	}

	/**
	* Returns <code>true</code> if this commerce product definition is pending.
	*
	* @return <code>true</code> if this commerce product definition is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _commerceProductDefinition.isPending();
	}

	/**
	* Returns <code>true</code> if this commerce product definition is scheduled.
	*
	* @return <code>true</code> if this commerce product definition is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _commerceProductDefinition.isScheduled();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _commerceProductDefinition.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CommerceProductDefinition> toCacheModel() {
		return _commerceProductDefinition.toCacheModel();
	}

	/**
	* Returns the trash handler for this commerce product definition.
	*
	* @return the trash handler for this commerce product definition
	* @deprecated As of 7.0.0, with no direct replacement
	*/
	@Deprecated
	@Override
	public com.liferay.portal.kernel.trash.TrashHandler getTrashHandler() {
		return _commerceProductDefinition.getTrashHandler();
	}

	/**
	* Returns the trash entry created when this commerce product definition was moved to the Recycle Bin. The trash entry may belong to one of the ancestors of this commerce product definition.
	*
	* @return the trash entry created when this commerce product definition was moved to the Recycle Bin
	*/
	@Override
	public com.liferay.trash.kernel.model.TrashEntry getTrashEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceProductDefinition.getTrashEntry();
	}

	@Override
	public int compareTo(CommerceProductDefinition commerceProductDefinition) {
		return _commerceProductDefinition.compareTo(commerceProductDefinition);
	}

	/**
	* Returns the status of this commerce product definition.
	*
	* @return the status of this commerce product definition
	*/
	@Override
	public int getStatus() {
		return _commerceProductDefinition.getStatus();
	}

	@Override
	public int hashCode() {
		return _commerceProductDefinition.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceProductDefinition.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new CommerceProductDefinitionWrapper((CommerceProductDefinition)_commerceProductDefinition.clone());
	}

	/**
	* Returns the base sku of this commerce product definition.
	*
	* @return the base sku of this commerce product definition
	*/
	@Override
	public java.lang.String getBaseSKU() {
		return _commerceProductDefinition.getBaseSKU();
	}

	/**
	* Returns the ddm structure key of this commerce product definition.
	*
	* @return the ddm structure key of this commerce product definition
	*/
	@Override
	public java.lang.String getDDMStructureKey() {
		return _commerceProductDefinition.getDDMStructureKey();
	}

	/**
	* Returns the default language ID of this commerce product definition.
	*
	* @return the default language ID of this commerce product definition
	*/
	@Override
	public java.lang.String getDefaultLanguageId() {
		return _commerceProductDefinition.getDefaultLanguageId();
	}

	@Override
	public java.lang.String getDescription() {
		return _commerceProductDefinition.getDescription();
	}

	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _commerceProductDefinition.getDescription(languageId);
	}

	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _commerceProductDefinition.getDescription(languageId, useDefault);
	}

	@Override
	public java.lang.String getDescriptionMapAsXML() {
		return _commerceProductDefinition.getDescriptionMapAsXML();
	}

	/**
	* Returns the product type name of this commerce product definition.
	*
	* @return the product type name of this commerce product definition
	*/
	@Override
	public java.lang.String getProductTypeName() {
		return _commerceProductDefinition.getProductTypeName();
	}

	/**
	* Returns the status by user name of this commerce product definition.
	*
	* @return the status by user name of this commerce product definition
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _commerceProductDefinition.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this commerce product definition.
	*
	* @return the status by user uuid of this commerce product definition
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _commerceProductDefinition.getStatusByUserUuid();
	}

	@Override
	public java.lang.String getTitle() {
		return _commerceProductDefinition.getTitle();
	}

	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _commerceProductDefinition.getTitle(languageId);
	}

	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _commerceProductDefinition.getTitle(languageId, useDefault);
	}

	@Override
	public java.lang.String getTitleMapAsXML() {
		return _commerceProductDefinition.getTitleMapAsXML();
	}

	@Override
	public java.lang.String getUrlTitle() {
		return _commerceProductDefinition.getUrlTitle();
	}

	@Override
	public java.lang.String getUrlTitle(java.lang.String languageId) {
		return _commerceProductDefinition.getUrlTitle(languageId);
	}

	@Override
	public java.lang.String getUrlTitle(java.lang.String languageId,
		boolean useDefault) {
		return _commerceProductDefinition.getUrlTitle(languageId, useDefault);
	}

	@Override
	public java.lang.String getUrlTitleMapAsXML() {
		return _commerceProductDefinition.getUrlTitleMapAsXML();
	}

	/**
	* Returns the user name of this commerce product definition.
	*
	* @return the user name of this commerce product definition
	*/
	@Override
	public java.lang.String getUserName() {
		return _commerceProductDefinition.getUserName();
	}

	/**
	* Returns the user uuid of this commerce product definition.
	*
	* @return the user uuid of this commerce product definition
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _commerceProductDefinition.getUserUuid();
	}

	/**
	* Returns the uuid of this commerce product definition.
	*
	* @return the uuid of this commerce product definition
	*/
	@Override
	public java.lang.String getUuid() {
		return _commerceProductDefinition.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _commerceProductDefinition.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _commerceProductDefinition.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _commerceProductDefinition.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this commerce product definition.
	*
	* @return the create date of this commerce product definition
	*/
	@Override
	public Date getCreateDate() {
		return _commerceProductDefinition.getCreateDate();
	}

	/**
	* Returns the display date of this commerce product definition.
	*
	* @return the display date of this commerce product definition
	*/
	@Override
	public Date getDisplayDate() {
		return _commerceProductDefinition.getDisplayDate();
	}

	/**
	* Returns the expiration date of this commerce product definition.
	*
	* @return the expiration date of this commerce product definition
	*/
	@Override
	public Date getExpirationDate() {
		return _commerceProductDefinition.getExpirationDate();
	}

	/**
	* Returns the last publish date of this commerce product definition.
	*
	* @return the last publish date of this commerce product definition
	*/
	@Override
	public Date getLastPublishDate() {
		return _commerceProductDefinition.getLastPublishDate();
	}

	/**
	* Returns the modified date of this commerce product definition.
	*
	* @return the modified date of this commerce product definition
	*/
	@Override
	public Date getModifiedDate() {
		return _commerceProductDefinition.getModifiedDate();
	}

	/**
	* Returns the status date of this commerce product definition.
	*
	* @return the status date of this commerce product definition
	*/
	@Override
	public Date getStatusDate() {
		return _commerceProductDefinition.getStatusDate();
	}

	@Override
	public Map<java.lang.String, java.lang.String> getLanguageIdToDescriptionMap() {
		return _commerceProductDefinition.getLanguageIdToDescriptionMap();
	}

	@Override
	public Map<java.lang.String, java.lang.String> getLanguageIdToTitleMap() {
		return _commerceProductDefinition.getLanguageIdToTitleMap();
	}

	@Override
	public Map<java.lang.String, java.lang.String> getLanguageIdToUrlTitleMap() {
		return _commerceProductDefinition.getLanguageIdToUrlTitleMap();
	}

	/**
	* Returns the commerce product definition ID of this commerce product definition.
	*
	* @return the commerce product definition ID of this commerce product definition
	*/
	@Override
	public long getCommerceProductDefinitionId() {
		return _commerceProductDefinition.getCommerceProductDefinitionId();
	}

	/**
	* Returns the company ID of this commerce product definition.
	*
	* @return the company ID of this commerce product definition
	*/
	@Override
	public long getCompanyId() {
		return _commerceProductDefinition.getCompanyId();
	}

	/**
	* Returns the group ID of this commerce product definition.
	*
	* @return the group ID of this commerce product definition
	*/
	@Override
	public long getGroupId() {
		return _commerceProductDefinition.getGroupId();
	}

	/**
	* Returns the primary key of this commerce product definition.
	*
	* @return the primary key of this commerce product definition
	*/
	@Override
	public long getPrimaryKey() {
		return _commerceProductDefinition.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this commerce product definition.
	*
	* @return the status by user ID of this commerce product definition
	*/
	@Override
	public long getStatusByUserId() {
		return _commerceProductDefinition.getStatusByUserId();
	}

	/**
	* Returns the class primary key of the trash entry for this commerce product definition.
	*
	* @return the class primary key of the trash entry for this commerce product definition
	*/
	@Override
	public long getTrashEntryClassPK() {
		return _commerceProductDefinition.getTrashEntryClassPK();
	}

	/**
	* Returns the user ID of this commerce product definition.
	*
	* @return the user ID of this commerce product definition
	*/
	@Override
	public long getUserId() {
		return _commerceProductDefinition.getUserId();
	}

	@Override
	public void persist() {
		_commerceProductDefinition.persist();
	}

	/**
	* Sets whether this commerce product definition is available individually.
	*
	* @param availableIndividually the available individually of this commerce product definition
	*/
	@Override
	public void setAvailableIndividually(boolean availableIndividually) {
		_commerceProductDefinition.setAvailableIndividually(availableIndividually);
	}

	/**
	* Sets the base sku of this commerce product definition.
	*
	* @param baseSKU the base sku of this commerce product definition
	*/
	@Override
	public void setBaseSKU(java.lang.String baseSKU) {
		_commerceProductDefinition.setBaseSKU(baseSKU);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_commerceProductDefinition.setCachedModel(cachedModel);
	}

	/**
	* Sets the commerce product definition ID of this commerce product definition.
	*
	* @param commerceProductDefinitionId the commerce product definition ID of this commerce product definition
	*/
	@Override
	public void setCommerceProductDefinitionId(long commerceProductDefinitionId) {
		_commerceProductDefinition.setCommerceProductDefinitionId(commerceProductDefinitionId);
	}

	/**
	* Sets the company ID of this commerce product definition.
	*
	* @param companyId the company ID of this commerce product definition
	*/
	@Override
	public void setCompanyId(long companyId) {
		_commerceProductDefinition.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this commerce product definition.
	*
	* @param createDate the create date of this commerce product definition
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_commerceProductDefinition.setCreateDate(createDate);
	}

	/**
	* Sets the ddm structure key of this commerce product definition.
	*
	* @param DDMStructureKey the ddm structure key of this commerce product definition
	*/
	@Override
	public void setDDMStructureKey(java.lang.String DDMStructureKey) {
		_commerceProductDefinition.setDDMStructureKey(DDMStructureKey);
	}

	/**
	* Sets the default language ID of this commerce product definition.
	*
	* @param defaultLanguageId the default language ID of this commerce product definition
	*/
	@Override
	public void setDefaultLanguageId(java.lang.String defaultLanguageId) {
		_commerceProductDefinition.setDefaultLanguageId(defaultLanguageId);
	}

	/**
	* Sets the display date of this commerce product definition.
	*
	* @param displayDate the display date of this commerce product definition
	*/
	@Override
	public void setDisplayDate(Date displayDate) {
		_commerceProductDefinition.setDisplayDate(displayDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_commerceProductDefinition.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_commerceProductDefinition.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_commerceProductDefinition.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the expiration date of this commerce product definition.
	*
	* @param expirationDate the expiration date of this commerce product definition
	*/
	@Override
	public void setExpirationDate(Date expirationDate) {
		_commerceProductDefinition.setExpirationDate(expirationDate);
	}

	/**
	* Sets the group ID of this commerce product definition.
	*
	* @param groupId the group ID of this commerce product definition
	*/
	@Override
	public void setGroupId(long groupId) {
		_commerceProductDefinition.setGroupId(groupId);
	}

	/**
	* Sets the last publish date of this commerce product definition.
	*
	* @param lastPublishDate the last publish date of this commerce product definition
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_commerceProductDefinition.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this commerce product definition.
	*
	* @param modifiedDate the modified date of this commerce product definition
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_commerceProductDefinition.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_commerceProductDefinition.setNew(n);
	}

	/**
	* Sets the primary key of this commerce product definition.
	*
	* @param primaryKey the primary key of this commerce product definition
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_commerceProductDefinition.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_commerceProductDefinition.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the product type name of this commerce product definition.
	*
	* @param productTypeName the product type name of this commerce product definition
	*/
	@Override
	public void setProductTypeName(java.lang.String productTypeName) {
		_commerceProductDefinition.setProductTypeName(productTypeName);
	}

	/**
	* Sets the status of this commerce product definition.
	*
	* @param status the status of this commerce product definition
	*/
	@Override
	public void setStatus(int status) {
		_commerceProductDefinition.setStatus(status);
	}

	/**
	* Sets the status by user ID of this commerce product definition.
	*
	* @param statusByUserId the status by user ID of this commerce product definition
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_commerceProductDefinition.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this commerce product definition.
	*
	* @param statusByUserName the status by user name of this commerce product definition
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_commerceProductDefinition.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this commerce product definition.
	*
	* @param statusByUserUuid the status by user uuid of this commerce product definition
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_commerceProductDefinition.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this commerce product definition.
	*
	* @param statusDate the status date of this commerce product definition
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_commerceProductDefinition.setStatusDate(statusDate);
	}

	/**
	* Sets the user ID of this commerce product definition.
	*
	* @param userId the user ID of this commerce product definition
	*/
	@Override
	public void setUserId(long userId) {
		_commerceProductDefinition.setUserId(userId);
	}

	/**
	* Sets the user name of this commerce product definition.
	*
	* @param userName the user name of this commerce product definition
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_commerceProductDefinition.setUserName(userName);
	}

	/**
	* Sets the user uuid of this commerce product definition.
	*
	* @param userUuid the user uuid of this commerce product definition
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_commerceProductDefinition.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this commerce product definition.
	*
	* @param uuid the uuid of this commerce product definition
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_commerceProductDefinition.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceProductDefinitionWrapper)) {
			return false;
		}

		CommerceProductDefinitionWrapper commerceProductDefinitionWrapper = (CommerceProductDefinitionWrapper)obj;

		if (Objects.equals(_commerceProductDefinition,
					commerceProductDefinitionWrapper._commerceProductDefinition)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _commerceProductDefinition.getStagedModelType();
	}

	@Override
	public CommerceProductDefinition getWrappedModel() {
		return _commerceProductDefinition;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _commerceProductDefinition.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _commerceProductDefinition.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_commerceProductDefinition.resetOriginalValues();
	}

	private final CommerceProductDefinition _commerceProductDefinition;
}