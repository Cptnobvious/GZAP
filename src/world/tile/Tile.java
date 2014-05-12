package world.tile;

import gzap.GameRegistry;

public class Tile {

	private AbstractTile base;
	int metadata;
	
	public Tile(int id){
		base = GameRegistry.getTile(id);
		metadata = 0;
	}
	
	
	public void draw(int x, int y){
		base.draw(x, y, metadata);
	}
	
	public boolean isSolid(){
		return false;
	}
}
