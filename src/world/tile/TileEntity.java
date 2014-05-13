package world.tile;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public abstract class TileEntity {

	protected Tile parent;
	
	public TileEntity(Tile parent){
		this.parent = parent;
	}
	
	public Tile getParent(){
		return parent;
	}
	
	public abstract void update();
	public abstract void getMouseEvent(int button);
	public abstract void save(FileOutputStream out);
	public abstract void load(FileInputStream in);
	
}
