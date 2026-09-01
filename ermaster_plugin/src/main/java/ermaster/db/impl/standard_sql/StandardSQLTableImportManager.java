package ermaster.db.impl.standard_sql;

import ermaster.editor.model.dbimport.ImportFromDBManagerEclipseBase;

public class StandardSQLTableImportManager extends ImportFromDBManagerEclipseBase {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getViewDefinitionSQL(String schema) {
		return null;
	}
}
