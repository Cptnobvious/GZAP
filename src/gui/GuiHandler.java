package gui;

import gui.elements.GUIWindow;

import org.lwjgl.input.Mouse;

public class GuiHandler {

	private static DebugGui debuggui = new DebugGui();
	private static boolean mouseState = false;
	private static GUIWindow activewindow = (GUIWindow)debuggui;
	
	
	public void mouseInput(){
		if (Mouse.isButtonDown(0) && mouseState){
			debuggui.move(Mouse.getDX(), Mouse.getDY());
		} else if (Mouse.isButtonDown(0)){
			mouseState = true;
		} else {
			mouseState = false;
		}
		
		
		if (Mouse.isButtonDown(1)){
			messageHandler(Mouse.getX(), Mouse.getY());
		} 
	}
	
	public void messageHandler(int x, int y){
		debuggui.rightClick(x, y);
	}
	
	public void draw(int x, int y){
		debuggui.draw();
	}
	
}
