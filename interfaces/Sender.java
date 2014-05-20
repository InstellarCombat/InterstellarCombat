package interfaces;

import java.io.Serializable;

/**
 * Represents an object that can send data over a network. Interface provides access to one method- send
 * @author Aditya Sampath
 */
public interface Sender {
	/**
	 * Method that sends data over a network
	 * @param s data that you're sending over an object 
	 */
	public void send(Serializable s);
}
