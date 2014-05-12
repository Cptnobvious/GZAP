package world.tile;

import java.util.ArrayList;

public enum TileInfo {

	ROAD			(TileID.ROAD, false),
	ROAD_STRIPE		(TileID.ROAD_STRIPE, false),
	SIDEWALK		(TileID.SIDEWALK, false),
	LAWN			(TileID.LAWN, false),
	LAWN_MUD		(TileID.LAWN_MUD, false),
	WOOD_FLOOR		(TileID.WOOD_FLOOR, false),
	CARPET			(TileID.CARPET, false),
	TILE_FLOOR		(TileID.TILE_FLOOR, false),
	WOOD_WALL		(TileID.WOOD_WALL, true);
	
	private int tileID;
	private TileTexInfo texinfo;
	private boolean isSolid;
	
	
	TileInfo(){
		this.tileID = 0;
		//this.texinfo = texinfo.getInfoByID(0);
		this.isSolid = false;
	}
	
	TileInfo(int id, boolean isSolid){
		this.tileID = id;
		//this.texinfo = TileTexInfo.getInfoByID(id);
		this.isSolid = isSolid;
	}
	
	public static TileInfo getInfoByID(int id){
		switch(id){
		default:
		case TileID.ROAD:
			return TileInfo.ROAD;
		case TileID.ROAD_STRIPE:
			return TileInfo.ROAD_STRIPE;
		case TileID.SIDEWALK:
			return TileInfo.SIDEWALK;
		case TileID.LAWN:
			return TileInfo.LAWN;
		case TileID.LAWN_MUD:
			return TileInfo.LAWN_MUD;
		case TileID.WOOD_FLOOR:
			return TileInfo.WOOD_FLOOR;
		case TileID.CARPET:
			return TileInfo.CARPET;
		case TileID.TILE_FLOOR:
			return TileInfo.TILE_FLOOR;
		case TileID.WOOD_WALL:
			return TileInfo.WOOD_WALL;
		}
	
	}
	
	public int getID(){
		return tileID;
	}
	
	public TileTexInfo getTexInfo(){
		return texinfo;
	}
	
	public boolean getSolid(){
		return isSolid;
	}
}
