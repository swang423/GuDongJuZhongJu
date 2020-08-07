public class Player_Good_XuYuan extends Player{
	public Player_Good_XuYuan(int id) {	
		super(id);
		this.isGood = true;
	}
	/**
	 * We do it one more time for XuYuan
	 */
	public boolean act_special_skill(Round round,Player[] players) {
		int antiqueID;
		while(true) {
			System.out.println("Enter id of the antique to check [0-3]:");
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
		if(chosen.isHidden()) {
			System.out.println("This antique cannot be inspected.");
		}else {
			boolean revealTruth = checkAntique(chosen);
			System.out.println("The antique "+chosen.getName()
				+" is "+(revealTruth?"Real.":"Fake."));
		}
		return true;
	}

}