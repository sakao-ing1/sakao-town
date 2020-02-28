package connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnectionPool {

	private Collection<Connection> connection = new ArrayList<Connection>();
	
	public void remplir (Connection con,String url,String login,String password) {
		try {
			con = DriverManager.getConnection(url, login, password);
			connection.add(con);
			System.out.println("connexion établie!!!!");
		} catch (SQLException ex) {
			Logger.getLogger(JDBCConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	/*
	public JDBCConnectionPool() {
		
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
*/
}
