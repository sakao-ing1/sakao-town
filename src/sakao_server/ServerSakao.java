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
import sakao_connection_pool.DataSource;

public class ServerSakao {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Request request;
	private Response response;
	private Crud_Service service;
	private ObjectMapper mapper;

	


	public void start(int port) throws IOException, JSONException {
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();
		out = new OutputStreamWriter(clientSocket.getOutputStream(),StandardCharsets.UTF_8);
		in = new BufferedReader (new InputStreamReader(clientSocket.getInputStream(),StandardCharsets.UTF_8));
		System.out.println("Client connecte");
		System.out.println(this.sendMessageToClient());
		
		/////System.out.println(this.sendMessageToClient());
	}
	
	
	public boolean sendMessageToClient() throws IOException, JSONException {
		boolean b = false;
		this.StartCrud();
		try {
			String outjsonString = mapper.writeValueAsString(response);
			out.write(outjsonString);
			out.flush();
			b = true;
		}
		catch(Exception e) {
		e.printStackTrace();
		}
		
		return  b;
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
	
	public static void main(String[] args) throws IOException, JSONException {
		ServerSakao serveur1 = new ServerSakao();
		serveur1.start(3030);
		/////System.out.println(serveur1.sendMessageToClient());
		serveur1.CloseConnection();
	}
	
	
	public void StartCrud() throws JsonParseException, JsonMappingException, IOException {
		mapper = new ObjectMapper();
		String jsonString = in.readLine();
		request = mapper.readValue(jsonString,Request.class);
		String operation_type = request.getOperation_type();
		
		
		switch(operation_type) {
		
		case "SELECT_ALL" :
			response.setStudents(this.service.showPersonne()); 
			response.setState(true);
			break;
		}
		
	}
	

}
