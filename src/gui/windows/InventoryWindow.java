package gui.windows;

import util.TexInfo;
import interfaces.Inventory;
import gui.elements.GUIIcon;
import gui.elements.GUIWindow;
import gzap.Boot;

public class InventoryWindow extends GUIWindow{

	private Inventory linksTo;
	
	public InventoryWindow(int id, String name, Inventory inventory) {
		super(id, name);
		linksTo = inventory;
	}

	
	@Override
	public void drawForeground(){
		clearIcons();
		
		for (int i = 0; i < linksTo.getInventorySize(); i++){
			if (linksTo.getItemInSlot(i) != null){
				TexInfo texinfo = linksTo.getItemInSlot(i).getBase().getTexInfo();
				texinfo.setTextureName("items");
				addIcon(new GUIIcon((i * 32) + 2, 4, texinfo));
			}
		}
		
		drawIcons();
	}
	
}
