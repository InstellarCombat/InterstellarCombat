package info;

import java.io.Serializable;

import com.jme3.math.Vector3f;

public class BulletInfo implements Serializable {
	private Vector3f coords;
	private static final long serialVersionUID = 1L;

	public BulletInfo (Vector3f c) {
		coords = c;
	}
	
	public Vector3f getCoords () {
		return coords;
	}
	
	public void setCoords (Vector3f c) {
		coords = c;
	}
}