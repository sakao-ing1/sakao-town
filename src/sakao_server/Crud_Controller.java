 package sakao_server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sakao_common.Personne;
import sakao_connection_pool.DataSource;

public class Crud_Controller {

	public Crud_Controller() throws ClassNotFoundException {
	}

	// Requete SELECT

	public ArrayList<Personne> showPersonne() throws ClassNotFoundException {
		ArrayList<Personne> retour = new ArrayList<Personne>();
		try {
			PreparedStatement pt = DataSource.getConnection().prepareStatement("select * from personne");
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

	// Requete DELETE a ameliorer car supprimer par nom est dangereux
	public void deletePersonneByName(String name) throws ClassNotFoundException {
		try {
			PreparedStatement pt = DataSource.getConnection()
					.prepareStatement("delete from personne where name like ?");
			pt.setString(1, name);
			pt.execute();
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}

	}

	public void deletePersonneById(int ID) throws ClassNotFoundException {
		try {
			PreparedStatement pt = DataSource.getConnection().prepareStatement("delete from personne where id = " + ID);
			pt.execute();
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}

	}

	// Requete INSERT

	public void addPersonne(Personne p) throws ClassNotFoundException {
		try {

			String req = "insert into personne(name,age) values (?,?)";
			PreparedStatement pstm = DataSource.getConnection().prepareStatement(req);
			pstm.setString(1, p.getName());
			pstm.setInt(2, p.getAge());
			pstm.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// Requete UPDATE

	public void updatePersonneAge(int id, int age) throws ClassNotFoundException {
		try {
			PreparedStatement pstm = DataSource.getConnection()
					.prepareStatement(" UPDATE personne SET age = ?  WHERE id = ?");
			pstm.setInt(1, age);
			pstm.setInt(2, id);
			pstm.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void updatePersonneName(int id, String name) throws ClassNotFoundException {
		try {
			PreparedStatement pstm = DataSource.getConnection()
					.prepareStatement(" UPDATE personne SET name = ?  WHERE id = ?");
			pstm.setString(1, name);
			pstm.setInt(2, id);
			pstm.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}

	}

	public void deleteAllPersonne() throws SQLException {
			Statement query = DataSource.getListConnectionavailable().get(0).createStatement();
			int result = query.executeUpdate("TRUNCATE TABLE personne");
	}
}
