package world.tile;

import world.tile.basetiles.TileAsphalt;
import world.tile.basetiles.TileCarpet;
import world.tile.basetiles.TileDoor;
import world.tile.basetiles.TileLawn;
import world.tile.basetiles.TileSidwalk;
import world.tile.basetiles.TileTileFloor;
import world.tile.basetiles.TileWoodFloor;
import world.tile.basetiles.TileWoodWall;
import gzap.GameRegistry;

public class Tiles {

	public static void initialize(){
		GameRegistry.registerTile(TileID.ASPHALT, 		new TileAsphalt());
		GameRegistry.registerTile(TileID.SIDEWALK, 		new TileSidwalk());
		GameRegistry.registerTile(TileID.LAWN, 			new TileLawn());
		GameRegistry.registerTile(TileID.WOOD_FLOOR, 	new TileWoodFloor());
		GameRegistry.registerTile(TileID.CARPET,		new TileCarpet());
		GameRegistry.registerTile(TileID.TILE_FLOOR, 	new TileTileFloor());
		GameRegistry.registerTile(TileID.WOOD_WALL, 	new TileWoodWall());
		GameRegistry.registerTile(TileID.DOOR, 			new TileDoor());
		
	}
	
	
}
