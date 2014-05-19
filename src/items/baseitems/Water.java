package items.baseitems;

import util.TexInfo;
import items.AbstractItem;
import items.ItemID;

public class Water extends AbstractItem{
	
	public Water(){
		super(ItemID.WATER_BOTTLE, "Water", null);
		texinfo = new TexInfo(2, 0);
	}

}
