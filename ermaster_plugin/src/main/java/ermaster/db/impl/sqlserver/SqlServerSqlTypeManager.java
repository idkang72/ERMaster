package ermaster.db.impl.sqlserver;

import ermaster.db.sqltype.SqlType;
import ermaster.db.sqltype.SqlTypeManagerBase;

public class SqlServerSqlTypeManager extends SqlTypeManagerBase {

	public int getByteLength(SqlType type, Integer length, Integer decimal) {
		return 0;
	}

}
