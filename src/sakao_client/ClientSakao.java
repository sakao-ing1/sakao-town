package sakao_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import sakao_common.Request;
import sakao_common.Response;

public class ClientSakao {
		private Socket clientSocket;
		private OutputStreamWriter out;
		private BufferedReader in;
		private Request request;
		private Response response;
		private final static String SELECT_ALL = "SELECT_ALL"; 
		private final static String DELETE_ALL = "DELETE_ALL";

		
		public void startConnection(String ip,int port,String a, int b) throws IOException, JSONException {
			System.out.println("Connexion au server");
			clientSocket = new Socket(ip,port);
			out = new OutputStreamWriter(clientSocket.getOutputStream(),StandardCharsets.UTF_8);
			in = new BufferedReader (new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
			System.out.println("Connexion au serveur reussi");
			///System.out.println(this.sendMessageToServer(a, b));
			///System.out.println(this.readMessageFromServer());
			this.CloseConnection();
		}
		
		/* Si le client tape 1 : request.operation_type = "Select_all" */
		public String sendMessageToServer(String table, int id,String name, int age) throws IOException, JSONException { 
			ObjectMapper mapper = new ObjectMapper();
			request = new Request(table,id,name,age);
			String jsonString = mapper.writeValueAsString(request);
			out.write(jsonString);
			out.flush();
			return jsonString ;
		}
		
		
		/*public String readMessageFromServer() throws JSONException, IOException {
			String line = in.readLine();
			///JSonObj= new JSONObject(line);
			///return JSonObj.toString(2) + " a ete recu en provenance du serveur";
		}*/
		
		
		public void CloseConnection() throws IOException {
			System.out.println("Deconnexion");
			out.close();
			in.close();
			clientSocket.close();
			System.out.println("Client deconnecte");
		}
		
		
		public static void main(String[] args) throws IOException, JSONException {
			ClientSakao client1 = new ClientSakao();
			client1.startConnection("localhost", 3030,"Alain",23);			

		}
	
}
