package ermaster.db.impl.sqlite;

import java.sql.SQLException;

import ermaster.editor.model.dbexport.db.PreTableExportManager;

public class SQLitePreTableExportManager extends PreTableExportManager {

	@Override
	protected String dropForeignKeys() throws SQLException {
		return "";
	}

	
}
