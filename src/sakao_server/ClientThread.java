package sakao_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import sakao_common.Request;
import sakao_common.Response;

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
		this.service = new Crud_Service();
	}

	/*public void CrudConfiguration(String operation_type, String target, ArrayList<String> list)
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {
		CrudConfiguration crudConfiguration = new CrudConfiguration();
		switch (operation_type) {
		case "SELECT_ALL":
			response.setObject( (Object) (crudConfiguration.showAllConfiguration()));
			String outjsonStringSelectAll = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectAll + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;
		case "INSERT":
			crudConfiguration.addOnConfiguration(list);
			String outjsonStringDeleteAll = mapper.writeValueAsString(response);
			out.write(outjsonStringDeleteAll + "\n");
			out.flush();
			System.out.println("Insert done for " + this.getName());
			System.out.println("********************");

			break;
		}

	}*/
	
	///////////////////////// CHOOSE THE GOOD REQUEST TO DO	////////////////////


	public void CrudSmartCity(String operation_type,String t, ArrayList<String> list) throws IOException {
		switch(operation_type) {
			case "SELECT_ALL" :
				try {
					response.setList(service.showCity());
					String outjsonStringSelectAll = mapper.writeValueAsString(response);
					out.write(outjsonStringSelectAll + "\n");
					out.flush();
					System.out.println("Display done for " + this.getName());
					System.out.println("********************");
				}
				catch(Exception e) {
					System.out.println("select not done");
					///response.getList().add("select not done");
					/*String outjsonStringSelectAll = mapper.writeValueAsString(response);
					out.write(outjsonStringSelectAll + "\n");
					out.flush();*/
				}

				break;
			
			case "INSERT" :
				try {
					///String resp = "insert done";
					service.addASmartCity(list);
					String outjsonStringInsert = mapper.writeValueAsString(response);
				    out.write(outjsonStringInsert + "\n"); out.flush();
					System.out.println("Insert done for " + this.getName());
					System.out.println("********************");
					/////response.getList().add(resp);
				}
				catch(Exception e) {
					e.printStackTrace();
					/*System.out.println("insert not done");
					response.getList().add("insert not done");
					String outjsonStringInsert = mapper.writeValueAsString(response);
					out.write(outjsonStringInsert + "\n");
					out.flush();*/
				}
			
				break;
				
			case "DELETE_ALL" :
				try {
					/////String resp = "delete done";
					service.deleteACity();
					///response.getList().add(resp);
					String outjsonStringDeleteAll = mapper.writeValueAsString(response);
				    out.write(outjsonStringDeleteAll + "\n"); out.flush();
					System.out.println("Delete done for " + this.getName());
					System.out.println("********************");
				
				}
				catch(Exception e) {
					e.printStackTrace();
					/*System.out.println("delete not done");
					response.getList().add("delete not done");*/
				}
				break;
				
			case "SELECT_DIMENSION" :
				try {
					response.setList(service.showCityDimension());
					String outjsonStringSelectAll = mapper.writeValueAsString(response);
					out.write(outjsonStringSelectAll + "\n");
					out.flush();
					System.out.println("Display done for " + this.getName());
					System.out.println("********************");
				}
				catch(Exception e) {
					System.out.println("select not done");
					///response.getList().add("select not done");
					/*String outjsonStringSelectAll = mapper.writeValueAsString(response);
					out.write(outjsonStringSelectAll + "\n");
					out.flush();*/
				}

				break;

			}
	}
	
	
	public void CrudStation(String operation_type, String t,ArrayList<String> list) throws IOException {
		switch(operation_type) {
			case "SELECT_ALL" :
				try {
					response.setList(service.showStations());
					String outjsonStringSelectAll = mapper.writeValueAsString(response);
					out.write(outjsonStringSelectAll + "\n");
					out.flush();
					System.out.println("Display done for " + this.getName());
					System.out.println("********************");
				}
				catch(Exception e) {
					System.out.println("select not done");
					response.getList().add("select not done");
				}

				break;
			
			case "INSERT" :
				try {
					/////String resp = "insert done";
					service.addStation(list);
					/////response.getList().add(resp);
					String outjsonStringInsert = mapper.writeValueAsString(response);
				    out.write(outjsonStringInsert + "\n"); out.flush();
					System.out.println("Insert done for " + this.getName());
					System.out.println("********************");
				}
				catch(Exception e) {
					e.printStackTrace();
					///System.out.println("insert not done");
					///response.getList().add("insert not done");
				}
				

				break;
			case "DELETE_ALL" :
				try {
					/////String resp = "delete done";
					service.deleteStations();
					/////response.add(resp);
					String outjsonStringDeleteAll = mapper.writeValueAsString(response);
				    out.write(outjsonStringDeleteAll + "\n"); out.flush();
					System.out.println("Delete done for " + this.getName());
					System.out.println("********************");
					break;
				}
				catch(Exception e) {
					e.printStackTrace();
					/*System.out.println("delete not done");
					response.getList().add("delete not done");*/
				}
				
			case "SELECT_COORD" :
				try {
					response.setList(service.showStationCoord());
					String outjsonStringSelectAll = mapper.writeValueAsString(response);
					out.write(outjsonStringSelectAll + "\n");
					out.flush();
					System.out.println("Display done for " + this.getName());
					System.out.println("********************");
				}
				catch(Exception e) {
					System.out.println("select not done");
					
				}

				break;
			
			}
	}
	
	public void CrudTramLineA(String operation_type, String t,ArrayList<String> list) throws IOException {
		switch(operation_type) {
			case "SELECT_ALL" :
				try {
					response.setList(service.showTramLineA());
					String outjsonStringSelectAll = mapper.writeValueAsString(response);
					out.write(outjsonStringSelectAll + "\n");
					out.flush();
					System.out.println("Display done for " + this.getName());
					System.out.println("********************");
				}
				catch(Exception e) {
					System.out.println("select not done");
					response.getList().add("select not done");
				}

				break;
			
			case "INSERT" :
				try {
					/////String resp = "insert done";
					service.addTramLineAStation(list);
					/////response.getList().add(resp);
					String outjsonStringInsert = mapper.writeValueAsString(response);
				    out.write(outjsonStringInsert + "\n"); out.flush();
					System.out.println("Insert done for " + this.getName());
					System.out.println("********************");
				}
				catch(Exception e) {
					e.printStackTrace();
					///System.out.println("insert not done");
					///response.getList().add("insert not done");
				}
				

				break;
			case "DELETE_ALL" :
				try {
					/////String resp = "delete done";
					service.deleteTramLineA();
					/////response.add(resp);
					String outjsonStringDeleteAll = mapper.writeValueAsString(response);
				    out.write(outjsonStringDeleteAll + "\n"); out.flush();
					System.out.println("Delete done for " + this.getName());
					System.out.println("********************");
					break;
				}
				catch(Exception e) {
					e.printStackTrace();
					/*System.out.println("delete not done");
					response.getList().add("delete not done");*/
				}
				
			case "SELECT_TRAMLINEA" :
				try {
					response.setList(service.showTramLineACoord());
					String outjsonStringSelectAll = mapper.writeValueAsString(response);
					out.write(outjsonStringSelectAll + "\n");
					out.flush();
					System.out.println("Display done for " + this.getName());
					System.out.println("********************");
				}
				catch(Exception e) {
					System.out.println("select not done");
					
				}

				break;
			
			}
	}
	
	
	public void CrudTramLineB(String operation_type, String t,ArrayList<String> list) throws IOException {
		switch(operation_type) {
			case "SELECT_ALL" :
				try {
					response.setList(service.showTramLineB());
					String outjsonStringSelectAll = mapper.writeValueAsString(response);
					out.write(outjsonStringSelectAll + "\n");
					out.flush();
					System.out.println("Display done for " + this.getName());
					System.out.println("********************");
				}
				catch(Exception e) {
					System.out.println("select not done");
					response.getList().add("select not done");
				}

				break;
			
			case "INSERT" :
				try {
					/////String resp = "insert done";
					service.addTramLineBStation(list);
					/////response.getList().add(resp);
					String outjsonStringInsert = mapper.writeValueAsString(response);
				    out.write(outjsonStringInsert + "\n"); out.flush();
					System.out.println("Insert done for " + this.getName());
					System.out.println("********************");
				}
				catch(Exception e) {
					e.printStackTrace();
					///System.out.println("insert not done");
					///response.getList().add("insert not done");
				}
				

				break;
			case "DELETE_ALL" :
				try {
					/////String resp = "delete done";
					service.deleteTramLineB();
					/////response.add(resp);
					String outjsonStringDeleteAll = mapper.writeValueAsString(response);
				    out.write(outjsonStringDeleteAll + "\n"); out.flush();
					System.out.println("Delete done for " + this.getName());
					System.out.println("********************");
					break;
				}
				catch(Exception e) {
					e.printStackTrace();
					/*System.out.println("delete not done");
					response.getList().add("delete not done");*/
				}
				
			case "SELECT_TRAMLINEB" :
				try {
					response.setList(service.showTramLineBCoord());
					String outjsonStringSelectAll = mapper.writeValueAsString(response);
					out.write(outjsonStringSelectAll + "\n");
					out.flush();
					System.out.println("Display done for " + this.getName());
					System.out.println("********************");
				}
				catch(Exception e) {
					System.out.println("select not done");
					
				}

				break;
			
			}
	}
	
	
	public void CrudTramLineC(String operation_type, String t,ArrayList<String> list) throws IOException {
		switch(operation_type) {
			case "SELECT_ALL" :
				try {
					response.setList(service.showTramLineC());
					String outjsonStringSelectAll = mapper.writeValueAsString(response);
					out.write(outjsonStringSelectAll + "\n");
					out.flush();
					System.out.println("Display done for " + this.getName());
					System.out.println("********************");
				}
				catch(Exception e) {
					System.out.println("select not done");
					response.getList().add("select not done");
				}

				break;
			
			case "INSERT" :
				try {
					/////String resp = "insert done";
					service.addTramLineCStation(list);
					/////response.getList().add(resp);
					String outjsonStringInsert = mapper.writeValueAsString(response);
				    out.write(outjsonStringInsert + "\n"); out.flush();
					System.out.println("Insert done for " + this.getName());
					System.out.println("********************");
				}
				catch(Exception e) {
					e.printStackTrace();
					///System.out.println("insert not done");
					///response.getList().add("insert not done");
				}
				

				break;
			case "DELETE_ALL" :
				try {
					/////String resp = "delete done";
					service.deleteTramLineC();
					/////response.add(resp);
					String outjsonStringDeleteAll = mapper.writeValueAsString(response);
				    out.write(outjsonStringDeleteAll + "\n"); out.flush();
					System.out.println("Delete done for " + this.getName());
					System.out.println("********************");
					break;
				}
				catch(Exception e) {
					e.printStackTrace();
					/*System.out.println("delete not done");
					response.getList().add("delete not done");*/
				}
				
			case "SELECT_TRAMLINEC" :
				try {
					response.setList(service.showTramLineCCoord());
					String outjsonStringSelectAll = mapper.writeValueAsString(response);
					out.write(outjsonStringSelectAll + "\n");
					out.flush();
					System.out.println("Display done for " + this.getName());
					System.out.println("********************");
				}
				catch(Exception e) {
					System.out.println("select not done");
					
				}

				break;
			
			}
	}
	
	
	public void CrudTramLineD(String operation_type, String t,ArrayList<String> list) throws IOException {
		switch(operation_type) {
			case "SELECT_ALL" :
				try {
					response.setList(service.showTramLineD());
					String outjsonStringSelectAll = mapper.writeValueAsString(response);
					out.write(outjsonStringSelectAll + "\n");
					out.flush();
					System.out.println("Display done for " + this.getName());
					System.out.println("********************");
				}
				catch(Exception e) {
					System.out.println("select not done");
					response.getList().add("select not done");
				}

				break;
			
			case "INSERT" :
				try {
					/////String resp = "insert done";
					service.addTramLineDStation(list);
					/////response.getList().add(resp);
					String outjsonStringInsert = mapper.writeValueAsString(response);
				    out.write(outjsonStringInsert + "\n"); out.flush();
					System.out.println("Insert done for " + this.getName());
					System.out.println("********************");
				}
				catch(Exception e) {
					e.printStackTrace();
					///System.out.println("insert not done");
					///response.getList().add("insert not done");
				}
				

				break;
			case "DELETE_ALL" :
				try {
					/////String resp = "delete done";
					service.deleteTramLineD();
					/////response.add(resp);
					String outjsonStringDeleteAll = mapper.writeValueAsString(response);
				    out.write(outjsonStringDeleteAll + "\n"); out.flush();
					System.out.println("Delete done for " + this.getName());
					System.out.println("********************");
					break;
				}
				catch(Exception e) {
					e.printStackTrace();
					/*System.out.println("delete not done");
					response.getList().add("delete not done");*/
				}
				
			case "SELECT_TRAMLINED" :
				try {
					response.setList(service.showTramLineDCoord());
					String outjsonStringSelectAll = mapper.writeValueAsString(response);
					out.write(outjsonStringSelectAll + "\n");
					out.flush();
					System.out.println("Display done for " + this.getName());
					System.out.println("********************");
				}
				catch(Exception e) {
					System.out.println("select not done");
					
				}

				break;
			
			}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
///////////////////////// CHOOSE ON WHICH TABLE TO DO THE REQUEST /////////////////////////
	public void StartCrud() throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException,
			NullPointerException, JSONException {
		mapper = new ObjectMapper();
		String outjsonString = in.readLine();

		request = mapper.readValue(outjsonString, Request.class);
		System.out.println("Request received from " + this.getName());
		///// String operation_type = request.getOperation_type();
		String target = this.request.getTarget();
		switch (target) {

		case "smartcity":
			try {
				System.out.println("Let's go to see what's in smartcity");
				this.CrudSmartCity(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;

		case "station":
			try {
				System.out.println("Let's go to see what's in station");
				this.CrudStation(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;
			
		case "tramlinea":
			try {
				System.out.println("Let's go to see what's in tramlinea");
				this.CrudTramLineA(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;
			
			
		case "tramlineb":
			try {
				System.out.println("Let's go to see what's in tramlineb");
				this.CrudTramLineB(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;
			
			
		case "tramlinec":
			try {
				System.out.println("Let's go to see what's in tramlinec");
				this.CrudTramLineC(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;
			
			
		case "tramlined":
			try {
				System.out.println("Let's go to see what's in tramlined");
				this.CrudTramLineD(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;
		
		}
		
	/////////////////////////
		
		
		
		
		
		
		
		/*
		 * case "SELECT_ALL": response.setStudents(this.service.showStudent());
		 * 
		 * 
		 * String outjsonStringSelectAll = mapper.writeValueAsString(response);
		 * out.write(outjsonStringSelectAll + "\n"); out.flush();
		 * System.out.println("Display done to " + this.getName());
		 * System.out.println("********************"); break;
		 */

		/*case "configuration":
			try {
				this.CrudConfiguration(this.request.getOperation_type(), this.request.getTarget(),
						this.request.getList());
			} catch (Exception e) {
			}

			break;*/
			
			
		
			/*case "station_all" :
				try {
					System.out.println("jsuis au delete");
					System.out.println( this.service.deleteStations());
					String outjsonStringInsert = mapper.writeValueAsString(response);
					out.write(outjsonStringInsert + "\n");
					out.flush();
					System.out.println("Response sent");
				}
				catch(Exception e) {}
				
				break;*/
				
				
			/*case "smartcity" :
				try {
					System.out.println("jsuis au insert city");
					///if(this.service.cityExists()) {
						///this.response.setB(true);
					///}
					///else {
						///this.response.setB(false);
					///}

					this.service.CreateASmartCity(this.request.getList());
					String outjsonStringInsert = mapper.writeValueAsString(response);
					out.write(outjsonStringInsert + "\n");
					out.flush();
					System.out.println("Response sent about if city exist");
				}
			
				catch(Exception e) {}
			
				break;*/
			
			
		
		

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

	public Crud_Service getService() {
		return service;
	}

	public void setService(Crud_Service service) {
		this.service = service;
	}
}
