import java.util.Scanner;
import java.io.IOException;

public class Player {
	public Player(int id) {
		this.myID = id;
	}
	public void setCamp(boolean isGood) {
		this.isGood = isGood;
	}
	public void assignRole(GameRole role) {
		this.myRole = role;
	}
	public GameRole getRole() {
		return this.myRole;
	}
	public boolean getCamp() {
		return this.isGood;
	}
	/**
	 * This is a method all roles inherit and do not modify
	 * It asks for an ID of the antique to inspect [0-3]
	 * and shows the identity to the player depending on his/her camp
	 * @param round: the id of the table, [0,1,2]
	 */
	public void act_check_antique(Round round) {
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
		
		Antique chosen = round.getAntique(antiqueID);
		if(chosen.isHidden()) {
			System.out.println(HIDDEN_MSG);
		}else {
			boolean revealTruth = checkAntique(chosen);
			System.out.println("The antique "+chosen.getName()
				+" is "+(revealTruth?"Real.":"Fake."));
		}
	}
	/**
	 * This is to be modified by each role
	 * @param 
	 * @param players: list of all players objects
	 * @return
	 */
	public boolean act_special_skill(Round round,Player[] players) {
		return false;
	}
	/**
	 * This is called at the end of each player's action
	 * It simply asks for which  player to act next
	 * In the announcement, we used (1-based) indexing
	 * @param num_player
	 * @return the id of the player to play next (0-based)
	 */
	public int act_choose_next_player(Round round) {
		int playerID = -1;
		int num_players = round.getNumPlayer();
		while(true) {
			System.out.println("Enter ID for the next player # [1-"+num_players+"]:");
			try {
				playerID = Integer.parseInt(queryBot.nextLine())-1;
			}catch (NumberFormatException e) {
				continue;
			}
			if(playerID<0 || playerID>=num_players){
				System.out.println("Illegal player id:"+(playerID+1)+". Try again.");
				continue;
			}
			if (round.hasActed(playerID)) {
				System.out.println("Player id "+(playerID+1)+" has acted.");
				continue;
			}
			System.out.println("[y] to confirm,[n] to redo");
			String confirm = queryBot.nextLine();
			if(confirm.equals("y"))	break;
		}
		return playerID;
	}
	/**
	 * This is called when each player's vote is tallied
	 * @return
	 */
	public int[] vote()  {
		this.coins += 2;
		int[] res = {0,0,0,0};
		while(true) {
			int sum = 0;
			for (int i = 0; i<NUM_ANTIQUES; i++)
				res[i] = 0;
			/*
			for(int i = 0; i<NUM_ANTIQUES;i++) {
				System.out.println("Please enter the vote of player ["+this.myID+"] on antique ["+i+"] as one by one #. ");
				res[i] = Integer.parseInt(queryBot.nextLine());
				sum += res[i];
			}
			*/
			System.out.println("Please enter the vote of player ["+(this.myID+1)+"] on as four integers:");
			System.out.println("Press enter to vote nothing");
			String votes_str = queryBot.nextLine();
			if(votes_str.equals("")) 
				return res;
				
			String[] votes_vec = votes_str.split(" ");
			if(votes_vec.length!=NUM_ANTIQUES) {
				//throw new IncorrectVotesException(votes_str);
				System.out.println("Your response is not recorded. Enter again");
				System.out.println("Format it as 0 0 0 0");
				continue;
			}
			boolean negative_vote = false;
			for (int i=0; i<NUM_ANTIQUES;i++) {
				res[i] = Integer.parseInt(votes_vec[i]);
				if(res[i] < 0) {
					negative_vote = true;
					break;
				}
				sum += res[i];
			}
			if(negative_vote) {
				System.out.print("Error: You can't vote with negative numbers.");
				continue;
			}
			if(sum>this.coins) {
				System.out.print("Error: You only have "+this.coins+" coins left");
				System.out.println("but you wish to vote with "+sum+ " coins.");
				continue;
			}
			System.out.print("You have entered [");
			for(int i = 0; i<NUM_ANTIQUES;i++) {
				System.out.print(res[i]+" ");
			}
			System.out.println("] for player "+(this.myID+1)+".");
			System.out.println("Please enter [y] to confirm, [n] to redo");
			if(queryBot.nextLine().equals("y")) {
				this.coins -= sum;
				break;
			}
		}
		return res;
	}
	//When YaoBuRan attack this player
	//Also used for reset afterwards
	public void setAttack(boolean attack) {
		isAttacked = attack;
	}
	public boolean isAttacked() {
		if(this.isAttacked) {
			System.out.println(ATTACK_MSG);
			return true;
		}else{
			return false;
		}
	}
	protected boolean checkAntique(Antique choice) {
		if(this.isGood) {
			return choice.getShow();
		}else {	//bad guy always get real identity
			return choice.getIdentity();
		}
	}
	public static final String ATTACK_MSG = "You have been attacked";
	public static final String HIDDEN_MSG = "This antique cannot be inspected.";
	protected Scanner queryBot = new Scanner( System.in );
	private boolean isAttacked = false;
	protected boolean isGood = false;	//good or bad
	private int myID;
	private int coins = 0;
	private GameRole myRole;
	public static final int NUM_ANTIQUES = 4;
}
