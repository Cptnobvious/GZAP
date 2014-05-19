package items;

import items.baseitems.Apple;
import items.baseitems.Milk;
import items.baseitems.Nutribar;
import items.baseitems.Water;
import gzap.GameRegistry;

public class Items {

	public static void initialize() {
		GameRegistry.registerItem(ItemID.MILK, new Milk());
		GameRegistry.registerItem(ItemID.APPLE, new Apple());
		GameRegistry.registerItem(ItemID.WATER_BOTTLE, new Water());
		GameRegistry.registerItem(ItemID.NUTRIBAR, new Nutribar());
	}

}
