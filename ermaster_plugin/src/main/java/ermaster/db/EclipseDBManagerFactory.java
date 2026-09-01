package ermaster.db;

import java.util.ArrayList;
import java.util.List;

import ermaster.ResourceString;
import ermaster.db.impl.access.AccessEclipseDBManager;
import ermaster.db.impl.db2.DB2EclipseDBManager;
import ermaster.db.impl.h2.H2EclipseDBManager;
import ermaster.db.impl.hsqldb.HSQLDBEclipseDBManager;
import ermaster.db.impl.mariadb.MariaDBEclipseDBManager;
import ermaster.db.impl.mysql.MySQLEclipseDBManager;
import ermaster.db.impl.oracle.OracleEclipseDBManager;
import ermaster.db.impl.postgres.PostgresEclipseDBManager;
import ermaster.db.impl.sqlite.SQLiteEclipseDBManager;
import ermaster.db.impl.sqlserver.SqlServerEclipseDBManager;
import ermaster.db.impl.sqlserver2008.SqlServer2008EclipseDBManager;
import ermaster.db.impl.standard_sql.StandardSQLEclipseDBManager;
import ermaster.editor.model.ERDiagram;

public class EclipseDBManagerFactory {

	private static final List<EclipseDBManager> DB_LIST = new ArrayList<EclipseDBManager>();

	static {
		new StandardSQLEclipseDBManager();
		new AccessEclipseDBManager();
		new DB2EclipseDBManager();
		new H2EclipseDBManager();
		new HSQLDBEclipseDBManager();
		new MySQLEclipseDBManager();
		new MariaDBEclipseDBManager();
		new OracleEclipseDBManager();
		new PostgresEclipseDBManager();
		new SQLiteEclipseDBManager();
		new SqlServerEclipseDBManager();
		new SqlServer2008EclipseDBManager();
	}

	static void addDB(EclipseDBManager manager) {
		DB_LIST.add(manager);
	}

	public static EclipseDBManager getEclipseDBManager(String database) {
		for (EclipseDBManager manager : DB_LIST) {
			if (manager.getId().equals(database)) {
				return manager;
			}
		}

		throw new IllegalArgumentException(
				ResourceString
						.getResourceString("error.database.is.not.supported")
						+ database);
	}

	public static EclipseDBManager getEclipseDBManager(ERDiagram diagram) {
		return getEclipseDBManager(diagram.getDatabase());
	}

}
