package inf101.v18.rogue101.objects;

import inf101.v18.rogue101.game.IGame;

public class Trophy implements IItem {

	@Override
	public int getCurrentHealth() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getDefence() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Trophy";
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 9;
	}

	@Override
	public String getSymbol() {
		// TODO Auto-generated method stub
		return "🥇";
	}

	@Override
	public int handleDamage(IGame game, IItem source, int amount) {
		// TODO Auto-generated method stub
		return 0;
	}

}
