package entities.living;

import entities.AbstractEntity;
import gzap.Boot;
import gzap.Standards;

public abstract class AbstractMob extends AbstractEntity{

	protected int health;
	protected int orientation;

	public AbstractMob(int x, int y, int z, int health) {
		super(x, y, z);
		this.health = health;
		this.orientation = Standards.NORTH;
	}

	public boolean canMove(int direction){
		int newX = x;
		int newY = y;
		int mapedge = (Standards.CHUNK_SIZE * Standards.MAP_SIZE) - 1; 

		switch(direction){
		default:
		case Standards.NORTH:
			newY--;
			break;
		case Standards.SOUTH:
			newY++;
			break;
		case Standards.EAST:
			newX++;
			break;
		case Standards.WEST:
			newX--;
			break;
		}

		if (newX >= 0 && newX <= mapedge){
			if (newY >= 0 && newY <= mapedge){

				boolean isSolid = Boot.getWorldObj().getTileAtCoords(newX, newY).isSolid();
				if (!isSolid){
					return true;
				}

			}
		}

		return false;
	}


	public boolean move(int direction){
		if (canMove(direction)){
			switch (direction){
			case Standards.NORTH:
				y--;
				break;
			case Standards.EAST:
				x++;
				break;
			case Standards.SOUTH:
				y++;
				break;
			case Standards.WEST:
				x--;
				break;
			default:
				break;
			}

			orientation = direction;

			return true;
		}
		
		orientation = direction;
		
		return false;
	}

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
