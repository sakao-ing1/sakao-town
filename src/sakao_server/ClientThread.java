package sakao_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

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

	public ClientThread(Socket socket) {
		super("ClientConnection" + position);
		position++;
		this.clientSocket = socket;
	}

	public void StartCrud() throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException {
		mapper = new ObjectMapper();
		String jsonString = in.readLine();
		request = mapper.readValue(jsonString, Request.class);
		System.out.println("Message recu");
		String operation_type = request.getOperation_type();

		switch (operation_type) {

		case "SELECT_ALL":
			service = new Crud_Service();
			response.setStudents(this.service.showPersonne());

			// response.setState(true);

			String outjsonStringSelectAll = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectAll + "\n");
			out.flush();
			break;

		case "INSERT":
			service = new Crud_Service();
			System.out.println(this.service.addPersonne(this.request.getName(), this.request.getAge()));
			String outjsonStringInsert = mapper.writeValueAsString(response);
			out.write(outjsonStringInsert + "\n");
			out.flush();
			break;

		case "DELETE_ALL":
			service = new Crud_Service();
			System.out.println(this.service.deleteAllPersonne());
			String outjsonStringDeleteAll = mapper.writeValueAsString(response);
			out.write(outjsonStringDeleteAll + "\n");
			out.flush();
			break;

		case "DELETE":
			service = new Crud_Service();
			System.out.println(this.service.deletePersonneById(this.request.getID()));
			String outjsonStringDeleteAStudent = mapper.writeValueAsString(response);
			out.write(outjsonStringDeleteAStudent + "\n");
			out.flush();
			break;

		case "UPDATE_AGE":
			service = new Crud_Service();
			System.out.println(this.service.updatePersonneAge(this.request.getID(), this.request.getAge()));
			String outjsonStringUpdateAge = mapper.writeValueAsString(response);
			out.write(outjsonStringUpdateAge + "\n");
			out.flush();
			break;

		case "UPDATE_NAME":
			service = new Crud_Service();
			System.out.println(this.service.updatePersonneName(this.request.getID(), this.request.getName()));
			String outjsonStringUpdateName = mapper.writeValueAsString(response);
			out.write(outjsonStringUpdateName + "\n");
			out.flush();
			break;

		}
	}

	public void run() {
		try {
			out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.println("Client connected");
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
		}
	}
}
