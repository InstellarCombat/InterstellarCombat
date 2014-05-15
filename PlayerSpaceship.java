public class PlayerSpaceship
{
	private double x;
	private double y;
	private double z;
	private double xVelo;
	private double yVelo;
	private double zVelo;
	
	public PlayerSpaceship()
	{
		x = 0;
		y = 0;
		z = 0;
		xVelo = 0;
		yVelo = 0;
		zVelo = 0;
	}
	
	public PlayerSpaceship(double x1, double y1, double z1)
	{
		x = x1;
		y = y1;
		z = z1;
		xVelo = 0;
		yVelo = 0;
		zVelo = 0;
	}
	
	public void moveRight(double v)
	{
		xVelo += v;
	}

	public void moveForward(double v)
	{
		yVelo += v;
	}
	
	public void moveUp(double v)
	{
		zVelo += v;
	}
		
	public void act()
	{
		x += xVelo;
		y += yVelo;
		z += zVelo;
	}
}