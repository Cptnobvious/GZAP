package gui.panes;

import gui.elements.GUIPane;
import gui.elements.GUIWindow;
import gui.windows.DebugGui;
import gzap.Boot;

public class DebugPane extends GUIPane{

	public DebugPane(){
		super();
		addButton(10, 10, 100, 40, 1);
	}

	
	@Override
	protected void recieveButtonEvent(int buttonID){
		if (buttonID == 1){
			Boot.getGUIHandler().addWindow((GUIWindow)(new DebugGui(-1)));
		}
	}
}
