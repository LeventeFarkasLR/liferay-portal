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
import fuzzy from 'fuzzy';
import debounce from 'lodash.debounce';
import PropTypes from 'prop-types';
import React, {useRef, useState} from 'react';

import {NAMESPACE} from '../../utilities/constants';
import {request} from '../../utilities/helpers';

const MAX_RESULTS = 7;

const AutocompleteItem = React.forwardRef(
	(
		{innerRef, match = '', secondaryValue = '', value, ...otherProps},
		ref
	) => {
		const fuzzyMatch = fuzzy.match(match, value);

		return (
			<ClayDropDown.Item {...otherProps} innerRef={innerRef} ref={ref}>
				<>
					{match && fuzzyMatch ? (
						<div className="main-item">{fuzzyMatch.rendered}</div>
					) : (
						<div className="main-item">{value}</div>
					)}

					{!!secondaryValue && (
						<div className="secondary-information">
							{secondaryValue}
						</div>
					)}
				</>
			</ClayDropDown.Item>
		);
	}
);

AutocompleteItem.propTypes = {
	href: PropTypes.string,
	match: PropTypes.string,
	secondaryValue: PropTypes.string,
	value: PropTypes.string
};

function Search({accountsHomeURL = '', resourceURL}) {
	const [error, setError] = useState(false);
	const [keywords, setKeywords] = useState('');
	const [results, setResults] = useState([]);

	const {current: requestSearchResults} = useRef(
		debounce(value => {
			request(resourceURL, {
				autocompleteKeywords: value,
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
		}, 200)
	);

	function buildSearchResultsURL() {
		return `${accountsHomeURL}&${NAMESPACE}keywords=${keywords}`;
	}

	function handleKeyDown(event) {
		if (event.keyCode === 13) {
			window.location.assign(buildSearchResultsURL());
		}
	}

	function handleOnChange(event) {
		setKeywords(event.target.value);

		requestSearchResults(event.target.value);
	}

	return (
		<ClayAutocomplete>
			<ClayAutocomplete.Input
				className="search-input"
				onChange={handleOnChange}
				onKeyDown={handleKeyDown}
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
								<AutocompleteItem
									href={result.url}
									key={result.key}
									match={keywords}
									secondaryValue={result.code}
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
