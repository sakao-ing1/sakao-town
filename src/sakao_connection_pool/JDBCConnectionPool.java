package sakao_connection_pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCConnectionPool {

	private static ArrayList<Connection> listConnectionavailable = new ArrayList<Connection>();/////OK
	
	private static ArrayList<Connection> listConnectionbusy = new ArrayList<Connection>();/////PAS SUR
	
	/////private ConnectionFileReader connectionfilereader;
	/////private static final int maxconnection = 5;
	/////private static int position = 0;

	/////Creer le pool de connection
	public JDBCConnectionPool() {
		this.initializeConnectionPool();
	}
	
	
	
	
	
	
	
	
	
	/////Creating a new connection in order to put it in the connection pool
	public Connection createNewConnection() throws ClassNotFoundException, SQLException {
		ConnectionFileReader connectionfilereader = new ConnectionFileReader();
		connectionfilereader.Read();
			Class.forName(connectionfilereader.getProperty("driver"));
			Connection con = DriverManager.getConnection(connectionfilereader.getProperty("url"), connectionfilereader.getProperty("login"),connectionfilereader.getProperty("password"));		
		System.out.println("A connection has been created");
		System.out.println("");
		return con;

	}
	
	/////Initializing the connection pool, feed the array list with 5 connections
	public void initializeConnectionPool() {
		while(!IsFull()) {
			try {
				listConnectionavailable.add(this.createNewConnection());
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
	}


		

///Check if there are less than 5 connection available in the pool
	public synchronized boolean IsFull() {
		final int MAX_POOL_CONNECTION = ConnectionFileReader.getMaxConnections();
		return (listConnectionavailable.size() < MAX_POOL_CONNECTION);
	}
	
	public synchronized boolean IsEmpty() {
		return listConnectionavailable.size() == 0;
	}

	/////Take a connection in the pool
	public synchronized Connection getConnectionFromPool() {
		Connection connection = null;
		while(this.IsEmpty()) {
			try {
				this.wait();
				System.out.println("No connection available please wait");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			connection = listConnectionavailable.get(0);
			listConnectionavailable.remove(0);
		
		return connection;
	}

	/////Put the connection in the pool
	public synchronized void returConnectionToPool(Connection connection) {
		listConnectionavailable.add(connection);
	}
	
	
	
	public void closeAllConnection() {
		for (Connection connection : listConnectionavailable) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	
	
	public ArrayList<Connection> getListConnectionavailable() {
		return JDBCConnectionPool.listConnectionavailable;
	}



	public void setListConnectionavailable(ArrayList<Connection> listConnectionavailable) {
		JDBCConnectionPool.listConnectionavailable = listConnectionavailable;
	}



	public ArrayList<Connection> getListConnectionbusy() {
		return listConnectionbusy;
	}



	public void setListConnectionbusy(ArrayList<Connection> listConnectionbusy) {
		JDBCConnectionPool.listConnectionbusy = listConnectionbusy;
	}


}
