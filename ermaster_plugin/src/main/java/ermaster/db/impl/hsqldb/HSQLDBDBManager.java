package ermaster.db.impl.hsqldb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ermaster.db.DBManagerBase;
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

public class HSQLDBDBManager extends DBManagerBase {

	public static final String ID = "HSQLDB";

	public String getId() {
		return ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDriverClassName() {
		return "org.hsqldb.jdbcDriver";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getURL() {
		return "jdbc:hsqldb:hsql://<SERVER NAME>:<PORT>/<DB NAME>";
	}

	public int getDefaultPort() {
		return 9001;
	}

	public SqlTypeManager getSqlTypeManager() {
		return new HSQLDBSqlTypeManager();
	}

	public TableProperties createTableProperties(TableProperties tableProperties) {
		if (tableProperties != null
				&& tableProperties instanceof HSQLDBTableProperties) {
			return tableProperties;
		}

		return new HSQLDBTableProperties();
	}

	public DDLCreator getDDLCreator(ERDiagram diagram, Category targetCategory,
			boolean semicolon) {
		return new HSQLDBDDLCreator(diagram, targetCategory, semicolon);
	}

	public List<String> getIndexTypeList(ERTable table) {
		List<String> list = new ArrayList<String>();

		list.add("BTREE");

		return list;
	}

	@Override
	protected int[] getSupportItems() {
		return new int[] { SUPPORT_SCHEMA, SUPPORT_SEQUENCE,
				SUPPORT_SEQUENCE_NOCACHE };
	}

	public ImportFromDBManager getTableImportManager() {
		return new HSQLDBTableImportManager();
	}

	public PreImportFromDBManager getPreTableImportManager() {
		return new HSQLDBPreTableImportManager();
	}

	public PreTableExportManager getPreTableExportManager() {
		return new HSQLDBPreTableExportManager();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean doesNeedURLDatabaseName() {
		return false;
	}

	public TablespaceProperties createTablespaceProperties() {
		return null;
	}

	public TablespaceProperties checkTablespaceProperties(
			TablespaceProperties tablespaceProperties) {
		return null;
	}

	public String[] getCurrentTimeValue() {
		return new String[] { "CURRENT_TIMESTAMP" };
	}

	@Override
	public List<String> getSystemSchemaList() {
		List<String> list = new ArrayList<String>();

		list.add("information_schema");
		list.add("system_lobs");

		return list;
	}

	public BigDecimal getSequenceMaxValue() {
		return null;
	}

}
