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
import com.liferay.osb.provisioning.zendesk.web.service.search.QueryFactory;
import com.liferay.osb.provisioning.zendesk.web.service.search.ZendeskTicketQuery;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = QueryFactory.class)
public class QueryFactoryImpl implements QueryFactory {

	public Query createQuery() {
		return new QueryImpl();
	}

	public ZendeskTicketQuery createZendeskTicketQuery() {
		return new ZendeskTicketQueryImpl();
	}

}