package world.tile.basetiles;

import world.tile.AbstractTile;
import world.tile.TileTexInfo;

public class TileSidwalk extends AbstractTile{

	private TileTexInfo normal = new TileTexInfo(2, 0);
	
	public TileSidwalk(){
		super(TileIDConfig.SIDEWALK);
	}

	@Override
	public TileTexInfo getTexInfo(int data){
		return normal;
	}
	
}
