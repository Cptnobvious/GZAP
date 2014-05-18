package items;

import items.baseitems.Milk;
import gzap.GameRegistry;

public class Items {

	public static void initialize() {
		GameRegistry.registerItem(ItemID.MILK, new Milk());
	}

}
