package ermaster.db.impl.hsqldb;

import ermaster.db.sqltype.SqlType;
import ermaster.db.sqltype.SqlTypeManagerBase;

public class HSQLDBSqlTypeManager extends SqlTypeManagerBase {

	public int getByteLength(SqlType type, Integer length, Integer decimal) {
		return 0;
	}

}
