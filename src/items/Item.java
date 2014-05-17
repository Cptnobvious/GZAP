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
	
}
