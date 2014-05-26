Daniel Guo, Aditya Sampath, Adi Zimmerman
APCS, Period 3
5/5/14

Interstellar Combat 3-D

Description:

Interstellar Combat 3-D is a game where a player controls a spaceship to try to shoot down other spaceships. Interstellar
Combat 3-D offers 3-D graphics, a new dimension to shoot in, and a networking system allowing play with friends.

The program is simply a game; it is provided only for the user's entertainment. Thus, the program solves the problem of
boredom and being addicted to Reddit - instead, you can get addicted to this.

The rules to the program are simply the rules of physics and other arcade games. You start off with a given amount of 
health (100), which will be displayed at the left side of the screen. Based off of the damage you take, you can 
eventually die. If this happens, then you will be removed from the game, and your opponent will win.

There is no gravity, and the only movement is caused by the arrow and A/Z keys.

Many people would want to use our program. People who are interested in a new and improved version of the previous Space
Invaders will want to try the program, such as people who are nostalgic for old-school gaming with a twist. In addition,
others will want to play for entertainment and enjoyment.

You control a spaceship (the player). You fire bullets which can hit and damage other player spaceships. You can play with friends
by connecting over the network. If you destroy an opponent's ship once, you win.

Instructions:

Use the arrow keys, A, and Z to control your spaceship and spacebar to shoot missiles. (The A and Z buttons move your ship up and down
vertically, in the z-axis.) The E button stops you ship immediately to prevent it from going out of control.

Features:

	Must-haves:

	-A 3-D implementation of the game.
	-A working networking system allowing players to play against each other.

	Want-to-Haves:

	-Upgrades which appear randomly around the board. These could include weapon upgrades (multiple bullets firing at the same time,
	more damage) and armor upgrades (less damage taken, more attack damage).
	-Team mode (where you destroy a base)
	-Team chat
	-Ability to control multiple spaceships

	Stretches:

	-AI for the game in a single-player mode similar to the original Space Invaders

Class List:

-SpaceShip: main spaceship class (represents a player, essentially)
-Main- main
-InterstellarCombat- application
-NetworkServer- sets up Server
-NetWorkClient- sets up Client
-MenuPanel- the menu screen panel
-InGameState- The state of the 3-D playing field.
-Projectile- A bullet fired by a spaceship.
	-DamageBullet- A bullet which travels slowly but has a high damage output.
	-SpeedBullet- A bullet which travels rapidly but has a low damage output.

Responsibility List:

Daniel Guo: Gameplay mechanics
Aditya Sampath: Networking
Adi Zimmerman: 3-D Graphics