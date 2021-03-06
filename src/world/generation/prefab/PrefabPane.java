package world.generation.prefab;

import java.io.File;
import java.io.IOException;

import world.generation.BlockGenerator;
import world.generation.RoadGenerator;
import world.tile.TileID;
import gui.elements.GUIPane;
import gui.elements.GUIWindow;
import gui.windows.DebugGui;
import gzap.Boot;
import gzap.Standards;

public class PrefabPane extends GUIPane{
	
	private int picks;
	private int x1;
	private int x2;
	private int y1;
	private int y2;

	private int spawnRotation = 0;
	
	private Prefab prefab = new Prefab(0, 0, 0, 0);
	private RoadGenerator roadGen = new RoadGenerator();
	private BlockGenerator blockGen = new BlockGenerator();
	
	public PrefabPane(){
		super();
		addButton(10, 10, 100, 40, 1, "Prefab Win");
		addButton(130, 10, 100, 40, 2, "Request New");
		addButton(130, 60, 100, 40, 4, "Save Prefab");
		addButton(130, 160, 100, 40, 3, "Random Pre");
		addButton(130, 110, 100, 40, 5, "Load Prefab");
		addButton(250, 10, 100, 40, 6, "Spawn Zone");
		addButton(250, 60, 100, 40, 7, "Make Road");
		addButton(250, 110, 100, 40, 8, "Make Block");
	}

	@Override
	public void drawForeground() {
		String str = "X1." + String.valueOf(x1) + " Y1." + String.valueOf(y1);
		String str2 = "X2." + String.valueOf(x2) + " Y2." + String.valueOf(y2);
		addText(10, 70, str);
		addText(10, 90, str2);
		
		int width;
		int height;
		
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
		
		String str3 = "W." + String.valueOf(width) + " H." + String.valueOf(height);
		addText(10, 110, str3);
		}
	
	@Override
	public boolean getPoint(int mouseX, int mouseY){
		int newX = -1;
		int newY = -1;
		
		if (mouseX < 800){
			int cx = Boot.getPlayer().getX();
			int cy = Boot.getPlayer().getY();

			newX = cx + (mouseX / Standards.TILE_SIZE) - 12;
			newY = cy + ((Standards.W_HEIGHT - mouseY) / Standards.TILE_SIZE) - 12;
		}
		
		
		if (newX != -1 && newY != -1){
			switch(picks){
			default:
				break;
			case 0:
				this.x1 = newX;
				this.y1 = newY;
				picks = 1;
				break;
			case 1:
				this.x2 = newX;
				this.y2 = newY;
				picks = 0;
				break;
			case 3:
				spawnBox(24, newX, newY);
				break;
			case 4:
				loadPrefab(newX, newY);
				break;
			case 5:
				loadRandomPrefab(newX, newY);
				break;
			}

		}
		
		return true;
	}
	
	@Override
	protected void recieveButtonEvent(int buttonID){
		switch(buttonID){
		default:
			break;
		case 1:
			Boot.getGUIHandler().addWindow((new DebugGui(-1)));
			break;
		case 2:
			requestPoints(2);
			picks = 0;
			break;
		case 4:
			makePrefab();
			break;
		case 3:
			requestPoints(1);
			picks = 5;
			break;
		case 5:
			requestPoints(1);
			picks = 4;
			break;
		case 6:
			requestPoints(1);
			picks = 3;
			break;
		case 7:
			roadGen.layRoad(x1, y1, x2, y2);
			break;
		case 8:
			blockGen.makeNewBlock(x1, y1, 3);
			break;
		}
	}
	
	
	private void makePrefab(){
		prefab = new Prefab(x1, y1, x2, y2);
		boolean found = true;
		int filenumber = 0;
		String pathTag = "HousePrefab";
		String path = "";
		
		do {
			path = "data/" + pathTag + String.valueOf(filenumber) + ".pfb";
			File f = new File(path);
			if (!f.isFile()){
				found = false;
			} else {
				filenumber++;
			}
		} while (found);
		
		
		
		try {
			prefab.save(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void loadPrefab(int spawnX, int spawnY){
		String pathTag = "HousePrefab";
		String path = "";
		
		path = "data/" + pathTag + String.valueOf(spawnRotation) + ".pfb";
		File f = new File(path);
		
		if (f.isFile()){
			try {
				prefab.buildPrefab(spawnX, spawnY, path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			spawnRotation++;
		} else {
			spawnRotation = 0;
		}
		
	}
	
	private void spawnBox(int size, int xStart, int yStart){
		size = size + 2;
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				if (i == 0 || i == size - 1 || j == 0 || j == size - 1){
					Boot.getWorldObj().getTileAtCoords(xStart + i, yStart + j).setTileID(TileID.ASPHALT);
				}
			}
		}
	}
	
	private void loadRandomPrefab(int xStart, int yStart){
		try {
			prefab.randomPrefabFromFile(xStart, yStart);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
