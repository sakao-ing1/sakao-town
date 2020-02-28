package connectionPool;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public final class ConnectionFileReader {
	private String url;
	private String driver;
	private String login;
	private String password;
	
	
	private static ConnectionFileReader instance = null;
	
	
    // La présence d'un constructeur privé supprime le constructeur public par défaut.
    // De plus, seul le singleton peut s'instancier lui-même.
	private ConnectionFileReader() {
		try {
			File xmlDoc = new File("sakao-connectionpool\\connectionPool\\ConnectionFile.xml");
			DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuild = dbFact.newDocumentBuilder();
			Document doc = dBuild.parse(xmlDoc);
			
			
			/////Root element
			System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("url");
			for(int i = 0; i < nList.getLength();i++) {
				Node nNode = nList.item(i);
				System.out.println("Node name : " + nNode.getNodeName() + " " + (i+1) );
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					
					Element eElement = (Element) nNode;
					
					this.driver = eElement.getElementsByTagName("driver").item(0).getTextContent();
					System.out.println("Driver : " + this.driver);
					
					this.url = eElement.getElementsByTagName("url").item(0).getTextContent();
					System.out.println("url :" + this.url);
					
					this.login = eElement.getElementsByTagName("login").item(0).getTextContent();
					System.out.println("login : " + this.login);
					
					this.password = eElement.getElementsByTagName("password").item(0).getTextContent();
					System.out.println("password " + this.password);
				}
				
			}
		}
		catch(Exception e) {;}

	}
	
	
	  /**
     * Méthode permettant de renvoyer une instance de la classe Singleton
     * @return Retourne l'instance du singleton.
     */
	
	
	public final static ConnectionFileReader getInstance() {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet 
        //d'éviter un appel coûteux à synchronized, 
        //une fois que l'instanciation est faite
		
		
		if(ConnectionFileReader.instance == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
			
			
			synchronized(ConnectionFileReader.class) {
				if(ConnectionFileReader.instance == null) {
					ConnectionFileReader.instance = new ConnectionFileReader(); 
				}
			}
		}
		return ConnectionFileReader.instance;
	}
	
 
	public String toString() {
		return "url : " + this.url + " driver : " + this.driver + " login : " + this.login + " password : " + this.password;
	}
	
	
	public static void main (String[] args) {
		ConnectionFileReader cfr = new ConnectionFileReader();
	}
	
}
