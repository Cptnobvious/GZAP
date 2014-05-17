package entities.living.player;

import util.TexInfo;
import items.ItemID;
import gui.elements.GUIIcon;
import gui.elements.GUIPane;
import gzap.Boot;
import gzap.GameRegistry;

public class PlayerPane extends GUIPane{

	public PlayerPane(){
		allowsMapInteraction = true;
		TexInfo texinfo = new TexInfo(0, 0, "items");
		addIcon(new GUIIcon(10, 10, texinfo));
	}
	
	
	@Override
	public void drawForeground(){
		clearIcons();
		
		for (int i = 0; i < Boot.getPlayer().getInventorySize(); i++){
			if (Boot.getPlayer().getItemInSlot(i) != null){
				addIcon(new GUIIcon(i * 32 + 8, 10, Boot.getPlayer().getItemInSlot(i).getBase().getTexInfo()));
			}
		}
		
		drawIcons();
	}
	
}
