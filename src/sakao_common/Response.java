package sakao_common;

import java.util.ArrayList;

public class Response {
	private ArrayList<Personne> students;
	private boolean state;
	
	
	
	
	
	
	
	
	
	
	
	public String toString() {
		return "students " + this.students.toString() + " state ; " + this.state; 
	}
	
	
	
	public ArrayList<Personne> getStudents() {
		return students;
	}
	public void setStudents(ArrayList<Personne> students) {
		this.students = students;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
	

}
