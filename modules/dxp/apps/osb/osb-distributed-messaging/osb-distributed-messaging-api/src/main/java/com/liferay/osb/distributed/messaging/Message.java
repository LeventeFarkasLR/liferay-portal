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

package com.liferay.osb.distributed.messaging;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.io.Deserializer;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MapUtil;

import java.io.InputStream;

import java.nio.ByteBuffer;

import java.util.Map;

/**
 * @author Amos Fong
 */
public class Message extends com.liferay.portal.kernel.messaging.Message {

	public static Message fromInputStream(InputStream inputStream)
		throws Exception {

		byte[] bytes = FileUtil.getBytes(inputStream);

		Deserializer deserializer = new Deserializer(ByteBuffer.wrap(bytes));

		return deserializer.readObject();
	}

	public Message(Map<String, Object> attributes, Object payload) {
		setAttributes(attributes);
		setPayload(payload);
	}

	public Message(Object payload) {
		setPayload(payload);
	}

	public Message(
		String topic, Map<String, Object> attributes, Object payload) {

		setTopic(topic);
		setAttributes(attributes);
		setPayload(payload);
	}

	public Map<String, Object> getAttributes() {
		return getValues();
	}

	public String getTopic() {
		return getDestinationName();
	}

	public void setAttributes(Map<String, Object> attributes) {
		setValues(attributes);
	}

	public void setTopic(String topic) {
		setDestinationName(topic);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{topic=");
		sb.append(getTopic());
		sb.append(", attributes=");
		sb.append(MapUtil.toString(getAttributes()));
		sb.append(", payload=");
		sb.append(getPayload());
		sb.append("}");

		return sb.toString();
	}

}