/**
 * 
 */
import java.io.File;
import java.util.ArrayList;
/**
 * @author Root
 *
 */
public class Board {

	public Board() {
		//TODO players
		//TODO rounds
		//TODO antiques
	}
	
	/**
	 * Initialize the players and antiques
	 * @param args
	 * @return true if successful
	 */
	public boolean init() {
		
		return false;
	}
	
	/**
	 * @param idRound, id of the round
	 * @param id_first_player index of the first player
	 * @return the id of the last player
	 */
	public int startRound(int idRound, int idFirstPlayer) {
		Round myRound = new Round(idRound);
		return -1;
	}
	/**
	 * win-lose verdict
	 * @return true if the good guys win
	 */
	public boolean isGoodWin() {
		return false;
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
