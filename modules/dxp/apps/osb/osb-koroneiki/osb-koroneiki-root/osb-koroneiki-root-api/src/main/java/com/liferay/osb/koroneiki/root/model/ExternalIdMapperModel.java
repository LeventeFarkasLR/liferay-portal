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

package com.liferay.osb.koroneiki.root.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ExternalIdMapper service. Represents a row in the &quot;Koroneiki_ExternalIdMapper&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.osb.koroneiki.root.model.impl.ExternalIdMapperModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.osb.koroneiki.root.model.impl.ExternalIdMapperImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExternalIdMapper
 * @generated
 */
@ProviderType
public interface ExternalIdMapperModel
	extends AttachedModel, BaseModel<ExternalIdMapper>, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a external ID mapper model instance should use the {@link ExternalIdMapper} interface instead.
	 */

	/**
	 * Returns the primary key of this external ID mapper.
	 *
	 * @return the primary key of this external ID mapper
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this external ID mapper.
	 *
	 * @param primaryKey the primary key of this external ID mapper
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the external ID mapper ID of this external ID mapper.
	 *
	 * @return the external ID mapper ID of this external ID mapper
	 */
	public long getExternalIdMapperId();

	/**
	 * Sets the external ID mapper ID of this external ID mapper.
	 *
	 * @param externalIdMapperId the external ID mapper ID of this external ID mapper
	 */
	public void setExternalIdMapperId(long externalIdMapperId);

	/**
	 * Returns the company ID of this external ID mapper.
	 *
	 * @return the company ID of this external ID mapper
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this external ID mapper.
	 *
	 * @param companyId the company ID of this external ID mapper
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the create date of this external ID mapper.
	 *
	 * @return the create date of this external ID mapper
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this external ID mapper.
	 *
	 * @param createDate the create date of this external ID mapper
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this external ID mapper.
	 *
	 * @return the modified date of this external ID mapper
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this external ID mapper.
	 *
	 * @param modifiedDate the modified date of this external ID mapper
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this external ID mapper.
	 *
	 * @return the fully qualified class name of this external ID mapper
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this external ID mapper.
	 *
	 * @return the class name ID of this external ID mapper
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this external ID mapper.
	 *
	 * @param classNameId the class name ID of this external ID mapper
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this external ID mapper.
	 *
	 * @return the class pk of this external ID mapper
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this external ID mapper.
	 *
	 * @param classPK the class pk of this external ID mapper
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the external source of this external ID mapper.
	 *
	 * @return the external source of this external ID mapper
	 */
	public int getExternalSource();

	/**
	 * Sets the external source of this external ID mapper.
	 *
	 * @param externalSource the external source of this external ID mapper
	 */
	public void setExternalSource(int externalSource);

	/**
	 * Returns the external ID of this external ID mapper.
	 *
	 * @return the external ID of this external ID mapper
	 */
	@AutoEscape
	public String getExternalId();

	/**
	 * Sets the external ID of this external ID mapper.
	 *
	 * @param externalId the external ID of this external ID mapper
	 */
	public void setExternalId(String externalId);

}