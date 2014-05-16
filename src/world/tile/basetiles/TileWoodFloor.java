package world.tile.basetiles;

import util.TexInfo;
import world.tile.AbstractTile;
import world.tile.Tile;
import world.tile.TileEntity;
import world.tile.TileID;

public class TileWoodFloor extends AbstractTile{

	private TexInfo normal = new TexInfo(5, 0);
	
	public TileWoodFloor(){
		super(TileID.WOOD_FLOOR);
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
