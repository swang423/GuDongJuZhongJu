
abstract public class Role {
	
	public Role(String gameName) {
		this.gameName = gameName;
	}
	
	public void attacked() {
		isAttacked = true;
	}
	abstract public void act();
	
	private String gameName;
	private boolean isAttacked = false;
}

