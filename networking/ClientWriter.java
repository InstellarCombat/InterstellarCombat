/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package networking;

import interfaces.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author john_shelby
 */
public class ClientWriter implements Runnable {

    private Socket s;
    private NetworkHandler updater;
    private ObjectOutputStream out;
    private Serializable obj;

    public ClientWriter(Socket s, NetworkHandler nh) {
        this.s = s;
        this.updater = nh;
        obj = null;
        
        try {
            BufferedOutputStream os = new BufferedOutputStream(s.getOutputStream());
            out = new ObjectOutputStream(os);
            out.flush();
        } catch(IOException e) {
            System.err.println("Error connecting output stream.");
            e.printStackTrace();
        }
        new Thread(this).start();
    }

    public void run() {

        try {
            while(s.isConnected() && out != null) {

                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {}

                if (obj != null) {
	                out.writeObject(obj);
	                out.flush();
	                out.reset();
	                obj = null;
	                System.out.println("Sent an update!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
                if (!s.isClosed())
                    s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }

	public void send(Serializable s) {
		obj = s;
	}
}
