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

import InlineEdit from '../../src/main/resources/META-INF/resources/js/components/InlineEdit';
import {
	FIELD_TYPE_SELECT,
	FIELD_TYPE_TOGGLE
} from '../../src/main/resources/META-INF/resources/js/utilities/constants';

const mockSubmitFn = jest.fn();

function renderInlineEdit(props) {
	return render(
		<InlineEdit
			fieldName="field1"
			fieldValue="test"
			save={mockSubmitFn}
			{...props}
		/>
	);
}

describe('InlineEdit', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderInlineEdit();

		expect(container).toBeTruthy();
	});

	it('displays an edit icon when hover over the field', () => {
		const {container} = renderInlineEdit();
		const {getByLabelText, getByText} = within(container);

		fireEvent.mouseEnter(getByText('test'));

		getByLabelText('edit-field-icon');
	});

	it('displays a Save and a Cancel button after the field is clicked on', () => {
		const {container} = renderInlineEdit();
		const {getByText} = within(container);

		fireEvent.click(getByText('test'));

		getByText('save');
		getByText('cancel');
	});

	it('hides the Cancel and Save button after the Cancel button is clicked', () => {
		const {container} = renderInlineEdit();
		const {getByText, queryByText} = within(container);

		fireEvent.click(getByText('test'));
		fireEvent.click(getByText('cancel'));

		expect(queryByText('save')).toBeFalsy();
		expect(queryByText('cancel')).toBeFalsy();
	});

	it('restores the field value after the Cancel button is clicked', () => {
		const {container} = renderInlineEdit();
		const {getByText, queryByText} = within(container);

		fireEvent.click(getByText('test'));
		fireEvent.change(container.querySelector('input'), {
			target: {value: 'test123'}
		});

		fireEvent.click(getByText('cancel'));

		expect(queryByText('test123')).toBeFalsy();
		getByText('test');
	});

	it('does not show the edit icon around the field after the Cancel button is clicked', () => {
		const {container} = renderInlineEdit();
		const {getByText, queryByLabelText} = within(container);

		fireEvent.click(getByText('test'));
		fireEvent.click(getByText('cancel'));

		expect(queryByLabelText('edit-field-icon')).toBeFalsy();
	});

	it('calls the provided submit function', () => {
		const {container} = renderInlineEdit();
		const {getByText} = within(container);

		fireEvent.click(getByText('test'));
		fireEvent.click(getByText('save'));

		expect(mockSubmitFn).toHaveBeenCalled();
	});

	it('displays an editable text input correctly', () => {
		const {container} = renderInlineEdit();
		const {getByText} = within(container);

		fireEvent.click(getByText('test'));

		expect(container.querySelector('input')['type']).toBe('text');
	});

	it('displays an editable select input correctly', () => {
		const {container} = renderInlineEdit({
			options: [
				{label: '1', value: '1'},
				{label: '2', value: '2'},
				{label: '3', value: '3'},
				{label: 'test', value: 'test'}
			],
			type: FIELD_TYPE_SELECT
		});
		const {getByText} = within(container);

		fireEvent.click(getByText('test'));

		const select = container.querySelector('select');

		expect(select).toBeTruthy();
		getByText('1');
		getByText('2');
		getByText('3');
	});

	it('displays an editable toggle correctly', () => {
		const {container} = renderInlineEdit({type: FIELD_TYPE_TOGGLE});
		const {getByText} = within(container);

		fireEvent.click(getByText('test'));

		expect(container.querySelector('input')['type']).toBe('checkbox');
	});
});
