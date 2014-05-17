package interfaces;

import items.Item;

public interface Inventory {

	public int getInventorySize();
	
	public Item getItemInSlot(int slot);
	
	public boolean setItemInSlot(int slot, Item item);
	
	public boolean isRoomInInventory();
	
	public boolean addItemToInventory(Item item);
}
