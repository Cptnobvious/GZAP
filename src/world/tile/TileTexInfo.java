package world.tile;

import util.Color4F;

public class TileTexInfo {
	
	private int spriteSheetX;
	private int spriteSheetY;
	private Color4F color4f;
	
	public TileTexInfo(int spriteSheetX, int spriteSheetY){
		this.spriteSheetX = spriteSheetX;
		this.spriteSheetY = spriteSheetY;
		
		this.color4f = new Color4F();
	}
	
	public TileTexInfo(int spriteSheetX, int spriteSheetY, float red, float green, float blue, float alpha){
		this.spriteSheetX = spriteSheetX;
		this.spriteSheetY = spriteSheetY;
		
		this.color4f = new Color4F(red, green, blue, alpha);
	}
	
	public TileTexInfo(int spriteSheetX, int spriteSheetY, int red, int green, int blue, int alpha){
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
	
}
