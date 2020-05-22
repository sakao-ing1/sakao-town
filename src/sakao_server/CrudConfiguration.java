/*package sakao_server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import sakao_common.Configuration;
import sakao_common.Student;
import sakao_connection_pool.DataSource;

public class CrudConfiguration {

	public CrudConfiguration() throws ClassNotFoundException {

	}

	public ArrayList<T> showAllConfiguration() throws ClassNotFoundException {
		ArrayList<T> retour = new ArrayList<T>();
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
	
	public void addOnConfiguration( ArrayList<String> list) throws ClassNotFoundException {
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
	
	
	
	
	

}
*/
