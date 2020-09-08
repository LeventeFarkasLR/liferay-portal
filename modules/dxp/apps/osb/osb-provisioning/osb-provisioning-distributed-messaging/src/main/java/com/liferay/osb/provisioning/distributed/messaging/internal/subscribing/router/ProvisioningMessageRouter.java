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

package com.liferay.osb.provisioning.distributed.messaging.internal.subscribing.router;

import com.liferay.osb.distributed.messaging.subscribing.router.BaseMessageRouter;
import com.liferay.osb.distributed.messaging.subscribing.router.MessageRouter;
import com.liferay.osb.provisioning.distributed.messaging.internal.subscribing.DossieraCreateMessageSubscriber;
import com.liferay.osb.provisioning.distributed.messaging.internal.subscribing.ProductMessageSubscriber;
import com.liferay.osb.provisioning.distributed.messaging.internal.subscribing.ProductPurchaseMessageSubscriber;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = MessageRouter.class)
public class ProvisioningMessageRouter extends BaseMessageRouter {

	@Reference(unbind = "-")
	protected void setDossieraCreateMessageSubscriber(
		DossieraCreateMessageSubscriber dossieraCreateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(dossieraCreateMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setProductMessageSubscriber(
		ProductMessageSubscriber productMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(productMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setProductPurchaseMessageSubscriber(
		ProductPurchaseMessageSubscriber productPurchaseMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(productPurchaseMessageSubscriber, properties);
	}

}