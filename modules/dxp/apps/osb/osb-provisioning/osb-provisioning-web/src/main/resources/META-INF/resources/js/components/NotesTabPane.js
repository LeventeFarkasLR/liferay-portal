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

import PropTypes from 'prop-types';
import React, {useState} from 'react';

import {useNotes} from '../hooks/notes';
import {
	NOTE_STATUS_APPROVED,
	NOTE_STATUS_ARCHIVED,
	NOTE_TYPE_GENERAL,
	NOTE_TYPE_SALES
} from '../utilities/constants';
import AddNote from './AddNote';
import Note from './Note';

function ApprovedGeneralNotes({notes}) {
	const pinned = notes.filter(note => note.pinned);
	const unpinned = notes.filter(note => !note.pinned);

	return (
		<>
			{!pinned.isEmpty() && (
				<div className="pinned-notes">
					<div className="notes-section-header">
						<svg
							aria-label={Liferay.Language.get(
								'pinned-notes-icon'
							)}
						>
							<use xlinkHref="#pin" />
						</svg>
						{Liferay.Language.get('pinned')}
					</div>

					{pinned.map(note => (
						<Note key={note.id} note={note} />
					))}
				</div>
			)}

			{!unpinned.isEmpty() && (
				<div className="general-notes">
					<div className="notes-section-header">
						{Liferay.Language.get('general')}
					</div>

					{unpinned.map(note => (
						<Note key={note.id} note={note} />
					))}
				</div>
			)}
		</>
	);
}

function ApprovedNotes({addURL, hasArchive, notes, onClick, tabType}) {
	return (
		<>
			<AddNote actionURL={addURL} type={tabType} />

			<div className="notes">
				{tabType === NOTE_TYPE_GENERAL ? (
					<ApprovedGeneralNotes notes={notes} />
				) : (
					notes.map(note => <Note key={note.id} note={note} />)
				)}

				{notes.isEmpty() && (
					<div className="empty-state">
						{tabType === NOTE_TYPE_GENERAL
							? Liferay.Language.get('no-notes-were-found')
							: Liferay.Language.get('no-sales-info-were-found')}
					</div>
				)}
			</div>

			{hasArchive && (
				<button
					className="archive-btn btn btn-link"
					onClick={() => onClick(true)}
				>
					{Liferay.Language.get('view-archived-notes')}{' '}
					<svg
						aria-label={Liferay.Language.get(
							'view-archived-notes-link'
						)}
						role="img"
					>
						<use xlinkHref="#angle-right" />
					</svg>
				</button>
			)}
		</>
	);
}

function ArchivedNotes({notes, onClick}) {
	return (
		<div className="notes">
			<div className="archive-section-header">
				<button
					className="back-btn btn btn-unstyled"
					onClick={() => onClick(false)}
					role="button"
					type="button"
				>
					<svg
						aria-label={Liferay.Language.get('back-to-notes')}
						role="img"
					>
						<use xlinkHref="#angle-left" />
					</svg>

					{Liferay.Language.get('back')}
				</button>

				<h4>{Liferay.Language.get('archive')}</h4>
			</div>

			{notes.map(note => (
				<Note key={note.id} note={note} />
			))}
		</div>
	);
}

function NotesTabPane({addURL, tabType}) {
	const [notes] = useNotes();
	const [viewArchive, setViewArchive] = useState(false);

	const notesForType = notes.toList().filter(note => note.type === tabType);

	const handleViewArchive = bool => {
		setViewArchive(bool);
	};

	const sortDateByRecency = (a, b) =>
		new Date(a.createDate) > new Date(b.createDate) ? -1 : 1;

	const approved = notesForType
		.filter(note => note.status === NOTE_STATUS_APPROVED)
		.sort(sortDateByRecency);

	const archived = notesForType
		.filter(note => note.status === NOTE_STATUS_ARCHIVED)
		.sort(sortDateByRecency);

	return (
		<div className="notes-container">
			{viewArchive ? (
				<ArchivedNotes notes={archived} onClick={handleViewArchive} />
			) : (
				<ApprovedNotes
					addURL={addURL}
					hasArchive={!archived.isEmpty()}
					notes={approved}
					onClick={handleViewArchive}
					tabType={tabType}
				/>
			)}
		</div>
	);
}

NotesTabPane.propTypes = {
	addURL: PropTypes.string,
	tabType: PropTypes.oneOf([NOTE_TYPE_GENERAL, NOTE_TYPE_SALES]).isRequired
};

export default NotesTabPane;
