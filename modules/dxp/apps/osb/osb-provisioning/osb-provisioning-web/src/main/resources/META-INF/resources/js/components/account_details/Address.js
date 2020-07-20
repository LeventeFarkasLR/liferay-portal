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

import ClayList from '@clayui/list';
import PropTypes from 'prop-types';
import React, {useEffect, useRef, useState} from 'react';

import {
	FIELD_TYPE_NONEDITABLE,
	FIELD_TYPE_SELECT,
	FIELD_TYPE_TEXT,
	FIELD_TYPE_TOGGLE,
	NAMESPACE
} from '../../utilities/constants';
import {convertDashToEmptyString} from '../../utilities/helpers';
import EditableField from '../EditableField';
import IconButton from '../IconButton';

function Address({accountKey, addURL, address, count, countryOptions}) {
	const [countryId, setCountryId] = useState(
		getFieldId(countryOptions, address.addressCountry)
	);
	const [editable, setEditable] = useState(false);
	const [regionOptions, setRegionOptions] = useState([]);
	const [zipCode, setZipCode] = useState(
		convertDashToEmptyString(address.postalCode)
	);
	const [zipRequired, setZipRequired] = useState(false);
	const formRef = useRef();

	const regionId = getFieldId(regionOptions, address.addressRegion);

	useEffect(() => {
		setCountryId(getFieldId(countryOptions, address.addressCountry));
	}, [address, countryOptions]);

	useEffect(() => {
		Liferay.Service('/region/get-regions', {
			active: true,
			countryId: Number(countryId)
		})
			.then(regions => {
				const options = regions.map(region => {
					return {
						label: region.name,
						value: region.regionId
					};
				});

				setRegionOptions(options);
			})
			.catch(err => console.error(err));
	}, [countryId]);

	function getFieldId(fieldOptions, currentValue) {
		const currentField = fieldOptions.find(
			option => option.label === currentValue
		);

		if (currentField) {
			return currentField.value;
		}

		return '';
	}

	function getZipRequirement(id) {
		const currentCountry = countryOptions.find(
			option => option.value === id
		);

		return currentCountry ? currentCountry.zipRequired : false;
	}

	function handleCancel() {
		location.reload();
	}

	function handleCountryUpdate(id) {
		setCountryId(id);
		setZipRequired(getZipRequirement(id));
	}

	function handleOnClick(bool) {
		setEditable(bool);
	}

	function handlePostalCodeUpdate(value) {
		setZipCode(value);
	}

	function handleSave() {
		formRef.current.submit();
	}

	function setFieldType(fieldType = FIELD_TYPE_TEXT) {
		if (address.id) {
			return fieldType;
		}

		return FIELD_TYPE_NONEDITABLE;
	}

	return (
		<form
			action={address.editPostalAddressURL}
			key={address.id}
			method="post"
			ref={formRef}
		>
			<input
				name={`${NAMESPACE}accountKey`}
				type="hidden"
				value={accountKey}
			/>
			<input
				name={`${NAMESPACE}addressType`}
				type="hidden"
				value={convertDashToEmptyString(address.addressType)}
			/>
			<input
				name={`${NAMESPACE}mailing`}
				type="hidden"
				value={convertDashToEmptyString(address.mailing)}
			/>

			<ClayList>
				<ClayList.Header>
					{Liferay.Language.get('address')} {count}
				</ClayList.Header>

				<AddressField
					editable={editable}
					fieldLabel={Liferay.Language.get('street-1')}
					fieldName="streetAddressLine1"
					onClick={handleOnClick}
					readOnly={address.readOnly}
					type={setFieldType()}
					value={address.streetAddressLine1}
				/>

				<AddressField
					editable={editable}
					fieldLabel={Liferay.Language.get('city')}
					fieldName="addressLocality"
					onClick={handleOnClick}
					readOnly={address.readOnly}
					type={setFieldType()}
					value={address.addressLocality}
				/>

				<AddressField
					editable={editable}
					fieldLabel={Liferay.Language.get('street-2')}
					fieldName="streetAddressLine2"
					onClick={handleOnClick}
					readOnly={address.readOnly}
					type={setFieldType()}
					value={address.streetAddressLine2}
				/>

				<AddressField
					displayValue={address.addressRegion}
					editable={editable}
					fieldLabel={Liferay.Language.get('state-province')}
					fieldName="addressRegionId"
					onClick={handleOnClick}
					options={regionOptions}
					readOnly={address.readOnly}
					type={setFieldType(FIELD_TYPE_SELECT)}
					value={regionId}
				/>

				<AddressField
					editable={editable}
					fieldLabel={Liferay.Language.get('street-3')}
					fieldName="streetAddressLine3"
					onClick={handleOnClick}
					readOnly={address.readOnly}
					type={setFieldType()}
					value={address.streetAddressLine3}
				/>

				<AddressField
					editable={editable}
					fieldLabel={Liferay.Language.get('postal-code')}
					fieldName="addressZip"
					onClick={handleOnClick}
					readOnly={address.readOnly}
					required={zipRequired}
					type={setFieldType()}
					updateFn={handlePostalCodeUpdate}
					value={address.postalCode}
				/>

				<AddressField
					displayValue={address.addressCountry}
					editable={editable}
					fieldLabel={Liferay.Language.get('country')}
					fieldName="addressCountryId"
					onClick={handleOnClick}
					options={countryOptions}
					readOnly={address.readOnly}
					type={setFieldType(FIELD_TYPE_SELECT)}
					updateFn={handleCountryUpdate}
					value={countryId}
				/>

				<AddressField
					displayValue={
						address.primary
							? Liferay.Language.get('yes')
							: Liferay.Language.get('no')
					}
					editable={editable}
					fieldLabel={Liferay.Language.get('primary')}
					fieldName="addressPrimary"
					onClick={handleOnClick}
					readOnly={address.readOnly}
					type={setFieldType(FIELD_TYPE_TOGGLE)}
					value={address.primary}
				/>

				<ClayList.Item
					className={`address-controls ${editable ? 'editing' : ''}`}
					flex
				>
					{editable && (
						<div className="btn-group" role="group">
							<div className="btn-group-item">
								<button
									className="btn btn-primary btn-sm save-btn"
									disabled={zipRequired && !zipCode}
									onClick={handleSave}
									role="button"
									type="button"
								>
									{Liferay.Language.get('save')}
								</button>
							</div>

							<div className="btn-group-item">
								<button
									className="btn btn-secondary btn-sm cancel-btn"
									onClick={handleCancel}
									role="button"
									type="button"
								>
									{Liferay.Language.get('cancel')}
								</button>
							</div>
						</div>
					)}

					<div className="btn-group" role="group">
						<div className="btn-group-item">
							<a
								className="add-address btn btn-secondary nav-btn nav-btn-monospaced"
								href={addURL}
								title={Liferay.Language.get('add')}
							>
								<svg
									aria-label={Liferay.Language.get('add')}
									className="lexicon-icon"
									role="img"
								>
									<use xlinkHref="#plus" />
								</svg>
							</a>
						</div>

						{!!address.deletePostalAddressURL && (
							<div className="btn-group-item">
								<IconButton
									cssClass="btn-secondary delete-address nav-btn nav-btn-monospaced"
									labelName={Liferay.Language.get('delete')}
									onClick={() => {
										if (
											window.confirm(
												Liferay.Language.get(
													'are-you-sure-you-want-to-delete-this-address'
												)
											)
										) {
											window.location.assign(
												address.deletePostalAddressURL
											);
										}
									}}
									svgId="#hr"
									title={Liferay.Language.get('delete')}
								/>
							</div>
						)}
					</div>
				</ClayList.Item>
			</ClayList>
		</form>
	);
}

Address.propTypes = {
	accountKey: PropTypes.string,
	addURL: PropTypes.string,
	address: PropTypes.shape({
		addressCountry: PropTypes.string,
		addressLocality: PropTypes.string,
		deletePostalAddressURL: PropTypes.string,
		editPostalAddressURL: PropTypes.string,
		id: PropTypes.string,
		postalCode: PropTypes.string,
		primary: PropTypes.bool,
		streetAddressLine1: PropTypes.string,
		streetAddressLine2: PropTypes.string,
		streetAddressLine3: PropTypes.string
	}),
	count: PropTypes.number,
	countryOptions: PropTypes.arrayOf(
		PropTypes.shape({
			label: PropTypes.string,
			value: PropTypes.string,
			zipRequired: PropTypes.bool
		})
	)
};

function AddressField({
	displayValue,
	editable = false,
	fieldLabel,
	fieldName,
	onClick,
	options = [],
	readOnly,
	required = false,
	type = FIELD_TYPE_TEXT,
	updateFn,
	value
}) {
	const [fieldEditable, setFieldEditable] = useState(false);
	const [fieldValue, setFieldValue] = useState(value);

	const namespacedFieldName = `${NAMESPACE}${fieldName}`;

	useEffect(() => {
		setFieldValue(value);
	}, [value]);

	function handleChange(event) {
		const currentTarget = event.currentTarget;

		setFieldValue(currentTarget.value);

		if (updateFn) {
			updateFn(currentTarget.value);
		}
	}

	function handleToggle() {
		setFieldValue(!fieldValue);
	}

	function getDisplayValue() {
		return displayValue ? displayValue : fieldValue;
	}

	return (
		<ClayList.Item flex>
			<div className="detail-field">
				<ClayList.ItemTitle>
					{fieldLabel}{' '}
					{required && <span className="text-warning">{'*'}</span>}
				</ClayList.ItemTitle>

				{readOnly && (
					<div className="list-group-text">{getDisplayValue()}</div>
				)}

				{!readOnly && (
					<div className="list-group-text">
						{!editable && (
							<div className="inline-edit">
								<div
									onClick={() => onClick(true)}
									onMouseEnter={() => setFieldEditable(true)}
									onMouseLeave={() => setFieldEditable(false)}
								>
									{fieldEditable ? (
										<EditableField
											value={getDisplayValue()}
										/>
									) : (
										getDisplayValue()
									)}
								</div>
							</div>
						)}

						{editable && type === FIELD_TYPE_SELECT && (
							<label
								className="form-control-label"
								htmlFor={namespacedFieldName}
							>
								<select
									className="form-control"
									disabled={options.length === 0}
									id={namespacedFieldName}
									name={namespacedFieldName}
									onChange={handleChange}
									value={convertDashToEmptyString(fieldValue)}
								>
									{options.map((option, index) => (
										<option
											key={option.value || index}
											value={option.value}
										>
											{option.label}
										</option>
									))}
								</select>
							</label>
						)}

						{editable && type === FIELD_TYPE_TEXT && (
							<label
								className="form-control-label"
								htmlFor={namespacedFieldName}
							>
								<input
									className="form-control"
									id={namespacedFieldName}
									name={namespacedFieldName}
									onChange={handleChange}
									type="text"
									value={convertDashToEmptyString(fieldValue)}
								/>
							</label>
						)}

						{editable && type === FIELD_TYPE_TOGGLE && (
							<label
								className="simple-toggle-switch toggle-switch"
								htmlFor={namespacedFieldName}
							>
								<span className="toggle-switch-check-bar">
									<input
										checked={fieldValue}
										className="toggle-switch-check"
										id={namespacedFieldName}
										name={namespacedFieldName}
										onChange={handleToggle}
										type="checkbox"
										value={fieldValue}
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
					</div>
				)}
			</div>
		</ClayList.Item>
	);
}

export default Address;
