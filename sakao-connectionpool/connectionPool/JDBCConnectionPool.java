package connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnectionPool {

	private Collection<Connection> listConnection = new ArrayList<Connection>();

	public void remplir(Connection con) throws ClassNotFoundException {
		try {
			Class.forName("");
			con = DriverManager.getConnection("", "postgres", "system");
			listConnection.add(con);
			System.out.println("connexion établie!!!!");

		} catch (SQLException ex) {
			Logger.getLogger(JDBCConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	
	 /* public Connection getConnection(String url) { 
		  for(Connection con : con)
	  
	 
	  }*/
	  
	 

}
