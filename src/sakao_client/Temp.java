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
/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * CEST POUR LA REPATIION XSTEP ETC A FAIRE POUR A PARTIR DE 20 A 22
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
				 
 /*
  * 		for (int i = 1; i <= TotalLineToPack; i++) {
			for (int j = 1; j <= StationsToPackEachLine - 1; j++) {
				///// QD ON PLACE DEUX PAR LIGNE A FAIRE CELUI DE TROIS PAR LIGNE EXEMPLE POUR 9

				if (this.MaxStation >= 0) {
					if (i % 2 == 1) {
						if (comportO % 2 == 1) {
							xp = stepX2;
							yp = yp - stepY;
							comportO = comportO + 1;
						} else {
							stepX1 = -(0.30 * widthKM);

							xp = stepX1;
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

					else if ((i % 2 == 0) && (this.isInTheCircle(stepX2, yp))) {
						xp = stepX3;
						yp = yp - stepY;
					} else {
						xp = 0.0;
						yp = yp - stepY;

					}
				}

  */

}
