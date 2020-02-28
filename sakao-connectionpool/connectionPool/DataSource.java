package connectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

	private static JDBCConnectionPool instance = new JDBCConnectionPool();

	// methode statique qui renvoie une connexion de l'attribut
	public Connection getConnection(String url) {
		return instance.getConnection(url);
	}

	// methode statique qui remet une connexion dans l'attribut
	public void reset(Connection con) {
		instance.setConnection(con);
	}

	// methode statique qui clot l'attribut.
	public static void closeInstance() {
		instance.closeAllConnection();
	}

}
