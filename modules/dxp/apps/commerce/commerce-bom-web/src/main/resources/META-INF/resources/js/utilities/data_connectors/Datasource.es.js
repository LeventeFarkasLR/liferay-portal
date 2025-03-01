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

import {
	createFilterObj,
	getFetchParams,
	serializeParams,
} from './datasourceUtils.es';

export default class Datasource {
	constructor(settings = null) {
		this.initializeProperties();

		if (settings) {
			this.applySettings(settings);
		}

		if (this.readAtCreation) {
			this.read();
		}

		return this;
	}

	applySettings(settings) {
		Object.entries(settings).forEach(([key, value]) => {
			switch (key) {
				default:
					this[key] = value;
					break;
			}
		});
	}

	getFilters(type = 'object') {
		if (type === 'object') {
			return this.filters.reduce(
				(acc, filter) => ({
					...acc,
					[filter.field]: {
						operations: filter.operations,
						value: filter.value,
					},
				}),
				{}
			);
		}

		return this.filters;
	}

	setFilter(field, value, operations = 'eq') {
		if (this.filters && value) {
			const filterFound = this.filters.reduce(
				(found, filter) => found || filter.field === field,
				false
			);

			if (filterFound) {
				this.filters = this.filters.reduce(
					(acc, filter) => [
						...acc,

						// if field already exists, it should update the existing filter
						// otherwise it should add it to filters array

						filter.field === field
							? createFilterObj(field, value, operations)
							: filter,
					],
					[]
				);
			}
			else {
				this.filters = this.filters.concat(
					createFilterObj(field, value, operations)
				);
			}
		}

		if (this.filters && !value) {
			this.filters = this.filters.filter(
				(element) => element.field !== field
			);
		}

		if (!this.filters && value) {
			this.filters = [createFilterObj(field, value, operations)];
		}

		return this.filters;
	}

	updateSettings(newSettings) {
		Object.keys(newSettings).forEach((key) => {
			if (
				this[key] instanceof Object &&
				newSettings[key] instanceof Object
			) {
				this[key] = {
					...this[key],
					...newSettings[key],
				};
			}
			else {
				this[key] = newSettings[key];
			}
		});
	}

	initializeProperties() {
		this.pagination = {
			page: 1,
			pageSize: 20,
		};
		this.readAtCreation = true;
		this.filters = null;

		/*
            {
                name: Config.string(),
                value: Config.oneOfType(
                    [
                        Config.string(),
                        Config.number()
                    ]
                ),
                operator: Config.oneOf(
                    'eq',
                    'neq',
                    'isnull',
                    'isnotnull',
                    'lt',
                    'lte',
                    'gt',
                    'gte',
                    'startswith',
                    'doesnotstartwith',
                    'endswith',
                    'doesnotendwith',
                    'contains',
                    'doesnotcontain',
                    'isempty',
                    'isnotempty'
                )
            }
        */
		this.sorting = null;

		/*
            {
                key: // string,
                order: oneOf(
                    'asc',
                    'desc'
                ) 
            }
        */
	}

	getFetchDataObj() {
		return {
			...(this.filters ? {filters: this.filters} : null),
			...(this.pagination ? {pagination: this.pagination} : null),
			...(this.sorting ? {filters: this.filters} : null),
		};
	}

	getRemoteSettings(crudOperation) {
		const settings = this.remote[crudOperation];
		if (!settings) {
			throw new Error(
				`Behaviour unkown for '${crudOperation}' operations`
			);
		}

		return settings;
	}

	performRequest(crudOperation) {
		const data = this.getFetchDataObj();

		const mappedData =
			!!this.on.mapParameters &&
			this.on.mapParameters(data, crudOperation);

		const serializedData =
			typeof mappedData === 'string'
				? mappedData
				: serializeParams(mappedData || data);

		const remoteSettings = this.getRemoteSettings(crudOperation);

		const fetchSettings = getFetchParams(
			crudOperation,
			remoteSettings,
			serializedData
		);

		return fetch
			.apply(null, fetchSettings)
			.then((response) => response.json())
			.then((response) =>
				this.on.parseResponse
					? this.on.parseResponse(response)
					: response
			)
			.then((jsonResponse) => {
				const func = this.on[crudOperation];
				if (func) {
					return func(jsonResponse, this.component);
				}

				return jsonResponse;
			})
			.catch((error) => {
				if (this.on.error) {
					return this.on.error(error, crudOperation, serializedData);
				}
				throw new Error(error);
			});
	}

	read() {
		return this.performRequest('read').catch(console.error);
	}

	update() {
		return this.performRequest('update').catch(console.error);
	}

	create() {
		return this.performRequest('create').catch(console.error);
	}

	delete() {
		return this.performRequest('delete').catch(console.error);
	}
}
