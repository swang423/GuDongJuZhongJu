/**
 * 
 */

/**
 * @author Root
 *
 */
public class Player_Bad_YaoBuRan extends Player{

	public Player_Bad_YaoBuRan(int id) {
		// TODO Auto-generated constructor stub
		super(id);
		this.isGood = false;
	}
	/**
	 * He attacks a player. If he attacks FangZhen, 
	 * XuYuan is also attacked
	 */
	public boolean act_special_skill(Round round,Player[] players) {
		int num_player = round.getNumPlayer();
		int playerID = -1;
		while(true) {
			System.out.println("Enter ID for the player to attack [1-"+num_player+"]:");
			try {
				playerID = Integer.parseInt(queryBot.nextLine())-1;
			}catch (NumberFormatException e) {
				continue;
			}
			if(playerID<0 || playerID>=num_player){
				System.out.println("Illegal player id:"+(playerID+1)+". Try again.");
				continue;
			}
			System.out.println("[y] to confirm,[n] to redo");
			String confirm = queryBot.nextLine();
			if(confirm.equals("y"))	break;
		}
		players[playerID].setAttack(true);
		if(players[playerID].getRole().equals(GameRole.FangZhen)){
			for(int ii = 0; ii < players.length;ii++) {
				if(players[ii].getRole().equals(GameRole.XuYuan)){
					players[ii].setAttack(true);
					break;
				}
			}
		}
		return true;
	}
}
