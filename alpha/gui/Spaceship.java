package gui;

/**
 * Based off of rotation demo
 * Is the Character that moves
 * @author Mr Shelby
 */
public class Spaceship extends MovingImage {
	private boolean moving;
	private static final long serialVersionUID = 1L;

	/**
	 * No args constructor. Creates a 40x40 spaceship at (200,200)
	 */
	public Spaceship() {
		super ("spaceship.png",200,200,40,40);
		moving = false;
	}

	/**
	 * Toggles a variable that sets whether or not the spaceship is moving
	 * @param moving Whether or not the spaceship is moving
	 */
	public void moveForward(boolean moving) {
		this.moving = moving;
	}
	
	/**
	 * Moves the spaceship if it should move. Moves based off of angle of the spaceship.
	 */
	public void act() {
		double dir = getDirection();
		if (moving)
			moveByAmount(4*Math.cos(dir - Math.PI/2),4*Math.sin(dir - Math.PI/2));
	}	
}