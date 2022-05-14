package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/** Reduce the number of turns remaining for each crop
 * 
 * Change the display character of a crop , 'c' to a ripe crop, 'r' when turns remaining is 0
 * @author Song Xen Hwa 
*/
public class Crop extends Ground {
	private int turn = 20;

	public Crop() {
		super('c');
	}
	
	/**
	 * This method is called in the FertiliseAction class to reduce the number of turns remaining for that crop
	 * 
	 */
	public void fertilise() {
		
		turn -= 10;
		if (turn <= 0) {
			turn = 1;

		}
		
	}
	
	/** Get the marks and comments for both assignment and exam, returns them when called
	 * @param location  the location of the crop in the game map
	*/
	@Override
	public void tick(Location location) {
		super.tick(location);

		turn--;
		if (turn == 0)
			displayChar = 'r';

	}

}
