package world.generation;

import java.io.IOException;

import world.generation.prefab.Prefab;

public class BlockGenerator {
	
	public void makeNewBlock(int x, int y, int length){
		x = x + 8;
		
		Prefab prefab = new Prefab(0, 0, 0, 0);
		RoadGenerator roadGen = new RoadGenerator();
		
		int xStart = x - 8;
		int xFar = x + (length * 24) + 8;
		int yFar =  y + ((2 * 8) + (24 * 2));
		
		roadGen.layRoad(xStart, y, xStart, yFar);
		roadGen.layRoad(xStart, y, xFar, y);
		roadGen.layRoad(x + (length * 24), y, x + (length * 24), y + ((2 * 8) + (24 * 2)));
		roadGen.layRoad(xStart, yFar - 8, xFar, yFar - 8);
		
		y = y + 8;
		
		for (int i = 0; i < length; i++){
			for (int j = 0; j < 2; j++){
				int xPos = (i * 24) + x;
				int yPos = (j * 24) + y;
				
				try {
					prefab.randomPrefabFromFile(xPos, yPos);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
