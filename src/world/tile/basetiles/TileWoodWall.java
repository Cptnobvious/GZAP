package world.tile.basetiles;

import world.tile.AbstractTile;
import world.tile.Tile;
import world.tile.TileEntity;
import world.tile.TileID;
import world.tile.TileTexInfo;

public class TileWoodWall extends AbstractTile{
	private TileTexInfo normal = new TileTexInfo(8, 0);

	public TileWoodWall(){
		super(TileID.WOOD_WALL);
		isSolid = true;
	}

	@Override
	public TileTexInfo getTexInfo(int metadata){
		return normal;
	}

	@Override
	public TileEntity getTileEntity(Tile parent) {
		// TODO Auto-generated method stub
		return null;
	}
}
