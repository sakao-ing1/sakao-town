package sakao_connection_pool;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class DataSource {

	private static JDBCConnectionPool instance; 

	public DataSource() throws ClassNotFoundException {
		instance = new JDBCConnectionPool();
		instance.fill();
	}

	// methode statique qui renvoie une connexion de l'attribut
	public Connection getConnection() {
		return instance.getConnection();
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
	
	public void addPersonne(int id,String name,int age) {
		Personne p = new Personne(id,name, age);
		instance.addPersonne(p);
	}
	
	public void deletePersonneByName(String name) {
		instance.deletePersonneByName(name);
	}
	
	public void deletePersonneById(int ID) {
		instance.deletePersonneById(ID);
	}
	
	public void updatePersonneAge(int id, int age) {
		instance.updatePersonneAge(id, age);
	}
	
	public void deleteAllPersonne() {
		try {
			instance.deleteAllPersonne();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePersonneName(int id, String name) {
		instance.updatePersonneName(id, name);
	}
	
	public void StartCRUD() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU CRUD");
		int choice  = 0;

		while(choice < 7 && choice >=0) {
			System.out.println("1.Ajouter une personne");
			System.out.println("2.Consulter la liste des personnes ");
			System.out.println("3.Supprimer une personnes");
			System.out.println("4.Modifier lage dune personne");
			System.out.println("5.Modifier le nom dune personne");
			System.out.println("6.Supprimer toutes les lignes");
			System.out.println("7.Fin");
			System.out.println("********************");
			System.out.println("");

			
			int choix = sc.nextInt();
			choice = choix;
			
			
			
			switch(choix) {
			
			
			case 1 :
				System.out.println("Veuillez renseigner un id");
				int id = sc.nextInt();
				System.out.println("Veuillez renseigner un nom");
				String name = sc.next();
				System.out.println("Veuillez renseigner un age");
				int age = sc.nextInt();
				this.addPersonne(id, name, age);
				System.out.println("********************");
				break;
				
			case 2 :
				this.showPersonne();
				System.out.println("********************");

				break;
				
			case 3 :
				System.out.println("Veuillez renseigner un id");
				int idtodelete = sc.nextInt();
				this.deletePersonneById(idtodelete);
				System.out.println("********************");

				break;
				
			case 4 :
				System.out.println("Veuillez renseigner un id");
				int idupdateage = sc.nextInt();
				System.out.println("");
				System.out.println("Veuillez renseigner le nouvel age");
				int ageupdateage = sc.nextInt();
				this.updatePersonneAge(idupdateage, ageupdateage);
				System.out.println("********************");

				break;
				
				
			case 5 :
				System.out.println("Veuillez renseigner un id");
				int idupdatename = sc.nextInt();
				System.out.println("");
				System.out.println("Veuillez renseigner le nouveau nom");
				String nameupdatename = sc.next();
				this.updatePersonneName(idupdatename, nameupdatename);
				System.out.println("********************");

				break;
				
			case 6 :
				this.deleteAllPersonne();
				break;
				
				
				
			case 7 :
			System.out.println("Vous avez quitte le menu");
			
			}

		
		}
		
		
		
	}
	

}
