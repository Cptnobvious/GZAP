package entities.inanimate.ents;

import gui.elements.GUIWindow;
import gui.windows.InventoryWindow;
import gzap.Boot;
import interfaces.Inventory;
import items.Item;
import items.ItemID;
import util.TexInfo;
import entities.inanimate.AbstractInanimateEntity;

public class Fridge extends AbstractInanimateEntity implements Inventory{
	
	private Item[] contains = new Item[9];
	private int ticksSinceClick = 0;
	private boolean canClick = true;

	public Fridge(int x, int y, int z) {
		super(x, y, z, null);
		texinfo = new TexInfo(0, 0, "items");
		isSolid = true;
		setItemInSlot(0, new Item(ItemID.MILK));
		setItemInSlot(1, new Item(ItemID.MILK));
		setItemInSlot(2, new Item(ItemID.MILK));
		setItemInSlot(3, new Item(ItemID.MILK));
		setItemInSlot(4, new Item(ItemID.MILK));
		setItemInSlot(5, new Item(ItemID.MILK));
	}

	@Override
	public void getMouseEvent(int button) {
		if (button == 0 && canClick){
			Boot.getGUIHandler().addWindow((GUIWindow)(new InventoryWindow(-1, "Fridge", this)));
		}
	}

	@Override
	public int getInventorySize() {
		return contains.length;
	}

	@Override
	public Item getItemInSlot(int slot) {
		return contains[slot];
	}

	@Override
	public boolean setItemInSlot(int slot, Item item) {
		contains[slot] = item;
		return true;
	}

	@Override
	public boolean isRoomInInventory() {
		for (int i = 0; i < contains.length; i++){
			if (contains[i] == null){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean addItemToInventory(Item item) {
		for (int i = 0; i < contains.length; i++){
			if (contains[i] == null){
				contains[i] = item;
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
}
