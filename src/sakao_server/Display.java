package sakao_server;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;


public class Display extends JComponent {
	private static final long serialVersionUID = -1416435289803099003L;
	private double widthKM = 5.0;///user
	private double heightKM = 5.0;///user
	private double widthPX = widthKM * 100; 
	private double heightPX = heightKM * 100;
	private ArrayList<Point2D.Double> graphPoints;
	/////private ArrayList<Point>> Points = new ArrayList<>();
	/////HashMap<Double,ArrayList<Point> grahpoints = new HasMapHashMap<Double,ArrayList<Point>> ();
	private int CityBudget = 24;///user
	private int aStationCost = 1;///user
	private int MaxStation = CityBudget/aStationCost;
	private int aLinkPrice = 15000;
	private int rest = 0;
	private int maxDiviser = 1;
	private int debt = 0;

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		graphPoints = new ArrayList<Point2D.Double>(MaxStation);
		Graphics2D g2d = (Graphics2D) graphics;

		
		graphics.setColor(Color.BLACK);
		Ellipse2D.Double oval = new Ellipse2D.Double(0.0, 0.0, widthPX, heightPX);
		g2d.fill(oval);
		
	
		
		graphics.setColor(Color.WHITE);
		g2d.draw(new Line2D.Double(0, heightPX/2, widthPX, heightPX/2));
		g2d.draw(new Line2D.Double(widthPX/2, 0, widthPX/2, heightPX));


		
		g2d.drawString("0", (float)(widthPX  * 0.51), (float)(heightPX * 0.57));
		g2d.drawString("-"+ widthKM,(float)(widthPX  * 0.03),(float) (heightPX * 0.57));
		g2d.drawString(""+widthKM,(float) (widthPX  * 0.95), (float) (heightPX * 0.57));
		g2d.drawString("-" +heightKM,(float) (widthPX  * 0.51), (float)(heightPX * 0.97));	
		g2d.drawString( heightKM  +"",(float) (widthPX  * 0.51), (float) (heightPX * 0.07));	
		
		
		graphics.setColor(Color.RED);
		this.StationAlgo(graphics);
		System.out.println(this.isInTheCircle(4.0, -3.0));
        /*Rectangle2D.Double rectangle = new  Rectangle2D.Double(xToPixel(-(widthKM-2)), yToPixel((heightKM - 3)),5, 5);
        g2d.fill(rectangle);
*/
		
	}
	
////////////////////////////////////////////////////////////////////////////
	/* Test if the point is in the circle
	 * Here in the circle also means on the circle*/
	
	public boolean isInTheCircle(double x, double y) {
		boolean b;
		Point2D.Double p = new Point2D.Double(xToPixel(x),yToPixel(y));
		Point2D.Double center = new Point2D.Double(xToPixel(0.0),yToPixel(0.0));
		double distance = p.distance(center);
		/////System.out.println("distance : " + distance);
		if(distance <= this.widthPX/2) {
			b = true;
		}
		else {
		b = false;
		/////Point2D.Double p1 = new Point2D.Double(x,y);
		/////System.out.println("False : " + p1);

		}

		return b;
	}
	
	
////////////////////////////////////////////////////////////////////////////
	/* Test if the point is in the ellipse
	 * Here in the ellipse also means on the ellipse*/
	
	public boolean isInTheEllipse(double x, double y) {
		boolean b;
		double nx = x/this.widthKM;
		double ny = y/this.heightKM;
		double r = (nx*nx) + (ny*ny);
		System.out.println(r);
		if(r<= 1) {
			b = true;
		}
		else {
			b = false;
		}
		
		return b;
	}
////////////////////////////////////////////////////////////////////////////
	/* Gives the biggest multiple of MaxStation in order to know how to organize stations on the map */
	
	
	public int StationDiviser() {
		System.out.println("MaxStation : " + MaxStation);
		if(MaxStation == 1 ||  MaxStation == 2) {
			maxDiviser = 1;
		}
		else if(MaxStation == 3) {
			maxDiviser = 2;
			rest =1;
		}
		else {
			for(int i = 1; i < MaxStation; i++) {
				if(MaxStation % i == 0 && maxDiviser <= i && maxDiviser < MaxStation) {
					maxDiviser = i;
				}
				
			}
			if (maxDiviser == 1) {
				rest = 1; ///// TO USE DURING THE ALGORITHM DON'T FORGET THIS LAST STATION
				for(int i = 1; i < MaxStation - rest; i++) {

					if((MaxStation - rest) % i == 0 && maxDiviser <= i && maxDiviser < (MaxStation - rest)) {
						maxDiviser = i;
					}
				}
			}
		}
		System.out.println("MaxDiviser : " + maxDiviser);
        System.out.println("rest : " + this.rest);
		return maxDiviser;
	}
	
	
	
	
	public Point2D.Double xSymetric(Point2D.Double p) {
		Point2D.Double symX;
		if(p.x == 0.0 && p.y == 0.0) {
			symX = new Point2D.Double(0.0,0.0);
		}
		
		
		else {
			double x = p.x;
			double y = -(p.y);
			symX = new Point2D.Double(x,y);
		}
		return symX;
	}
	
	
	public Point2D.Double ySymetric(Point2D.Double p) {
		Point2D.Double symY;
		if(p.x == 0.0 && p.y == 0.0) {
			symY = new Point2D.Double(0.0,0.0);
		}
		else {
			double x = -(p.x);
			double y = p.y;
			symY = new Point2D.Double(x,y);
		}
		return symY;
	}
	
	public Point2D.Double OSymetric(Point2D.Double p) {
		Point2D.Double symO;
		if(p.x == 0.0 && p.y == 0.0) {
			symO = new Point2D.Double(0.0,0.0);
		}
		else {
			double x = -(p.x);
			double y = -(p.y);
			symO = new Point2D.Double(x,y);
		}
		return symO;
	}
	
	public Point2D.Double xMidSymetric(Point2D.Double p) {
		Point2D.Double symmidx;
			if(p.x == 0.0 && p.y == 0.0) {
				symmidx = new Point2D.Double(0.0,0.0);
			}
			
			else {
				double x =0;
				double y = p.y;
				symmidx = new Point2D.Double(x,y);
			}
		return symmidx;
	}
	
	
	public Point2D.Double yMidSymetric(Point2D.Double p) {
		double x =p.x;
		double y = 0;
		
		Point2D.Double symmidy = new Point2D.Double(x,y);
		
		return symmidy;
	}
	
	public boolean pontExist(Point2D.Double p) {
		boolean b = false;
			for(Point2D.Double point : this.graphPoints) {
				if((point.x == p.x) && (point.y == p.y)) {
					b = true;
					break;
				}
			}
		
		return b;
		
	}
	
	
	public void DrawPoints(	Graphics2D g) {
        for (int i = 0; i < graphPoints.size(); i++) {
            double x = graphPoints.get(i).x;
            double y = graphPoints.get(i).y ;
            Rectangle2D.Double rectangle = new  Rectangle2D.Double(xToPixel(x), yToPixel(y),5, 5);
            g.fill(rectangle);
        }
		this.DisplayCoord();

        System.out.println("size : " + graphPoints.size());
	}
	
	
	public void DrawLines(Graphics2D g) {
		for (int i = 0; i < graphPoints.size() ; i++) {
        	for(int j = i+1; j<graphPoints.size(); j++) {
	            double x1 = graphPoints.get(i).x;
	            double y1 = graphPoints.get(i).y;
	            double x2 = graphPoints.get(j).x;
	            double y2 = graphPoints.get(j).y;
	            g.draw(new Line2D.Double(xToPixel(x1), yToPixel(y1), xToPixel( x2), yToPixel( y2)));
        	}
		}
	}
	
	
	public void DisplayCoord() {
		for(Point2D.Double p : this.graphPoints ) {
			System.out.println(p);
		}
	}
	
	public int HowManyNotInTheCircle() {
		int PointInTheGraph = 0;
		
		
		
		for(Point2D.Double p : graphPoints) {
			if(!this.isInTheCircle(p.x, p.y)) {
				PointInTheGraph = PointInTheGraph + 1;
			}
		}
		
		return PointInTheGraph;
	}
	
	
	public int clean() {///// PAS DE FOR EACH SI C POUR MODIFIER UN ELEMENT
		int compteur = 0;
		if(this.widthKM == this.heightKM) {
			for (int i = 0; i < this.graphPoints.size(); i++) {
				while (!this.isInTheCircle(this.graphPoints.get(i).x, this.graphPoints.get(i).y)) {
					this.graphPoints.remove(this.graphPoints.get(i));
					compteur = compteur + 1;
	
				}
			}
		}
		else {
			for (int i = 0; i < this.graphPoints.size(); i++) {
				if(!this.isInTheCircle(this.graphPoints.get(i).x, this.graphPoints.get(i).y)) {
					this.graphPoints.remove(this.graphPoints.get(i));
					compteur = compteur + 1;

				}
			}
			
		}

		return compteur;
	}
	
	public void PlacementOfTwoPerLine(int TotalLineToPack, int StationsToPackEachLine) {
		double stepX4 = -(0.10 * widthKM);
		double stepX1 = -(0.30 * (widthKM));///// LORSQUE 2 A PLACER
		double stepX2 = -(0.60 * widthKM);///// LORSQUE 2 OU 3 A PLACER
		double stepX3 = -(0.80 * widthKM);///// LORSQUE 2 A PLACER
		int comptor = 1;

		double stepY = (this.heightKM * 2) / TotalLineToPack;///// LORSQUE 2 OU 3 A PLACER

		double xp = 0.0;
		double yp = (this.heightKM * 0.80);
		
		for (int i = 1; i <= TotalLineToPack; i++) {
			for (int j = 1; j <= StationsToPackEachLine - 1; j++) {
				///// QD ON PLACE DEUX PAR LIGNE A FAIRE CELUI DE TROIS PAR LIGNE EXEMPLE POUR 9
				
				
				
				
				if(this.MaxStation >= 25) {
					
				}
				
				else {
					if(i%2 == 1) {
						xp = stepX1;
						if(i == 1) {
							yp = (this.heightKM)* 0.80;
						}
						else {
							yp = yp -stepY;
						}
					}
					
					else if((i%2 == 0) && (this.isInTheCircle(stepX2, yp))) {
						xp = stepX2;
						yp = yp -stepY;
					}
					else {
						xp = 0.0;
						yp = yp -stepY;

					}
				}

				/*if (i % 2 == 1) { /////If we are on a odd line, xp = stepX1
					if (i == 1) {
						System.out.println("jsuis dans le if i = 1 de i impair");
						yp = (this.heightKM) * 0.80;
						xp = stepX1;

					} 
					else {
						System.out.println("jsuis dans le else de i impair");
						if(comptor % 2 == 1) {
							System.out.println("jsuis dans le if compteur impaire du else du i impair");
							yp = yp - stepY;
							xp = stepX1;
							comptor = comptor + 1;
						}
						else  {
							System.out.println("jsuis dans le else du else du i impair");
							yp = yp - stepY;
							xp = stepX4;
							comptor = comptor + 1;
						}
					}
				}

				else if ((i % 2 == 0) ) {
					System.out.println("jsuis dans le i pair");
					if ((this.isInTheCircle(stepX2, yp)) && (comptor % 2 == 1)) {/////If we are in a peer line and stepX2 is in the circle and stepX2 is not we take stepX2
						System.out.println("jsuis dans le if i pair");

						xp = stepX2;
						yp = yp - stepY;
						comptor = comptor + 1;
					}
					else if (this.isInTheCircle(stepX3, yp)&& (comptor % 2 == 0) && (this.MaxStation >= 25))
					
					
					{
						System.out.println("jsuis dans le else if i pair");
						xp = stepX3;
						yp = yp - stepY;
						comptor = comptor + 1;
					}
					else {
						System.out.println("jsuis dans le else i pair");
						xp = stepX2;
						yp = yp - stepY;
						comptor = comptor + 1;
					}
	
				}*/
				
				  /*else if((i % 2 == 0) && (this.isInTheCircle(stepX3, yp))){/////SI on est sur une ligne paire = eloigne alors on prend comme celui le plus grand 
					  System.out.println("jssuis dans le else");
					  xp = stepX3; 
					  yp = yp -stepY; 
				  }*/
				 

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
	
	
	public void PlacementPerThreePerLine(int TotalLineToPack, int StationsToPackEachLine) {
		
	}
	
	
	
////////////////////////////////////////////////////////////////////////////
	/* Main algorithm which places the points on the map*/
	
	public void StationAlgo(Graphics g) {
		Graphics2D gr2d = (Graphics2D) g;
		/////Establish points position and add them to the list______ heart of the algorithm <3
		
		if(this.MaxStation == 1) {
			this.graphPoints.add(new Point2D.Double (0.0,0.0));
			this.DrawPoints(gr2d);
		}
		
		
		else if(this.MaxStation == 2) {
			Point2D.Double p = new Point2D.Double ((-this.widthKM )* 0.60,this.heightKM * 0.60);
			this.graphPoints.add(p);
			this.graphPoints.add(OSymetric(p));
			this.DrawPoints(gr2d);

		}
		
		
		else if (this.MaxStation == 3) {
			this.graphPoints.add(new Point2D.Double (0.0,this.heightKM * 0.60));
			this.graphPoints.add(new Point2D.Double ((-this.widthKM )* 0.60, (-this.heightKM) * 0.40));
			this.graphPoints.add(new Point2D.Double (this.widthKM * 0.60, (-this.heightKM) * 0.40));
			this.DrawPoints(gr2d);
		}
		
		else if (this.MaxStation == 4) {
			Point2D.Double p = new Point2D.Double ((-this.widthKM )* 0.50,this.heightKM * 0.50);
			this.graphPoints.add(p);
			this.graphPoints.add(xSymetric(p));
			this.graphPoints.add(ySymetric(p));
			this.graphPoints.add(OSymetric(p));
			this.DrawPoints(gr2d);


		}
		
		else if (this.MaxStation == 5) {
			Point2D.Double p = new Point2D.Double ((-this.widthKM )* 0.50,this.heightKM * 0.50);
			this.graphPoints.add(p);
			this.graphPoints.add(xSymetric(p));
			this.graphPoints.add(ySymetric(p));
			this.graphPoints.add(OSymetric(p));
			this.graphPoints.add(new Point2D.Double(0.0,0.0));
			this.DrawPoints(gr2d);
		}
		
		
		else {
			int TotalLineToPack;
			int StationsToPackEachLine;

			if(this.MaxStation/this.StationDiviser() == 2) /*|| this.MaxStation/this.StationDiviser() == 3)*/ {
				TotalLineToPack = this.StationDiviser();
				StationsToPackEachLine = MaxStation/TotalLineToPack;
			}

			else {
				int MaxStationLessOne = MaxStation-1;
				TotalLineToPack = this.StationDiviser();
				StationsToPackEachLine = MaxStationLessOne/TotalLineToPack;
				this.debt = 1;
			}
			
			


		
			if(this.widthKM == this.heightKM || this.widthKM != this.heightKM) {/////Circle case
				///int step = (TotalStationsToPack - StationsToPackEachLine);
				int PointOuTheGraph = 0;
				int PointInTheGraph = 0;
				

				/////if(this.MaxStation <= 24) {
					this.PlacementOfTwoPerLine(TotalLineToPack, StationsToPackEachLine);
				/////}
				/*else if(StationsToPackEachLine == 3) {
				}*/

				/*else {
					this.PlacementOfTwoPerLine(TotalLineToPack, StationsToPackEachLine);
				}*/
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
//////////////////////////////////////////////////////////////////////////////:
				if(this.rest == 1) { /////WHEN U HAVE AN IMPAIR MAXSTATION TO PLACE THE LAST ONE IS PLACED
					Point2D.Double p = new Point2D.Double(0,0);
					while(this.pontExist(p)) {
					p.x = p.x - (widthKM/10.0);
					}
					this.graphPoints.add(p);
				}
				
				
				if(this.debt == 1) {
					Point2D.Double p = new Point2D.Double(0,0);
					while(this.pontExist(p)) {
					p.x = p.x - (widthKM/10.0);
					}
					this.graphPoints.add(p);
					MaxStation = MaxStation + 1;

				}
						
							/////TOUS LES POINTS SONT PLACES
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
							
							
							
				PointOuTheGraph =this.HowManyNotInTheCircle();
							
							
				if(PointOuTheGraph == 2) {
								double y = this.heightKM*0.5;
								double x = 0.0;
								Point2D.Double P1 = new Point2D.Double(x,y);
								this.graphPoints.add(P1);
								this.graphPoints.add(xSymetric(P1));
								
							}
							else if(PointOuTheGraph == 1) {

								this.graphPoints.add(new Point2D.Double(0.0,0.0) );
							}
							else {
							
								
								if(PointOuTheGraph%2 == 0) {/////CAS PAIR A PLACER 
									System.out.println("1er if");
									double y = this.heightKM*0.80;
									double x = 0.0;
									double stepYPointOut = ((this.heightKM) * 2) / PointOuTheGraph;
									for(int k =0; k < PointOuTheGraph; k++) {
										if(k == 0) {
											 y = this.heightKM*0.80;
											 x = 0.0;
										}
										else {
											x = 0.0;
											y = y - stepYPointOut;
										}
										Point2D.Double P1 = new Point2D.Double(x,y);
										this.graphPoints.add(P1);
						
									}
	
								}
								else if(PointOuTheGraph%2 == 1 && PointOuTheGraph >= 3 ) { ///// CAR IMPAIRE SUP OU EGAL A 3 A PLACER
									System.out.println("2eme if");
									double stepYPointOut =  (this.heightKM*2)/PointOuTheGraph;
									double x = 0.0;
									double y = this.heightKM*0.80;
									int PointOuTheGraphBis = PointOuTheGraph - 1;
									
									for(int k =0; k < PointOuTheGraphBis; k++) {
										if(k == 0) {
											 y = this.heightKM*0.80;
											 x = 0.0;
										}
										else {
											x = 0.0;
											y = y - stepYPointOut;
										}
										Point2D.Double P1 = new Point2D.Double(x,y);
										this.graphPoints.add(P1);
									}
	
								}
							}
							
							
							PointOuTheGraph = this.HowManyNotInTheCircle();
							this.clean();
							this.DrawPoints(gr2d);
							System.out.println("Not in the circle : " + PointOuTheGraph);
							System.out.println("In the circle : " + (this.graphPoints.size() -  PointOuTheGraph));
							System.out.println("Station to pack each line : " + StationsToPackEachLine);
							System.out.println("Number of line : " + TotalLineToPack);
							System.out.println("Lack : " + (MaxStation - graphPoints.size()));
							System.out.println("");
							System.out.println("size : " + this.graphPoints.size());
							System.out.println("Not in the circle : " + this.HowManyNotInTheCircle());
							System.out.println("In the circle : " + (this.graphPoints.size() - this.HowManyNotInTheCircle()));
							System.out.println("Station to pack each line : " + StationsToPackEachLine);
							System.out.println("Number of line : " + TotalLineToPack);
							System.out.println("Lack : " + (MaxStation - graphPoints.size()));
							System.out.println("MaxStation : " + MaxStation);
							
							if(this.clean() == this.HowManyNotInTheCircle()) {
								System.out.println("tout est ok");
							}
							else {
								System.out.println("refaire");
								System.out.println("clean : " + this.clean());
								System.out.println("out : " + this.HowManyNotInTheCircle() );
							}
			}
	
			///////////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////////
	
			
		/////Establish points position and add them to the list______ heart of the algorithm <3
			else if (this.widthKM != this.heightKM) {/////Ellipse case

			}
			
			

		}
	}

	
////////////////////////////////////////////////////////////////////////////

	

	/////Logical values into physical values
	public double xToPixel(double x) {
		return  ((this.widthKM*50) + (x*50));
	}
	
	
	public double yToPixel(double y) {
		return  ((this.heightKM*50) + (-y*50));
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
		this.repaint();
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
	
}
