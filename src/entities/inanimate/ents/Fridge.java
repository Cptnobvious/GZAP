package entities.inanimate.ents;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import gui.elements.GUIWindow;
import gui.elements.GUIInventoryWindow;
import gzap.Boot;
import interfaces.Inventory;
import items.Item;
import items.ItemID;
import util.PointMath;
import util.TexInfo;
import util.save.SaveInfo;
import world.generation.container.FoodGen;
import entities.inanimate.AbstractInanimateEntity;
import entities.inanimate.InanimateIDs;

public class Fridge extends AbstractInanimateEntity implements Inventory{

	private Item[] contains = new Item[9];
	private int ticksSinceClick = 0;
	private boolean canClick = true;

	public Fridge(){
		super(0, 0, 0, null);
		texinfo = new TexInfo(0, 0, "entities");
		isSolid = true;
		EntID = InanimateIDs.FRIDGE;
	}
	
	public Fridge(int x, int y, int z) {
		super(x, y, z, null);
		texinfo = new TexInfo(0, 0, "entities");
		isSolid = true;
		EntID = InanimateIDs.FRIDGE;
	}

	private void fill(){
		initialized = true;
		ArrayList<Item> templist = FoodGen.generateContents(contains.length, 30);

		for (int i = 0; i < contains.length; i++){
			setItemInSlot(i, templist.get(i));
		}
	}

	@Override
	public void getMouseEvent(int button) {
		if (button == 0 && canClick){
			if (PointMath.distance2Points(xLoc, yLoc, Boot.getPlayer().getX(), Boot.getPlayer().getY()) <= 2){
				if (!initialized){
					fill();
				}
				Boot.getGUIHandler().addWindow((new GUIInventoryWindow(-1, "Fridge", this, xLoc, yLoc, 3)));
			}
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

	@Override
	public void save(FileOutputStream out) throws IOException {
		SaveInfo info = new SaveInfo();
		
		if (initialized){
			info.addInt(contains.length);
			for (int i = 0; i < contains.length; i++){
				contains[i].getBase().getID();
			}
		} else {
			info.addInt(-1);
		}
		
		info.saveInfo(out);
	}

	@Override
	public void load(FileInputStream in) throws IOException{
		int c = in.read();
		
		if (c != -1){
			for (int i = 0; i < contains.length; i++){
				setItemInSlot(i, new Item(in.read()));
			}
		}

	}


}
