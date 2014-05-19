package items.baseitems;

import util.TexInfo;
import items.AbstractItem;
import items.ItemID;

public class Apple extends AbstractItem{

	public Apple() {
		super(ItemID.APPLE, "Apple", null);
		texinfo = new TexInfo(1, 0);
	}

}
