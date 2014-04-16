package entities;

public abstract class AbstractEntity {

	protected int x;
	protected int y;
	protected int z;
	

	public AbstractEntity(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public abstract void draw(int x, int y);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
}
