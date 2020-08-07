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
			//we fake the query process even if it always return false
			int antiqueID = -1;
			while(true) {
				System.out.println("Enter id of the antique to check [1-4]:");
				try {
					antiqueID =  Integer.parseInt(queryBot.nextLine())-1;
				}catch (NumberFormatException e) {
					continue;
				}
				if(antiqueID<0 || antiqueID>=NUM_ANTIQUES) {
					System.out.println("Illegal id:"+(antiqueID+1)+". Try again.");
					continue;
				}
				System.out.println("[y] to confirm,[n] to redo");
				String confirm = queryBot.nextLine();
				if(confirm.equals("y"))	break;
			}
			System.out.println(HIDDEN_MSG);
			return false;
		}
		return true;
	}
	private int freeze_round;
	public static final int NUM_ROUNDS = 3;
}
