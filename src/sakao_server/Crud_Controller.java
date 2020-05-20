package sakao_server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sakao_common.Configuration;
import sakao_common.SmartCity;
import sakao_common.Station;
import sakao_common.TramLineA;
import sakao_common.TramLineB;
import sakao_common.TramLineC;
import sakao_common.TramLineD;
import sakao_connection_pool.DataSource;

public class Crud_Controller {

	public Crud_Controller() throws ClassNotFoundException {
	}


	
	/////////// TEST SI UNE VILLE EXIST///////////////////////// ///////////////////////// ///////////////////////// /////////////////////////
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
	
	public boolean cityExists() throws ClassNotFoundException, SQLException {
		boolean b = false;
		try {
			Connection con = DataSource.getConnection();
			String sql = "select * from smartcity";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				b = true;
				return b;
			}
			DataSource.returnConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return b;

	}
	
	
	/////////////////////////////// SELECT ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
	public ArrayList<String> showCity() {
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from smartcity");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new SmartCity(rs.getInt("idcity"), rs.getDouble("heightkm"), rs.getDouble("widthkm"),
						rs.getInt("budget"), rs.getInt("astationcost"), rs.getString("name")).toString());
			}

			DataSource.returnConnection(con);
			System.out.println("fini");
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}

		return displayer;
	}
	
	
	public ArrayList<String> showStations(){
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from station");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new Station(rs.getInt("idstation"), rs.getString("typestation"), rs.getDouble("coordx"),
						rs.getDouble("coordy"), rs.getBoolean("isbuilt"), rs.getInt("idzone")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}
		
		return displayer;
	}
	
	
	public ArrayList<String> showTramLineA(){
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from tramlinea");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new TramLineA(rs.getInt("idtramlinea"), rs.getInt("idstation"),rs.getDouble("coordx"),rs.getDouble("coordy")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}
		
		return displayer;
	}
	
	
	public ArrayList<String> showTramLineB(){
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from tramlineb");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new TramLineA(rs.getInt("idtramlineb"), rs.getInt("idstation"),rs.getDouble("coordx"),rs.getDouble("coordy")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}
		
		return displayer;
	}
	
	
	public ArrayList<String> showTramLineC(){
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from tramlinec");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new TramLineA(rs.getInt("idtramlinec"), rs.getInt("idstation"),rs.getDouble("coordx"),rs.getDouble("coordy")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}
		
		return displayer;
	}
	
	
	public ArrayList<String> showTramLineD(){
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from tramlined");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new TramLineA(rs.getInt("idtramlined"), rs.getInt("idstation"),rs.getDouble("coordx"),rs.getDouble("coordy")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}
		
		return displayer;
	}
	
	
	
	
	
	public ArrayList<String> showCityDimension(){
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select heightkm,widthkm from smartcity");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new SmartCity(rs.getDouble("heightkm"), rs.getDouble("widthkm")).toString());
			}

			DataSource.returnConnection(con);
			System.out.println("fini");
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}

		return displayer;
	}
	
	
	
	
	
	
	
	
	public ArrayList<String> showStationCoord(){
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select coordx,coordy from station");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new Station(rs.getDouble("coordx"),
						rs.getDouble("coordy")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}
		
		return displayer;
	}
	
	
	
	
	

	
	
	
	
	
	public ArrayList<String> showTramLineACoord(){
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select coordx,coordy from tramlinea");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new TramLineA(rs.getDouble("coordx"), rs.getDouble("coordy")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}
		
		return displayer;
	}
	
	
	
	
	public ArrayList<String> showTramLineBCoord(){
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select coordx,coordy from tramlineb");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new TramLineB(rs.getDouble("coordx"), rs.getDouble("coordy")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}
		
		return displayer;
	}
	
	
	
	
	public ArrayList<String> showTramLineCCoord(){
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select coordx,coordy from tramlinec");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new TramLineC(rs.getDouble("coordx"), rs.getDouble("coordy")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}
		
		return displayer;
	}
	
	
	
	
	public ArrayList<String> showTramLineDCoord(){
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select coordx,coordy from tramlined");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new TramLineD(rs.getDouble("coordx"), rs.getDouble("coordy")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}
		
		return displayer;
	}
	
	
	///////////////////////////////////////  ADD ///////////////////////// ///////////////////////// ///////////////////////// /////////////////////////
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
	
	
	
	public void addASmartCity(ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			String sqlreq = "insert into smartcity(heightkm,widthkm,budget,astationcost,name) VALUES";
			String s = "sakao";
			System.out.println("list size : " + list.size());

			int i = 2;
			while (i < list.size()) {
				sqlreq += "(" + list.get(i) + "," + list.get(i + 2) + "," + list.get(i + 4) + "," + list.get(i + 6)
						+ "," + "'" + s + "'" + "),";
				i += list.size();
			}
			
			sqlreq = sqlreq.substring(0, sqlreq.length() - 1);
			System.out.println(sqlreq);

			PreparedStatement pstmt = con.prepareStatement(sqlreq);

			pstmt.executeUpdate();
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}
	
	
	public void addStation(ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			
			String sqlReqFkCity = "select idcity from smartcity";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlReqFkCity);
			Object idfk = null;
			while (rs.next()) {
			 idfk = rs.getInt(1);
			}
			
			DataSource.returnConnection(con);
			
			
			con = DataSource.getConnection();
			String sqlreq = "insert into station(typestation,coordx,coordy,isbuilt,idcity,idtransportation) VALUES";
			
			System.out.println("list size : " + list.size());

			int i = 2;
			while (i < list.size()) {
				sqlreq += "(" + "'" + list.get(i) + "'" + "," + list.get(i + 2) + "," + list.get(i + 4) + "," + list.get(i + 6) +"," + idfk + ","+ 1
						 + "),";
				i += 8;
			}

			sqlreq = sqlreq.substring(0, sqlreq.length() - 1);
			System.out.println(sqlreq);

			PreparedStatement pstmt = con.prepareStatement(sqlreq);

			pstmt.executeUpdate();
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}
	
	
	
	public void addTramLineAStation(ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();

			String sqlReqFkCoord = 
					"select station.idstation from station "
					+ "inner join tramlinea on "
					+ "station.coordx =  + " + list.get(2) 
					+ "and station.coordy = " + list.get(4);
			String sqlreq = "insert into tramlinea(idstation,coordx,coordy) VALUES";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlReqFkCoord);
			Object idfk = null;
			while (rs.next()) {
			 idfk = rs.getInt(1);
			}
			
			DataSource.returnConnection(con);

			System.out.println("list size : " + list.size());

			int i = 2;
			while (i < list.size()) {
				sqlreq += "(" + idfk+ "," + list.get(i) + "," + list.get(i + 2) 
						 + "),";
				i += 6;
			}
			 con = DataSource.getConnection();
			sqlreq = sqlreq.substring(0, sqlreq.length() - 1);
			System.out.println(sqlreq);
			PreparedStatement pstmt = con.prepareStatement(sqlreq);
			pstmt.executeUpdate();
			DataSource.returnConnection(con);
			rs.close();


		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}
	
	
	
	public void addTramLineBStation(ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();

			String sqlReqFkCoord = 
					"select station.idstation from station "
					+ "inner join tramlineb on "
					+ "station.coordx =  + " + list.get(2) 
					+ "and station.coordy = " + list.get(4);
			String sqlreq = "insert into tramlineb(idstation,coordx,coordy) VALUES";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlReqFkCoord);
			Object idfk = null;
			while (rs.next()) {
			 idfk = rs.getInt(1);
			}
			
			DataSource.returnConnection(con);

			System.out.println("list size : " + list.size());

			int i = 2;
			while (i < list.size()) {
				sqlreq += "(" + idfk+ "," + list.get(i) + "," + list.get(i + 2) 
						 + "),";
				i += 6;
			}
			 con = DataSource.getConnection();
			sqlreq = sqlreq.substring(0, sqlreq.length() - 1);
			System.out.println(sqlreq);
			PreparedStatement pstmt = con.prepareStatement(sqlreq);
			pstmt.executeUpdate();
			DataSource.returnConnection(con);
			rs.close();


		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}
	
	
	public void addTramLineCStation(ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			int idfk = 0;

			String sqlReqFkCoord = 
					"select station.idstation from station "
					+ "inner join tramlinec on "
					+ "station.coordx =  + " + list.get(2) 
					+ "and station.coordy = " + list.get(4);
			String sqlreq = "insert into tramlinec(coordx,coordy) VALUES";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlReqFkCoord);
			while (rs.next()) {
			 idfk = rs.getInt(1);
			}
			int idcoord = idfk;
			DataSource.returnConnection(con);

			System.out.println("list size : " + list.size());

			int i = 2;
			while (i < list.size()) {
				sqlreq += "(" + list.get(i) + "," + list.get(i + 2) 
						 + "),";
				i += 4;
			}
			 con = DataSource.getConnection();
			sqlreq = sqlreq.substring(0, sqlreq.length() - 1);
			System.out.println(sqlreq);
			PreparedStatement pstmt = con.prepareStatement(sqlreq);
			pstmt.executeUpdate();
			rs.close();
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}
	
	
	public void addTramLineDStation(ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			int idfk = 0;

			String sqlReqFkCoord = 
					"select station.idstation from station "
					+ "inner join tramlined on "
					+ "station.coordx =  + " + list.get(2) 
					+ "and station.coordy = " + list.get(4);
			String sqlreq = "insert into tramlined(coordx,coordy) VALUES";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlReqFkCoord);
			while (rs.next()) {
			 idfk = rs.getInt(1);
			}
			int idcoord = idfk;
			DataSource.returnConnection(con);

			System.out.println("list size : " + list.size());

			int i = 2;
			while (i < list.size()) {
				sqlreq += "(" + list.get(i) + "," + list.get(i + 2) 
						 + "),";
				i += 4;
			}
			 con = DataSource.getConnection();
			sqlreq = sqlreq.substring(0, sqlreq.length() - 1);
			System.out.println(sqlreq);
			PreparedStatement pstmt = con.prepareStatement(sqlreq);
			pstmt.executeUpdate();
			rs.close();
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}
	
	
	
	
	
	
	
	///////////////////////// DELETE ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// /////////////////////////
	
	public void deleteACity() throws SQLException, ClassNotFoundException {
		Connection con = DataSource.getConnection();

		Statement query = con.createStatement();
		query.executeUpdate("TRUNCATE TABLE smartcity cascade");
		///System.out.println(result + "rows deleted");
		DataSource.returnConnection(con);
	}
	
	
	public void deleteStations() throws SQLException, ClassNotFoundException {
		Connection con = DataSource.getConnection();
		Statement query = con.createStatement();
		query.executeUpdate("TRUNCATE TABLE station cascade");
		/////System.out.println(result + "row(s) deleted");
		DataSource.returnConnection(con);
	}
	

	
	
	public void deleteTramLineA() throws SQLException, ClassNotFoundException {
		Connection con = DataSource.getConnection();
		Statement query = con.createStatement();
		query.executeUpdate("TRUNCATE TABLE tramlinea cascade");
		/////System.out.println(result + "row(s) deleted");
		DataSource.returnConnection(con);
	}
	
	
	public void deleteTramLineB() throws SQLException, ClassNotFoundException {
		Connection con = DataSource.getConnection();
		Statement query = con.createStatement();
		query.executeUpdate("TRUNCATE TABLE tramlineb cascade");
		/////System.out.println(result + "row(s) deleted");
		DataSource.returnConnection(con);
	}
	
	
	public void deleteTramLineC() throws SQLException, ClassNotFoundException {
		Connection con = DataSource.getConnection();
		Statement query = con.createStatement();
		query.executeUpdate("TRUNCATE TABLE tramlinec cascade");
		/////System.out.println(result + "row(s) deleted");
		DataSource.returnConnection(con);
	}
	
	
	public void deleteTramLineD() throws SQLException, ClassNotFoundException {
		Connection con = DataSource.getConnection();
		Statement query = con.createStatement();
		query.executeUpdate("TRUNCATE TABLE tramlined cascade");
		/////System.out.println(result + "row(s) deleted");
		DataSource.returnConnection(con);
	}
	
	
	
	
	
	
	
	
	
	
	
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
		/*String SelectIDCity = "select IDCity,heightkm,widthkm from smartcity;";
		ResultSet rs = stmt.executeQuery(SelectIDCity);
		DataSource.returnConnection(con);





		int idcity= 0;
		double w = 0.0;
		double h =0.0;
		while(rs.next()) {
			idcity = rs.getInt(1);
			w = rs.getInt(2);
			h = rs.getInt(3);
		}


		Connection con3 = DataSource.getConnection();

		String sql = "select idtransportation from transportation where typeoftransport = 'tramway';";
		ResultSet rs3 = stmt.executeQuery(sql);
		DataSource.returnConnection(con3);
		int idtransportation = 0;
		if(rs3.first()) {
			 idtransportation = rs3.getInt(1);			
		}
	

		String s = "";
			if(station.getCoordX()< w/2 ) {
				s = "WEST";
			}
			else if(station.getCoordX() >= w/2 ) {
				s = "EAST";
			}
			else if(station.getCoordY() < h/2 ) {
				s = "SOUTH";

			}
			else if(station.getCoordY()>= h/2 ) {
				s = "NORTH";

			}
			Connection con2 = DataSource.getConnection();

			String rsql = "select idzone from zone where location = " + s + ";";
			ResultSet rs1 = stmt.executeQuery(rsql);
			int idzone = 0;
			if(rs1.first()) {
				 idzone = rs1.getInt(1);				
			}
			DataSource.returnConnection(con2);
			Connection con1 = DataSource.getConnection();*/	

	/////Ajouter dasn la table configurarion
	
	public void addOnConfiguration(String target, ArrayList<Object> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			String req = "insert into configuration(frequency, dateofconfiguration) VALUES ";
			int i = 2;
			while(i<list.size()) {
				req+="("+list.get(i)+","+"'"+list.get(i+2)+"'"+",";
			i+=6;
			}
			 req= req.substring(0,req.length()-1);
			 System.out.println(req);
			PreparedStatement pstm = con.prepareStatement(req);
			pstm.executeUpdate();
			System.out.println(req);
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
	/////Afficher la table configuration
	
	public ArrayList<Object> showAllConfiguration() throws ClassNotFoundException {
		ArrayList<Object> retour = new ArrayList<Object>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("select * from configuration");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				retour.add(new Configuration(rs.getInt("idconfiguration"), rs.getInt("frequency"),
						rs.getDate("dateofconfiguration")));
				DataSource.returnConnection(con);
			}

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;

	}
	
}


/*public Student existStudent(int id) throws ClassNotFoundException {
Student retour = null;
try {
	Connection con = DataSource.getConnection();

	PreparedStatement pt = con.prepareStatement("select * from personne where id =" + id);
	ResultSet rs = pt.executeQuery();
	while (rs.next()) {
		int idS = rs.getInt(1);
		String name = rs.getString(2);
		int age = rs.getInt(3);
		retour = new Student(idS, name, age);
		DataSource.returnConnection(con);
	}

} catch (SQLException ex) {
	System.out.println("erreur " + ex.getMessage());
}
return retour;

}*/

// Request SELECT

/*public ArrayList<Object> showStudent(String target) throws ClassNotFoundException {
ArrayList<Object> retour = new ArrayList<Object>();
try {
	Connection con = DataSource.getConnection();

	PreparedStatement pt = con.prepareStatement("select * from" + target);
	ResultSet rs = pt.executeQuery();
	while (rs.next()) {
		int id = rs.getInt(1);
		String name = rs.getString(2);
		int age = rs.getInt(3);
		retour.add(new Student(id, name, age));
		DataSource.returnConnection(con);
	}

} catch (SQLException ex) {
	System.out.println("erreur " + ex.getMessage());
}
return retour;

}*/


/*
* public void deleteStudentByName(int ID) throws ClassNotFoundException { try {
* Connection con = DataSource.getConnection(); Statement stmt =
* con.createStatement(); ResultSet rslt =
* stmt.executeQuery("select id from Student where name = " + name);
* while(rslt.next()) { if(rslt.equals(obj)) }
* 
* PreparedStatement pt = con
* .prepareStatement("delete from Student where name like ?"); pt.setString(1,
* name); pt.execute(); DataSource.returnConnection(con);
* 
* } catch (SQLException ex) { System.out.println("erreur " + ex.getMessage());
* }
* 
* }
*/

/*public void deleteStudentById(int ID) throws ClassNotFoundException {
try {
	Connection con = DataSource.getConnection();
	PreparedStatement pt = con.prepareStatement("delete from personne where id = " + ID);
	pt.execute();
	DataSource.returnConnection(con);

}

catch (SQLException ex) {
	System.out.println("erreur " + ex.getMessage());
}
}*/

// Request INSERT

/*public void addOnTable(String target, ArrayList<String> list) throws ClassNotFoundException {
try {

	Connection con = DataSource.getConnection();
	System.out.println(con);

	String req = "insert into " + target + "(";
	
	int i = 1;
	while(i < list.size() - 2 ) {
		if(i == list.size() - 3) {
			req = req + list.get(i) + ") ";
			break;
		}
		else { 
			req = req + list.get(i) + "," ;
			i = i+2;
		}
	}
	req = req + "values (";
	int j = 2;
	while(j < list.size() - 1) {
		if(j == list.size() - 2) {
			req = req + list.get(j) + ")";
			break;
		}
		else {
			req = req + list.get(j) + ",";
			j = j + 2;
		}
	}
	System.out.println(req);
	PreparedStatement pstm = con.prepareStatement(req);
	/*pstm.setString(1, p.getName());
	pstm.setInt(2, p.getAge());
	pstm.executeUpdate();
	System.out.println(req);
	DataSource.returnConnection(con);

} catch (SQLException ex) {
	ex.printStackTrace();
}
}*/

// Request UPDATE

/*public void updateStudentAge(int id, int age) throws ClassNotFoundException {
try {
	Connection con = DataSource.getConnection();

	PreparedStatement pstm = con.prepareStatement(" UPDATE personne SET age = ?  WHERE id = ?");
	pstm.setInt(1, age);
	pstm.setInt(2, id);
	pstm.executeUpdate();
	DataSource.returnConnection(con);

} catch (SQLException ex) {
	System.out.println("erreur " + ex.getMessage());
}
}*/

/*public void updateStudentName(int id, String name) throws ClassNotFoundException {
try {
	Connection con = DataSource.getConnection();

	PreparedStatement pstm = con.prepareStatement(" UPDATE personne SET name = ?  WHERE id = ?");
	pstm.setString(1, name);
	pstm.setInt(2, id);
	pstm.executeUpdate();
	DataSource.returnConnection(con);

} catch (SQLException ex) {
	System.out.println("erreur " + ex.getMessage());
}

}

public void deleteAllStudent() throws SQLException, ClassNotFoundException {
Connection con = DataSource.getConnection();

Statement query = con.createStatement();
int result = query.executeUpdate("TRUNCATE TABLE personne");
DataSource.returnConnection(con);

}*/
