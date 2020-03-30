package sakao_server;

import java.util.ArrayList;

import sakao_common.Personne;

public class Crud_Service {

	private Crud_Controller controller;

	public Crud_Service() {
		this.controller = new Crud_Controller();
	}

	public ArrayList<Personne> showPersonne() {
		return controller.showPersonne();
	}

	public boolean addPersonne(String name, int age) {
		boolean b = false;
		try {
			Personne p = new Personne(name, age);
			controller.addPersonne(p);
			this.controller.getDatasource().reset(controller.getDatasource().getListConnectionbusy()
					.get(controller.getDatasource().getListConnectionbusy().size() - 1));

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
			this.controller.getDatasource().reset(controller.getDatasource().getListConnectionbusy()
					.get(controller.getDatasource().getListConnectionbusy().size() - 1));

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
			this.controller.getDatasource().reset(controller.getDatasource().getListConnectionbusy()
					.get(controller.getDatasource().getListConnectionbusy().size() - 1));

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
			this.controller.getDatasource().reset(controller.getDatasource().getListConnectionbusy()
					.get(controller.getDatasource().getListConnectionbusy().size() - 1));

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
			this.controller.getDatasource().reset(controller.getDatasource().getListConnectionbusy()
					.get(controller.getDatasource().getListConnectionbusy().size() - 1));

			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean updatePersonneName(int id, String name) {
		boolean b = false;
		try {
			controller.updatePersonneName(id, name);
			this.controller.getDatasource().reset(controller.getDatasource().getListConnectionbusy()
					.get(controller.getDatasource().getListConnectionbusy().size() - 1));

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
