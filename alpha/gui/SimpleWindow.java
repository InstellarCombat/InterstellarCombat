package gui;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Based off of rotation demo. Is the window that the game takes place in
 * @author Mr Shelby
 * @version 5/15/2014
 */
public class SimpleWindow extends JPanel implements Runnable {
  private Spaceship guy;
  private Thread runThread;
  private static final long serialVersionUID = 1L;

  	/**
  	 * Constructor. Creates a SimpleWindow
  	 * @param w JFrame that needs to be passed in so a keylistener can be added to it
  	 */
	public SimpleWindow (JFrame w) {
		super();
		guy = new Spaceship();
		addMouseMotionListener(new MMHandler());
		w.addKeyListener(new KeyBoardHandler());
		runThread = new Thread(this);
		runThread.start();
	}
	
	/**
	 * Draws the spaceship on the screen
	 * @post spaceship may have new coordinates and angle of rotation
	 */
	public void paintComponent(Graphics g) {
	  super.paintComponent(g);  
	  guy.applyWindowLimits(getWidth(), getHeight());
	  guy.draw(g, this);
	}
	
	/**
	 * Animation loop that controls the program and movement of the spaceship
	 * @post spaceship may have new coordinates
	 */
	public void run() {
		while(true) {
			long startTime = System.currentTimeMillis(); 
			
			guy.act();
			
			repaint();
			
			long endTime = System.currentTimeMillis();
			
			try {
				Thread.sleep(Math.max(0, 20-(endTime-startTime)));
			} catch(InterruptedException ex) {}
		}
	}
	
	/**
	 * Main method. Creates a SimpleWindow instance to start the game
	 */
	public static void main(String[] args) {
		JFrame w = new JFrame("Simple Window");
		SimpleWindow panel = new SimpleWindow(w);
		Container c = w.getContentPane();

		panel.setBackground(Color.WHITE);
		c.add(panel);
		
		w.setResizable(true);
		w.setVisible(true);
		w.setBounds(100, 100, 640, 480);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/** START MINI CLASSES **/
	class MMHandler implements MouseMotionListener {
		public void mouseDragged(MouseEvent arg0) { }
		
		public void mouseMoved(MouseEvent arg0) {
			guy.turnToward(arg0.getX(), arg0.getY());
		}
	}
	
	class KeyBoardHandler implements KeyListener {

		public void keyPressed(KeyEvent arg0) {
			int code = arg0.getKeyCode();
			
			if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				guy.moveForward(true);
			}
		}
		
		public void keyReleased(KeyEvent arg0) {
			int code = arg0.getKeyCode();
			
			if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				guy.moveForward(false);
			}		
		}
		
		public void keyTyped(KeyEvent arg0) { }
	}
}