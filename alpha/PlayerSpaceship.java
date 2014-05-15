package alpha;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class PlayerSpaceship implements ActionListener
{
	public static final int SHIP_WIDTH = 40;
	public static final int SHIP_HEIGHT = 60;
	
	private double x;
	private double y;
	private double z;
	private double xVelo;
	private double yVelo;
	private double zVelo;
	
	private Image image;

	public PlayerSpaceship()
	{
		image = new ImageIcon("spaceship.png").getImage();
		
		x = 0;
		y = 0;
		z = 0;
		xVelo = 0;
		yVelo = 0;
		zVelo = 0;
	}

	public PlayerSpaceship(double x1, double y1, double z1)
	{
		image = new ImageIcon("spaceship.png").getImage();
		
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
		double xCord = x;
		double yCord = y;
		xCord += xVelo;
		yCord += yVelo;
		x = xCord;
		y = yCord;
		z += zVelo;
	}
	
	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(image,(int)x,(int)y,(int)SHIP_WIDTH,(int)SHIP_HEIGHT,io);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
