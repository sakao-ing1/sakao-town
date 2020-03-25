package sakao_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.json.JSONException;
import org.json.JSONObject;

public class ServerSakao {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private JSONObject json;


	public void start(int port) throws IOException, JSONException {
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();
		out = new OutputStreamWriter(clientSocket.getOutputStream(),StandardCharsets.UTF_8);
		in = new BufferedReader (new InputStreamReader(clientSocket.getInputStream(),StandardCharsets.UTF_8));
		System.out.println("Client connecte");
		System.out.println(this.sendMessageToClient());
		
		/////System.out.println(this.sendMessageToClient());
	}
	
	
	public String sendMessageToClient() throws IOException, JSONException {

		json = new JSONObject();
		json.put("message du servr", "Server a recu votre message");
		out.write(json.toString());
		out.flush();
		return json.toString() + " a ete envoye par le server";
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
	

}
