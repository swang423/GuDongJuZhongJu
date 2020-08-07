import java.util.Random;
public class Player_Good_HuangYanYan extends Player{

	public Player_Good_HuangYanYan(int id) {
		super(id);
		this.isGood = true;
		Random rnd = new Random();
		this.freeze_round = rnd.nextInt(NUM_ROUNDS);
	}
	public boolean act_special_skill(Round round,Player[] players) {
		if(round.getID() == freeze_round) {
			System.out.println(HIDDEN_MSG);
			return false;
		}
		return true;
	}
	private int freeze_round;
	public static final int NUM_ROUNDS = 3;
}
