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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.problem.Problem;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductConsumptionSerDes;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public interface ProductConsumptionResource {

	public static Builder builder() {
		return new Builder();
	}

	public Page<ProductConsumption> getAccountAccountKeyProductConsumptionsPage(
			String accountKey, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getAccountAccountKeyProductConsumptionsPageHttpResponse(
				String accountKey, Pagination pagination)
		throws Exception;

	public ProductConsumption postAccountAccountKeyProductConsumption(
			String agentName, String agentUID, String accountKey,
			ProductConsumption productConsumption)
		throws Exception;

	public HttpInvoker.HttpResponse
			postAccountAccountKeyProductConsumptionHttpResponse(
				String agentName, String agentUID, String accountKey,
				ProductConsumption productConsumption)
		throws Exception;

	public Page<ProductConsumption> getContactByOktaProductConsumptionsPage(
			String oktaId, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getContactByOktaProductConsumptionsPageHttpResponse(
				String oktaId, Pagination pagination)
		throws Exception;

	public Page<ProductConsumption>
			getContactByUuidContactUuidProductConsumptionsPage(
				String contactUuid, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getContactByUuidContactUuidProductConsumptionsPageHttpResponse(
				String contactUuid, Pagination pagination)
		throws Exception;

	public Page<ProductConsumption> getProductConsumptionsPage(
			String search, String filterString, Pagination pagination,
			String sortString)
		throws Exception;

	public HttpInvoker.HttpResponse getProductConsumptionsPageHttpResponse(
			String search, String filterString, Pagination pagination,
			String sortString)
		throws Exception;

	public Page<ProductConsumption>
			getProductConsumptionByExternalLinkDomainEntityNameEntityPage(
				String domain, String entityName, String entityId,
				Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getProductConsumptionByExternalLinkDomainEntityNameEntityPageHttpResponse(
				String domain, String entityName, String entityId,
				Pagination pagination)
		throws Exception;

	public void deleteProductConsumption(
			String agentName, String agentUID, String productConsumptionKey)
		throws Exception;

	public HttpInvoker.HttpResponse deleteProductConsumptionHttpResponse(
			String agentName, String agentUID, String productConsumptionKey)
		throws Exception;

	public void deleteProductConsumptionBatch(
			String agentName, String agentUID, String productConsumptionKey,
			String callbackURL, Object object)
		throws Exception;

	public HttpInvoker.HttpResponse deleteProductConsumptionBatchHttpResponse(
			String agentName, String agentUID, String productConsumptionKey,
			String callbackURL, Object object)
		throws Exception;

	public ProductConsumption getProductConsumption(
			String productConsumptionKey)
		throws Exception;

	public HttpInvoker.HttpResponse getProductConsumptionHttpResponse(
			String productConsumptionKey)
		throws Exception;

	public void deleteProductConsumptionProductConsumptionPermission(
			String agentName, String agentUID, String productConsumptionKey,
			com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
				ProductConsumptionPermission productConsumptionPermission)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteProductConsumptionProductConsumptionPermissionHttpResponse(
				String agentName, String agentUID, String productConsumptionKey,
				com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
					ProductConsumptionPermission productConsumptionPermission)
		throws Exception;

	public void putProductConsumptionProductConsumptionPermission(
			String agentName, String agentUID, String productConsumptionKey,
			com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
				ProductConsumptionPermission productConsumptionPermission)
		throws Exception;

	public HttpInvoker.HttpResponse
			putProductConsumptionProductConsumptionPermissionHttpResponse(
				String agentName, String agentUID, String productConsumptionKey,
				com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
					ProductConsumptionPermission productConsumptionPermission)
		throws Exception;

	public static class Builder {

		public Builder authentication(String login, String password) {
			_login = login;
			_password = password;

			return this;
		}

		public ProductConsumptionResource build() {
			return new ProductConsumptionResourceImpl(this);
		}

		public Builder endpoint(String host, int port, String scheme) {
			_host = host;
			_port = port;
			_scheme = scheme;

			return this;
		}

		public Builder header(String key, String value) {
			_headers.put(key, value);

			return this;
		}

		public Builder locale(Locale locale) {
			_locale = locale;

			return this;
		}

		public Builder parameter(String key, String value) {
			_parameters.put(key, value);

			return this;
		}

		private Builder() {
		}

		private Map<String, String> _headers = new LinkedHashMap<>();
		private String _host = "localhost";
		private Locale _locale;
		private String _login = "test@liferay.com";
		private String _password = "test";
		private Map<String, String> _parameters = new LinkedHashMap<>();
		private int _port = 8080;
		private String _scheme = "http";

	}

	public static class ProductConsumptionResourceImpl
		implements ProductConsumptionResource {

		public Page<ProductConsumption>
				getAccountAccountKeyProductConsumptionsPage(
					String accountKey, Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getAccountAccountKeyProductConsumptionsPageHttpResponse(
					accountKey, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, ProductConsumptionSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getAccountAccountKeyProductConsumptionsPageHttpResponse(
					String accountKey, Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/product-consumptions",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public ProductConsumption postAccountAccountKeyProductConsumption(
				String agentName, String agentUID, String accountKey,
				ProductConsumption productConsumption)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				postAccountAccountKeyProductConsumptionHttpResponse(
					agentName, agentUID, accountKey, productConsumption);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return ProductConsumptionSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				postAccountAccountKeyProductConsumptionHttpResponse(
					String agentName, String agentUID, String accountKey,
					ProductConsumption productConsumption)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(productConsumption.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/product-consumptions",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<ProductConsumption> getContactByOktaProductConsumptionsPage(
				String oktaId, Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getContactByOktaProductConsumptionsPageHttpResponse(
					oktaId, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, ProductConsumptionSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getContactByOktaProductConsumptionsPageHttpResponse(
					String oktaId, Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contacts/by-okta-id/{oktaId}/product-consumptions",
				oktaId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<ProductConsumption>
				getContactByUuidContactUuidProductConsumptionsPage(
					String contactUuid, Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getContactByUuidContactUuidProductConsumptionsPageHttpResponse(
					contactUuid, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, ProductConsumptionSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getContactByUuidContactUuidProductConsumptionsPageHttpResponse(
					String contactUuid, Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contacts/by-uuid/{contactUuid}/product-consumptions",
				contactUuid);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<ProductConsumption> getProductConsumptionsPage(
				String search, String filterString, Pagination pagination,
				String sortString)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getProductConsumptionsPageHttpResponse(
					search, filterString, pagination, sortString);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, ProductConsumptionSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse getProductConsumptionsPageHttpResponse(
				String search, String filterString, Pagination pagination,
				String sortString)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (search != null) {
				httpInvoker.parameter("search", String.valueOf(search));
			}

			if (filterString != null) {
				httpInvoker.parameter("filter", filterString);
			}

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			if (sortString != null) {
				httpInvoker.parameter("sort", sortString);
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/product-consumptions");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<ProductConsumption>
				getProductConsumptionByExternalLinkDomainEntityNameEntityPage(
					String domain, String entityName, String entityId,
					Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getProductConsumptionByExternalLinkDomainEntityNameEntityPageHttpResponse(
					domain, entityName, entityId, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, ProductConsumptionSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getProductConsumptionByExternalLinkDomainEntityNameEntityPageHttpResponse(
					String domain, String entityName, String entityId,
					Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/product-consumptions/by-external-link/{domain}/{entityName}/{entityId}",
				domain, entityName, entityId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteProductConsumption(
				String agentName, String agentUID, String productConsumptionKey)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteProductConsumptionHttpResponse(
					agentName, agentUID, productConsumptionKey);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse deleteProductConsumptionHttpResponse(
				String agentName, String agentUID, String productConsumptionKey)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/product-consumptions/{productConsumptionKey}",
				productConsumptionKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteProductConsumptionBatch(
				String agentName, String agentUID, String productConsumptionKey,
				String callbackURL, Object object)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteProductConsumptionBatchHttpResponse(
					agentName, agentUID, productConsumptionKey, callbackURL,
					object);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				deleteProductConsumptionBatchHttpResponse(
					String agentName, String agentUID,
					String productConsumptionKey, String callbackURL,
					Object object)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			if (callbackURL != null) {
				httpInvoker.parameter(
					"callbackURL", String.valueOf(callbackURL));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/product-consumptions/{productConsumptionKey}/batch",
				productConsumptionKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public ProductConsumption getProductConsumption(
				String productConsumptionKey)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getProductConsumptionHttpResponse(productConsumptionKey);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return ProductConsumptionSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse getProductConsumptionHttpResponse(
				String productConsumptionKey)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/product-consumptions/{productConsumptionKey}",
				productConsumptionKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteProductConsumptionProductConsumptionPermission(
				String agentName, String agentUID, String productConsumptionKey,
				com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
					ProductConsumptionPermission productConsumptionPermission)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteProductConsumptionProductConsumptionPermissionHttpResponse(
					agentName, agentUID, productConsumptionKey,
					productConsumptionPermission);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				deleteProductConsumptionProductConsumptionPermissionHttpResponse(
					String agentName, String agentUID,
					String productConsumptionKey,
					com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
						ProductConsumptionPermission
							productConsumptionPermission)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/product-consumptions/{productConsumptionKey}/product-consumption-permissions",
				productConsumptionKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putProductConsumptionProductConsumptionPermission(
				String agentName, String agentUID, String productConsumptionKey,
				com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
					ProductConsumptionPermission productConsumptionPermission)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putProductConsumptionProductConsumptionPermissionHttpResponse(
					agentName, agentUID, productConsumptionKey,
					productConsumptionPermission);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				putProductConsumptionProductConsumptionPermissionHttpResponse(
					String agentName, String agentUID,
					String productConsumptionKey,
					com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
						ProductConsumptionPermission
							productConsumptionPermission)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				productConsumptionPermission.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/product-consumptions/{productConsumptionKey}/product-consumption-permissions",
				productConsumptionKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		private ProductConsumptionResourceImpl(Builder builder) {
			_builder = builder;
		}

		private static final Logger _logger = Logger.getLogger(
			ProductConsumptionResource.class.getName());

		private Builder _builder;

	}

}