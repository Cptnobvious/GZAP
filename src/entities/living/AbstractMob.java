package entities.living;

import entities.AbstractEntity;
import gzap.Boot;
import gzap.Standards;

public abstract class AbstractMob extends AbstractEntity{

	protected int health;
	protected int orientation;
	protected int id = -1;

	public AbstractMob(int x, int y, int z, int health) {
		super(x, y, z);
		this.health = health;
		this.orientation = Standards.NORTH;
	}

	public boolean canMove(int direction){
		int newX = xLoc;
		int newY = yLoc;
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
					if (newX != Boot.getPlayer().getX() || newY != Boot.getPlayer().getY()){
						if (!Boot.getNPCList().isOccupied(newX, newY)){
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	public void setID(int a){
		id = a;
	}

	public int getID(){
		return id;
	}

	public boolean move(int direction){
		if (canMove(direction)){
			switch (direction){
			case Standards.NORTH:
				yLoc--;
				break;
			case Standards.EAST:
				xLoc++;
				break;
			case Standards.SOUTH:
				yLoc++;
				break;
			case Standards.WEST:
				xLoc--;
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

	@Override
	public void update(){
	}

	public int getHealth() {
		return health;
	}

	public boolean damage(int damage){
		setHealth(this.health - damage);
		if (health <= 0) {
			kill();
		}
		return true;
	}

	public void kill(){
		Boot.getNPCList().removeNPC(id);
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
