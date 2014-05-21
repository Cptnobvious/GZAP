package gui;


import interfaces.Inventory;
import items.Item;

import java.util.ArrayList;

import entities.living.AbstractMob;
import gui.elements.GUIIcon;
import gui.elements.GUIPane;
import gui.elements.GUIWindow;
import gui.panes.DebugPane;
import gzap.Boot;
import gzap.Standards;

import org.lwjgl.input.Mouse;

import util.TexInfo;
import world.tile.Tile;

public class GuiHandler {

	private static ArrayList<GUIWindow> windowslist = new ArrayList<GUIWindow>();
	private static GUIPane activePane = new DebugPane();
	private static PanesSelector panesselector = new PanesSelector();
	
	private boolean pointsRequested = false;
	private boolean justRequested = false;
	private int id = -1;
	private int requestedPointsLeft = -1;
	
	private boolean gotWorldInput = false;
	
	private int ticksPassed = 0;
	
	private Inventory inventory = null;
	private int slotID = -1;
	private Item grabbed = null;
	
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
		if (ticksPassed > 20){
			gotWorldInput = false;
			ticksPassed = 0;
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
			
			AbstractMob mob = Boot.getNPCList().NPCAtLocation(newX, newY);
			if (mob != null){
				Boot.getPlayer().mobInteract(mob);
			} else {
				Tile active = Boot.getWorldObj().getTileAtCoords(newX, newY);
				if (active != null){
					active.getMouseEvent(button);
				}
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
		drawGrabbed();
	}
	
	public void drawSideMenu(){
		activePane.draw();
	}
	
	public void addWindow(GUIWindow window){
		if (window.getID() == -1){
			window.setID(getUniqueWindowID());
		}
		
		int dx = Mouse.getX();
		int dy = Mouse.getY() - Standards.W_HEIGHT;
		
		window.move(dx, dy);
		
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
	
	public void updateWindows(){
		for (int i = 0; i < windowslist.size(); i++){
			windowslist.get(i).update();
		}
	}
	
	public void interactSlot(Inventory inv, int slot){
		if (inv.getItemInSlot(slot) != null && grabbed == null){
			this.inventory = inv;
			this.slotID = slot;
			grabbed = inventory.getItemInSlot(slotID);
			inventory.setItemInSlot(slotID, null);
		} else if (grabbed != null && inv.getItemInSlot(slot) == null){
			inv.setItemInSlot(slot, grabbed);
			this.inventory = null;
			this.slotID = -1;
			grabbed = null;
		}
	}
	
	public void drawGrabbed(){
		if (grabbed != null){
			TexInfo tempinfo = grabbed.getBase().getTexInfo();
			tempinfo.setTextureName("items");
			GUIIcon drawIcon = new GUIIcon(0, 0, tempinfo);
			drawIcon.draw(Mouse.getX() - 16, Standards.W_HEIGHT - Mouse.getY() - 16);
		}
	}
	
}
