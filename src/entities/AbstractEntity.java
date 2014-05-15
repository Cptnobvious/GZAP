package entities;

public abstract class AbstractEntity {

	protected int xLoc;
	protected int yLoc;
	protected int zLoc;
	

	public AbstractEntity(int x, int y, int z){
		this.xLoc = x;
		this.yLoc = y;
		this.zLoc = z;
	}
	
	public abstract void draw(int x, int y);

	public int getX() {
		return xLoc;
	}

	public void setX(int x) {
		this.xLoc = x;
	}

	public int getY() {
		return yLoc;
	}

	public void setY(int y) {
		this.yLoc = y;
	}

	public int getZ() {
		return zLoc;
	}

	public void setZ(int z) {
		this.zLoc = z;
	}
	
}
