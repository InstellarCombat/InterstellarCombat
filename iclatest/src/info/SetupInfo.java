package info;

import java.io.Serializable;

import com.jme3.math.Vector3f;

public class SetupInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean big;
	private Vector3f startCoords;
	
	public SetupInfo (boolean b, Vector3f c) {
		big = b;
		startCoords = c;
	}
	
	public void setBig (boolean b) {
		big = b;
	}
	
	public void setCoords (Vector3f c) {
		startCoords = c;
	}
	
	public boolean isBig () {
		return big;
	}
	
	public Vector3f getCoords () {
		return startCoords;
	}
}
