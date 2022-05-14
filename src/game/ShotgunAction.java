package game;

import java.util.ArrayList;
import java.util.Random;



import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;


/**
 * 
 * ShotgunAction class is the action that shotgun has. It applies the damge to all actors within
 * 3 grids 90 degrees from its shot location in a cone shape.
 * @author Xinyi Li
 */
public class ShotgunAction extends Action {

	/**
	 * direction from 0-7 as the index in the directionString that player chose. set to -1 in default.
	 */
	private int directionInt=-1;
	/**
	 * the Shotgun that called this action
	 */
	private Shotgun Shotgun;
	private Random rand = new Random();
	/**
	 * determine if the shot in this turn has happened.
	 */
	private boolean hasShot=false;

	/**
	 * a list of string representing the 8 directions
	 */
	private String[] directionString=new String[] {
			"North West",
			"North",
			"North East",
			"West",
			"East",
			"South West",
			"South",
			"South East",
	};
	/**
	 * constructor
	 * @param direction integer from 0-7
	 * @param gun shotgun that called this action
	 */
	private ShotgunAction(int direction, Shotgun gun)
	{
		assert 0<=direction && direction<=7 : "direction integer not within 0-7"; 
		this.directionInt=direction;
		this.Shotgun=gun;
		this.hasShot=true;
	}
	/**
	 * constructor
	 * @param gun shotgun that called this action
	 */
	public ShotgunAction(Shotgun gun)
	{
		this.Shotgun=gun;
	}

	/**
	 * if direction has not been set, return the switch to shotgun message
	 * if actor can have ammunitions and has shotgun bullet,
	 *     apply shot damage to all the actors within the range of affect
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String returnMessage="";
		if (directionInt==-1) { 
			returnMessage=actor+" switch to the shotgun";
		}
		else if (actor.canHaveAmmo() && actor.getShotgunAmmoNumber()<=0) { 
			returnMessage=actor+" has no shotgun bullet.";
		}
		else {
			if (actor.canHaveAmmo()) {
				actor.setShotgunAmmoNumber(actor.getShotgunAmmoNumber()-1);
			}
			
			ArrayList<Actor> targetArray=findActorandTakeDamage(map,actor); 
			if (targetArray.size()==0) {
				return actor+" shots nothing.";
			}
			
			detectAlive(targetArray,map);
			
			String actorName="";
			for (Actor targetInstance:targetArray) {
				actorName=actorName+" "+ targetInstance;
			}
			returnMessage=actor+" shots"+actorName+" for "+Shotgun.getRangeDamage()+" damages";
		}
		
		
		
		return returnMessage;
	}

	/**
	 * menu description for starts aiming if {@code directionInt<0} and shoot otherwise
	 */
	@Override
	public String menuDescription(Actor actor) {
		String returnMessage="";
		if (directionInt<0) {
			returnMessage=actor+" starts aiming direction with the shotgun";
		}
		else { 
			returnMessage=actor+" shoots in the "+directionString[directionInt]+" direction";
		}
		return returnMessage;
	}
	

	/**
	 * return the sub menu for all the directions to shoot at
	 */
	@Override
	public Actions getNextActions(GameMap map) {
		if (hasShot==false) {
			Actions actionList=new Actions();
			for (int i=0;i<8;i++) {
				actionList.add(new ShotgunAction(i,Shotgun));
			}

			return actionList;
		}
		return null;
	}
	
	/**
	 * take the damge at the target if there is a target exist at map location {@code xCoord+a,yCoord+b}
	 * destroy the fence within range
	 * @param targetArray array of actor that has taken damage   
	 * @param map current game map
	 * @param xCoord player's x coordinate
	 * @param yCoord player's y coordinate
	 * @param a integer added to {@code xCoord}
	 * @param b integer added to {@code yCoord}
	 */
	private void takeDamage(ArrayList<Actor> targetArray, GameMap map, int xCoord, int yCoord, int a, int b) {
		if (map.getXRange().contains(xCoord+a) && map.getYRange().contains(yCoord+b)) {
			if (map.isAnActorAt(map.at(xCoord+a,yCoord+b))&&rand.nextFloat()<0.75) {
				Actor currentActor=map.getActorAt(map.at(xCoord+a,yCoord+b));
				currentActor.hurt(Shotgun.getRangeDamage());
				targetArray.add(currentActor);
			}
			if (map.at(xCoord+a,yCoord+b).getGround().canbeDestroyed()) {   //bonus mark
				map.at(xCoord+a,yCoord+b).setGround(new Dirt());
			}
		}
		
	}
	
	/**
	 * find the actor that could be affected by the shoot, apply damage to them with 75% chance
	 * @param map current game map
	 * @param actor the player actor
	 * @return an array of actors that has been hurt by the attack
	 */
	private ArrayList<Actor> findActorandTakeDamage(GameMap map,Actor actor) {
		Location currentLocation=map.locationOf(actor);
		ArrayList<Actor> targetArray=new ArrayList<>();
		int xCoord=currentLocation.x();
		int yCoord=currentLocation.y();
		int range=3;
		for (int i=1;i<=range;i++) {
			if (directionString[directionInt]=="North") {
				int b=-i;	
				for (int a=-i;a<=i;a++) {
					takeDamage(targetArray,map,xCoord,yCoord,a,b);
				}
			}
		
			else if (directionString[directionInt]=="South") {
			
				int b=i;
				for (int a=-i;a<=i;a++) {
					takeDamage(targetArray,map,xCoord,yCoord,a,b);
				}
			}
			else if (directionString[directionInt]=="West") {
			
				int a=-i;
				for (int b=-i;b<=i;b++) {
					takeDamage(targetArray,map,xCoord,yCoord,a,b);
				}
			}
			else if (directionString[directionInt]=="East") {
			
				int a=i;
				for (int b=-i;b<=i;b++) {
					takeDamage(targetArray,map,xCoord,yCoord,a,b);
				}
			}
			else if (directionString[directionInt]=="North West") {
			
				for (int a=-i, b=0; a <= 0 && b >= -i; a++, b--) {  
					takeDamage(targetArray,map,xCoord,yCoord,a,b);
				}
			}
			else if (directionString[directionInt]=="North Eest") {
			
				for (int a=i, b =0; a >= 0 && b >= -i; a--, b--) {  
					takeDamage(targetArray,map,xCoord,yCoord,a,b);
				}
			}
			else if (directionString[directionInt]=="South West") {
			
				for (int a=-i, b =0; a <= 0 && b <= i; a++, b++) {  
					takeDamage(targetArray,map,xCoord,yCoord,a,b);
				}
			}
			else if (directionString[directionInt]=="South East") {
			
				for (int a=i, b =0; a >= 0 && b <= i; a--, b++) { 
					takeDamage(targetArray,map,xCoord,yCoord,a,b);
				}
			}
		}
		return targetArray;
	}
	/**
	 * remove the target from the map if they are not conscious
	 * @param targetArray an array of actors that has taken damage
	 * @param map current game map
	 */
	public void detectAlive(ArrayList<Actor> targetArray, GameMap map) {
		for (Actor shotTarget:targetArray) {
			if (!shotTarget.isConscious()) {
				if (shotTarget.hasCapability(ZombieCapability.ALIVE)) {
					Item corpse = new PortableItem("dead " + shotTarget, '%');
					map.locationOf(shotTarget).addItem(corpse);
				}
				Actions dropActions = new Actions();
				for (Item item : shotTarget.getInventory())
					dropActions.add(item.getDropAction());
				for (Action drop : dropActions)		
					drop.execute(shotTarget, map);
				map.removeActor(shotTarget);
				
			}
		}
	}
	
}
