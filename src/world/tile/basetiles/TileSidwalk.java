package world.tile.basetiles;

import world.tile.AbstractTile;
import world.tile.Tile;
import world.tile.TileEntity;
import world.tile.TileID;
import world.tile.TileTexInfo;

public class TileSidwalk extends AbstractTile{

	private TileTexInfo normal = new TileTexInfo(2, 0);
	
	public TileSidwalk(){
		super(TileID.SIDEWALK);
	}

	@Override
	public TileTexInfo getTexInfo(int data){
		return normal;
	}

	@Override
	public TileEntity getTileEntity(Tile parent) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
