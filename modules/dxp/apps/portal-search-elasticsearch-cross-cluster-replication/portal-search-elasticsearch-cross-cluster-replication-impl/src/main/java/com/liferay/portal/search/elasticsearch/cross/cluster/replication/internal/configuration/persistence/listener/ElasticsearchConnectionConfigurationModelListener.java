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

package com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.configuration.persistence.listener;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.persistence.listener.ConfigurationModelListener;
import com.liferay.portal.configuration.persistence.listener.ConfigurationModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.configuration.ElasticsearchConnectionConfiguration;

import java.util.Dictionary;
import java.util.ResourceBundle;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bryan Engler
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.configuration.ElasticsearchConnectionConfiguration",
	service = ConfigurationModelListener.class
)
public class ElasticsearchConnectionConfigurationModelListener
	implements ConfigurationModelListener {

	@Override
	public void onBeforeSave(String pid, Dictionary<String, Object> properties)
		throws ConfigurationModelListenerException {

		try {
			String connectionId = StringUtil.unquote(
				(String)properties.get("connectionId"));

			_validateUniqueConnectionId(pid, connectionId);

			_validateNetworkHostAddress(properties);
		}
		catch (Exception exception) {
			throw new ConfigurationModelListenerException(
				exception.getMessage(),
				ElasticsearchConnectionConfiguration.class, getClass(),
				properties);
		}
	}

	@Reference
	protected ConfigurationAdmin configurationAdmin;

	private String _getMessage(String key, Object... arguments) {
		try {
			ResourceBundle resourceBundle = _getResourceBundle();

			return ResourceBundleUtil.getString(resourceBundle, key, arguments);
		}
		catch (Exception exception) {
			return null;
		}
	}

	private ResourceBundle _getResourceBundle() {
		return ResourceBundleUtil.getBundle(
			"content.Language", LocaleThreadLocal.getThemeDisplayLocale(),
			getClass());
	}

	private void _validateNetworkHostAddress(
			Dictionary<String, Object> properties)
		throws Exception {

		String networkHostAddress = (String)properties.get(
			"networkHostAddress");

		if (!Validator.isBlank(networkHostAddress)) {
			return;
		}

		_log.error("Unable to validate network host address");

		throw new Exception(_getMessage("please-set-the-network-host-address"));
	}

	private void _validateUniqueConnectionId(String pid, String connectionId)
		throws Exception {

		if (Validator.isBlank(connectionId)) {
			_log.error("Connection ID is blank");

			throw new Exception(_getMessage("please-set-a-connection-id"));
		}

		if (connectionId.equals("EMBEDDED") || connectionId.equals("REMOTE")) {
			_log.error("The ID you entered is reserved: " + connectionId);

			throw new Exception(
				_getMessage("the-id-you-entered-is-reserved-x", connectionId));
		}

		Configuration configuration = configurationAdmin.getConfiguration(
			pid, StringPool.QUESTION);

		Dictionary<String, Object> properties = configuration.getProperties();

		if (properties != null) {
			String previousConnectionId = StringUtil.unquote(
				(String)properties.get("connectionId"));

			if (!previousConnectionId.equals(connectionId)) {
				_log.error("The connection ID cannot be changed");

				throw new Exception(
					_getMessage("the-connection-id-cannot-be-changed"));
			}
		}

		String filterString = String.format(
			"(&(service.factoryPid=%s)(connectionId=%s))",
			ElasticsearchConnectionConfiguration.class.getName(), connectionId);

		Configuration[] configurations = configurationAdmin.listConfigurations(
			filterString);

		if (configurations == null) {
			return;
		}

		configuration = configurations[0];

		if (pid.equals(configuration.getPid())) {
			return;
		}

		_log.error(
			"There is already a connection with the ID: " + connectionId);

		throw new Exception(
			_getMessage(
				"there-is-already-a-connection-with-the-id-x", connectionId));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ElasticsearchConnectionConfigurationModelListener.class);

}