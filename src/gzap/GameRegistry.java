package gzap;

import java.util.ArrayList;

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
		//uniqueID = uniqueID - 1;
		if (uniqueID <= tiles.size() && tiles.get(uniqueID) != null){
			return tiles.get(uniqueID);
		}
		
		return null;
	}
}
