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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ContactResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactSerDes;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = ContactWebService.class
)
public class ContactWebServiceImpl implements ContactWebService {

	public Contact addContact(
			String agentName, String agentUID, Contact contact)
		throws Exception {

		return _contactResource.postContact(agentName, agentUID, contact);
	}

	public Contact fetchContactByEmailAddress(String emailAddress)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			_contactResource.getContactByEmailAddresEmailAddressHttpResponse(
				emailAddress);

		if ((httpResponse.getStatusCode() ==
				HttpServletResponse.SC_BAD_REQUEST) ||
			(httpResponse.getStatusCode() ==
				HttpServletResponse.SC_NOT_FOUND)) {

			return null;
		}

		return ContactSerDes.toDTO(httpResponse.getContent());
	}

	public Contact getContactByEmailAddress(String emailAddress)
		throws Exception {

		return _contactResource.getContactByEmailAddresEmailAddress(
			emailAddress);
	}

	public List<Contact> search(
			String search, String filterString, int page, int pageSize,
			String sortString)
		throws Exception {

		Page<Contact> contactsPage = _contactResource.getContactsPage(
			search, filterString, Pagination.of(page, pageSize), sortString);

		if ((contactsPage != null) && (contactsPage.getItems() != null)) {
			return new ArrayList<>(contactsPage.getItems());
		}

		return Collections.emptyList();
	}

	public long searchCount(String search, String filterString)
		throws Exception {

		Page<Contact> contactsPage = _contactResource.getContactsPage(
			search, filterString, Pagination.of(1, 1), StringPool.BLANK);

		if (contactsPage != null) {
			return contactsPage.getTotalCount();
		}

		return 0;
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		ContactResource.Builder builder = ContactResource.builder();

		_contactResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).parameter(
			"nestedFields", "entitlements"
		).build();
	}

	private ContactResource _contactResource;

}