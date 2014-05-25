package projectiles;

import com.jme3.scene.shape.Sphere;

public abstract class Projectile extends Sphere
{
	public Projectile(int i, int j, float f, boolean b, boolean c)
	{
		super(i, j, f, b, c);
	}
	
	public Projectile()
	{
		
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	protected int damage;
	protected int speed;
}
