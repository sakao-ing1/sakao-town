package sakao_server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import sakao_common.Personne;

public class Crud_Service {
	
	private  Crud_Controller controller;
	
	
	public ArrayList<Personne> showPersonne() {
		return 	controller.showPersonne();
	}
	
	public void addPersonne(String name,int age) {
		Personne p = new Personne(name, age);
		controller.addPersonne(p);
	}
	
	public void deletePersonneByName(String name) {
		controller.deletePersonneByName(name);
	}
	
	public void deletePersonneById(int ID) {
		controller.deletePersonneById(ID);
	}
	
	public void updatePersonneAge(int id, int age) {
		controller.updatePersonneAge(id, age);
	}
	
	public void deleteAllPersonne() {
		try {
			controller.deleteAllPersonne();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePersonneName(int id, String name) {
		controller.updatePersonneName(id, name);
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
				System.out.println("Veuillez renseigner un nom");
				String name = sc.next();
				System.out.println("Veuillez renseigner un age");
				int age = sc.nextInt();
				this.addPersonne(name, age);
				this.controller.getDatasource().reset(controller.getDatasource().getListConnectionbusy().get(controller.getDatasource().getListConnectionbusy().size() - 1));				
				System.out.println("********************");
				break;
				
			case 2 :
				this.showPersonne();
				this.controller.getDatasource().reset(controller.getDatasource().getListConnectionbusy().get(controller.getDatasource().getListConnectionbusy().size() - 1));

				System.out.println("********************");

				break;
				
			case 3 :
				System.out.println("Veuillez renseigner un id");
				int idtodelete = sc.nextInt();
				this.deletePersonneById(idtodelete);
				this.controller.getDatasource().reset(controller.getDatasource().getListConnectionbusy().get(controller.getDatasource().getListConnectionbusy().size() - 1));
				System.out.println("********************");

				break;
				
			case 4 :
				System.out.println("Veuillez renseigner un id");
				int idupdateage = sc.nextInt();
				System.out.println("");
				System.out.println("Veuillez renseigner le nouvel age");
				int ageupdateage = sc.nextInt();
				this.updatePersonneAge(idupdateage, ageupdateage);
				this.controller.getDatasource().reset(controller.getDatasource().getListConnectionbusy().get(controller.getDatasource().getListConnectionbusy().size() - 1));
				System.out.println("********************");

				break;
				
				
			case 5 :
				System.out.println("Veuillez renseigner un id");
				int idupdatename = sc.nextInt();
				System.out.println("");
				System.out.println("Veuillez renseigner le nouveau nom");
				String nameupdatename = sc.next();
				this.updatePersonneName(idupdatename, nameupdatename);
				this.controller.getDatasource().reset(controller.getDatasource().getListConnectionbusy().get(controller.getDatasource().getListConnectionbusy().size() - 1));
				System.out.println("********************");

				break;
				
			case 6 :
				this.deleteAllPersonne();
				/////this.reset(instance.getListConnectionbusy().get(instance.getListConnectionbusy().size() - 1));
				break;
				
				
				
			case 7 :
			System.out.println("Vous avez quitte le menu");
			
			}

		
		}
		
		
		
	}
	

}
