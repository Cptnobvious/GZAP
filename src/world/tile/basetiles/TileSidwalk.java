package world.tile.basetiles;

import util.TexInfo;
import world.tile.AbstractTile;
import world.tile.Tile;
import world.tile.TileEntity;
import world.tile.TileID;

public class TileSidwalk extends AbstractTile{

	private TexInfo normal = new TexInfo(2, 0);
	
	public TileSidwalk(){
		super(TileID.SIDEWALK);
	}

	@Override
	public TexInfo getTexInfo(int data){
		return normal;
	}

	@Override
	public TileEntity getTileEntity(Tile parent) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
