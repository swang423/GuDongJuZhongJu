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
	 * @param id_first_player index of the first player
	 * @return the id of the last player
	 */
	public int start_a_round(int id_first_player) {
		return -1;
	}
	/**
	 * win-lose verdict
	 * @return true if the good guys win
	 */
	public boolean is_good_win() {
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		Board my_board = new Board();
		my_board.init();
		
	}

}
