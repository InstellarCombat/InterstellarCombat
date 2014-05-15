/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package networking;

import interfaces.*;

import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;

/**
 * NOT USED ANYMORE
 * Does network connection handling
 * @author aditya sampath
 */
public class RequestThread extends Thread {
    private Socket socket = null;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private NetworkHandler netHandler;
    private String msg;
    private boolean canSend;

    public RequestThread(Socket sock, NetworkHandler nh) {
        super("RequestThread");
    	JOptionPane.showMessageDialog(null, "a");
        this.socket = sock;
        netHandler = nh;
        msg = null;
        canSend = true;
        
        try {
            BufferedInputStream is = new BufferedInputStream(sock.getInputStream());
            BufferedOutputStream os = new BufferedOutputStream(sock.getOutputStream());

            in = new ObjectInputStream(is);
            
            out = new ObjectOutputStream(os);
            out.flush();
        } catch(IOException e) {
            System.err.println("Error connecting input/output streamx.");
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while(socket.isConnected() && out != null && in != null) {
	            try {
					if (in.available() <= 0) continue; 

	                Serializable data = (Serializable)in.readObject();
	                JOptionPane.showMessageDialog(null, "sDone");

	                String p = (String)data;
	                System.out.println(p);
	                netHandler.networkReceive(p, -1);
	            } catch(ClassNotFoundException ex) {
	                System.out.println("Error: Protocol does not exist on server side");
	            }
	
	            if (msg != null && canSend) {
	            	out.writeObject(msg);
	            	out.flush();
	                out.reset();

	            	canSend = false;
            	}
	            
                JOptionPane.showMessageDialog(null, "out" + out + "in" + in + "connect" + socket.isConnected());

	            if (msg.equals("/break")) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
                if (!socket.isClosed())
                	socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String s) {
    	System.out.println("Aquisenor");
		msg = s;
		canSend = true;
	}
}