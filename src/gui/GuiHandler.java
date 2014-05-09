package gui;


import java.util.ArrayList;

import gui.elements.GUIPane;
import gui.elements.GUIWindow;
import gui.panes.DebugPane;
import gui.windows.DebugGui;

import org.lwjgl.input.Mouse;

import world.generation.prefab.PrefabPane;

public class GuiHandler {

	private static ArrayList<GUIWindow> windowslist = new ArrayList<GUIWindow>();
	private static GUIPane activePane = (GUIPane)new DebugPane();
	private static PanesSelector panesselector = new PanesSelector();
	
	private boolean pointsRequested = false;
	private boolean justRequested = false;
	private int id = -1;
	private int requestedPointsLeft = -1;
	
	public void mouseInput(){
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
		
		if (!justRequested){
			distributePoints(mouseX, mouseY);
			justRequested = true;
		} 
		
		for (int x = 0; x < windowslist.size(); x ++){
			if (windowslist.get(x).recieveMouseEvent(mouseX, mouseY)){
				break;
			}
		}
		
		panesselector.recieveInput(mouseX, mouseY);
		
		activePane.recieveMouseEvent(mouseX, mouseY);

		if (!Mouse.isButtonDown(0) && justRequested){
			justRequested = false;
		}
	}
	
	public void changeActivePane(GUIPane pane){
		activePane = pane;
	}
	
	public void update(){
		
	}
	
	public void draw(){
		for (int x = 0; x < windowslist.size(); x++){
			windowslist.get(x).draw();
		}
		
		panesselector.draw();
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
	
	public void requestPoints(int id, int points){
		this.pointsRequested = true;
		this.id = id;
		this.requestedPointsLeft = points;
		this.justRequested = true;
	}
	
	private void distributePoints(int mouseX, int mouseY){
		if (pointsRequested && id != -1 && requestedPointsLeft != -1){
			if (requestedPointsLeft > 0){
				for (int x = 0; x < windowslist.size(); x++){
					
					if (id != -2 && windowslist.get(x).getID() == id){
						if (Mouse.isButtonDown(0)){
							if (windowslist.get(x).getPoint(mouseX, mouseY)){
								requestedPointsLeft--;
							}
						}
					} 
					
					if (id == -2){
						if (Mouse.isButtonDown(0)){
							if (activePane.getPoint(mouseX, mouseY)){
								requestedPointsLeft--;
							}
						}
					}
					
				}
			} else {
				id = -1;
				pointsRequested = false;
				requestedPointsLeft = -1;
			}
		}
	}
	
}
