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

package com.liferay.osb.provisioning.zendesk.web.service.internal.search;

import com.liferay.osb.provisioning.zendesk.web.service.search.Query;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Amos Fong
 */
public class QueryImpl implements Query {

	public void addCriterion(String criterion) {
		_criteria.add(criterion);
	}

	public void addParameter(String key, String value) {
		_parameters.put(key, value);
	}

	public int getPage() {
		return _page;
	}

	public Map<String, String> getParameters() {
		Map<String, String> parameters = new HashMap<>();

		if (!_sideloads.isEmpty()) {
			parameters.put("include", StringUtil.merge(_sideloads));
		}

		if (_page > 0) {
			parameters.put("page", String.valueOf(_page));
		}

		if (!_parameters.isEmpty()) {
			parameters.putAll(_parameters);
		}

		if (!_criteria.isEmpty()) {
			parameters.put("query", getQuery());
		}

		if (Validator.isNotNull(_sortBy)) {
			parameters.put("sort_by", _sortBy);

			if (Validator.isNotNull(_sortOrder)) {
				parameters.put("sort_order", _sortOrder);
			}
		}

		return parameters;
	}

	public void setPage(int page) {
		_page = page;
	}

	public void setSortBy(String sortBy) {
		_sortBy = sortBy;
	}

	public void setSortOrder(boolean asc) {
		if (asc) {
			_sortOrder = "asc";
		}
		else {
			_sortOrder = "desc";
		}
	}

	protected String getQuery() {
		StringBundler sb = new StringBundler();

		for (String criterion : _criteria) {
			if (sb.index() != 0) {
				sb.append(StringPool.SPACE);
			}

			sb.append(criterion);
		}

		return sb.toString();
	}

	private final Set<String> _criteria = new HashSet<>();
	private int _page;
	private final Map<String, String> _parameters = new HashMap<>();
	private final Set<String> _sideloads = new HashSet<>();
	private String _sortBy;
	private String _sortOrder;

}