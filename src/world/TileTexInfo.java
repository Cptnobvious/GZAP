package world;

public enum TileTexInfo {

	//X / Y on the terrain.png
	
	ROAD			(0, 0),
	ROAD_STRIPE		(1, 0),
	SIDEWALK		(2, 0),
	LAWN			(3, 0),
	LAWN_MUD		(4, 0);
	
	
	private int spriteSheetX;
	private int spriteSheetY;
	
	TileTexInfo(int spriteSheetX, int spriteSheetY){
		this.spriteSheetX = spriteSheetX;
		this.spriteSheetY = spriteSheetY;
	}

	public int getSpriteSheetX() {
		return spriteSheetX;
	}

	public int getSpriteSheetY() {
		return spriteSheetY;
	}
	
}
