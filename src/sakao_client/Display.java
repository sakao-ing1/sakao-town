package sakao_client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComponent;

public class Display extends JComponent {
	private static final long serialVersionUID = -1416435289803099003L;
	private double widthKM;/// user
	private double heightKM ;/// user
	private double widthPX;
	private double heightPX;
	private ArrayList<Point2D.Double> graphPoints;
	private int CityBudget;/// user MODIFIER A PARTIR DE MAXS  = 30 le x2
	private int aStationCost;/// user
	private int MaxStation;
	private int aLinkPrice = 15000;
	private int rest;
	private int maxDiviser;
	private int debt;
	private boolean shouldRunAlgo = false;
	private boolean shouldRunDisplay = false;
	ArrayList<Point2D.Double> graphNorthToSouth;
	ArrayList<Point2D.Double> graphWestToEast;
	ArrayList<Point2D.Double> graphNorthWestToSouthEast;
	ArrayList<Point2D.Double> graphNorthEastToSouthWest;

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		Graphics2D g2d = (Graphics2D) graphics;
		if(shouldRunAlgo) {/////CALCULATION OF THE VALUES THANKS TO THE ALGORITH AND DISPLAY OF THESE VALUES ***** BUTTON SIMULATE CITY
			this.StationAlgo(graphics, widthKM, heightKM, CityBudget, aStationCost);
			this.StationDrawerAlgo(graphics);
		}
		 if (shouldRunDisplay) {/////READING OF THE DB AND DISPLAY OF ITS ELEMENTS THAT WE NEED TO DRAW ***** BUTTON GENERATE CITY
			this.DrawCity(graphics, this.widthKM, this.heightKM);
			this.DrawPoints(g2d);
			this.DrawTramLine(g2d,this.graphNorthToSouth,this.graphWestToEast,this.graphNorthEastToSouthWest,this.graphNorthWestToSouthEast);

		}

	}
	
	
/////////////////////////DRAWING METHODE WHEN THEN CLIENT JUST WANT TO CHARGE AN EXISTING CITY/ THERE IS NO ALGORITHM/////////////////////////
	
	public void DrawCity(Graphics g, double w, double h) {
		this.widthPX = this.widthKM * 100;
		this.heightPX = this.heightKM * 100;
		g.setColor(Color.BLACK);
		Ellipse2D.Double oval = new Ellipse2D.Double(0.0, 0.0, widthPX, heightPX);
		Graphics2D gr2d = (Graphics2D) g;

		gr2d.fill(oval);

		g.setColor(Color.WHITE);

		gr2d.drawString("0", (float) (widthPX * 0.51), (float) (heightPX * 0.57));
		gr2d.drawString("-" + widthKM / 2, (float) (widthPX * 0.03), (float) (heightPX * 0.57));
		gr2d.drawString("" + widthKM / 2, (float) (widthPX * 0.95), (float) (heightPX * 0.57));
		gr2d.drawString("-" + heightKM / 2, (float) (widthPX * 0.51), (float) (heightPX * 0.97));
		gr2d.drawString(heightKM / 2 + "", (float) (widthPX * 0.51), (float) (heightPX * 0.07));

	}
	
	
	public void DrawStationForCRUD(Graphics g, double x, double y) {
		Graphics2D g2d = (Graphics2D) g;
			Rectangle2D.Double rectangle = new Rectangle2D.Double(xToPixel(x), yToPixel(y), 10, 10);
			g2d.fill(rectangle);
		}
	
	
	
	

/////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Test if the point is in the circle Here in the circle also means on the
	 * circle
	 */

	public boolean isInTheCircle(double x, double y) {
		boolean b;
		Point2D.Double p = new Point2D.Double(xToPixel(x), yToPixel(y));
		Point2D.Double center = new Point2D.Double(xToPixel(0.0), yToPixel(0.0));
		double distance = p.distance(center);
		///// System.out.println("distance : " + distance);
		if (distance <= this.widthPX / 2) {
			b = true;
		} else {
			b = false;
			///// Point2D.Double p1 = new Point2D.Double(x,y);

		}

		return b;
	}

////////////////////////////////////////////////////////////////////////////
	/*
	 * Test if the point is in the ellipse Here in the ellipse also means on the
	 * ellipse
	 */

	public boolean isInTheEllipse(double x, double y) {
		boolean b;
		double nx = x / this.widthKM;
		double ny = y / this.heightKM;
		double r = (nx * nx) + (ny * ny);
		System.out.println(r);
		if (r <= 1) {
			b = true;
		} else {
			b = false;
		}

		return b;
	}

////////////////////////////////////////////////////////////////////////////
	/*
	 * Gives the biggest multiple of MaxStation in order to know how to organize
	 * stations on the map
	 */

	public int StationDiviser(int MaxStation) {
		if (MaxStation == 1 || MaxStation == 2) {
			maxDiviser = 1;
		} else if (MaxStation == 3) {
			maxDiviser = 2;
			rest = 1;
		} else {
			for (int i = 1; i < MaxStation; i++) {
				if (MaxStation % i == 0 && maxDiviser <= i && maxDiviser < MaxStation) {
					maxDiviser = i;
				}

			} 
			/*if (maxDiviser == 1) {
				rest = 1; ///// TO USE DURING THE ALGORITHM DON'T FORGET THIS LAST STATION
				for (int i = 1; i < MaxStation - rest; i++) {

					if ((MaxStation - rest) % i == 0 && maxDiviser <= i && maxDiviser < (MaxStation - rest)) {
						maxDiviser = i;
					}
				}
			}*/
		}
		System.out.println("_____");
		System.out.println("MaxStation : " + MaxStation);
		System.out.println("MaxDiviser : " + maxDiviser);
		System.out.println("rest : " + this.rest);
		System.out.println("_____");

		return maxDiviser;
	}

	public Point2D.Double xSymetric(Point2D.Double p) {
		Point2D.Double symX;
		if (p.x == 0.0 && p.y == 0.0) {
			symX = new Point2D.Double(0.0, 0.0);
		}

		else {
			double x = p.x;
			double y = -(p.y);
			symX = new Point2D.Double(x, y);
		}
		return symX;
	}

	public Point2D.Double ySymetric(Point2D.Double p) {
		Point2D.Double symY;
		if (p.x == 0.0 && p.y == 0.0) {
			symY = new Point2D.Double(0.0, 0.0);
		} else {
			double x = -(p.x);
			double y = p.y;
			symY = new Point2D.Double(x, y);
		}
		return symY;
	}

	public Point2D.Double OSymetric(Point2D.Double p) {
		Point2D.Double symO;
		if (p.x == 0.0 && p.y == 0.0) {
			symO = new Point2D.Double(0.0, 0.0);
		} else {
			double x = -(p.x);
			double y = -(p.y);
			symO = new Point2D.Double(x, y);
		}
		return symO;
	}

	public Point2D.Double xMidSymetric(Point2D.Double p) {
		Point2D.Double symmidx;
		if (p.x == 0.0 && p.y == 0.0) {
			symmidx = new Point2D.Double(0.0, 0.0);
		}

		else {
			double x = 0;
			double y = p.y;
			symmidx = new Point2D.Double(x, y);
		}
		return symmidx;
	}

	public Point2D.Double yMidSymetric(Point2D.Double p) {
		double x = p.x;
		double y = 0;

		Point2D.Double symmidy = new Point2D.Double(x, y);

		return symmidy;
	}

	public boolean pontExist(Point2D.Double p) {
		boolean b = false;
		for (Point2D.Double point : this.graphPoints) {
			if ((point.x == p.x) && (point.y == p.y)) {
				b = true;
				break;
			}
		}

		return b;

	}
	///// Logical values into physical values
	public double xToPixel(double x) {
		return ((this.widthKM * 50) + (x * 50));
	}

	public double yToPixel(double y) {
		return ((this.heightKM * 50) + (-y * 50));
	}

	public boolean isACircle() {
		boolean b = false;
		if (this.widthKM == this.heightKM) {
			b = true;
		}
		return b;
	}

	public boolean isAnEllipse() {
		boolean b = false;
		if (this.widthKM != this.heightKM) {
			b = true;
		}
		return b;
	}

	public void DrawPoints(Graphics2D g) {
		g.setColor(Color.WHITE);
		for (int i = 0; i < graphPoints.size(); i++) {
			double x = graphPoints.get(i).x;
			double y = graphPoints.get(i).y;

			Rectangle2D.Double rectangle = new Rectangle2D.Double(xToPixel(x), yToPixel(y), 5, 5);
			g.fill(rectangle);
		}
		///this.DisplayCoord();

		///// System.out.println("size : " + graphPoints.size());
	}



	public void DisplayCoord() {
		for (Point2D.Double p : this.graphPoints) {
			System.out.println(p);
		}
	}

	public int HowManyNotInTheCircle() {
		int PointOutTheGraph = 0;

		if (this.isACircle()) {
			for (Point2D.Double p : graphPoints) {
				if (!this.isInTheCircle(p.x, p.y)) {
					PointOutTheGraph = PointOutTheGraph + 1;
				}
			}
		} else {
			for (Point2D.Double p : graphPoints) {
				if (!this.isInTheEllipse(p.x, p.y)) {
					PointOutTheGraph = PointOutTheGraph + 1;
				}
			}
		}

		return PointOutTheGraph;
	}
	
	
	public int HowManyInTheCircle() {
		int PointInTheGraph = 0;

		if (this.isACircle()) {
			for (Point2D.Double p : graphPoints) {
				if (this.isInTheCircle(p.x, p.y)) {
					PointInTheGraph = PointInTheGraph + 1;
				}
			}
		} else {
			for (Point2D.Double p : graphPoints) {
				if (this.isInTheEllipse(p.x, p.y)) {
					PointInTheGraph = PointInTheGraph + 1;
				}
			}
		}

		return PointInTheGraph;
	}

	public void cleanAndDraw(Graphics2D gr2d, int MaxStation) {
		Iterator<Point2D.Double> it = graphPoints.iterator();
		if (this.isACircle()) {
			while(it.hasNext()) {
				Point2D.Double p = it.next();
				if(!this.isInTheCircle(p.x, p.y)) {
					it.remove();
				}
			}
			
		} else {
			while(it.hasNext()) {
				Point2D.Double p = it.next();
				if(!this.isInTheEllipse(p.x, p.y)) {
					it.remove();
				}
			}
		}

		this.DrawPoints(gr2d);
	}

	public void PlacementOfTwoPerLine(int TotalLineToPack, int StationsToPackEachLine, int maxStation2) {
		double stepX1 = -(0.10 * widthKM);
		double stepX2 = -(0.30 * (widthKM));///// LORSQUE 2 A PLACER
		double stepX3 = -(0.60 * widthKM);///// LORSQUE 2 OU 3 A PLACER
		double stepX4 = -(0.80 * widthKM);///// LORSQUE 2 A PLACER
		int comptorP = 1;
		int comportO = 1;
		graphPoints = new ArrayList<Point2D.Double>(maxStation2);
		double stepY = (this.heightKM * 2) / TotalLineToPack;///// LORSQUE 2 OU 3 A PLACER

		double xp = 0.0;
		double yp = (this.heightKM * 0.95);

		for (int i = 1; i <= TotalLineToPack; i++) {
			for (int j = 1; j <= StationsToPackEachLine - 1; j++) {
				
				if (maxStation2 >= 20) {
					if (i % 2 == 1) {
						if (comportO % 2 == 1) {
							if(maxStation2 >= 40) {
								xp =-(0.40 * (widthKM));
							}
							else {
								xp = stepX2;

							}
							yp = yp - stepY;
							comportO = comportO + 1;
						} else {
							if(maxStation2 >= 40) {
								xp = -(0.20 * widthKM);
							}
							else {
								xp = -(0.15 * widthKM);

							}

							yp = yp - stepY;
							comportO = comportO + 1;

						}
					} else {
						if (comptorP % 2 == 1) {
							xp = stepX3;
							yp = yp - stepY;
							comptorP = comptorP + 1;

						} else {
							xp = stepX4;
							yp = yp - stepY;
							comptorP = comptorP + 1;

						}
					}
				}

				else {
					if (i % 2 == 1) {
						xp = stepX2;
						if (i == 1) {
							yp = (this.heightKM) * 0.80;
						} else {
							yp = yp - stepY;
						}
					}

					else if ((i % 2 == 0) && (this.isInTheCircle(stepX3, yp))
							|| (i % 2 == 0) && (this.isInTheEllipse(stepX3, yp))) {
						xp = stepX3;
						yp = yp - stepY;
					} else {
						xp = 0.0;
						yp = yp - stepY;

					}
				}

				Point2D.Double P = new Point2D.Double(xp, yp);

				while (this.pontExist(P)) {
					if (P.x < 0) {
						P.x = P.x - (widthKM / 10.0);

					} else if (P.x > 0) {
						P.x = P.x - (widthKM / 10.0);
					}
				}

				this.graphPoints.add(P);
				this.graphPoints.add(this.ySymetric(P));
			}
		}

	}

	public void PlaceTheRestOrTheDebt() {
		if (this.rest == 1) { ///// WHEN U HAVE AN IMPAIR MAXSTATION TO PLACE THE LAST ONE IS PLACED
			System.out.println(" jsuis au rest");
			Point2D.Double p = new Point2D.Double(0, 0);
			while (this.pontExist(p)) {
				p.x = p.x - (widthKM / 10.0);
			}
			this.graphPoints.add(p);
			System.out.println("Jai ajoute le rest soit un point : " + p);

		}

		if (this.debt == 1) {
			Point2D.Double p = new Point2D.Double(0, 0);
			while (this.pontExist(p)) {
				p.x = p.x - (widthKM / 10.0);
			}
			this.graphPoints.add(p);
			System.out.println("Jai ajoute la dette soit un point : " + p);

			MaxStation = MaxStation + 1;

			this.debt = this.debt - 1;
		}
	}

	public void PlaceTheFiveFirstCase(Graphics2D gr2d) {
		if (this.MaxStation == 1) {
			graphPoints = new ArrayList<Point2D.Double>(MaxStation);
			this.graphPoints.add(new Point2D.Double(0.0, 0.0));
			this.DrawPoints(gr2d);
		}

		else if (this.MaxStation == 2) {
			graphPoints = new ArrayList<Point2D.Double>(MaxStation);
			Point2D.Double p = new Point2D.Double((-this.widthKM) * 0.60, this.heightKM * 0.60);
			this.graphPoints.add(p);
			this.graphPoints.add(OSymetric(p));
			this.DrawPoints(gr2d);

		}

		else if (this.MaxStation == 3) {
			graphPoints = new ArrayList<Point2D.Double>(MaxStation);
			this.graphPoints.add(new Point2D.Double(0.0, this.heightKM * 0.60));
			this.graphPoints.add(new Point2D.Double((-this.widthKM) * 0.60, (-this.heightKM) * 0.40));
			this.graphPoints.add(new Point2D.Double(this.widthKM * 0.60, (-this.heightKM) * 0.40));
			this.DrawPoints(gr2d);
		}

		else if (this.MaxStation == 4) {
			graphPoints = new ArrayList<Point2D.Double>(MaxStation);
			Point2D.Double p = new Point2D.Double((-this.widthKM) * 0.50, this.heightKM * 0.50);
			this.graphPoints.add(p);
			this.graphPoints.add(xSymetric(p));
			this.graphPoints.add(ySymetric(p));
			this.graphPoints.add(OSymetric(p));
			this.DrawPoints(gr2d);

		}

		else if (this.MaxStation == 5) {
			graphPoints = new ArrayList<Point2D.Double>(MaxStation);
			Point2D.Double p = new Point2D.Double((-this.widthKM) * 0.50, this.heightKM * 0.50);
			this.graphPoints.add(p);
			this.graphPoints.add(xSymetric(p));
			this.graphPoints.add(ySymetric(p));
			this.graphPoints.add(OSymetric(p));
			this.graphPoints.add(new Point2D.Double(0.0, 0.0));
			this.DrawPoints(gr2d);
		}
	}

	public void ReplacePointOutTheGraph(int PointOuTheGraph) {
		int comptor = 0;
		if (PointOuTheGraph == 2) {
			double y = this.heightKM * 0.5;
			double x = 0.0;
			Point2D.Double P1 = new Point2D.Double(x, y);
			this.graphPoints.add(P1);
			this.graphPoints.add(xSymetric(P1));

		} else if (PointOuTheGraph == 1) {
			Point2D.Double P1 = new Point2D.Double(0.0, 0.0);
			while (this.pontExist(P1)) {
				if (P1.x <= 0) {
					P1.x = P1.x - (widthKM / 10.0);

				} else if (P1.x > 0) {
					P1.x = P1.x - (widthKM / 10.0);


				}
			}
			this.graphPoints.add(P1);

		} else {

			if (PointOuTheGraph % 2 == 0) {///// CAS PAIR A PLACER
				double y = this.heightKM * 0.80;
				double x = 0.0;
				double stepYPointOut = ((this.heightKM) * 2) / PointOuTheGraph;
				for (int k = 0; k < PointOuTheGraph; k++) {
					if (k == 0) {
						y = this.heightKM * 0.80;
						x = 0.0;
					} else {
						x = 0.0;
						y = y - stepYPointOut;
					}
					Point2D.Double P1 = new Point2D.Double(x, y);
					if(!this.isInTheCircle(P1.x,P1.y) || !this.isInTheEllipse(P1.x,P1.y)) {
						comptor = comptor + 1;
					}
					else {
						while (this.pontExist(P1)) {
							if (P1.x <= 0) {
								P1.x = P1.x - (widthKM / 10.0);
	
							} else if (P1.x > 0) {
								P1.x = P1.x + (widthKM / 10.0);
							}
						}
	
						this.graphPoints.add(P1);
						System.out.println(k);
						/////System.out.println(P1);
					}
				}
				double stepYComptor= ((this.heightKM) * 2) / comptor;

				for(int a = 0; a < comptor; a++) {
					Point2D.Double P1 = new Point2D.Double(x, y);
					if(a == 1) {
						P1.x = 0.0;
						P1.y = this.heightKM;
					}
					else {
						P1.x = 0.0;
						P1.y = this.heightKM - stepYComptor;
					}


					this.graphPoints.add(P1);
					/////System.out.println(P1);
				}

			} else if (PointOuTheGraph % 2 == 1 && PointOuTheGraph >= 3) { ///// CAR IMPAIRE SUP OU EGAL A 3 A PLACER
				double stepYPointOut = (this.heightKM * 2) / PointOuTheGraph;
				double x = 0.0;
				double y = this.heightKM * 0.80;
				int PointOuTheGraphBis = PointOuTheGraph - 1;

				for (int k = 0; k < PointOuTheGraphBis; k++) {
					if (k == 0) {
						y = this.heightKM * 0.80;
						x = 0.0;
					} else {
						x = 0.0;
						y = y - stepYPointOut;
					}
					Point2D.Double P1 = new Point2D.Double(x, y);
					while (this.pontExist(P1)) {
						if (P1.x <= 0) {
							P1.x = P1.x - (widthKM / 10.0);

						} else if (P1.x > 0) {
							P1.x = P1.x - (widthKM / 10.0);
						}
					}
					this.graphPoints.add(P1);
				}

			}
		}
	}

////////////////////////////////////////////////////////////////////////////
	/* Main algorithm which places the points on the map */

	public void StationAlgo(Graphics g, double widthKM,double heightKM, int CityBudget, int aStationCost) {
		Graphics2D gr2d = (Graphics2D) g;

		this.MaxStation = CityBudget / aStationCost;
		System.out.println(MaxStation);
		this.widthKM = widthKM;
		this.heightKM = heightKM;
		this.setCityBudget(CityBudget);
		this.setaStationCost(aStationCost);
		 this.widthPX = widthKM * 100.5;
		this.heightPX = heightKM * 100.5;
		this.rest = 0;
		this.maxDiviser = 1;
		this.debt = 0;


		
		this.DrawCity(g,this.widthKM, this.heightKM);
		
		g.setColor(Color.RED);


		///// Establish points position and add them to the list______ heart of the
		///// algorithm <3

		if (this.MaxStation <= 5) {
			this.PlaceTheFiveFirstCase(gr2d);
			this.DrawLines(gr2d);

		}

		else {
			int TotalLineToPack;
			int StationsToPackEachLine;

			if (this.widthKM == this.heightKM || this.widthKM != this.heightKM) {///// Circle case
				/// int step = (TotalStationsToPack - StationsToPackEachLine);
				int PointOuTheGraph = 0;
				int PointInTheGraph = 0;

				/*
				 * else if(StationsToPackEachLine == 3) { } else {
				 * this.PlacementOfTwoPerLine(TotalLineToPack, StationsToPackEachLine); }
				 */
				if (this.MaxStation % 2 == 0) /* || this.MaxStation/this.StationDiviser() == 3) */ {
					System.out.println("jsuis dans le if et maxstation vaut : " + this.MaxStation);
					TotalLineToPack = this.StationDiviser(this.MaxStation);
					StationsToPackEachLine = MaxStation / TotalLineToPack;
					this.PlacementOfTwoPerLine(TotalLineToPack, StationsToPackEachLine, MaxStation);
					this.PlaceTheRestOrTheDebt();
					PointOuTheGraph = this.HowManyNotInTheCircle();
					PointInTheGraph = this.HowManyInTheCircle();
					System.out.println("Point out the graph : " + PointOuTheGraph);
					System.out.println("Point in the graph : " + PointInTheGraph);

					this.ReplacePointOutTheGraph(PointOuTheGraph);
					this.cleanAndDraw(gr2d, MaxStation);
					
					PointOuTheGraph = this.HowManyNotInTheCircle();
					PointInTheGraph = this.HowManyInTheCircle();
					
					System.out.println("Point out the graph : " + PointOuTheGraph);
					System.out.println("Point in the graph : " + PointInTheGraph);
					System.out.println("size : " + this.graphPoints.size());

				}

				else if(this.MaxStation % 2 == 1){
					int max = MaxStation - 1;
					System.out.println("je suis dans le else et maxstation vaut " + this.MaxStation);
					this.debt = this.debt + 1;
					System.out.println(debt);
					TotalLineToPack = this.StationDiviser(max);
					StationsToPackEachLine = MaxStation / TotalLineToPack;
					this.PlacementOfTwoPerLine(TotalLineToPack, StationsToPackEachLine, max);
					this.PlaceTheRestOrTheDebt();
					PointOuTheGraph = this.HowManyNotInTheCircle();
					PointInTheGraph = this.HowManyInTheCircle();

					System.out.println("Point out the graph : " + PointOuTheGraph);
					System.out.println("Point in the graph : " + PointInTheGraph);

					this.ReplacePointOutTheGraph(PointOuTheGraph);
					this.cleanAndDraw(gr2d, MaxStation);
					PointOuTheGraph = this.HowManyNotInTheCircle();
					PointInTheGraph = this.HowManyInTheCircle();
					System.out.println("Point out the graph : " + PointOuTheGraph);
					System.out.println("Point in the graph : " + PointInTheGraph);
					System.out.println("size : " + this.graphPoints.size());

				}

				/////this.DrawLines(gr2d);
//////////////////////////////////////////////////////////////////////////////:

				///// TOUS LES POINTS SONT PLACES
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


				///this.PlaceTheRestOrTheDebt();

				/*
				 * System.out.println(""); System.out.println(""); System.out.println("");
				 * 
				 * this.DisplayCoord(); System.out.println(""); System.out.println("");
				 * System.out.println("");
				 */

				/*
				 * System.out.println(""); System.out.println(""); System.out.println("");
				 * 
				 * this.DisplayCoord(); System.out.println(""); System.out.println("");
				 * System.out.println("");
				 */
				/*
				 * PointOuTheGraph = this.HowManyNotInTheCircle();
				 * System.out.println("Not in the circle : " + PointOuTheGraph);
				 * System.out.println("In the circle : " + (this.graphPoints.size() -
				 * PointOuTheGraph)); System.out.println("Station to pack each line : " +
				 * StationsToPackEachLine); System.out.println("Number of line : " +
				 * TotalLineToPack); System.out.println("Lack : " + (MaxStation -
				 * graphPoints.size())); System.out.println(""); System.out.println("size : " +
				 * this.graphPoints.size()); System.out.println("Not in the circle : " +
				 * this.HowManyNotInTheCircle()); System.out.println("In the circle : " +
				 * (this.graphPoints.size() - this.HowManyNotInTheCircle()));
				 * System.out.println("Station to pack each line : " + StationsToPackEachLine);
				 * System.out.println("Number of line : " + TotalLineToPack);
				 * System.out.println("Lack : " + (MaxStation - graphPoints.size()));
				 * System.out.println("MaxStation : " + MaxStation); if (this.cleanAndDraw(gr2d,
				 * MaxStation) == this.HowManyNotInTheCircle()) {
				 * System.out.println("tout est ok"); } else { System.out.println("refaire");
				 * System.out.println("clean : " + this.cleanAndDraw(gr2d, MaxStation));
				 * System.out.println("out : " + this.HowManyNotInTheCircle()); }
				 */

			}

		}
	}
	
	public void DisplayGraph(ArrayList<Point2D.Double> graph) {
		Iterator<Point2D.Double> it = graph.iterator();
		while(it.hasNext()) {
			Point2D.Double p = it.next();
			System.out.println(p);
		}
	}
	
	
	public void fillGraphs() {
		/////ON PEUT ICI SI JAMAIS ON METS LES GRAPH EN INSTANCE DE CLASSE
		ArrayList<Point2D.Double> graphNorthToSouth = new ArrayList<Point2D.Double>();
		ArrayList<Point2D.Double> graphWestToEast = new ArrayList<Point2D.Double>();
		ArrayList<Point2D.Double> graphNorthWestToSouthEast = new ArrayList<Point2D.Double>();
		ArrayList<Point2D.Double> graphNorthEastToSouthWest= new ArrayList<Point2D.Double>();
		double North = this.getHeightKM() * 0.60;
		double South = -(this.getHeightKM() * 0.60);
		double West = -(this.getWidthKM()*0.60);
		double East = (this.getWidthKM()*0.60);

		Iterator<Point2D.Double> it = graphPoints.iterator();
			while(it.hasNext()) {
				Point2D.Double p = it.next();
				if(p.y < South ) {
					graphNorthToSouth.add(p);
				}
				
				else if(p.y> North) {
					graphNorthToSouth.add(p);
				}
				
				else if(p.x < West) {
					graphWestToEast.add(p);
				}
				else if (p.x > East) {
					graphWestToEast.add(p);
				}
				else if (p.x == 0.0) {
					graphNorthToSouth.add(p);
				}
				
				else if(p.y == 0.0) {
					graphWestToEast.add(p);
				}
				
				else if(p.x >= West && (p.y <= North || p.y >= South)) {
					graphNorthWestToSouthEast.add(p);
				}
				else if (p.x <= East && (p.y <= North || p.y >= South)){
					graphNorthEastToSouthWest.add(p);
				}			
			}
			
			System.out.println("North TO South");
			this.DisplayGraph(graphNorthToSouth);
			System.out.println("");
			System.out.println("West to East");
			this.DisplayGraph(graphWestToEast);
			System.out.println("");
			System.out.println("NorthEast to SouthWEST");
			this.DisplayGraph(graphNorthEastToSouthWest);
			System.out.println("");
			System.out.println("NorthWEST to SouthEast");
			this.DisplayGraph(graphNorthWestToSouthEast);
			System.out.println("");
			
			

	}
	
	
	public void DrawLines(ArrayList<Point2D.Double> graph,Graphics2D g,Color c) {
		for (int i = 0; i < graph.size(); i++) {
			g.setColor(c);

			for (int j = i + 1; j < graph.size(); j++) {
				double x1 = graph.get(i).x;
				double y1 = graph.get(i).y;
				double x2 = graph.get(i+1).x;
				double y2 = graph.get(i+1).y;
				g.draw(new Line2D.Double(xToPixel(x1), yToPixel(y1), xToPixel(x2), yToPixel(y2)));
			}
		}
	}
	
	
	public void StationDrawerAlgo(Graphics g) {
		Graphics2D gr2d = (Graphics2D) g;
		this.graphNorthToSouth = new ArrayList<Point2D.Double>();
		this.graphWestToEast = new ArrayList<Point2D.Double>();
		this.graphNorthWestToSouthEast = new ArrayList<Point2D.Double>();
		this.graphNorthEastToSouthWest= new ArrayList<Point2D.Double>();
		double North = this.getHeightKM() * 0.60;
		double South = -(this.getHeightKM() * 0.60);
		double West = -(this.getWidthKM()*0.60);
		double East = (this.getWidthKM()*0.60);

		Iterator<Point2D.Double> it = graphPoints.iterator();
			while(it.hasNext()) {
				Point2D.Double p = it.next();
				if(p.y < South || p.y > North || p.x == 0.0) {
					graphNorthToSouth.add(p);
				}
				
				if(p.x < West || p.x >East || p.y == 0.0 || (p.y <= this.getHeightKM() * 0.2 &&p.y >= -(this.getHeightKM() * 0.2))) {
					graphWestToEast.add(p);
				}
	
				if ((p.x >= West && p.x < 0.0) &&(p.y <= North && p.y> 0.0)){
					graphNorthWestToSouthEast.add(p);
				}
				if ((p.x > 0.0 && p.x <= East) && (p.y >= South && p.y < 0.0)) {
					graphNorthWestToSouthEast.add(p);
				}
				if ((p.x <= East && p.x > 0.0) &&(p.y <= North && p.y> 0.0)){
					graphNorthEastToSouthWest.add(p);
				}
				if ((p.x < 0.0 && p.x >= West) && (p.y >= South && p.y < 0.0)) {
					graphNorthEastToSouthWest.add(p);
				}
			
			}
			
			System.out.println("North TO South");
			this.DisplayGraph(graphNorthToSouth);
			System.out.println("");
			System.out.println("West to East");
			this.DisplayGraph(graphWestToEast);
			System.out.println("");
			System.out.println("NorthEast to SouthWEST");
			this.DisplayGraph(graphNorthEastToSouthWest);
			System.out.println("");
			System.out.println("NorthWEST to SouthEast");
			this.DisplayGraph(graphNorthWestToSouthEast);
			System.out.println("");
			this.DrawTramLine(gr2d,this.graphNorthToSouth,this.graphWestToEast,this.graphNorthEastToSouthWest,this.graphNorthWestToSouthEast);

	}
	
	
	public void DrawTramLine(Graphics g,ArrayList<Point2D.Double> graphNorthToSouth,ArrayList<Point2D.Double> graphWestToEast
			,ArrayList<Point2D.Double> graphNorthEastToSouthWest,ArrayList<Point2D.Double> graphNorthWestToSouthEast) {
		Graphics2D gr2d = (Graphics2D) g;
		this.DrawLines(graphNorthToSouth,gr2d,Color.RED);
		this.DrawLines(graphWestToEast,gr2d,Color.CYAN);
		this.DrawLines(graphNorthEastToSouthWest,gr2d,Color.ORANGE);
		this.DrawLines(graphNorthWestToSouthEast,gr2d,Color.GREEN);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////

	public void DrawLines(Graphics2D g) {///// ALGO DE PRIM
		for (int i = 0; i < graphPoints.size(); i++) {
			g.setColor(Color.GREEN);

			for (int j = i + 1; j < graphPoints.size(); j++) {
				double x1 = graphPoints.get(i).x;
				double y1 = graphPoints.get(i).y;
				double x2 = graphPoints.get(i+1).x;
				double y2 = graphPoints.get(i+1).y;
				g.draw(new Line2D.Double(xToPixel(x1), yToPixel(y1), xToPixel(x2), yToPixel(y2)));
			}
		}
		/*for (int i = 0; i < graphPoints.size(); i++) {
			g.setColor(Color.YELLOW);

			for (int j = 0; j < graphPoints.size(); j++) {
				double x1 = graphPoints.get(i).x;
				double y1 = graphPoints.get(i).y;
				double x2 = graphPoints.get(j).x;
				double y2 = graphPoints.get(j).y;
				g.draw(new Line2D.Double(xToPixel(x1), yToPixel(y1), xToPixel(x2), yToPixel(y2)));
			}
		}*/
	}

	public double getWidthPX() {
		return widthPX;
	}

	public void setWidthPX(double widthPX) {
		this.widthPX = widthPX;
	}

	public double getHeightPX() {
		return heightPX;
	}

	public void setHeightPX(double heightPX) {
		this.heightPX = heightPX;
	}

	public ArrayList<Point2D.Double> getGraphPoints() {
		return graphPoints;
	}

	public void setGraphPoints(ArrayList<Point2D.Double> graphPoints) {
		this.graphPoints = graphPoints;
	}

	public int getMaxStation() {
		return MaxStation;
	}

	public void setMaxStation(int maxStation) {
		MaxStation = maxStation;
	}

	public int getaLinkPrice() {
		return aLinkPrice;
	}

	public void setaLinkPrice(int aLinkPrice) {
		this.aLinkPrice = aLinkPrice;
	}

	public int getRest() {
		return rest;
	}

	public void setRest(int rest) {
		this.rest = rest;
	}

	public int getCityBudget() {
		return CityBudget;
	}

	public void setCityBudget(int cityBudget) {
		CityBudget = cityBudget;
	}

	public int getaStationCost() {
		return aStationCost;
	}

	public void setaStationCost(int aStationCost) {
		this.aStationCost = aStationCost;
	}

	public double getWidthKM() {
		return widthKM;
	}

	public void setWidthKM(double widthKM) {
		this.widthKM = widthKM;
	}

	public double getHeightKM() {
		return heightKM;
	}

	public void setHeightKM(double heightKM) {
		this.heightKM = heightKM;
	}

	public int getMaxDiviser() {
		return maxDiviser;
	}

	public void setMaxDiviser(int maxDiviser) {
		this.maxDiviser = maxDiviser;
	}

	public int getDebt() {
		return debt;
	}

	public void setDebt(int debt) {
		this.debt = debt;
	}

	
	public void repaint(){
		// repaint le component courant
		super.repaint();
		// repaint tous les components qu'il possède
		for(int i = 0; i < this.getComponentCount(); i++)
		this.getComponent(i).repaint();
	}


	public boolean isShouldRunAlgo() {
		return shouldRunAlgo;
	}


	public void setShouldRunAlgo(boolean shouldRunAlgo) {
		this.shouldRunAlgo = shouldRunAlgo;
	}


	public boolean isShouldRunDisplay() {
		return shouldRunDisplay;
	}


	public void setShouldRunDisplay(boolean shouldRunDisplay) {
		this.shouldRunDisplay = shouldRunDisplay;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public ArrayList<Point2D.Double> getGraphNorthToSouth() {
		return graphNorthToSouth;
	}


	public void setGraphNorthToSouth(ArrayList<Point2D.Double> graphNorthToSouth) {
		this.graphNorthToSouth = graphNorthToSouth;
	}


	public ArrayList<Point2D.Double> getGraphWestToEast() {
		return graphWestToEast;
	}


	public void setGraphWestToEast(ArrayList<Point2D.Double> graphWestToEast) {
		this.graphWestToEast = graphWestToEast;
	}


	public ArrayList<Point2D.Double> getGraphNorthWestToSouthEast() {
		return graphNorthWestToSouthEast;
	}


	public void setGraphNorthWestToSouthEast(ArrayList<Point2D.Double> graphNorthWestToSouthEast) {
		this.graphNorthWestToSouthEast = graphNorthWestToSouthEast;
	}


	public ArrayList<Point2D.Double> getGraphNorthEastToSouthWest() {
		return graphNorthEastToSouthWest;
	}


	public void setGraphNorthEastToSouthWest(ArrayList<Point2D.Double> graphNorthEastToSouthWest) {
		this.graphNorthEastToSouthWest = graphNorthEastToSouthWest;
	}
	
}
