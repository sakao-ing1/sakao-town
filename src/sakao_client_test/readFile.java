package sakao_client_test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class readFile {

	public String readFileInitilization() throws IOException {
		InputStream fis = new FileInputStream("resources\\table-to-be-inserted\\InitilizationTESTVehicles.json");
		InputStreamReader ipsr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	public String readFileInitilizationSmartcity() throws IOException {
		InputStream fis = new FileInputStream("resources\\table-to-be-inserted\\InitilizationTESTSmartCity.json");
		InputStreamReader ipsr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	
	public String readFileVehiclesTEST1() throws IOException {
		InputStream fis = new FileInputStream("resources\\table-to-be-inserted\\TestVehicles1.json");
		InputStreamReader ipsr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	public String readFileVehiclesTEST2() throws IOException {
		InputStream fis = new FileInputStream("resources\\table-to-be-inserted\\TestVehicles2.json");
		InputStreamReader ipsr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}

	public String readFileVehiclesTEST3() throws IOException {
		InputStream fis = new FileInputStream("resources\\table-to-be-inserted\\TestVehicles3.json");
		InputStreamReader ipsr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}

	public String readFileVehiclesTEST4() throws IOException {
		InputStream fis = new FileInputStream("resources\\table-to-be-inserted\\TestVehicles4.json");
		InputStreamReader ipsr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}

}
