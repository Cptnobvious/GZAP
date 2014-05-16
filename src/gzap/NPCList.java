package gzap;

import java.util.ArrayList;

import entities.living.AbstractMob;

public class NPCList {

	private ArrayList<AbstractMob> mobs = new ArrayList<AbstractMob>();
	
	public void addNPC(AbstractMob npc){
		mobs.add(npc);
		mobs.get(mobs.size() - 1).setID(getUniqueID());
	}

	public void removeNPC(int id){
		for (int x = 0; x < mobs.size(); x++){
			if (mobs.get(x).getID() == id){
				mobs.remove(x);
			}
		}
	}
	
	public void draw(){
		for (int x = 0; x < mobs.size(); x++){
			AbstractMob current = mobs.get(x);
			
			if (isInSightRange(current.getX(), current.getY())){
				int xDraw = 13 - (Boot.getPlayer().getX() - current.getX());
				xDraw = xDraw * Standards.TILE_SIZE;
				
				int yDraw = 13 - (Boot.getPlayer().getY() - current.getY());
				yDraw = yDraw * Standards.TILE_SIZE;
				
				mobs.get(x).draw(xDraw, yDraw);
			}
		}
	}
	
	public void update(int delta){
		for (int x = 0; x < mobs.size(); x++){
			mobs.get(x)._update(delta);
		}
	}
	
	public boolean isOccupied(int x, int y){
		for (int i = 0; i < mobs.size(); i++){
			int occupiedX = mobs.get(i).getX();
			int occupiedY = mobs.get(i).getY();
			
			if (x == occupiedX && y == occupiedY){
				return true;
			}
		}
		return false;
	}
	
	public boolean isInSightRange(int x, int y){
		int xStart = Boot.getPlayer().getX();
		int yStart = Boot.getPlayer().getY();
		
		int xEnd = xStart + 12;
		xStart = xStart - 12;
		
		int yEnd = yStart + 12;
		yEnd = yEnd - 12;
		
		
		if (x >= xStart || x <= xEnd){
			if (y >= yStart || y <= yEnd){
				return true;
			}
		}
		
		return false;
	}
	
	public int getUniqueID(){
		int id = 0;
		boolean unique = false;
		
		while (!unique){
			unique = true;
			
			for (int x = 0; x < mobs.size(); x++){
				if (id == mobs.get(x).getID()){
					unique = false;
					id++;
				}
			}
		}
		
		return id;
	}
}
