package sakao_connection_pool;

import java.io.FileInputStream;
/////import java.io.FileNotFoundException;
import java.io.IOException;
///import java.util.Enumeration;
import java.util.Properties;



public final class ConnectionFileReader {

	private Properties p;

	///Constructeur qu'on utilise juste pour creer un objet
	public ConnectionFileReader() {
		 p = new Properties();
	}
	
	
	/////Methode qui permet douvrir le fichier et de le lire
	public void Read() {
		FileInputStream fis;
		try {
			fis = new FileInputStream("src\\sakao_connection_pool\\ConnectionFile.xml");
			p.loadFromXML(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/////Creer une methode qui retourne la cle du fichier pour ne pas a avoir a tout tapper a la main 
	///// Cette methode va nous permettre de parcourir le fichier
	public String getProperty(String key) {
		return p.getProperty(key);
		
	}
	
	
	public Properties getP() {
		return p;
	}

	public void setP(Properties p) {
		this.p = p;
	}


}
