package sakao_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import sakao_common.Request;
import sakao_common.Response;

public class ClientThread extends Thread {
	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Request request = new Request();
	private Response response = new Response();
	private Crud_Service service;
	private ObjectMapper mapper;
	private static int position = 1;
	private boolean shouldRun = true;
	///// private Connection con; ON PEUT AJOTUER UNE CONNECTION

	public ClientThread(Socket socket) throws ClassNotFoundException {
		super("Client" + position);
		position++;
		this.clientSocket = socket;
		service = new Crud_Service();
	}

	public void StartCrud()
			throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException, NullPointerException {
		mapper = new ObjectMapper();
		String jsonString = in.readLine();
		request = mapper.readValue(jsonString, Request.class);
		System.out.println("Request received from " + this.getName());
		String operation_type = request.getOperation_type();
		

		switch (operation_type) {

		case "SELECT_ALL":
			response.setStudents(this.service.showPersonne());

			// response.setState(true);

			String outjsonStringSelectAll = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectAll + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;

		case "INSERT":
			try {
			System.out.println(this.service.addPersonne(this.request.getName(),this.request.getAge()));
			String outjsonStringInsert = mapper.writeValueAsString(response);
			out.write(outjsonStringInsert + "\n");
			out.flush();
			System.out.println("Insert done for " + this.getName());
			System.out.println("********************");
			}
			catch(Exception e) {}

			break;

		case "DELETE_ALL":
			System.out.println(this.service.deleteAllPersonne());
			String outjsonStringDeleteAll = mapper.writeValueAsString(response);
			out.write(outjsonStringDeleteAll + "\n");
			out.flush();
			System.out.println("All rows deleted for " + this.getName());
			System.out.println("********************");


			break;

		case "DELETE":
			System.out.println(this.service.deletePersonneById(this.request.getID()));
			String outjsonStringDeleteAStudent = mapper.writeValueAsString(response);
			out.write(outjsonStringDeleteAStudent + "\n");
			out.flush();
			System.out.println("A row deleted for " + this.getName());
			System.out.println("********************");


			break;

		case "UPDATE_AGE":
			System.out.println(this.service.updatePersonneAge(this.request.getID(), this.request.getAge()));
			String outjsonStringUpdateAge = mapper.writeValueAsString(response);
			out.write(outjsonStringUpdateAge + "\n");
			out.flush();
			System.out.println("Update age done for " + this.getName());
			System.out.println("********************");


			break;

		case "UPDATE_NAME":
			System.out.println(this.service.updatePersonneName(this.request.getID(), this.request.getName()));
			String outjsonStringUpdateName = mapper.writeValueAsString(response);
			out.write(outjsonStringUpdateName + "\n");
			out.flush();
			System.out.println("Update name done for " + this.getName());
			System.out.println("********************");

			break;

		}
	}

	public void run() {
		try {
			out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.println(this.getName() + " connected");
			System.out.println("********************");

			///// con = DataSource.getConnection();
			while (shouldRun) {
				this.StartCrud();
			}

			in.close();
			out.close();
			clientSocket.close();

		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println( this.getName()+ " disconnected");
			System.out.println("********************");

		}
	}
}
