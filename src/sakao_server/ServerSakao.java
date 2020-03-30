package sakao_server;

import java.io.IOException;
import java.net.ServerSocket;

import org.json.JSONException;

public class ServerSakao {
	private ServerSocket serverSocket;

	public void start(int port) throws IOException, JSONException {
		serverSocket = new ServerSocket(port);
		while (true) {
			new ClientThread(serverSocket.accept()).start();
		}
	}

	public void CloseConnection() throws IOException {
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
		serveur1.CloseConnection();
	}

}
