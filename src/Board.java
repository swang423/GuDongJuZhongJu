/**
 * 
 */
import java.io.File;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Sicheng Wang @GT
 *
 */
public class Board {
	public static final int NUM_PLAYERS = 6;
	public static final int NUM_ROUNDS = 3;
	private Player[] players = new Player[NUM_PLAYERS];
	private Round[] rounds = new Round[NUM_ROUNDS];
	public Board() {
	}
	
	/**
	 * Initialize the players and tables of antiques (called rounds)
	 * @param args
	 * @return true if successful
	 */
	public boolean init() {
		//Scanner queryBot = new Scanner( System.in );
		GameRole[] roles = GameRole.getRandomRoles(NUM_PLAYERS);
		
		int id_bad1_LaoChaoFeng=-1, id_bad2_YaoBuRan = -1;
		for (int i = 0 ; i < NUM_PLAYERS; i++) {
			//System.out.println("Enter name for player #"+(i+1));
			//String playerName = queryBot.next();
			switch(roles[i]) {
			case XuYuan:
				players[i] = new Player_Good_XuYuan(i);
				break;
			case FangZhen:
				players[i] = new Player_Good_FangZhen(i);
				break;
			case JiYunFu:
				players[i] = new Player_Good_JiYunFu(i);
				break;
			case MuHuJiaNai:
				players[i] = new Player_Good_MuHuJiaNai(i);
				break;
			case HuangYanYan:
				players[i] = new Player_Good_HuangYanYan(i);
				break;
			case LaoChaoFeng:
				players[i] = new Player_Bad_LaoChaoFeng(i);
				break;
			case YaoBuRan:
				players[i] = new Player_Bad_YaoBuRan(i);
				break;
			case ZhenGuoQu:
				players[i] = new Player_Bad_ZhenGuoQu(i);
				break;
			default: 
				throw new IllegalArgumentException("GameRole not recognized.");
			}
			if(roles[i] == GameRole.LaoChaoFeng)
				id_bad1_LaoChaoFeng = i+1;
			else if(roles[i] == GameRole.YaoBuRan)
				id_bad2_YaoBuRan = i+1;
			players[i].assignRole(roles[i]);
		}
		assert(id_bad1_LaoChaoFeng>0);
		assert(id_bad2_YaoBuRan>0);
		System.out.print("Please inform player ["+ id_bad1_LaoChaoFeng);
		System.out.println("] and ["+id_bad2_YaoBuRan+"] are teammates.");
		for (int i = 0 ; i < NUM_ROUNDS; i++) {
			rounds[i] = new Round(i+1,NUM_PLAYERS);
			//TODO debug
			rounds[i].printTable();
		}
		//save log
		revealPlayerGroundTruth();
		return false;
	}
	
	/**
	 * In each round, players take turns to investigate
	 * All players except the last always chooses the next player 
	 * Before doing this, he/she may use his personal skills 
	 * as well as inspect an antique if he/she is not attacked
	 * After all the players have acted, there will be a discussion
	 * after which voting starts.
	 * The identity of the antique with the second highets vote 
	 * shall be revealed. 
	 * @param idRound, id of the round
	 * @param id_first_player index of the first player
	 * @return the id of the last player
	 */
	public int startRound(int idRound, int idFirstPlayer) {
		int nextPlayer = idFirstPlayer;
		for (int i = 0; i < NUM_PLAYERS; i++) {
			//nextPlayer = rounds[idRound].actNext(nextPlayer);
			rounds[idRound].updateSeq(i,nextPlayer);
			System.out.println("-----------------------");
			System.out.println("You are "+players[nextPlayer].getRole());
			if(!players[nextPlayer].isAttacked()) {
				players[nextPlayer].setAttack(false);
				boolean can_check_antique = players[nextPlayer].act_special_skill(rounds[idRound],players);
				if(can_check_antique)
					players[nextPlayer].act_check_antique(rounds[idRound]);
			}
			if(i<NUM_PLAYERS-1)
				nextPlayer = players[nextPlayer].act_choose_next_player(rounds[idRound]);
		}
		System.out.println("Action sequence:");
		rounds[idRound].printSeq();
		//start discussion from the left of the last player
		int firstSpeaker = rounds[idRound].getLastPlayer();
		if(firstSpeaker == 0)
			firstSpeaker = NUM_PLAYERS;
		System.out.println("Please begin discussion from "+firstSpeaker);
		System.out.println("Get ready to vote!");
		for (int i = 0; i < NUM_PLAYERS; i++) {
			//nextPlayer = rounds[idRound].actNext(nextPlayer);
			int [] vote = players[i].vote();
			rounds[idRound].updateVote(vote);
		}
		rounds[idRound].getScore();
		return rounds[idRound].getLastPlayer();
		
		
	}
	/**
	 * win-lose verdict
	 * @return true if the good guys win
	 */
	public boolean isGoodWin() {
		return false;
	}
	/**
	 * debug method or called at the end to reveal the groundtruth of all antiques
	 */
	public void revealAntiqueGroundTruth() {
		for(int i = 0 ; i<NUM_ROUNDS; i++)
			rounds[i].printTable();
	}
	public void revealPlayerGroundTruth() {
		for(int i = 0 ; i<NUM_PLAYERS; i++)
			System.out.println("Player "+(i+1)+" is "+players[i].getRole());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random random = new Random();
		Board my_board = new Board();
		my_board.init();
		// TODO get random player id to start
		int id_next_round_start = random.nextInt(NUM_PLAYERS);
		for(int i = 0 ; i < NUM_ROUNDS; i++) {
			System.out.print("The first player to play in round[");
			System.out.println((i+1)+"] is ["+(id_next_round_start+1)+"].");
			id_next_round_start = my_board.startRound(i,id_next_round_start);
		}
		boolean is_good_win = my_board.isGoodWin();
		System.out.print("Congrats to ");
		System.out.print(is_good_win?"good":"bad");
		System.out.println(" guys for winning!");
	}

}
