/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package networking;

/**
 *
 * @author aditya sampath
 */
import interfaces.*;

import java.io.*;
import java.net.*;

public class SchoolClient implements NetworkHandler {
	
	private NetworkGUI win;
	private ClientReader cr;
	private ClientWriter cw;

	
    public SchoolClient () {
        System.out.println("I am the client!");
        win = null;
        cr = null;
        cw = null;
    }
    
    public void run (Socket res) {
        cr = new ClientReader(res, -1, this);
        cw = new ClientWriter(res, this);
    }
    
    public Socket connect(String host) {
    	Socket connection = null;
    	int cPort = 4444;
    	
        try {
            connection = new Socket(host, cPort);
            connection.setKeepAlive(true);
        } catch (UnknownHostException e) {
        	System.out.println("UNKNOWNHOST");
        	e.printStackTrace();
            return null;
        } catch (IOException e) {
        	System.out.println("IO");
        	e.printStackTrace();
            return null;
        }
        return connection;
    }

	public void register(NetworkGUI n) {
		win = n;
	}

	@Override
	public void send(Serializable s) {
		if (cw != null)
			cw.send(s);
		else
			System.out.println("Cannot send message.");
	}

	@Override
	public void networkReceive(Serializable s, int n) {
		if (win != null)
			win.receive(s);
		else
			System.out.println("No gui to receive message");
	}

}