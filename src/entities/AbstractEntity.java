package entities;

public abstract class AbstractEntity {

	protected int xLoc;
	protected int yLoc;
	protected int zLoc;
	protected int deltaTime = 0;
	protected int updateRate;

	public AbstractEntity(int x, int y, int z){
		this.xLoc = x;
		this.yLoc = y;
		this.zLoc = z;
		this.updateRate = 20;
	}
	
	public abstract void draw(int x, int y);
	
	

	public void _update(int delta){
		
		if (deltaTime == 0){
			update();
		}
		
		deltaTime++;
		if (deltaTime == updateRate){
			deltaTime = 0;
		}	
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
