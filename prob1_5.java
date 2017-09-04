/**************
Assignment 1 - Problem 1.5
This is an implementation of Dashed Line
Devanshu Sheth
dds160030

**************////////////


import java.awt.*;
import java.awt.event.*;

public class prob1_5 extends Frame
{  public static void main(String[] args)
   {
    prob1_5 prob1_5;
    prob1_5 = new prob1_5();
   }
   
   //constructor for prob1_5
   prob1_5()
   {  
    //display Dashed Line in title bar
   super("Dashed Line");
      
      //check for change in window size
      addWindowListener(new WindowAdapter()
      {
      //override method
      @Override
      public void windowClosing(WindowEvent e){System.exit(0);}});
      //set size of the drawing rectangle to 900*600
      setSize(900, 600);
      add("Center", new Lines());
      //show is decprecated, use setVisible(true) instead for painting the canvas
      setVisible(true);
   }
}
//Lines class required in program
class Lines extends Canvas
{  
   //define static variables for device coordinates of center and boundaries
   static int centerX, centerY, maxX, maxY;
   //used for logical coordinates  
   static float pixelSize, rWidth = 80.0F, rHeight = 80.0F, xP = 1000000, yP;

  //empty constructor
  Lines()
  {
      
  }
    //intialize method for graphics class
   void initgr()  
   {  
   //get dimension of current screen
      Dimension d = getSize();
      maxX = d.width - 1;
      maxY = d.height - 1;
      //calculate pixelsize
      pixelSize = Math.max(rWidth/maxX, rHeight/maxY);
      //get device coordinates for center
      centerX = maxX/2; centerY = maxY/2;
   }
   

   //methods for conversion between device and logical coordinates
   //given in textbook
   static int iX(float x){return Math.round(centerX + x/pixelSize);}
   static int iY(float y){return Math.round(centerY - y/pixelSize);}
   static float fx(int x){return (x - centerX) * pixelSize;}
   static float fy(int y){return (centerY - y) * pixelSize;}   
   
   
 //static method drawdashedLine
 public static void drawdashedLine(Graphics g, int xA, int yA, int xB, int yB, int dashedLength)
	{	
      //Length of the dashed line
		float L = (float) Math.hypot((xB-xA), (yB-yA));

		//Case 1: line is shorter than the dash length
                if(L <= dashedLength)
		{//draw line as is
			g.drawLine(xA, yA, xB, yB);
		}
		//line is at most equal to two dashes.
      //If this is the case we will not have two dashes at endpoints
		else if(L <= 2*dashedLength)
		{
      //draw line as is
			g.drawLine(xA, yA, xB, yB);
		}
		//line is greater than two dashes, means there is space for dashes
		else
		{
			//calculate the number of dashes using textbook formula
			int n = (int) Math.ceil(((L / (float) dashedLength) + 1) / 2);
			
         //calculate gap length as given in hints in textbook
			float gap = ((L - dashedLength*n) / (float) (n-1));
			
         //calculate x and y axis length for the gap
         float g1 = (xB-xA) / L * gap;
			float g2 = (yB-yA) / L * gap;
			
			//calculate x and y axis length for the dash
			float h1 = (xB-xA) / L * dashedLength;
			float h2 = (yB-yA) / L * dashedLength;
         
         //loop for dashes
			for(int i=0; i<n; i++)
			{
         
				float xA1 = xA + i*(h1+g1);
				float yA1 = yA + i*(h2+g2);
				float xB1 = xA + i*(h1+g1) + h1;
				float yB1 = yA + i*(h2+g2) + h2;
				//draw dashes from A1 to B1 with coordinates (xA1, yA1) and (xB1, yB1)
            g.drawLine((int)xA1, (int)yA1, (int)xB1, (int)yB1);
			}
			
		}
	}
   

   @Override
   public void paint(Graphics g)
   {  
   initgr();
    
   //logical coordinates for A,B,C,D,A1,B1,C1,D1
   //ABCD is outer full line rectangle and A1B1C1D1 is inner dashed rectangle 
   float xA, xB, xC, xD, yA, yB, yC, yD, xA1, yA1, xB1, yB1, xC1, yC1, xD1, yD1;
     
     //define coordinates for outer rectangle ( 40,20) , (-40,20), (40, -20), (-40, -20)     
     xA = xD = -40;
     xC = xB = 40;
     yA = yB = 20;
     yC = yD = -20;
     
     
     //random divide by 2 for x coordinate and by 2.5 for y coordinate
     xA1 = xA/2.0F;
     yA1 = yA/2.5F;
     
     xB1 = xB/2.0F;
     yB1 = yB/2.5F;
     
     xC1 = xC/2.0F;
     yC1 = yC/2.5F;
     
     xD1 = xD/2.0F;
     yD1 = yD/2.5F;
     
     //draw outer rectangle lines
      g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB));
      g.drawLine(iX(xB), iY(yB), iX(xC), iY(yC));
      g.drawLine(iX(xC), iY(yC), iX(xD), iY(yD));
      g.drawLine(iX(xD), iY(yD), iX(xA), iY(yA));
      
      //draw inner rectangle dashed lines
      drawdashedLine(g, iX(xA1), iY(yA1), iX(xB1), iY(yB1), 20);
      drawdashedLine(g, iX(xB1), iY(yB1), iX(xC1), iY(yC1), 20);
      drawdashedLine(g, iX(xC1), iY(yC1), iX(xD1), iY(yD1), 20);
      drawdashedLine(g, iX(xD1), iY(yD1), iX(xA1), iY(yA1), 20);
      
      //draw the lines joining two rectangles.
      drawdashedLine(g, iX(xA), iY(yA), iX(xA1), iY(yA1), 20);
      drawdashedLine(g, iX(xB), iY(yB), iX(xB1), iY(yB1), 20);
      drawdashedLine(g, iX(xC), iY(yC), iX(xC1), iY(yC1), 20);
      drawdashedLine(g, iX(xD), iY(yD), iX(xD1), iY(yD1), 20);

      
}         
}
