package mygame;
import java.util.ArrayList;

import javax.swing.JPanel;

import powerups.Powerup;
import projectiles.Projectile;
import ship.PlayerSpaceship;

public class GameField extends JPanel
{
	private PlayerSpaceship[] ship;
	private ArrayList<Powerup> powerups;
	private ArrayList<Projectile> bullets;

	public GameField(PlayerSpaceship[] s)
	{
		ship = s;

		powerups = new ArrayList<Powerup>();
		bullets = new ArrayList<Projectile>();
	}
}