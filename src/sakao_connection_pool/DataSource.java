package sakao_connection_pool;


import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

	private static JDBCConnectionPool instance = new JDBCConnectionPool();

	public DataSource() throws ClassNotFoundException {
		instance.fill();
	}

	// methode statique qui renvoie une connexion de l'attribut
	public Connection getConnection(String url) {
		return instance.getConnection(url);
	}

	// methode statique qui remet une connexion dans l'attribut
	public void reset(Connection con) {
		instance.setConnection(con);
	}

	// methode statique qui clot l'attribut.
	public static void closeInstance() {
		instance.closeAllConnection();
	}
	
	public void showPersonne() {
		System.out.println(instance.showPersonne().toString());
	}
	
	public void addPersonne(String name,int age) {
		Personne p = new Personne(name, age);
		instance.addPersonne(p);
	}
	
	public void deletePersonne(String name) {
		instance.deletePersonneByName(name);
	}
	
	public void updatePersonne(int id, String name, int age) {
		instance.updatePersonne(id, name, age);
	}
	

}
