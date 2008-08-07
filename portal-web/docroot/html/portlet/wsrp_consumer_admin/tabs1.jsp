<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/html/portlet/wsrp_consumer_admin/init.jsp" %>

<portlet:renderURL var="tabs1URL0">
	<portlet:param name="<%= Constants.ACTION %>" value="<%= String.valueOf(AdminPortletAction.LIST) %>" />
	<portlet:param name="tabs1" value="producers" />
</portlet:renderURL>

<portlet:actionURL var="tabs1URL1">
	<portlet:param name="<%= Constants.ACTION %>" value="<%= String.valueOf(AdminPortletAction.LIST_CHANNELS) %>" />
	<portlet:param name="tabs1" value="portlets" />
</portlet:actionURL>

<liferay-ui:tabs
	names="producers,portlets"
	url0="<%= tabs1URL0 %>"
	url1="<%= tabs1URL1 %>"
/>