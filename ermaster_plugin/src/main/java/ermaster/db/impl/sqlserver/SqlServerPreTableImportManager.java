package ermaster.db.impl.sqlserver;

import ermaster.editor.model.dbimport.PreImportFromDBManager;
import ermaster.util.Check;

public class SqlServerPreTableImportManager extends PreImportFromDBManager {

	@Override
	protected String getTableNameWithSchema(String schema, String tableName) {
		if (!Check.isEmpty(schema)) {
			schema = "[" + schema + "]";
		}

		tableName = "[" + tableName + "]";

		return super.getTableNameWithSchema(schema, tableName);
	}

}
