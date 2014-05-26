package ship;

import projectiles.*;

import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

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
	  
	  public void moveUp() {
		  Vector3f direction=new Vector3f(0,(float)(speed*3),0);
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);
		
	  }
	  
	  public void moveDown() {
		  Vector3f direction=new Vector3f(0,(float)(-speed*3),0);
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);
		
	  }
	  
	  public void moveLeft() {
		  Vector3f direction=new Vector3f(0,0,(float)(speed*3));
		  ship_phy.getPhysicsRotation().multLocal(direction);
		  ship_phy.applyCentralForce(direction);
		
	  }
	  
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