package sakao_common;

import java.util.ArrayList;

public class Response {
	ArrayList<Object> list;

	public Response() {
	}

	public String toString() {
		return "Students : [ " + this.getObject() + "; ]";
	}

	public ArrayList<Object> getObject() {
		return list;
	}

	public void setObject(ArrayList<Object> list) {
		this.list = list;
	}
}
