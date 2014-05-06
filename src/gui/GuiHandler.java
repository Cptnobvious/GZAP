package gui;


import java.util.ArrayList;

import gui.elements.GUIWindow;
import gui.panes.DebugPane;
import gui.windows.DebugGui;

import org.lwjgl.input.Mouse;

public class GuiHandler {

	//private static DebugGui debuggui = new DebugGui(0, "Debug Gui");
	private static boolean mouseState = false;
	//private GUIWindow activewindow = (GUIWindow)debuggui;
	private static ArrayList<GUIWindow> windowslist = new ArrayList<GUIWindow>();
	private static DebugPane activePane = new DebugPane();
	
	
	public void mouseInput(){
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
		
		activePane.recieveMouseEvent(mouseX, mouseY);
	}
	
	public void messageHandler(int x, int y){
		//activewindow.rightClick(x, y);
	}
	
	public void update(){
		
	}
	
	public void draw(){
		for (int x = 0; x < windowslist.size(); x++){
			windowslist.get(x).draw();
		}
		
		drawSideMenu();
	}
	
	public void drawSideMenu(){
		activePane.draw();
	}
	
	public void addWindow(GUIWindow window){
		if (window.getID() == -1){
			window.setID(windowslist.size() + 1);
		}
		
		windowslist.add(window);
	}
	
}
