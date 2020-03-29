package sakao_server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sakao_common.Personne;
import sakao_connection_pool.DataSource;
import sakao_connection_pool.JDBCConnectionPool;

public class Crud_Controller {
	
	private  DataSource datasource;
	
	
	public Crud_Controller() {
		try {
			this.datasource = new DataSource();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	// Requete SELECT
	
	public ArrayList<Personne> showPersonne() {
		ArrayList<Personne> retour = new ArrayList<Personne>();
		try {
			PreparedStatement pt = this.datasource.getConnection()
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
	            PreparedStatement pt = this.datasource.getConnection().prepareStatement("delete from personne where name like ?");
	            pt.setString(1, name);
	            pt.execute();
	            System.out.println("removal by name done");
	        } catch (SQLException ex) {
	            System.out.println("erreur " + ex.getMessage());
	        }

	    }
	  
	  
	  public void deletePersonneById(int ID) {
	        try {
	            PreparedStatement pt = this.datasource.getConnection().prepareStatement("delete from personne where id = " + ID);
	            pt.execute();
	            System.out.println("removal by id done");
	        } catch (SQLException ex) {
	            System.out.println("erreur " + ex.getMessage());
	        }

	    }

	  
	  
	// Requete INSERT

  public void addPersonne(Personne p) {
      try {

          String req = "insert into personne(name,age) values (?,?)";
          PreparedStatement pstm = this.datasource.getConnection().prepareStatement(req);
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
          PreparedStatement pstm = this.datasource.getConnection().prepareStatement(" UPDATE personne SET age = ?  WHERE id = ?");
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
          PreparedStatement pstm = this.datasource.getConnection().prepareStatement(" UPDATE personne SET name = ?  WHERE id = ?");
          pstm.setString(1, name);
          pstm.setInt(2,id);
          pstm.executeUpdate();
          System.out.println("update name done");

      } catch (SQLException ex) {
          System.out.println("erreur " + ex.getMessage());
      }

  }
  
  
  public void deleteAllPersonne() throws SQLException {
  	Statement query = this.datasource.getListConnectionavailable().get(0).createStatement();
  	int result = query.executeUpdate("Delete from personne");
  	System.out.println("all rows deleted");
  	
  }





public DataSource getDatasource() {
	return datasource;
}


public void setDatasource(DataSource datasource) {
	this.datasource = datasource;
}
  
}
