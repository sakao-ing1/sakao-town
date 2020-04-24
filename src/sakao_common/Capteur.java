package sakao_common;

import java.io.IOException;

import org.json.JSONException;

import sakao_server.ServerSakao;

public class Capteur {
	protected int idCapteur;
	protected String EtatCapteur;
	protected String TypeCapteur;
	private Configuration configuration;
	private Borne borne;
	private Zone zone;
	private AgentMunicipalite agent;

	// Utilisé pour constructeur
	protected int idConfiguration = configuration.getIdConfiguration();
	protected int idBorne = borne.getIdBorne();
	protected int idZone = zone.getIdZone();
	protected int idAgent = agent.getIdAgent();

	public Capteur(/*int idCapteur, String EtatCapteur, String TypeCapteur, int idConfiguration, int idBorne, int idZone,
			int idAgent*/) {
		/*this.idCapteur = idCapteur;
		this.EtatCapteur = EtatCapteur;
		this.TypeCapteur = TypeCapteur;
		this.idConfiguration = idConfiguration;
		this.idBorne = idBorne;
		this.idZone = idZone;
		this.idAgent = idAgent;*/
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
		return configuration.getIdConfiguration();
	}

	public void setIdConfiguration(int idConfiguration) {
		this.configuration.setIdConfiguration(idConfiguration);
	}

	public int getIdBorne() {
		return borne.getIdBorne();
	}

	public void setIdBorne(int idBorne) {
		this.borne.setIdBorne(idBorne);
	}

	public int getIdZone() {
		return zone.getIdZone();
	}

	public void setIdZone(int idZone) {
		this.zone.setIdZone(idZone);
	}

	public int getIdAgent() {
		return agent.getIdAgent();
	}

	public void setIdAgent(int idAgent) {
		this.agent.setIdAgent(idAgent);
	}

}
