package ship;

import projectiles.*;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class PlayerSpaceship {
	private float x;
	private float y;
	private float z;
	private int attack;
	private int defense;
	private int speed;
	static final long serialVersionUID = 1L;
	
	private RigidBodyControl ship_phy;
	private SpaceshipControl ship_control;
	private Spatial ship;

	public PlayerSpaceship() {
		x = 0;
		y = 0;
		z = 0;
		attack = 0;
		defense = 0;
		speed = 0;
	}
	
	private PlayerSpaceship(Spatial s, RigidBodyControl rc) {
		ship = s;
		ship_phy = rc;
		ship.addControl(ship_phy);
	}

	public PlayerSpaceship(Spatial s, RigidBodyControl rc, float x1, float y1, float z1, int a, int d, int sp) {
		this(s, rc);
		x = x1;
		y = y1;
		z = z1;
		attack = a;
		defense = d;
		speed = sp;
	}
	
	public void moveShip(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		ship.move(x,y,z);
	}
	
	  public void shoot(Projectile p) {
		  /** Create a cannon ball geometry and attach to scene graph. */
		  p.accelerateBullet();
	  }
	
	  /**
	   * Accelerates the ship
	   */
	  public void accelerateShip() {
		  Vector3f direction=new Vector3f(-15f,0,0);
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);

	  }
	  
	  /**
	   * Brake function for the ship
	   */
	  public void decelerateShip() {
		  Vector3f direction=new Vector3f(15f,0,0);
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);

	  }
	  
	  public void moveUp() {
		  Vector3f direction=new Vector3f(0,15f,0);
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);
		
	  }
	  
	  public void moveDown() {
		  Vector3f direction=new Vector3f(0,-15f,0);
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);
		
	  }
	  
	  public void moveLeft() {
		  Vector3f direction=new Vector3f(0,0,15f);
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);
		
	  }
	  
	  public void moveRight() {
		  Vector3f direction=new Vector3f(0,0,-15f);
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);
		
	  }
	  
	  /** 
	   * Sets velocity of ship to 0
	   */
	  public void clear() {
		  ship_phy.setLinearVelocity(new Vector3f(0, 0, 0));
		  ship_phy.setAngularVelocity(new Vector3f(0, 0, 0));
	  }
	  
	public void setControl(RigidBodyControl rgc) {
		ship_phy = rgc;
		ship.addControl(rgc);
	}
	
	public RigidBodyControl getRigidBodyControl() {
		return ship_phy;
	}
	
	public Vector3f getSpot() {
		return new Vector3f(x,y,z);
	}
	
	public void setSpatial(Spatial s) {
		ship = s;
	}
	
	public Spatial getSpatial() {
		return ship;
	}
}