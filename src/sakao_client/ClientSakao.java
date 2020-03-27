package sakao_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import sakao_common.Request;
import sakao_common.Response;

public class ClientSakao {
		private Socket clientSocket;
		private OutputStreamWriter out;
		private BufferedReader in;
		private Request request;
		private Response response;
		private ObjectMapper mapper;
		private final static String SELECT_ALL = "SELECT_ALL"; 
		private final static String DELETE_ALL = "DELETE_ALL";
		private final static String INSERT = "INSERT";
		private final static String DELETE = "DELETE";
		private final static String UPDATE_NAME = "UPDATE_NAME";
		private final static String UPDATE_AGE= "UPDATE_AGE";
		


		
		public void startConnection(String ip,int port) throws IOException, JSONException {

			System.out.println("Connexion au server");
			clientSocket = new Socket(ip,port);
			out = new OutputStreamWriter(clientSocket.getOutputStream(),StandardCharsets.UTF_8);
			in = new BufferedReader (new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
			System.out.println("Connexion au serveur reussi");
			this.StartHMI();

		}
		
	/////IMPORTANT NOUS FAISON UNE METHODE POUR CHAQUE ACTION DU CRUD A OPTIMISER POUR EN FAIRE QUUNE SEULE
		
		/* Si le client tape SELECT ALL OU DELETE ALL : request.operation_type = "Select_all" "Delete_All */
		public String sendSelectAllMessageToServer(String table) throws IOException, JSONException { 
			mapper = new ObjectMapper();
			request = new Request(SELECT_ALL,table);
			String outjsonString = mapper.writeValueAsString(request);
			out.write(outjsonString);
			out.flush();
			System.out.println("REQUETE ENVOYE");
			String injsonString = in.readLine();
			response = new Response();
			response = mapper.readValue(injsonString, Response.class);
			return response.toString();
		}
		
		
		public String sendDeleteAllMessageToServer(String table) throws IOException, JSONException { 
			mapper = new ObjectMapper();
			request = new Request(DELETE_ALL,table);
			String outjsonString = mapper.writeValueAsString(request);
			out.write(outjsonString);
			out.flush();
			String injsonString = in.readLine();
			response = new Response();
			response = mapper.readValue(injsonString, Response.class);
			return response.toString();
		}
		
		
		public String sendInsertMessageToServer(String table, String name, int age) throws IOException {
			mapper = new ObjectMapper();
			request = new Request(INSERT,table,name,age);
			String outjsonString = mapper.writeValueAsString(request);
			out.write(outjsonString);
			out.flush();
			String injsonString = in.readLine();
			response = mapper.readValue(injsonString, Response.class);
			return response.toString();
		}
		
		
		public String sendDeleteAStudentMessageToServer(String table,int id) throws IOException {
			mapper = new ObjectMapper();
			request = new Request(DELETE ,table,id);
			String outjsonString = mapper.writeValueAsString(request);
			out.write(outjsonString);
			out.flush();
			String injsonString = in.readLine();
			response = mapper.readValue(injsonString, Response.class);
			return response.toString();
		}
		
		public String sendUpdateANameMessageToServer(String table,int id,String name) throws IOException {
			mapper = new ObjectMapper();
			request = new Request(UPDATE_NAME ,table,id,name);
			String outjsonString = mapper.writeValueAsString(request);
			out.write(outjsonString);
			out.flush();
			String injsonString = in.readLine();
			response = mapper.readValue(injsonString, Response.class);
			return response.toString();
		}
		
		public String sendUpdateANameMessageToServer(String table,int id, int age) throws IOException {
			 mapper = new ObjectMapper();
			request = new Request(UPDATE_AGE ,table,id,age);
			String outjsonString = mapper.writeValueAsString(request);
			out.write(outjsonString);
			out.flush();
			String injsonString = in.readLine();
			response = mapper.readValue(injsonString, Response.class);
			return response.toString();
		}
		
		
		public void CloseConnection() throws IOException {
			System.out.println("Deconnexion");
			out.close();
			in.close();
			clientSocket.close();
			System.out.println("Client deconnecte");
		}
			
		
		public void StartHMI() {
			
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

					System.out.println("********************");
					break;
					
				case 2 :
					System.out.println("Veuillez renseigner la table");
					String table = sc.next();
					try {
						this.sendSelectAllMessageToServer(table);
					} catch (IOException | JSONException e) {
						e.printStackTrace();
					}
					System.out.println("********************");
					break;
					
				case 3 :
					System.out.println("Veuillez renseigner un id");
					int idtodelete = sc.nextInt();
					System.out.println("********************");

					break;
					
				case 4 :
					System.out.println("Veuillez renseigner un id");
					int idupdateage = sc.nextInt();
					System.out.println("");
					System.out.println("Veuillez renseigner le nouvel age");
					int ageupdateage = sc.nextInt();
					System.out.println("********************");

					break;
					
					
				case 5 :
					System.out.println("Veuillez renseigner un id");
					int idupdatename = sc.nextInt();
					System.out.println("");
					System.out.println("Veuillez renseigner le nouveau nom");
					String nameupdatename = sc.next();
					System.out.println("********************");

					break;
					
				case 6 :
					break;
					
					
					
				case 7 :
					try {
						this.CloseConnection();
					} catch (IOException e) {
						e.printStackTrace();
					}
				System.out.println("Vous avez quitte le menu");
				
				}

			
			}
			
			
			
		}
		
		public static void main(String[] args) throws IOException, JSONException {
			ClientSakao client1 = new ClientSakao();
			client1.startConnection("localhost", 3030);			

		}
}
