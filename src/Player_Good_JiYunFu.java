/**
 * JiYunFu always gets the real identity 
 * not affected by LaoChaoFeng
 * So we use her special skill as the ordinary inspect
 * and disallow her to use ordinary inspect (always return false)
 * instead of overwrite her ordinary inspect
 * Once she has been attacked, her skill is disabled
 */

/**
 * @author Root
 *
 */
public class Player_Good_JiYunFu extends Player{
	public Player_Good_JiYunFu(int id) {
		super(id);
		this.isGood = true;
	}
	/**
	 * In the following code, JiYunFu is not affected by ZhenGuoQu either
	 */
	public boolean act_special_skill(Round round,Player[] players) {
		if(this.isDisabled) {
			//TODO not sure what to output
			System.out.println(ATTACK_MSG);
			return false;
		}
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
		//if(chosen.isHidden()) {
		//	System.out.println(HIDDEN_MSG);
		//}else {
			boolean revealTruth = checkAntique(chosen);
			System.out.println("The antique "+chosen.getName()
				+" is "+(revealTruth?"Real.":"Fake."));
		//}
		return false;
	}
	protected boolean checkAntique(Antique choice) {
		return choice.getIdentity();
	}
	//cannot be reset
	public void setAttack(boolean attack) {
		if(attack)
			this.isDisabled = true;
	}
	private boolean isDisabled = false;
}
