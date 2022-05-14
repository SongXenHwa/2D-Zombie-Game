package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.interfaces.ItemInterface;


/**
 * This class extends Action class, its execute method will apply hurt and sideEffect like heal to the 
 * target or actor
 * 
 *
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * either null if actor is holding a weapon, or set to the zombie's intrinsic weapons.
	 */
	private Weapon initialWeapon=null;
	/**
	 * weapon's chance of successful hit.
	 */
	private double Prob=0.5;
	/**
	 * determines whether the weapon has side effect or not
	 */
	private boolean AddEffect=false;
	/**
	 * Random number generator
	 */
	private Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}
	

	/**
	 * Constructor
	 * @param target the Actor to attack
	 * @param weapon weapon to attack
	 * @param probability chance of successful attack
	 */
	public AttackAction(Actor target, Weapon weapon,double probability) {
		this.target = target;
		this.initialWeapon=weapon;
		this.Prob=probability;
	}
	
	
	/**
	 * Decrease life of actor when attacked
	 * Creates a new portable item when human or farmer dies
	 * Drop all inventory that the actor had when it dies
	 * 
	 * @author Song Xen Hwa
	 * @param actor  the actor that is being hurt or dead
	 * @param map    the game map
	 * 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();
		if (initialWeapon!=null) {
			weapon=initialWeapon;
		}
		if (rand.nextFloat()<=Prob) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);
		if (weapon instanceof ItemInterface) {
			try {
				((ItemInterface) weapon).sideEffect(actor); //apply side effect
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if (!target.isConscious()) {
			if (target.hasCapability(ZombieCapability.ALIVE)) {
				Item corpse = new PortableItem("dead " + target, '%');
				map.locationOf(target).addItem(corpse);
			}
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
