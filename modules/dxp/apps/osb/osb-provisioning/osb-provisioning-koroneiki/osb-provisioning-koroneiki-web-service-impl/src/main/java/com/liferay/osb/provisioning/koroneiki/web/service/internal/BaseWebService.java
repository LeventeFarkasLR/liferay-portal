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

package com.liferay.osb.provisioning.koroneiki.web.service.internal;

import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.provisioning.koroneiki.web.service.exception.HttpException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @author Amos Fong
 */
public class BaseWebService {

	protected <T> T processDTO(
			HttpInvoker.HttpResponse httpResponse,
			Function<String, T> toDTOFunction)
		throws Exception {

		validateResponse(httpResponse);

		try {
			return toDTOFunction.apply(httpResponse.getContent());
		}
		catch (Exception exception) {
			throw new HttpException(
				httpResponse.getContent(), exception,
				httpResponse.getStatusCode());
		}
	}

	protected <T> List<T> processDTOList(
			HttpInvoker.HttpResponse httpResponse,
			Function<String, T> toDTOFunction)
		throws Exception {

		validateResponse(httpResponse);

		try {
			Page<T> page = Page.of(httpResponse.getContent(), toDTOFunction);

			if ((page != null) && (page.getItems() != null)) {
				return new ArrayList<>(page.getItems());
			}

			return Collections.emptyList();
		}
		catch (Exception exception) {
			throw new HttpException(
				httpResponse.getContent(), exception,
				httpResponse.getStatusCode());
		}
	}

	protected void validateResponse(HttpInvoker.HttpResponse httpResponse)
		throws HttpException {

		int statusCode = httpResponse.getStatusCode();

		if (statusCode >= 300) {
			String errorMessage = httpResponse.getContent();

			try {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
					httpResponse.getContent());

				String title = jsonObject.getString("title");

				if (Validator.isNotNull(title)) {
					errorMessage = title;
				}
			}
			catch (Exception exception) {
			}

			throw new HttpException(errorMessage, statusCode);
		}
	}

}