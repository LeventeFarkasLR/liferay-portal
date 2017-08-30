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

package com.liferay.commerce.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link CommerceAddress}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceAddress
 * @generated
 */
@ProviderType
public class CommerceAddressWrapper implements CommerceAddress,
	ModelWrapper<CommerceAddress> {
	public CommerceAddressWrapper(CommerceAddress commerceAddress) {
		_commerceAddress = commerceAddress;
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceAddress.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceAddress.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commerceAddressId", getCommerceAddressId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("addressUserId", getAddressUserId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("street1", getStreet1());
		attributes.put("street2", getStreet2());
		attributes.put("street3", getStreet3());
		attributes.put("city", getCity());
		attributes.put("zip", getZip());
		attributes.put("commerceRegionId", getCommerceRegionId());
		attributes.put("commerceCountryId", getCommerceCountryId());
		attributes.put("phoneNumber", getPhoneNumber());
		attributes.put("defaultBilling", getDefaultBilling());
		attributes.put("defaultShipping", getDefaultShipping());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long commerceAddressId = (Long)attributes.get("commerceAddressId");

		if (commerceAddressId != null) {
			setCommerceAddressId(commerceAddressId);
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

		Long addressUserId = (Long)attributes.get("addressUserId");

		if (addressUserId != null) {
			setAddressUserId(addressUserId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String street1 = (String)attributes.get("street1");

		if (street1 != null) {
			setStreet1(street1);
		}

		String street2 = (String)attributes.get("street2");

		if (street2 != null) {
			setStreet2(street2);
		}

		String street3 = (String)attributes.get("street3");

		if (street3 != null) {
			setStreet3(street3);
		}

		String city = (String)attributes.get("city");

		if (city != null) {
			setCity(city);
		}

		String zip = (String)attributes.get("zip");

		if (zip != null) {
			setZip(zip);
		}

		Long commerceRegionId = (Long)attributes.get("commerceRegionId");

		if (commerceRegionId != null) {
			setCommerceRegionId(commerceRegionId);
		}

		Long commerceCountryId = (Long)attributes.get("commerceCountryId");

		if (commerceCountryId != null) {
			setCommerceCountryId(commerceCountryId);
		}

		String phoneNumber = (String)attributes.get("phoneNumber");

		if (phoneNumber != null) {
			setPhoneNumber(phoneNumber);
		}

		Boolean defaultBilling = (Boolean)attributes.get("defaultBilling");

		if (defaultBilling != null) {
			setDefaultBilling(defaultBilling);
		}

		Boolean defaultShipping = (Boolean)attributes.get("defaultShipping");

		if (defaultShipping != null) {
			setDefaultShipping(defaultShipping);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CommerceAddressWrapper((CommerceAddress)_commerceAddress.clone());
	}

	@Override
	public int compareTo(CommerceAddress commerceAddress) {
		return _commerceAddress.compareTo(commerceAddress);
	}

	/**
	* Returns the address user ID of this commerce address.
	*
	* @return the address user ID of this commerce address
	*/
	@Override
	public long getAddressUserId() {
		return _commerceAddress.getAddressUserId();
	}

	/**
	* Returns the address user uuid of this commerce address.
	*
	* @return the address user uuid of this commerce address
	*/
	@Override
	public java.lang.String getAddressUserUuid() {
		return _commerceAddress.getAddressUserUuid();
	}

	/**
	* Returns the city of this commerce address.
	*
	* @return the city of this commerce address
	*/
	@Override
	public java.lang.String getCity() {
		return _commerceAddress.getCity();
	}

	/**
	* Returns the commerce address ID of this commerce address.
	*
	* @return the commerce address ID of this commerce address
	*/
	@Override
	public long getCommerceAddressId() {
		return _commerceAddress.getCommerceAddressId();
	}

	/**
	* Returns the commerce country ID of this commerce address.
	*
	* @return the commerce country ID of this commerce address
	*/
	@Override
	public long getCommerceCountryId() {
		return _commerceAddress.getCommerceCountryId();
	}

	/**
	* Returns the commerce region ID of this commerce address.
	*
	* @return the commerce region ID of this commerce address
	*/
	@Override
	public long getCommerceRegionId() {
		return _commerceAddress.getCommerceRegionId();
	}

	/**
	* Returns the company ID of this commerce address.
	*
	* @return the company ID of this commerce address
	*/
	@Override
	public long getCompanyId() {
		return _commerceAddress.getCompanyId();
	}

	/**
	* Returns the create date of this commerce address.
	*
	* @return the create date of this commerce address
	*/
	@Override
	public Date getCreateDate() {
		return _commerceAddress.getCreateDate();
	}

	/**
	* Returns the default billing of this commerce address.
	*
	* @return the default billing of this commerce address
	*/
	@Override
	public boolean getDefaultBilling() {
		return _commerceAddress.getDefaultBilling();
	}

	/**
	* Returns the default shipping of this commerce address.
	*
	* @return the default shipping of this commerce address
	*/
	@Override
	public boolean getDefaultShipping() {
		return _commerceAddress.getDefaultShipping();
	}

	/**
	* Returns the description of this commerce address.
	*
	* @return the description of this commerce address
	*/
	@Override
	public java.lang.String getDescription() {
		return _commerceAddress.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _commerceAddress.getExpandoBridge();
	}

	/**
	* Returns the group ID of this commerce address.
	*
	* @return the group ID of this commerce address
	*/
	@Override
	public long getGroupId() {
		return _commerceAddress.getGroupId();
	}

	/**
	* Returns the modified date of this commerce address.
	*
	* @return the modified date of this commerce address
	*/
	@Override
	public Date getModifiedDate() {
		return _commerceAddress.getModifiedDate();
	}

	/**
	* Returns the name of this commerce address.
	*
	* @return the name of this commerce address
	*/
	@Override
	public java.lang.String getName() {
		return _commerceAddress.getName();
	}

	/**
	* Returns the phone number of this commerce address.
	*
	* @return the phone number of this commerce address
	*/
	@Override
	public java.lang.String getPhoneNumber() {
		return _commerceAddress.getPhoneNumber();
	}

	/**
	* Returns the primary key of this commerce address.
	*
	* @return the primary key of this commerce address
	*/
	@Override
	public long getPrimaryKey() {
		return _commerceAddress.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceAddress.getPrimaryKeyObj();
	}

	/**
	* Returns the street1 of this commerce address.
	*
	* @return the street1 of this commerce address
	*/
	@Override
	public java.lang.String getStreet1() {
		return _commerceAddress.getStreet1();
	}

	/**
	* Returns the street2 of this commerce address.
	*
	* @return the street2 of this commerce address
	*/
	@Override
	public java.lang.String getStreet2() {
		return _commerceAddress.getStreet2();
	}

	/**
	* Returns the street3 of this commerce address.
	*
	* @return the street3 of this commerce address
	*/
	@Override
	public java.lang.String getStreet3() {
		return _commerceAddress.getStreet3();
	}

	/**
	* Returns the user ID of this commerce address.
	*
	* @return the user ID of this commerce address
	*/
	@Override
	public long getUserId() {
		return _commerceAddress.getUserId();
	}

	/**
	* Returns the user name of this commerce address.
	*
	* @return the user name of this commerce address
	*/
	@Override
	public java.lang.String getUserName() {
		return _commerceAddress.getUserName();
	}

	/**
	* Returns the user uuid of this commerce address.
	*
	* @return the user uuid of this commerce address
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _commerceAddress.getUserUuid();
	}

	/**
	* Returns the zip of this commerce address.
	*
	* @return the zip of this commerce address
	*/
	@Override
	public java.lang.String getZip() {
		return _commerceAddress.getZip();
	}

	@Override
	public int hashCode() {
		return _commerceAddress.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _commerceAddress.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this commerce address is default billing.
	*
	* @return <code>true</code> if this commerce address is default billing; <code>false</code> otherwise
	*/
	@Override
	public boolean isDefaultBilling() {
		return _commerceAddress.isDefaultBilling();
	}

	/**
	* Returns <code>true</code> if this commerce address is default shipping.
	*
	* @return <code>true</code> if this commerce address is default shipping; <code>false</code> otherwise
	*/
	@Override
	public boolean isDefaultShipping() {
		return _commerceAddress.isDefaultShipping();
	}

	@Override
	public boolean isEscapedModel() {
		return _commerceAddress.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _commerceAddress.isNew();
	}

	@Override
	public void persist() {
		_commerceAddress.persist();
	}

	/**
	* Sets the address user ID of this commerce address.
	*
	* @param addressUserId the address user ID of this commerce address
	*/
	@Override
	public void setAddressUserId(long addressUserId) {
		_commerceAddress.setAddressUserId(addressUserId);
	}

	/**
	* Sets the address user uuid of this commerce address.
	*
	* @param addressUserUuid the address user uuid of this commerce address
	*/
	@Override
	public void setAddressUserUuid(java.lang.String addressUserUuid) {
		_commerceAddress.setAddressUserUuid(addressUserUuid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_commerceAddress.setCachedModel(cachedModel);
	}

	/**
	* Sets the city of this commerce address.
	*
	* @param city the city of this commerce address
	*/
	@Override
	public void setCity(java.lang.String city) {
		_commerceAddress.setCity(city);
	}

	/**
	* Sets the commerce address ID of this commerce address.
	*
	* @param commerceAddressId the commerce address ID of this commerce address
	*/
	@Override
	public void setCommerceAddressId(long commerceAddressId) {
		_commerceAddress.setCommerceAddressId(commerceAddressId);
	}

	/**
	* Sets the commerce country ID of this commerce address.
	*
	* @param commerceCountryId the commerce country ID of this commerce address
	*/
	@Override
	public void setCommerceCountryId(long commerceCountryId) {
		_commerceAddress.setCommerceCountryId(commerceCountryId);
	}

	/**
	* Sets the commerce region ID of this commerce address.
	*
	* @param commerceRegionId the commerce region ID of this commerce address
	*/
	@Override
	public void setCommerceRegionId(long commerceRegionId) {
		_commerceAddress.setCommerceRegionId(commerceRegionId);
	}

	/**
	* Sets the company ID of this commerce address.
	*
	* @param companyId the company ID of this commerce address
	*/
	@Override
	public void setCompanyId(long companyId) {
		_commerceAddress.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this commerce address.
	*
	* @param createDate the create date of this commerce address
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_commerceAddress.setCreateDate(createDate);
	}

	/**
	* Sets whether this commerce address is default billing.
	*
	* @param defaultBilling the default billing of this commerce address
	*/
	@Override
	public void setDefaultBilling(boolean defaultBilling) {
		_commerceAddress.setDefaultBilling(defaultBilling);
	}

	/**
	* Sets whether this commerce address is default shipping.
	*
	* @param defaultShipping the default shipping of this commerce address
	*/
	@Override
	public void setDefaultShipping(boolean defaultShipping) {
		_commerceAddress.setDefaultShipping(defaultShipping);
	}

	/**
	* Sets the description of this commerce address.
	*
	* @param description the description of this commerce address
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_commerceAddress.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_commerceAddress.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_commerceAddress.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_commerceAddress.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this commerce address.
	*
	* @param groupId the group ID of this commerce address
	*/
	@Override
	public void setGroupId(long groupId) {
		_commerceAddress.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this commerce address.
	*
	* @param modifiedDate the modified date of this commerce address
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_commerceAddress.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this commerce address.
	*
	* @param name the name of this commerce address
	*/
	@Override
	public void setName(java.lang.String name) {
		_commerceAddress.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_commerceAddress.setNew(n);
	}

	/**
	* Sets the phone number of this commerce address.
	*
	* @param phoneNumber the phone number of this commerce address
	*/
	@Override
	public void setPhoneNumber(java.lang.String phoneNumber) {
		_commerceAddress.setPhoneNumber(phoneNumber);
	}

	/**
	* Sets the primary key of this commerce address.
	*
	* @param primaryKey the primary key of this commerce address
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_commerceAddress.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_commerceAddress.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the street1 of this commerce address.
	*
	* @param street1 the street1 of this commerce address
	*/
	@Override
	public void setStreet1(java.lang.String street1) {
		_commerceAddress.setStreet1(street1);
	}

	/**
	* Sets the street2 of this commerce address.
	*
	* @param street2 the street2 of this commerce address
	*/
	@Override
	public void setStreet2(java.lang.String street2) {
		_commerceAddress.setStreet2(street2);
	}

	/**
	* Sets the street3 of this commerce address.
	*
	* @param street3 the street3 of this commerce address
	*/
	@Override
	public void setStreet3(java.lang.String street3) {
		_commerceAddress.setStreet3(street3);
	}

	/**
	* Sets the user ID of this commerce address.
	*
	* @param userId the user ID of this commerce address
	*/
	@Override
	public void setUserId(long userId) {
		_commerceAddress.setUserId(userId);
	}

	/**
	* Sets the user name of this commerce address.
	*
	* @param userName the user name of this commerce address
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_commerceAddress.setUserName(userName);
	}

	/**
	* Sets the user uuid of this commerce address.
	*
	* @param userUuid the user uuid of this commerce address
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_commerceAddress.setUserUuid(userUuid);
	}

	/**
	* Sets the zip of this commerce address.
	*
	* @param zip the zip of this commerce address
	*/
	@Override
	public void setZip(java.lang.String zip) {
		_commerceAddress.setZip(zip);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CommerceAddress> toCacheModel() {
		return _commerceAddress.toCacheModel();
	}

	@Override
	public CommerceAddress toEscapedModel() {
		return new CommerceAddressWrapper(_commerceAddress.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _commerceAddress.toString();
	}

	@Override
	public CommerceAddress toUnescapedModel() {
		return new CommerceAddressWrapper(_commerceAddress.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _commerceAddress.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceAddressWrapper)) {
			return false;
		}

		CommerceAddressWrapper commerceAddressWrapper = (CommerceAddressWrapper)obj;

		if (Objects.equals(_commerceAddress,
					commerceAddressWrapper._commerceAddress)) {
			return true;
		}

		return false;
	}

	@Override
	public CommerceAddress getWrappedModel() {
		return _commerceAddress;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _commerceAddress.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _commerceAddress.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_commerceAddress.resetOriginalValues();
	}

	private final CommerceAddress _commerceAddress;
}