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

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Note;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.Format;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class NoteDisplay {

	public NoteDisplay(
		PortletRequest portletRequest, PortletResponse portletResponse,
		Note note) {

		_portletRequest = portletRequest;
		_portletResponse = portletResponse;
		_note = note;

		_dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy hh:mm a");
		_httpServletRequest = PortalUtil.getHttpServletRequest(portletRequest);
		_liferayPortletResponse = PortalUtil.getLiferayPortletResponse(
			portletResponse);
	}

	public String getCreateDate() {
		return _dateFormat.format(_note.getDateCreated());
	}

	public String getCreatorName() throws Exception {
		if (Validator.isNotNull(_note.getCreatorName())) {
			return _note.getCreatorName();
		}

		return StringPool.DASH;
	}

	public String getCreatorPortraitURL() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return UserConstants.getPortraitURL(
			themeDisplay.getPathImage(), true, 0, StringPool.BLANK);
	}

	public String getDeleteNoteURL() {
		PortletURL deleteNoteURL = _liferayPortletResponse.createActionURL();

		deleteNoteURL.setParameter(ActionRequest.ACTION_NAME, "/edit_note");
		deleteNoteURL.setParameter(Constants.CMD, Constants.DELETE);
		deleteNoteURL.setParameter("noteKey", _note.getKey());

		return deleteNoteURL.toString();
	}

	public String getFormat() {
		return _note.getFormatAsString();
	}

	public String getHtmlContent() {
		Note.Format format = _note.getFormat();

		if (format == Note.Format.PLAIN) {
			return StringUtil.replace(
				_note.getContent(), CharPool.NEW_LINE, "<br />");
		}

		return _note.getContent();
	}

	public String getKey() {
		return _note.getKey();
	}

	public String getStatus() {
		return _note.getStatusAsString();
	}

	public String getType() {
		return _note.getTypeAsString();
	}

	public String getUpdateNoteURL() {
		PortletURL updateNoteURL = _liferayPortletResponse.createActionURL();

		updateNoteURL.setParameter(ActionRequest.ACTION_NAME, "/edit_note");
		updateNoteURL.setParameter("noteKey", _note.getKey());

		return updateNoteURL.toString();
	}

	public boolean isEdited() {
		if ((_note.getDateModified() != null) &&
			!DateUtil.equals(_note.getDateModified(), _note.getDateCreated())) {

			return true;
		}

		return false;
	}

	public boolean isPinned() {
		if (_note.getPriority() == 1) {
			return true;
		}

		return false;
	}

	private final Format _dateFormat;
	private final HttpServletRequest _httpServletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private final Note _note;
	private final PortletRequest _portletRequest;
	private final PortletResponse _portletResponse;

}