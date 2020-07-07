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

package com.liferay.asset.taglib.servlet.taglib;

import com.liferay.asset.taglib.internal.servlet.ServletContextUtil;
import com.liferay.asset.taglib.internal.servlet.taglib.util.AssetEntryUsagesTaglibUtil;
import com.liferay.fragment.constants.FragmentActionKeys;
import com.liferay.layout.content.page.editor.constants.ContentPageEditorWebKeys;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Eudaldo Alonso
 */
public class AssetEntryUsagesTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		AssetEntryUsagesTaglibUtil.recordAssetEntryUsage(_className, _classPK);

		request.setAttribute(
			ContentPageEditorWebKeys.FRAGMENT_COLLECTION_CONTRIBUTOR_TRACKER,
			ServletContextUtil.getFragmentCollectionContributorTracker());
		request.setAttribute(
			FragmentActionKeys.FRAGMENT_RENDERER_TRACKER,
			ServletContextUtil.getFragmentRendererTracker());

		return super.doStartTag();
	}

	public String getClassName() {
		return _className;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_className = null;
		_classPK = 0;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		httpServletRequest.setAttribute(
			"liferay-asset:asset-entry-usages:className", _className);
		httpServletRequest.setAttribute(
			"liferay-asset:asset-entry-usages:classPK",
			String.valueOf(_classPK));
	}

	private static final String _PAGE = "/asset_entry_usages/page.jsp";

	private String _className;
	private long _classPK;

}