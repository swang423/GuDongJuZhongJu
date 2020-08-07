import java.util.Random;
public enum GameRole {

	XuYuan, FangZhen,MuHuJiaNai, HuangYanYan,
	LaoChaoFeng, YaoBuRan, 
	JiYunFu, 
	ZhenGuoQu;
	/**
	 * Get random order of roles 
	 * @param num_player
	 * @return
	 */
	public static GameRole[] getRandomRoles(int num_player) {
		Random random = new Random();
		int[] order = new int[num_player];
		GameRole[] ans = new GameRole[num_player];
		for(int i = 0; i < num_player; i++)
			order[i] = i;
		for(int i = 0; i < num_player; i++) {
			int rand = random.nextInt(num_player-i)+i;
			int temp = order[i];
			order[i] = order[rand];
			order[rand] = temp;
			ans[i] = values()[order[i]];
		}
		return ans;
	}
	
}
