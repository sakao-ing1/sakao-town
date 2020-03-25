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
		setServerSocket(new ServerSocket(port));
		clientSocket = serverSocket.accept();
		json = new JSONObject();
		out = new OutputStreamWriter(clientSocket.getOutputStream(),StandardCharsets.UTF_8);
		in = new BufferedReader (new InputStreamReader(clientSocket.getInputStream(),StandardCharsets.UTF_8));
		this.sendMessageToClient();
		this.CloseConnection();
	}
	
	
	public String sendMessageToClient() throws IOException, JSONException {
		String arrival = in.readLine();
		if(arrival != null) {
			json = new JSONObject();
			json.put("message du servr", "Server a recu votre message");
			out.write(json.toString());
		}
		return json.toString() + " a ete recu du server";
	}
	
	
	public void CloseConnection() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
		serverSocket.close();
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
	}
	

}
