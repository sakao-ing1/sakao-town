package sakao_common;

public class Capteur {
	private int idCapteur;
	private String EtatCapteur;
	private String TypeCapteur;
	private int idConfiguration;
	private int idBorne;
	private int idZone;
	private int idAgent;
	
	public Capteur() {
	}
	public int getIdCapteur() {
		return idCapteur;
	}
	public void setIdCapteur(int idCapteur) {
		this.idCapteur = idCapteur;
	}
	public String getEtatCapteur() {
		return EtatCapteur;
	}
	public void setEtatCapteur(String etatCapteur) {
		EtatCapteur = etatCapteur;
	}
	public String getTypeCapteur() {
		return TypeCapteur;
	}
	public void setTypeCapteur(String typeCapteur) {
		TypeCapteur = typeCapteur;
	}
	public int getIdConfiguration() {
		return idConfiguration;
	}
	public void setIdConfiguration(int idConfiguration) {
		this.idConfiguration = idConfiguration;
	}
	public int getIdBorne() {
		return idBorne;
	}
	public void setIdBorne(int idBorne) {
		this.idBorne = idBorne;
	}
	public int getIdZone() {
		return idZone;
	}
	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}
	public int getIdAgent() {
		return idAgent;
	}
	public void setIdAgent(int idAgent) {
		this.idAgent = idAgent;
	}
	
	
}
