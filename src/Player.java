/**
 * 
 */

/**
 * @author Root
 *
 */

/*enum GameRole {
	XuYuan, FangZhen, JiYunFu, MuHuJiaNai, HuangYanYan,
	LaoChaoFeng, YaoBuRan, ZhenGuoQu;
}*/
public class Player {
	public Player(String name) {
		this.myName = name;
	}
	
	public void setRole(Role aRole) {
		myRole = aRole;
	}
	public Role getRole() {
		return myRole;
	}
	private String myName;
	private Role myRole;
	private int[] coins = {2,2,2};
}
