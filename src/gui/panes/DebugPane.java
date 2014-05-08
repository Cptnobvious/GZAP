package gui.panes;

import org.lwjgl.input.Mouse;

import gui.elements.GUIPane;
import gui.elements.GUIWindow;
import gui.windows.DebugGui;
import gzap.Boot;
import gzap.Standards;

public class DebugPane extends GUIPane{

	public DebugPane(){
		super();
		addButton(10, 10, 100, 40, 1, "Debug");
	}

	
	@Override
	protected void recieveButtonEvent(int buttonID){
		if (buttonID == 1){
			Boot.getGUIHandler().addWindow((GUIWindow)(new DebugGui(-1)));
		}
	}
	
	@Override 
	public void drawForeground(){
		addText(10, 68, "Mouse Info");
		String str = "X." + Integer.toString(Mouse.getX()) + " Y." + Integer.toString(Standards.W_HEIGHT - Mouse.getY());
		addText(10, 80, str);
	}
}
