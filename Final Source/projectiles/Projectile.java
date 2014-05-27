
package projectiles;

import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Sphere.TextureMode;

public abstract class Projectile extends Sphere {
	protected int damage;
	protected int speed;
	protected Geometry ball_geo;
	protected RigidBodyControl bullet_phy;
	protected SphereCollisionShape bullet_shape;

	public Projectile(int i, int j, float f, boolean b, boolean c, RigidBodyControl rc, SphereCollisionShape scs) {
		super(i, j, f, b, c);
		setTextureMode(TextureMode.Projected);
		ball_geo = new Geometry("bullet", this);
		
		bullet_phy = rc;
		bullet_shape = scs;
		
	}

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
	
	public void accelerateBullet() {
		/** Accelerate the physcial ball to shoot it. */
	    bullet_phy.setLinearVelocity(new Vector3f(-1,0,0).mult(speed*10));
	}
	
	public Geometry getGeometry() {
		return ball_geo;
	}
	
	public Vector3f getCurrentLoc() {
		return ball_geo.getLocalTranslation();
	}
	
}