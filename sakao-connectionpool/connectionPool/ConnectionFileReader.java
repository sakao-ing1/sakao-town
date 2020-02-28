package connectionPool;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class ConnectionFileReader {
	
	/*private String driver;
	private String url;
	private String login;
	private String password;
	*/
	
	public static void main(String [] args) {
		try {
			File xmlDoc = new File("sakao-connectionpool\\connectionPool\\ConnectionFile.xml");
			DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuild = dbFact.newDocumentBuilder();
			Document doc = dBuild.parse(xmlDoc);
			System.out.println(doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("entry");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	

}
