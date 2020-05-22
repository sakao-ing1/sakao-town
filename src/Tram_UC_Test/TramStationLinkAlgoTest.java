package Tram_UC_Test;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;

import sakao_client.TramStationComputer;
import sakao_common.Request;
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
			ArrayList<Point2D.Double> listCoord = new ArrayList<Point2D.Double>();/////File's values int this list


			for (int i = 2; i < listStationInfo.size()-2; i++) {
				String s1 = listStationInfo.get(i) + "\n";
				String s2 = listStationInfo.get(i+2) + "\n";
				i = i +5;
				Point2D.Double p = new Point2D.Double(Double.parseDouble(s1),Double.parseDouble(s2));
				listCoord.add(p);
			}
			///////////////////////////////////////////////////////////////////// EXPECTED
			///////////////////////////////////////////////////////////////////// VALUES
			/////LINEA OF THE CITY RESULTS PREPARED
			ArrayList<Point2D.Double> TramLineAResultPREPARED = new ArrayList<Point2D.Double>();
			
			
			/////LINEB OF THE CITY RESULTS PREPARED
			ArrayList<Point2D.Double> TramLineBResultPREPARED = new ArrayList<Point2D.Double>();
			
			
			/////LINEC OF THE CITY RESULTS PREPARED
			ArrayList<Point2D.Double> TramLineCResultPREPARED = new ArrayList<Point2D.Double>();
			
			
			/////LINED OF THE CITY RESULTS PREPARED
			ArrayList<Point2D.Double> TramLineDResultPREPARED = new ArrayList<Point2D.Double>();
			
			
///////////////
			
			/////LAUNCH THE MESH ALGORITHM
			computer.StationLinkAlgo(listCoord);

			///// STACK THE RESULTS OF THE ALGORITHM IN THESE LISTS IN ORDER TO COMPARE
			
			/////LINEA OF THE CITY RESULTS OF THE ALGORITHM
			ArrayList<Point2D.Double> TramLineAResult = new ArrayList<Point2D.Double>();
			TramLineAResult = computer.getGraphNorthToSouth();
			
			
			/////LINEB OF THE CITY RESULTS OF THE ALGORITHM
			ArrayList<Point2D.Double> TramLineBResult = new ArrayList<Point2D.Double>();
			TramLineBResult = computer.getGraphWestToEast();
			
			
			/////LINEC OF THE CITY RESULTS OF THE ALGORITHM
			ArrayList<Point2D.Double> TramLineCResult = new ArrayList<Point2D.Double>();
			TramLineCResult = computer.getGraphNorthEastToSouthWest();
			
			
			/////LINED OF THE CITY RESULTS OF THE ALGORITHM
			ArrayList<Point2D.Double> TramLineDResult = new ArrayList<Point2D.Double>();
			TramLineDResult = computer.getGraphNorthWestToSouthEast();
			
			System.out.println("//////////////////////////////////////////////////////////");
			System.out.println("THE TEST BEGIN LET'S CHECK IF THE ALGORITHM GIVES EXPECTED VALUES");
			System.out.println("//////////////////////////////////////////////////////////");
			System.out.println("");
			System.out.println("Lists of prepared points that we expect to have after the algorithm launch");
			System.out.println("");
			
			System.out.println("Prepared list : "  );
			System.out.println("A : " + TramLineAResultPREPARED.toString());
			System.out.println("Size : " + TramLineAResultPREPARED.size());
			System.out.println("");
			
			
			System.out.println("Prepared list : "  );
			System.out.println("B : " + TramLineBResultPREPARED.toString());
			System.out.println("Size : " + TramLineBResultPREPARED.size());
			System.out.println("");
			
			
			System.out.println("Prepared list : "  );
			System.out.println("C : " + TramLineCResultPREPARED.toString());
			System.out.println("Size : " + TramLineCResultPREPARED.size());
			System.out.println("");
			
			
			System.out.println("Prepared list : "  );
			System.out.println("D : " + TramLineDResultPREPARED.toString());
			System.out.println("Size : " + TramLineDResultPREPARED.size());
			System.out.println("");

	/////FIRST STEP COMPARE SIZES
			if(TramLineAResult.size() == TramLineAResultPREPARED.size()) {
				System.out.println("_______________");
				System.out.println("First step : The number of points expected is correct !");
				System.out.println("Each one have " + TramLineAResult.size() + " points !");
				System.out.println("Expected results : 15");
				System.out.println("_______________");
				System.out.println("");
				
				
				for (int i = 0; i < TramLineAResult.size(); i++) {
					if (TramLineAResultPREPARED.get(i).equals(TramLineAResult.get(i))) {
						System.out.println("_______________");
						System.out.println("The algorithm generates expected points : n° " + (i + 1));
						System.out.println("Prepared list expected results : " + TramLineAResultPREPARED.get(i));
						System.out.println("Results of the algorithm : " + TramLineAResult.get(i));
						System.out.println("_______________");
						System.out.println("");
					} else {
						System.out.println("Fail : There is a problem, the algorithm did not generate expected points");
					}
				}
			}
			
			
			else {
				System.out.println("Fail : The number of points expected is not correct line A !");
			}
			
			
			if(TramLineBResult.size() == TramLineBResultPREPARED.size()) {
				System.out.println("_______________");
				System.out.println("First step : The number of points expected is correct !");
				System.out.println("Each one have " + TramLineBResult.size() + " points !");
				System.out.println("Expected results : 15");
				System.out.println("_______________");
				System.out.println("");
				
				
				for (int i = 0; i < TramLineBResult.size(); i++) {
					if (TramLineAResultPREPARED.get(i).equals(TramLineBResult.get(i))) {
						System.out.println("_______________");
						System.out.println("The algorithm generates expected points : n° " + (i + 1));
						System.out.println("Prepared list expected results : " + TramLineBResultPREPARED.get(i));
						System.out.println("Results of the algorithm : " + TramLineBResult.get(i));
						System.out.println("_______________");
						System.out.println("");
					} else {
						System.out.println("Fail : There is a problem, the algorithm did not generate expected points");

					}
				}
				
				
				
			}
			
			
			else {
				System.out.println("Fail : The number of points expected is not correct for line B !");
			}
			
			
			if(TramLineCResult.size() == TramLineCResultPREPARED.size()) {
				System.out.println("_______________");
				System.out.println("First step : The number of points expected is correct for line C !");
				System.out.println("Each one have " + TramLineCResult.size() + " points !");
				System.out.println("Expected results : 15");
				System.out.println("_______________");
				System.out.println("");
				
				
				for (int i = 0; i < TramLineCResult.size(); i++) {
					if (TramLineCResultPREPARED.get(i).equals(TramLineCResult.get(i))) {
						System.out.println("_______________");
						System.out.println("The algorithm generates expected points : n° " + (i + 1));
						System.out.println("Prepared list expected results : " + TramLineCResultPREPARED.get(i));
						System.out.println("Results of the algorithm : " + TramLineCResult.get(i));
						System.out.println("_______________");
						System.out.println("");
					} else {
						System.out.println("Fail : There is a problem, the algorithm did not generate expected points");

					}
				}
			}
			
			
			else {
				System.out.println("Fail : The number of points expected is not correct for line D !");
			}
			
			
			if(TramLineDResult.size() == TramLineDResultPREPARED.size()) {
				System.out.println("_______________");
				System.out.println("First step : The number of points expected is correct !");
				System.out.println("Each one have " + TramLineDResult.size() + " points !");
				System.out.println("Expected results : 15");
				System.out.println("_______________");
				System.out.println("");
				
				
				for (int i = 0; i < TramLineDResult.size(); i++) {
					if (TramLineDResultPREPARED.get(i).equals(TramLineDResult.get(i))) {
						System.out.println("_______________");
						System.out.println("The algorithm generates expected points : n° " + (i + 1));
						System.out.println("Prepared list expected results : " + TramLineDResultPREPARED.get(i));
						System.out.println("Results of the algorithm : " + TramLineDResult.get(i));
						System.out.println("_______________");
						System.out.println("");
					} else {
						System.out.println("Fail : There is a problem, the algorithm did not generate expected points");

					}
				}
			}
			
			
			else {
				System.out.println("Fail : The number of points expected is not correct !");
			}
			
			
			
			
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
