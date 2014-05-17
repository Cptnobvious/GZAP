package entities.inanimate.ents;

import interfaces.Inventory;
import items.Item;
import util.TexInfo;
import entities.inanimate.AbstractInanimateEntity;

public class Fridge extends AbstractInanimateEntity implements Inventory{
	

	public Fridge(int x, int y, int z) {
		super(x, y, z, null);
		texinfo = new TexInfo(0, 0, "items");
		isSolid = true;
	}

	@Override
	public void getMouseEvent(int button) {
		
	}

	@Override
	public int getInventorySize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Item getItemInSlot(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setItemInSlot(int slot, Item item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRoomInInventory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addItemToInventory(Item item) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
