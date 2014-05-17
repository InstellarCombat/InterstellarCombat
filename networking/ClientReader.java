/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package networking;

import interfaces.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 * Reads data from the network. Isn't visible to any class outside of
 * this package
 * 
 * @author john_shelby
 * @credits Aditya Sampath
 */
class ClientReader implements Runnable {

    private Socket s;
    private NetworkHandler updater;
    private ObjectInputStream in;
	private int num;

    public ClientReader(Socket s, int n, NetworkHandler updater) {
        this.s = s;
        this.updater = updater;
        num = n;
        
        try {
            in = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
        } catch(IOException e) {
            System.err.println("Error connecting input stream.");
            e.printStackTrace();
        }
        new Thread(this).start();
    }

    public void run() {
        //String info = null;
        try {
            while(s.isConnected() && in != null) {

                Serializable data = null;
                try {
                    data = (Serializable) in.readObject();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                    System.exit(0);
                }
                //info = (String)data;
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
}
