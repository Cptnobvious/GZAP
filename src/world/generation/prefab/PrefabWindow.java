package world.generation.prefab;

import gui.elements.GUIWindow;
import gzap.Boot;
import gzap.Standards;

public class PrefabWindow extends GUIWindow{

	private int posX = 0;
	private int posY = 0;
	private int posX2 = 0;
	private int posY2 = 0;
	private int picks = 0;
	
	public PrefabWindow(int id){
		super(id, "Prefab Window");
		
		addButton(10, 10, 30, 10, 1);
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
				this.posX = newX;
				this.posY = newY;
				picks = 1;
			} else {
				this.posX2 = newX;
				this.posY2 = newY;
				picks = 0;
			}
		}
		
		return true;
	}

	@Override
	protected void recieveButtonEvent(int buttonID){
		if (buttonID == 1){
			requestPoints(2);
			picks = 0;
		}
	}

	@Override
	public void drawBackground() {
		super.drawBackground();
	}

	@Override
	public void drawForeground() {
		String str = "X." + String.valueOf(posX) + " Y." + String.valueOf(posY);
		String str2 = "X1." + String.valueOf(posX2) + " Y2." + String.valueOf(posY2);
		addText(0, 0, str);
		addText(0, 12, str2);
	}
	
}
