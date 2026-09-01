package ermaster.db;

import java.util.ArrayList;
import java.util.List;

import ermaster.ResourceString;
import ermaster.db.impl.access.AccessDBManager;
import ermaster.db.impl.db2.DB2DBManager;
import ermaster.db.impl.h2.H2DBManager;
import ermaster.db.impl.hsqldb.HSQLDBDBManager;
import ermaster.db.impl.mariadb.MariaDBDBManager;
import ermaster.db.impl.mysql.MySQLDBManager;
import ermaster.db.impl.oracle.OracleDBManager;
import ermaster.db.impl.postgres.PostgresDBManager;
import ermaster.db.impl.sqlite.SQLiteDBManager;
import ermaster.db.impl.sqlserver.SqlServerDBManager;
import ermaster.db.impl.sqlserver2008.SqlServer2008DBManager;
import ermaster.db.impl.standard_sql.StandardSQLDBManager;
import ermaster.editor.model.ERDiagram;

public class DBManagerFactory {

	private static final List<DBManager> DB_LIST = new ArrayList<DBManager>();

	private static final List<String> DB_ID_LIST = new ArrayList<String>();

	static {
		new StandardSQLDBManager();
		new AccessDBManager();
		new DB2DBManager();
		new H2DBManager();
		new HSQLDBDBManager();
		new MySQLDBManager();
		new MariaDBDBManager();
		new OracleDBManager();
		new PostgresDBManager();
		new SQLiteDBManager();
		new SqlServerDBManager();
		new SqlServer2008DBManager();
	}

	static void addDB(DBManager manager) {
		DB_LIST.add(manager);
		DB_ID_LIST.add(manager.getId());
	}

	public static DBManager getDBManager(String database) {
		for (DBManager manager : DB_LIST) {
			if (manager.getId().equals(database)) {
				return manager;
			}
		}

		throw new IllegalArgumentException(
				ResourceString
						.getResourceString("error.database.is.not.supported")
						+ database);
	}

	public static DBManager getDBManager(ERDiagram diagram) {
		return getDBManager(diagram.getDatabase());
	}

	public static List<String> getAllDBList() {
		return DB_ID_LIST;
	}

}
