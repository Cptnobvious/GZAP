package world.tile;

import world.tile.basetiles.TileAsphalt;
import world.tile.basetiles.TileIDConfig;
import world.tile.basetiles.TileLawn;
import world.tile.basetiles.TileSidwalk;
import gzap.GameRegistry;

public class Tiles {

	public static void initialize(){
		GameRegistry.registerTile(TileIDConfig.ASPHALT, 	new TileAsphalt());
		GameRegistry.registerTile(TileIDConfig.SIDEWALK, 	new TileSidwalk());
		GameRegistry.registerTile(TileIDConfig.LAWN, 		new TileLawn());
	}
	
	
}
