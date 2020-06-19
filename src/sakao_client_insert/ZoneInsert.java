package sakao_client_insert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import sakao_common.Request;
import sakao_common.Response;

public class ZoneInsert {
	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Response response = new Response();

	private ObjectMapper mapper;

	public void startConnection(String ip, int port) throws IOException, JSONException {
		System.out.println("waiting for connection in to the server");
		clientSocket = new Socket(ip, port);
		out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
		System.out.println("connection succeed");
		
	}

	public String sendMessageToServer(Request request) throws IOException {
		mapper = new ObjectMapper();
		String outjsonString = mapper.writeValueAsString(request);
		System.out.println("REQUEST SENT");
		System.out.println(outjsonString);
		System.out.println(" _____");
		out.write(outjsonString + "\n");
		out.flush();
		String injsonString = in.readLine();
		response = mapper.readValue(injsonString, Response.class);
		return response.toString();
	}

	public void CloseConnection() throws IOException {
		System.out.println("waiting for disconnection");
		out.close();
		in.close();
		clientSocket.close();
		System.out.println("disconnected");
	}


	public static void main(String[] args) throws IOException, JSONException {
		ZoneInsert client1 = new ZoneInsert();
		client1.startConnection("localhost", 3030);
		TablesToBeInserted table = new TablesToBeInserted();
		Request req1 = new ObjectMapper().readValue(table.readFileZone(), Request.class);
		client1.sendMessageToServer(req1);
		System.out.println("Insert done");
		System.out.println("********************");
		client1.CloseConnection();
		
		
	}
	
	
}
