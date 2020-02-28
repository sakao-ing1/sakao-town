package connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnectionPool {

	private Connection connection;

	final String url = "jdbc:mysql://127.0.0.1/cite_de_la_culture";
	final String login = "root";
	final String password = "";

	public JDBCConnectionPool() {
		try {
			connection = DriverManager.getConnection(url, login, password);
			System.out.println("connexion établie!!!!");
		} catch (SQLException ex) {
			Logger.getLogger(JDBCConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
