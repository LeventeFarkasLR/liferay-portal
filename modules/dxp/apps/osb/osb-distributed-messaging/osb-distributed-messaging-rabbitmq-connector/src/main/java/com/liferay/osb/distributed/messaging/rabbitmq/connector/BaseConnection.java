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

package com.liferay.osb.distributed.messaging.rabbitmq.connector;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

import java.security.KeyStore;

import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Amos Fong
 */
public class BaseConnection implements Connection {

	@Override
	public void connect() {
		ConnectionFactory connectionFactory = new ConnectionFactory();

		connectionFactory.setAutomaticRecoveryEnabled(true);
		connectionFactory.setHost(_host);
		connectionFactory.setPassword(_password);
		connectionFactory.setPort(_port);
		connectionFactory.setUsername(_username);

		try {
			if (_log.isInfoEnabled()) {
				_log.info(
					StringBundler.concat(
						"Connecting to RabbitMQ at ", _host, ":", _port));
			}

			if (_useSSL) {
				if (_log.isInfoEnabled()) {
					_log.info("Enabling SSL");
				}

				TrustManagerFactory trustManagerFactory =
					TrustManagerFactory.getInstance(
						TrustManagerFactory.getDefaultAlgorithm());

				trustManagerFactory.init((KeyStore)null);

				SSLContext sslContext = SSLContext.getInstance("TLSv1.2");

				sslContext.init(
					null, trustManagerFactory.getTrustManagers(), null);

				connectionFactory.useSslProtocol(sslContext);

				connectionFactory.enableHostnameVerification();
			}

			_connection = connectionFactory.newConnection();

			if (_log.isInfoEnabled()) {
				_log.info("Connection successful");
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			disconnect();
		}
	}

	@Override
	public Channel createChannel() throws IOException {
		return createChannel(0);
	}

	@Override
	public Channel createChannel(int prefetchCount) throws IOException {
		if (!isConnected()) {
			connect();
		}

		Channel channel = null;

		try {
			channel = _connection.createChannel();
		}
		catch (IOException ioException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to create channel. Reconnecting and retrying.",
					ioException);
			}

			disconnect();

			connect();

			channel = _connection.createChannel();
		}

		if (prefetchCount > 0) {
			channel.basicQos(prefetchCount);
		}

		return channel;
	}

	@Deactivate
	@Override
	public void disconnect() {
		try {
			if (_connection != null) {
				_connection.close();
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		if (_log.isInfoEnabled()) {
			_log.info("Disconnected from RabbitMQ");
		}
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_host = GetterUtil.getString(properties.get("host"));
		_password = GetterUtil.getString(properties.get("password"));
		_port = GetterUtil.getInteger(properties.get("port"));
		_username = GetterUtil.getString(properties.get("username"));
		_useSSL = GetterUtil.getBoolean(properties.get("useSSL"));

		connect();
	}

	protected boolean isConnected() {
		if (_connection == null) {
			return false;
		}

		if (!_connection.isOpen()) {
			_log.error(
				"Connection is unexpectedly closed",
				_connection.getCloseReason());

			return false;
		}

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(BaseConnection.class);

	private com.rabbitmq.client.Connection _connection;
	private String _host;
	private String _password;
	private int _port;
	private String _username;
	private boolean _useSSL;

}