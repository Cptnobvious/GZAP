package world.tile.basetiles;

import world.tile.AbstractTile;
import world.tile.Tile;
import world.tile.TileEntity;
import world.tile.TileID;
import world.tile.TileTexInfo;

public class TileDoor extends AbstractTile{

	private TileTexInfo normal = new TileTexInfo(9, 0);
	private TileTexInfo open = new TileTexInfo(10, 0);
	
	public TileDoor(){
		super(TileID.DOOR);
		isSolid = true;
	}

	@Override
	public TileTexInfo getTexInfo(int metadata){
		switch(metadata){
		default:
		case 0:
			return normal;
		case 1:
			return open;
		}
	}

	@Override
	public TileEntity getTileEntity(Tile parent) {
		return (TileEntity)new TileDoorEntity(parent);
	}
	
}
