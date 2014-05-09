package world.generation.prefab;

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

	public PrefabPane(){
		super();
		addButton(10, 10, 100, 40, 1, "Prefab Win");
		addButton(130, 10, 100, 40, 2, "Request New");
	}

	@Override
	public void drawForeground() {
		String str = "X1." + String.valueOf(x1) + " Y1." + String.valueOf(y1);
		String str2 = "X2." + String.valueOf(x2) + " Y2." + String.valueOf(y2);
		addText(10, 70, str);
		addText(10, 90, str2);
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
		}
	}
}
