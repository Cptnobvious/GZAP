package world.tile.basetiles;

import world.tile.AbstractTile;
import world.tile.TileID;
import world.tile.TileTexInfo;

public class TileCarpet extends AbstractTile{
	private TileTexInfo normal = new TileTexInfo(6, 0, 124, 134, 153, 255);

	public TileCarpet(){
		super(TileID.CARPET);
	}

	@Override
	public TileTexInfo getTexInfo(int metadata){
		return normal;
	}
}
