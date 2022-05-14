package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {
	private int turn = 5;

	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}

	@Override
	public void setPickUp() {
		
	}

	@Override
	public void setDrop() {
		
	}
	/**
	 * Each corpse has a turn of 5 before turning into a zombie.
	 * This method decrease the number of remaining turn by the corpse by 1
	 * Creates a new zombie actor with the actor's last name when turn remaining  equals 0
	 * 
	 * @param location  location of the corpse
	 * @author Song Xen Hwa
	 */
	public void tick(Location location) {
		super.tick(location);

		turn--;
		try {
			if (turn == 0) {
				if (this.getDisplayChar() == '%') {
					location.removeItem(this);
					location.addActor(new Zombie("Zombie "+this.name.substring(5)));
					System.out.println(this.name + " risen form the dead.");
				}
			}
		}catch (Exception e) {
			turn ++ ;
		}
	}

	@Override
	public void sideEffect(Actor ZombieActor) {
		// TODO Auto-generated method stub
		
	}
}
