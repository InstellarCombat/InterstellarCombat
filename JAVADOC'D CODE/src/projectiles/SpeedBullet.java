package projectiles;

import java.awt.Graphics;

import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;

/**
 * 
 * @author Daniel Guo
 * This class defines a speed bullet, extending Projectile
 *
 */
public class SpeedBullet extends Projectile
{
	/**
	 * Creates a speedy bullet
	 */
	public SpeedBullet()
	{
		damage = 5;
		speed = 20;
	}

	/**
	 * 
	 * @param i - sphere int
	 * @param j - sphere int
	 * @param f - sphere int
	 * @param b - sphere boolean
	 * @param c - sphere boolean
	 * @param rc - control of bullet to add rigidity
	 * @param scs - adds collision shape to bullet
	 */
	public SpeedBullet(int i, int j, float f, boolean b, boolean c, RigidBodyControl rc, SphereCollisionShape scs)
	{
		super(i, j, f, b, c, rc, scs);
		damage = 5;
		speed = 20;
	}

}