package items.baseitems;

import util.TexInfo;
import items.AbstractItem;
import items.ItemID;

public class Stick extends AbstractItem{
	
	public Stick(){
		super(ItemID.STICK, "Stick", null);
		texinfo = new TexInfo(4, 0);
		damageDealt = 10;
		attackSpeed = 10;
	}

}
