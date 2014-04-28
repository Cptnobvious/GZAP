package gui;

import gzap.Boot;
import gzap.Standards;

public class DebugGui {

	private int x = 0;
	private int y = 0;
	
	public void showInfo(){
		String str = "X." + String.valueOf(x) + " Y." + String.valueOf(y);
		Boot.getTextWriter().drawString(810, 10, str);
	}
	
	public void rightClick(int x, int y){
		
		if (x < 800){
			int cx = Boot.getPlayer().getX();
			int cy = Boot.getPlayer().getY();

			this.x = cx + (x / Standards.TILE_SIZE) - 12;
			this.y = cy + ((Standards.W_HEIGHT - y) / Standards.TILE_SIZE) - 12;
		}
	}
	
}
