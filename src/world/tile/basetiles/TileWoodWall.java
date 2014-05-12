package world.tile.basetiles;

import world.tile.AbstractTile;
import world.tile.TileID;
import world.tile.TileTexInfo;

public class TileWoodWall extends AbstractTile{
	private TileTexInfo normal = new TileTexInfo(8, 0);

	public TileWoodWall(){
		super(TileID.WOOD_WALL);
	}

	@Override
	public TileTexInfo getTexInfo(int metadata){
		return normal;
	}
}
