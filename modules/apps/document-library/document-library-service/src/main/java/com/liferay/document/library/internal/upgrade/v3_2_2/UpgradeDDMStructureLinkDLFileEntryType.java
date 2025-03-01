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

package com.liferay.document.library.internal.upgrade.v3_2_2;

import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PortalUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Alicia Garcia
 */
public class UpgradeDDMStructureLinkDLFileEntryType extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		_fixDataDefinitionIdFields();
	}

	private void _fixDataDefinitionIdFields() throws Exception {
		StringBuilder sb = new StringBuilder(5);

		sb.append("select DLFileEntryType.uuid_, fileEntryTypeId, ");
		sb.append("DLFileEntryType.groupId, DLFileEntryType.companyId, ");
		sb.append("dataDefinitionId, fileEntryTypeKey from DLFileEntryType ");
		sb.append("inner join DDMStructure ON dataDefinitionId = structureId ");
		sb.append("where type_ = 0");

		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
				sb.toString());
			PreparedStatement preparedStatement2 = connection.prepareStatement(
				"select structureId FROM DDMStructure where groupId = ? AND " +
					"classNameId = ? AND (structureKey = ? OR structureKey = " +
						"? OR structureKey = ? ) ");
			PreparedStatement preparedStatement3 =
				AutoBatchPreparedStatementUtil.autoBatch(
					connection.prepareStatement(
						"update DLFileEntryType set dataDefinitionId = ? " +
							"where fileEntryTypeId = ? "));
			PreparedStatement preparedStatement4 = connection.prepareStatement(
				"select structureLinkId from DDMStructureLink where " +
					"companyId = ? and classNameId = ? and classPK = ? and " +
						"structureId = ?");
			PreparedStatement preparedStatement5 =
				AutoBatchPreparedStatementUtil.autoBatch(
					connection.prepareStatement(
						"insert into DDMStructureLink (structureLinkId, " +
							"companyId, classNameId, classPK, structureId) " +
								"values (?, ?, ?, ?, ?)"));
			ResultSet resultSet1 = preparedStatement1.executeQuery()) {

			while (resultSet1.next()) {
				long fileEntryTypeId = resultSet1.getLong(2);

				preparedStatement2.setLong(1, resultSet1.getLong(3));
				preparedStatement2.setLong(
					2, PortalUtil.getClassNameId(DLFileEntryMetadata.class));
				preparedStatement2.setString(
					3, DLUtil.getDDMStructureKey(resultSet1.getString(1)));
				preparedStatement2.setString(
					4, DLUtil.getDeprecatedDDMStructureKey(fileEntryTypeId));
				preparedStatement2.setString(5, resultSet1.getString(4));

				try (ResultSet resultSet2 = preparedStatement2.executeQuery()) {
					if (resultSet2.next()) {
						preparedStatement3.setLong(1, resultSet2.getLong(1));
						preparedStatement3.setLong(2, fileEntryTypeId);

						preparedStatement3.addBatch();
					}
				}

				long companyId = resultSet1.getLong(4);

				preparedStatement4.setLong(1, companyId);

				preparedStatement4.setLong(
					2, PortalUtil.getClassNameId(DLFileEntryType.class));
				preparedStatement4.setLong(3, fileEntryTypeId);

				long dataDefinitionId = resultSet1.getLong(5);

				preparedStatement4.setLong(4, dataDefinitionId);

				try (ResultSet resultSet3 = preparedStatement4.executeQuery()) {
					if (resultSet3.next()) {
						continue;
					}

					preparedStatement5.setLong(1, increment());
					preparedStatement5.setLong(2, companyId);
					preparedStatement5.setLong(
						3, PortalUtil.getClassNameId(DLFileEntryType.class));
					preparedStatement5.setLong(4, fileEntryTypeId);
					preparedStatement5.setLong(5, dataDefinitionId);

					preparedStatement5.addBatch();
				}

				preparedStatement3.executeBatch();

				preparedStatement5.executeBatch();
			}
		}
	}

}