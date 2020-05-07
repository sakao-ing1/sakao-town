package sakao_client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D.Double;
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
	private int CityBudget = 1;///user
	private int aStationCost = 1;///user
	private int MaxStation = CityBudget/aStationCost;
	private int aLinkPrice = 15000;
	private int rest = 0;
	private int maxDiviser = 1;

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
		if(MaxStation == 1) {
			maxDiviser = 1;
			return maxDiviser;
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
		return maxDiviser;
	}
	
	
	public Point2D.Double xSymetric(Point2D.Double p) {
		
		double x = p.x;
		double y = -(p.y);
		
		Point2D.Double symX = new Point2D.Double(x,y);
		return symX;
	}
	
	
	public Point2D.Double ySymetric(Point2D.Double p) {
		
		double x = -(p.x);
		double y = p.y;
		
		Point2D.Double symY = new Point2D.Double(x,y);
		return symY;
	}
	
	public Point2D.Double OSymetric(Point2D.Double p) {
		
		double x = -(p.x);
		double y = -(p.y);
		
		Point2D.Double symO = new Point2D.Double(x,y);
		return symO;
	}
	
	public Point2D.Double xMidSymetric(Point2D.Double p) {
		double x =0;
		double y = p.y;
		
		Point2D.Double symmidx = new Point2D.Double(x,y);
		
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
	
	
	
	
////////////////////////////////////////////////////////////////////////////
	/* Main algorithm which places the points on the map*/
	
	public void StationAlgo(Graphics g) {
		
		
		int TotalStationsToPack = this.StationDiviser();
		int StationsToPackEachLine = MaxStation/TotalStationsToPack;
		Graphics2D gr2d = (Graphics2D) g;
		/////Establish points position and add them to the list______ heart of the algorithm <3
		if(this.maxDiviser == 1) {
			this.graphPoints.add(new Point2D.Double (0.0,0.0));
	        for (int i = 0; i < graphPoints.size(); i++) {
	            double x = graphPoints.get(i).x;
	            double y = graphPoints.get(i).y ;
	            Rectangle2D.Double rectangle = new  Rectangle2D.Double(xToPixel(x), yToPixel(y),5, 5);
	            gr2d.fill(rectangle);
	        }
	        System.out.println("rest : " + this.rest);
	        System.out.println("size : " + graphPoints.size());
		}
		else {
		
			if(this.widthKM == this.heightKM) {/////Circle case
				///int step = (TotalStationsToPack - StationsToPackEachLine);
				double xp = (-widthPX);
				double yp = heightPX;
				int compteur = 0;
				
				/*switch(MaxStation) {
				case 1 :
					Point2D.Double p = new Point2D.Double(0,0);
					this.graphPoints.add(p);
				}*/
				
				for(int i = 0; i< TotalStationsToPack; i++) {
					for(int j = 0; j < StationsToPackEachLine;j++) {
						while(!this.isInTheCircle((double)xp,(double)yp)) {
							xp = xp + this.widthKM/10.0;
							yp = yp  - this.widthKM/10.0;
	
	
						}
						Point2D.Double p = new Point2D.Double(xp,yp);
						this.graphPoints.add(p);
						xp = xp + 0.5;
						yp = yp - 0.5;
														/////this.+ operation InsertX(x), InsertY(y) to insert points position in table Station
						}
				}
				if(this.rest == 1) {
					Point2D.Double p = new Point2D.Double(0,0);
					while(this.pontExist(p)) {
						p.x = p.x - (widthKM/10.0);
						
						System.out.println(p);
					}
					this.graphPoints.add(p);

					
				}
					
					/*for(int k = 0; k<StationsToPackEachLine; k++) {
						yp = yp  - 1;
						Point p = new Point(xp,yp);
						this.graphPoints.add(p);
						while(!this.isInTheCircle((double)xp,(double)yp)) {
							xp = xp + 1;
							yp = yp  - 1;
							break;
						}
					}*/
	
					/*this.graphPoints.add(new Point(xSymetric(p)));
					this.graphPoints.add(new Point(ySymetric(p)));
					this.graphPoints.add(new Point(OSymetric(p)));
					this.graphPoints.add(new Point(xMidSymetric(p)));
					this.graphPoints.add(new Point(yMidSymetric(p)));*/	
				
					
			
				
				
		///// Draw points
		        for (int i = 0; i < graphPoints.size(); i++) {
		            double x = graphPoints.get(i).x;
		            double y = graphPoints.get(i).y ;
		            Rectangle2D.Double rectangle = new  Rectangle2D.Double(xToPixel(x), yToPixel(y),5, 5);
		            gr2d.fill(rectangle);
		        }
		        
		        
		        System.out.println("rest : " + this.rest);
		        System.out.println("size : " + graphPoints.size());
	
		        
		        
		/////Draw Lines
		        /////g2d.draw(new Line2D.Double(0, y, getWidth(), y));
		        
		        /*for (int i = 0; i < graphPoints.size() ; i++) {
		        	for(int j = i+1; j<graphPoints.size(); j++) {
			            double x1 = graphPoints.get(i).x;
			            double y1 = graphPoints.get(i).y;
			            double x2 = graphPoints.get(j).x;
			            double y2 = graphPoints.get(j).y;
			            gr2d.draw(new Line2D.Double(xToPixel(x1), yToPixel(y1), xToPixel( x2), yToPixel( y2)));
		        	}
		        }*/
			
			}
			
			
			
			
			
			
			///////////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////////////
	
			
		/////Establish points position and add them to the list______ heart of the algorithm <3
			else if (this.widthKM != this.heightKM) {/////Ellipse case
				
				
				
				
				
				
				
				///// Draw points
		        for (int i = 0; i < graphPoints.size(); i++) {
		            double x = graphPoints.get(i).x;
		            double y = graphPoints.get(i).y ;
		            Rectangle2D.Double rectangle = new  Rectangle2D.Double(x, y,5, 5);
		            gr2d.fill(rectangle);
		        }
		/////Draw Lines
		        
		       /* for (int i = 0; i < graphPoints.size() -1; i++) {
		        	double x1 = graphPoints.get(i).x;
		        	double y1 = graphPoints.get(i).y;
		        	double x2 = graphPoints.get(i + 1).x;
		        	double y2 = graphPoints.get(i + 1).y;
		        	
		        	 gr2d.draw(new Line2D.Double(xToPixel(x1), yToPixel(y1), xToPixel( x2), yToPixel( y2)));	        }
				*/
			}
			
			
			else {
				
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
