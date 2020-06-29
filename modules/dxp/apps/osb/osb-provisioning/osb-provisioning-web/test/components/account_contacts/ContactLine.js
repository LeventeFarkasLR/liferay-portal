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

import {cleanup, fireEvent, render, within} from '@testing-library/react';
import React from 'react';

import {ContactsContext} from '../../../src/main/resources/META-INF/resources/js/components/account_contacts/AccountAddContacts';
import ContactLine from '../../../src/main/resources/META-INF/resources/js/components/account_contacts/ContactLine';

const mockSetContactRoleKeysFn = jest.fn();
const mockSetEmailAddressFn = jest.fn();

function renderContactLine(props) {
	const allContactRoles = [
		{key: 'KEY-100', name: 'Manager'},
		{key: 'KEY-101', name: 'Member'},
		{key: 'KEY-102', name: 'Analyst'},
		{key: 'KEY-103', name: 'Designer'}
	];

	return render(
		<table>
			<tbody>
				<ContactsContext.Provider value={allContactRoles}>
					<ContactLine
						accountName={'Test Account'}
						contactRoleKeys={[]}
						disableEmail={false}
						emailAddress={''}
						setContactRoleKeys={mockSetContactRoleKeysFn}
						setEmailAddress={mockSetEmailAddressFn}
						{...props}
					/>
				</ContactsContext.Provider>
			</tbody>
		</table>
	);
}

describe('AccountAddress', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderContactLine();

		expect(container).toBeTruthy();
	});

	it('displays account name', () => {
		const {getByText} = renderContactLine();

		getByText('Test Account');
	});

	it('displays full name and email if provided', () => {
		const {getByText} = renderContactLine({
			contactRoleKeys: ['KEY-100'],
			disableEmail: true,
			emailAddress: 'test1@liferay.com',
			userFullName: 'Test One'
		});

		getByText('Test One');
		getByText('test1@liferay.com');
	});

	it('displays email as an input if enabled', () => {
		const {container} = renderContactLine();

		expect(container.querySelectorAll('input')[0].type).toBe('text');
	});

	it('hides email as an input if disabled', () => {
		const {container} = renderContactLine({
			contactRoleKeys: ['KEY-100'],
			disableEmail: true,
			emailAddress: 'test1@liferay.com',
			userFullName: 'Test One'
		});

		expect(container.querySelectorAll('input')[0].type).toBe('hidden');
	});

	it('does not display full name if email is enabled', () => {
		const {queryByText} = renderContactLine({
			userFullName: 'Test One'
		});

		expect(queryByText('Test One')).toBeFalsy();
	});

	it('displays contact roles if provided', () => {
		const {container} = renderContactLine({
			contactRoleKeys: ['KEY-100', 'KEY-101'],
			disableEmail: true,
			emailAddress: 'test1@liferay.com',
			userFullName: 'Test One'
		});

		const {getByText} = within(
			container.querySelector('.input-group-item')
		);

		getByText('Manager');
		getByText('Member');
	});

	it('calls function when contact roles are selected from dropdown', () => {
		const {getByText, getByTitle} = renderContactLine();

		fireEvent.click(getByTitle('add-roles'));

		fireEvent.click(getByText('Manager'));

		expect(mockSetContactRoleKeysFn).toHaveBeenCalled();
	});

	it('calls function when contact roles are removed', () => {
		const {queryAllByTitle} = renderContactLine({
			contactRoleKeys: ['KEY-100', 'KEY-101'],
			disableEmail: true,
			emailAddress: 'test1@liferay.com',
			userFullName: 'Test One'
		});

		fireEvent.click(queryAllByTitle('delete')[0]);

		expect(mockSetContactRoleKeysFn).toHaveBeenCalled();
	});
});
