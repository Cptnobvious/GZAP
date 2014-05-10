package world.generation.prefab;

import java.io.IOException;

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

	private Prefab prefab = new Prefab(0, 0, 0, 0);
	
	public PrefabPane(){
		super();
		addButton(10, 10, 100, 40, 1, "Prefab Win");
		addButton(130, 10, 100, 40, 2, "Request New");
		addButton(130, 60, 100, 40, 3, "Make Prefab");
		addButton(130, 110, 100, 40, 4, "Save Prefab");
		addButton(130, 160, 100, 40, 5, "Load Prefab");
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
			if (picks == 0){
				this.x1 = newX;
				this.y1 = newY;
				picks = 1;
			} else {
				this.x2 = newX;
				this.y2 = newY;
				picks = 0;
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
			Boot.getGUIHandler().addWindow((GUIWindow)(new DebugGui(-1)));
			break;
		case 2:
			requestPoints(2);
			picks = 0;
			break;
		case 3:
			prefab = new Prefab(x1, y1, x2, y2);
			break;
		case 4:
			try {
				prefab.save();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 5:
			try {
				prefab.buildPrefab(3, 3, "prefabtest");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
}
