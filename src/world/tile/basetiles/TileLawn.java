package world.tile.basetiles;

import java.util.Random;

import util.TexInfo;
import world.tile.AbstractTile;
import world.tile.Tile;
import world.tile.TileEntity;
import world.tile.TileID;

public class TileLawn extends AbstractTile{
	private TexInfo normal = new TexInfo(3, 0);
	private TexInfo mud = new TexInfo(4, 0);
	private TexInfo whiteFlowers = new TexInfo(3, 1);
	
	public TileLawn(){
		super(TileID.LAWN, 3);
	}

	@Override
	public TexInfo getTexInfo(int data){
		switch(data){
		default:
		case 0:
			return normal;
		case 1:
			return mud;
		case 2:
			return whiteFlowers;
		}
	}

	@Override
	public TileEntity getTileEntity(Tile parent) {
		// TODO Auto-generated method stub
		return null;
	}
}
