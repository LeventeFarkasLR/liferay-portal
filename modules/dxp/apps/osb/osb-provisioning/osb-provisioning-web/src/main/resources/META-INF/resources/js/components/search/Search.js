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

import ClayAutocomplete from '@clayui/autocomplete';
import ClayDropDown from '@clayui/drop-down';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import {NAMESPACE} from '../../utilities/constants';
import {request} from '../../utilities/helpers';

const MAX_RESULTS = 7;

function Search({accountsHomeURL = '', resourceURL}) {
	const [error, setError] = useState(false);
	const [keywords, setKeywords] = useState('');
	const [results, setResults] = useState([]);

	function buildSearchResultsURL() {
		return `${accountsHomeURL}&${NAMESPACE}keywords=${keywords}`;
	}

	function handleOnChange(event) {
		const newValue = event.target.value;

		setKeywords(newValue);

		request(resourceURL, {
			autocompleteKeywords: newValue,
			maxResults: MAX_RESULTS
		})
			.then(({data}) => {
				if (data.length === 0) {
					setError(true);
				}
				else {
					setError(false);
					setResults(data);
				}
			})
			.catch(err => {
				setError(true);

				console.error(err);
			});
	}

	return (
		<ClayAutocomplete>
			<ClayAutocomplete.Input
				className="search-input"
				onChange={handleOnChange}
				placeholder={Liferay.Language.get('search-accounts')}
				value={keywords}
			/>

			<a
				className="btn btn-default search-btn"
				href={buildSearchResultsURL()}
				role="button"
			>
				<svg
					aria-hidden="true"
					aria-label={Liferay.Language.get('search-icon')}
					className="lexicon-icon lexicon-icon-search"
					role="image"
				>
					<use xlinkHref="#search" />
				</svg>
			</a>

			<ClayAutocomplete.DropDown active={keywords}>
				{error && (
					<ul className="list-unstyled">
						<ClayDropDown.Item className="disabled">
							{Liferay.Language.get('no-results-were-found')}
						</ClayDropDown.Item>
					</ul>
				)}

				{!error && (
					<>
						<ClayDropDown.ItemList>
							{results.map(result => (
								<ClayAutocomplete.Item
									href={result.url}
									key={result.key}
									match={keywords}
									value={result.name}
								/>
							))}
						</ClayDropDown.ItemList>

						{results.length === MAX_RESULTS && (
							<a
								className="all-results dropdown-item"
								href={buildSearchResultsURL()}
							>
								{Liferay.Language.get('see-all-results')}
							</a>
						)}
					</>
				)}
			</ClayAutocomplete.DropDown>
		</ClayAutocomplete>
	);
}

Search.propTypes = {
	accountsHomeURL: PropTypes.string.isRequired,
	resourceURL: PropTypes.string.isRequired
};

export default Search;
