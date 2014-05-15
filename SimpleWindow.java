import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 Rotation Demo
*/
public class SimpleWindow extends JPanel implements MouseMotionListener, KeyListener, Runnable {
  private Hero guy;
  private Thread runThread;
  private static final long serialVersionUID = 1L;

	public SimpleWindow () {
		super();
		guy = new Hero();
		addMouseMotionListener(this);
		runThread = new Thread(this);
		runThread.start();
	}
	
	public void paintComponent(Graphics g) {
	  super.paintComponent(g);  
	  guy.draw(g, this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			guy.moveForward(true);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			guy.moveForward(false);
		}
		
	}
	
	public void keyTyped(KeyEvent arg0) { }
	
	public void mouseDragged(MouseEvent arg0) { }
	
	public void mouseMoved(MouseEvent arg0) {
		guy.turnToward(arg0.getX(), arg0.getY());
	}
	
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
	

	public static void main(String[] args) {
		JFrame w = new JFrame("Simple Window");
		w.setBounds(100, 100, 640, 480);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SimpleWindow panel = new SimpleWindow();
		panel.setBackground(Color.WHITE);
		Container c = w.getContentPane();
		c.add(panel);
		w.setResizable(true);
		w.setVisible(true);
		w.addKeyListener(panel);
	}
}