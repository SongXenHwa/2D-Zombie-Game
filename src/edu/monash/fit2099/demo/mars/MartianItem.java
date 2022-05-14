package edu.monash.fit2099.demo.mars;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;

public class MartianItem extends Item{

	public MartianItem(String name, char displayChar, boolean portable) {
		super(name, displayChar, portable);
	}
	
	public void addAction(Action action) {
		this.allowableActions.add(action);
	}

	@Override
	public void setPickUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDrop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sideEffect(Actor ZombieActor) {
		// TODO Auto-generated method stub
		
	}
}

