package world.tile.basetiles;

import world.tile.AbstractTile;
import world.tile.Tile;
import world.tile.TileEntity;
import world.tile.TileID;
import world.tile.TileTexInfo;

public class TileLawn extends AbstractTile{
	private TileTexInfo normal = new TileTexInfo(3, 0);
	private TileTexInfo mud = new TileTexInfo(4, 0);
	
	public TileLawn(){
		super(TileID.LAWN, 2);
	}

	@Override
	public TileTexInfo getTexInfo(int data){
		switch(data){
		default:
		case 0:
			return normal;
		case 1:
			return mud;
		}
	}

	@Override
	public TileEntity getTileEntity(Tile parent) {
		// TODO Auto-generated method stub
		return null;
	}
}
