/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package deprecated;

import java.io.Serializable;

import javax.swing.JOptionPane;

/**
 *NOT USED ANYMORE
 * @author john_shelby
 */
public class CommandProtocol implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public CommandProtocol () {}
    
    public String run() {
		/*System.out.println("What is your input? -->");
		Scanner keyboard = new Scanner(System.in);
		String out = keyboard.nextLine();
		keyboard.close();*/
    	String out = JOptionPane.showInputDialog("Message");
		return out;
    }

}
