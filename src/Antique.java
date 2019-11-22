/**
 * 
 */

/**
 * @author Root
 *
 */
public class Antique {
	public Antique(String tag,String name) {
		this.name = name;
		this.tag = tag;
	}
	/**
	 * Called initially
	 * @param val 
	 */
	public void setIdentity(boolean val) {
		isReal = val;
	}
	/**
	 * Called when YaoBuRan acts on this antique
	 */
	public void hide() {
		isHidden = true;
	}
	/**
	 * Called when LaoChaoFeng acts in the round for all 4 antiques
	 */
	public void manipulate() {
		isManipulated = true;
	}
	/**
	 * Called when a role checks this antique
	 * @return 0 real, 1 fake, 2 unknown
	 */
	public String check() {
		if(isHidden)
			return "Unknown";	//TODO check response
		else {
			if(isManipulated)
				return isReal?"Fake":"Real";
			else
				return isReal?"Real":"Fake";
		}
	}
	private String tag;	//J,Q,K
	private String name;	//c,d,h,s
	private boolean isReal;
	private boolean isHidden = false;
	private boolean isManipulated = false;
}
