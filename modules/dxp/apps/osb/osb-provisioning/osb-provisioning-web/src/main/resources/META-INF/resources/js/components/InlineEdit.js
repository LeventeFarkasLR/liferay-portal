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
import React, {useEffect, useState} from 'react';

import {
	FIELD_TYPE_EXTERNAL,
	FIELD_TYPE_SELECT,
	FIELD_TYPE_TEXT,
	FIELD_TYPE_TEXTAREA,
	FIELD_TYPE_TOGGLE,
	NAMESPACE
} from '../utilities/constants';
import {convertDashToEmptyString} from '../utilities/helpers';
import EditableField from './EditableField';

function InlineEdit({
	deleteFn,
	displayAs = 'text',
	displayValue,
	fieldName,
	fieldValue,
	inputStyle = '',
	options = [{label: '', value: ''}],
	saveFn,
	type = FIELD_TYPE_TEXT
}) {
	const [fieldEditable, setFieldEditable] = useState(false);
	const [showEditor, setShowEditor] = useState(false);
	const [value, setValue] = useState(fieldValue);

	useEffect(() => {
		setShowEditor(false);
	}, [fieldValue]);

	const namespacedFieldName = `${NAMESPACE}${fieldName}`;

	function handleChange(event) {
		setValue(event.currentTarget.value);
	}

	function handleClick() {
		saveFn(value);

		setFieldEditable(false);
		setShowEditor(false);
	}

	function handleDelete() {
		deleteFn();
	}

	function handleToggle() {
		setValue(!convertDashToEmptyString(value));
	}

	function handleReset() {
		setFieldEditable(false);
		setShowEditor(false);
		setValue(fieldValue);
	}

	function getDisplayValue() {
		return displayValue ? displayValue : value;
	}

	return (
		<div className={`inline-edit ${showEditor ? 'block' : ''}`}>
			{!showEditor && (
				<div
					onClick={() => setShowEditor(true)}
					onMouseEnter={() => setFieldEditable(true)}
					onMouseLeave={() => setFieldEditable(false)}
				>
					{fieldEditable && (
						<EditableField value={getDisplayValue()} />
					)}

					{!fieldEditable &&
						(displayAs === 'label' ? (
							<Label inputStyle={inputStyle} value={value} />
						) : (
							getDisplayValue()
						))}
				</div>
			)}

			{showEditor && (
				<>
					{type === FIELD_TYPE_EXTERNAL && (
						<div className="external">
							<label
								className="form-control-label"
								htmlFor={namespacedFieldName}
							>
								<input
									className="form-control"
									disabled
									id={namespacedFieldName}
									type="text"
									value={value}
								/>
							</label>

							<button
								className="btn btn-secondary btn-sm"
								onClick={handleClick}
								role="button"
								title={Liferay.Language.get('select')}
								type="button"
							>
								{Liferay.Language.get('select')}
							</button>

							<button
								className="btn btn-icon btn-sm"
								onClick={handleDelete}
								role="button"
								title={Liferay.Language.get('delete')}
								type="button"
							>
								<svg
									aria-label={Liferay.Language.get(
										'delete-field-icon'
									)}
									className="delete-icon"
									role="img"
								>
									<use xlinkHref="#trash" />
								</svg>
							</button>
						</div>
					)}

					{type === FIELD_TYPE_SELECT && (
						<label
							className="form-control-label"
							htmlFor={namespacedFieldName}
						>
							<select
								className="form-control"
								disabled={options.length === 0}
								id={namespacedFieldName}
								onChange={handleChange}
								value={value}
							>
								{options.map(option => (
									<option
										key={option.value}
										value={option.value}
									>
										{option.label}
									</option>
								))}
							</select>
						</label>
					)}

					{type === FIELD_TYPE_TEXT && (
						<label
							className="form-control-label"
							htmlFor={namespacedFieldName}
						>
							<input
								className="form-control"
								id={namespacedFieldName}
								onChange={handleChange}
								type="text"
								value={value}
							/>
						</label>
					)}

					{type === FIELD_TYPE_TEXTAREA && (
						<label
							className="form-control-label"
							htmlFor={namespacedFieldName}
						>
							<textarea
								className="form-control"
								id={namespacedFieldName}
								onChange={handleChange}
								type="text"
								value={value}
							/>
						</label>
					)}

					{type === FIELD_TYPE_TOGGLE && (
						<label
							className="simple-toggle-switch toggle-switch"
							htmlFor={namespacedFieldName}
						>
							<span className="toggle-switch-check-bar">
								<input
									checked={value}
									className="toggle-switch-check"
									id={namespacedFieldName}
									onChange={handleToggle}
									type="checkbox"
									value={value}
								/>
								<span
									aria-hidden="true"
									className="toggle-switch-bar"
								>
									<span className="toggle-switch-handle"></span>
								</span>
							</span>
						</label>
					)}

					<div className="button-row" role="group">
						<button
							className="btn btn-primary btn-sm save-btn"
							disabled={
								fieldValue !== '-' && value === fieldValue
							}
							onClick={handleClick}
							role="button"
							type="button"
						>
							{Liferay.Language.get('save')}
						</button>

						<button
							className="btn btn-secondary btn-sm cancel-btn"
							onClick={handleReset}
							role="button"
							type="button"
						>
							{Liferay.Language.get('cancel')}
						</button>
					</div>
				</>
			)}
		</div>
	);
}

function Label({inputStyle, value}) {
	return <span className={`label ${inputStyle}`}>{value}</span>;
}

InlineEdit.propTypes = {
	deleteFn: PropTypes.func,
	displayAs: PropTypes.oneOf(['label', 'text']),
	displayValue: PropTypes.string,
	fieldName: PropTypes.string,
	fieldValue: PropTypes.string,
	inputStyle: PropTypes.string,
	options: PropTypes.arrayOf(
		PropTypes.shape({
			label: PropTypes.string,
			value: PropTypes.string
		})
	),
	saveFn: PropTypes.func.isRequired,
	type: PropTypes.oneOf([
		FIELD_TYPE_EXTERNAL,
		FIELD_TYPE_SELECT,
		FIELD_TYPE_TEXT,
		FIELD_TYPE_TEXTAREA,
		FIELD_TYPE_TOGGLE
	])
};

export default InlineEdit;
