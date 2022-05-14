package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Let Mambo Marie spawn 5 zombie in random location every 10 turns
 * @author Song Xen Hwa
 *
 */
public class ChantingBehaviour implements Behaviour{
	private int turns = 0;

	/**
	 * Check if current turn is a multiple of 10
	 * @param actor Mambo Marie
	 * @param map the Gamemap
	 * @return the result of calling ChantingAction()
	 * 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		System.out.println(turns);
		turns ++;
		if (turns % 10 == 0 && turns != 30) {
			return new ChantingAction();
		}
		return null;
	}

}
