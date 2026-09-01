package ermaster.db.impl.mariadb;

import ermaster.db.impl.mysql.MySQLDBManager;

public class MariaDBDBManager extends MySQLDBManager {

	public static final String ID = "MariaDB";


	@Override
	public String getId() {
		return ID;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDriverClassName() {
		return "org.mariadb.jdbc.Driver";
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getURL() {
		return "jdbc:mariadb://<SERVER NAME>:<PORT>/<DB NAME>";
	}


	@Override
	protected int[] getSupportItems() {
		return new int[] { SUPPORT_AUTO_INCREMENT,
				SUPPORT_AUTO_INCREMENT_SETTING, SUPPORT_DESC_INDEX,
				SUPPORT_FULLTEXT_INDEX, SUPPORT_SCHEMA, SUPPORT_SEQUENCE };
	}
}
