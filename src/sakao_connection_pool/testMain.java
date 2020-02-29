package sakao_connection_pool;

import sakao_connection_pool.DataSource;;

public class testMain {

	public static void main(String[] args) throws ClassNotFoundException {
		DataSource ds = new DataSource();
		ds.showPersonne();
		ds.addPersonne("kumanan", 66);
		ds.deletePersonne("aziz");
		ds.updatePersonne(2, "alain", 60);
		
	}

}
