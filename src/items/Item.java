package items;

import gzap.GameRegistry;

public class Item {

	private AbstractItem base;
	
	public Item(int id){
		base = GameRegistry.getItem(id);
	}
	
	public AbstractItem getBase(){
		return base;
	}
	
	public int getDamageDealt(){
		return base.getDamageDealt();
	}
	
	public int getAttackSpeed(){
		return base.getAttackSpeed();
	}
	
}
