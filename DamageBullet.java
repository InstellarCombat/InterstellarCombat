package projectiles;

import java.awt.Graphics;

public class DamageBullet extends Projectile
{
	public DamageBullet()
	{
		damage = 10;
		speed = 10;
	}

	public DamageBullet(int i, int j, float f, boolean b, boolean c) {
		super(i, j, f, b, c);
		damage = 10;
		speed = 10;
	}
	
	/*@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}*/

}
