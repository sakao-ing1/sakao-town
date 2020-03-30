package sakao_connection_pool;

import java.sql.Connection;
import java.util.ArrayList;

public class DataSource {

	private static JDBCConnectionPool instance;

	public DataSource() throws ClassNotFoundException {
		instance = new JDBCConnectionPool();
		instance.fill();
	}

	// methode statique qui renvoie une connexion de l'attribut
	public Connection getConnection() {
		return instance.getConnection();
	}

	// methode statique qui remet une connexion dans l'attribut
	public void reset(Connection con) {
		instance.setConnection(con);
	}

	// methode statique qui clot l'attribut.
	public static void closeInstance() {
		instance.closeAllConnection();
	}

	public ArrayList<Connection> getListConnectionavailable() {
		return instance.getListConnectionavailable();
	}

	public ArrayList<Connection> getListConnectionbusy() {
		return instance.getListConnectionbusy();
	}

	public static JDBCConnectionPool getInstance() {
		return instance;
	}

	public static void setInstance(JDBCConnectionPool instance) {
		DataSource.instance = instance;
	}

}
