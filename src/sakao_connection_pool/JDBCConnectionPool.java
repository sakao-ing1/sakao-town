package sakao_connection_pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
			Connection con = DriverManager.getConnection(ConnectionFileReader.getUrl(), ConnectionFileReader.getLogin(),
					ConnectionFileReader.getPassword());
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

	// Partie CRUD

	// Requete SELECT
	public List<Personne> showPersonne() {
		List<Personne> retour = new ArrayList<Personne>();
		try {
			PreparedStatement pt = this.getConnection(ConnectionFileReader.getUrl())
					.prepareStatement("select * from personne");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				retour.add(new Personne(id, name, age));
			}
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;
	}

	// Requete DELETE
	  public void deletePersonneByName(String name) {
	        try {
	            PreparedStatement pt = this.getConnection(ConnectionFileReader.getUrl()).prepareStatement("delete from personne where name like ?");
	            pt.setString(1, name);
	            pt.execute();
	        } catch (SQLException ex) {
	            System.out.println("erreur " + ex.getMessage());
	        }

	    }

	// Requete CREATE

    public void addPersonne(Personne p) {
        try {

            String req = "insert into personne(name,age) values (?,?)";
            PreparedStatement pstm = this.getConnection(ConnectionFileReader.getUrl()).prepareStatement(req);
            pstm.setString(1, p.getName());
            pstm.setInt(2, p.getAge());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

	// Requete UPDATE
    
    public void updatePersonne(int id, String name, int age) {
        try {
            PreparedStatement pstm = this.getConnection(ConnectionFileReader.getUrl()).prepareStatement(" UPDATE personne SET name= ? ,age= ? WHERE id=? ");
            pstm.setString(1, name);
            pstm.setInt(2, age);
            pstm.setInt(3, id);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
    }

}
