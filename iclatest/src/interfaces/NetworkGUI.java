package interfaces;

import java.io.Serializable;

/**
 * Interface to describe a class that hooks into the network connection classes
 * This is pretty much what a class that uses this library is
 * 
 * @author Aditya Sampath
 * @version 5/17/14
 */
public interface NetworkGUI {
	
	/**
	 * This method lets the NetworkGUI receive methods. It's called by
	 * a NetworkHandler
	 * @param s data that is received in Serializable form
	 */
	public void receive (Serializable s);
}
