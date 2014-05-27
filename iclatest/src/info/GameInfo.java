package info;

import com.jme3.math.Vector3f;

public class GameInfo {
	private boolean server;
	private boolean BigShip;
	private String serverIP;
	private boolean shouldStart;
	private Vector3f coords;
	
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
	
	public Vector3f getStartCoords () {
		return coords;
	}
	
	public void setCoords (Vector3f c) {
		coords = c;
	}
	
	public boolean start () {
		return shouldStart;
	}
	
	public void startGame(boolean s) {
		shouldStart = s;
	}
}
