package inf101.v18.rogue101.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import inf101.v18.grid.ILocation;
import inf101.v18.rogue101.examples.Carrot;
import inf101.v18.rogue101.examples.Rabbit;
import inf101.v18.rogue101.map.GameMap;
import inf101.v18.rogue101.objects.Apple;
import inf101.v18.rogue101.objects.Dust;
import inf101.v18.rogue101.objects.IItem;
import inf101.v18.rogue101.objects.Player;

class GameMapTest {

	// This test asserts that items are sorted by their size on a give
	// Ilocation in the map.
	// NB: In order to run the test GameMap.getItems() needs to comment out the
	// line where it removes instances of IActors.
	void testSortedAdd() {
		GameMap gameMap = new GameMap(20, 20);
		ILocation location = gameMap.getLocation(10, 10);

		// done:

		IItem item1 = new Carrot(); // size 2
		IItem item2 = new Rabbit(); // size 4
		IItem item3 = new Dust(); // size 1
		IItem item4 = new Player(); // size 10
		IItem item5 = new Apple(); // size 5

		gameMap.add(location, item1);
		gameMap.add(location, item2);
		gameMap.add(location, item3);
		gameMap.add(location, item4);
		gameMap.add(location, item5);

		List<IItem> itemList = gameMap.getItems(location);

		assertEquals(itemList.get(0), item4); // player
		assertEquals(itemList.get(1), item5); // apple
		assertEquals(itemList.get(2), item2); // rabbit
		assertEquals(itemList.get(3), item1); // carrot
		assertEquals(itemList.get(4), item3); // dust
	}

	// Not really used because my game does not feature a vision-system
	@Test
	public void testVision() {
		GameMap gameMap = new GameMap(20, 20);
		ILocation centre = gameMap.getLocation(10, 10);
		int dist = 2;
		List<ILocation> neighbourhood = gameMap.getNeighbourhood(centre, dist);
		for (ILocation loc : neighbourhood) {
			assertTrue(centre.gridDistanceTo(loc) <= dist);
		}

	}

}
