package sakao_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

public class ClientThread extends Thread {
	private ServerSakao serversakao;
	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private JSONObject json;

	
	
	
	public void run() {
		try {
		//	clientSocket = this.serversakao.getServerSocket().accept();
			json = new JSONObject();
			out = new OutputStreamWriter(clientSocket.getOutputStream(),StandardCharsets.UTF_8);
			in = new BufferedReader (new InputStreamReader(clientSocket.getInputStream(),StandardCharsets.UTF_8));
			String inputLine;
			
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
