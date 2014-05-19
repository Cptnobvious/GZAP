package items.baseitems;

import util.TexInfo;
import items.AbstractItem;
import items.ItemID;

public class Nutribar extends AbstractItem{

	public Nutribar(){
		super(ItemID.NUTRIBAR, "Nutribar", null);
		texinfo = new TexInfo(3, 0);
	}
	
}
