package networking;

import interfaces.*;
import java.io.*;
import java.net.*;

/**
 * Writes data to the network. Not visible to anyone outside of this
 * package
 * @author Aditya Sampath
 * @credits john_shelby (major credits). Acknowledgements Matt F.
 */
class ClientWriter implements Runnable {

    private Socket s;
    private NetworkHandler updater;
    private ObjectOutputStream out;
    private Serializable obj;
    private boolean shouldClose;

    ClientWriter(Socket s, NetworkHandler nh) {
        this.s = s;
        this.updater = nh;
        obj = null;
        shouldClose = false;
        
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
            while(s.isConnected() && out != null && !shouldClose) {
                try {
                    Thread.sleep(40);
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

	void send(Serializable s) {
		obj = s;
	}
	
	void close () {
		shouldClose = true;
	}
}
