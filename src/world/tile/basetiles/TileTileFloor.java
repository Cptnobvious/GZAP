package world.tile.basetiles;

import util.TexInfo;
import world.tile.AbstractTile;
import world.tile.Tile;
import world.tile.TileEntity;
import world.tile.TileID;

public class TileTileFloor extends AbstractTile{
	private TexInfo normal = new TexInfo(7, 0);

	public TileTileFloor(){
		super(TileID.TILE_FLOOR);
	}

	@Override
	public TexInfo getTexInfo(int metadata){
		return normal;
	}

	@Override
	public TileEntity getTileEntity(Tile parent) {
		// TODO Auto-generated method stub
		return null;
	}
}
