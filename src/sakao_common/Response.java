package sakao_common;

import java.util.ArrayList;

public class Response {
 ArrayList<String> list;

	public Response() {
	}

	public String toString() {
		return "Students : [ " + this.getObject() + "; ]";
	}

	public ArrayList<String> getObject() {
		return list;
	}

	public void setObject(ArrayList<String> list) {
		this.list = list;
	}
}
