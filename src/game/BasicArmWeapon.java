package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * This class extends BasicWeapon, with function to return the weapon that it is crafted to.
 * @author Xinyi
 *
 */
public class BasicArmWeapon extends BasicWeapon {

	/**
	 * constructor that set the name, displaychar, damage and verb of a basic weapon
	 * its pickUp attributes is set to false in parent class.
	 * @param name    the name of the weapon
	 * @param displayChar the character representing the weapon
	 * @param damage  the damage of the weapon
	 * @param verb    the verb of the weapon
	 */
	public BasicArmWeapon(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return a new ZombieClub object that is of type WeaponItem
	 */
	@Override
	public WeaponItem CraftTo() {
		return new ZombieClub("Club", 'C', 25, "whack");
	}
	
	/**
	 * @return the name of weapon "Club"
	 */
	@Override
	public String getCraftToName() {
		return "Club";
	}
}
