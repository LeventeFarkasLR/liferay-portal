<%--
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
--%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@
	page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
	page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
	page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@
	page import="com.liferay.commerce.model.CommerceCountry" %>
<%@ page
	import="com.liferay.osb.commerce.provisioning.web.internal.portlet.display.context.TrialRegistrationDisplayContext" %>
<%@
	page import="java.util.List" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />