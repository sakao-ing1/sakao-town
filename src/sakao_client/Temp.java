package sakao_client;

public class Temp {
	/////graphics.fill(new Oval(0,0, widthPX, heightPX));
	/*graphics.drawLine(0, heightPX/2, widthPX, heightPX/2);
	graphics.drawLine(widthPX/2, 0, widthPX/2, heightPX);
	*/
	
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
	
	
	///Point2D.Double
	/*Point2D.Double p1 = new Point2D.Double(2.5,3.0);
	Point2D.Double p2 = new Point2D.Double(1.0,3.5);
    /*Rectangle2D.Double rectangle1 = new  Rectangle2D.Double(xToPixel(p1.x), yToPixel(p1.y),5, 5);
    g2d.fill(rectangle1);
    Rectangle2D.Double rectangle2 = new  Rectangle2D.Double(xToPixel(p2.x), yToPixel(p2.y),5, 5);
    g2d.fill(rectangle2);
    g2d.draw(new Line2D.Double(this.xToPixel(p1.x),this.yToPixel(p1.y),this.xToPixel(p2.x),this.yToPixel(p2.y)));*/
	
	/*switch(MaxStation) {
	case 1 :
		Point2D.Double p = new Point2D.Double(0,0);
		this.graphPoints.add(p);
	}*/
	
	
	
	
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

	
    /*for (int i = 0; i < graphPoints.size(); i++) {
    double x = graphPoints.get(i).x;
    double y = graphPoints.get(i).y ;
    Rectangle2D.Double rectangle = new  Rectangle2D.Double(xToPixel(x), yToPixel(y),5, 5);
    gr2d.fill(rectangle);
}


System.out.println("rest : " + this.rest);
System.out.println("size : " + graphPoints.size());
*/
	
	
	
	
	
	
	
	
	
	/*while(MaxStation - PointInTheGraph >= 1) {
	
	Point2D.Double p = new Point2D.Double (xp,yp);
	
	if(p.x == 0.0 && p.y == 0.0) {
		xp = xp + (this.heightKM * 0.2);
		yp = yp -  (this.heightKM * 0.2);
		this.graphPoints.add(p);
		PointInTheGraph = PointInTheGraph + 1;
	}
	else {
		this.graphPoints.add(p);
		this.graphPoints.add((xSymetric(p)));

		PointInTheGraph = PointInTheGraph + 2;
	
		xp = xp + (this.heightKM * 0.2);
		yp = yp -  (this.heightKM * 0.2);
	}
	
	if(!this.isInTheCircle((double)xp,(double)yp)) {
			PointOut = this.MaxStation - this.graphPoints.size(); /////Number of points out of the circle
			
			if(PointOut%2 ==0) {
				for(int i = 1; i <= PointOut/2; i++) {/////Replace in the circle the  points which are not in the cirlce
					xp = ((-1^i) * this.widthKM * 0.80) ;
					yp = 0;
					this.graphPoints.add(new Point2D.Double(xp,yp));
				}
				for(int i = 1; i <= PointOut/2; i++) {/////Replace in the circle the  points which are not in the cirlce
					xp =0 ;
					yp = ((-1^i) * this.widthKM * 0.80);
					this.graphPoints.add(new Point2D.Double(xp,yp));
				}
			}
			else {
				int PointOutMid = PointOut - 1;
				for(int i = 1; i <= PointOutMid/2; i++) {/////Replace in the circle the  points which are not in the cirlce
					xp = ((-1^i) * this.widthKM * 0.80) ;
					yp = 0;
					this.graphPoints.add(new Point2D.Double(xp,yp));

				}
				for(int i = 1; i <= PointOut/2; i++) {/////Replace in the circle the  points which are not in the cirlce
					xp =0 ;
					yp = ((-1^i) * this.widthKM * 0.80);
					this.graphPoints.add(new Point2D.Double(xp,yp));

				}
				///this.graphPoints.add(new Point2D.Double(0.0,this.widthKM));

				
			}
	}
	
	///this.graphPoints.add(new Point2D.Double(this.widthKM,0.0));
	PointInTheGraph = PointInTheGraph + 1;
	
	
}*/
	
	
	
	
	
	
	
	
	/*for(int i = 0; i< TotalStationsToPack; i++) {
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
}*/


}
