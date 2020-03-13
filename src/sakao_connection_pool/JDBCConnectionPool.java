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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnectionPool {

	private ArrayList<Connection> listConnectionavailable;
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


	private ArrayList<Connection> listConnectionbusy;
	private ConnectionFileReader connectionfilereader;
	/////private static final int maxconnection = 5;
	private static int position = 0;

	/////Creer le pool de connection
	public JDBCConnectionPool() {
		listConnectionavailable = new ArrayList<Connection>();
		listConnectionbusy = new ArrayList<Connection>();
		connectionfilereader = new ConnectionFileReader();
	}
	
	

	/////Methode pour creer une connexion et la met dans larraylist
	public void fill() throws ClassNotFoundException {
		
		try {
			connectionfilereader.Read();
			for(int i = 0; i < 5; i++) {	
				Class.forName(connectionfilereader.getProperty("driver"));
				Connection con = DriverManager.getConnection(connectionfilereader.getProperty("url"), connectionfilereader.getProperty("login"),connectionfilereader.getProperty("password"));
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
		}
		catch(Exception e) {
			System.out.println("La connection que vous voulez utilise nexiste pas ou nest pas dispo");
		}
		return connection;
			
	}
	
	
	/////Methode qui renvoie un objet Connection le plus proche dispo dans l'attribut
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
		}
		catch(Exception e) {
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
	
	
	

	                                                                 // Partie CRUD

	
	
	
	// Requete SELECT
	
	public ArrayList<Personne> showPersonne() {
		ArrayList<Personne> retour = new ArrayList<Personne>();
		try {
			PreparedStatement pt = this.getConnection()
					.prepareStatement("select * from personne");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				retour.add(new Personne(id, name, age));
			}
			System.out.println("display done");

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;
		
	}
	

	// Requete DELETE a ameliorer car supprimer par nom est dangereux
	  public void deletePersonneByName(String name) {
	        try {
	            PreparedStatement pt = this.getConnection().prepareStatement("delete from personne where name like ?");
	            pt.setString(1, name);
	            pt.execute();
	            System.out.println("removal by name done");
	        } catch (SQLException ex) {
	            System.out.println("erreur " + ex.getMessage());
	        }

	    }
	  
	  
	  public void deletePersonneById(int ID) {
	        try {
	            PreparedStatement pt = this.getConnection().prepareStatement("delete from personne where id = " + ID);
	            pt.execute();
	            System.out.println("removal by id done");
	        } catch (SQLException ex) {
	            System.out.println("erreur " + ex.getMessage());
	        }

	    }

	  
	  
	// Requete CREATE

    public void addPersonne(Personne p) {
        try {

            String req = "insert into personne(name,age) values (?,?)";
            PreparedStatement pstm = this.getConnection().prepareStatement(req);
            pstm.setString(1, p.getName());
            pstm.setInt(2, p.getAge());
            pstm.executeUpdate();
            System.out.println("addition done");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    

	// Requete UPDATE
    
    public void updatePersonneAge(int id,int age) {
        try {
            PreparedStatement pstm = this.getConnection().prepareStatement(" UPDATE personne SET age = ?  WHERE id = ?");
            pstm.setInt(1, age);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("update age done");
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
    }
    
    
    public void updatePersonneName(int id,String name) {
        try {
            PreparedStatement pstm = this.getConnection().prepareStatement(" UPDATE personne SET name = ?  WHERE id = ?");
            pstm.setString(1, name);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("update name done");

        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }

    }
    
    
    public void deleteAllPersonne() throws SQLException {
    	Statement query = this.listConnectionavailable.get(0).createStatement();
    	int result = query.executeUpdate("Delete from personne");
    	System.out.println("all rows deleted");
    }
    
 

	/*public static int getMaxconnection() {
		return maxconnection;
	}*/
    

}
