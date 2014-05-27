package ship;

import projectiles.*;

import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

/**
 * The class that represents the spaceship.
 * @author Daniel
 * @version v1.0
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
	 * The no-args constructor for the PlayerSpaceship.
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
	
	/**
	 * The constructor for the PlayerSpaceship, taking in four arguments.
	 * @param s The model that represents the ship.
	 * @param rc The RigidBodyControl of the ship.
	 * @param scs The CollisionShape of the ship.
	 * @param b Whether or not the ship is big.
	 */
	private PlayerSpaceship(Spatial s, RigidBodyControl rc, CollisionShape scs, boolean b) {
		ship = s;
		ship_phy = rc;
		ship_shape = scs;
		ship.addControl(ship_phy);
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
	 * The constructor for the PlayerSpaceship, taking in nine arguments.
	 * @param s The model that represents the ship.
	 * @param rc The RigidBodyControl of the ship.
	 * @param scs The CollisionShape of the ship.
	 * @param x1 The starting x-coordinate of the ship.
	 * @param y1 The starting y-coordinate of the ship.
	 * @param z1 The starting z-coordinate of the ship.
	 * @param a The attack power of the ship.
	 * @param d The defensive power of the ship.
	 * @param b Whether or not the ship is big.
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
	/**
	 * The method to move the ship.
	 * @param x The x-coordinate that the ship moves to.
	 * @param y The y-coordinate that the ship moves to.
	 * @param z The z-coordinate that the ship moves to.
	 */
	public void moveShip(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		ship.move(x,y,z);
	}
	
	/**
	 * The method to shoot a projectile.
	 * @param p The projectile that will be shot.
	 * @pre p is not null.
	 */
	  public void shoot(Projectile p) {
		  /** Create a cannon ball geometry and attach to scene graph. */
		  p.accelerateBullet();
	  }
	
	  /**
	   * Accelerates the ship.
	   * @post The ship's velocity vector increases in the forward direction.
	   */
	  public void accelerateShip() {
		  Vector3f direction=new Vector3f((float)(-speed*3),0,0);
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);

	  }
	  
	  /**
	   * Brakes the ship.
	   * @post The ship's velocity vector decreases in the forward direction.
	   */
	  public void decelerateShip() {
		  Vector3f direction=new Vector3f((float)(speed*3),0,0);
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);

	  }
	  
	  /**
	   * Applies a force on the ship to move it up.
	   * @post The ship's velocity vector increases in the upward direction.
	   */
	  public void moveUp() {
		  Vector3f direction=new Vector3f(0,(float)(speed*3),0);
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);
	  }
	  
	  /**
	   * Applies a force on the ship to move it down.
	   * @post The ship's velocity vector decreases in the upward direction.
	   */
	  public void moveDown() {
		  Vector3f direction=new Vector3f(0,(float)(-speed*3),0);
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);
		
	  }
	  
	  /**
	   * Applies a force on the ship to move it left.
	   * @post The ship's velocity vector decreases in the rightward direction.
	   */
	  public void moveLeft() {
		  Vector3f direction=new Vector3f(0,0,(float)(speed*3));
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);
		
	  }
	  
	  /**
	   * Applies a force on the ship to move it right.
	   * @post The ship's velocity vector increases in the rightward direction.
	   */
	  public void moveRight() {
		  Vector3f direction=new Vector3f(0,0,(float)(-speed*3));
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);
		
	  }
	  
	  /** 
	   * Sets the velocity of the ship to 0.
	   * @post The ship does not move.
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
	
	public CollisionShape getSphereCollisionShape() {
		return ship_shape;
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
	
	public void setSize(boolean b)
    {
    	big = b;
    }
	
	public boolean getSize()
    {
    	return big;
    }
	
	public int getSpeed()
	{
		return speed;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealth() {
		return health;
	}
}