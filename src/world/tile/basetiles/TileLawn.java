package world.tile.basetiles;

import world.tile.AbstractTile;
import world.tile.TileTexInfo;

public class TileLawn extends AbstractTile{
	private TileTexInfo normal = new TileTexInfo(3, 0);
	
	public TileLawn(){
		super(TileIDConfig.LAWN);
	}

	@Override
	public TileTexInfo getTexInfo(int data){
		return normal;
	}
}
