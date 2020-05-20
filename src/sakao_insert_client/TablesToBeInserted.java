package sakao_insert_client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class TablesToBeInserted {

	public String readFileConfiguration() throws IOException {
		
		InputStream fis = new FileInputStream("resources\\table-to-be-inserted\\configuration.json");
		InputStreamReader ipsr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	
	public String readFileTramLineA() throws IOException {
		
		InputStream fis = new FileInputStream("resources\\table-to-be-inserted\\tramlinea.json");
		InputStreamReader ipsr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	
	public String readFileSmartCityInsert() throws IOException {
		
		InputStream fis = new FileInputStream("resources/table-to-be-inserted/smartcityinsertfile.json");
		InputStreamReader ipsr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	
	public String readFileSmartCityRepartitorAlgo() throws IOException {
		InputStream fis = new FileInputStream("resources/table-to-be-inserted/smartCityComputeTramStationRepartitorAlgoTest.json");
		InputStreamReader ipsr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		System.out.println("Data reading in the file : " + chaine);
		return chaine;
	}
	
	
	public String readFileSmartCityLinkAlgo() throws IOException {
		InputStream fis = new FileInputStream("resources/table-to-be-inserted/smartCityComputeLinkStationAlgoTest.json");
		InputStreamReader ipsr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		System.out.println("Data reading in the file : " + chaine);
		return chaine;
	}


}
