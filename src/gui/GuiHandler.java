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
		
		
		for (int x = 0; x < windowslist.size(); x ++){
			windowslist.get(x).recieveMouseEvent(mouseX, mouseY);
		}
		
		
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
			window.setID(getUniqueWindowID());
		}
		
		windowslist.add(window);
	}
	
	public void removeWindow(int id){
		for (int x = 0; x < windowslist.size(); x++){
			if (windowslist.get(x).getID() == id){
				windowslist.remove(x);
			}
		}
	}
	
	public int getUniqueWindowID(){
		int id = 0;
		boolean unique = false;
		
		while (!unique){
			unique = true;
			
			for (int x = 0; x < windowslist.size(); x++){
				if (id == windowslist.get(x).getID()){
					unique = false;
					id++;
				}
			}
		}
		
		return id;
	}
	
}
