package world;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import world.tile.Tile;
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
					contents[x][y] = new Tile(2);
				} else if (x == 6 || x == 7 || x == 9 || x == 10){
					contents[x][y] = new Tile(0);
				} else if (x == 8){
					contents[x][y] = new Tile(1);
				} else if (x == Standards.CHUNK_SIZE - 1) {
					contents[x][y] = new Tile(2);
				} else {
					contents[x][y] = new Tile(2);
				}
				
				if (x == 0){
					contents[x][y] = new Tile(2);
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

	public void save(int chunkX, int chunkY) throws IOException{
		String path = Standards.WORLD_SAVE_LOCATION + "/" + chunkX + "x" + chunkY + ".cnk";
		FileOutputStream out = new FileOutputStream(path);

		for (int x = 0; x < Standards.CHUNK_SIZE; x++){
			for (int y = 0; y < Standards.CHUNK_SIZE; y++){
				//contents[x][y].save(out);
			}
		}

		//Don't forget to close
		out.close();
	}
	
	public void load(int chunkX, int chunkY) throws IOException{
		
		String path = Standards.WORLD_SAVE_LOCATION + "/" + chunkX + "x" + chunkY + ".cnk";
		FileInputStream in = new FileInputStream(path);
		
		for (int x = 0; x < Standards.CHUNK_SIZE; x++){
			for (int y = 0; y < Standards.CHUNK_SIZE; y++){
				//contents[x][y].load(in);
			}
		}

		
		//Don't forget to close
		in.close();
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
