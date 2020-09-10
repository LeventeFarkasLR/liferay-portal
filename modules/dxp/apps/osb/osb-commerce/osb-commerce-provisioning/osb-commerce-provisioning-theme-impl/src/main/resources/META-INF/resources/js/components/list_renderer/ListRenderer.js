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

import React from 'react';

import ProductCard from '../product_card/ProductCard';

function ListRenderer({cpEntries, portletNamespace}) {
	return (
		<div className={'align-items-center d-flex'}>
			{cpEntries.map((entry, index) => (
				<div
					className={
						'col-md-4 col-sm-12 osb-commerce-product-card-container'
					}
					key={index}
				>
					<ProductCard
						isFeatured={index === 1}
						isTrial={index === 0}
						key={index}
						namespace={portletNamespace}
						{...entry}
					/>
				</div>
			))}
		</div>
	);
}

export default ListRenderer;
