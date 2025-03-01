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

package com.liferay.object.rest.manager.v1_0;

import com.liferay.object.rest.dto.v1_0.ObjectEntry;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.aggregation.Aggregation;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

/**
 * @author Gabriel Albuquerque
 */
public interface ObjectEntryManager {

	public ObjectEntry addObjectEntry(
			DTOConverterContext dtoConverterContext, long userId,
			long objectDefinitionId, ObjectEntry objectEntry)
		throws Exception;

	public void deleteObjectEntry(long objectEntryId) throws Exception;

	public Page<ObjectEntry> getObjectEntries(
			long companyId, long objectDefinitionId, Aggregation aggregation,
			DTOConverterContext dtoConverterContext, Filter filter,
			Pagination pagination, String search, Sort[] sorts)
		throws Exception;

	public ObjectEntry getObjectEntry(
			DTOConverterContext dtoConverterContext, long objectEntryId)
		throws Exception;

	public ObjectEntry updateObjectEntry(
			DTOConverterContext dtoConverterContext, long userId,
			long objectEntryId, ObjectEntry objectEntry)
		throws Exception;

}