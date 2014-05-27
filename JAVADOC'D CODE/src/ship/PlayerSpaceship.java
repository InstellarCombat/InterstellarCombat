package ship;

import projectiles.*;

import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
/**
 * 
 * @author Adi Zimmerman
 * 
 * This class defines a spaceship in the 3D world
 *
 */
public class PlayerSpaceship {
	private float x;
	private float y;
	private float z;
	private int health;
	private int attack;
	private int defense;
	private int speed;
	static final long serialVersionUID = 1L;
	private boolean big;
	
	private RigidBodyControl ship_phy;
	private CollisionShape ship_shape;
	private Spatial ship;

	/**
	 * Empty Constructor
	 */
	public PlayerSpaceship() {
		x = 0;
		y = 0;
		z = 0;
		attack = 0;
		defense = 0;
		speed = 0;
		health = 100;
		big = true;
	}
	
	private PlayerSpaceship(Spatial s, RigidBodyControl rc, CollisionShape scs, boolean b) {
		ship = s;
		ship_phy = rc;
		ship_shape = scs;
		//ship.addControl(ship_phy);
		big = b;
		if (b)
		{
			speed = 5;
		}
		else
		{
			speed = 15;
		}
		health = 100;
	}
	
	/**
	 * 
	 * @param s - spaceship model
	 * @param rc - control of spaceship to add rigidity
	 * @param scs - adds collision detection to a shape
	 * @param x1 - location x
	 * @param y1 - location y
	 * @param z1 - location z
	 * @param a - attack damage
	 * @param d - defense multiplier
	 * @param b - whether it is a Big ship or a Small ship
	 */
	public PlayerSpaceship(Spatial s, RigidBodyControl rc, CollisionShape scs, float x1, float y1, float z1, int a, int d, boolean b) {
		this(s, rc, scs, b);
		x = x1;
		y = y1;
		z = z1;
		attack = a;
		defense = d;
		health = 100;
	}
	
	public void moveShip(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		ship.move(x,y,z);
	}
	
	/**
	 * Shoots a projectile from this spaceship
	 * @param p - Projectile to be shot
	 */
	  public void shoot(Projectile p) {
		  /** Create a cannon ball geometry and attach to scene graph. */
		  p.accelerateBullet();
	  }
	
	  /**
	   * Accelerates the ship
	   */
	  public void accelerateShip() {
		  Vector3f direction=new Vector3f((float)(-speed*3),0,0);
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);

	  }
	  
	  /**
	   * Brake function for the ship
	   */
	  public void decelerateShip() {
		  Vector3f direction=new Vector3f((float)(speed*3),0,0);
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);

	  }
	  /**
	   * Moves the spaceship up
	   */
	  public void moveUp() {
		  Vector3f direction=new Vector3f(0,(float)(speed*3),0);
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);
		
	  }
	  
	  /**
	   * Moves the spaceship down
	   */
	  public void moveDown() {
		  Vector3f direction=new Vector3f(0,(float)(-speed*3),0);
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);
		
	  }
	  
	  /**
	   * Moves the spaceship left
	   */
	  public void moveLeft() {
		  Vector3f direction=new Vector3f(0,0,(float)(speed*3));
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);
		
	  }
	  
	  /**
	   * Moves the spaceship right
	   */
	  public void moveRight() {
		  Vector3f direction=new Vector3f(0,0,(float)(-speed*3));
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
	  
	 /**
	  * Sets the control of the rigid body to the spaceship
	  * @param rgc - RigidBodyControl
	  */
	public void setControl(RigidBodyControl rgc) {
		ship_phy = rgc;
		ship.addControl(rgc);
	}
	
	/**
	 * 
	 * @return - the RigidBodyControl of this spaceship
	 */
	public RigidBodyControl getRigidBodyControl() {
		return ship_phy;
	}
	
	/**
	 * 
	 * @return - the SphereCollisionShape of this spaceship
	 */
	public CollisionShape getSphereCollisionShape() {
		return ship_shape;
	}
	
	/**
	 * 
	 * @return - the Vector3f (x,y,z) coordinate of this spaceship
	 */
	public Vector3f getSpot() {
		return new Vector3f(x,y,z);
	}
	
	/**
	 * 
	 * @param s - sets the spatial of this spaceship
	 */
	public void setSpatial(Spatial s) {
		ship = s;
	}
	
	/**
	 * 
	 * @return the spatial of this spaceship
	 */
	public Spatial getSpatial() {
		return ship;
	}
	
	/**
	 * 
	 * @param b - sets the size of this spaceship (big or small)
	 */
	public void setSize(boolean b)
    {
    	big = b;
    }
	
	/**
	 * 
	 * @return the size of this spaceship
	 */
	public boolean getSize()
    {
    	return big;
    }
	
	/**
	 * 
	 * @return the speed of this spaceship
	 */
	public int getSpeed()
	{
		return speed;
	}
	
	/**
	 * 
	 * @param health - sets the health of this spaceship
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * 
	 * @return the health of this spaceship
	 */
	public int getHealth() {
		return health;
	}
}