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

package com.liferay.osb.koroneiki.phloem.rest.internal.jaxrs.exception.mapper;

import com.liferay.portal.kernel.exception.AddressCityException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * Converts any {@code AddressCityException} to a {@code 400} error.
 *
 * @author Amos Fong
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Koroneiki.REST)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Koroneiki.REST.AddressCityException"
	},
	service = ExceptionMapper.class
)
public class AddressCityExceptionMapper
	implements ExceptionMapper<AddressCityException> {

	@Override
	public Response toResponse(AddressCityException addressCityException) {
		return Response.status(
			400
		).type(
			MediaType.TEXT_PLAIN
		).entity(
			"Please enter a valid address locality"
		).build();
	}

}