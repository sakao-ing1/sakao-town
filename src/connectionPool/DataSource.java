package connectionPool;

import java.sql.SQLException;

public class DataSource {

	private static JDBCConnectionPool instance;

	
	
	public static JDBCConnectionPool getInstance() throws SQLException {
		if (instance == null)
			instance = new JDBCConnectionPool();

		return instance;
	}

}
