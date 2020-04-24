package sakao_common;

public class CapteurDePollution extends Capteur {
	private int idCapteurPollution;
	private int niveauSO;
	private int niveauPM;
	private int niveauNO;
	private int niveauO;

	public CapteurDePollution(/*int idCapteur, String EtatCapteur, String TypeCapteur, int idConfiguration, int idBorne,
			int idZone, int idAgent*/) {// J'ai mis juste les parametres du super
		//super(idCapteur, EtatCapteur, TypeCapteur, idConfiguration, idBorne, idZone, idAgent);

	}

	public int getIdCapteurPollution() {
		return idCapteurPollution;
	}

	public void setIdCapteurPollution(int idCapteurPollution) {
		this.idCapteurPollution = idCapteurPollution;
	}

	public int getNiveauSO() {
		return niveauSO;
	}

	public void setNiveauSO(int niveauSO) {
		this.niveauSO = niveauSO;
	}

	public int getNiveauPM() {
		return niveauPM;
	}

	public void setNiveauPM(int niveauPM) {
		this.niveauPM = niveauPM;
	}

	public int getNiveauNO() {
		return niveauNO;
	}

	public void setNiveauNO(int niveauNO) {
		this.niveauNO = niveauNO;
	}

	public int getNiveauO() {
		return niveauO;
	}

	public void setNiveauO(int niveauO) {
		this.niveauO = niveauO;
	}

}
