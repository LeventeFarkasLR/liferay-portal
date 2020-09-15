/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

AUI.add(
	'liferay-search-facet-util',
	A => {
		var FacetUtil = {
			addURLParameter(key, value, parameterArray) {
				key = encodeURIComponent(key);
				value = encodeURIComponent(value);

				parameterArray[parameterArray.length] = [key, value].join('=');

				return parameterArray;
			},

			changeSelection(event) {
				var form = event.currentTarget.form;

				if (!form) {
					return;
				}

				var selections = [];

				var formCheckboxes = document.querySelectorAll(
					'#' + form.id + ' input.facet-term'
				);

				Array.prototype.forEach.call(formCheckboxes, checkbox => {
					if (checkbox.checked) {
						selections.push(checkbox.getAttribute('data-term-id'));
					}
				});

				FacetUtil.selectTerms(form, selections);
			},

			clearSelections(event) {
				var form = A.one(event.target).ancestor('form');

				if (!form) {
					return;
				}

				var selections = [];

				FacetUtil.selectTerms(form._node, selections);
			},

			removeStartParameter(startParameterName, queryString) {
				var search = queryString;

				var hasQuestionMark = search[0] === '?';

				if (hasQuestionMark) {
					search = search.substr(1);
				}

				var parameterArray = search.split('&').filter(item => {
					return item.trim() !== '';
				});

				var newParameters = FacetUtil.removeURLParameters(
					startParameterName,
					parameterArray
				);

				search = newParameters.join('&');

				if (hasQuestionMark) {
					search = '?' + search;
				}

				return search;
			},

			removeURLParameters(key, parameterArray) {
				key = encodeURIComponent(key);

				return parameterArray.filter(item => {
					var itemSplit = item.split('=');

					return !(itemSplit && itemSplit[0] === key);
				});
			},

			selectTerms(form, selections) {
				var formParameterNameElement = document.querySelector(
					'#' + form.id + ' input.facet-parameter-name'
				);

				var startParameterNameElement = document.querySelector(
					'#' + form.id + ' input.start-parameter-name'
				);

				var search = document.location.search;

				if (startParameterNameElement) {
					search = FacetUtil.removeStartParameter(
						startParameterNameElement.value,
						search
					);
				}

				search = FacetUtil.updateQueryString(
					formParameterNameElement.value,
					selections,
					search
				);

				document.location.search = search;
			},

			setURLParameter(url, name, value) {
				var parts = url.split('?');

				var address = parts[0];

				var queryString = parts[1];

				if (!queryString) {
					queryString = '';
				}

				queryString = Liferay.Search.FacetUtil.updateQueryString(
					name,
					[value],
					queryString
				);

				return address + '?' + queryString;
			},

			setURLParameters(key, selections, parameterArray) {
				var newParameters = FacetUtil.removeURLParameters(
					key,
					parameterArray
				);

				newParameters = FacetUtil.removeURLParameters(
					key + 'From',
					newParameters
				);

				newParameters = FacetUtil.removeURLParameters(
					key + 'To',
					newParameters
				);

				selections.forEach(item => {
					newParameters = FacetUtil.addURLParameter(
						key,
						item,
						newParameters
					);
				});

				return newParameters;
			},

			updateQueryString(key, selections, queryString) {
				var search = queryString;

				var hasQuestionMark = search[0] === '?';

				if (hasQuestionMark) {
					search = search.substr(1);
				}

				var parameterArray = search.split('&').filter(item => {
					return item.trim() !== '';
				});

				var newParameters = FacetUtil.setURLParameters(
					key,
					selections,
					parameterArray
				);

				search = newParameters.join('&');

				if (hasQuestionMark) {
					search = '?' + search;
				}

				return search;
			}
		};

		Liferay.namespace('Search').FacetUtil = FacetUtil;
	},
	'',
	{
		requires: []
	}
);
