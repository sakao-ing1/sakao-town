package sakao_common;

public class Zone {
	private int idZone;
	private String meteoZone;
	private String proximiteDeLaZone;
	private String localisation;
	private int seuilBeta;
	private int seuilVehicule;
	private boolean etatAlerte; // a revoir
	
	public Zone() {
	}
	
	public int getIdZone() {
		return idZone;
	}
	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}
	public String getMeteoZone() {
		return meteoZone;
	}
	public void setMeteoZone(String meteoZone) {
		this.meteoZone = meteoZone;
	}
	public String getProximiteDeLaZone() {
		return proximiteDeLaZone;
	}
	public void setProximiteDeLaZone(String proximiteDeLaZone) {
		this.proximiteDeLaZone = proximiteDeLaZone;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public int getSeuilBeta() {
		return seuilBeta;
	}
	public void setSeuilBeta(int seuilBeta) {
		this.seuilBeta = seuilBeta;
	}
	public int getSeuilVehicule() {
		return seuilVehicule;
	}
	public void setSeuilVehicule(int seuilVehicule) {
		this.seuilVehicule = seuilVehicule;
	}
	public boolean isEtatAlerte() {
		return etatAlerte;
	}
	public void setEtatAlerte(boolean etatAlerte) {
		this.etatAlerte = etatAlerte;
	}

	@Override
	public String toString() {
		return "Zone [idZone=" + idZone + ", meteoZone=" + meteoZone + ", proximiteDeLaZone=" + proximiteDeLaZone
				+ ", localisation=" + localisation + ", seuilBeta=" + seuilBeta + ", seuilVehicule=" + seuilVehicule
				+ ", etatAlerte=" + etatAlerte + "]";
	}
	
	
	
	
}
