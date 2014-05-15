package gui;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * Based off of rotation demo
 * @author Mr Shelby
 */
 
public class MovingImage extends Rectangle2D.Double{
	// FIELDS
	private Image image;
	private double dir;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates a MovingImage
	 * @param filename filename of the image file to base this movingimage object around
	 * @param x starting x coord of the moving image
	 * @param y starting y coord of the moving image
	 * @param w width of the moving image
	 * @param h height of the moving image
	 */
	public MovingImage(String filename, double x, double y, double w, double h) {
		this((new ImageIcon(filename)).getImage(),x,y,w,h);
	}
	
	/**
	 * Creates a MovingImage
	 * @param img image file to base this movingimage object around
	 * @param x starting x coord of the moving image
	 * @param y starting y coord of the moving image
	 * @param w width of the moving image
	 * @param h height of the moving image
	 */
	public MovingImage(Image img, double x, double y, double w, double h) {
		super(x,y,w,h);
		image = img;
		dir = 0;
	}
	
	
	// METHODS
	/**
	 * Moves this movingimage to a new location
	 * @param x x-coord to move the moving image to
	 * @param y y-coord to move the movingimage to
	 */
	public void moveToLocation(double x, double y) {
		super.x = x;
		super.y = y;
	}
	
	/**
	 * Moves a moving image by a certain amount
	 * @param x Amount to add on to the moving image's x-coord
	 * @param y Amount to add on to the moving image's y-coord
	 */
	public void moveByAmount(double x, double y) {
		super.x += x;
		super.y += y;
	}
	
	/**
	 * Don't let the moving image move out of the bounds windowWidth x windowHeight
	 * @param windowWidth Bounds for x-axis (-windowWidth to windowWidth)
	 * @param windowHeight Bounds for y-axis (-windowHeight to windowHeight)
	 */
	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x,windowWidth-width);
		y = Math.min(y,windowHeight-height);
		x = Math.max(0,x);
		y = Math.max(0,y);
	}
	
	/**
	 * Draws moving image on to the screen
	 * @param g graphics object to draw the image with
	 * @param io ImageObserver to watch the loading/drawing of the image onto the screen
	 */
	public void draw(Graphics g, ImageObserver io) {
		Graphics2D g2 = (Graphics2D)g;
		AffineTransform at = g2.getTransform();
		g2.translate(x+width/2, y+height/2);
		g2.rotate(dir);
		g.drawImage(image,(int)(-width/2),(int)(-height/2),(int)width,(int)height,io);
		g2.setTransform(at);
	}
	
	/**
	 * Turns the moving image to a certain direction
	 * @param dir
	 */
	public void turn(double dir) {
		this.dir = dir;
	}
	
	/**
	 * Turn the moving image towards a point (x, y)
	 * @param x x-coordinate to turn the moving image towards
	 * @param y y-coordinate to turn the moving image towards
	 */
	public void turnToward(int x, int y) {
		dir = Math.atan(((double)this.y-y)/(this.x-x));
		dir += Math.PI/2;
		if (this.x > x)
			dir += Math.PI;
	}
	
	public double getDirection() {
		return dir;
	}	
}