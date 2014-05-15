package world.generation;

import world.tile.TileID;
import gzap.Boot;
import gzap.Standards;

public class RoadGenerator {

	public void layRoad(int x1, int y1, int x2, int y2){
		
		int xpotential = 0;
		int ypotential = 0;
		boolean northSouth = true;
		int length = 0;
		int direction = 1;
		
		if (x1 > x2){
			xpotential = x1 - x2;
			direction = -1;
		} else {
			xpotential = x2 - x1;
		}
		
		if (y1 > y2){
			ypotential = y1 - y2;
			direction = -1;
		} else {
			ypotential = y2 - y1;
		}
		
		if (xpotential >= ypotential){
			northSouth = false;
		} else {
			northSouth = true;
		}
		
		
		if (northSouth){
			length = ypotential;
			
			for (int i = 0; i < length; i++){
				for (int j = 0; j < 8; j++){
					if (j == 0 || j == 7){
						if (Boot.getWorldObj().getTileAtCoords(x1 + j, y1 + (i * direction)).getID() != TileID.ASPHALT){
							Boot.getWorldObj().getTileAtCoords(x1 + j, y1 + (i * direction)).setTileID(TileID.SIDEWALK);
						}
					} else if (j == 3) {
						if (Boot.getWorldObj().getTileAtCoords(x1 + j, y1 + (i * direction)).getID() != TileID.ASPHALT){
							Boot.getWorldObj().getTileAtCoords(x1 + j, y1 + (i * direction)).setTileID(TileID.ASPHALT, 1);
							Boot.getWorldObj().getTileAtCoords(x1 + j, y1 + (i * direction)).setOrientation(Standards.SOUTH);
						} else {
							Boot.getWorldObj().getTileAtCoords(x1 + j, y1 + (i * direction)).setTileID(TileID.ASPHALT, 0);
						}
					} else if (j == 4) {
						if (Boot.getWorldObj().getTileAtCoords(x1 + j, y1 + (i * direction)).getID() != TileID.ASPHALT){
							Boot.getWorldObj().getTileAtCoords(x1 + j, y1 + (i * direction)).setTileID(TileID.ASPHALT, 1);
						} else {
							Boot.getWorldObj().getTileAtCoords(x1 + j, y1 + (i * direction)).setTileID(TileID.ASPHALT, 0);
						}
					} else {
						Boot.getWorldObj().getTileAtCoords(x1 + j, y1 + (i * direction)).setTileID(TileID.ASPHALT);
					}
				}
			}
			
		} else {
			length = xpotential;
			
			for (int i = 0; i < length; i++){
				for (int j = 0; j < 8; j++){
					if (j == 0 || j == 7){
						if (Boot.getWorldObj().getTileAtCoords(x1 + (i * direction), y1 + j).getID() != TileID.ASPHALT){
							Boot.getWorldObj().getTileAtCoords(x1 + (i * direction), y1 + j).setTileID(TileID.SIDEWALK);
						}
					} else if (j == 3) {
						if (Boot.getWorldObj().getTileAtCoords(x1 + (i * direction), y1 + j).getID() != TileID.ASPHALT){
							Boot.getWorldObj().getTileAtCoords(x1 + (i * direction), y1 + j).setTileID(TileID.ASPHALT, 1);
							Boot.getWorldObj().getTileAtCoords(x1 + (i * direction), y1 + j).setOrientation(Standards.WEST);
						} else {
							Boot.getWorldObj().getTileAtCoords(x1 + (i * direction), y1 + j).setTileID(TileID.ASPHALT, 0);
						}
					} else if (j == 4){
						if (Boot.getWorldObj().getTileAtCoords(x1 + (i * direction), y1 + j).getID() != TileID.ASPHALT){
							Boot.getWorldObj().getTileAtCoords(x1 + (i * direction), y1 + j).setTileID(TileID.ASPHALT, 1);
							Boot.getWorldObj().getTileAtCoords(x1 + (i * direction), y1 + j).setOrientation(Standards.EAST);
						} else {
							Boot.getWorldObj().getTileAtCoords(x1 + (i * direction), y1 + j).setTileID(TileID.ASPHALT, 0);
						}
					} else {
						Boot.getWorldObj().getTileAtCoords(x1 + (i * direction), y1 + j).setTileID(TileID.ASPHALT);
					}
				}
			}
		}
		
		
	}
	
}
