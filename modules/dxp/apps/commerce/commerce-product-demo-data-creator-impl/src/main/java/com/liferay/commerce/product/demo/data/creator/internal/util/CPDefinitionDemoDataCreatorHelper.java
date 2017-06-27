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

package com.liferay.commerce.product.demo.data.creator.internal.util;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionLinkLocalService;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(service = CPDefinitionDemoDataCreatorHelper.class)
public class CPDefinitionDemoDataCreatorHelper
	extends BaseCPDemoDataCreatorHelper {

	public void addCPDefinitions(long userId, long groupId, boolean buildSkus)
		throws Exception {

		ServiceContext serviceContext = getServiceContext(userId, groupId);

		JSONArray catalogJSONArray = getCatalogJSONArray();

		// Product definitions

		for (int i = 0; i < catalogJSONArray.length(); i++) {
			JSONObject productJSONObject = catalogJSONArray.getJSONObject(i);

			String baseSKU = productJSONObject.getString("baseSKU");
			String title = productJSONObject.getString("title");
			String description = productJSONObject.getString("description");
			String urlTitle = productJSONObject.getString("urlTitle");
			String productTypeName = productJSONObject.getString(
				"productTypeName");

			String layoutUuid = _layoutDemoDataCreatorHelper.getLayoutUuid(
				userId, groupId, "Products");

			JSONArray productJSONArray = productJSONObject.getJSONArray(
				"categories");

			long[] assetCategoryIds =
				_assetCategoryDemoDataCreatorHelper.getProductAssetCategoryIds(
					productJSONArray);

			CPDefinition cpDefinition = createCPDefinition(
				userId, groupId, baseSKU, title, description, urlTitle,
				layoutUuid, productTypeName, assetCategoryIds);

			JSONArray cpOptionsJSONArray = productJSONObject.getJSONArray(
				"options");

			_cpOptionDemoDataCreatorHelper.addCPOptions(
				Locale.US, userId, groupId, cpDefinition.getCPDefinitionId(),
				cpOptionsJSONArray);

			JSONArray cpAttachmentFileEntriesJSONArray =
				productJSONObject.getJSONArray("images");

			_cpAttachmentFileEntryDemoDataCreatorHelper.
				addCPAttachmentFileEntries(
					userId, groupId, cpDefinition.getCPDefinitionId(),
					cpAttachmentFileEntriesJSONArray);

			if (buildSkus) {
				_cpInstanceLocalService.buildCPInstances(
					cpDefinition.getCPDefinitionId(), serviceContext);
			}
		}

		// Related products

		for (int i = 0; i < catalogJSONArray.length(); i++) {
			JSONObject productJSONObject = catalogJSONArray.getJSONObject(i);

			String title = productJSONObject.getString("title");

			Collection<CPDefinition> cpDefinitions = _cpDefinitions.values();

			JSONArray cpDefinitionLinksJSONArray =
				productJSONObject.getJSONArray("relatedProducts");

			for (CPDefinition cpDefinition : cpDefinitions) {
				List<Long> cpDefinitionIdsList = new ArrayList<>();

				for (int x = 0; x < cpDefinitionLinksJSONArray.length(); x++) {
					JSONObject relatedProductJSONObject =
						cpDefinitionLinksJSONArray.getJSONObject(x);

					String cpDefinitionEntryTitle =
						relatedProductJSONObject.getString("title");

					if (title.equals(cpDefinition.getTitle("en_US"))) {
						CPDefinition cpDefinitionEntry = getCPDefinitionByTitle(
							cpDefinitionEntryTitle);

						cpDefinitionIdsList.add(
							cpDefinitionEntry.getCPDefinitionId());
					}
					else {
						continue;
					}
				}

				long[] cpDefinitionEntryIds = ArrayUtil.toLongArray(
					cpDefinitionIdsList);

				_cpDefinitionLinkLocalService.updateCPDefinitionLinks(
					cpDefinition.getCPDefinitionId(), cpDefinitionEntryIds, 0,
					serviceContext);
			}
		}
	}

	public void deleteCPDefinitions() throws PortalException {
		Set<Map.Entry<String, CPDefinition>> entrySet =
			_cpDefinitions.entrySet();

		Iterator<Map.Entry<String, CPDefinition>> iterator =
			entrySet.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, CPDefinition> entry = iterator.next();

			_cpDefinitionLocalService.deleteCPDefinition(entry.getValue());

			iterator.remove();
		}
	}

	public CPDefinition getCPDefinitionByTitle(String title)
		throws PortalException {

		return _cpDefinitions.get(title);
	}

	public void init() {
		_cpDefinitions = new HashMap<>();
	}

	@Activate
	protected void activate() {
		init();
	}

	protected CPDefinition createCPDefinition(
			long userId, long groupId, String baseSKU, String title,
			String description, String urlTitle, String layoutUuid,
			String productTypeName, long[] assetCategoryIds)
		throws PortalException {

		CPDefinition cpDefinition = getCPDefinitionByTitle(title);

		if (cpDefinition != null) {
			return cpDefinition;
		}

		ServiceContext serviceContext = getServiceContext(userId, groupId);

		serviceContext.setAssetCategoryIds(assetCategoryIds);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		int displayDateMonth = displayCalendar.get(Calendar.MONTH);
		int displayDateDay = displayCalendar.get(Calendar.DAY_OF_MONTH) - 1;
		int displayDateYear = displayCalendar.get(Calendar.YEAR);
		int displayDateHour = displayCalendar.get(Calendar.HOUR);
		int displayDateMinute = displayCalendar.get(Calendar.MINUTE);
		int displayDateAmPm = displayCalendar.get(Calendar.AM_PM);

		if (displayDateAmPm == Calendar.PM) {
			displayDateHour += 12;
		}

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		int expirationDateMonth = expirationCalendar.get(Calendar.MONTH) + 1;
		int expirationDateDay = expirationCalendar.get(Calendar.DAY_OF_MONTH);
		int expirationDateYear = expirationCalendar.get(Calendar.YEAR);
		int expirationDateHour = expirationCalendar.get(Calendar.HOUR);
		int expirationDateMinute = expirationCalendar.get(Calendar.MINUTE);
		int expirationDateAmPm = expirationCalendar.get(Calendar.AM_PM);

		if (expirationDateAmPm == Calendar.PM) {
			expirationDateHour += 12;
		}

		Map<Locale, String> titleMap = Collections.singletonMap(
			Locale.US, title);
		Map<Locale, String> descriptionMap = Collections.singletonMap(
			Locale.US, description);
		Map<Locale, String> urlTitleMap = Collections.singletonMap(
			Locale.US, urlTitle);

		cpDefinition = _cpDefinitionLocalService.addCPDefinition(
			baseSKU, titleMap, null, descriptionMap, urlTitleMap, layoutUuid,
			productTypeName, null, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, true, serviceContext);

		_cpDefinitions.put(title, cpDefinition);

		return cpDefinition;
	}

	@Deactivate
	protected void deactivate() {
		_cpDefinitions = null;
	}

	protected JSONArray getCatalogJSONArray() throws Exception {
		Class<?> clazz = getClass();

		String catalogPath =
			"com/liferay/commerce/product/demo/data/creator/internal" +
				"/dependencies/catalog.json";

		String catalogJSON = StringUtil.read(
			clazz.getClassLoader(), catalogPath, false);

		JSONArray catalogJSONArray = JSONFactoryUtil.createJSONArray(
			catalogJSON);

		return catalogJSONArray;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPDefinitionDemoDataCreatorHelper.class);

	@Reference
	private AssetCategoryDemoDataCreatorHelper
		_assetCategoryDemoDataCreatorHelper;

	@Reference
	private AssetVocabularyDemoDataCreatorHelper
		_assetVocabularyDemoDataCreatorHelper;

	@Reference
	private CPAttachmentFileEntryDemoDataCreatorHelper
		_cpAttachmentFileEntryDemoDataCreatorHelper;

	@Reference
	private CPDefinitionLinkLocalService _cpDefinitionLinkLocalService;

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

	private Map<String, CPDefinition> _cpDefinitions;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

	@Reference
	private CPOptionDemoDataCreatorHelper _cpOptionDemoDataCreatorHelper;

	@Reference
	private LayoutDemoDataCreatorHelper _layoutDemoDataCreatorHelper;

}