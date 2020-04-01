package sakao_server;

import java.util.ArrayList;

import sakao_common.Personne;
import sakao_connection_pool.DataSource;

public class Crud_Service {

	private Crud_Controller controller;

	public Crud_Service() throws ClassNotFoundException {
		this.controller = new Crud_Controller();
	}

	public ArrayList<Personne> showPersonne() throws ClassNotFoundException {
		return controller.showPersonne();
	}

	public boolean addPersonne(String name, int age) {
		boolean b = false;
		try {
			Personne p = new Personne(name, age);
			controller.addPersonne(p);
			DataSource.returnConnection((DataSource.getConnection()));

			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}

	public boolean deletePersonneByName(String name) {
		boolean b = false;
		try {
			controller.deletePersonneByName(name);
			DataSource.returnConnection((DataSource.getConnection()));

			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}

	public boolean deletePersonneById(int ID) {
		boolean b = false;
		try {
			controller.deletePersonneById(ID);
			DataSource.returnConnection((DataSource.getConnection()));

			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}

	public boolean updatePersonneAge(int id, int age) {
		boolean b = false;
		try {
			controller.updatePersonneAge(id, age);
			DataSource.returnConnection((DataSource.getConnection()));

			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}

		return b;
	}

	public boolean deleteAllPersonne() {
		boolean b = false;
		try {
			controller.deleteAllPersonne();
			DataSource.returnConnection((DataSource.getConnection()));

			b = true;
		} catch (Exception e) {
			System.out.println("all rows deleted");
		}
		return b;
	}

	public boolean updatePersonneName(int id, String name) {
		boolean b = false;
		try {
			controller.updatePersonneName(id, name);
			DataSource.returnConnection((DataSource.getConnection()));

			b = true;
		}

		catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}

	public Crud_Controller getController() {
		return controller;
	}

	public void setController(Crud_Controller controller) {
		this.controller = controller;
	}

}
