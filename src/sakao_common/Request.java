package sakao_common;

import java.util.ArrayList;

public class Request {
	private String operation_type;
	private String target;
	private ArrayList<String> list = new ArrayList<String>();/////VALUE
	

	public Request() {;}
	
	public Request(String o, String t) {
		this.operation_type = o;
		this.target = t;

	}
	/*private int ID;
	private String name;
	private int age;
	 
	public Request(String operation_type, String target, int ID, String name, int age) {
		this.operation_type = operation_type;
		this.target = target;
		this.ID = ID;
		this.name = name;
		this.age = age;
	}

	public Request(String operation_type, String target) {///// Select all et delete all
		this.operation_type = operation_type;
		this.target = target;
	}

	public Request(String operation_type, String target, String name, int age) {/// Insert
		this.operation_type = operation_type;
		this.target = target;
		this.name = name;
		this.age = age;
	}

	public Request(String operation_type, String target, int id) {
		this.operation_type = operation_type;
		this.target = target;
		this.ID = id;
	}

	public Request(String operation_type, String target, int ID, String name) {
		this.operation_type = operation_type;
		this.target = target;
		this.ID = ID;
		this.name = name;
	}

	public Request(String operation_type, String target, int ID, int age) {
		this.operation_type = operation_type;
		this.target = target;
		this.ID = ID;
		this.age = age;
	}

	public Request() {
	}

	/*
	 * public String toString() { return "operation_type : " + this.operation_type +
	 * "; target : " + this.target + "; };"; }
	 */

	
	  public String toString() {
	 
	  return "{\"operation_type\":\""+this.operation_type + "\"," + "\"target\":\""+this.target + "\"," + "\"list\":\""+ list + "\"}";
	  }
	
	
	public String getOperation_type() {
		return operation_type;
	}

	public void setOperation_type(String operation_type) {
		this.operation_type = operation_type;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}



}
