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
	public Antique(String tag,String name,boolean isReal) {
		this.name = name;
		this.tag = tag;
		this.isReal = isReal;
		this.showReal = isReal;
	}

	/**
	 * Called when LaoChaoFeng flips its identity
	 * @param val: what val to show to good people
	 */
	public void reverseShow() {
		showReal = !this.isReal;
	}
	/**
	 * Called when ZhenGuoQu acts on this antique
	 */
	public void hide() {
		isHidden = true;
	}
	public boolean getIdentity() {
		return isReal;
	}
	public boolean getShow() {
		return showReal;
	}
	public boolean isHidden() {
		return isHidden;
	}
	public String getName() {
		return name+tag;
	}
	private String tag;	//J,Q,K
	private String name;	//c,d,h,s
	private boolean isReal, showReal; //isReal is the real identity, showReal is what is shown to good guys
	private boolean isHidden = false;
}
