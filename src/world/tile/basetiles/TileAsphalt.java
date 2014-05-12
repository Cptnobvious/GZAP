package world.tile.basetiles;

import world.tile.AbstractTile;
import world.tile.TileTexInfo;

public class TileAsphalt extends AbstractTile{
	
	private TileTexInfo normal = new TileTexInfo(0, 0);
	private TileTexInfo striped = new TileTexInfo(1, 0);
	
	public TileAsphalt(){
		super(TileIDConfig.ASPHALT);
	}

	@Override
	public TileTexInfo getTexInfo(int metadata){
		switch(metadata){
		default:
		case 0:
			return normal;
		case 1:
			return striped;
		}
	}

}
