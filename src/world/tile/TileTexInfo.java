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
	TILE_FLOOR		(7, 0);
	
	
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
	
	public TileTexInfo getInfoByID(int id){
		TileTexInfo texInfo;
		
		switch(id){
		default:
		case TileID.ROAD:
			texInfo = TileTexInfo.ROAD;
			break;
		case TileID.ROAD_STRIPE:
			texInfo = TileTexInfo.ROAD_STRIPE;
			break;
		case TileID.SIDEWALK:
			texInfo = TileTexInfo.SIDEWALK;
			break;
		case TileID.LAWN:
			texInfo = TileTexInfo.LAWN;
			break;
		case TileID.LAWN_MUD:
			texInfo = TileTexInfo.LAWN_MUD;
			break;
		case TileID.WOOD_FLOOR:
			texInfo = TileTexInfo.WOOD_FLOOR;
			break;
		case TileID.CARPET:
			texInfo = TileTexInfo.CARPET;
			break;
		case TileID.TILE_FLOOR:
			texInfo = TileTexInfo.TILE_FLOOR;
			break;
		}
	
		return texInfo;
	}
	
}
