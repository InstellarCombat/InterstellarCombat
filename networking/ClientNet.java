package networking;

import interfaces.*;

import java.io.*;
import java.net.*;

/**
 * NOT USED ANYMORE
 */
public class ClientNet implements Runnable  {
	private Socket kkSocket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private NetworkHandler netHandler;
	private String msg;
	private boolean canSend;

	public ClientNet (Socket ks, NetworkHandler nh) {
		kkSocket = ks;
		netHandler = nh;
		msg = null;
		canSend = true;
		
		 try {
				BufferedOutputStream os = new BufferedOutputStream(kkSocket.getOutputStream());
				out = new ObjectOutputStream(os);
				out.flush();
				
				BufferedInputStream is = new BufferedInputStream(kkSocket.getInputStream());
				in = new ObjectInputStream(is);
			} catch(IOException e) {
				System.err.println("Error connecting input/output streamx.");
				e.printStackTrace();
			}		
	}
	
	public void run () {
		String code = "None";
		try {
			while(kkSocket.isConnected() && out != null && in != null) {
				Serializable data;
				
				if (msg != null && canSend) {
					out.writeObject(msg);
					out.flush();
					out.reset();
					canSend = false;
					System.out.println("Objects away!");		            
				}

				try {
					if (in.available() <= 0) continue; 
					data = (Serializable)in.readObject();
					code = (String)data;
					System.out.println(code);
					netHandler.networkReceive(code, -1);
				} catch (ClassNotFoundException e) {
					System.out.println("Error: Protocol does not exist on client side");
				}

				if (code.equals("/break")) break;
			}
			System.out.println("Heh");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
				if (!kkSocket.isClosed())
					kkSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void send(String s) {
		System.out.println("aquiiiii");
		msg = s;
		canSend = true;
	}
}
