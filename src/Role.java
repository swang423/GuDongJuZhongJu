
public class Role {
	
	public Role(String gameName) {
		this.gameName = gameName;
	}
	
	/**
	 * attacked by YaoBuRan
	 */
	public void attacked() {
		isAttacked = true;
	}
	/**
	 * act depends on the player's ability
	 */
	//abstract public void act();
	
	public boolean checkAntique(Antique choice) {
		if(isGood) {
			return choice.getShow();
		}else {	//bad guy always get real identity
			return choice.getIdentity();
		}
	}
	private String gameName;
	private boolean isAttacked = false;
	//private int[] coins = new int[] {2,2,2};
	private boolean isGood;	//if the player belongs to the good camp
}

