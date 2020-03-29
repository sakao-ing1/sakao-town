package sakao_server;

import java.io.IOException;
import java.net.ServerSocket;

import org.json.JSONException;

public class ServerSakao {
	private ServerSocket serverSocket;
	///private Socket clientSocket;
	///private OutputStreamWriter out;
	///private BufferedReader in;
	/*private Request request = new Request();
	private Response response = new Response();
	private Crud_Service service;
	private ObjectMapper mapper;*/

	public void start(int port) throws IOException, JSONException {
		serverSocket = new ServerSocket(port);
		/*clientSocket = serverSocket.accept();
		out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
		System.out.println("Client connected");
		while(true) {
			this.StartCrud();
		}
		*/
		while(true) {
        	new ClientThread(serverSocket.accept()).start();
		}		
	}

	/*public boolean sendMessageToClient() throws IOException, JSONException {
		boolean b = false;
		mapper = new ObjectMapper();
		String jsonString = in.readLine();
		request = mapper.readValue(jsonString, Request.class);
		System.out.println("Message recu");
		try {
			String operation_type = request.getOperation_type();
			service = new Crud_Service();
			this.response.setStudents(this.service.showPersonne(this.request.getTarget()));
			// this.response.setState(true);
			String outjsonString = mapper.writeValueAsString(response);
			out.write(outjsonString);
			out.flush();
			b = true;
		} catch (Exception e) {
		}

		return b;
	}*/

	public void CloseConnection() throws IOException {
		/*in.close();
		out.close();
		clientSocket.close();*/
		serverSocket.close();
		System.out.println("Server deconnecte");
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	/*public void StartCrud() throws JsonParseException, JsonMappingException, IOException {
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
			
			
		case "INSERT" :
			service = new Crud_Service();
			System.out.println(this.service.addPersonne(this.request.getName(), this.request.getAge()));
			String outjsonStringInsert = mapper.writeValueAsString(response);
			out.write(outjsonStringInsert + "\n");
			out.flush();
			break;
		
		
		case "DELETE_ALL" : 
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
		
		
		case "UPDATE_AGE" :
			service = new Crud_Service();
			System.out.println(this.service.updatePersonneAge(this.request.getID(), this.request.getAge()));
			String outjsonStringUpdateAge = mapper.writeValueAsString(response);
			out.write(outjsonStringUpdateAge + "\n");
			out.flush();
			break;
		
		
		case "UPDATE_NAME" :
			service = new Crud_Service();
			System.out.println(this.service.updatePersonneName(this.request.getID(), this.request.getName()));
			String outjsonStringUpdateName = mapper.writeValueAsString(response);
			out.write(outjsonStringUpdateName + "\n");
			out.flush();
			break;

		}

	}*/

	public static void main(String[] args) throws IOException, JSONException {
		ServerSakao serveur1 = new ServerSakao();
		
			serveur1.start(3030);
			serveur1.CloseConnection();

	}

}
