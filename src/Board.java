/**
 * 
 */
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * @author Root
 *
 */
public class Board {
	public static final int NUM_PLAYERS = 8;
	public static final int NUM_ROUNDS = 3;
	private Player[] players = new Player[NUM_PLAYERS];
	private Round[] rounds = new Round[NUM_ROUNDS];
	public Board() {
	}
	
	/**
	 * Initialize the players and antiques
	 * @param args
	 * @return true if successful
	 */
	public boolean init() {
		Scanner queryBot = new Scanner( System.in );
		for (int i = 0 ; i < NUM_PLAYERS; i++) {
			System.out.println("Enter name for player #"+(i+1));
			String playerName = queryBot.next();
			players[i] = new Player(playerName);
		}
		for (int i = 0 ; i < NUM_ROUNDS; i++) {
			rounds[i] = new Round(i);
		}
		return false;
	}
	
	/**
	 * @param idRound, id of the round
	 * @param id_first_player index of the first player
	 * @return the id of the last player
	 */
	public int startRound(int idRound, int idFirstPlayer) {
		int nextPlayer = idFirstPlayer;
		for (int i = 0; i < NUM_PLAYERS; i++) {
			nextPlayer = rounds[idRound].actNext(nextPlayer);
		}
		return nextPlayer;
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
		System.out.println("TODO");
	}
	public void revealPlayerGroundTruth() {
		System.out.println("TODO");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Board my_board = new Board();
		my_board.init();
		// TODO get random player id to start
		int id_next_round_start = my_board.startRound(1,0);
		id_next_round_start = my_board.startRound(2,id_next_round_start);
		my_board.startRound(3,id_next_round_start);
		boolean is_good_win = my_board.isGoodWin();
		System.out.print("Congrats to ");
		System.out.print(is_good_win?"good":"bad");
		System.out.println(" guys for winning!");
	}

}
