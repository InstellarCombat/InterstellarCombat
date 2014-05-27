Daniel Guo, Aditya Sampath, Adi Zimmerman
APCS, Period 3
5/5/14

Interstellar Combat 3-D

Description:

Interstellar Combat 3-D is a game where a player controls a spaceship to try to shoot down other spaceships, collecting points along
the way. Interstellar Combat 3-D offers 3-D graphics, a new dimension to shoot in, a networking system allowing play with friends, and
a player versus player shoot-out game.

The program is simply a game; it is provided only for the user's entertainment. Thus, the program solves the problem of
boredom and being addicted to Reddit - instead, you can get addicted to this.

The rules to the program are simply the rules of physics and other arcade games. The standard three lives rule applies, as is the case 
in other video games. You start off with a given amount of health, which will be displayed at the top of the screen in a health bar.
Based off of the damage you take, you can eventually die and one life will be lost. If all three lives are lost, then you can be

a) respawned with basic equipment
OR
b) removed from the game

depending on the type of game you're playing (shoot out or a game with a time limit).
There is no gravity, and the only movement is caused by the arrow and Q/A keys.

Many people would want to use our program. People who are interested in a new and improved version of the previous Space
Invaders will want to try the program, such as people who are nostalgic for old-school gaming with a twist. In addition,
others will want to play for entertainment and enjoyment.

You control a spaceship (the player). You fire bullets which can hit and damage other player spaceships. You can play with friends
by connecting over the network. If you destroy an opponent's ship three times, you win.

Instructions:

Use the arrow keys, Q, and A to control your spaceship and spacebar to shoot missiles. (The Q and A buttons move your ship up and down
vertically, in the z-axis.)

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

	Really Stretch:
	-Daniel Guo to stop talking about the pokemon gen3 remakes (yeah aditya, it was ONE post.)

Class List:

-SpaceShip: main spaceship class (represents a player, essentially)
 -BigSpaceShip: damage- and armor-oriented spaceship
 -SmallSpaceShip: speed-oriented spaceship
-InterstellarCombat- main
-Interaction- keyboard/mouse input handling
-GameMechanics- the game mechanics
-Scoreboard- manages the health of everyone, sets up a leaderboard, etc
-NetworkServer- sets up Server
-NetWorkClient- sets up Client
-GameWindow- graphics window
-GamePanel- the graphics panel
-GameField- the playing field
-MenuPanel- the menu screen panel
-Projectile- A bullet fired by a spaceship.
-Powerup- A powerup that boosts attack or defense.
 -AttackBoost- Increases attack damage by one
 -DefenseBoost- Increases defense by one

Responsibility List:

Daniel Guo: Gameplay mechanics
Aditya Sampath: Networking
Adi Zimmerman: 3-D Graphics