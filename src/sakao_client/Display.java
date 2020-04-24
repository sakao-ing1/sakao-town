package sakao_client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JComponent;


public class Display extends JComponent {
	private static final long serialVersionUID = -1416435289803099003L;
	private int widthKM = 4;
	private int heightKM = 2;
	private int widthPX = widthKM * 100;
	private int heightPX = heightKM * 100;
	private ArrayList<Point> graphPoints;
	private int CityBudget = 81;
	private int aStationCost = 9;
	private int MaxStation = CityBudget/aStationCost;
	private int aLinkPrice = 15000;
	private int rest = 0;
	private int maxDiviser = 1;

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		graphPoints = new ArrayList<Point>(MaxStation);
		
		
		graphics.setColor(Color.BLACK);
		graphics.fillOval(0,0, widthPX, heightPX);
		
		
		graphics.setColor(Color.WHITE);
		graphics.drawLine(0, heightPX/2, widthPX, heightPX/2);
		graphics.drawLine(widthPX/2, 0, widthPX/2, heightPX);
		
		
		graphics.drawString("0",(int) (widthPX  * 0.51), (int) (heightPX * 0.57));
		graphics.drawString("-"+ widthKM,(int) (widthPX  * 0.03), (int) (heightPX * 0.57));
		graphics.drawString(""+widthKM,(int) (widthPX  * 0.95), (int) (heightPX * 0.57));
		graphics.drawString("-" +heightKM,(int) (widthPX  * 0.51), (int) (heightPX * 0.97));	
		graphics.drawString( heightKM +"",(int) (widthPX  * 0.51), (int) (heightPX * 0.07));	
		

		
		/*graphics.setColor(Color.RED);
		graphics.fillRect(xToPixel(-1.80),yToPixel(1.0), 5, 5);
		graphics.fillRect(xToPixel(-1.0),yToPixel(1.0), 5, 5);		
		graphics.drawLine(xToPixel(-2.0),yToPixel(1.0),xToPixel(-1.0),yToPixel(1.0));
		graphics.drawLine(xToPixel(-1.0),yToPixel(1.0),xToPixel(1.0),yToPixel(1.0));
		graphics.fillRect(xToPixel(1.0),yToPixel(1.0), 5, 5);
		graphics.fillRect(xToPixel(2.0),yToPixel(1.0), 5, 5);		
		graphics.drawLine(xToPixel(1.0),yToPixel(1.0),xToPixel(3.0),yToPixel(1.0));
		
		graphics.drawLine(xToPixel(2.0),yToPixel(1.0),xToPixel(-2.0),yToPixel(0.0));

		graphics.fillRect(xToPixel(-2.0),yToPixel(0.0), 5, 5);
		graphics.fillRect(xToPixel(-1.0),yToPixel(0.0), 5, 5);		
		graphics.drawLine(xToPixel(-2.0),yToPixel(0.0),xToPixel(-1.0),yToPixel(0.0));
		graphics.drawLine(xToPixel(-1.0),yToPixel(0.0),xToPixel(1.0),yToPixel(0.0));
		graphics.fillRect(xToPixel(1.0),yToPixel(0.0), 5, 5);
		graphics.fillRect(xToPixel(2.0),yToPixel(0.0), 5, 5);		
		graphics.drawLine(xToPixel(1.0),yToPixel(0.0),xToPixel(2.0),yToPixel(0.0));
		
		graphics.drawLine(xToPixel(2.0),yToPixel(0.0),xToPixel(-2.0),yToPixel(-1.0));
		graphics.fillRect(xToPixel(-2.0),yToPixel(-1.0), 5, 5);
		graphics.fillRect(xToPixel(-1.0),yToPixel(-1.0), 5, 5);		
		graphics.drawLine(xToPixel(-2.0),yToPixel(-1.0),xToPixel(-1.0),yToPixel(-1.0));
		graphics.drawLine(xToPixel(-1.0),yToPixel(-1.0),xToPixel(1.0),yToPixel(-1.0));
		graphics.fillRect(xToPixel(1.0),yToPixel(-1.0), 5, 5);
		graphics.fillRect(xToPixel(2.0),yToPixel(-1.0), 5, 5);		
		graphics.drawLine(xToPixel(1.0),yToPixel(-1.0),xToPixel(2.0),yToPixel(-1.0));
		*/
		
		graphics.setColor(Color.RED);
		graphics.fillRect(xToPixel(4.0),yToPixel(-3.0), 5, 5);		
		System.out.println(this.isInTheEllipse(4.0, -3.0));
		/////this.StationAlgo(graphics);
		
	}
	
////////////////////////////////////////////////////////////////////////////
	/* Test if the point is in the circle
	 * Here in the circle also means on the circle*/
	
	public boolean isInTheCircle(double x, double y) {
		boolean b;
		Point p = new Point(xToPixel(x),yToPixel(y));
		Point center = new Point(xToPixel(0.0),yToPixel(0.0));
		double distance = p.distance(center);
		System.out.println(distance);
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
	/* Gives the multiple of the maximum station number in order to know how to organize the stations on the maps */
	
	
	public boolean MaxDiviserWithoutRest() {
		boolean b = true;
		for(int i = 1; i <= MaxStation; i++) {
			if(MaxStation % i == 0 && maxDiviser <= i && maxDiviser < MaxStation) {
				maxDiviser = i;
			}
			else {
				setRest(maxDiviser % i);
				maxDiviser = maxDiviser/i;
				b = false;
			}
		}
		return b;
	}
	
	
////////////////////////////////////////////////////////////////////////////
	/* Main algorithm which places the points on the map*/
	
	public void StationAlgo(Graphics g) {
		
		
		///int step = MaxStation/maxDiviser;


		/////Establish points position and add them to the list heart of the algorithm
		
		if(this.widthKM == this.heightKM) {/////Circle case
			for(int i = 0; i < MaxStation; i++) {
				for(int j = 0; j < maxDiviser; j++) {
					int x = 0;
					int y = this.heightKM - i;
					this.graphPoints.add(new Point(x,y));
					for(int k = i; k < maxDiviser; k++) {
						int x1 = this.widthKM - i;
						int y1 = 0;
						this.graphPoints.add(new Point(x1,y1));
					}
				}
	
			}
			
	///// Draw points
	        for (int i = 0; i < graphPoints.size(); i++) {
	            int x = graphPoints.get(i).x;
	            int y = graphPoints.get(i).y ;
	            g.fillRect(x, y, 5,5);
	        }
	/////Draw Lines
	        
	        for (int i = 0; i < graphPoints.size() -1; i++) {
	            int x1 = graphPoints.get(i).x;
	            int y1 = graphPoints.get(i).y;
	            int x2 = graphPoints.get(i + 1).x;
	            int y2 = graphPoints.get(i + 1).y;
	            g.drawLine(x1, y1, x2, y2);
	        }
		}
		else if (this.widthKM != this.heightKM) {/////Ellipse case
			
		}
		
		
		else {
			
		}
	}
	
////////////////////////////////////////////////////////////////////////////

	

	/////Logical values into physical values
	public int xToPixel(Double x) {
		return (int) ((this.widthKM*50) + (x*50));
	}
	
	
	public int yToPixel(Double y) {
		return (int) ((this.heightKM*50) + (-y*50));
	}
	 

	public int getWidthPX() {
		return widthPX;
	}

	public void setWidthPX(int widthPX) {
		this.widthPX = widthPX;
	}

	public int getHeightPX() {
		return heightPX;
	}

	public void setHeightPX(int heightPX) {
		this.heightPX = heightPX;
	}


	public ArrayList<Point> getGraphPoints() {
		return graphPoints;
	}

	public void setGraphPoints(ArrayList<Point> graphPoints) {
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
