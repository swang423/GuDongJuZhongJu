/**
 * 
 */

/**
 * @author Root
 *
 */
public class Player_Bad_LaoChaoFeng extends Player {
	
	public Player_Bad_LaoChaoFeng(int id) {
		super(id);
		this.isGood = false;
	}
	
	/**
	 * She changes the appearance of all antiques to good guys
	 */
	public boolean act_special_skill(Round round,Player[] players) {
		
		System.out.println("Do you wish to reverse the table?");
		System.out.println("[y] to confirm,[n] to do nothing");
		String confirm = queryBot.nextLine();
		if(confirm.equals("y"))	
			round.reverseTable();
		return true;
	}
}
