package inf101.v18.rogue101.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import inf101.v18.grid.GridDirection;
import inf101.v18.grid.ILocation;
import inf101.v18.rogue101.examples.Carrot;
import inf101.v18.rogue101.examples.Rabbit;
import inf101.v18.rogue101.game.IGame;

public class Enemy extends Emojis implements IActor, INonPlayer {

	public Enemy(int hp, int defense, int attack, String name, int size, String symbol, int armor, int money) {
		super(hp, defense, attack, name, size, symbol, armor, money);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getAttack() {
		return attack;
	}

	@Override
	public int getDamage() {
		return attack;
	}

	@Override
	public void doTurn(IGame game) {
		
		// TODO Auto-generated method stub
		List<GridDirection> possibleMoves = game.getPossibleMoves();
		List<GridDirection> toRemove = new ArrayList<>();

		
		for (int i = 0; i < possibleMoves.size(); i++) {
			ILocation loc = game.getLocation(possibleMoves.get(i));
			List<IItem> itemList = game.getMap().getItems(loc);
			for (int j = 0; j < itemList.size(); j++) {

				// should handle damage, only attacks players.
				if (itemList.get(j) instanceof IPlayer) {
					game.attack(possibleMoves.get(i), itemList.get(j));
					return;
				}

				//should not move to somewhere there is an actor.
				if (itemList.get(j) instanceof INonPlayer) {
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

}
