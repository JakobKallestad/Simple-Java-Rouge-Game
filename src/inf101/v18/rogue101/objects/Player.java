package inf101.v18.rogue101.objects;

import java.util.List;
import inf101.v18.grid.GridDirection;
import inf101.v18.grid.ILocation;
import inf101.v18.rogue101.game.IGame;
import javafx.scene.input.KeyCode;

public class Player implements IPlayer {
	private int hp = 100;
	private int currentHP = hp;
	private int defense = 10;
	private int attack = 10;
	private int damage = 15;
	private String name = "Jakob";
	private int size = 10;
	private String symbol = "😃";
	private int slainEnemies = 0;
	private int level = 1;

	public Container<Food> food = new Container<Food>(200, 100);
	public Container<Armor> armor = new Container<Armor>(100, 6);
	public Container<Weapon> weapon = new Container<Weapon>(100, 2);

	public void getEXP() {
		slainEnemies++;
		checkLevelUp();
	}

	public int getLevel() {
		return level;
	}

	public void checkLevelUp() {
		if (slainEnemies > level * 2) {
			level++;
		}
	}

	@Override
	public int getAttack() {
		return attack + weapon.getAllWeapon() + (3 * level);
	}

	@Override
	public int getDamage() {
		return damage + weapon.getAllWeapon() + (3 * level);
	}

	@Override
	public int getCurrentHealth() {
		return currentHP;
	}

	@Override
	public int getDefence() {
		return defense + armor.getAllArmor() + 3 * level;
	}

	@Override
	public int getMaxHealth() {
		return hp;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public String getSymbol() {
		return "\u001b[93m" + symbol + "\u001b[0m";
	}

	public void setSymbol(String newSymbol) {
		symbol = newSymbol;
	}

	@Override
	public int handleDamage(IGame game, IItem source, int amount) {
		amount -= armor.getAllArmor();
		if (amount < 0) {
			amount = 0;
		}
		currentHP -= amount;
		return amount;
	}

	@Override
	public void keyPressed(IGame game, KeyCode key) {

		changeSymbol();
		game.displayPlayerInfo();

		if (key == KeyCode.LEFT) {
			tryToMove(game, GridDirection.WEST);
		} else if (key == KeyCode.RIGHT) {
			tryToMove(game, GridDirection.EAST);
		} else if (key == KeyCode.UP) {
			tryToMove(game, GridDirection.NORTH);
		} else if (key == KeyCode.DOWN) {
			tryToMove(game, GridDirection.SOUTH);
		} else if (key == KeyCode.D) {
			food.dropSomething(game);
		} else if (key == KeyCode.P) {
			pickUp(game);
		} else if (key == KeyCode.E) {
			eat(game);
		} else if (key == KeyCode.W) {
			weapon.dropSomething(game);
		} else if (key == KeyCode.A) {
			armor.dropSomething(game);
		}
		showStatus(game);
	}

	private void changeSymbol() {
		if (food.nElements() == 30) {
			setSymbol("😍"); // easter egg!
		} else if (armor.containsSunglasses()) {
			setSymbol("😎");
		} else if (currentHP > 80) {
			setSymbol("😃");
		} else if (currentHP > 60) {
			setSymbol("😓");
		} else if (currentHP > 40) {
			setSymbol("😧");
		} else if (currentHP > 20) {
			setSymbol("😭");
		} else if (currentHP > 10) {
			setSymbol("😨");
		} else if (currentHP < 10) {
			setSymbol("🌝");
		}
	}

	private void eat(IGame game) {
		Food eatable = food.takeOut();
		if (eatable != null) {
			game.displayMessage("Ate a delicious " + eatable.getName());
			currentHP += eatable.getCurrentHealth();
		}
	}

	private void pickUp(IGame game) {
		List<IItem> localItems = game.getLocalItems();
		if (!localItems.isEmpty()) {
			for (IItem item : localItems) {
				IItem theItem = game.pickUp(item);
				if (theItem instanceof Food) {
					food.add((Food) theItem);
				}

				if (theItem instanceof Armor) {
					armor.add((Armor) theItem);
				}

				if (theItem instanceof Weapon) {
					weapon.add((Weapon) theItem);
				}

				if (theItem instanceof Trophy) {
					game.displayMessage(
							"You picked up a beautiful medal and feel proud that you were able to win the game");
					setSymbol("😉");
				}

				if (theItem != null && !(theItem instanceof Dust)) {
					game.displayMessage("picked up " + theItem.getName());
				}

			}
		}
	}

	public void tryToMove(IGame game, GridDirection dir) {
		if (game.canGo(dir)) {

			ILocation loc = game.getLocation(dir);
			List<IItem> itemList = game.getMap().getItems(loc);
			for (IItem item : itemList) {
				if (item instanceof IActor) {
					game.attack(dir, item);
					return;
				}
			}

			// else move
			game.move(dir);
		}

		// else nothing
	}

	public void showStatus(IGame game) {
		String status = "HP: " + getCurrentHealth() + "/" + getMaxHealth();
		game.displayStatus(status);
	}

}
