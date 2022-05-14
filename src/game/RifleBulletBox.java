package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * 
 * RifleBulletBox have a rifle ammunition number that can be added to the actor,
 * it cannot be added to the inventory, can only change the actor's attribute
 * 
 * @author Xinyi Li
 *
 */
public class RifleBulletBox extends Item {
	/**
	 * number of ammunition
	 */
	private int ammoNumber;
	/**
	 * constructor that sets the ammunition number
	 * @param ammoNumber integer greater than 0
	 */
	public RifleBulletBox(int ammoNumber) {
		super("Rifle bullet box", '}', true);
		
		assert ammoNumber>=0:"ammunition number cannot be negative";
		this.ammoNumber=ammoNumber;
	}
	
	/**
	 * get the action that allows actors to pick it up
	 *
	 */
	@Override 
	public PickUpItemAction getPickUpAction() {
		if(portable)
			try {
				return new PlayerPickUpItemAction(this);
				}catch (Exception e) {
		        	e.printStackTrace();
		        }
		
		return null;
	}
	/**
	 * get the action that allows actors to drop it
	 */
	@Override 
	public DropItemAction getDropAction() {
		if(portable)
			try {
				return new PlayerDropItemAction(this);
				}catch (Exception e) {
		        	e.printStackTrace();
		        }
		
		return null;
	}
	/**
	 * returns false because it cannot be added to inventory
	 */
	@Override
	public boolean canAddtoInventory() {
		return false;
	}
	
	
	/**
	 * set the actor's {@code RifleAmmoNumber} attribute to add on the number of ammunition in the box
	 */
	@Override
	public void changePlayerAttribute(Actor actor) {
		actor.setRifleAmmoNumber(actor.getRifleAmmoNumber()+ammoNumber);
//		System.out.println(actor.getRifleAmmoNumber());
	}

}