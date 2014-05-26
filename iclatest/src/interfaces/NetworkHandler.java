package interfaces;

import java.io.Serializable;

/**
 * Interface to describe a class that a non library class will hook into
 * 
 * @author Aditya Sampath
 * @version 5/17/14
 */
public interface NetworkHandler {
	/**
	 * Registers a NetworkGUI with the NetworkHandler so the class can send
	 * the gui data
	 * 
	 * @param n NetworkGUI object to register
	 */
	public void register(NetworkGUI n);
	
	/**
	 * Sends data over the network. Should be called in the non-library
	 * class using the NetworkHandler
	 * 
	 * @param s data (in serializable form) to send over the network
	 */
	public void send(Serializable s);
	
	/**
	 * Used to receive data from the network itself. Should NOT be called
	 * by non library classes
	 * 
	 * @param s data that is received (in serializable form)
	 * @param n id of client that is sending the data (server only)
	 */
	public void networkReceive(Serializable s, int n);
	
	/**
	 * Closes connection to network
	 */
	public void close ();
}
