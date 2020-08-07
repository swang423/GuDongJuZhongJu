/**
 * 
 */
import java.util.Random;
import java.util.Arrays;
/**
 * @author Root
 *
 */
public class Round {
	public Round(int id, int numPlayer) {
		assert id >= 1 && id <= 3 : "id between 1 and 3";
		this.id = id;
		this.numPlayer = numPlayer;
		this.player_seq = new int[numPlayer];
		for(int i = 0; i < numPlayer; i++)
			this.player_seq[i] = -1;
		//set identity of antiques in this round;
		this.initTable(this.id);
		//TODO
		//save state to log
	}

	/**
	 * Bad guy 1 LaoChaoFeng reverse the identity showing to good guys
	 */
	public void reverseTable() {
		for(int i = 0; i < NUM_ANTIQUES; i++) 
			table[i].reverseShow();	
	}
	public int getID() {
		return this.id;
	}
	public Antique getAntique(int i) {
		assert id >= 0 && id <= 3 : "Antique id between 0 and 3";
		return table[i];
	}
	public boolean hasActed(int playerID) {
		for(int i = 0; i < numPlayer; i++) {
			if(player_seq[i]==playerID)	return true;
		}
		return false;
	}
	/**
	 * init antique identity
	 */
	private void initTable(int id) {
		String tag;
		switch(id) {
		case 1: tag = "J"; break;
		case 2: tag = "Q"; break;
		case 3: tag = "K"; break;
		default: 
			throw new IllegalArgumentException("id must be between 1 and 3");
		}
		//get two random assignment for real
		//the others default to fake
		Random random = new Random();
		boolean[] iden = {true,true,false,false};
		for (int i = 0; i<NUM_ANTIQUES;i++) {
			int randomIndexToSwap = random.nextInt(NUM_ANTIQUES);
			boolean tmp = iden[randomIndexToSwap];
			iden[randomIndexToSwap]=iden[i];
			iden[i]=tmp;
		}
		table[0] = new Antique(tag,"c",iden[0]);
		table[1] = new Antique(tag,"d",iden[1]);
		table[2] = new Antique(tag,"h",iden[2]);
		table[3] = new Antique(tag,"s",iden[3]);
		
		
		/*
		int realA = random.nextInt(NUM_ANTIQUES) ;
		int realB;
		do {
			realB = random.nextInt(NUM_ANTIQUES);
		}while(realA == realB);
		assert realA != realB;
		//assert 0 <= realA && realA < NUM_ANTIQUES;
		//assert 0 <= realB && realB < NUM_ANTIQUES;
		table[realA].setIdentity(true);
		table[realB].setIdentity(true);
		*/
	}
	//debug method to display table
	public void printSeq() {
		for(int i = 0 ; i < numPlayer;i++) {
			System.out.print(this.player_seq[i]+" ");
		}
		System.out.println();
	}
	public void printTable() {
		for(int i = 0 ; i < NUM_ANTIQUES;i++) {
			System.out.print(table[i].getName()+" is "+table[i].getIdentity());
			System.out.println(". It is"+(table[i].isHidden()?" ":" not "+"hidden."));
		}
	}
	public void printVotes() {
		for(int i = 0 ; i < NUM_ANTIQUES;i++) {
			System.out.print(table[i].getName()+" received "+vote[i]+" votes.");
		}
	}
	public void updateSeq(int order, int playerID) {
		this.player_seq[order] = playerID;
	}
	public int getLastPlayer() {
		return this.player_seq[this.player_seq.length-1];
	}
	public void updateVote(int[] vote) {
		assert(vote.length==NUM_ANTIQUES);
		for(int i=0; i < NUM_ANTIQUES; i++)
			this.vote[i] += vote[i];
	}
	public int getNumPlayer() {
		return this.numPlayer;
	}
	/**
	 * called after voting to tally score for good guys
	 * when equal votes, pick the first one
	 * @return 2 if both top two voted are real, 1 if only one of top two is real 
	 */
	public int getScore() {
		int score = 0;
		Arrays.sort(this.vote);
		int idx_1 = 0,idx_2 = 0;
		int val_1 = vote[0],val_2 = vote[0];
		for (int i = 1; i < NUM_ANTIQUES; i++) {
			if(vote[i]>val_1) {
				val_2 = val_1;
				idx_2 = idx_1;
				val_1 = vote[i];
				idx_1 = i;
			}else if(vote[i]>val_2) {
				val_2 = vote[i];
				idx_2 = i;
			}
		}
		if(table[idx_1].getIdentity())
			score ++;
		if(table[idx_2].getIdentity())
			score ++;
		System.out.print("The final votes are:\n[ ");
		for (int i = 0 ; i<NUM_ANTIQUES; i++)
			System.out.print(vote[i]+" ");
		System.out.println("].");
		System.out.println("["+table[idx_2].getName()+"] receives the second highest votes of "+val_2 +" coins");
		System.out.println("["+table[idx_2].getName()+"] is "+table[idx_2].getIdentity());
		System.out.println("-- End of round "+id+" --");
		return score;
	}
	
	private int numPlayer = 0;
	private int[] player_seq; 
	public static final int NUM_ANTIQUES = 4;
	private int id;
	//private int acted = 0; //# of players acted in this round
	private Antique[] table = new Antique[NUM_ANTIQUES];	//list of 4 antinques this round
	private int[] vote = {0,0,0,0}; //how many coins each antique receives
}
