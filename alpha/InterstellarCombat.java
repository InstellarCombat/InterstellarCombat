package alpha;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class InterstellarCombat extends JFrame {

	JPanel cardPanel;

	public InterstellarCombat(String title) {
		super(title);
		setBounds(100, 100, 800, 600);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    cardPanel = new JPanel();
	    CardLayout cl = new CardLayout();
	    cardPanel.setLayout(cl);

		MenuPanel panel1 = new MenuPanel(this);    
	    GamePanel panel2 = new GamePanel();

	    addKeyListener(panel2);

	    cardPanel.add(panel1,"1");
	    cardPanel.add(panel2,"2");

	    add(cardPanel);

	    setVisible(true);
	}

	public static void main(String[] args)
	{
		InterstellarCombat w = new InterstellarCombat("InterstellarCombat");
	}
  
	public void changePanel() {
		((CardLayout)cardPanel.getLayout()).next(cardPanel);
		requestFocus();
	}

}