package networking;

import interfaces.*;
import java.net.*;
import java.util.*;
import java.io.*;

/**
 * Sets up server which hosts a connection with itself and 1+
 * SchoolClients on port 4444
 * 
 * @author Aditya Sampath
 */
public class SchoolServer implements NetworkHandler {
	private NetworkGUI win;
	private ArrayList<ClientReader> cr;
	private ArrayList<ClientWriter> cw;


	/**
	 * No args constructor, creates schoolserver object
	 */
	public SchoolServer () {
        System.out.println("I am the server!");
        win = null;
        cr = new ArrayList<ClientReader>();
        cw = new ArrayList<ClientWriter>();
	}
	
	/**
	 * Runs the SchoolServer. Initializes connection on port 4444.
	 * Exits the program if
	 * 
	 * @throws IOException if it can't create a connection on port 4444
	 * or if it can't close the serversocket
	 */
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
            throw e;
        }
        
        try {
        	if (serverSocket != null) serverSocket.close();
        } catch (IOException ex) {
        	throw ex;
        }
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
	
	/**
	 * Closes the server's network connection and the connection from all the clients to the server
	 */
	public void close() {
		send(new Close());
		
		for (ClientWriter c : cw) c.close();	
		for (ClientReader c : cr) c.close();
	}
}
