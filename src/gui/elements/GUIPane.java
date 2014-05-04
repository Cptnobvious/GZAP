package gui.elements;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import gzap.Boot;

public abstract class GUIPane {
	
	private String texturename;
	private ArrayList<GUIButton> buttons = new ArrayList<GUIButton>();
	
	public GUIPane(String texturename){
		this.texturename = texturename;
	}
	
	public GUIPane(){
		this.texturename = "debugmenu";
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
		//Boot.getTextWriter().drawString(x + ScreenX, y + ScreenY + GrabBarHeight, text);
	}
	
	public void rightClick(int x, int y){
		
	}
	
	public void drawButtons(){
		for (int x = 0; x < buttons.size(); x ++){
			buttons.get(x).draw(800, 288);
		}
	}
	
	public void addButton(int x, int y, int width, int height, int buttonID){
		buttons.add(new GUIButton(x, y, width, height, buttonID));
	}
	
}
