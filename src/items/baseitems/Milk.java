package items.baseitems;

import util.TexInfo;
import items.AbstractItem;
import items.ItemID;

public class Milk extends AbstractItem{

	public Milk() {
		super(ItemID.MILK, "Milk", null);
		texinfo = new TexInfo(0, 0);
	}

	
}
