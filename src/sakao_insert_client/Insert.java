package sakao_insert_client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import org.codehaus.jackson.map.ObjectMapper;







public class Insert {

	public static void main(String[] args) throws IOException {
		
		Socket clientSocket = new Socket("localhost", 3030);
		 OutputStreamWriter out =new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
		 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
		
		
	       
	      //lecture du fichier texte
	      try{
	    	  ///String chaine = "";
	         InputStream fis = new FileInputStream("C:\\Users\\alain\\git\\sakao-town\\src\\sakao_insert_client\\test.json");
	         InputStreamReader ipsr = new InputStreamReader(fis);
	         BufferedReader br = new BufferedReader(ipsr);
	         String outjsonString = "";
	         String chaine = "";
	         
	         
	        	      
	         while((outjsonString = br.readLine()) != null) {
	        	 chaine = chaine + outjsonString;
	         }
	        out.write(chaine + "\n");
	         out.flush();
	         System.out.println("Request sent : " + chaine);
	         
	         String injsonString;
	         injsonString = in.readLine();
	         System.out.println(injsonString);
	         
	         clientSocket.close();
	      }    
	      catch (IOException e){
	     
	      }
	
	}
}

