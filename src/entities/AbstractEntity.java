package entities;

public abstract class AbstractEntity {

	protected int xLoc;
	protected int yLoc;
	protected int zLoc;
	protected int deltaTime = 0;
	protected int thinkTime = 0;
	protected int updateRate;
	protected int thinkRate;

	public AbstractEntity(int x, int y, int z){
		this.xLoc = x;
		this.yLoc = y;
		this.zLoc = z;
		this.updateRate = 12;
		this.thinkRate = 20;
	}
	
	public abstract void draw(int x, int y);
	
	

	public void _update(int delta){
		
		if (deltaTime == 0){
			update();
		}
		
		if (thinkTime == 0){
			think();
		}
		
		thinkTime++;
		deltaTime++;
		
		if (thinkTime == thinkRate){
			thinkTime = 0;
		}
		
		if (deltaTime == updateRate){
			deltaTime = 0;
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
