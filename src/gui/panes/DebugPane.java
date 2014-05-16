package gui.panes;

import org.lwjgl.input.Mouse;

import entities.inanimate.ents.Fridge;
import entities.living.mobs.Zombie;
import gui.elements.GUIPane;
import gui.elements.GUIWindow;
import gui.windows.DebugGui;
import gzap.Boot;
import gzap.Standards;

public class DebugPane extends GUIPane{

	int picks = 0;
	
	public DebugPane(){
		super();
		addButton(10, 10, 100, 40, 1, "Debug");
		addButton(10, 60, 100, 40, 2, "Zombie");
		addButton(10, 110, 100, 40, 3, "Fridge");
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
		
		switch(picks){
		default:
		case 0:
			Boot.getNPCList().addNPC(new Zombie(newX, newY, 0));
			break;
		case 1:
			Boot.getWorldObj().getTileAtCoords(newX, newY).setInanimateEntity(new Fridge(newX, newY, 0));
			break;
		}
		
		
		return true;
	}
	
	@Override
	protected void recieveButtonEvent(int buttonID){
		switch(buttonID){
		default:
		case 1:
			Boot.getGUIHandler().addWindow((GUIWindow)(new DebugGui(-1)));
			break;
		case 2:
			requestPoints(1);
			picks = 0;
			break;
		case 3:
			requestPoints(1);
			picks = 1;
			break;
		}
		
			
	}
	
	@Override 
	public void drawForeground(){
		addText(10, 68, "Mouse Info");
		String str = "X." + Integer.toString(Mouse.getX()) + " Y." + Integer.toString(Standards.W_HEIGHT - Mouse.getY());
		addText(10, 80, str);
	}
}
