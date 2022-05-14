package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Menu;

/**
 * Class representing the Player.
 */
public class Player extends Human {
	private Menu menu = new Menu();
	private int ShotgunAmmo=0;
	private int RifleAmmo=0;
	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}
	
	/**
	 * Displaying the list of possible actions that can be done by player. 
	 * 
	 * @author Song Xen Hwa
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current location player is at
	 * @param display the Display where actions that can be performed by the player\
	 * @return actions that player can perform in that round
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		actions.add(new QuitGameAction());
		double prob = Math.random();
		int woodCount = 0;
		int x = map.locationOf(this).x();
		int y = map.locationOf(this).y();
		
		for(Item item : this.getInventory()) {
			if(item.getDisplayChar() == 'W') {
				woodCount ++;
			}
		}
		
		if (woodCount >= 4){
			actions.add(new SettingTrapAction());
		}
		
		if (this.hitPoints < this.maxHitPoints) {
			for (Item i: this.getInventory()) {
					if (i.getDisplayChar() == '?') {
						actions.add(new EatingAction(i));
				}
			}	
		}
		
		for (int i =-1 ; i< 2; i++) {
			for (int j  =-1 ; j< 2; j++) {
				try {
					if (map.at(x+i, y+j).getGround().getDisplayChar() == 'r') {
						actions.add(new HarvestAction(x+i,y+j));
					}
					else if (map.at(x+i, y+j).getGround().getDisplayChar() == 'T') {
						actions.add(new ChopTreeAction(x+i,y+j));
					}
					else if (map.at(x+i, y+j).getGround().getDisplayChar() == 'X') {
						if (map.at(x+i, y+j).getDisplayChar() != 'Z') {
						actions.add(new RepairTrapAction(x+i,y+j));
						}
					}
				} catch (Exception e) {
					continue;
				}
			}
		}
		
		
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		Action currentSelected=menu.showMenu(this, actions, display);
		if (currentSelected.getNextActions(map)!=null) {
			currentSelected=menu.showMenu(this, currentSelected.getNextActions(map), display);
			if (currentSelected.getSecondNextActions(map,this,lastAction)!=null) {
				currentSelected=menu.showMenu(this, currentSelected.getSecondNextActions(map,this,lastAction), display);
			}
		}
		return currentSelected;
	}
	@Override
	public int getShotgunAmmoNumber() {       
		return ShotgunAmmo;                   
	}
	@Override
	public void setShotgunAmmoNumber(int number) {
		ShotgunAmmo=number;
	}
	@Override
	public int getRifleAmmoNumber() {       
		return RifleAmmo;                   
	}
	@Override
	public void setRifleAmmoNumber(int number) {
		RifleAmmo=number;
	}
	@Override
	public boolean canHaveAmmo() {
		return true;
	}
	@Override 
	public int getHitpoint() {
		return hitPoints;
	}

}

