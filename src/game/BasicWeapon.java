package game;


import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.WeaponItem;
import edu.monash.fit2099.interfaces.ItemInterface;

/**
 * This class is the parent of BasicArmWeapon and BasicLegWeapon, 
 * with function overriding the getAllowableActions,getPickUpAction,getDropAction 
 * that in extra set the state of the basic weapon to either pickUp or not.
 * Some functions will interact with CraftAction class.
 * @author Xinyi
 *
 */
public class BasicWeapon extends WeaponItem implements ItemInterface {
	private boolean pickUp;
	

	/**
	 * initialize attributes and set pickUp to false
	 * @param name    the name of the weapon
	 * @param displayChar the character representing the weapon
	 * @param damage  the damage of the weapon
	 * @param verb    the verb of the weapon
	 */
	public BasicWeapon(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
		pickUp=false;
	}
	
	/**
	 * @return the CraftAction action if the item is picked up, otherwise return an empty array list
	 */
	@Override
	public List<Action> getAllowableActions(){
		Actions actions=new Actions();
		if (pickUp) {
			try {
				actions.add(new CraftAction(this));
			}catch (Exception e) {
	        	e.printStackTrace();
	        }
			
		}
		return actions.getUnmodifiableActionList();    //avoid privacy leak
	}
	
	/**
	 * @return the PlayerPickUpItemAction action if the item portable, otherwise return null
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
	 * @return the PlayerDropItemAction action if the item portable, otherwise return null
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
	 * set {@code pickUp} to be true
	 */
	@Override
	public void setPickUp() {
		pickUp=true;
	}
	
	/**
	 * set {@code pickUp} to be false
	 */
	@Override
	public void setDrop() {
		pickUp=false;
	}
	
	/**
	 * @return get the weapon's {@code name}
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the WeaponItem that it will be crafted to
	 */
	public WeaponItem CraftTo() {
		return null;
	};
	
	/**
	 * @return the name of the WeaponItem that it will be crafted to
	 */
	public String getCraftToName() {
		return null;
	}

}
