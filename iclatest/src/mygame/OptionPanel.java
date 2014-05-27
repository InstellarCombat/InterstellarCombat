package mygame;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

import javax.imageio.*;
import javax.swing.*;

import com.jme3.app.SimpleApplication;

public class OptionPanel extends JPanel {
	private Main w;
	private Image background;
	private JTextField tB;
	private JButton client, server, big, small;
	private String myIP;
	private Rectangle rec;
	private boolean isAdded;
	private static final long serialVersionUID = 1L;

    public OptionPanel (Main win) {
    	w = win;
    	isAdded = false;
    	
		try {
			File file = new File("StarField.jpg");
            System.out.println("Is the file there ? " + file.exists());
            background = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
        setLayout(null);
        setBackground(Color.WHITE);
        
		try {
	        InetAddress thisIp = InetAddress.getLocalHost();
	        myIP = thisIp.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

        tB = new JTextField(39);
        tB.setEditable(false);
		tB.setEnabled(false);
        
        client = new JButton("Client");
        server = new JButton("Server");
        big = new JButton("Big Ship");
        small = new JButton("Small Ship");

        
        client.setFont(new Font("Agency FB", Font.BOLD, 54)); 
        server.setFont(new Font("Agency FB", Font.BOLD, 54));
        big.setFont(new Font("Agency FB", Font.BOLD, 54)); 
        small.setFont(new Font("Agency FB", Font.BOLD, 54)); 
  
        client.setForeground(Color.WHITE); 
        client.setBackground(Color.BLACK); 
  
        server.setForeground(Color.WHITE); 
        server.setBackground(Color.BLACK); 
        
        big.setForeground(Color.WHITE); 
        big.setBackground(Color.BLACK); 

        small.setForeground(Color.WHITE); 
        small.setBackground(Color.BLACK); 

  
        client.setFocusPainted(false); 
        server.setFocusPainted(false);
        big.setFocusPainted(false); 
        small.setFocusPainted(false); 

              
        this.addMouseListener(new optionListener());
        this.addMouseMotionListener(new optionMouseMotion(this));
    }
    
    public void paintComponent (Graphics g){
    	super.paintComponent(g);  // Call JPanel's paintComponent method to paint the background
    	
    	
    	if (!isAdded) {
            Dimension tSize = tB.getPreferredSize();
            tB.setBounds((getWidth() - tSize.width)/2 + 10, 150,
            	tSize.width, tSize.height);

            Insets insets = getInsets(); 
            Dimension size = client.getPreferredSize();
            Dimension size2 = server.getPreferredSize();
            Dimension sSize = small.getPreferredSize();
            Dimension bSize = big.getPreferredSize();
            client.setBounds(getWidth()/2 - size.width - 40 + insets.left,
            		40 + insets.top, size.width, size.height); 
            server.setBounds(getWidth()/2 + 40 + insets.left,
            		40 + insets.top, size2.width, size.height);
            small.setBounds(getWidth()/2 - sSize.width - 60 + insets.left,
            		240 + insets.top, sSize.width, size.height); 
            big.setBounds(getWidth()/2 + 80 + insets.left,
            		240 + insets.top, bSize.width, size.height); 

            small.setToolTipText("Faster but less powerful");
            big.setToolTipText("More Powerful but slower");

            add(client);
            add(server);
            add(big);
            add(small);
            add(tB);
            
            tB.setVisible(true);
            client.addActionListener(new optButtonListener()); 
            server.addActionListener(new optButtonListener());
            big.addActionListener(new optButtonListener()); 
            small.addActionListener(new optButtonListener());
            isAdded = true;
    	}
    	//Background Image
    	g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

    	//Centering Text- getting font/string info
    	Font f = new Font("Trebuchet MS", Font.BOLD, 62);
    	g.setFont(f);
    	FontMetrics fm = g.getFontMetrics();
    	Rectangle2D rect = fm.getStringBounds("[Start]", g);
    	GlyphVector gv = f.createGlyphVector(fm.getFontRenderContext(), "[Start]");

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
    	g.drawString("[Start]", strx, stry);
    }
    
    private void startApp() {
    	final InterstellarCombat app = (InterstellarCombat)w.getApp();
        app.startCanvas();
        app.enqueue(new Callable<Void>(){
            public Void call(){
                if (app instanceof SimpleApplication){
                    SimpleApplication simpleApp = (SimpleApplication) app;
                    simpleApp.getFlyByCamera().setDragToRotate(true);
                }
                return null;
            }
        });
    }
    
    class optionListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			int xMouse = e.getX();
			int yMouse = e.getY();
			int b = e.getButton();

			if (rec == null) return;

			if(b == MouseEvent.BUTTON1 && rec.contains(xMouse, yMouse)) {
				w.getInfo().setServer(!tB.isEnabled());
				
				if (tB.isEnabled()) w.getInfo().setServerIP(tB.getText());
				
				w.getNetManager().connect(w.getInfo());
				startApp();
				w.changePanel("play");
			}
		}

		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}

		public void mousePressed(MouseEvent e) {}

		public void mouseReleased(MouseEvent e) {}
    	
    }
    
    class optButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String text = e.getActionCommand();
			
			if (text.equals("Client")) {
				tB.setEnabled(true);
				tB.setEditable(true);
				tB.setBorder(null);
				tB.setText("Enter server IP here");
			} else if (text.equals("Server")) {
				tB.setEnabled(false);
				tB.setEditable(false);
				tB.setBackground(Color.WHITE);
				tB.setText(myIP);
			} else if (text.equals("Big Ship")) {
				w.getInfo().setShip(true);
			} else if (text.equals("Small Ship")) {
				w.getInfo().setShip(false);
			}
		}
    }
    
    class optionMouseMotion implements MouseMotionListener {
    	private OptionPanel oP;
    	
    	public optionMouseMotion (OptionPanel oPanel) {
    		oP = oPanel;
    	}
    	
		public void mouseDragged(MouseEvent e) {}

		public void mouseMoved(MouseEvent e) {
			int xMouse = e.getX();
			int yMouse = e.getY();
			
			if (rec.contains(xMouse, yMouse)) {
				oP.setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else {
				oP.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}
    	
    }
}
