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

import OpenProcessesSummary from '../../../src/main/resources/META-INF/resources/js/components/open-processes-summary/OpenProcessesSummary.es';
import React from 'react';
import renderer from 'react-test-renderer';

test('Should render component', () => {
	const component = renderer.create(<OpenProcessesSummary />);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});
