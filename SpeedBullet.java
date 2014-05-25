package projectiles;

import java.awt.Graphics;

public class SpeedBullet extends Projectile
{
	public SpeedBullet()
	{
		damage = 5;
		speed = 20;
	}

	public SpeedBullet(int i, int j, float f, boolean b, boolean c)
	{
		super(i, j, f, b, c);
		damage = 5;
		speed = 20;
	}
	
/*	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}*/

}
