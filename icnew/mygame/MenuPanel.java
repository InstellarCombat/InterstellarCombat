package mygame;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import util.GraphicsHelper;

/**
 * @(#)MenuPanel.java
 *
 * A menu
 *
 * @author Aditya Sampath
 * @version 5/22/14
 */

public class MenuPanel extends JPanel {
	
	private Main w;
	private Image background;
	private Rectangle rec;
	private JButton button;
	private static final long serialVersionUID = 1L;

    public MenuPanel(Main win) {
    	w = win;
    	
		try {
			File file = new File("Menu.jpg");
            System.out.println("Is the file there ? " + file.exists());
            background = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
        setLayout(null);
        setBackground(Color.WHITE);

        this.addMouseListener(new playListener(this));
    }

    public void paintComponent (Graphics g){
    	super.paintComponent(g);  // Call JPanel's paintComponent method to paint the background

    	//FontMetrics fm;
    	
    	//Background Image
    	g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    	
    	//Centering Text- getting font/string info
    	Font f = new Font("Trebuchet MS", Font.BOLD, 62);
    	g.setFont(f);
    	FontMetrics fm = g.getFontMetrics();
    	Rectangle2D rect = fm.getStringBounds("[Play]", g);
    	GlyphVector gv = f.createGlyphVector(fm.getFontRenderContext(), "[Play]");

    	//Math stuff for centering text
    	int strWidth = (int)rect.getWidth();
    	int strHeight = (int)gv.getVisualBounds().getHeight();
    	int width = getWidth();
    	int height = getHeight();

    	int strx = (width - strWidth)/2;
    	int stry = (int)((height * 5)/6 - (strHeight/2));
    	
    	if (rec == null) {
    		rec = new Rectangle(strx, stry - strHeight, strWidth, strHeight);
    	} else {
    		rec.setBounds(strx, stry - strHeight, strWidth, strHeight);
    	}

    	//Write text
    	g.setColor(Color.WHITE);
    	g.drawString("[Play]", strx, stry);
    	/*g.setFont(new Font("Trebuchet MS", Font.BOLD, 62));
    	fm = g.getFontMetrics();
        g.setColor(new Color(9,21,61));
        g.drawString("Interstellar", 175, 250);
        g.setColor(new Color(218, 165, 32));
        g.drawString("Combat", fm.stringWidth("Interstellar") + 175, 250);*/
    }
    
    class playListener implements MouseListener {
    	private MenuPanel win;
    	
    	public playListener(MenuPanel w) {
    		win = w;
    	}
    	
		public void mouseClicked(MouseEvent e) {
			int xC = e.getX();
			int yC = e.getY();
			int b = e.getButton();
			
			if (rec == null) return;
									
			if(b == MouseEvent.BUTTON1 && rec.contains(xC, yC)) {
				w.changePanel("play");
			}
		}

		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}

		public void mousePressed(MouseEvent e) {}

		public void mouseReleased(MouseEvent e) {}
    	
    }
}