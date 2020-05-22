package sakao_mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import sakao_common.Request;
import sakao_common.Response;
import sakao_common.Zone;

public class SensorPollutionController {

	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Response response = new Response();
	private ObjectMapper mapper;

	private List<Map<String, String>> map;

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
		System.out.println("");
		out.write(outjsonString + "\n");
		out.flush();
		String injsonString = in.readLine();
		System.out.println(injsonString);
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

	public void getSensorPollutionScenario(List<Map<String, String>> map)
			throws JsonParseException, JsonMappingException, IOException {
		// Zone zone = new ObjectMapper().readValue(map.get(0).toString(), Zone.class);
		int schoolVal = 161;
		int pietVal = 190;
		int denseVal = 218;
		if (map.get(0).get("nexttothezone").equals("School")) {
			if (Integer.parseInt(map.get(1).get("temperature")) > 30) {
				double sVal = schoolVal * 0.85;
				if (Double.parseDouble((map.get(2).get("betaaverage"))) > sVal) {

					System.out.println("Alerte");
					this.alerteInsert();
					this.zoneUpdate(Integer.parseInt(map.get(0).get("id")));

				} else {
					System.out.println("Pas d'alerte");
				}
			} else if (Integer.parseInt(map.get(1).get("temperature")) < 15) {
				double sVal = schoolVal * 1.15;
				if (Double.parseDouble((map.get(2).get("betaaverage"))) > sVal) {
					System.out.println("Alerte");
					this.alerteInsert();

					this.zoneUpdate(Integer.parseInt(map.get(0).get("id")));

				} else {
					System.out.println("Pas d'alerte");
				}
			} else {
				if (Integer.parseInt((map.get(2).get("betaaverage"))) > schoolVal) {
					System.out.println("Alerte");
					this.alerteInsert();

					this.zoneUpdate(Integer.parseInt(map.get(0).get("id")));

				} else {
					System.out.println("Pas d'alerte");
				}
			}
		}
		if (map.get(0).get("nexttothezone").equals("Pedestrian")) {
			if (Integer.parseInt(map.get(1).get("temperature")) > 30) {
				double sVal = pietVal * 0.85;
				if (Double.parseDouble((map.get(2).get("betaaverage"))) > sVal) {
					System.out.println("Alerte");
					this.alerteInsert();

					this.zoneUpdate(Integer.parseInt(map.get(0).get("id")));
				} else {
					System.out.println("Pas d'alerte");
				}
			} else if (Integer.parseInt(map.get(1).get("temperature")) < 15) {
				double sVal = pietVal * 1.15;
				if (Double.parseDouble((map.get(2).get("betaaverage"))) > sVal) {
					System.out.println("Alerte");
					this.alerteInsert();

					this.zoneUpdate(Integer.parseInt(map.get(0).get("id")));
				} else {
					System.out.println("Pas d'alerte");
				}
			} else {
				if (Integer.parseInt((map.get(2).get("betaaverage"))) > pietVal) {
					System.out.println("Alerte");
					this.alerteInsert();

					this.zoneUpdate(Integer.parseInt(map.get(0).get("id")));
				} else {
					System.out.println("Pas d'alerte");
				}
			}
		}
		if (map.get(0).get("nexttothezone").equals("Dense")) {
			if (Integer.parseInt(map.get(1).get("temperature")) > 30) {
				double sVal = denseVal * 0.85;
				if (Double.parseDouble((map.get(2).get("betaaverage"))) > sVal) {
					System.out.println("Alerte");
					this.alerteInsert();

					this.zoneUpdate(Integer.parseInt(map.get(0).get("id")));
				} else {
					System.out.println("Pas d'alerte");
				}
			} else if (Integer.parseInt(map.get(1).get("temperature")) < 15) {
				double sVal = denseVal * 1.15;
				if (Double.parseDouble((map.get(2).get("betaaverage"))) > sVal) {
					System.out.println("Alerte");
					this.alerteInsert();
				} else {
					System.out.println("Pas d'alerte");
				}
			} else {
				if (Integer.parseInt((map.get(2).get("betaaverage"))) > denseVal) {
					System.out.println("Alerte");
					this.alerteInsert();

					this.zoneUpdate(Integer.parseInt(map.get(0).get("id")));
				} else {
					System.out.println("Pas d'alerte");
				}
			}
		}

		this.pollutionInsert(map.get(2).get("betaaverage"));
		this.weatherInsert(Integer.parseInt(map.get(1).get("temperature")));
	}

	public void pollutionInsert(String average) throws IOException {
		String s1 = "{";
		String s2 = "}";
		ArrayList<String> a = new ArrayList<String>();
		a.add(s1);
		a.add("betaaverage");
		a.add(String.valueOf(average));
		a.add("idconfiguration");
		a.add(null);
		a.add("idsensor");
		a.add(null);
		a.add(s2);
		Request request = new Request("INSERT", "pollutionsensor", a);
		this.sendMessageToServer(request);
	}

	public void weatherInsert(int temperature) throws IOException {
		String s1 = "{";
		String s2 = "}";
		ArrayList<String> a2 = new ArrayList<String>();
		a2.add(s1);
		a2.add("temperature");
		a2.add(String.valueOf(temperature));
		a2.add("stateofthesky");
		if (temperature < 10) {
			a2.add("cloudy");
		} else {
			a2.add("nice");
		}
		a2.add("idsensor");
		a2.add(null);
		a2.add("idconfiguration");
		a2.add(null);
		a2.add(s2);
		Request request2 = new Request("INSERT", "weathersensor", a2);
		this.sendMessageToServer(request2);
	}

	public void zoneUpdate(int idZone) throws IOException {
		ArrayList<String> azon = new ArrayList<String>();
		azon.add(String.valueOf(idZone));
		azon.add("true");
		Request request2 = new Request("UPDATE", "zone", azon);
		this.sendMessageToServer(request2);
	}

	public void alerteInsert() throws IOException {
		String s1 = "{";
		String s2 = "}";
		Date actuelle = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dat = dateFormat.format(actuelle);
		ArrayList<String> a = new ArrayList<String>();
		a.add(s1);
		a.add("dateajout");
		a.add(dat);
		a.add("idsensor");
		a.add(null);
		a.add(s2);
		Request request = new Request("INSERT", "alert", a);
		this.sendMessageToServer(request);
	}

}
