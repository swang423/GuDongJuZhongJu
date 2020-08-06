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
	}
	/**
	 * Called initially
	 * @param val 
	 */
	public void setIdentity(boolean val) {
		isReal = val;
		showReal = val;
	}
	public void setShow(boolean val) {
		showReal = val;
	}
	/**
	 * Called when YaoBuRan acts on this antique
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
	private String tag;	//J,Q,K
	private String name;	//c,d,h,s
	private boolean isReal, showReal; //isReal is the real identity, showReal is what is shown to good guys
	private boolean isHidden = false;
}
