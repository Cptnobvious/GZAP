package world;

import gzap.Standards;

public class Map {

	private static Chunk[][] world;
	
	public Map(){
		world = new Chunk[Standards.MAP_SIZE][Standards.MAP_SIZE];
		for (int x = 0; x < Standards.MAP_SIZE; x++){
			for (int y = 0; y < Standards.MAP_SIZE; y++){
				world[x][y] = new Chunk();
			}
		}
	}
	
	public void draw(int windowX, int windowY, int xStart, int yStart){
		for (int x = 0; x < Standards.MAP_TILES_TO_DRAW; x++){
			for (int y = 0; y < Standards.MAP_TILES_TO_DRAW; y++){
				
				//The +1s are due to a quirk in the drawing code that sets it off-center
				//TODO figure out why that's happening
				
				int cx = x + xStart + 1;
				int cy = y + yStart + 1;
				
				if (isOnMap(cx, cy)){
					int drawX = x * Standards.TILE_SIZE;
					int drawY = y * Standards.TILE_SIZE;
					
					getTileAtCoords(cx, cy).draw(drawX, drawY);
				}
			}
		}
	}
	
	//Gets the tile or your coordinates
	public Tile getTileAtCoords(int x, int y){
		if (isOnMap(x, y)){
			int cx = x % Standards.CHUNK_SIZE;
			int cy = y % Standards.CHUNK_SIZE;
			return getChunkOfCoords(x, y).getTile(cx, cy);
		}
		return null;
	}
	
	//Returns the chunk your coordinates are in
	public Chunk getChunkOfCoords(int x, int y){
		if (isOnMap(x, y)){
			
			int cx = (x / Standards.CHUNK_SIZE);
			int cy = (y / Standards.CHUNK_SIZE);
			
			return world[cx][cy]; 
		}
		
		return null;
	}
	
	public boolean isOnMap(int x, int y){
		if (x >= 0 && x < (Standards.CHUNK_SIZE * Standards.MAP_SIZE)){
			if (y >= 0 && y < (Standards.CHUNK_SIZE * Standards.MAP_SIZE)){
				return true;
			}
		}
		
		return false;
	}
}
