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

package com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ExternalLinkSerDes;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public class ExternalLinkResource {

	public static Page<ExternalLink> getAccountExternalLinksPage(
			Long accountId, Pagination pagination)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			getAccountExternalLinksPageHttpResponse(accountId, pagination);

		String content = httpResponse.getContent();

		_logger.fine("HTTP response content: " + content);

		_logger.fine("HTTP response message: " + httpResponse.getMessage());
		_logger.fine(
			"HTTP response status code: " + httpResponse.getStatusCode());

		return Page.of(content, ExternalLinkSerDes::toDTO);
	}

	public static HttpInvoker.HttpResponse
			getAccountExternalLinksPageHttpResponse(
				Long accountId, Pagination pagination)
		throws Exception {

		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

		if (pagination != null) {
			httpInvoker.parameter("page", String.valueOf(pagination.getPage()));
			httpInvoker.parameter(
				"pageSize", String.valueOf(pagination.getPageSize()));
		}

		httpInvoker.path(
			"http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountId}/external-links",
			accountId);

		httpInvoker.userNameAndPassword("test@liferay.com:test");

		return httpInvoker.invoke();
	}

	public static ExternalLink postAccountExternalLink(
			Long accountId, ExternalLink externalLink)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			postAccountExternalLinkHttpResponse(accountId, externalLink);

		String content = httpResponse.getContent();

		_logger.fine("HTTP response content: " + content);

		_logger.fine("HTTP response message: " + httpResponse.getMessage());
		_logger.fine(
			"HTTP response status code: " + httpResponse.getStatusCode());

		try {
			return ExternalLinkSerDes.toDTO(content);
		}
		catch (Exception e) {
			_logger.log(
				Level.WARNING, "Unable to process HTTP response: " + content,
				e);

			throw e;
		}
	}

	public static HttpInvoker.HttpResponse postAccountExternalLinkHttpResponse(
			Long accountId, ExternalLink externalLink)
		throws Exception {

		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(externalLink.toString(), "application/json");

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);

		httpInvoker.path(
			"http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountId}/external-links",
			accountId);

		httpInvoker.userNameAndPassword("test@liferay.com:test");

		return httpInvoker.invoke();
	}

	public static Page<ExternalLink> getContactExternalLinksPage(
			Long contactId, Pagination pagination)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			getContactExternalLinksPageHttpResponse(contactId, pagination);

		String content = httpResponse.getContent();

		_logger.fine("HTTP response content: " + content);

		_logger.fine("HTTP response message: " + httpResponse.getMessage());
		_logger.fine(
			"HTTP response status code: " + httpResponse.getStatusCode());

		return Page.of(content, ExternalLinkSerDes::toDTO);
	}

	public static HttpInvoker.HttpResponse
			getContactExternalLinksPageHttpResponse(
				Long contactId, Pagination pagination)
		throws Exception {

		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

		if (pagination != null) {
			httpInvoker.parameter("page", String.valueOf(pagination.getPage()));
			httpInvoker.parameter(
				"pageSize", String.valueOf(pagination.getPageSize()));
		}

		httpInvoker.path(
			"http://localhost:8080/o/koroneiki-rest/v1.0/contacts/{contactId}/external-links",
			contactId);

		httpInvoker.userNameAndPassword("test@liferay.com:test");

		return httpInvoker.invoke();
	}

	public static ExternalLink postContactExternalLink(
			Long contactId, ExternalLink externalLink)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			postContactExternalLinkHttpResponse(contactId, externalLink);

		String content = httpResponse.getContent();

		_logger.fine("HTTP response content: " + content);

		_logger.fine("HTTP response message: " + httpResponse.getMessage());
		_logger.fine(
			"HTTP response status code: " + httpResponse.getStatusCode());

		try {
			return ExternalLinkSerDes.toDTO(content);
		}
		catch (Exception e) {
			_logger.log(
				Level.WARNING, "Unable to process HTTP response: " + content,
				e);

			throw e;
		}
	}

	public static HttpInvoker.HttpResponse postContactExternalLinkHttpResponse(
			Long contactId, ExternalLink externalLink)
		throws Exception {

		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(externalLink.toString(), "application/json");

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);

		httpInvoker.path(
			"http://localhost:8080/o/koroneiki-rest/v1.0/contacts/{contactId}/external-links",
			contactId);

		httpInvoker.userNameAndPassword("test@liferay.com:test");

		return httpInvoker.invoke();
	}

	public static void deleteExternalLink(Long externalLinkId)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse = deleteExternalLinkHttpResponse(
			externalLinkId);

		String content = httpResponse.getContent();

		_logger.fine("HTTP response content: " + content);

		_logger.fine("HTTP response message: " + httpResponse.getMessage());
		_logger.fine(
			"HTTP response status code: " + httpResponse.getStatusCode());
	}

	public static HttpInvoker.HttpResponse deleteExternalLinkHttpResponse(
			Long externalLinkId)
		throws Exception {

		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

		httpInvoker.path(
			"http://localhost:8080/o/koroneiki-rest/v1.0/external-links/{externalLinkId}",
			externalLinkId);

		httpInvoker.userNameAndPassword("test@liferay.com:test");

		return httpInvoker.invoke();
	}

	public static ExternalLink getExternalLink(Long externalLinkId)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse = getExternalLinkHttpResponse(
			externalLinkId);

		String content = httpResponse.getContent();

		_logger.fine("HTTP response content: " + content);

		_logger.fine("HTTP response message: " + httpResponse.getMessage());
		_logger.fine(
			"HTTP response status code: " + httpResponse.getStatusCode());

		try {
			return ExternalLinkSerDes.toDTO(content);
		}
		catch (Exception e) {
			_logger.log(
				Level.WARNING, "Unable to process HTTP response: " + content,
				e);

			throw e;
		}
	}

	public static HttpInvoker.HttpResponse getExternalLinkHttpResponse(
			Long externalLinkId)
		throws Exception {

		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

		httpInvoker.path(
			"http://localhost:8080/o/koroneiki-rest/v1.0/external-links/{externalLinkId}",
			externalLinkId);

		httpInvoker.userNameAndPassword("test@liferay.com:test");

		return httpInvoker.invoke();
	}

	public static Page<ExternalLink> getProductConsumptionExternalLinksPage(
			Long productConsumptionId, Pagination pagination)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			getProductConsumptionExternalLinksPageHttpResponse(
				productConsumptionId, pagination);

		String content = httpResponse.getContent();

		_logger.fine("HTTP response content: " + content);

		_logger.fine("HTTP response message: " + httpResponse.getMessage());
		_logger.fine(
			"HTTP response status code: " + httpResponse.getStatusCode());

		return Page.of(content, ExternalLinkSerDes::toDTO);
	}

	public static HttpInvoker.HttpResponse
			getProductConsumptionExternalLinksPageHttpResponse(
				Long productConsumptionId, Pagination pagination)
		throws Exception {

		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

		if (pagination != null) {
			httpInvoker.parameter("page", String.valueOf(pagination.getPage()));
			httpInvoker.parameter(
				"pageSize", String.valueOf(pagination.getPageSize()));
		}

		httpInvoker.path(
			"http://localhost:8080/o/koroneiki-rest/v1.0/product-consumptions/{productConsumptionId}/external-links",
			productConsumptionId);

		httpInvoker.userNameAndPassword("test@liferay.com:test");

		return httpInvoker.invoke();
	}

	public static ExternalLink postProductConsumptionExternalLink(
			Long productConsumptionId, ExternalLink externalLink)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			postProductConsumptionExternalLinkHttpResponse(
				productConsumptionId, externalLink);

		String content = httpResponse.getContent();

		_logger.fine("HTTP response content: " + content);

		_logger.fine("HTTP response message: " + httpResponse.getMessage());
		_logger.fine(
			"HTTP response status code: " + httpResponse.getStatusCode());

		try {
			return ExternalLinkSerDes.toDTO(content);
		}
		catch (Exception e) {
			_logger.log(
				Level.WARNING, "Unable to process HTTP response: " + content,
				e);

			throw e;
		}
	}

	public static HttpInvoker.HttpResponse
			postProductConsumptionExternalLinkHttpResponse(
				Long productConsumptionId, ExternalLink externalLink)
		throws Exception {

		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(externalLink.toString(), "application/json");

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);

		httpInvoker.path(
			"http://localhost:8080/o/koroneiki-rest/v1.0/product-consumptions/{productConsumptionId}/external-links",
			productConsumptionId);

		httpInvoker.userNameAndPassword("test@liferay.com:test");

		return httpInvoker.invoke();
	}

	public static Page<ExternalLink> getProductPurchaseExternalLinksPage(
			Long productPurchaseId, Pagination pagination)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			getProductPurchaseExternalLinksPageHttpResponse(
				productPurchaseId, pagination);

		String content = httpResponse.getContent();

		_logger.fine("HTTP response content: " + content);

		_logger.fine("HTTP response message: " + httpResponse.getMessage());
		_logger.fine(
			"HTTP response status code: " + httpResponse.getStatusCode());

		return Page.of(content, ExternalLinkSerDes::toDTO);
	}

	public static HttpInvoker.HttpResponse
			getProductPurchaseExternalLinksPageHttpResponse(
				Long productPurchaseId, Pagination pagination)
		throws Exception {

		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

		if (pagination != null) {
			httpInvoker.parameter("page", String.valueOf(pagination.getPage()));
			httpInvoker.parameter(
				"pageSize", String.valueOf(pagination.getPageSize()));
		}

		httpInvoker.path(
			"http://localhost:8080/o/koroneiki-rest/v1.0/product-purchases/{productPurchaseId}/external-links",
			productPurchaseId);

		httpInvoker.userNameAndPassword("test@liferay.com:test");

		return httpInvoker.invoke();
	}

	public static ExternalLink postProductPurchaseExternalLink(
			Long productPurchaseId, ExternalLink externalLink)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			postProductPurchaseExternalLinkHttpResponse(
				productPurchaseId, externalLink);

		String content = httpResponse.getContent();

		_logger.fine("HTTP response content: " + content);

		_logger.fine("HTTP response message: " + httpResponse.getMessage());
		_logger.fine(
			"HTTP response status code: " + httpResponse.getStatusCode());

		try {
			return ExternalLinkSerDes.toDTO(content);
		}
		catch (Exception e) {
			_logger.log(
				Level.WARNING, "Unable to process HTTP response: " + content,
				e);

			throw e;
		}
	}

	public static HttpInvoker.HttpResponse
			postProductPurchaseExternalLinkHttpResponse(
				Long productPurchaseId, ExternalLink externalLink)
		throws Exception {

		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(externalLink.toString(), "application/json");

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);

		httpInvoker.path(
			"http://localhost:8080/o/koroneiki-rest/v1.0/product-purchases/{productPurchaseId}/external-links",
			productPurchaseId);

		httpInvoker.userNameAndPassword("test@liferay.com:test");

		return httpInvoker.invoke();
	}

	public static Page<ExternalLink> getProductExternalLinksPage(
			Long productId, Pagination pagination)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			getProductExternalLinksPageHttpResponse(productId, pagination);

		String content = httpResponse.getContent();

		_logger.fine("HTTP response content: " + content);

		_logger.fine("HTTP response message: " + httpResponse.getMessage());
		_logger.fine(
			"HTTP response status code: " + httpResponse.getStatusCode());

		return Page.of(content, ExternalLinkSerDes::toDTO);
	}

	public static HttpInvoker.HttpResponse
			getProductExternalLinksPageHttpResponse(
				Long productId, Pagination pagination)
		throws Exception {

		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

		if (pagination != null) {
			httpInvoker.parameter("page", String.valueOf(pagination.getPage()));
			httpInvoker.parameter(
				"pageSize", String.valueOf(pagination.getPageSize()));
		}

		httpInvoker.path(
			"http://localhost:8080/o/koroneiki-rest/v1.0/products/{productId}/external-links",
			productId);

		httpInvoker.userNameAndPassword("test@liferay.com:test");

		return httpInvoker.invoke();
	}

	public static ExternalLink postProductExternalLink(
			Long productId, ExternalLink externalLink)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			postProductExternalLinkHttpResponse(productId, externalLink);

		String content = httpResponse.getContent();

		_logger.fine("HTTP response content: " + content);

		_logger.fine("HTTP response message: " + httpResponse.getMessage());
		_logger.fine(
			"HTTP response status code: " + httpResponse.getStatusCode());

		try {
			return ExternalLinkSerDes.toDTO(content);
		}
		catch (Exception e) {
			_logger.log(
				Level.WARNING, "Unable to process HTTP response: " + content,
				e);

			throw e;
		}
	}

	public static HttpInvoker.HttpResponse postProductExternalLinkHttpResponse(
			Long productId, ExternalLink externalLink)
		throws Exception {

		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(externalLink.toString(), "application/json");

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);

		httpInvoker.path(
			"http://localhost:8080/o/koroneiki-rest/v1.0/products/{productId}/external-links",
			productId);

		httpInvoker.userNameAndPassword("test@liferay.com:test");

		return httpInvoker.invoke();
	}

	public static Page<ExternalLink> getProjectExternalLinksPage(
			Long projectId, Pagination pagination)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			getProjectExternalLinksPageHttpResponse(projectId, pagination);

		String content = httpResponse.getContent();

		_logger.fine("HTTP response content: " + content);

		_logger.fine("HTTP response message: " + httpResponse.getMessage());
		_logger.fine(
			"HTTP response status code: " + httpResponse.getStatusCode());

		return Page.of(content, ExternalLinkSerDes::toDTO);
	}

	public static HttpInvoker.HttpResponse
			getProjectExternalLinksPageHttpResponse(
				Long projectId, Pagination pagination)
		throws Exception {

		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

		if (pagination != null) {
			httpInvoker.parameter("page", String.valueOf(pagination.getPage()));
			httpInvoker.parameter(
				"pageSize", String.valueOf(pagination.getPageSize()));
		}

		httpInvoker.path(
			"http://localhost:8080/o/koroneiki-rest/v1.0/projects/{projectId}/external-links",
			projectId);

		httpInvoker.userNameAndPassword("test@liferay.com:test");

		return httpInvoker.invoke();
	}

	public static ExternalLink postProjectExternalLink(
			Long projectId, ExternalLink externalLink)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			postProjectExternalLinkHttpResponse(projectId, externalLink);

		String content = httpResponse.getContent();

		_logger.fine("HTTP response content: " + content);

		_logger.fine("HTTP response message: " + httpResponse.getMessage());
		_logger.fine(
			"HTTP response status code: " + httpResponse.getStatusCode());

		try {
			return ExternalLinkSerDes.toDTO(content);
		}
		catch (Exception e) {
			_logger.log(
				Level.WARNING, "Unable to process HTTP response: " + content,
				e);

			throw e;
		}
	}

	public static HttpInvoker.HttpResponse postProjectExternalLinkHttpResponse(
			Long projectId, ExternalLink externalLink)
		throws Exception {

		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(externalLink.toString(), "application/json");

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);

		httpInvoker.path(
			"http://localhost:8080/o/koroneiki-rest/v1.0/projects/{projectId}/external-links",
			projectId);

		httpInvoker.userNameAndPassword("test@liferay.com:test");

		return httpInvoker.invoke();
	}

	public static Page<ExternalLink> getTeamExternalLinksPage(
			Long teamId, Pagination pagination)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			getTeamExternalLinksPageHttpResponse(teamId, pagination);

		String content = httpResponse.getContent();

		_logger.fine("HTTP response content: " + content);

		_logger.fine("HTTP response message: " + httpResponse.getMessage());
		_logger.fine(
			"HTTP response status code: " + httpResponse.getStatusCode());

		return Page.of(content, ExternalLinkSerDes::toDTO);
	}

	public static HttpInvoker.HttpResponse getTeamExternalLinksPageHttpResponse(
			Long teamId, Pagination pagination)
		throws Exception {

		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

		if (pagination != null) {
			httpInvoker.parameter("page", String.valueOf(pagination.getPage()));
			httpInvoker.parameter(
				"pageSize", String.valueOf(pagination.getPageSize()));
		}

		httpInvoker.path(
			"http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamId}/external-links",
			teamId);

		httpInvoker.userNameAndPassword("test@liferay.com:test");

		return httpInvoker.invoke();
	}

	public static ExternalLink postTeamExternalLink(
			Long teamId, ExternalLink externalLink)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			postTeamExternalLinkHttpResponse(teamId, externalLink);

		String content = httpResponse.getContent();

		_logger.fine("HTTP response content: " + content);

		_logger.fine("HTTP response message: " + httpResponse.getMessage());
		_logger.fine(
			"HTTP response status code: " + httpResponse.getStatusCode());

		try {
			return ExternalLinkSerDes.toDTO(content);
		}
		catch (Exception e) {
			_logger.log(
				Level.WARNING, "Unable to process HTTP response: " + content,
				e);

			throw e;
		}
	}

	public static HttpInvoker.HttpResponse postTeamExternalLinkHttpResponse(
			Long teamId, ExternalLink externalLink)
		throws Exception {

		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(externalLink.toString(), "application/json");

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);

		httpInvoker.path(
			"http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamId}/external-links",
			teamId);

		httpInvoker.userNameAndPassword("test@liferay.com:test");

		return httpInvoker.invoke();
	}

	private static final Logger _logger = Logger.getLogger(
		ExternalLinkResource.class.getName());

}