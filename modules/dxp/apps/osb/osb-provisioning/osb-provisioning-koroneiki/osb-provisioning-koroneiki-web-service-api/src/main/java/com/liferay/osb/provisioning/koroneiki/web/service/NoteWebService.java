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

package com.liferay.osb.provisioning.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Note;

import java.util.List;

/**
 * @author Amos Fong
 */
public interface NoteWebService {

	public Note addNote(
			String agentName, String agentUID, String accountKey, Note note)
		throws Exception;

	public void deleteNote(String agentName, String agentUID, String noteKey)
		throws Exception;

	public List<Note> getNotes(
			String accountKey, String type, int priority, String status, 
			int page, int pageSize)
		throws Exception;

	public Note updateNote(
			String agentName, String agentUID, String noteKey, Note note)
		throws Exception;

}