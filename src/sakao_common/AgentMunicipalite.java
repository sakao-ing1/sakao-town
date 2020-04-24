package sakao_common;

public class AgentMunicipalite {
	private int idAgent;
	private String nom;
	private String prenom;
	
	public AgentMunicipalite() {
	}
	
	public AgentMunicipalite(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public AgentMunicipalite(int idAgent, String nom, String prenom) {
		this.idAgent = idAgent;
		this.nom = nom;
		this.prenom = prenom;
	}

	public int getIdAgent() {
		return idAgent;
	}
	
	public void setIdAgent(int idAgent) {
		this.idAgent = idAgent;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
}
