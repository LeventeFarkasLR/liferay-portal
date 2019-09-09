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

import React, {createContext, useContext, useEffect, useState} from 'react';
import {buildFallbackItems} from '../../../../shared/components/filter/util/filterEvents.es';
import {compareArrays} from '../../../../shared/util/array.es';
import moment from 'moment';
import {TimeRangeContext} from './TimeRangeStore.es';
import {usePrevious} from '../../../../shared/util/hooks.es';

const useVelocityUnit = velocityUnitKeys => {
	const {getSelectedTimeRange} = useContext(TimeRangeContext);
	const [velocityUnits, setVelocityUnits] = useState([]);

	const timeRange = getSelectedTimeRange();

	const fetchData = timeRange => {
		const {dateEnd, dateStart} = timeRange;

		const daysDiff = moment
			.utc(dateEnd)
			.diff(moment.utc(dateStart), 'days');

		const velocityUnits = (
			Object.keys(velocityUnitsMap)
				.filter(key => daysDiff < key)
				.map(key => velocityUnitsMap[key])[0] || [asDefault(yearsUnit)]
		).map(unit => ({
			...unit,
			active: velocityUnitKeys.includes(unit.key)
		}));

		setVelocityUnits(velocityUnits);
	};

	const defaultVelocityUnit = getDefaultVelocityUnit(velocityUnits);

	const getSelectedVelocityUnit = fallbackKeys => {
		if (!velocityUnits || !velocityUnits.length) {
			return buildFallbackItems(fallbackKeys);
		}

		const selectedVelocityUnits = velocityUnits.filter(item => item.active);

		return selectedVelocityUnits.length ? selectedVelocityUnits[0] : null;
	};

	const updateData = () => {
		setVelocityUnits(
			velocityUnits.map(velocityUnit => ({
				...velocityUnit,
				active: velocityUnitKeys.includes(velocityUnit.key)
			}))
		);
	};

	useEffect(() => {
		if (timeRange) {
			fetchData(timeRange);
		}
	}, [fetchData, timeRange]);

	const previousKeys = usePrevious(velocityUnitKeys);

	useEffect(() => {
		const filterChanged = !compareArrays(previousKeys, velocityUnitKeys);

		if (filterChanged && velocityUnits.length) {
			updateData();
		}
	}, [previousKeys, updateData, velocityUnitKeys, velocityUnits.length]);

	return {
		defaultVelocityUnit,
		getSelectedVelocityUnit,
		velocityUnits
	};
};

const asDefault = velocityUnit => {
	return {
		...velocityUnit,
		active: true,
		defaultVelocityUnit: true
	};
};

const getDefaultVelocityUnit = velocityUnits => {
	const defaultVelocityUnits = velocityUnits.filter(
		velocityUnit => velocityUnit.defaultVelocityUnit
	);

	return defaultVelocityUnits.length ? defaultVelocityUnits[0] : null;
};

const daysUnit = {
	key: 'Days',
	name: Liferay.Language.get('inst-day')
};

const hoursUnit = {
	key: 'Hours',
	name: Liferay.Language.get('inst-hour')
};

const monthsUnit = {
	key: 'Months',
	name: Liferay.Language.get('inst-month')
};

const weeksUnit = {
	key: 'Weeks',
	name: Liferay.Language.get('inst-week')
};

const yearsUnit = {
	key: 'Years',
	name: Liferay.Language.get('inst-year')
};

const velocityUnitsMap = {
	1: [asDefault(hoursUnit)],
	7: [asDefault(daysUnit)],
	30: [asDefault(daysUnit), weeksUnit],
	90: [daysUnit, asDefault(weeksUnit), monthsUnit],
	180: [weeksUnit, asDefault(monthsUnit)],
	366: [weeksUnit, asDefault(monthsUnit)],
	730: [asDefault(monthsUnit), yearsUnit]
};

const VelocityUnitContext = createContext(null);

const VelocityUnitProvider = ({children, velocityUnitKeys}) => {
	return (
		<VelocityUnitContext.Provider value={useVelocityUnit(velocityUnitKeys)}>
			{children}
		</VelocityUnitContext.Provider>
	);
};

export {VelocityUnitContext, VelocityUnitProvider, useVelocityUnit};
