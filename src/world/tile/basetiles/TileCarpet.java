package world.tile.basetiles;

import util.TexInfo;
import world.tile.AbstractTile;
import world.tile.Tile;
import world.tile.TileEntity;
import world.tile.TileID;

public class TileCarpet extends AbstractTile{
	private TexInfo normal = new TexInfo(6, 0, 124, 134, 153, 255);

	public TileCarpet(){
		super(TileID.CARPET);
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
