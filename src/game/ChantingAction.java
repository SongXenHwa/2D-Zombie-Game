package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/** 
 * Spawn 5 randon zombies at random location
 * @author Song Xen Hwa 
*/
public class ChantingAction extends Action {
	Random ran = new Random();
	private static int count = 1;
	
	/**
	 * Spawn 5 randon zombies at random location
	 * @param actor Mambo Marie
	 * @return menuDescription()
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		int i = 0;
		while (i != 5) {
			int x = ran.nextInt(80);
			int y = ran.nextInt(24);
			try {
				if (x > 23 && x < 62) {
					if (y>17) {
						map.at(x, y).addActor(new Zombie("Zombie " + count++));
						i++;
					}
				}else {
					map.at(x, y).addActor(new Zombie("Zombie " + count++));
					i++;
				}
			}catch (Exception e) {
				count--;
			}
		}
		return menuDescription(actor);
	}
	
	/**
	 * @param actor Mambo Marie
	 * @return a string consisting on the mambo marie spawing 5 zombies
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " spawn 5 zombies in random locations";
	}

}
