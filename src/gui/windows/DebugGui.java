package gui.windows;

import gui.elements.GUIWindow;
import gzap.Boot;
import gzap.Standards;

public class DebugGui extends GUIWindow{

	private int posX = 0;
	private int posY = 0;
	
	public DebugGui(int id, String name){
		super(id, name);
		
		addButton(10, 10, 30, 10, 1);
	}
	
	public DebugGui(int id){
		super(id);
		
		addButton(10, 10, 30, 10, 1);
	}
	

	@Override
	public boolean getPoint(int mouseX, int mouseY){
		
		if (mouseX < 800){
			int cx = Boot.getPlayer().getX();
			int cy = Boot.getPlayer().getY();

			this.posX = cx + (mouseX / Standards.TILE_SIZE) - 12;
			this.posY = cy + ((Standards.W_HEIGHT - mouseY) / Standards.TILE_SIZE) - 12;
		}
		
		return true;
	}

	@Override
	protected void recieveButtonEvent(int buttonID){
		if (buttonID == 1){
			requestPoints(1);
		}
	}

	@Override
	public void drawBackground() {
		super.drawBackground();
	}

	@Override
	public void drawForeground() {
		String str = "X." + String.valueOf(posX) + " Y." + String.valueOf(posY);
		addText(0, 0, str);
	}
	
}
