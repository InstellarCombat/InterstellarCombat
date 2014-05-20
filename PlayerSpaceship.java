public class PlayerSpaceship
{
	private float x;
	private float y;
	private float z;
	private float xVelo;
	private float yVelo;
	private float zVelo;
	private int attack;
	private int defense;
	private int speed;
	static final long serialVersionUID = 1L;
	
	public PlayerSpaceship()
	{
		x = 0;
		y = 0;
		z = 0;
		xVelo = 0;
		yVelo = 0;
		zVelo = 0;
		attack = 0;
		defense = 0;
		speed = 0;
	}
	
	public PlayerSpaceship(float x1, float y1, float z1, int a, int d, int sp)
	{
		x = x1;
		y = y1;
		z = z1;
		xVelo = 0;
		yVelo = 0;
		zVelo = 0;
		attack = a;
		defense = d;
		speed = sp;
	}
	
	public void moveRight(float v)
	{
		if (xVelo + v <= speed)
			xVelo += v;
	}

	public void moveForward(float v)
	{
		if (yVelo + v <= speed)
			yVelo += v;
	}
	
	public void moveUp(float v)
	{
		if (zVelo + v <= speed)
			zVelo += v;
	}
		
	public void act()
	{
		x += xVelo;
		y += yVelo;
		z += zVelo;
	}
}