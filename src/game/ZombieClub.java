package game;


import edu.monash.fit2099.engine.WeaponItem;

/**
 * extends WeaponItem,
 * initialized inside the BasicArmWeapon class.
 * @author TinaP
 *
 */
public class ZombieClub extends WeaponItem {
	
	/**
	 * constructor that set the name, displaychar, damage and verb
	 * @param name    the name of the weapon
	 * @param displayChar the character representing the weapon
	 * @param damage  the damage of the weapon
	 * @param verb    the verb of the weapon
	 */
	public ZombieClub(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
		// TODO Auto-generated constructor stub
	}



}

