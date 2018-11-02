package inf101.v18.rogue101.objects;

import inf101.v18.rogue101.game.IGame;

public class Emojis implements IItem {
	int hp;
	int currentHP;
	int defense;
	int attack;
	String name;
	int size;
	String symbol;
	
	int armor;
	int money;
	
	
	public Emojis(int hp, int defense, int attack, String name, int size, String symbol, int armor, int money) {
		this.hp = hp;
		currentHP = hp;
		this.defense = defense;
		this.attack = attack;
		this.name = name;
		this.size = size;
		this.symbol = symbol;
		this.armor = armor;
		this.money = money;
		
		changeColorOfEmoji();
	}
	
	private void changeColorOfEmoji() {
		if (symbol.equals("💙")) {
			symbol = "\u001B[31m" + symbol + "\u001B[0m";
		}
		else if (symbol.equals("🧀")) {
			symbol = "\u001B[33m" + symbol + "\u001B[0m";
		}
		else if (symbol.equals("🍄")) {
			symbol = "\u001B[31m" + symbol + "\u001B[0m";
		}
		else if (symbol.equals("🍙")) {
			symbol = "\u001B[47m" + "\u001B[30m" + symbol + "\u001B[0m";
		}
		else if (symbol.equals("🍩")) {
			symbol = "\u001B[47m" + "\u001B[30m" + symbol + "\u001B[0m";
		}
		else if (symbol.equals("🥕")) {
			symbol = "\u001B[47m" + "\u001B[31m" + symbol + "\u001B[0m";
		}
		else if (symbol.equals("🥗")) {
			symbol = "\u001B[92m" + symbol + "\u001B[0m";
		}
		else if (symbol.equals("🍉")) {
			symbol = "\u001B[31m" + symbol + "\u001B[0m";
		}
		else if (symbol.equals("🍌")) {
			symbol = "\u001B[33m" + symbol + "\u001B[0m";
		}
		else if (symbol.equals("🍆")) {
			symbol = "\u001B[35m" + symbol + "\u001B[0m";
		}
		else if (symbol.equals("🍦")) {
			symbol = "\u001B[47m" + "\u001B[30m" + symbol + "\u001B[0m";
		}
		else if (symbol.equals("🦀")) {
			symbol = "\u001B[31m" + symbol + "\u001B[0m";
		}
		else if (symbol.equals("🐷")) {
			symbol = "\u001B[31m" + symbol + "\u001B[0m";
		}
		else if (symbol.equals("🛸")) {
			symbol = "\u001B[34m" + symbol + "\u001B[0m";
		}
		else if (symbol.equals("🤡")) {
			symbol = "\u001B[36m" + symbol + "\u001B[0m";
		}
		else if (symbol.equals("👘")) {
			symbol = "\u001B[33m" + symbol + "\u001B[0m";
		}
		else if (symbol.equals("👑")) {
			symbol = "\u001B[33m" + symbol + "\u001B[0m";
		}
		else if (symbol.equals("🥇")) {
			symbol = "\u001B[46m" + "\u001B[33m" + symbol + "\u001B[0m";
		}
		
		else if (this instanceof Weapon || this instanceof Armor) {
			symbol = "\u001B[47m" + "\u001B[30m" + symbol + "\u001B[0m";
		}
		
	}


	@Override
	public int getCurrentHealth() {
		return currentHP;
	}

	@Override
	public int getDefence() {
		return defense;
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
		return symbol;
	}

	@Override
	public int handleDamage(IGame game, IItem source, int amount) {
		currentHP -= amount;

		if (currentHP < 0) {
			// we're all eaten!
			currentHP = -1;
		}
		return amount;
	}

}
