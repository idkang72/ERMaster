package ermaster.db.impl.sqlite;

import ermaster.db.sqltype.SqlType;
import ermaster.db.sqltype.SqlTypeManagerBase;

public class SQLiteSqlTypeManager extends SqlTypeManagerBase {

	public int getByteLength(SqlType type, Integer length, Integer decimal) {
		return 0;
	}

}
