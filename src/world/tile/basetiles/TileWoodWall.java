package world.tile.basetiles;

import util.TexInfo;
import world.tile.AbstractTile;
import world.tile.Tile;
import world.tile.TileEntity;
import world.tile.TileID;

public class TileWoodWall extends AbstractTile{
	private TexInfo normal = new TexInfo(8, 0);

	public TileWoodWall(){
		super(TileID.WOOD_WALL);
		isSolid = true;
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
