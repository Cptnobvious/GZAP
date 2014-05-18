package gui.elements;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;

import util.TexInfo;
import gui.elements.buttons.GUIButton;
import gui.elements.buttons.GUISlot;
import gzap.Boot;
import interfaces.Inventory;

public class GUIInventoryWindow extends GUIWindow{

	private Inventory linksTo;
	private ArrayList<GUISlot> slots = new ArrayList<GUISlot>();
	
	public GUIInventoryWindow(int id, String name, Inventory inventory) {
		super(id, name);
		linksTo = inventory;
		this.Width = (5 * 32) + 4;
		this.Height = (((linksTo.getInventorySize() / 5) + 1) * 32) + 8;
		addSlot(new GUISlot(0, 0, 0, linksTo));
	}

	
	@Override
	public void draw(){
		drawGrabBar();
		drawBackground();
		drawForeground();
		drawButtons();
		drawSlots();
	}
	
	@Override
	public boolean recieveMouseEvent(int mouseX, int mouseY){
		if (isInWindow(mouseX, mouseY) || grabbed){
			if (Mouse.isButtonDown(0) && mouseLeft){
				if (grabbed){
					move(Mouse.getDX(), Mouse.getDY());
				}
			} else if (Mouse.isButtonDown(0) && !mouseLeft){
				mouseLeft = true;
				if (isInGrabBar(mouseX, mouseY)){
					grabbed = true;
				}
			} else {
				mouseLeft = false;
				grabbed = false;
			}
			
			
			
			if (Mouse.isButtonDown(1)){
				if (isInGrabBar(mouseX, mouseY)){
					Boot.getGUIHandler().removeWindow(this.id);
				}
			}
			
			//Deal with buttons
			
			for (int i = 0; i < buttons.size(); i++){
				if (buttons.get(i).isOnButton(ScreenX, ScreenY, mouseX, mouseY)){
					buttons.get(i).setHover(true);
				} else {
					buttons.get(i).setHover(false);
				}
				
				if (Mouse.isButtonDown(0) && !mouseLeftButton){
					int buttonID = buttons.get(i).onClick(ScreenX, ScreenY, mouseX, mouseY);
					if (buttonID != -1){
						recieveButtonEvent(buttonID);
					}
					mouseLeftButton = true;
				} else if (Mouse.isButtonDown(0) && mouseLeftButton) {
					//do nothing
				} else {
					mouseLeftButton = false;
				}
			}
			
			//Deal with slots
			
			for (int i = 0; i < slots.size(); i++){
				if (slots.get(i).isOnSlot(ScreenX, ScreenY, mouseX, mouseY)){
					slots.get(i).setHover(true);
				} else {
					slots.get(i).setHover(false);
				}
				
				if (Mouse.isButtonDown(0) && !mouseLeftButton){
					int buttonID = slots.get(i).onClick(ScreenX, ScreenY, mouseX, mouseY);
					if (buttonID != -1){
						recieveSlotEvent(buttonID);
					}
					mouseLeftButton = true;
				} else if (Mouse.isButtonDown(0) && mouseLeftButton) {
					//do nothing
				} else {
					mouseLeftButton = false;
				}
			}
			
			return true;
		}
		
		//Do unhovering
		for (int i = 0; i < slots.size(); i++){
			if (!slots.get(i).isOnSlot(ScreenX, ScreenY, mouseX, mouseY)){
				slots.get(i).setHover(false);
			}
		}
		
		return false;
	}
	
	@Override
	public void drawForeground(){
		clearIcons();
		
		int j = 0;
		int k = 0;
		
		for (int i = 0; i < linksTo.getInventorySize(); i++){
			if (i % 5 == 0 && i != 0){
				k = 0;
				j++;
			}
			
			if (linksTo.getItemInSlot(i) != null){
				TexInfo texinfo = linksTo.getItemInSlot(i).getBase().getTexInfo();
				texinfo.setTextureName("items");
				addIcon(new GUIIcon((k * 32) + 2, (j * 32) + 4, texinfo));
			}
			
			k++;
		}
		
		drawIcons();
	}
	
	public void addSlot(GUISlot slot){
		slot.setY(slot.getY() + GrabBarHeight);
		slots.add(slot);
	}
	
	public void drawSlots(){
		for (int x = 0; x < slots.size(); x++){
			slots.get(x).draw(ScreenX, ScreenY);
		}
	}
	
	protected void recieveSlotEvent(int slotID){
		
	}
	
}