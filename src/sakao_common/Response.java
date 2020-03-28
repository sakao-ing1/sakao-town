package sakao_common;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Response {
	private ArrayList<Personne> students;
	// private boolean state;

	public Response() {
	}

	public String toString() {
		return "personnes : [ " + this.getStudents() + "; ]";
	}

	public ArrayList<Personne> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Personne> students) {
		this.students = students;
	}
	/*
	 * public boolean isState() { return state; } public void setState(boolean
	 * state) { this.state = state; }
	 */

}
