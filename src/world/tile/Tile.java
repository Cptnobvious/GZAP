package world.tile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import gzap.GameRegistry;

public class Tile {

	private AbstractTile base;
	int metadata;
	boolean isSolid;
	
	public Tile(int id){
		base = GameRegistry.getTile(id);
		isSolid = base.getSolid();
		metadata = 0;
	}
	
	public Tile(int id, int metadata){
		base = GameRegistry.getTile(id);
		isSolid = base.getSolid();
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
		return isSolid;
	}
	
	public void setTileID(int id){
		base = GameRegistry.getTile(id);
		isSolid = base.getSolid();
	}
	
	public void setTileID(int id, int metadata){
		base = GameRegistry.getTile(id);
		this.metadata = metadata;
	}
	
	public void save(FileOutputStream out) throws IOException{
		out.write(base.getBaseType());
	}
	
	public void load(FileInputStream in) throws IOException{
		int c = in.read();
		setTileID(c);
	}
}
