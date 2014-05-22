import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @(#)MenuPanel.java
 *
 * A menu
 *
 * @author Aditya Sampath
 * @version 5/22/14
 */

public class MenuPanel extends JPanel implements ActionListener {
	
	private InterstellarCombat w;
	private static final long serialVersionUID = 1L;


    public MenuPanel(InterstellarCombat win) {
    	w = win;
    	
        setLayout(null);
        setBackground(Color.WHITE);

        JButton button1 = new JButton("Play");
        JButton button2 = new JButton("Exit");

        button1.setFont(new Font("Trebuchet MS", Font.BOLD, 54));
        button2.setFont(new Font("Trebuchet MS", Font.BOLD, 54));

        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.BLACK);


        button1.setFocusPainted(false);
        button2.setFocusPainted(false);


        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.BLACK);

        Insets insets = getInsets();
        Dimension size = button1.getPreferredSize();
        button1.setBounds(370 + insets.left, 340 + insets.top,
             size.width, size.height);
        button2.setBounds(370 + insets.left, 520 + insets.top,
             size.width, size.height);

        button1.addActionListener(this);
        button2.addActionListener(this);

        add(button1);
        add(button2);
    }

    public void paintComponent (Graphics g){
    	super.paintComponent(g);  // Call JPanel's paintComponent method to paint the background

    	FontMetrics fm;
    	g.setFont(new Font("Trebuchet MS", Font.BOLD, 172));
    	fm = g.getFontMetrics();
        g.setColor(new Color(9,21,61));
        g.drawString("Interstellar", 175, 250);
        g.setColor(new Color(218, 165, 32));
        g.drawString("Combat", fm.stringWidth("Interstellar") + 175, 250);

    }
    
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Play")){
            w.changePanel("play");
        } else if (action.equals("Exit")) {
            System.exit(0);
        }

    }

}