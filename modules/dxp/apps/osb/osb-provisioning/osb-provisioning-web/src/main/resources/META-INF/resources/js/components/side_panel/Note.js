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

import ClaySticker from '@clayui/sticker';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import {NoteRecord, useNotes} from '../../hooks/notes';
import {
	NOTE_PRIORITY_PINNED,
	NOTE_PRIORITY_UNPINNED,
	NOTE_STATUS_APPROVED,
	NOTE_STATUS_ARCHIVED
} from '../../utilities/constants';
import {postData} from '../../utilities/helpers';
import ActionMenu from './ActionMenu';
import AddNote from './AddNote';
import PanelDropdownMenu from './PanelDropdownMenu';

function Note({note}) {
	const [editNote, setEditNote] = useState(false);
	const [showActionMenu, setShowActionMenu] = useState(false);
	const [, {archiveNote, pinNote}] = useNotes();

	const {content, format, id, pinned, status, type, updateURL} = note;

	const noteData = prop => {
		return {
			content,
			format,
			priority: pinned ? NOTE_PRIORITY_PINNED : NOTE_PRIORITY_UNPINNED,
			status,
			type,
			...prop
		};
	};

	function handleArchive() {
		const formData = noteData({
			status:
				status === NOTE_STATUS_APPROVED
					? NOTE_STATUS_ARCHIVED
					: NOTE_STATUS_APPROVED
		});

		postData(updateURL, formData, 'formData')
			.then(({data}) => {
				const noteFromAPI = NoteRecord({
					id: data.note.key,
					status: data.note.status
				});

				archiveNote(noteFromAPI.id, noteFromAPI.status);
			})
			.catch(err => console.error(err));
	}

	function handleCancel() {
		setEditNote(false);
	}

	function handleEdit() {
		setEditNote(true);
	}

	function handlePinning() {
		const formData = noteData({
			priority: pinned ? NOTE_PRIORITY_UNPINNED : NOTE_PRIORITY_PINNED
		});

		postData(updateURL, formData, 'formData')
			.then(({data}) => {
				const noteFromAPI = NoteRecord({
					id: data.note.key,
					pinned: data.note.pinned
				});

				pinNote(noteFromAPI.id, noteFromAPI.pinned);
			})
			.catch(err => console.error(err));
	}

	return (
		<div
			className="note"
			onMouseEnter={() =>
				setShowActionMenu(status === NOTE_STATUS_APPROVED)
			}
			onMouseLeave={() => setShowActionMenu(false)}
		>
			<div className="note-header">
				<div className="note-metadata">
					<ClaySticker
						displayType="secondary"
						shape="circle"
						size="md"
					>
						<img
							alt={Liferay.Language.get('note-author-avatar')}
							className="sticker-img"
							src={note.creatorPortraitURL}
						/>
					</ClaySticker>

					<div className="metadata">
						<h4 className="note-author">{note.creatorName}</h4>
						<div className="note-create-date">
							{note.createDate}{' '}
							{note.edited && (
								<span className="edited">
									({Liferay.Language.get('edited')})
								</span>
							)}
						</div>
					</div>
				</div>

				<div className="note-menu">
					{showActionMenu && (
						<ActionMenu
							onEdit={handleEdit}
							onPinning={handlePinning}
							pinned={pinned}
							tabType={type}
						/>
					)}

					<PanelDropdownMenu
						id={id}
						onArchive={handleArchive}
						onEdit={handleEdit}
						onPinning={handlePinning}
						pinned={pinned}
						status={status}
						tabType={type}
					/>
				</div>
			</div>

			{editNote ? (
				<AddNote
					actionURL={updateURL}
					content={content}
					format={format}
					id={id}
					onCancel={handleCancel}
					pinned={pinned}
					status={status}
					type={type}
				/>
			) : (
				<section
					className="note-content"
					dangerouslySetInnerHTML={{__html: content}}
				/>
			)}
		</div>
	);
}

Note.propTypes = {
	note: PropTypes.instanceOf(NoteRecord)
};

export default Note;
