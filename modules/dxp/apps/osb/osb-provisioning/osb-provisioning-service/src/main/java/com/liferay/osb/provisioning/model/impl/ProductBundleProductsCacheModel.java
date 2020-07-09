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

package com.liferay.osb.provisioning.model.impl;

import com.liferay.osb.provisioning.model.ProductBundleProducts;
import com.liferay.osb.provisioning.service.persistence.ProductBundleProductsPK;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProductBundleProducts in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProductBundleProductsCacheModel
	implements CacheModel<ProductBundleProducts>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ProductBundleProductsCacheModel)) {
			return false;
		}

		ProductBundleProductsCacheModel productBundleProductsCacheModel =
			(ProductBundleProductsCacheModel)object;

		if (productBundleProductsPK.equals(
				productBundleProductsCacheModel.productBundleProductsPK) &&
			(mvccVersion == productBundleProductsCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, productBundleProductsPK);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", productBundleId=");
		sb.append(productBundleId);
		sb.append(", productKey=");
		sb.append(productKey);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProductBundleProducts toEntityModel() {
		ProductBundleProductsImpl productBundleProductsImpl =
			new ProductBundleProductsImpl();

		productBundleProductsImpl.setMvccVersion(mvccVersion);
		productBundleProductsImpl.setProductBundleId(productBundleId);

		if (productKey == null) {
			productBundleProductsImpl.setProductKey("");
		}
		else {
			productBundleProductsImpl.setProductKey(productKey);
		}

		productBundleProductsImpl.resetOriginalValues();

		return productBundleProductsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		productBundleId = objectInput.readLong();
		productKey = objectInput.readUTF();

		productBundleProductsPK = new ProductBundleProductsPK(
			productBundleId, productKey);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(productBundleId);

		if (productKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(productKey);
		}
	}

	public long mvccVersion;
	public long productBundleId;
	public String productKey;
	public transient ProductBundleProductsPK productBundleProductsPK;

}