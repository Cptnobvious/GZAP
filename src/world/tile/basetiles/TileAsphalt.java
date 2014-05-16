package world.tile.basetiles;

import util.TexInfo;
import world.tile.AbstractTile;
import world.tile.Tile;
import world.tile.TileEntity;
import world.tile.TileID;

public class TileAsphalt extends AbstractTile{
	
	private TexInfo normal = new TexInfo(0, 0);
	private TexInfo striped = new TexInfo(1, 0);
	
	public TileAsphalt(){
		super(TileID.ASPHALT, 2);
	}

	@Override
	public TexInfo getTexInfo(int metadata){
		switch(metadata){
		default:
		case 0:
			return normal;
		case 1:
			return striped;
		}
	}

	@Override
	public TileEntity getTileEntity(Tile parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
