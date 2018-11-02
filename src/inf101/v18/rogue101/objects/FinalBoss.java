package inf101.v18.rogue101.objects;


public class FinalBoss extends Enemy implements IActor, INonPlayer {

	public FinalBoss() {
		super(1000, 150, 400, "Boss", 30, "\u001b[41m" + "\u001b[30m" + "😈" + "\u001b[0m", 50, 0);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getDamage() {
		return 200;
	}
	
}
