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

	ConnectionFileReader conFileRead = ConnectionFileReader.getInstance();

	// methode qui remplit l'attribut avec un certain nombre d'instances de la
	// classe Connection
	public void fill() throws ClassNotFoundException {
		try {

			Class.forName(ConnectionFileReader.getDriver());
			Connection con = DriverManager.getConnection(ConnectionFileReader.getUrl(), ConnectionFileReader.getLogin(), ConnectionFileReader.getPassword());
			listConnection.add(con);
			System.out.println("connexion établie!!!!");

		} catch (SQLException ex) {
			Logger.getLogger(JDBCConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	// methode qui renvoie un objet Connection pris dans l'attribut
	public Connection getConnection(String url) {
		for (Connection con : listConnection) {
			String url2;
			try {
				url2 = con.getMetaData().getURL();
				if (url2.equals(url)) {
					return con;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// methode avec un parametre instance de la classe Connection qui remet cet
	// objet dans l'attribut
	public void setConnection(Connection connection) {
		this.listConnection.add(connection);
	}

	// methode qui ferme toutes les connexions de l'attribut
	public void closeAllConnection() {
		for (Connection connection : listConnection) {
			try {
				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
