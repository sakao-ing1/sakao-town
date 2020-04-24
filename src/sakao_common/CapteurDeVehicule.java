package sakao_common;

public class CapteurDeVehicule extends Capteur {
	
	private int idCapteurVehicule;
	private String TypeVoie;
	private int NombreDeVehicule;
	
	
	public CapteurDeVehicule(/*int idCapteur, String EtatCapteur, String TypeCapteur, int idConfiguration, int idBorne, int idZone,
			int idAgent,int idCapteurVehicule, String TypeVoie, int NombreDeVehicule*/) {
		super();
		
	   /* super(idCapteur, EtatCapteur,TypeCapteur,idConfiguration, idBorne, idZone,idAgent);
		this.idCapteurVehicule = idCapteurVehicule;
		this.TypeCapteur = TypeCapteur;
		this.NombreDeVehicule = NombreDeVehicule;*/
		
	}


	public int getIdCapteurVehicule() {
		return idCapteurVehicule;
	}


	public void setIdCapteurVehicule(int idCapteurVehicule) {
		this.idCapteurVehicule = idCapteurVehicule;
	}


	public String getTypeCapteur() {
		return TypeCapteur;
	}


	public void setTypeCapteur(String typeCapteur) {
		TypeCapteur = typeCapteur;
	}


	public int getNombreDeVehicule() {
		return NombreDeVehicule;
	}


	public void setNombreDeVehicule(int nombreDeVehicule) {
		NombreDeVehicule = nombreDeVehicule;
	}

}
