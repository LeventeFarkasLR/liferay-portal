/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.service.impl;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.provisioning.exception.RequiredProductException;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.model.ProductBundleProducts;
import com.liferay.osb.provisioning.service.ProductBundleLocalService;
import com.liferay.osb.provisioning.service.base.ProductBundleProductsLocalServiceBaseImpl;
import com.liferay.osb.provisioning.service.persistence.ProductBundleProductsPK;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 * @author Yuanyuan Huang
 */
@Component(
	property = "model.class.name=com.liferay.osb.provisioning.model.ProductBundleProducts",
	service = AopService.class
)
public class ProductBundleProductsLocalServiceImpl
	extends ProductBundleProductsLocalServiceBaseImpl {

	public ProductBundleProducts addProductBundleProducts(
			long productBundleId, String productKey)
		throws Exception {

		validate(productBundleId, productKey);

		ProductBundleProductsPK productBundleProductsPK =
			new ProductBundleProductsPK(productBundleId, productKey);

		ProductBundleProducts productBundleProducts =
			productBundleProductsPersistence.fetchByPrimaryKey(
				productBundleProductsPK);

		if (productBundleProducts == null) {
			productBundleProducts = productBundleProductsPersistence.create(
				productBundleProductsPK);

			productBundleProducts = productBundleProductsPersistence.update(
				productBundleProducts);
		}

		return productBundleProducts;
	}

	public ProductBundleProducts deleteProductBundleProducts(
			long productBundleId, String productKey)
		throws Exception {

		ProductBundleProductsPK productBundleProductsPK =
			new ProductBundleProductsPK(productBundleId, productKey);

		return productBundleProductsPersistence.remove(productBundleProductsPK);
	}

	public List<Product> getProductBundleAssignedProducts(long productBundleId)
		throws Exception {

		List<ProductBundleProducts> productBundleProducts =
			getProductBundleProducts(productBundleId);

		StringBundler sb = new StringBundler(
			(productBundleProducts.size() * 4) - 1);

		for (int i = 0; i < productBundleProducts.size(); i++) {
			ProductBundleProducts productBundleProduct =
				productBundleProducts.get(i);

			sb.append("(productKey eq '");
			sb.append(productBundleProduct.getProductKey());
			sb.append("')");

			if (i < (productBundleProducts.size() - 1)) {
				sb.append(" or ");
			}
		}

		return _productWebService.getProducts(
			StringPool.BLANK, sb.toString(), 1, 1000, "name");
	}

	public List<ProductBundleProducts> getProductBundleProducts(
		long productBundleId) {

		return productBundleProductsPersistence.findByProductBundleId(
			productBundleId);
	}

	public List<ProductBundleProducts> getProductBundleProducts(
		String productKey) {

		return productBundleProductsPersistence.findByProductKey(productKey);
	}

	public int getProductBundleProductsCount(long productBundleId) {
		return productBundleProductsPersistence.countByProductBundleId(
			productBundleId);
	}

	public int getProductBundleProductsCount(String productKey) {
		return productBundleProductsPersistence.countByProductKey(productKey);
	}

	protected void validate(long productBundleId, String productKey)
		throws Exception {

		if (Validator.isNull(productKey)) {
			throw new RequiredProductException();
		}

		_productBundleLocalService.getProductBundle(productBundleId);

		_productWebService.getProduct(productKey);
	}

	@Reference
	private ProductBundleLocalService _productBundleLocalService;

	@Reference
	private ProductWebService _productWebService;

}