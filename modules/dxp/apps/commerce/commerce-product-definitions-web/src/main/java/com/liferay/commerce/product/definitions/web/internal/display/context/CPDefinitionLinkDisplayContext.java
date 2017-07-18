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

package com.liferay.commerce.product.definitions.web.internal.display.context;

import com.liferay.commerce.product.definitions.web.display.context.BaseCPDefinitionsSearchContainerDisplayContext;
import com.liferay.commerce.product.definitions.web.internal.util.CPDefinitionsPortletUtil;
import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.item.selector.criterion.CPDefinitionItemSelectorCriterion;
import com.liferay.commerce.product.model.CPDefinitionLink;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.service.CPDefinitionLinkService;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionLinkDisplayContext
	extends BaseCPDefinitionsSearchContainerDisplayContext<CPDefinitionLink> {

	public CPDefinitionLinkDisplayContext(
			ActionHelper actionHelper, HttpServletRequest httpServletRequest,
			CPDefinitionLinkService cpDefinitionLinkService,
			ItemSelector itemSelector)
		throws PortalException {

		super(
			actionHelper, httpServletRequest,
			CPDefinitionOptionRel.class.getSimpleName());

		setDefaultOrderByCol("priority");

		_cpDefinitionLinkService = cpDefinitionLinkService;
		_itemSelector = itemSelector;
	}

	public CPDefinitionLink getCPDefinitionLink() throws PortalException {
		if (_cpDefinitionLink != null) {
			return _cpDefinitionLink;
		}

		_cpDefinitionLink = actionHelper.getCPDefinitionLink(
			cpRequestHelper.getRenderRequest());

		return _cpDefinitionLink;
	}

	public long getCPDefinitionLinkId() throws PortalException {
		CPDefinitionLink cpDefinitionLink = getCPDefinitionLink();

		if (cpDefinitionLink == null) {
			return 0;
		}

		return cpDefinitionLink.getCPDefinitionLinkId();
	}

	public String getItemSelectorUrl() throws PortalException {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(
				cpRequestHelper.getRenderRequest());

		CPDefinitionItemSelectorCriterion cpDefinitionItemSelectorCriterion =
			new CPDefinitionItemSelectorCriterion();

		cpDefinitionItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.<ItemSelectorReturnType>singletonList(
				new UUIDItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "productDefinitionsSelectItem",
			cpDefinitionItemSelectorCriterion);

		long cpDefinitionId = getCPDefinitionId();

		if (cpDefinitionId > 0) {
			itemSelectorURL.setParameter(
				"cpDefinitionId", String.valueOf(cpDefinitionId));

			String checkedCPDefinitionIds = StringUtil.merge(
				getCheckedCPDefinitionIds(cpDefinitionId));

			String disabledCPDefinitionIds = StringUtil.merge(
				getDisabledCPDefinitionIds(cpDefinitionId));

			itemSelectorURL.setParameter(
				"checkedCPDefinitionIds", checkedCPDefinitionIds);
			itemSelectorURL.setParameter(
				"disabledCPDefinitionIds", disabledCPDefinitionIds);
		}

		return itemSelectorURL.toString();
	}

	@Override
	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = super.getPortletURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "viewCPDefinitionLinks");

		return portletURL;
	}

	@Override
	public SearchContainer<CPDefinitionLink> getSearchContainer()
		throws PortalException {

		if (searchContainer != null) {
			return searchContainer;
		}

		searchContainer = new SearchContainer<>(
			liferayPortletRequest, getPortletURL(), null, null);

		searchContainer.setEmptyResultsMessage(
			"no-related-products-were-found");

		OrderByComparator<CPDefinitionLink> orderByComparator =
			CPDefinitionsPortletUtil.getCPDefinitionLinkOrderByComparator(
				getOrderByCol(), getOrderByType());

		searchContainer.setOrderByCol(getOrderByCol());
		searchContainer.setOrderByComparator(orderByComparator);
		searchContainer.setOrderByType(getOrderByType());
		searchContainer.setRowChecker(getRowChecker());

		int total = _cpDefinitionLinkService.getCPDefinitionLinksCount(
			getCPDefinitionId());

		searchContainer.setTotal(total);

		List<CPDefinitionLink> results =
			_cpDefinitionLinkService.getCPDefinitionLinks(
				getCPDefinitionId(), searchContainer.getStart(),
				searchContainer.getEnd(), orderByComparator);

		searchContainer.setResults(results);

		return searchContainer;
	}

	protected List<CPDefinitionLink> getCPDefinitionLinks(long cpDefinitionId)
		throws PortalException {

		return _cpDefinitionLinkService.getCPDefinitionLinks(cpDefinitionId);
	}

	protected long[] getCheckedCPDefinitionIds(long cpDefinitionId)
		throws PortalException {

		List<Long> cpDefinitionIdsList = new ArrayList<>();

		List<CPDefinitionLink> cpDefinitionLinks = getCPDefinitionLinks(
			cpDefinitionId);

		for (CPDefinitionLink cpDefinitionLink : cpDefinitionLinks) {
			cpDefinitionIdsList.add(cpDefinitionLink.getCPDefinitionId2());
		}

		if (cpDefinitionIdsList.size() > 0) {
			return ArrayUtil.toLongArray(cpDefinitionIdsList);
		}

		return new long[0];
	}

	protected long[] getDisabledCPDefinitionIds(long cpDefinitionId)
		throws PortalException {

		List<Long> cpDefinitionIdsList = new ArrayList<>();

		List<CPDefinitionLink> cpDefinitionLinks = getCPDefinitionLinks(
			cpDefinitionId);

		for (CPDefinitionLink cpDefinitionLink : cpDefinitionLinks) {
			cpDefinitionIdsList.add(cpDefinitionLink.getCPDefinitionId1());
		}

		if (cpDefinitionIdsList.size() > 0) {
			return ArrayUtil.toLongArray(cpDefinitionIdsList);
		}

		return new long[0];
	}

	private CPDefinitionLink _cpDefinitionLink;
	private final CPDefinitionLinkService _cpDefinitionLinkService;
	private final ItemSelector _itemSelector;

}