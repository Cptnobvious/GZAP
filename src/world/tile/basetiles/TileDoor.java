package world.tile.basetiles;

import util.TexInfo;
import world.tile.AbstractTile;
import world.tile.Tile;
import world.tile.TileEntity;
import world.tile.TileID;

public class TileDoor extends AbstractTile{

	private TexInfo normal = new TexInfo(9, 0);
	private TexInfo open = new TexInfo(10, 0);
	
	public TileDoor(){
		super(TileID.DOOR);
		isSolid = true;
	}

	@Override
	public TexInfo getTexInfo(int metadata){
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
