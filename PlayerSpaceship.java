public class PlayerSpaceship
{
	private int x;
	private int y;
	private int z;
	private int xVelo;
	private int yVelo;
	private int zVelo;
	
	public PlayerSpaceship()
	{
		x = 0;
		y = 0;
		z = 0;
		xVelo = 0;
		yVelo = 0;
		zVelo = 0;
	}
	
	public PlayerSpaceship(int x1, int y1, int z1)
	{
		x = x1;
		y = y1;
		z = z1;
		xVelo = 0;
		yVelo = 0;
		zVelo = 0;
	}
	
	public void moveRight(int v)
	{
		xVelo += v;
	}

	public void moveForward(int v)
	{
		yVelo += v;
	}
	
	public void moveUp(int v)
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