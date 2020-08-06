/**
 * 
 */
import java.util.Random;
import java.util.ArrayList;
/**
 * @author Root
 *
 */
public class Round {
	public Round(int id) {
		assert id >= 1 && id <= 3 : "id between 1 and 3";
		this.id = id;
		//set identity of antiques in this round;
		this.initTable(this.id);
	}
	public int actNext(int idPlayer) {
		return -1;
	}
	/**
	 * Bad guy 1 LaoChaoFeng reverse the identity showing to good guys
	 */
	public void actBad1LaoChaoFeng() {
		for(int i = 0; i < NUM_ANTIQUES; i++) {
			boolean identity = table[i].getIdentity();
			table[i].setShow(!identity);
		}
	}
	/**
	 * init antique identity
	 */
	private void initTable(int id) {
		//TODO let random
		String tag;
		switch(id) {
		case 1: tag = "J"; break;
		case 2: tag = "Q"; break;
		case 3: tag = "K"; break;
		default: 
			throw new IllegalArgumentException("id must be between 1 and 3");
		}
		table[0] = new Antique(tag,"c",false);
		table[1] = new Antique(tag,"d",false);
		table[2] = new Antique(tag,"h",false);
		table[3] = new Antique(tag,"s",false);
		//get two random assignment for real
		//the others default to fake
		Random random = new Random();
		int realA = random.nextInt(NUM_ANTIQUES) ;
		int realB;
		do {
			realB = random.nextInt(NUM_ANTIQUES);
		}while(realA == realB);
		assert realA != realB;
		//assert 0 <= realA && realA < NUM_ANTIQUES;
		//assert 0 <= realB && realB < NUM_ANTIQUES;
		table[realA].setIdentity(false);
		table[realB].setIdentity(false);
	}
	/**
	 * called after voting to tally score for good guys
	 * when equal votes, pick randomly
	 * @return 2 if both top two voted are real, 1 if only one of top two is real 
	 */
	public int getFinalScore() {
		int score = 0;
		int idxSecondHighest = -1;
		ArrayList<Integer> max_idx = getHighestInList(vote);
		if(max_idx.size()>=2) { //if more than two highest, get random to score and reveal
			//TODO
		}else if(max_idx.size()==1) {//if exactly one, then get next
			int idxFirstHighest = max_idx.get(0);
			score += table[idxFirstHighest].getIdentity()?1:0;
			ArrayList<Integer> second_max_idx = getSecondHighestInList(vote,vote[idxFirstHighest]);
			//TODO
		}else {//if none
			throw new RuntimeException("Expect to find at least 1 highest.");
		}
		
		System.out.println("[] receives the second highest votes of ? coins");
		System.out.println("[] is ?");
		System.out.println("-- End of round "+id+" --");
		return score;
	}
	/**
	 * @param list int array of non-negative values;
	 * @return id of the largest element, -1 if multiple maxima are found
	 */
	private static ArrayList<Integer> getHighestInList(int[] list) {
		int max = 0;
		ArrayList<Integer> max_idx = new ArrayList<Integer>();
		//get max value
		for (int i = 0 ; i<NUM_ANTIQUES; i++) {
			if(list[i]>max) {
				max = list[i];
			}
		}
		for (int i = 0 ; i<NUM_ANTIQUES; i++) {
			if(list[i] == max){
				max_idx.add(i);
			}
		}
		return max_idx;
	}
	private static ArrayList<Integer> getSecondHighestInList(int[] list,int max) {
		int secondMax = -1;
		ArrayList<Integer> max_idx = new ArrayList<Integer>();
		//get max value
		for (int i = 0 ; i<NUM_ANTIQUES; i++) {
			if(list[i]>secondMax && list[i]<max) {
				secondMax = list[i];
			}
		}
		for (int i = 0 ; i<NUM_ANTIQUES; i++) {
			if(list[i] == secondMax)
				max_idx.add(i);
		}
		return max_idx;
	}
	public static final int NUM_ANTIQUES = 4;
	private int id;
	private int acted = 0; //# of players acted in this round
	private Antique[] table = new Antique[NUM_ANTIQUES];	//list of 4 antinques this round
	private int[] vote = {0,0,0,0}; //how many coins each antique receives
}
