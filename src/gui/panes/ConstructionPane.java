package gui.panes;

import org.lwjgl.input.Mouse;

import util.PointMath;
import gui.elements.GUIPane;
import gui.elements.buttons.GUIButton;
import gui.elements.buttons.TerrainButton;
import gzap.Boot;
import gzap.GameRegistry;
import gzap.Standards;

public class ConstructionPane extends GUIPane{

	private int ID = 8;
	private int[] allowed = {0, 1, 2, 3, 4, 5, 6};

	public ConstructionPane(){
		super();
		
		int buttonY = 10;
		int buttonX = 10;
		int offset = 0;
		
		for (int i = 0; i < allowed.length; i ++){
			if ((i + offset) % 8 == 0){
				buttonY = (((i + offset) / 8) * (32 + 4)) + 10;
			}
			
			
			if (GameRegistry.getTile(i).getSubTiles() > 0){
				for (int j = 0; j < GameRegistry.getTile(i).getSubTiles(); j++){
					buttonX = (((i + offset) % 8)* (32 + 4)) + 10;
					addButton((GUIButton)(new TerrainButton(buttonX, buttonY, allowed[i], j)));
					offset++;
				}
				offset--;
			} else {
				buttonX = (((i + offset) % 8)* (32 + 4)) + 10;
				addButton((GUIButton)(new TerrainButton(buttonX, buttonY, allowed[i], 0)));
			}
		}
	}

	@Override
	public void drawForeground() {

	}

	@Override
	public void recieveMouseEvent(int mouseX, int mouseY){
		if (mouseX < 800){
			int newX = -1;
			int newY = -1;
			int cx = Boot.getPlayer().getX();
			int cy = Boot.getPlayer().getY();

			newX = cx + (mouseX / Standards.TILE_SIZE) - 12;
			newY = cy + ((Standards.W_HEIGHT - mouseY) / Standards.TILE_SIZE) - 12;

			if (Mouse.isButtonDown(0)){
				if (PointMath.distance2Points(newX, newY, cx, cy) < 3){
					Boot.getWorldObj().getTileAtCoords(newX, newY).setTileID(ID);
				}
			}
			
			if (Mouse.isButtonDown(1)){
				if (PointMath.distance2Points(newX, newY, cx, cy) < 3){
					int meta = Boot.getWorldObj().getTileAtCoords(newX, newY).getMetadata();
					Boot.getWorldObj().getTileAtCoords(newX, newY).setMetadata(meta + 1);
				}
			}
		}
	}

	@Override
	protected void recieveButtonEvent(int buttonID){
		ID = buttonID;
	}
}
