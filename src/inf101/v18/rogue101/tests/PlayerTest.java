package inf101.v18.rogue101.tests;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import inf101.v18.grid.GridDirection;
import inf101.v18.grid.ILocation;
import inf101.v18.rogue101.game.Game;
import inf101.v18.rogue101.game.IGame;
import inf101.v18.rogue101.map.GameMap;
import inf101.v18.rogue101.objects.IItem;
import inf101.v18.rogue101.objects.IPlayer;
import javafx.scene.input.KeyCode;

class PlayerTest {
	public static String TEST_MAP = "40 5\n" //
			+ "########################################\n" //
			+ "#...... ..C.R ......R.R......... ..R...#\n" //
			+ "#.R@R...... ..........RC..R...... ... .#\n" //
			+ "#... ..R........R......R. R........R.RR#\n" //
			+ "########################################\n" //
	;

	@Test
	void testPlayer1() {
		// new game with our test map
		Game game = new Game(TEST_MAP);
		// pick (3,2) as the "current" position; this is where the player is on the
		// test map, so it'll set up the player and return it
		IPlayer player = (IPlayer) game.setCurrent(3, 2);

		// find players location
		ILocation loc = game.getLocation();
		// press "UP" key
		player.keyPressed(game, KeyCode.UP);
		// see that we moved north
		assertEquals(loc.go(GridDirection.NORTH), game.getLocation());
	}

	@Test
	// In order to actually run this test we have to comment out the if-statement in 
	// game.move(). Otherwise this test will fail with "You're out of moves!".
	public void testIfPlayerCanGoIntoWalls() {
		// new game with our test map
		Game game = new Game(TEST_MAP);
		// pick (3,2) as the "current" position; this is where the player is on the
		// test map, so it'll set up the player and return it
		IPlayer player = (IPlayer) game.setCurrent(3, 2);
		
		// press "DOWN" key five times
		for (int i = 0; i < 5; i++) {
			player.keyPressed(game, KeyCode.DOWN);
		}
		// press "LEFT" key five times
		for (int i = 0; i < 5; i++) {
			player.keyPressed(game, KeyCode.LEFT);
		}
		
		// we still end up in the down-leftmost corner and could not move into the wall
		ILocation loc = game.getMap().getLocation(1, 3);
		assertEquals(loc, game.getLocation());
	}

}
