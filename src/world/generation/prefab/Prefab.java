package world.generation.prefab;

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
	}
	
}
