package gzap;

import items.AbstractItem;
import items.Items;

import java.util.ArrayList;

import entities.inanimate.AbstractInanimateEntity;
import entities.inanimate.InanimateEntities;
import world.tile.AbstractTile;
import world.tile.Tiles;

public class GameRegistry {

	static private ArrayList<AbstractTile> tiles = new ArrayList<AbstractTile>();
	static private ArrayList<AbstractItem> items = new ArrayList<AbstractItem>();
	static private ArrayList<AbstractInanimateEntity> inent = new ArrayList<AbstractInanimateEntity>();
	
	public static void initializeRegistry(){
		//Setup tiles
		
		for(int x = 0; x < 1024; x++){
			tiles.add(null);
			items.add(null);
			inent.add(null);
		}
		
		Tiles.initialize();
		Items.initialize();
		InanimateEntities.initialize();
	}
	
	public static void registerTile(int uniqueID, AbstractTile tile){
		if (tiles.get(uniqueID) == null){
			tiles.set(uniqueID, tile);
		} else {
			System.out.println("Tile ID " + String.valueOf(uniqueID) + "Is already taken");
		}
	}
	
	public static AbstractTile getTile(int uniqueID){
		if (uniqueID <= tiles.size() && tiles.get(uniqueID) != null){
			return tiles.get(uniqueID);
		} else {
			System.out.println("Tried to access an ID not bound to a tile");
			System.out.println(String.valueOf(uniqueID));
			return tiles.get(0);
		}
		
		//return null;
	}
	
	
	
	public static void registerItem(int uniqueID, AbstractItem item){
		if (items.get(uniqueID) == null){
			items.set(uniqueID, item);
		} else {
			System.out.println("Item ID " + String.valueOf(uniqueID) + "Is already taken");
		}
	}
	

	public static AbstractItem getItem(int uniqueID){
		if (uniqueID <= items.size() && items.get(uniqueID) != null){
			return items.get(uniqueID);
		} else {
			System.out.println("Tried to access an ID not bound to an Item");
			System.out.println(String.valueOf(uniqueID));
			return items.get(0);
		}
		
		//return null;
	}
	
	public static void registerInEnt(int uniqueID, AbstractInanimateEntity inEnt){
		if (inent.get(uniqueID) == null){
			inent.set(uniqueID, inEnt);
		} else {
			System.out.println("InEnt ID " + String.valueOf(uniqueID) + "Is already taken");
		}
	}
	
	public static AbstractInanimateEntity getInEnt(int uniqueID){
		if (uniqueID <= inent.size() && inent.get(uniqueID) != null){
			return inent.get(uniqueID);
		} else {
			System.out.println("Tried to access an ID not bound to an InEnt");
			System.out.println(String.valueOf(uniqueID));
			return inent.get(0);
		}
		
		//return null;
	}
}
