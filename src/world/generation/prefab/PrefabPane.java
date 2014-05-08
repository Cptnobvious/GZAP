package world.generation.prefab;

import gui.elements.GUIPane;
import gui.elements.GUIWindow;
import gui.windows.DebugGui;
import gzap.Boot;

public class PrefabPane extends GUIPane{

	public PrefabPane(){
		super();
		addButton(10, 10, 100, 40, 1, "Prefab Window");
	}

	
	@Override
	protected void recieveButtonEvent(int buttonID){
		if (buttonID == 1){
			Boot.getGUIHandler().addWindow((GUIWindow)(new DebugGui(-1)));
		}
	}
}
