package world.tile.basetiles;

import world.tile.AbstractTile;
import world.tile.Tile;
import world.tile.TileEntity;
import world.tile.TileID;
import world.tile.TileTexInfo;

public class TileTileFloor extends AbstractTile{
	private TileTexInfo normal = new TileTexInfo(7, 0);

	public TileTileFloor(){
		super(TileID.TILE_FLOOR);
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
