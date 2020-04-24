package sakao_client;

import java.io.IOException;
import java.util.Scanner;

import sakao_common.Request;

public class IhmBorneCapteur {

	public IhmBorneCapteur() {

	}

	public void  start(){
		Scanner sc = new Scanner(System.in);

		System.out.println("Configuration des bornes et des capteurs");
		int choice = 0;
		while (choice < 4 && choice >= 0) {
			System.out.println("1.Configuration des capteurs et des bornes d'entrée");
			System.out.println("2.Configuration des capteurs et des bornes de sortie");
			System.out.println("3.Retour à l'accueil ");
			System.out.println("4.Log out");
			System.out.println("********************");
			System.out.println("");
			
			int choix = sc.nextInt();

			choice = choix;
			 
			switch (choix) {

			case 1:
				System.out.println("Aficher Tableau Entrée"); 

				break;

			case 2:
				System.out.println("Aficher Tableau  Sortie"); 

				break;

			case 3:
				System.out.println("Retour Acceuil");

				break;


				}
		}
	}
}

