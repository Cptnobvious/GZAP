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
				int xDraw = 15 - Boot.getPlayer().getX() - current.getX();
				xDraw = xDraw * Standards.TILE_SIZE;
				
				int yDraw = 15 - Boot.getPlayer().getY() - current.getY();
				yDraw = yDraw * Standards.TILE_SIZE;
				
				mobs.get(x).draw(xDraw, yDraw);
			}
		}
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
