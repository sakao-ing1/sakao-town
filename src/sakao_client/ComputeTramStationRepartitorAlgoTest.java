package sakao_client;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;

import sakao_common.Request;
import sakao_insert_client.TablesToBeInserted;

public class ComputeTramStationRepartitorAlgoTest {

	TramStationComputer computer = new TramStationComputer();

	public ComputeTramStationRepartitorAlgoTest() throws IOException {

	}

	public void computeTramStation() throws IOException {
		TablesToBeInserted table = new TablesToBeInserted();
		String s = table.readFileSmartCityDisplay();///// STACK THE FILE'S VALUES INTO s
		Request reqSmartCity = new ObjectMapper().readValue(s, Request.class);///// JSON TO OBJEC
		ArrayList<String> listCityInfo = reqSmartCity.getList();///// STACK THE REQUEST LIST INTO list IN ORDER TO READ
																///// VARIABLES VALUES
		int length = 0;

		///////////////////////////////////////////////////////////////////// EXPECTED
		///////////////////////////////////////////////////////////////////// VALUES
		Point2D.Double p1 = new Point2D.Double(-1.5, 4.0);
		Point2D.Double p2 = new Point2D.Double(1.5, 4.0);
		Point2D.Double p3 = new Point2D.Double(-3.0, 2.571428571428571);
		Point2D.Double p4 = new Point2D.Double(3.0, 2.571428571428571);
		Point2D.Double p5 = new Point2D.Double(-1.5, 1.1428571428571426);
		Point2D.Double p6 = new Point2D.Double(1.5, 1.1428571428571426);
		Point2D.Double p7 = new Point2D.Double(-3.0, -0.28571428571428603);
		Point2D.Double p8 = new Point2D.Double(3.0, -0.28571428571428603);
		Point2D.Double p9 = new Point2D.Double(-1.5, -1.7142857142857146);
		Point2D.Double p10 = new Point2D.Double(1.5, -1.7142857142857146);
		Point2D.Double p11 = new Point2D.Double(-3.0, -3.1428571428571432);
		Point2D.Double p12 = new Point2D.Double(3.0, -3.1428571428571432);
		Point2D.Double p13 = new Point2D.Double(-1.5, -4.571428571428572);
		Point2D.Double p14 = new Point2D.Double(1.5, -4.571428571428572);
		Point2D.Double p15 = new Point2D.Double(0.0, 0.0);

		ArrayList<Point2D.Double> listPrepared = new ArrayList<Point2D.Double>();
		listPrepared.add(p1);
		listPrepared.add(p2);
		listPrepared.add(p3);
		listPrepared.add(p4);
		listPrepared.add(p5);
		listPrepared.add(p6);
		listPrepared.add(p7);
		listPrepared.add(p8);
		listPrepared.add(p9);
		listPrepared.add(p10);
		listPrepared.add(p11);
		listPrepared.add(p12);
		listPrepared.add(p13);
		listPrepared.add(p14);
		listPrepared.add(p15);
///////////////////////////////////////////////////////////////

		///// USE THE FILE'S VALUE TO LAUNCH ALGORITHMS
		double w = Double.parseDouble(listCityInfo.get(2));
		double h = Double.parseDouble(listCityInfo.get(4));
		int b = Integer.parseInt(listCityInfo.get(6));
		int c = Integer.parseInt(listCityInfo.get(8));

		/////////////////////////////////////////////////
		computer.setWidthKM(w);
		computer.setHeightKM(h);
		computer.setCityBudget(b);
		computer.setaStationCost(c);

		///// LAUNCH THE REPARTITOR ALGORITHM
		computer.StationRepartitorAlgo(w, h, b, c);

		///// STACK THE RESULTS OF THE ALGORITHM IN THIS LIST IN ORDER TO COMPARE
		ArrayList<Point2D.Double> listResult = new ArrayList<Point2D.Double>();
		listResult = computer.getGraphPoints();

		if (listResult.size() == listPrepared.size()) {///// FIRST STEP COMPARE THE SIZE
			length = listPrepared.size();
			System.out.println("//////////////////////////////////////////////////////////");
			System.out.println("Fist step : The number of points expected is correct !");
			System.out.println("Each one have " + length + " points !");
			System.out.println("//////////////////////////////////////////////////////////");
			System.out.println("");

			///// NEXT STEP COMPARE THE VALUES IN EACH LIST
			for (int i = 0; i < length; i++) {
				if (listPrepared.get(i).equals(listResult.get(i))) {
					System.out.println("//////////////////////////////////////////////////////////");
					System.out.println("The algorithm generates expected points : " + (i + 1));
					System.out.println("Prepared list : " + listPrepared.get(i));
					System.out.println("Results list : " + listResult.get(i));
					System.out.println("//////////////////////////////////////////////////////////");
					System.out.println("");
				} else {
					System.out.println("Fail : There is a problem, the algorithm did not generate expected points");

				}
			}
		} else {
			System.out.println("Fail : The number of points expected is not correct !");
		}

	}

	public static void main(String[] args) throws IOException {
		ComputeTramStationRepartitorAlgoTest tramstation = new ComputeTramStationRepartitorAlgoTest();
		tramstation.computeTramStation();

	}

}
