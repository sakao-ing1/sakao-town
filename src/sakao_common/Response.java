package sakao_common;

import java.util.ArrayList;

public class Response {
	private ArrayList<Personne> students;

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
}
