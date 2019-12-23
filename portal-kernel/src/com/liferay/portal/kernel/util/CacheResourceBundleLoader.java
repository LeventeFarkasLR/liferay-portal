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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Carlos Sierra Andrés
 */
public class CacheResourceBundleLoader implements ResourceBundleLoader {

	public static void notifyResourceBundleModification() {
		_resourceBundlesModifiedCount.incrementAndGet();
	}

	public CacheResourceBundleLoader(
		ResourceBundleLoader resourceBundleLoader) {

		_resourceBundleLoader = resourceBundleLoader;
	}

	@Override
	public ResourceBundle loadResourceBundle(Locale locale) {
		ResourceBundle resourceBundle = null;

		long resourceBundlesModifiedCount =
			_resourceBundlesModifiedCount.longValue();

		if (_cacheModifiedCount.longValue() >= resourceBundlesModifiedCount) {
			resourceBundle = _resourceBundles.get(locale);
		}

		if (resourceBundle == _nullResourceBundle) {
			return null;
		}

		if (resourceBundle == null) {
			try {
				resourceBundle = _resourceBundleLoader.loadResourceBundle(
					locale);
			}
			catch (Exception exception) {
				if (_log.isDebugEnabled()) {
					_log.debug(exception, exception);
				}
			}

			if (resourceBundle == null) {
				_resourceBundles.put(locale, _nullResourceBundle);
			}
			else {
				_resourceBundles.put(locale, resourceBundle);
			}

			_cacheModifiedCount.set(resourceBundlesModifiedCount);
		}

		return resourceBundle;
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #loadResourceBundle(Locale)}
	 */
	@Deprecated
	@Override
	public ResourceBundle loadResourceBundle(String languageId) {
		return ResourceBundleLoader.super.loadResourceBundle(languageId);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CacheResourceBundleLoader.class);

	private static final ResourceBundle _nullResourceBundle =
		new ResourceBundle() {

			@Override
			public Enumeration<String> getKeys() {
				throw new UnsupportedOperationException();
			}

			@Override
			protected Object handleGetObject(String key) {
				throw new UnsupportedOperationException();
			}

		};

	private static final AtomicLong _resourceBundlesModifiedCount =
		new AtomicLong(0);

	private final AtomicLong _cacheModifiedCount = new AtomicLong(0);
	private final ResourceBundleLoader _resourceBundleLoader;
	private final Map<Locale, ResourceBundle> _resourceBundles =
		new ConcurrentHashMap<>();

}