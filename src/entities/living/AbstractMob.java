package entities.living;

import entities.AbstractEntity;
import gzap.Standards;

public abstract class AbstractMob extends AbstractEntity{
	
	protected int health;
	protected int orientation;
	
	public AbstractMob(int x, int y, int z, int health) {
		super(x, y, z);
		this.health = health;
		this.orientation = Standards.NORTH;
	}
	
	public abstract boolean canMove();
	public abstract boolean move(int direction);

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

}
