package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponItem;


/**
 * Zombie's bite weapon that has the sideEffect function.
 * @author Xinyi
 *
 */
public class TeethIntrinsicWeapon extends WeaponItem {

	public TeethIntrinsicWeapon(int damage, String verb) {
		super(null, 'B',damage, verb);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 *heals the zombie itself for 5 points
	 */
	@Override
	public void sideEffect(Actor ZombieActor) throws Exception{
		if (ZombieActor==null) {
			throw new Exception("actor that takes side effect is null ");
		}
		ZombieActor.heal(5);
	}


}

