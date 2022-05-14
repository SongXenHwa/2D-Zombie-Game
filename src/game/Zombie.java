package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;


/**
 * A Zombie.
 * 
 * This Zombie has in default 2 arms and legs, when hurt, limbs will have the probability to be
 * knocked to the ground. It has an array of behaviours to choose from each turn.
 * 
 * @author ram, Xinyi
 *
 */
public class Zombie extends ZombieActor {
	/**
	 * 2 arms
	 */
	private int Arms=2;
	/**
	 * 2 legs
	 */
	private int Legs=2;
	/**
	 * a map that enables hurt() to drop item to the ground around the zombie
	 * made private to reduce dependencies
	 */
	private GameMap map;
	/**
	 * start count when a leg is losed.
	 */
	private int countTerm;
	
	/**
	 * stores a hashmap with keys equal to the verb of behaviour, and Behaviour object
	 */
	private HashMap<String,Behaviour> behaviours=new HashMap<>();
	/**
	 * Random number generator
	 */
	private Random newRand=new Random();
	//private Behaviour[] behaviours = {
			//new RoarBehaviour(),
			//new PickUpItemBehaviour(),    
			//new ZombieAttackBehaviour(ZombieCapability.ALIVE),
			//new HuntBehaviour(Human.class, 10),
			//new WanderBehaviour()
	//};
	
	/**
	 * reduce limb number from the zombie itself, generate the limbs on the ground near zombie.
	 * has a chance for the zombie to lose all its weapons.
	 */
	@Override
	public void hurt(int points) {
		hitPoints -= points;
		if (map!=null) {
			ArrayList<Integer> knockoffLimbs=generateLoseLimb();
			int tempArms=Arms;
			int tempLegs=Legs;
			setLimbs(knockoffLimbs.get(0)+knockoffLimbs.get(1),knockoffLimbs.get(2)+knockoffLimbs.get(3));
			Location here = map.locationOf(this);
			
			//if zombie lost one arm, it will have 50% chance to drop any weapon it is holding
			//if zombie has no arm, it will drop any weapon it is holding
			if (tempArms!=Arms) {
				if ((tempArms-Arms)==1 && newRand.nextBoolean() || Arms==0) {
					List<Item> inventItems=this.inventory;
					for (Item inventoryItem:inventItems) {
						getRandomPlace(here,newRand).getDestination().addItem(inventoryItem);
						System.out.println("The zombie drops "+inventoryItem.getDisplayChar());
					}
					this.inventory.clear();
				}

				
			}
			//create limbs on the ground
			for (int i=0;i<tempArms-Arms;i++) {
				getRandomPlace(here,newRand).getDestination().addItem(new BasicArmWeapon("Arm",'A',10,"hit"));
			}
			for (int i=0;i<tempLegs-Legs;i++) {
				getRandomPlace(here,newRand).getDestination().addItem(new BasicLegWeapon("Leg",'L',10,"hit"));
			}
		}
		
	}
	
	/**
	 * @return a random place next to zombie
	 */
	private Exit getRandomPlace(Location here,Random newRand) {
		List<Exit> allDestination=here.getExits();
		Exit returnExit=allDestination.get(newRand.nextInt(allDestination.size()));
		while (returnExit.getDestination().canActorEnter(this)==false) {
			returnExit=allDestination.get(newRand.nextInt(allDestination.size()));
		}
		return returnExit;
	}
	/**
	 * @return an arraylist with 4 integers, first two represents lose arms, last two represents lose legs.
	 */
	private ArrayList<Integer> generateLoseLimb(){
		ArrayList<Integer> knockoffLimbs=new ArrayList<>();
		for (int i=0;i<4;i++) {
			if (newRand.nextFloat()>0.25) {
				knockoffLimbs.add(0);
			}
			else {
				knockoffLimbs.add(1);
			}
		}
		return knockoffLimbs;
	}
	/**
	 * @param name 
	 * constructor, add a bunch of behaviours
	 */
	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
		behaviours.put("Roar",new RoarBehaviour());
		behaviours.put("Pick",new ZombiePickUpItemBehaviour());
		behaviours.put("Attack",new ZombieAttackBehaviour(ZombieCapability.ALIVE,this));
		behaviours.put("Hunt",new HuntBehaviour(Human.class, 10));
		behaviours.put("Wander",new WanderBehaviour());

	}
	
	/**
	 * @return get zombie's arm number
	 */
	public int getArms() {
		return Arms;
	}
	/**
	 * @return get zombie's leg number
	 */
	public int getLegs() {
		return Legs;
	}
	/**
	 * @param loseArm set zombie's arm number
	 * @param loseLeg set zombie's leg number
	 * 
	 */
	public void setLimbs(int loseArm,int loseLeg) {
		Arms=Math.max(Arms-loseArm,0);
		Legs=Math.max(Legs-loseLeg,0);
	}


	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "punches");
	}

	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.  
	 * If no humans are close enough it will wander randomly. It has 10% chance to say Brain.
	 * It don't move if lost both legs, and if it lost one leg, it will move every two turns.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		this.map=map;         //set the map in the zombie in every playTurn.
		
		if (map.locationOf(this).getGround().getDisplayChar() == 'O') {
			return new TrapKillZombie();
		}
		//remove pickupitembehaviour if zombie has no arm
		if (this.getArms()==0) {
			if (behaviours.containsKey("Pick")) {
				behaviours.remove("Pick");
			}
		}
		
		//count start when the zombie lost at least one leg
		if (this.getLegs()!=2) {
			countTerm+=1;
		}

		changeMoveBehaviour();
		
		for (Entry<String, Behaviour> entry : behaviours.entrySet()) {
			Action action=entry.getValue().getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();		
	}
	
	//take turns to add or remove the hunt and wander behaviours as playTurn() is called once every term
	private void changeMoveBehaviour() {
		if (this.getLegs()==1&&countTerm%2==1 || this.getLegs()==0) {
					
					if (behaviours.containsKey("Hunt")) {
						behaviours.remove("Hunt");
					}
					if (behaviours.containsKey("Wander")) {
						behaviours.remove("Wander");
					}
		
				}
				else if (this.getLegs()==1&&countTerm%2==0) {
					//System.out.println("Two behaviours added");
					behaviours.put("Hunt",new HuntBehaviour(Human.class, 10));
					behaviours.put("Wander",new WanderBehaviour());
		}
	}
}
