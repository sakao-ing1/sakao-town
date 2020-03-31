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
	private Response response = new Response();

	private ObjectMapper mapper;
	private final static String SELECT_ALL = "SELECT_ALL";
	private final static String DELETE_ALL = "DELETE_ALL";
	private final static String INSERT = "INSERT";
	private final static String DELETE = "DELETE";
	private final static String UPDATE_NAME = "UPDATE_NAME";
	private final static String UPDATE_AGE = "UPDATE_AGE";

	public void startConnection(String ip, int port) throws IOException, JSONException {
		System.out.println("Log in to the server");
		clientSocket = new Socket(ip, port);
		out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
		System.out.println("Log in succeed");
		this.StartHMI();
	}

	///// IMPORTANT NOUS FAISON UNE METHODE POUR CHAQUE ACTION DU CRUD A OPTIMISER
	///// POUR EN FAIRE QUUNE SEULE

	/*
	 * Si le client tape SELECT ALL OU DELETE ALL : request.operation_type =
	 * "Select_all" "Delete_All
	 */

	public String sendMessageToServer(Request request) throws IOException {
		mapper = new ObjectMapper();
		String outjsonString = mapper.writeValueAsString(request);
		///// System.out.println(request);
		out.write(outjsonString + "\n");
		out.flush();
		System.out.println("REQUEST SENT");
		String injsonString = in.readLine();
		System.out.println(injsonString);
		response = mapper.readValue(injsonString, Response.class);
		return response.toString();
	}

	public void CloseConnection() throws IOException {
		System.out.println("Log out");
		out.close();
		in.close();
		clientSocket.close();
		System.out.println("Client disconnected");
	}

	public void StartHMI() {
		Scanner sc = new Scanner(System.in);
		System.out.println("CRUD MENU");
		int choice = 0;

		while (choice < 7 && choice >= 0) {
			System.out.println("1.Add a student");
			System.out.println("2.View all students");
			System.out.println("3.Delete a student");
			System.out.println("4.Update the age of a student");
			System.out.println("5.Update the name of a student");
			System.out.println("6.Delete all students");
			System.out.println("7.Log out");
			System.out.println("********************");
			System.out.println("");

			int choix = sc.nextInt();
			choice = choix;

			switch (choix) {

			case 1:
				System.out.println("Please enter the table");
				String tableInsert = sc.next();
				System.out.println("Please enter the name");
				String nameInsert = sc.next();
				System.out.println("Please enter the age");
				int ageInsert = sc.nextInt();
				try {
					Request request = new Request(INSERT, tableInsert, nameInsert, ageInsert);
					this.sendMessageToServer(request);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				System.out.println("Insert done");
				System.out.println("********************");
				break;

			case 2:///// OK
				System.out.println("Please enter the table");
				String tableSelect = sc.next();
				try {
					Request request = new Request(SELECT_ALL, tableSelect);
					this.sendMessageToServer(request);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("Display done");
				System.out.println("********************");
				break;

			case 3:
				System.out.println("Please enter the table");
				String tableDeleteAStudent = sc.next();
				System.out.println("Please enter the id");
				int idtodelete = sc.nextInt();
				try {
					Request request = new Request(DELETE, tableDeleteAStudent, idtodelete);
					this.sendMessageToServer(request);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				System.out.println("Delete done");
				System.out.println("********************");

				break;

			case 4:
				System.out.println("Please enter the table");
				String TableUpdateAge = sc.next();
				System.out.println("Please enter the id");
				int idupdateage = sc.nextInt();
				System.out.println("Please enter the new age");
				int ageupdateage = sc.nextInt();
				try {
					Request request = new Request(UPDATE_AGE, TableUpdateAge, idupdateage, ageupdateage);
					this.sendMessageToServer(request);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				System.out.println("Update age done");
				System.out.println("********************");

				break;

			case 5:
				System.out.println("Please enter the table");
				String TableUpdateName = sc.next();
				System.out.println("Please enter the id");
				int idupdatename = sc.nextInt();
				System.out.println("");
				System.out.println("Please enter the new name");
				String nameupdatename = sc.next();
				try {
					Request request = new Request(UPDATE_NAME, TableUpdateName, idupdatename, nameupdatename);
					this.sendMessageToServer(request);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				System.out.println("Update name done");
				System.out.println("********************");

				break;

			case 6:
				System.out.println("Please enter the table");
				String tableDeleteAll = sc.next();
				try {
					Request request = new Request(DELETE_ALL, tableDeleteAll);
					this.sendMessageToServer(request);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println("Delete all done");
				System.out.println("********************");
				break;

			case 7: ///// OK
				try {
					this.CloseConnection();
					sc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("********************");
				System.out.println("You left the menu, see you soon !");

			}

		}

	}

	public static void main(String[] args) throws IOException, JSONException {
		ClientSakao client1 = new ClientSakao();
		client1.startConnection("localhost", 3030);

	}
}
