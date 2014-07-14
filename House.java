/* Class:   imageviewer
 * Name:		Rick Larsen
 * Date:		11/25/2013 
 * Purpose:	Enables users to select image files to view.
 */
 
 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 
 public class House extends JApplet
 {
   private boolean leftWindowClosed = true;  // state of left window
   private boolean rightWindowClosed = true; // state of right window
   private boolean doorClosed = true;        // state of door
   
   // constants for drawing house, roof, windows, and door.
   private final int HOUSE_WIDTH = 200;
   private final int HOUSE_HEIGHT = 80;
   private final int HOUSE_X = 20;
   private final int HOUSE_Y = 30;
   private final int ROOF_TOP = 0;
   private final int WINDOW_WIDTH = 50;
   private final int WINDOW_HEIGHT = 30;
   private final int DOOR_WIDTH = 40;
   private final int DOOR_HEIGHT = 70;
   private final int DOOR_Y = 40;
   private final int DOOR_X = (((HOUSE_WIDTH/2)+HOUSE_X)-(DOOR_WIDTH/2));
   private final int LEFT_WINDOW_X = ((HOUSE_WIDTH/4)+HOUSE_X) - (WINDOW_WIDTH/2);
   private final int LEFT_WINDOW_Y = 40;
   private final int RIGHT_WINDOW_X = (((HOUSE_WIDTH/4)*3)+HOUSE_X)-(WINDOW_WIDTH/2);
   private final int RIGHT_WINDOW_Y = LEFT_WINDOW_Y;
   private final int KNOB_WIDTH = 10;
   private final int KNOB_HEIGHT = 10;
   
   //init method
   public void init()
   {
      // set background color to white
      getContentPane().setBackground(Color.white);
      
      // add mouse listener
      addMouseListener(new MyMouseListener());
   }
   
   public void paint(Graphics g)
   {
      // call base class paint method
      super.paint(g);
      
      // draw the outline of the house and roof
      drawHouse(g);
      
      // draw the correct windows and door
      if (leftWindowClosed)
         drawClosedWindow(g, LEFT_WINDOW_X, LEFT_WINDOW_Y);
      else
         drawOpenWindow(g, LEFT_WINDOW_X, LEFT_WINDOW_Y);
      
      if (rightWindowClosed)
         drawClosedWindow(g, RIGHT_WINDOW_X, RIGHT_WINDOW_Y);
      else
         drawOpenWindow(g, RIGHT_WINDOW_X, RIGHT_WINDOW_Y);
         
      if (doorClosed)
         drawClosedDoor(g, DOOR_X, DOOR_Y);
      else
         drawOpenDoor(g, DOOR_X, DOOR_Y);         
   }
   
   private void drawHouse(Graphics g)
   {
      // draw unfilled rectangle for base of house
      g.setColor(Color.black);
      g.drawRect(HOUSE_X, HOUSE_Y, HOUSE_WIDTH, HOUSE_HEIGHT);
      
      // draw triangle for roof
      g.drawLine((HOUSE_X-10), HOUSE_Y, (HOUSE_WIDTH+30), HOUSE_Y);
      g.drawLine((HOUSE_X-10), HOUSE_Y, ((HOUSE_WIDTH/2)+HOUSE_X), ROOF_TOP);
      g.drawLine(((HOUSE_WIDTH/2)+HOUSE_X), ROOF_TOP, (HOUSE_WIDTH+30), HOUSE_Y);
   }
   
   private void drawOpenWindow(Graphics g, int x, int y)
   {
      // set color to black & draw filled rectangle
      g.setColor(Color.black);
      g.fillRect(x, y, WINDOW_WIDTH, WINDOW_HEIGHT);      
   }
   
   private void drawClosedWindow(Graphics g, int x, int y)
   {
      // set color and draw open rectangle
      g.setColor(Color.black);
      g.drawRect(x, y, WINDOW_WIDTH, WINDOW_HEIGHT);
      // draw lines for window panes
      g.drawLine(x, (y + (WINDOW_HEIGHT/2)), (x+WINDOW_WIDTH), (y+WINDOW_HEIGHT/2));
      g.drawLine((x+(WINDOW_WIDTH/2)), (y+WINDOW_HEIGHT), (x+(WINDOW_WIDTH/2)), y); 
   }
   
   private void drawOpenDoor(Graphics g, int x, int y)
   {
      // set color and draw filled door
      g.setColor(Color.black);
      g.fillRect(x, y, DOOR_WIDTH, DOOR_HEIGHT);
   }
   
   private void drawClosedDoor(Graphics g, int x, int y)
   {
      // set color and draw open door
      g.setColor(Color.black);
      g.drawRect(x, y, DOOR_WIDTH, DOOR_HEIGHT);
      // draw doorknob
      g.fillOval(((x+DOOR_WIDTH)-KNOB_WIDTH), (y+(DOOR_HEIGHT/2)), KNOB_WIDTH, KNOB_HEIGHT);
   }
   
   // MyMouseListener private inner class
   private class MyMouseListener extends MouseAdapter
   {
      // mouseClicked method 
      public void mouseClicked(MouseEvent e)
      {
         // determine if mouse clicked within a window or the door
         if (isInLeftWindow(e.getX(), e.getY()))
            leftWindowClosed = !leftWindowClosed;
         else if (isInRightWindow(e.getX(), e.getY()))
            rightWindowClosed = !rightWindowClosed;
         else if (isInDoor(e.getX(), e.getY()))
            doorClosed = !doorClosed;
            
         // force a repaint
         repaint();
      }
      
      private boolean isInLeftWindow(int x, int y)
      {
         // determine if click event is within left window
         boolean status = false;
         if (x >= LEFT_WINDOW_X && x <= (LEFT_WINDOW_X + WINDOW_WIDTH) &&
             y >= LEFT_WINDOW_Y && y <= (LEFT_WINDOW_Y + WINDOW_HEIGHT))
             status = true;
         return status;
      }
      
      private boolean isInRightWindow(int x, int y)
      {
         // determine if click event is within right window
         boolean status = false;
         if (x >= RIGHT_WINDOW_X && x <= (RIGHT_WINDOW_X + WINDOW_WIDTH) &&
             y >= RIGHT_WINDOW_Y && y <= (RIGHT_WINDOW_Y + WINDOW_HEIGHT))
             status = true;
         return status;
      }
      
      private boolean isInDoor(int x, int y)
      {
         // determine if click event is within door
         boolean status = false;
         if ((x >= DOOR_X) && (x <= (DOOR_X + DOOR_WIDTH)) &&
             (y >= DOOR_Y) && (y <= (DOOR_Y + DOOR_HEIGHT)))
              status = true;
         return status;
      }
   }// end of inner class
 }
 
 