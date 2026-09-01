package ermaster.db.impl.h2;

import ermaster.db.sqltype.SqlType;
import ermaster.db.sqltype.SqlTypeManagerBase;

public class H2SqlTypeManager extends SqlTypeManagerBase {

	public int getByteLength(SqlType type, Integer length, Integer decimal) {
		return 0;
	}

}
