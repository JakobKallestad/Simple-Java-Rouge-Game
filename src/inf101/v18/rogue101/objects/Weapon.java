package inf101.v18.rogue101.objects;

public class Weapon extends Emojis {

	public Weapon(int hp, int defense, int attack, String name, int size, String symbol, int armor, int money) {
		super(hp, defense, attack, name, size, symbol, armor, money);
		// TODO Auto-generated constructor stub
	}
	
	public int wieldWeapon() {
		return attack;
	}

}
