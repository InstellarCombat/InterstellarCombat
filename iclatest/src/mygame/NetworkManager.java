package mygame;

import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import networking.SchoolClient;
import networking.SchoolServer;
import interfaces.*;

public class NetworkManager {
	private NetworkGUI gui;
	
	public void setGUI (NetworkGUI game) {
		gui = game;
	}
	
	public boolean connect (GameInfo info) {
		if (info.isServer()) {
			SchoolServer iServer = new SchoolServer();
			iServer.register(gui);
			
			try {
				iServer.run();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Cannot create server");
				return false;
			}
		} else {
            SchoolClient sc = new SchoolClient();
            String host = info.getServerIP();
            Socket res = sc.connect(host);

            if (res == null) {
                JOptionPane.showMessageDialog(null, "Could not connect to " + host + "!");
                return false;
            } else {
                sc.register(gui);
                sc.run(res);
            }            
		}
        return true;
	}
}
