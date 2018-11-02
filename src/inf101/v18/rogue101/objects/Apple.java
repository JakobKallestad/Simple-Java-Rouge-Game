package inf101.v18.rogue101.objects;

import inf101.v18.gfx.gfxmode.ITurtle;
import inf101.v18.rogue101.game.IGame;
import inf101.v18.rogue101.objects.IItem;
import javafx.scene.paint.Color;

public class Apple implements IItem {
	private int hp = getMaxHealth();

	public void doTurn(IGame game) {
		hp = Math.min(hp + 1, getMaxHealth());
	}

	@Override
	public boolean draw(ITurtle painter, double w, double h) {
		return false;
	}

	@Override
	public int getCurrentHealth() {
		return hp;
	}

	@Override
	public int getDefence() {
		return 0;
	}

	@Override
	public double getHealthStatus() {
		return getCurrentHealth() / getMaxHealth();
	}

	@Override
	public int getMaxHealth() {
		return 10;
	}

	@Override
	public String getName() {
		return "apple";
	}

	@Override
	public int getSize() {
		return 5;
	}

	@Override
	public String getSymbol() {
		return "🍎"; //"\u1F34E";
	}

	@Override
	public int handleDamage(IGame game, IItem source, int amount) {
		hp -= amount;

		if (hp < 0) {
			// we're all eaten!
			hp = -1;
		}
		return amount;
	}

}
