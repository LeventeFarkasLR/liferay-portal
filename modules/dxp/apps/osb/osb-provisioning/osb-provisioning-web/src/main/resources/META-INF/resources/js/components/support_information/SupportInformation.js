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
import React from 'react';

import Instructions from './Instructions';
import SupportDetails from './SupportDetails';

function SupportInformation({
	account,
	instructions,
	languageId,
	languageList,
	regionNames,
	updateAccountURL,
	updateInstructionsURL,
	updateLanguageIdURL
}) {
	return (
		<>
			<SupportDetails
				account={account}
				languageId={languageId}
				languageList={languageList}
				regionNames={regionNames}
				updateAccountURL={updateAccountURL}
				updateLanguageIdURL={updateLanguageIdURL}
			/>

			<Instructions
				instructions={instructions}
				updateInstructionsURL={updateInstructionsURL}
			/>
		</>
	);
}

SupportInformation.propTypes = {
	account: PropTypes.shape({
		code: PropTypes.string,
		editAccountURL: PropTypes.string,
		key: PropTypes.string,
		name: PropTypes.string,
		region: PropTypes.string,
		status: PropTypes.string,
		tier: PropTypes.string
	}),
	instructions: PropTypes.string,
	languageId: PropTypes.string,
	languageList: PropTypes.arrayOf(
		PropTypes.shape({
			languageId: PropTypes.string,
			languageName: PropTypes.string
		})
	),
	regionNames: PropTypes.arrayOf(PropTypes.string),
	updateAccountURL: PropTypes.string,
	updateInstructionsURL: PropTypes.string,
	updateLanguageIdURL: PropTypes.string
};

export default SupportInformation;
