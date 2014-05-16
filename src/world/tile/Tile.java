package world.tile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import entities.inanimate.AbstractInanimateEntity;
import gzap.GameRegistry;
import gzap.Standards;

public class Tile {

	private AbstractTile base;
	private int metadata;
	private boolean isSolid;
	private int orientation = Standards.NORTH;
	private int mapX;
	private int mapY;
	private AbstractInanimateEntity AIE = null;
	private TileEntity TE = null;
	
	public Tile(int id, int x, int y){
		base = GameRegistry.getTile(id);
		mapX = x;
		mapY = y;
		isSolid = base.getSolid();
		TE = base.getTileEntity(this);
		metadata = 0;
	}
	
	public Tile(int id, int metadata, int x, int y){
		base = GameRegistry.getTile(id);
		mapX = x;
		mapY = y;
		isSolid = base.getSolid();
		TE = base.getTileEntity(this);
		this.metadata = metadata;
	}
	
	public void update(){
		if (TE != null){
			TE.update();
		}
	}
	
	public int getID(){
		return base.getBaseType();
	}
	
	public AbstractInanimateEntity getInanimateEntity(){
		return AIE;
	}
	
	public void setInanimateEntity(AbstractInanimateEntity ent){
		AIE = ent;
	}
	
	public TileEntity getTileEntity(){
		return TE;
	}
	
	public void draw(int x, int y){
		base.draw(x, y, orientation, metadata);
		if (AIE != null){
			AIE.draw(x, y);
		}
	}
	
	public int getMetadata(){
		return metadata;
	}
	
	public void setMetadata(int metadata){
		this.metadata = metadata;
	}
	
	public void setSolid(boolean solid){
		isSolid = solid;
	}
	
	public boolean isSolid(){
		return isSolid;
	}
	
	public int getMapX() {
		return mapX;
	}

	public int getMapY() {
		return mapY;
	}

	public void setTileID(int id){
		base = GameRegistry.getTile(id);
		isSolid = base.getSolid();
		TE = base.getTileEntity(this);
		metadata = 0;
	}
	
	public void setTileID(int id, int metadata){
		base = GameRegistry.getTile(id);
		isSolid = base.getSolid();
		TE = base.getTileEntity(this);
		this.metadata = metadata;
	}
	
	public void save(FileOutputStream out) throws IOException{
		out.write(base.getBaseType());
	}
	
	public void load(FileInputStream in) throws IOException{
		int c = in.read();
		setTileID(c);
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public void getMouseEvent(int button) {
		TE.getMouseEvent(button);
	}
}
