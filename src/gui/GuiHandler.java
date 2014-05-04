package gui;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import gui.elements.GUIWindow;
import gzap.Boot;

import org.lwjgl.input.Mouse;

public class GuiHandler {

	private static DebugGui debuggui = new DebugGui("Debug Gui");
	private static boolean mouseState = false;
	private GUIWindow activewindow = (GUIWindow)debuggui;
	private static ArrayList<GUIWindow> windowslist = new ArrayList<GUIWindow>();
	
	
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
		Boot.getTexHandler().bindTexture("debugmenu");
		
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
	
}
