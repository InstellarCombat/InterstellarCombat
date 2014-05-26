package projectiles;

import java.awt.Graphics;

import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;

/**
 * A bullet which travels slowly but has a high damage output. 
 * @author Daniel
 * @version v1.0
 */
public class DamageBullet extends Projectile
{
	/**
	 * The no-args constructor for the DamageBullet.
	 */
	public DamageBullet()
	{
		damage = 10;
		speed = 10;
	}

	/**
	 * The constructor for the DamageBullet, taking in seven arguments.
	 * @param i The z-sample of the sphere.
	 * @param j The radial samples of the sphere.
	 * @param f The radius of the sphere.
	 * @param b Whether or not to use even slices when drawing the sphere.
	 * @param c Whether or not the interior is colored.
	 * @param rc The RigidBodyControl that represents the sphere.
	 * @param scs The CollisionShape that represents the sphere.
	 */
	public DamageBullet(int i, int j, float f, boolean b, boolean c, RigidBodyControl rc, SphereCollisionShape scs)
	{
		super(i, j, f, b, c, rc, scs);
		damage = 10;
		speed = 10;
	}

	/*@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}*/

}