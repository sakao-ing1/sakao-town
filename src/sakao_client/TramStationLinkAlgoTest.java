package sakao_client;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;

import sakao_common.Request;
import sakao_common.Station;
import sakao_insert_client.TablesToBeInserted;

public class TramStationLinkAlgoTest {
		TramStationComputer computer = new TramStationComputer();

		public TramStationLinkAlgoTest() throws IOException {

		}

		public void computeTramStation() throws IOException {
			TablesToBeInserted table = new TablesToBeInserted();
			String s = table.readFileSmartCityLinkAlgo();///// STACK THE FILE'S VALUES INTO s
			Request reqStation = new ObjectMapper().readValue(s, Request.class);///// JSON TO OBJEC
			ArrayList<String> listStationInfo = reqStation.getList();///// STACK THE REQUEST LIST INTO list IN ORDER TO READ
			ArrayList<Point2D.Double> listCoord = new ArrayList<Point2D.Double>();

			System.out.println("LIST : " + listStationInfo);
			System.out.println(listStationInfo.size());
			for (int i = 2; i < listStationInfo.size()-2; i++) {
				String s1 = listStationInfo.get(i) + "\n";
				String s2 = listStationInfo.get(i+2) + "\n";
				i = i +5;
				Point2D.Double p = new Point2D.Double(Double.parseDouble(s1),Double.parseDouble(s2));
				listCoord.add(p);
			}




			///// STACK THE RESULTS OF THE ALGORITHM IN THIS LIST IN ORDER TO COMPARE
			ArrayList<Point2D.Double> listResult = new ArrayList<Point2D.Double>();
			listResult = computer.getGraphPoints();
			System.out.println("//////////////////////////////////////////////////////////");
			System.out.println("THE TEST BEGIN LET'S CHECK IF THE ALGORITHM GIVES EXPECTED VALUES");
			System.out.println("//////////////////////////////////////////////////////////");
			System.out.println("");
			System.out.println("List of prepared points that we expect to have after the algorithm launch");
			System.out.println("Prepared list : "  );
			
	
			
			System.out.println("");
			System.out.println("//////////////////////////////////////////////////////////");
			System.out.println("THE TEST ENDS");
			System.out.println("//////////////////////////////////////////////////////////");

		}

		public static void main(String[] args) throws IOException {
			TramStationLinkAlgoTest tramstation = new TramStationLinkAlgoTest();
			tramstation.computeTramStation();

		}

	
}
