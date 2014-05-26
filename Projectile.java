package projectiles;

import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Sphere.TextureMode;

/**
 * The base abstract class for a bullet.
 * @author Daniel
 * @version v1.0
 */
public abstract class Projectile extends Sphere {
	protected int damage;
	protected int speed;
	protected Geometry ball_geo;
	protected RigidBodyControl bullet_phy;
	protected SphereCollisionShape bullet_shape;

	/**
	 * The constructor for the Projectile, taking in seven arguments.
	 * @param i The z-sample of the sphere.
	 * @param j The radial samples of the sphere.
	 * @param f The radius of the sphere.
	 * @param b Whether or not to use even slices when drawing the sphere.
	 * @param c Whether or not the interior is colored.
	 * @param rc The RigidBodyControl that represents the sphere.
	 * @param scs The CollisionShape that represents the sphere.
	 */
	public Projectile(int i, int j, float f, boolean b, boolean c, RigidBodyControl rc, SphereCollisionShape scs) {
		super(i, j, f, b, c);
		setTextureMode(TextureMode.Projected);
		ball_geo = new Geometry("bullet", this);
		
		bullet_phy = rc;
		bullet_shape = scs;
		
	}

	/**
	 * The no-args constructor for the Projectile.
	 */
	public Projectile() {

	}

	public int getSpeed() {
		return speed;
	}
	
	public RigidBodyControl getRigidBodyControl() {
		return bullet_phy;
	}
	
	public SphereCollisionShape getSphereCollisionShape() {
		return bullet_shape;
	}
	
	/**
	 * Accelerates the sphere represented by the Projectile to shoot it. 
	 */
	public void accelerateBullet() {
	    bullet_phy.setLinearVelocity(new Vector3f(-1,0,0).mult(speed*10));
	}
	
	public Geometry getGeometry() {
		return ball_geo;
	}
	
}