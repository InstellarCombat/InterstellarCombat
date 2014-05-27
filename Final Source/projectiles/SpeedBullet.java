package projectiles;

import java.awt.Graphics;

import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;

public class SpeedBullet extends Projectile
{
	public SpeedBullet()
	{
		damage = 5;
		speed = 20;
	}

	public SpeedBullet(int i, int j, float f, boolean b, boolean c, RigidBodyControl rc, SphereCollisionShape scs)
	{
		super(i, j, f, b, c, rc, scs);
		damage = 5;
		speed = 20;
	}

/*	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}*/

}