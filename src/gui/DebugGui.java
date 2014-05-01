package gui;

import gui.elements.GUIWindow;
import gzap.Boot;
import gzap.Standards;

public class DebugGui extends GUIWindow{

	private int ChunkX = 0;
	private int ChunkY = 0;
	
	public DebugGui(String name){
		super(name);
	}
	
	public void rightClick(int x, int y){
		
		if (x < 800){
			int cx = Boot.getPlayer().getX();
			int cy = Boot.getPlayer().getY();

			this.ChunkX = cx + (x / Standards.TILE_SIZE) - 12;
			this.ChunkY = cy + ((Standards.W_HEIGHT - y) / Standards.TILE_SIZE) - 12;
		}
	}

	@Override
	public void drawBackground() {
		super.drawBackground();
	}

	@Override
	public void drawForeground() {
		String str = "X." + String.valueOf(ChunkX) + " Y." + String.valueOf(ChunkY);
		addText(0, 0, str);
	}
	
}
