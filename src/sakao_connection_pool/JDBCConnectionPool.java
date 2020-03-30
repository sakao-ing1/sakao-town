package sakao_connection_pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnectionPool {

	private ArrayList<Connection> listConnectionavailable;
	private ArrayList<Connection> listConnectionbusy;
	private ConnectionFileReader connectionfilereader;
	///// private static final int maxconnection = 5;
	private static int position = 0;

	public ArrayList<Connection> getListConnectionavailable() {
		return listConnectionavailable;
	}

	public void setListConnectionavailable(ArrayList<Connection> listConnectionavailable) {
		this.listConnectionavailable = listConnectionavailable;
	}

	public ArrayList<Connection> getListConnectionbusy() {
		return listConnectionbusy;
	}

	public void setListConnectionbusy(ArrayList<Connection> listConnectionbusy) {
		this.listConnectionbusy = listConnectionbusy;
	}

	///// Creer le pool de connection
	public JDBCConnectionPool() {
		listConnectionavailable = new ArrayList<Connection>();
		listConnectionbusy = new ArrayList<Connection>();
		connectionfilereader = new ConnectionFileReader();
	}

	///// Methode pour creer une connexion et la met dans larraylist
	public void fill() throws ClassNotFoundException {

		try {
			connectionfilereader.Read();
			for (int i = 0; i < 5; i++) {
				Class.forName(connectionfilereader.getProperty("driver"));
				Connection con = DriverManager.getConnection(connectionfilereader.getProperty("url"),
						connectionfilereader.getProperty("login"), connectionfilereader.getProperty("password"));
				listConnectionavailable.add(con);
				position = position + 1;
			}
			System.out.println(position + " connections has been created");
			System.out.println("");
		}

		catch (SQLException ex) {
			Logger.getLogger(JDBCConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	// methode qui renvoie un objet Connection specifique pris dans l'attribut
	public Connection getConnection(Connection connection) {
		try {
			listConnectionbusy.add(connection);
			listConnectionavailable.remove(connection);
		} catch (Exception e) {
			System.out.println("La connection que vous voulez utilise nexiste pas ou nest pas dispo");
		}
		return connection;

	}

	///// Methode qui renvoie un objet Connection le plus proche dispo dans
	///// l'attribut
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = listConnectionavailable.get(0);
			listConnectionbusy.add(connection);
			listConnectionavailable.remove(connection);
			System.out.println("available conection(s) : " + listConnectionavailable.size());
			System.out.println("busy connection(s) : " + listConnectionbusy.size());
			System.out.println("Total connection(s) : 5");
			System.out.println("");
		} catch (Exception e) {
			System.out.println("Il ny a pas de connexion dispo");
		}
		return listConnectionavailable.get(0);

	}

	// methode avec un parametre instance de la classe Connection qui remet cet
	// objet dans l'attribut

	public void setConnection(Connection connection) {
		this.listConnectionavailable.add(connection);
		this.listConnectionbusy.remove(connection);
	}

	// methode qui ferme toutes les connexions de l'attribut
	public void closeAllConnection() {
		for (Connection connection : listConnectionavailable) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
