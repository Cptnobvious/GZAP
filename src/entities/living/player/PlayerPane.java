package entities.living.player;

import gui.elements.GUIPane;
import gui.elements.buttons.GUISlot;
import gzap.Boot;

public class PlayerPane extends GUIPane{

	public PlayerPane(){
		super("playerpane");
		allowsMapInteraction = true;
		linksTo = Boot.getPlayer();

		int j = 0;
		int k = 0;
		int l = -1;
		
		for (int i = 0; i < linksTo.getInventorySize(); i++){
			if (i % 4 == 0 && i != 0){
				k = 0;
				j++;
			}
			if (i % 12 == 0){
				l++;
			}


			addSlot(new GUISlot((k * 35) + 7, (j * 35) + (l * 5) + 7, i, linksTo));


			k++;
		}
	}


	@Override
	public void drawForeground(){
		
	}

}
