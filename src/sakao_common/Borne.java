package sakao_common;

public class Borne {
	
	
	private int idBorne;
	private boolean etatBorne;
	private Zone zone;
	
	
	public Borne() {
		
	}


	public int getIdBorne() {
		return idBorne;
	}


	public void setIdBorne(int idBorne) {
		this.idBorne = idBorne;
	}


	public boolean isEtatBorne() {
		return etatBorne;
	}


	public void setEtatBorne(boolean etatBorne) {
		this.etatBorne = etatBorne;
	}


	public int getIdZone() {
		return  zone.getIdZone();
	}


	public void setIdZone(int idZone) {
		zone.setIdZone(idZone);
	}
	
	
}
