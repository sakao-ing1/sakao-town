package sakao_common;

import java.util.ArrayList;

public class Response {
 ArrayList<String> list;

	public Response() {
	}


	public String toString() {
		return 	"{\"list\":\"" + this.list +"\"}";
	}
	
	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}


	public void add(String resp) {
		this.list.add(resp);
	}
}
