package entities;

public abstract class AbstractEntity {

	protected int xLoc;
	protected int yLoc;
	protected int zLoc;
	protected int updateTick = 0;
	protected int updateSpeed = 0;

	public AbstractEntity(int x, int y, int z){
		this.xLoc = x;
		this.yLoc = y;
		this.zLoc = z;
	}
	
	public abstract void draw(int x, int y);

	public void _update(int delta){
		if (updateTick == 0){
			update();
			think();
			updateTick = updateSpeed;
		} else {
			updateTick--;
		}
	}
	
	public void think(){
		
	}
	
	public void update(){
		
	}
	
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
