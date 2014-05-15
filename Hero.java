public class Hero extends MovingImage {
	private boolean moving;
	private static final long serialVersionUID = 1L;

	public Hero() {
		super ("Warrior.PNG",200,200,40,40);
		moving = false;
	}

	public void moveForward(boolean moving) {
		this.moving = moving;
	}
	
	public void act() {
		double dir = getDirection();
		if (moving)
			moveByAmount(4*Math.cos(dir),4*Math.sin(dir));
	}	
}