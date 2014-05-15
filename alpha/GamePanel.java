package alpha;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

import javax.swing.*;

/**
   TODO Write a one-sentence summary of your class here.
   TODO Follow it with additional details about its purpose, what abstraction
   it represents, and how to use it.

   @author  TODO Your Name
   @version TODO Date

   Period - TODO Your Period
   Assignment - TODO Name of Assignment

   Sources - TODO list collaborators
 */
public class GamePanel extends JPanel implements KeyListener, Runnable
{
  // TODO Your Instance Variables Here
	
  public static final int DRAWING_WIDTH = 800;
  public static final int DRAWING_HEIGHT = 600;
	 
  private PlayerSpaceship ship;
  private boolean forward;

  public GamePanel() {
	  super();
	  setBackground(Color.DARK_GRAY);
	  ship = new PlayerSpaceship(100,100,100);
	  new Thread(this).start();
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);  // Call JPanel's paintComponent method
                              //   to paint the background
    Graphics2D g2 = (Graphics2D)g;
    
    int width = getWidth();
    int height = getHeight();
    
    double ratioX = (double)width/DRAWING_WIDTH;
    double ratioY = (double)height/DRAWING_HEIGHT;
    
    AffineTransform at = g2.getTransform();
    g2.scale(ratioX, ratioY);
    
    ship.draw(g2,this);
    g2.setTransform(at);
  }

@Override
public void keyPressed(KeyEvent arg0) {
  	if (arg0.getKeyCode() == KeyEvent.VK_UP) {
		forward = true;
  	}
}

@Override
public void keyReleased(KeyEvent arg0) {
	if (arg0.getKeyCode() == KeyEvent.VK_UP) {
		forward = false;
  	}
}

@Override
public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub

}

@Override
public void run() {
	// TODO Auto-generated method stub
	while (true) {
		if (forward) {
			ship.moveForward(1);
		}
		ship.act();
		repaint();
	}
}
}