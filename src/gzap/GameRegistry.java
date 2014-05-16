package gzap;

import java.util.ArrayList;

import entities.inanimate.AbstractInanimateEntity;
import world.tile.AbstractTile;
import world.tile.Tiles;

public class GameRegistry {

	static private ArrayList<AbstractTile> tiles = new ArrayList<AbstractTile>();
	
	public static void initializeRegistry(){
		//Setup tiles
		
		for(int x = 0; x < 1024; x++){
			tiles.add(null);
		}
		
		Tiles.initialize();
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
	
}
