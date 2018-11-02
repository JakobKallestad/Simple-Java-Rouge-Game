package inf101.v18.rogue101.examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import inf101.v18.gfx.gfxmode.ITurtle;
import inf101.v18.grid.GridDirection;
import inf101.v18.grid.ILocation;
import inf101.v18.rogue101.game.IGame;
import inf101.v18.rogue101.objects.Food;
import inf101.v18.rogue101.objects.IActor;
import inf101.v18.rogue101.objects.IItem;
import inf101.v18.rogue101.objects.INonPlayer;
import inf101.v18.rogue101.objects.IPlayer;

public class Rabbit implements INonPlayer {
	private int food = 0;
	private int hp = getMaxHealth();

	@Override
	public void doTurn(IGame game) {
		if (food == 0)
			hp--;
		else
			food--;
		if (hp < 1)
			return;

		for (IItem item : game.getLocalItems()) {
			if (item instanceof Carrot) {
				System.out.println("found carrot!");
				int eaten = item.handleDamage(game, this, 5);
				if (eaten > 0) {
					System.out.println("ate carrot worth " + eaten + "!");
					food += eaten;
					game.displayMessage("You hear a faint crunching (" + getName() + " eats " + item.getArticle() + " "
							+ item.getName() + ")");
					return;
				}
			}
			
			if (item instanceof Food) {
				System.out.println("found " + item.getName() + "!");
				int eaten = item.handleDamage(game, this, 5);
				if (eaten > 0) {
					System.out.println("ate " + item.getName() + " worth " + eaten + "!");
					food += eaten;
					game.displayMessage(getSymbol() + "Itadakimasu!");
					return;
				}
			}
			
		}
		// done: prøv forskjellige varianter her

		// List of possible moves
		List<GridDirection> possibleMoves = game.getPossibleMoves();
		List<GridDirection> toRemove = new ArrayList<>();

		// Move smart if neighbor contains Carrot. Does not work, I don't know why..?
		for (int i = 0; i < possibleMoves.size(); i++) {
			ILocation loc = game.getLocation(possibleMoves.get(i));
			List<IItem> itemList = game.getMap().getItems(loc);
			for (int j = 0; j < itemList.size(); j++) {

				// should handle damage, only attacks players.
				if (itemList.get(j) instanceof IPlayer) {
					game.attack(possibleMoves.get(i), itemList.get(j));
					return;
				}

				// should handle pick up carrots
				if (itemList.get(j) instanceof Carrot) {
					game.move(possibleMoves.get(i));
					return;
				}
				
				// should handle pick up food
				if (itemList.get(j) instanceof Food) {
					game.move(possibleMoves.get(i));
					return;
				}

				// should makes sure no rabbits attack another rabbit
				if (itemList.get(j) instanceof Rabbit) {
					toRemove.add(possibleMoves.get(i));
				}

			}
		}
		
		possibleMoves.removeAll(toRemove);

		// Move random
		if (!possibleMoves.isEmpty()) {
			Collections.shuffle(possibleMoves);
			game.move(possibleMoves.get(0));
		}
	}

	@Override
	public boolean draw(ITurtle painter, double w, double h) {
		return false;
	}

	@Override
	public int getAttack() {
		return 10;
	}

	@Override
	public int getCurrentHealth() {
		return hp;
	}

	@Override
	public int getDamage() {
		return 10;
	}

	@Override
	public int getDefence() {
		return 5;
	}

	@Override
	public int getMaxHealth() {
		return 10;
	}

	@Override
	public String getName() {
		return "rabbit";
	}

	@Override
	public int getSize() {
		return 6;
	}

	@Override
	public String getSymbol() {
		return hp > 0 ? "🐇" : "¤";
	}

	@Override
	public int handleDamage(IGame game, IItem source, int amount) {
		hp -= amount;
		return amount;
	}

}
