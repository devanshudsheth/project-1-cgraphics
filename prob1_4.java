/**************
Assignment 1 - Problem 1.4
This is an implementation of Hexagon Grid. 
Also displays the logical coordinates and radius for user's knowledge.

Devanshu Sheth
dds160030

**************////////////

import java.awt.*;
import java.awt.event.*;

public class prob1_4 extends Frame
{ 
public static void main(String[] args)
   {
   
    prob1_4 prob1_4;
    prob1_4 = new prob1_4();
   }
   
   //constructor for prob1_4
   prob1_4()
   {  
   //display hexagon grid in title bar
   super("Hexagon Grid");
      
      //check for change in window size
      addWindowListener(new WindowAdapter()
      {
      //override method
      @Override
      public void windowClosing(WindowEvent e){System.exit(0);}});
      
      //set size of the drawing rectangle to 600*600
      setSize(600, 600);
      //add canvas   
      add("Center", new Cvprob1_4());
      //display the cross haired cursor
      setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
      //show is decprecated, use setVisible(true) instead for painting the canvas
      setVisible(true);
   }
}

class Cvprob1_4 extends Canvas
{  
   //define the variables needed
   //device coordinates for center and the boundaries
   int centerX, centerY, maxX, maxY;
   
   //used for logical coordinates
   float pixelSize, rWidth = 80.0F, rHeight = 80.0F, xP = 1000000, yP;
   
   //radius required for hexagons
   float r;
   
  //constructor for the canvas class
  Cvprob1_4()
  {
      //check for mouse click
     addMouseListener(new MouseAdapter()
     {
         @Override
         public void mousePressed(MouseEvent evt)
         {
             //get logical coordinates for the point at which mouse was clicked
             xP = fx(evt.getX()); 
             yP = fy(evt.getY());
             
       //hypot function can be used for distance
       //logical coordinates of the left corner and the point at which mouse was clicked     
       r = (float) Math.hypot( -rWidth/2 - xP, rHeight/2 - yP); 
             
       //repaint on click      
       repaint();
         }
     });
      
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
   int iX(float x)
   {
   return Math.round(centerX + x/pixelSize);
   }
   
   int iY(float y)
   {
   return Math.round(centerY - y/pixelSize);
   }
   
   float fx(int x)
   {
       return (x - centerX ) * pixelSize;
   }
   
   float fy(int y)
   {
       return (centerY - y) * pixelSize;
   }
   
   //method to draw line between point A with coordinates (xA, yA) and B (xB, yB)
   void drawLine(Graphics g, float xA,float yA, float xB, float yB)
   {
   g.drawLine(iX(xA),iY(yA),iX(xB),iY(yB));
   }

   //override the paint method
   @Override
   public void paint(Graphics g)
   {  
   initgr();
   
   //calculate horizontal pitch, half radius
   //half radius is required for the hexagon drawing
   //horpitch is the distance moved for next center.  
   float halfr = r/2, horpitch = 1.5F * r, 
   
   //w and h are used for height of the hexagon from the center
   w= r* (float)Math.sqrt(3), h = w/2, marginleft, marginbottom;
   
   //margins are defined
   //nhor and nvert will calculate the number of hexagons that can fit
   int nhor = (int)Math.floor((rWidth - 2 * r)/ horpitch) + 1,
       nvert = (int)Math.floor(rHeight/w);
       
   marginleft = -rWidth/2 + 0.5F * (rWidth - halfr - nhor * horpitch);
   marginbottom = -rHeight/2 + 0.5F * (rHeight - nvert * w);
   
   
   for(int i = 0; i < nhor; i++)
   {
   float x = marginleft + r + i * horpitch, 
   y0 = marginbottom + (1+i%2) * h;  //center of lowest hexagon
   
   int m = nvert - i % 2;
   //There will be nvert hexagons in each column for i = 0,2,4,...
   
   //while there will be nvert - 1 in each column for i = 1,3,5....
   
   //Special case: if nvert = 1 and nhor > 1, then x is increased by horpitch/2 because otherwise there will be an empty column on the right
   
   if(nvert == 1 && nhor > 1)
   {
      x+= horpitch/2;
   }
   
   for (int j = 0; j < m ; j++)
   {
   float y = y0 + j*w;
   
   //the 6 lines for the hexagons 
   drawLine(g, x + halfr, y+h, x - halfr, y+h);
   drawLine(g, x - halfr, y+h, x - r, y);
   drawLine(g, x - r, y,  x - halfr, y - h);
   drawLine(g, x - halfr, y-h, x+halfr, y-h);
   drawLine(g, x + halfr, y-h,  x + r, y );
   drawLine(g, x + r, y,  x + halfr, y + h);
   
   
   
   }
   
   } 
   //set XOR mode to make sure it alternates drawing the lines
  g.setXORMode(Color.white);
   
   //display logical coordinates of the point pressed and radius for user
   if(xP != 1000000)
   {
       g.drawString("Logical coordinates of selected point: " + xP + " " + yP + " radius: " +  r, 20, 100);
   }
       
   }
}
