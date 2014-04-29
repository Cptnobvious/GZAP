package gui;

public class GuiHandler {

	private static DebugGui debuggui = new DebugGui();
	
	public void messageDistributor(int x, int y){
		debuggui.rightClick(x, y);
	}
	
	public void draw(int x, int y){
		debuggui.showInfo();
	}
	
}
