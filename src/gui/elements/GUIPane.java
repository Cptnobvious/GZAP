package gui.elements;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;

import gui.elements.buttons.GUIButton;
import gzap.Boot;

public abstract class GUIPane {
	
	private String texturename;
	private ArrayList<GUIButton> buttons = new ArrayList<GUIButton>();
	private ArrayList<GUIIcon> icons = new ArrayList<GUIIcon>();
	private boolean mouseStatus = false;
	protected boolean allowsMapInteraction = false;
	
	public GUIPane(String texturename){
		this.texturename = texturename;
	}
	
	public GUIPane(){
		this.texturename = "debugmenu";
	}
	
	public boolean doesAllowMapInteraction(){
		return allowsMapInteraction;
	}

	public void setAllowsMapInteraction(boolean allowsMapInteraction) {
		this.allowsMapInteraction = allowsMapInteraction;
	}

	public void drawForeground(){
		
	}
	
	public void drawBackground(){
		Boot.getTexHandler().bindTexture(texturename);
		
		glColor4f(1f, 1f, 1f, 1f);
		
		glPushMatrix();
		
		
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0f, 0f);
			glVertex2i(800, 288);
			
			glTexCoord2f((480f / 512f), 0f);
			glVertex2i(1280, 288);

			glTexCoord2f((480f / 512f), 1f);
			glVertex2i(1280, 800);
			
			glTexCoord2f(0f, 1f);
			glVertex2i(800, 800);
		}
		glEnd();
		glPopMatrix();
	}
	
	public void draw(){
		drawBackground();
		drawForeground();
		drawButtons();
	}
	
	public void addText(int x, int y, String text){
		Boot.getTextWriter().drawString(800 + x, 288 + y, text);
	}
	
	public void drawButtons(){
		for (int x = 0; x < buttons.size(); x++){
			buttons.get(x).draw(800, 288);
		}
	}
	
	public void addIcon(GUIIcon icon){
		icons.add(icon);
	}
	
	public void drawIcons(){
		for (int x = 0; x < icons.size(); x++){
			icons.get(x).draw(800, 288);
		}
	}
	
	public void clearIcons(){
		icons.clear();
	}
	
	public void addButton(GUIButton newButton){
		buttons.add(newButton);
	}
	
	public void addButton(int x, int y, int width, int height, int buttonID){
		buttons.add(new GUIButton(x, y, width, height, buttonID));
	}
	
	public void addButton(int x, int y, int width, int height, int buttonID, String name){
		buttons.add(new GUIButton(x, y, width, height, buttonID, name));
	}
	
	public void __recieveMouseEvent(int x, int y){
		boolean left = false;
		
		if (Mouse.isButtonDown(0)){
			left = true;
		}
		
		for (int i = 0; i < buttons.size(); i++){
			if (buttons.get(i).isOnButton(800, 288, x, y)){
				buttons.get(i).setHover(true);
			} else {
				buttons.get(i).setHover(false);
			}
			
			if (left && !mouseStatus){
				int buttonID = buttons.get(i).onClick(800, 288, x, y);
				if (buttonID != -1){
					recieveButtonEvent(buttonID);
					mouseStatus = true;
				}
			} else if (left && mouseStatus) {
				//do nothing
			} else {
				mouseStatus = false;
			}
		}
		
		recieveMouseEvent(x, y);
	}
	
	public void recieveMouseEvent(int mouseX, int mouseY){
		
	}
	
	protected void recieveButtonEvent(int buttonID){
		
	}
	
	public void requestPoints(int number){
		Boot.getGUIHandler().requestPoints(-2, number);
	}
	
	public boolean getPoint(int mouseX, int mouseY){
		return true;
	}
	
}
