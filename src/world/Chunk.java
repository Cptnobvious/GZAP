package world;

import gzap.Standards;

public class Chunk {

	private boolean isLoaded = false;
	private Tile[][] contents;

	public Chunk(){
		contents = new Tile[Standards.CHUNK_SIZE][Standards.CHUNK_SIZE];
		init();
	}

	private void init(){

		for (int x = 0; x < Standards.CHUNK_SIZE; x++){
			for (int y = 0; y < Standards.CHUNK_SIZE; y++){
				if (x == 5 || x == 11){
					contents[x][y] = new Tile(TileTexInfo.SIDEWALK);
				} else if (x == 6 || x == 7 || x == 9 || x == 10){
					contents[x][y] = new Tile(TileTexInfo.ROAD);
				} else if (x == 8){
					contents[x][y] = new Tile(TileTexInfo.ROAD_STRIPE);
				} else if (x == Standards.CHUNK_SIZE - 1) {
					contents[x][y] = new Tile(TileTexInfo.ROAD_STRIPE);
				} else {
					contents[x][y] = new Tile(TileTexInfo.LAWN);
				}
				
				if (x == 0){
					contents[x][y] = new Tile(TileTexInfo.SIDEWALK);
				}
			}
		}

		isLoaded = true;
	}

	public Tile getTile(int x, int y){
		if (x < Standards.CHUNK_SIZE && x >= 0){
			if (y < Standards.CHUNK_SIZE && y >= 0){
				return contents[x][y];
			}
		}

		return null;
	}

	//This might be removed
	@Deprecated
	public void draw(int screenX, int screenY, int xStart, int yStart, int xStop, int yStop){
		for (int x = xStart; x < xStop; x++){
			for (int y = yStart; y < yStop; y++){
				contents[x][y].draw(screenX + (x * Standards.TILE_SIZE), screenY + (y * Standards.TILE_SIZE));
			}
		}
	}

}
