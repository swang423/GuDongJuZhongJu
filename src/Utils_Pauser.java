/**
 * 
 */
import java.util.Scanner;
/**
 * @author mydel
 *
 */
public class Utils_Pauser {
	public Utils_Pauser() {
		queryBot = new Scanner( System.in );
	}
	public void pause() {
		System.out.println("Press Enter when ready");
		queryBot.nextLine();
	}
	public int getNextInt() {
		int p = -1;
		while(true) {
			try {
				p = Integer.parseInt(queryBot.nextLine());
			}catch(NumberFormatException e) {
				continue;
			}
			break;
		}
		return p;
	}
	public void fakeClear() {
		for (int j = 0 ; j < NUM_SPACES; j++)
			System.out.println();
	}
	private Scanner queryBot;
	public static final int NUM_SPACES = 10;	//insert spaces between responses
}
