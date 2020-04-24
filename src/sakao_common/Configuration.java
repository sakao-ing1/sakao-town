package sakao_common;

import java.util.Date;

public class Configuration {
	private int idConfiguration;
	private int frequence;
	private Date dateDeConfiguration;
	
	public Configuration() {
	}
	
	public int getIdConfiguration() {
		return idConfiguration;
	}
	public void setIdConfiguration(int idConfiguration) {
		this.idConfiguration = idConfiguration;
	}
	public int getFrequence() {
		return frequence;
	}
	public void setFrequence(int frequence) {
		this.frequence = frequence;
	}
	public Date getDateDeConfiguration() {
		return dateDeConfiguration;
	}
	public void setDateDeConfiguration(Date dateDeConfiguration) {
		this.dateDeConfiguration = dateDeConfiguration;
	}
	
	
	
}
