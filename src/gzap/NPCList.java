package gzap;

import java.util.ArrayList;

import util.PointMath;
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
	
	public ArrayList<AbstractMob> findNearMobs(int x, int y, int range, int selfID){
		ArrayList<AbstractMob> nearby = new ArrayList<AbstractMob>();
		
		for (int i = 0; i < mobs.size(); i++){
			AbstractMob current = mobs.get(i);
			
			double distance = PointMath.distance2Points(current.getX(), current.getY(), x, y);
			
			
			if (distance < range + 1){
				if (mobs.get(i).getID() != selfID){
					nearby.add(mobs.get(i));
				}
			}
		}
		
		double distance = PointMath.distance2Points(x, y, Boot.getPlayer().getX(), Boot.getPlayer().getY());
		
		if (distance < range){
			nearby.add(Boot.getPlayer());
		}
		
		if (nearby.isEmpty()){
			return null;
		}  else {
			return nearby;
		}
	}
	
	public AbstractMob NPCAtLocation(int x, int y){
		for (int i = 0; i < mobs.size(); i++){
			AbstractMob mob = mobs.get(i);
			if (mob.getX() == x && mob.getY() == y){
				return mob;
			}
		}
		
		return null;
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
