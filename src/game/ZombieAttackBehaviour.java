package game;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * This class is responsible for decide which weapon to use, the prob of successful attack, whether the 
 * weapon has side effect, and create AttackAction with corresponding parameters.
 * @author Xinyi
 *
 */
public class ZombieAttackBehaviour extends AttackBehaviour {
	/**
	 * a rand generator
	 */
	private Random rand=new Random(); 
	/**
	 * chance of succesful attack
	 */
	private double ChanceofPunch=0.5;
	/**
	 * the zombie actor itself
	 */
	private Zombie zombie;
	public ZombieAttackBehaviour(ZombieCapability attackableTeam) {
		super(attackableTeam);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param attackableTeam zombieCaoability
	 * @param zombie actor
	 */
	public ZombieAttackBehaviour(ZombieCapability attackableTeam,Zombie zombie) {
		super(attackableTeam);
		this.zombie=zombie;
	}
	
	/**
	 * create AttackAction with corresponding parameters, 
	 * including which weapon to use, the prob of successful attack
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		Collections.shuffle(exits);

		setChanceofPunch();    // detect number of zombie arms to set the chance of punch 
		for (Exit e: exits) {
			if (!(e.getDestination().containsAnActor()))
				continue;
			if (e.getDestination().getActor().hasCapability(getAttackableTeam())) {
				Weapon weapon = actor.getWeapon();
				if (weapon instanceof WeaponItem) {
					return new AttackAction(e.getDestination().getActor());
				}
				else {
					if (rand.nextFloat()<ChanceofPunch) {
						return new AttackAction(e.getDestination().getActor(),new IntrinsicWeapon(10, "punches"),0.5);
					}
					else {
						return new AttackAction(e.getDestination().getActor(),new TeethIntrinsicWeapon(20, "bite"),0.7);
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * set chance of punch base on zombie's arm number
	 */
	private void setChanceofPunch() {
		if (zombie.getArms()==1) {
			ChanceofPunch=0.25;
		}
		else if (zombie.getArms()==0) {
			ChanceofPunch=0;
		}
	}
}