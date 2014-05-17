package gui;


import java.util.ArrayList;

import gui.elements.GUIPane;
import gui.elements.GUIWindow;
import gui.panes.DebugPane;
import gzap.Boot;
import gzap.Standards;

import org.lwjgl.input.Mouse;

import world.tile.Tile;
import world.tile.TileEntity;

public class GuiHandler {

	private static ArrayList<GUIWindow> windowslist = new ArrayList<GUIWindow>();
	private static GUIPane activePane = (GUIPane)new DebugPane();
	private static PanesSelector panesselector = new PanesSelector();
	
	private boolean pointsRequested = false;
	private boolean justRequested = false;
	private int id = -1;
	private int requestedPointsLeft = -1;
	
	private boolean gotWorldInput = false;
	
	private int ticksPassed = 0;
	
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
		
		activePane.__recieveMouseEvent(mouseX, mouseY);

		if (!Mouse.isButtonDown(0) && justRequested){
			justRequested = false;
		}
		
		if (Mouse.isButtonDown(0) && activePane.doesAllowMapInteraction() && !gotWorldInput){
			worldInput(mouseX, mouseY);
			gotWorldInput = true;
		}
		
		ticksPassed++;
		if (ticksPassed > 10){
			gotWorldInput = false;
		}
	}
	
	public void changeActivePane(GUIPane pane){
		activePane = pane;
	}
	
	public void worldInput(int mouseX, int mouseY){
		
		int button = -1;
		
		if (Mouse.isButtonDown(0)){
			button = 0;
		}
		if (mouseX < 800){
			int newX = -1;
			int newY = -1;
			int cx = Boot.getPlayer().getX();
			int cy = Boot.getPlayer().getY();

			newX = cx + (mouseX / Standards.TILE_SIZE) - 12;
			newY = cy + ((Standards.W_HEIGHT - mouseY) / Standards.TILE_SIZE) - 12;
			
			Tile active = Boot.getWorldObj().getTileAtCoords(newX, newY);
			//TileEntity active = Boot.getWorldObj().getTileAtCoords(newX, newY).getTileEntity();
			if (active != null){
				active.getMouseEvent(button);
			}
		}
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
			if (requestedPointsLeft > 0 && id != -2){
				for (int x = 0; x < windowslist.size(); x++){
					
					if (windowslist.get(x).getID() == id){
						if (Mouse.isButtonDown(0)){
							if (windowslist.get(x).getPoint(mouseX, mouseY)){
								requestedPointsLeft--;
							}
						}
					} 
					
				}
			} else if (requestedPointsLeft > 0 && id == -2){
				if (Mouse.isButtonDown(0)){
					if (activePane.getPoint(mouseX, mouseY)){
						requestedPointsLeft--;
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
