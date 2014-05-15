package gui;

import java.io.Serializable;

public class Message implements Serializable {
	private String str;
	private String myIP;
	private static final long serialVersionUID = 1L;
	
	public Message(String s, String ip) {
		str = s;
		myIP = ip;
	}
	
	public String getText() {
		return str;
	}
	
	public String getIP() {
		return myIP;
	}
}
