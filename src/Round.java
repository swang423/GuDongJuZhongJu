/**
 * 
 */

/**
 * @author Root
 *
 */
public class Round {
	public Round(int id) {
		assert id >= 1 && id <= 3 : "id between 1 and 3";
		this.id = id;
	}
	private int id;
}
