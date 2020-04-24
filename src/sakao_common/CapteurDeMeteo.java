package sakao_common;

public class CapteurDeMeteo extends Capteur{
	private int idCapteurMeteo;
	private double Temperature;
	private String etatDuCiel;
	
	
	public CapteurDeMeteo(/*int idCapteur, String EtatCapteur, String TypeCapteur, int idConfiguration, int idBorne, int idZone,
			int idAgent*/) { // J'ai mis juste les parametres du super
		super();
		//super(idCapteur, EtatCapteur,TypeCapteur,idConfiguration,idBorne,idZone,idAgent);
		
	}
	public int getIdCapteurMeteo() {
		return idCapteurMeteo;
	}
	public void setIdCapteurMeteo(int idCapteurMeteo) {
		this.idCapteurMeteo = idCapteurMeteo;
	}
	public double getTemperature() {
		return Temperature;
	}
	public void setTemperature(double temperature) {
		Temperature = temperature;
	}
	public String getEtatDuCiel() {
		return etatDuCiel;
	}
	public void setEtatDuCiel(String etatDuCiel) {
		this.etatDuCiel = etatDuCiel;
	}

	
}
