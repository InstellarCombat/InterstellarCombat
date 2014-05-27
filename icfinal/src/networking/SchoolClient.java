/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package networking;

import interfaces.*;
import java.io.*;
import java.net.*;

/**
 * Representation of a client. Allows for a non library classes to connect
 * to a server on port 4444
 * 
 * @author aditya sampath
 */
public class SchoolClient implements NetworkHandler {
	
	private NetworkGUI win;
	private ClientReader cr;
	private ClientWriter cw;
	
	/**
	 * No args constructor. Creates a SchoolClient Object
	 */
    public SchoolClient () {
        System.out.println("I am the client!");
        win = null;
        cr = null;
        cw = null;
    }
    
    /**
     * Runs the schoolclient and allows for data to be sent
     * 
     * @pre connect() has already been called, and the socket being
     * passed in is the socket that was returned from connect()
     * @param s Socket that you are connecting to the server with
     */
    public void run (Socket s) {
        cr = new ClientReader(s, -1, this);
        cw = new ClientWriter(s, this);
    }
    
    /**
     * Connects to the server (whose IP is given in host) and returns a
     * socket if the connection was successful, otherwise returns null
     * and prints out a stack trace/name of the exception that was thrown
     * 
     * @param host IP String of server to connect to
     * @return a socket to use on run() or null if the connection was
     * unsucessful
     */
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

	public void send(Serializable s) {
		if (cw != null)
			cw.send(s);
		else
			System.out.println("Cannot send message.");
	}

	public void networkReceive(Serializable s, int n) {
		if (win != null) {
			if (s instanceof Close)
				close();
			else
				win.receive(s);
		} else {
			System.out.println("No gui to receive message");
		}
	}
	
	/**
	 * Closes client connection to server cleanly
	 */
	public void close () {
		cw.close();
		cr.close();
	}
}