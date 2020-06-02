/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

export const NAMESPACE = window.ProvisioningConstants.namespace;

// Note properties

export const NOTE_FORMAT_HTML = window.ProvisioningConstants.noteFormat.html;
export const NOTE_FORMAT_PLAIN =
	window.ProvisioningConstants.noteFormat.plaintext;
export const NOTE_PRIORITY_PINNED = 1;
export const NOTE_PRIORITY_UNPINNED = 2;
export const NOTE_STATUS_APPROVED =
	window.ProvisioningConstants.noteStatus.approved;
export const NOTE_STATUS_ARCHIVED =
	window.ProvisioningConstants.noteStatus.archived;
export const NOTE_TYPE_GENERAL = window.ProvisioningConstants.noteType.general;
export const NOTE_TYPE_SALES = window.ProvisioningConstants.noteType.sales;

// Action types for interacting with a Note

export const ADD_NOTE = 'addNote';
export const ARCHIVE_NOTE = 'archiveNote';
export const EDIT_NOTE = 'editNote';
export const PIN_NOTE = 'pinNote';


