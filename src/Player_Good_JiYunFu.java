/**
 * 
 */

/**
 * @author Root
 *
 */
public class Player_Good_JiYunFu extends Role{
	public Player_Good_JiYunFu(String gameName) {
		super(gameName);
	}
	
	public void act() {
		if(isDisabled) {
			//TODO: no ability
		}else {
			//TODO: act as usually
			
			
		}
	}
	public boolean checkAntique(Antique choice) {
		return choice.getIdentity();
	}
	private boolean isDisabled = false;
}
