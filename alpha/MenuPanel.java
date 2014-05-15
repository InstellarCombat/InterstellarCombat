package alpha;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class MenuPanel extends JPanel implements ActionListener {

	InterstellarCombat w;

	public MenuPanel(InterstellarCombat w) {
		this.w = w;
		JButton button = new JButton("Press me!");
		button.addActionListener(this);
		add(button);
		setBackground(Color.GRAY);
	}

	public void actionPerformed(ActionEvent e) {
		w.changePanel();
	}
}