/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.headless.delivery.resource.v1_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.dynamic.data.mapping.io.DDMFormDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormDeserializerDeserializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormDeserializerDeserializeResponse;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureConstants;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.storage.StorageType;
import com.liferay.dynamic.data.mapping.test.util.DDMStructureTestHelper;
import com.liferay.dynamic.data.mapping.test.util.DDMTemplateTestUtil;
import com.liferay.headless.delivery.client.dto.v1_0.ContentField;
import com.liferay.headless.delivery.client.dto.v1_0.StructuredContent;
import com.liferay.headless.delivery.client.dto.v1_0.Value;
import com.liferay.headless.delivery.client.resource.v1_0.StructuredContentResource;
import com.liferay.headless.delivery.client.serdes.v1_0.StructuredContentSerDes;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.test.util.JournalTestUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.io.InputStream;

import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Javier Gamarra
 */
@RunWith(Arquillian.class)
public class StructuredContentResourceTest
	extends BaseStructuredContentResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_ddmStructure = _addDDMStructure(testGroup);
		_irrelevantDDMStructure = _addDDMStructure(irrelevantGroup);

		_ddmTemplate = _addDDMTemplate(_ddmStructure);
		_addDDMTemplate(_irrelevantDDMStructure);

		_journalFolder = JournalTestUtil.addFolder(
			testGroup.getGroupId(), RandomTestUtil.randomString());
		_irrelevantJournalFolder = JournalTestUtil.addFolder(
			irrelevantGroup.getGroupId(), RandomTestUtil.randomString());
	}

	@After
	@Override
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetSiteStructuredContentWithDifferentLocale()
		throws Exception {

		StructuredContent structuredContent =
			structuredContentResource.postSiteStructuredContent(
				testGroup.getGroupId(), randomStructuredContent());

		String title = structuredContent.getTitle();

		StructuredContentResource.Builder builder =
			StructuredContentResource.builder();

		StructuredContentResource frenchStructuredContentResource =
			builder.locale(
				LocaleUtil.FRANCE
			).build();

		String frenchTitle = RandomTestUtil.randomString();

		structuredContent.setTitle(frenchTitle);

		frenchStructuredContentResource.putStructuredContent(
			structuredContent.getId(), structuredContent);

		structuredContent =
			frenchStructuredContentResource.getStructuredContent(
				structuredContent.getId());

		Assert.assertEquals(frenchTitle, structuredContent.getTitle());

		structuredContent = structuredContentResource.getStructuredContent(
			structuredContent.getId());

		Assert.assertEquals(title, structuredContent.getTitle());
	}

	@Override
	@Test
	public void testGetStructuredContentRenderedContentTemplate()
		throws Exception {

		StructuredContent structuredContent =
			testGetSiteStructuredContentByKey_addStructuredContent();

		ContentField[] contentFields = structuredContent.getContentFields();

		Value value = contentFields[0].getValue();

		Assert.assertEquals(
			"<div>" + value.getData() + "</div>",
			structuredContentResource.
				getStructuredContentRenderedContentTemplate(
					structuredContent.getId(), _ddmTemplate.getTemplateId()));
	}

	@Test
	public void testGraphQLGetSiteStructuredContentByKey() throws Exception {
		StructuredContent structuredContent =
			testPostSiteStructuredContent_addStructuredContent(
				randomStructuredContent());

		List<GraphQLField> graphQLFields = getGraphQLFields();

		GraphQLField graphQLField = new GraphQLField(
			"query",
			new GraphQLField(
				"structuredContentByKey",
				(HashMap)HashMapBuilder.put(
					"key", "\"" + structuredContent.getKey() + "\""
				).put(
					"siteKey", "\"" + structuredContent.getSiteId() + "\""
				).build(),
				graphQLFields.toArray(new GraphQLField[0])));

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			invoke(graphQLField.toString()));

		JSONObject dataJSONObject = jsonObject.getJSONObject("data");

		Assert.assertTrue(
			equals(
				structuredContent,
				StructuredContentSerDes.toDTO(
					dataJSONObject.getString("structuredContentByKey"))));
	}

	@Override
	@Test
	public void testGraphQLGetSiteStructuredContentByUuid() throws Exception {
		StructuredContent structuredContent =
			testPostSiteStructuredContent_addStructuredContent(
				randomStructuredContent());

		List<GraphQLField> graphQLFields = getGraphQLFields();

		GraphQLField graphQLField = new GraphQLField(
			"query",
			new GraphQLField(
				"structuredContentByUuid",
				(HashMap)HashMapBuilder.put(
					"siteKey", "\"" + structuredContent.getSiteId() + "\""
				).put(
					"uuid", "\"" + structuredContent.getUuid() + "\""
				).build(),
				graphQLFields.toArray(new GraphQLField[0])));

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			invoke(graphQLField.toString()));

		JSONObject dataJSONObject = jsonObject.getJSONObject("data");

		Assert.assertTrue(
			equals(
				structuredContent,
				StructuredContentSerDes.toDTO(
					dataJSONObject.getString("structuredContentByUuid"))));
	}

	@Override
	protected String[] getAdditionalAssertFieldNames() {
		return new String[] {"contentStructureId", "description", "title"};
	}

	@Override
	protected String[] getIgnoredEntityFieldNames() {
		return new String[] {"contentStructureId", "creatorId"};
	}

	@Override
	protected StructuredContent randomIrrelevantStructuredContent()
		throws Exception {

		StructuredContent structuredContent = randomStructuredContent();

		structuredContent.setContentStructureId(
			_irrelevantDDMStructure.getStructureId());

		return structuredContent;
	}

	@Override
	protected StructuredContent randomStructuredContent() throws Exception {
		StructuredContent structuredContent = super.randomStructuredContent();

		structuredContent.setContentFields(
			new ContentField[] {
				new ContentField() {
					{
						name = "MyText";
						value = new Value() {
							{
								data = RandomTestUtil.randomString(10);
							}
						};
					}
				}
			});
		structuredContent.setContentStructureId(_ddmStructure.getStructureId());

		return structuredContent;
	}

	@Override
	protected StructuredContent
			testGetContentStructureStructuredContentsPage_addStructuredContent(
				Long contentStructureId, StructuredContent structuredContent)
		throws Exception {

		return structuredContentResource.postSiteStructuredContent(
			testGroup.getGroupId(), structuredContent);
	}

	@Override
	protected Long
			testGetContentStructureStructuredContentsPage_getContentStructureId()
		throws Exception {

		return _ddmStructure.getStructureId();
	}

	@Override
	protected Long
		testGetStructuredContentFolderStructuredContentsPage_getIrrelevantStructuredContentFolderId() {

		return _irrelevantJournalFolder.getFolderId();
	}

	@Override
	protected Long
		testGetStructuredContentFolderStructuredContentsPage_getStructuredContentFolderId() {

		return _journalFolder.getFolderId();
	}

	private DDMStructure _addDDMStructure(Group group) throws Exception {
		DDMStructureTestHelper ddmStructureTestHelper =
			new DDMStructureTestHelper(
				PortalUtil.getClassNameId(JournalArticle.class), group);

		return ddmStructureTestHelper.addStructure(
			PortalUtil.getClassNameId(JournalArticle.class),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			_deserialize(_read("test-structured-content-structure.json")),
			StorageType.JSON.getValue(), DDMStructureConstants.TYPE_DEFAULT);
	}

	private DDMTemplate _addDDMTemplate(DDMStructure ddmStructure)
		throws Exception {

		return DDMTemplateTestUtil.addTemplate(
			ddmStructure.getGroupId(), ddmStructure.getStructureId(),
			PortalUtil.getClassNameId(JournalArticle.class),
			TemplateConstants.LANG_TYPE_VM,
			_read("test-structured-content-template.xsl"), LocaleUtil.US);
	}

	private DDMForm _deserialize(String content) {
		DDMFormDeserializerDeserializeRequest.Builder builder =
			DDMFormDeserializerDeserializeRequest.Builder.newBuilder(content);

		DDMFormDeserializerDeserializeResponse
			ddmFormDeserializerDeserializeResponse =
				_jsonDDMFormDeserializer.deserialize(builder.build());

		return ddmFormDeserializerDeserializeResponse.getDDMForm();
	}

	private String _read(String fileName) throws Exception {
		Class<?> clazz = getClass();

		InputStream inputStream = clazz.getResourceAsStream(
			"dependencies/" + fileName);

		return StringUtil.read(inputStream);
	}

	@Inject(filter = "ddm.form.deserializer.type=json")
	private static DDMFormDeserializer _jsonDDMFormDeserializer;

	private DDMStructure _ddmStructure;
	private DDMTemplate _ddmTemplate;
	private DDMStructure _irrelevantDDMStructure;
	private JournalFolder _irrelevantJournalFolder;
	private JournalFolder _journalFolder;

}