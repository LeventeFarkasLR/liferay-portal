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

package com.liferay.osb.koroneiki.taproot.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedModel;

import java.util.Date;

/**
 * The base model interface for the Contact service. Represents a row in the &quot;Koroneiki_Contact&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.osb.koroneiki.taproot.model.impl.ContactModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.osb.koroneiki.taproot.model.impl.ContactImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Contact
 * @generated
 */
@ProviderType
public interface ContactModel
	extends BaseModel<Contact>, ShardedModel, StagedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a contact model instance should use the {@link Contact} interface instead.
	 */

	/**
	 * Returns the primary key of this contact.
	 *
	 * @return the primary key of this contact
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this contact.
	 *
	 * @param primaryKey the primary key of this contact
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this contact.
	 *
	 * @return the uuid of this contact
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this contact.
	 *
	 * @param uuid the uuid of this contact
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the contact ID of this contact.
	 *
	 * @return the contact ID of this contact
	 */
	public long getContactId();

	/**
	 * Sets the contact ID of this contact.
	 *
	 * @param contactId the contact ID of this contact
	 */
	public void setContactId(long contactId);

	/**
	 * Returns the company ID of this contact.
	 *
	 * @return the company ID of this contact
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this contact.
	 *
	 * @param companyId the company ID of this contact
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this contact.
	 *
	 * @return the user ID of this contact
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this contact.
	 *
	 * @param userId the user ID of this contact
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this contact.
	 *
	 * @return the user uuid of this contact
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this contact.
	 *
	 * @param userUuid the user uuid of this contact
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create date of this contact.
	 *
	 * @return the create date of this contact
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this contact.
	 *
	 * @param createDate the create date of this contact
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this contact.
	 *
	 * @return the modified date of this contact
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this contact.
	 *
	 * @param modifiedDate the modified date of this contact
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the first name of this contact.
	 *
	 * @return the first name of this contact
	 */
	@AutoEscape
	public String getFirstName();

	/**
	 * Sets the first name of this contact.
	 *
	 * @param firstName the first name of this contact
	 */
	public void setFirstName(String firstName);

	/**
	 * Returns the middle name of this contact.
	 *
	 * @return the middle name of this contact
	 */
	@AutoEscape
	public String getMiddleName();

	/**
	 * Sets the middle name of this contact.
	 *
	 * @param middleName the middle name of this contact
	 */
	public void setMiddleName(String middleName);

	/**
	 * Returns the last name of this contact.
	 *
	 * @return the last name of this contact
	 */
	@AutoEscape
	public String getLastName();

	/**
	 * Sets the last name of this contact.
	 *
	 * @param lastName the last name of this contact
	 */
	public void setLastName(String lastName);

	/**
	 * Returns the email address of this contact.
	 *
	 * @return the email address of this contact
	 */
	@AutoEscape
	public String getEmailAddress();

	/**
	 * Sets the email address of this contact.
	 *
	 * @param emailAddress the email address of this contact
	 */
	public void setEmailAddress(String emailAddress);

	/**
	 * Returns the language ID of this contact.
	 *
	 * @return the language ID of this contact
	 */
	@AutoEscape
	public String getLanguageId();

	/**
	 * Sets the language ID of this contact.
	 *
	 * @param languageId the language ID of this contact
	 */
	public void setLanguageId(String languageId);

}