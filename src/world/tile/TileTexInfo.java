package world.tile;

import util.Color4F;

public enum TileTexInfo {

	//X / Y on the terrain.png
	
	ROAD			(0, 0),
	ROAD_STRIPE		(1, 0),
	SIDEWALK		(2, 0),
	LAWN			(3, 0),
	LAWN_MUD		(4, 0),
	WOOD_FLOOR		(5, 0),
	CARPET			(6, 0, 0.7f, 0.7f, 1f, 1f),
	TILE_FLOOR		(7, 0),
	WOOD_WALL		(8, 0);
	
	
	private int spriteSheetX;
	private int spriteSheetY;
	private Color4F color4f;
	
	TileTexInfo(int spriteSheetX, int spriteSheetY){
		this.spriteSheetX = spriteSheetX;
		this.spriteSheetY = spriteSheetY;
		
		this.color4f = new Color4F();
	}
	
	TileTexInfo(int spriteSheetX, int spriteSheetY, float red, float green, float blue, float alpha){
		this.spriteSheetX = spriteSheetX;
		this.spriteSheetY = spriteSheetY;
		
		this.color4f = new Color4F(red, green, blue, alpha);
	}

	public int getSpriteSheetX() {
		return spriteSheetX;
	}

	public int getSpriteSheetY() {
		return spriteSheetY;
	}
	
	public Color4F getColor4F(){
		return color4f;
	}
	
	public static TileTexInfo getInfoByID(int id){
		
		switch(id){
		default:
		case TileID.ROAD:
			return TileTexInfo.ROAD;
		case TileID.ROAD_STRIPE:
			return TileTexInfo.ROAD_STRIPE;
		case TileID.SIDEWALK:
			return TileTexInfo.SIDEWALK;
		case TileID.LAWN:
			return TileTexInfo.LAWN;
		case TileID.LAWN_MUD:
			return TileTexInfo.LAWN_MUD;
		case TileID.WOOD_FLOOR:
			return TileTexInfo.WOOD_FLOOR;
		case TileID.CARPET:
			return TileTexInfo.CARPET;
		case TileID.TILE_FLOOR:
			return TileTexInfo.TILE_FLOOR;
		case TileID.WOOD_WALL:
			return TileTexInfo.WOOD_WALL;
		}
	
	}
	
}
