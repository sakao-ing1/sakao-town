package sakao_common;

public class CapteurDeMeteo {
	private int idCapteurMeteo;
	private double Temperature;
	private String etatDuCiel;
	private int idCapteur;
	
	public CapteurDeMeteo() { 
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
	public int getIdCapteur() {
		return idCapteur;
	}
	public void setIdCapteur(int idCapteur) {
		this.idCapteur = idCapteur;
	}
	
}
