package world.tile;

import gzap.GameRegistry;

public class Tile {

	private AbstractTile base;
	int metadata;
	
	public Tile(int id){
		base = GameRegistry.getTile(id);
		metadata = 0;
	}
	
	public Tile(int id, int metadata){
		base = GameRegistry.getTile(id);
		this.metadata = metadata;
	}
	
	
	public void draw(int x, int y){
		base.draw(x, y, metadata);
	}
	
	public int getMetadata(){
		return metadata;
	}
	
	public void setMetadata(int metadata){
		this.metadata = metadata;
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public void setTileID(int id){
		base = GameRegistry.getTile(id);
	}
	
	public void setTileID(int id, int metadata){
		base = GameRegistry.getTile(id);
		this.metadata = metadata;
	}
}
