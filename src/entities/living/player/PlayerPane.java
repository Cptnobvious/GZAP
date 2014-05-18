package entities.living.player;

import interfaces.Inventory;
import util.TexInfo;
import gui.elements.GUIIcon;
import gui.elements.GUIPane;
import gui.elements.buttons.GUISlot;
import gzap.Boot;

public class PlayerPane extends GUIPane{

	private Inventory inventory;

	public PlayerPane(){
		allowsMapInteraction = true;
		inventory = Boot.getPlayer();

		int j = 0;
		int k = 0;

		for (int i = 0; i < inventory.getInventorySize(); i++){
			if (i % 5 == 0 && i != 0){
				k = 0;
				j++;
			}


			addSlot(new GUISlot((k * 36) + 10, (j * 36 + 2) + 4, i, inventory));


			k++;
		}
	}


	@Override
	public void drawForeground(){

	}

}
