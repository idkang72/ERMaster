package ermaster.db.impl.postgres;

import ermaster.db.sqltype.SqlType;
import ermaster.db.sqltype.SqlTypeManagerBase;

public class PostgresSqlTypeManager extends SqlTypeManagerBase {

	public int getByteLength(SqlType type, Integer length, Integer decimal) {
		return 0;
	}

}
