package info;

import java.io.Serializable;

import com.jme3.math.Vector3f;

public class ShipInfo implements Serializable {
	private Vector3f coords;
	private static final long serialVersionUID = 1L;

	public ShipInfo (Vector3f c) {
		coords = c;
	}
	
	public Vector3f getCoords () {
		return coords;
	}
	
	public void setCoords (Vector3f c) {
		coords = c;
	}
}