
package projectiles;

import java.awt.Graphics;

import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;

public class DamageBullet extends Projectile
{
	public DamageBullet()
	{
		damage = 10;
		speed = 10;
	}

	public DamageBullet(int i, int j, float f, boolean b, boolean c, RigidBodyControl rc, SphereCollisionShape scs) {
		super(i, j, f, b, c, rc, scs);
		damage = 10;
		speed = 10;
	}

	/*@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}*/

}