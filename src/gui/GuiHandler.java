package gui;


import java.util.ArrayList;

import gui.elements.GUIWindow;
import gui.panes.DebugPane;
import gui.windows.DebugGui;

import org.lwjgl.input.Mouse;

public class GuiHandler {

	private static DebugGui debuggui = new DebugGui(0, "Debug Gui");
	private static boolean mouseState = false;
	private GUIWindow activewindow = (GUIWindow)debuggui;
	private static ArrayList<GUIWindow> windowslist = new ArrayList<GUIWindow>();
	private static DebugPane activePane = new DebugPane();
	
	
	public void mouseInput(){
		if (Mouse.isButtonDown(0) && mouseState){
			activewindow.move(Mouse.getDX(), Mouse.getDY());
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
		activewindow.rightClick(x, y);
	}
	
	public void update(){
		
	}
	
	public void draw(int x, int y){
		activewindow = (GUIWindow)debuggui;
		activewindow.draw();
		drawSideMenu();
	}
	
	public void drawSideMenu(){
		activePane.draw();
	}
	
}
