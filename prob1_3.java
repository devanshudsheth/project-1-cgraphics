/**************
Assignment 1 - Problem 1.3
This is an implementation of concentric squares

Devanshu Sheth
dds160030

**************////////////


import java.awt.*;
import java.awt.event.*;


public class prob1_3 extends Frame
        
{ 
    //declare static object for class
    private static prob1_3 prob1_3;
    
    public static void main(String[] args)
    {
    prob1_3 = new prob1_3();
    
    }
   //constructor for prob1_3
   prob1_3()
   {  
   //display Concentric Squares in title bar
   super("Concentric Squares");

   //check for change in window size
   addWindowListener(new WindowAdapter()
         {
         //override method
         @Override
         public void windowClosing(WindowEvent e){System.exit(0);}});
   
   //set size of the drawing rectangle to 600*600
    setSize(600, 600);
    //add canvas 
    Component add = add("Center", new CVprob1_3());
    //show is decprecated, use setVisible(true) instead for painting the canvas
    setVisible(true);
 
   }
}

class CVprob1_3 extends Canvas
{  
   //define the variables needed
   //device coordinates for center and the boundaries
   int centerX, centerY;
   
   //used for logical coordinates
   float pixelSize, rWidth = 10.0F, rHeight = 10.0F, xP = 1000000, yP;

    //intialize method for graphics class
   void initgr()  
   {  
   //get dimension of current screen
   Dimension d = getSize();
      int maxX = d.width - 1, maxY = d.height - 1;
      
      //calculate pixelsize
      pixelSize = Math.max(rWidth/maxX, rHeight/maxY);
      
      //get device coordinates for center
      centerX = maxX/2; centerY = maxY/2;
   }

   //methods for conversion between device and logical coordinates
   //given in textbook
   int iX(float x){return Math.round(centerX + x/pixelSize);}
   int iY(float y){return Math.round(centerY - y/pixelSize);}
   float fx(int x){return (x - centerX) * pixelSize;}
   float fy(int y){return (centerY - y) * pixelSize;}   

   //override the paint method
   @Override
   public void paint(Graphics g)
   {  
   
   initgr();
     
    //set the logical coordintes drawing rectangle
    float left = (-rWidth/2), right = (rWidth/2),
          bottom = (-rHeight/2), top = (rHeight/2);
    
    //set the logical coordinates for the center      
    float xMiddle = (0), yMiddle = (0);
   
    //variables for the squares
    //represent the squares ABCD and A1B1C1D1 (alternate oncentric squares)    
    float xA, yA, xB, yB, xC, yC, xD, yD, xA1, yA1, xB1, yB1, xC1, yC1, xD1, yD1;
     
     
     //logical coordinates for the first square
     xA = xMiddle;
     yA = top;
     xB = left;
     yB = yMiddle;
     xC = xMiddle;
     yC = bottom;
     xD = right;
     yD = yMiddle;
     
     //loop(50 times) and make more squares
     for(int i = 0 ; i <50; i++)
     {
      g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB));
      g.drawLine(iX(xB),iY(yB), iX(xC), iY(yC));
      g.drawLine(iX(xC), iY(yC), iX(xD), iY(yD));
      g.drawLine(iX(xD), iY(yD), iX(xA), iY(yA));
      
      //the new coordinates of the next square.
      //It will be the center of the corners of the current square
      
      xA1 = (xA+xB)/2;
      yA1 = (yA+yB)/2;
      
      xB1 = (xB+xC)/2;
      yB1 = (yB+yC)/2;
      
      xC1 = (xC+xD)/2;
      yC1 = (yC+yD)/2;
      
      xD1 = (xD+xA)/2;
      yD1 = (yD+yA)/2;
      
      xA = xA1; xB = xB1; xC = xC1; xD = xD1;
      yA = yA1; yB = yB1; yC = yC1; yD = yD1;

      
}         }
}
