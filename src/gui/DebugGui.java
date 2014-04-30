package gui;

import gui.elements.GUIWindow;
import gzap.Boot;
import gzap.Standards;

public class DebugGui extends GUIWindow{

	private int x = 0;
	private int y = 0;
	

	
	public void rightClick(int x, int y){
		
		if (x < 800){
			int cx = Boot.getPlayer().getX();
			int cy = Boot.getPlayer().getY();

			this.x = cx + (x / Standards.TILE_SIZE) - 12;
			this.y = cy + ((Standards.W_HEIGHT - y) / Standards.TILE_SIZE) - 12;
		}
	}

	@Override
	public void drawBackground() {
		// TODO Auto-generated method stub
	}

	@Override
	public void drawForeground() {
		String str = "X." + String.valueOf(x) + " Y." + String.valueOf(y);
		addText(0, 0, str);
	}
	
}
