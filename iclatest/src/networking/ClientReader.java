package networking;

import interfaces.*;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;

/**
 * Reads data from the network. Isn't visible to any class outside of
 * this package
 * 
 * @author Aditya Sampath
 * @credits john_shelby
 */
class ClientReader implements Runnable {

    private Socket s;
    private NetworkHandler updater;
    private ObjectInputStream in;
    private boolean shouldClose;
	private int num;

    ClientReader(Socket s, int n, NetworkHandler updater) {
        this.s = s;
        this.updater = updater;
        num = n;
        shouldClose = false;
        
        try {
            in = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
        } catch(IOException e) {
            System.err.println("Error connecting input stream.");
            e.printStackTrace();
        }
        new Thread(this).start();
    }

    public void run() {
        try {
            while(s.isConnected() && in != null && !shouldClose) {
                Serializable data = null;
                try {
                    data = (Serializable) in.readObject();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                    System.exit(0);
                } catch (SocketException ex) {
                	//nothing happens gg
                } catch (EOFException ex) {
                }
                updater.networkReceive(data, num);
                System.out.println("Got an update!");
            }
            if (in != null)
                in.close();
            if (!s.isClosed())
                s.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
                if (!s.isClosed())
                    s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    void close () {
    	shouldClose = true;
    }
}
