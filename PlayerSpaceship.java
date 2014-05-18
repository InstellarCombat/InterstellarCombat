public class PlayerSpaceship
{
	private double x;
	private double y;
	private double z;
	private double xVelo;
	private double yVelo;
	private double zVelo;
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
	
	public PlayerSpaceship(double x1, double y1, double z1, int a, int d, int sp)
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
	
	public void moveRight(double v)
	{
		if (xVelo + v <= speed)
			xVelo += v;
	}

	public void moveForward(double v)
	{
		if (yVelo + v <= speed)
			yVelo += v;
	}
	
	public void moveUp(double v)
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