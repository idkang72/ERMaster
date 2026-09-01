package ermaster.db.impl.postgres;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ermaster.db.DBManagerBase;
import ermaster.db.impl.postgres.tablespace.PostgresTablespaceProperties;
import ermaster.db.sqltype.SqlTypeManager;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.dbexport.db.PreTableExportManager;
import ermaster.editor.model.dbexport.ddl.DDLCreator;
import ermaster.editor.model.dbimport.ImportFromDBManager;
import ermaster.editor.model.dbimport.PreImportFromDBManager;
import ermaster.editor.model.diagram_contents.element.node.category.Category;
import ermaster.editor.model.diagram_contents.element.node.table.ERTable;
import ermaster.editor.model.diagram_contents.element.node.table.properties.TableProperties;
import ermaster.editor.model.diagram_contents.not_element.tablespace.TablespaceProperties;

public class PostgresDBManager extends DBManagerBase {

	public static final String ID = "PostgreSQL";

	public String getId() {
		return ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDriverClassName() {
		return "org.postgresql.Driver";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getURL() {
		return "jdbc:postgresql://<SERVER NAME>:<PORT>/<DB NAME>";
	}

	public int getDefaultPort() {
		return 5432;
	}

	public SqlTypeManager getSqlTypeManager() {
		return new PostgresSqlTypeManager();
	}

	public TableProperties createTableProperties(TableProperties tableProperties) {
		if (tableProperties != null
				&& tableProperties instanceof PostgresTableProperties) {
			return tableProperties;
		}

		return new PostgresTableProperties();
	}

	public DDLCreator getDDLCreator(ERDiagram diagram, Category targetCategory,
			boolean semicolon) {
		return new PostgresDDLCreator(diagram, targetCategory, semicolon);
	}

	public List<String> getIndexTypeList(ERTable table) {
		List<String> list = new ArrayList<String>();

		list.add("BTREE");

		return list;
	}

	@Override
	protected int[] getSupportItems() {
		return new int[] { SUPPORT_AUTO_INCREMENT_SETTING, SUPPORT_SCHEMA,
				SUPPORT_SEQUENCE };
	}

	public ImportFromDBManager getTableImportManager() {
		return new PostgresTableImportManager();
	}

	public PreImportFromDBManager getPreTableImportManager() {
		return new PostgresPreTableImportManager();
	}

	public PreTableExportManager getPreTableExportManager() {
		return new PostgresPreTableExportManager();
	}

	public TablespaceProperties createTablespaceProperties() {
		return new PostgresTablespaceProperties();
	}

	public TablespaceProperties checkTablespaceProperties(
			TablespaceProperties tablespaceProperties) {

		if (!(tablespaceProperties instanceof PostgresTablespaceProperties)) {
			return new PostgresTablespaceProperties();
		}

		return tablespaceProperties;
	}

	public String[] getCurrentTimeValue() {
		return new String[] { "CURRENT_TIMESTAMP", "now()" };
	}

	@Override
	public List<String> getSystemSchemaList() {
		List<String> list = new ArrayList<String>();

		list.add("information_schema");
		list.add("pg_catalog");
		list.add("pg_toast_temp_1");

		return list;
	}

	public BigDecimal getSequenceMaxValue() {
		return BigDecimal.valueOf(Long.MAX_VALUE);
	}
}
