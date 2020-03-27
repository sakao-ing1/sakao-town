package sakao_common;

public class Personne {
	private int id;
	private String name;
	private int age;

	public Personne(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public Personne(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	
	public String toString() {
		return "Personne [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
