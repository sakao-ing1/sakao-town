package sakao_common;

public class Request {
	private String operation_type;
	private String target;
	private int ID;
	private String name;
	private int age;
	
	public Request(String target, int ID, String name, int age){
		this.target = target;
		this.ID = ID;
		this.name = name;
		this.age = age;
	}
	
	
	public Request(String target){/////Select all et delete all
		this.target = target;
	}
	
	public Request(String target, String name, int age){///Insert
		this.target = target;
		this.name = name;
		this.age = age;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String toString() {
		
		
		return "operation_type : " + this.operation_type + " target : " + this.target + " name : " + this.name + " age : " + this.age;
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
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	

}
