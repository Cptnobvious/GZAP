package world.generation.prefab;

import gzap.Boot;
import gzap.Standards;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Prefab {

	private int width;
	private int height;
	
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
	public Prefab(int x1, int y1, int x2, int y2){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		
		if (x2 > x1){
			width = x2 - x1;
		} else {
			width = x1 - x2;
		}
		
		if (y2 > y1){
			height = y2 - y1;
		} else {
			height = y1 - y2;
		}
		
		height++;
		width++;
	}
	
	
	public void save(String name) throws IOException{
		String path = name;
		FileOutputStream out = new FileOutputStream(path);

		out.write(width);
		out.write(height);
		
		int startx = x1;
		int starty = y1;
		
		for (int x = 0; x < width; x++){
			for (int y = 0; y < height; y++){
				//Boot.getWorldObj().getTileAtCoords(x1 + x, y1 + y).save(out);
			}
		}

		//Don't forget to close
		out.close();
	}
	
	public void buildPrefab(int x, int y, String name) throws IOException{
		String path = name; 
		FileInputStream in = new FileInputStream(path);
		
		width = in.read();
		height = in.read();
		
		for (int i = 0; i < width; i++){
			for (int j = 0; j < height; j++){
				//Boot.getWorldObj().getTileAtCoords(x + i, y + j).init(in.read());
			}
		}

		
		//Don't forget to close
		in.close();
	}
}
