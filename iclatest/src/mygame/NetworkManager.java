package mygame;

import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import networking.SchoolClient;
import networking.SchoolServer;
import info.GameInfo;
import interfaces.*;

public class NetworkManager {
	private NetworkGUI gui;
	private SchoolServer server;
	private SchoolClient client;
	
	public void setGUI (NetworkGUI game) {
		gui = game;
	}
	
	public boolean connect (GameInfo info) {
		if (server != null) {
			server.register(gui);
			
			try {
				server.run();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Cannot create server");
				return false;
			}
		} else {
            String host = info.getServerIP();
            Socket res = client.connect(host);

            if (res == null) {
                JOptionPane.showMessageDialog(null, "Could not connect to " + host + "!");
                return false;
            } else {
            	client.register(gui);
            	client.run(res);
            }            
		}
        return true;
	}

	public NetworkHandler createHandler(GameInfo info) {
		if (info.isServer()) {
			server = new SchoolServer();
			return server;
		} else {
			client = new SchoolClient();
			return client;
		}
	}
}
