package sakao_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import sakao_common.Request;
import sakao_common.Response;
import sakao_insert_client.TablesToBeInserted;

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

	public ClientThread(Socket socket) throws ClassNotFoundException {
		super("Client" + position);
		position++;
		this.clientSocket = socket;
		service = new Crud_Service();
	}

	public void CrudConfiguration(String operation_type, String target, ArrayList<String> list)
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {
		CrudConfiguration crudConfiguration = new CrudConfiguration();
		switch (operation_type) {
		case "SELECT_ALL":
			response.setObject(crudConfiguration.showAllConfiguration());
			String outjsonStringSelectAll = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectAll + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;
		case "INSERT":
			crudConfiguration.addOnConfiguration("configuration", list);
			String outjsonStringDeleteAll = mapper.writeValueAsString(response);
			out.write(outjsonStringDeleteAll + "\n");
			out.flush();
			System.out.println("Insert done for " + this.getName());
			System.out.println("********************");

			break;
		}

	}

	public void StartCrud() throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException,
			NullPointerException, JSONException {
		mapper = new ObjectMapper();
		String outjsonString = in.readLine();

		request = mapper.readValue(outjsonString, Request.class);
		System.out.println(request.getList());
		System.out.println(request.getList().size());
		System.out.println("Request received from " + this.getName());
		///// String operation_type = request.getOperation_type();
		String target = this.request.getTarget();
		switch (target) {

		/*
		 * case "SELECT_ALL": response.setStudents(this.service.showStudent());
		 * 
		 * 
		 * String outjsonStringSelectAll = mapper.writeValueAsString(response);
		 * out.write(outjsonStringSelectAll + "\n"); out.flush();
		 * System.out.println("Display done to " + this.getName());
		 * System.out.println("********************"); break;
		 */

		case "configuration":
			try {
				this.CrudConfiguration(this.request.getOperation_type(), this.request.getTarget(),
						this.request.getList());
			} catch (Exception e) {
			}

			break;
		}

		/*
		 * case "DELETE_ALL": System.out.println(this.service.deleteAllStudent());
		 * String outjsonStringDeleteAll = mapper.writeValueAsString(response);
		 * out.write(outjsonStringDeleteAll + "\n"); out.flush();
		 * System.out.println("All rows deleted for " + this.getName());
		 * System.out.println("********************");
		 * 
		 * break;
		 * 
		 * case "DELETE":
		 * System.out.println(this.service.deleteStudentById(this.request.getID()));
		 * String outjsonStringDeleteAStudent = mapper.writeValueAsString(response);
		 * out.write(outjsonStringDeleteAStudent + "\n"); out.flush();
		 * System.out.println("A row deleted for " + this.getName());
		 * System.out.println("********************");
		 * 
		 * break;
		 * 
		 * case "UPDATE_AGE":
		 * System.out.println(this.service.updateStudentAge(this.request.getID(),
		 * this.request.getAge())); String outjsonStringUpdateAge =
		 * mapper.writeValueAsString(response); out.write(outjsonStringUpdateAge +
		 * "\n"); out.flush(); System.out.println("Update age done for " +
		 * this.getName()); System.out.println("********************");
		 * 
		 * break;
		 * 
		 * case "UPDATE_NAME":
		 * System.out.println(this.service.updateStudentName(this.request.getID(),
		 * this.request.getName())); String outjsonStringUpdateName =
		 * mapper.writeValueAsString(response); out.write(outjsonStringUpdateName +
		 * "\n"); out.flush(); System.out.println("Update name done for " +
		 * this.getName()); System.out.println("********************");
		 * 
		 * break;
		 * 
		 * }
		 */
	}

	public void run() {
		try {
			out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.println(this.getName() + " connected");
			System.out.println("********************");

			while (shouldRun) {
				this.StartCrud();
			}

			in.close();
			out.close();
			clientSocket.close();

		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println(this.getName() + " disconnected");
			System.out.println("********************");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
