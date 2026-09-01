package ermaster.db.impl.access;

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

public class AccessDBManager extends DBManagerBase {

	public static final String ID = "MSAccess";

	public String getId() {
		return ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDriverClassName() {
		return "sun.jdbc.odbc.JdbcOdbcDriver";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getURL() {
		return "jdbc:odbc:<DB NAME>";
	}

	public int getDefaultPort() {
		return 0;
	}

	public SqlTypeManager getSqlTypeManager() {
		return new AccessSqlTypeManager();
	}

	public TableProperties createTableProperties(TableProperties tableProperties) {
		if (tableProperties != null
				&& tableProperties instanceof AccessTableProperties) {
			return tableProperties;
		}

		return new AccessTableProperties();
	}

	public DDLCreator getDDLCreator(ERDiagram diagram, Category targetCategory,
			boolean semicolon) {
		return new AccessDDLCreator(diagram, targetCategory, semicolon);
	}

	public List<String> getIndexTypeList(ERTable table) {
		List<String> list = new ArrayList<String>();

		list.add("BTREE");

		return list;
	}

	@Override
	protected int[] getSupportItems() {
		return new int[] { SUPPORT_AUTO_INCREMENT,
				SUPPORT_AUTO_INCREMENT_SETTING };
	}

	public ImportFromDBManager getTableImportManager() {
		return new AccessTableImportManager();
	}

	public PreImportFromDBManager getPreTableImportManager() {
		return new AccessPreTableImportManager();
	}

	public PreTableExportManager getPreTableExportManager() {
		return new AccessPreTableExportManager();
	}

	public String[] getCurrentTimeValue() {
		return new String[] { "GETDATE()", "CURRENT_TIMESTAMP" };
	}

	@Override
	public List<String> getSystemSchemaList() {
		List<String> list = new ArrayList<String>();

		return list;
	}

	public BigDecimal getSequenceMaxValue() {
		return null;
	}

	public TablespaceProperties checkTablespaceProperties(
			TablespaceProperties tablespaceProperties) {
		return null;
	}

	public TablespaceProperties createTablespaceProperties() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean doesNeedURLServerName() {
		return false;
	}

}
