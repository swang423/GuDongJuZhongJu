
public class Player_Good_FangZhen extends Player{
	public Player_Good_FangZhen(int id) {
		super(id);
		this.isGood = true;
	}
	/**
	 * FangZhen doesn't act on round but on players
	 */
	public boolean act_special_skill(Round round,Player[] players) {
		int num_player = round.getNumPlayer();
		int playerID;
		while(true) {
			System.out.println("Enter ID for the player to probe:");
			playerID = Integer.parseInt(queryBot.nextLine());
			if(playerID<0 || playerID>num_player){
				System.out.println("Illegal player id:"+playerID+". Try again.");
				continue;
			}
			System.out.println("[y] to confirm,[n] to redo");
			String confirm = queryBot.nextLine();
			if(confirm.equals("y"))	break;
		}
		//get person's identity
		boolean playerIsGood = players[playerID].getCamp();
		System.out.println("Player ["+playerID+"] is "+(playerIsGood?"good":"bad"));
		return false;
	}

}
