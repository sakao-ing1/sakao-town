package sakao_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import sakao_common.Request;
import sakao_common.Response;

public class ServerSakao {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Request request = new Request();
	private Response response = new Response();
	private Crud_Service service;
	private ObjectMapper mapper;

	public void start(int port) throws IOException, JSONException {
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();
		out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
		System.out.println("Client connecte");
		///// System.out.println(this.sendMessageToClient());
		mapper = new ObjectMapper();
		String jsonString = in.readLine();
		System.out.println(jsonString);
		request = mapper.readValue(jsonString, Request.class);
		System.out.println("Message recu");
		String operation_type = request.getOperation_type();
		System.out.println(operation_type);
		service = new Crud_Service();
		this.response.setStudents(this.service.showPersonne());
		// this.response.setState(true);
		String outjsonString = mapper.writeValueAsString(response);
		out.write(outjsonString + "\n");
		out.flush();

	}

	public boolean sendMessageToClient() throws IOException, JSONException {
		boolean b = false;
		mapper = new ObjectMapper();
		String jsonString = in.readLine();
		request = mapper.readValue(jsonString, Request.class);
		System.out.println("Message recu");
		try {
			String operation_type = request.getOperation_type();
			service = new Crud_Service();
			this.response.setStudents(this.service.showPersonne());
			// this.response.setState(true);
			String outjsonString = mapper.writeValueAsString(response);
			out.write(outjsonString);
			out.flush();
			b = true;
		} catch (Exception e) {
		}

		return b;
	}

	public void CloseConnection() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
		serverSocket.close();
		System.out.println("Server deconnecte");
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public void StartCrud() throws JsonParseException, JsonMappingException, IOException {
		System.out.println("ESSAIE");
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
			break;
		}

	}

	public static void main(String[] args) {
		ServerSakao serveur1 = new ServerSakao();
		try {
			serveur1.start(3030);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// serveur1.CloseConnection();
	}

}
