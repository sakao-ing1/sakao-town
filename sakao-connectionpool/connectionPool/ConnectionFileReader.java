package connectionPool;


import java.io.FileInputStream;
///import java.util.Enumeration;
import java.util.Properties;



public final class ConnectionFileReader {
	private static String url;
	private static String driver;
	private static String login;
	private static String password;
	
	
	private static ConnectionFileReader instance = null;
	
	
    // La présence d'un constructeur privé supprime le constructeur public par défaut.
    // De plus, seul le singleton peut s'instancier lui-même.
	private ConnectionFileReader() {
		Properties p = new Properties();
		try {
			FileInputStream fis = new FileInputStream("sakao-connectionpool\\connectionPool\\ConnectionFile.xml");
			p.loadFromXML(fis);
			///Enumeration<?> enumeration = p.propertyNames();
			
			ConnectionFileReader.url = (String) p.get("url");
			ConnectionFileReader.driver = (String) p.get("driver");
			ConnectionFileReader.login = (String) p.get("login");
			ConnectionFileReader.password = (String) p.get("password");
			
			System.out.println(ConnectionFileReader.url);
			System.out.println(ConnectionFileReader.driver);
			System.out.println(ConnectionFileReader.login);
			System.out.println(ConnectionFileReader.password );

			
			
			/////Util mais cest pour parcourir et afficher nous avons nous besoin de recuperer des valeurs afin de les attribuer aux attributs de la vlasse, pour cela nous utilisons get
			///while(enumeration.hasMoreElements()) {
				///String key = (String) enumeration.nextElement();
				///String value = p.getProperty(key);
				///if(key == "driver") {
					///System.out.println(key + " = " +  value);

				///}
			///}
			
			///DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
			///DocumentBuilder dBuild = dbFact.newDocumentBuilder();
			///Document doc = dBuild.parse(XMLDoc);
			
			
		
				}

		catch(Exception e) {;}

	}
	
	
	  
    /// * Méthode permettant de renvoyer une instance de la classe Singleton
     ///* @return Retourne l'instance du singleton.
     
	
	
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
		return "url : " + ConnectionFileReader.url + " driver : " + ConnectionFileReader.driver + " login : " + ConnectionFileReader.login + " password : " + ConnectionFileReader.password;
	}
	
	
	public static void main (String[] args) {
		ConnectionFileReader cfr = new ConnectionFileReader();
	}
	
}
