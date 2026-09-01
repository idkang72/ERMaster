package ermaster.db.impl.sqlite;

import ermaster.editor.model.dbimport.PreImportFromDBManager;

public class SQLitePreTableImportManager extends PreImportFromDBManager {

	@Override
	protected String getTableNameWithSchema(String schema, String tableName) {
		return "[" + super.getTableNameWithSchema(schema, tableName) + "]";
	}
}
