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

import com.liferay.osb.koroneiki.taproot.service.persistence.ContactAccountRolePK;
import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ContactAccountRole service. Represents a row in the &quot;Koroneiki_ContactAccountRole&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.osb.koroneiki.taproot.model.impl.ContactAccountRoleModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.osb.koroneiki.taproot.model.impl.ContactAccountRoleImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContactAccountRole
 * @generated
 */
@ProviderType
public interface ContactAccountRoleModel extends BaseModel<ContactAccountRole> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a contact account role model instance should use the {@link ContactAccountRole} interface instead.
	 */

	/**
	 * Returns the primary key of this contact account role.
	 *
	 * @return the primary key of this contact account role
	 */
	public ContactAccountRolePK getPrimaryKey();

	/**
	 * Sets the primary key of this contact account role.
	 *
	 * @param primaryKey the primary key of this contact account role
	 */
	public void setPrimaryKey(ContactAccountRolePK primaryKey);

	/**
	 * Returns the contact ID of this contact account role.
	 *
	 * @return the contact ID of this contact account role
	 */
	public long getContactId();

	/**
	 * Sets the contact ID of this contact account role.
	 *
	 * @param contactId the contact ID of this contact account role
	 */
	public void setContactId(long contactId);

	/**
	 * Returns the account ID of this contact account role.
	 *
	 * @return the account ID of this contact account role
	 */
	public long getAccountId();

	/**
	 * Sets the account ID of this contact account role.
	 *
	 * @param accountId the account ID of this contact account role
	 */
	public void setAccountId(long accountId);

	/**
	 * Returns the contact role ID of this contact account role.
	 *
	 * @return the contact role ID of this contact account role
	 */
	public long getContactRoleId();

	/**
	 * Sets the contact role ID of this contact account role.
	 *
	 * @param contactRoleId the contact role ID of this contact account role
	 */
	public void setContactRoleId(long contactRoleId);

}