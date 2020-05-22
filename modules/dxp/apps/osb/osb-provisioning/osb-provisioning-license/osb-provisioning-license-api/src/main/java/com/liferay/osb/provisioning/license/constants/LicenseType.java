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

package com.liferay.osb.provisioning.license.constants;

/**
 * @author Amos Fong
 */
public class LicenseType {

	public static final String BACKUP = "backup";

	public static final String CLUSTER = "cluster";

	public static final String DEVELOPER = "developer";

	public static final String DEVELOPER_CLUSTER = "developer-cluster";

	public static final String ELASTIC = "elastic";

	public static final String ENTERPRISE = "enterprise";

	public static final String LIMITED = "limited";

	public static final String NON_PRODUCTION = "non-production";

	public static final String OEM = "oem";

	public static final String PER_USER = "per-user";

	public static final String PRODUCTION = "production";

	public static final String TRIAL = "trial";

	public static final String[] VALUES = {
		CLUSTER, DEVELOPER, DEVELOPER_CLUSTER, ELASTIC, ENTERPRISE, LIMITED,
		OEM, PER_USER, PRODUCTION, TRIAL
	};

}