package networking;

import interfaces.*;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

/**
 * Sets up server
 * 
 * @author aditya sampath
 */
public class SchoolServer implements NetworkHandler {
	private NetworkGUI win;
	private ArrayList<ClientReader> cr;
	private ArrayList<ClientWriter> cw;


	public SchoolServer () {
        System.out.println("I am the server!");
        win = null;
        cr = new ArrayList<ClientReader>();
        cw = new ArrayList<ClientWriter>();
	}
	
	public void run () throws IOException {
        ServerSocket serverSocket = null;
        int sPort = 4444;
        int num = 0;
        boolean listening = true;
        
        try {
            serverSocket = new ServerSocket(sPort);
            
            while (listening) {
                Socket s = serverSocket.accept();
                cw.add(new ClientWriter(s, this));
                cr.add(new ClientReader(s, num, this));
                num++;
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + sPort);
            System.exit(-1);
        }
        
        serverSocket.close();
    }

	public void register(NetworkGUI n) {
		win = n;
	}

	public void send(Serializable s) {
		if (cw.size() > 0) {
			for (ClientWriter c : cw) {
				c.send(s);
			}
		} else {
			System.out.println("Cannot send message (server).");
		}
	}

	public void networkReceive(Serializable s, int n) {
		if (win != null) {
			for (int i = 0; i < cw.size(); i++) {
				if (i != n)
					cw.get(i).send(s);
			}
			win.receive(s);
		} else {
			System.out.println("No gui to receive message (server)");
		}
	}
}
