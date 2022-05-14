package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;


/**
 * Zombie's roaring action, called by RoarBehaviour with 10% chance.
 * @author Xinyi
 *
 */
public class RoarBehaviour implements Behaviour {
	/**
	 * rand determines whether the zombie roar or not
	 */
	protected Random rand=new Random();
	public RoarBehaviour() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return RoarAction or null
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		if (rand.nextFloat()<=0.1) {
			return new RoarAction();
		}
		return null;
	}

}
