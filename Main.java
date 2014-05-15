import gui.*;

import java.io.IOException;
import java.net.*;

import javax.swing.JOptionPane;

import networking.*;


/**
 * Main method for the whole rigamarole
 * 
 * @author john_shelby
 */
public class Main {

    public static void main(String[] args) throws UnknownHostException {
        int response = JOptionPane.showOptionDialog(null, "Are you the server or the client?", "Settings", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"Server","Client"}, null);
        InetAddress thisIp = InetAddress.getLocalHost();
        String myIP = thisIp.getHostAddress();

        if (response == JOptionPane.YES_OPTION) {
            SchoolServer schoolS = new SchoolServer();
            
            JOptionPane.showMessageDialog(null, "Your IP address is " + myIP);
            
            ChatWindow gui = new ChatWindow(schoolS, myIP, true);
            
            schoolS.register(gui);
            gui.makeVisibiletoWorld();

            try {
				schoolS.run();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
            
        } else if (response == JOptionPane.NO_OPTION) {
            SchoolClient sc = new SchoolClient();
            String host = JOptionPane.showInputDialog(null, "Your IP address is " + myIP + ". What host would you like to connect to?", "Host", JOptionPane.QUESTION_MESSAGE);
            //String host = myIP;
            Socket res = sc.connect(host);

            if (res == null) {
                JOptionPane.showMessageDialog(null, "Could not connect to " + host + "!");
                return;
            } else {
                JOptionPane.showMessageDialog(null, "Connected to " + host + "!");
                
                ChatWindow gui = new ChatWindow(sc, myIP, false);
                
                sc.register(gui);
                gui.makeVisibiletoWorld();

                sc.run(res);
            }
            
            System.out.println(res);
        } else {
        	System.out.println("quitting");
        	return;
        }
    }

}
