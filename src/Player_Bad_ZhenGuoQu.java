/**
 * 
 */

/**
 * @author Root
 *
 */
public class Player_Bad_ZhenGuoQu extends Player{

	/**
	 * 
	 */
	public Player_Bad_ZhenGuoQu(int id) {
		// TODO Auto-generated constructor stub
		super(id);
		this.isGood = false;
	}
	/**
	 * He hides an antique
	 */
	public boolean act_special_skill(Round round,Player[] players) {
		int antiqueID;
		while(true) {
			System.out.println("Enter id of the antique to hide [0-3]:");
			antiqueID =  Integer.parseInt(queryBot.nextLine());
			if(antiqueID<0 || antiqueID>3) {
				System.out.println("Illegal id:"+antiqueID+". Try again.");
				continue;
			}
			System.out.println("[y] to confirm,[n] to redo");
			String confirm = queryBot.nextLine();
			if(confirm.equals("y"))	break;
		}
		
		Antique chosen = round.getAntique(antiqueID);
		chosen.hide();
		System.out.println("The antique "+chosen.getName()
			+" has been hidden.");
		
		return true;
	}
}
//TODO: currently ZhenGuoQu hides an antique first.
//If he checks the antique he just hid, he won't get a response, wasting his chances
//Not sure if this is intended
