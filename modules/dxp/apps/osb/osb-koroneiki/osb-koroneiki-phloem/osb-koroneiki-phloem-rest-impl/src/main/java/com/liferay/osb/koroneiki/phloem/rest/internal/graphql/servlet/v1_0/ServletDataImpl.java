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

package com.liferay.osb.koroneiki.phloem.rest.internal.graphql.servlet.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.internal.graphql.mutation.v1_0.Mutation;
import com.liferay.osb.koroneiki.phloem.rest.internal.graphql.query.v1_0.Query;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.AccountResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactRoleResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ExternalLinkResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProjectResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamRoleResource;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Amos Fong
 * @generated
 */
@Component(immediate = true, service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setAccountResourceComponentServiceObjects(
			_accountResourceComponentServiceObjects);
		Mutation.setContactResourceComponentServiceObjects(
			_contactResourceComponentServiceObjects);
		Mutation.setContactRoleResourceComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects);
		Mutation.setExternalLinkResourceComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects);
		Mutation.setProjectResourceComponentServiceObjects(
			_projectResourceComponentServiceObjects);
		Mutation.setTeamResourceComponentServiceObjects(
			_teamResourceComponentServiceObjects);
		Mutation.setTeamRoleResourceComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects);

		Query.setAccountResourceComponentServiceObjects(
			_accountResourceComponentServiceObjects);
		Query.setContactResourceComponentServiceObjects(
			_contactResourceComponentServiceObjects);
		Query.setContactRoleResourceComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects);
		Query.setExternalLinkResourceComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects);
		Query.setProjectResourceComponentServiceObjects(
			_projectResourceComponentServiceObjects);
		Query.setTeamResourceComponentServiceObjects(
			_teamResourceComponentServiceObjects);
		Query.setTeamRoleResourceComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects);
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/koroneiki-rest-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<AccountResource>
		_accountResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ContactResource>
		_contactResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ContactRoleResource>
		_contactRoleResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ExternalLinkResource>
		_externalLinkResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ProjectResource>
		_projectResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<TeamResource>
		_teamResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<TeamRoleResource>
		_teamRoleResourceComponentServiceObjects;

}