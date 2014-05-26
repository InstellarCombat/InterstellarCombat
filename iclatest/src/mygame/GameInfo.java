package mygame;

public class GameInfo {
	private boolean server;
	private boolean BigShip;
	private String serverIP;
	
	public boolean isServer() {
		return server;
	}
	
	public String getServerIP() {
		return serverIP;
	}
	
	public boolean isBigShip() {
		return BigShip;
	}
	
	public void setServer(boolean isServer) {
		server = isServer;
	}
	
	public void setServerIP (String myIP) {
		serverIP = myIP;
	}
	
	public void setShip(boolean isBig) {
		BigShip = isBig;
	}
}
