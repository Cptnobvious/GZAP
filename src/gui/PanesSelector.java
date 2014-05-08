package gui;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;

import world.generation.prefab.PrefabPane;
import gui.elements.GUIPane;
import gui.panes.DebugPane;
import gzap.Boot;
import gzap.Standards;

public class PanesSelector {

	public void recieveInput(int mouseX, int mouseY){
		mouseY = Standards.W_HEIGHT - mouseY;
		
		if (isInPanesSelector(mouseX, mouseY)){
			if (Mouse.isButtonDown(0)){
				Boot.getGUIHandler().changeActivePane(findPane(mouseX, mouseY));
			}
		}
	}
	
	public boolean isInPanesSelector(int mouseX, int mouseY){
		
		if (mouseX > 800 && mouseX < 1280){
			if (mouseY > 0 && mouseY < 288){
				return true;
			}
		}
		
		return false;
	}
	
	private GUIPane findPane(int mouseX, int mouseY){
		
		
		mouseX = mouseX - 800;
		mouseX = mouseX / 48;
		mouseY = mouseY / 48;
		
		int pane = 0;
		pane = pane + mouseX + (mouseY * 10);
		
		switch (pane){
		default:
		case 0:
			return (GUIPane)(new DebugPane());
		case 1:
			return (GUIPane)(new PrefabPane());
		}
		
	}
	
	public void draw(){

		glColor4f(1f, 1f, 1f, 1f);

		Boot.getTexHandler().bindTexture("panes");

		glPushMatrix();

		glBegin(GL_QUADS);
		{
			glTexCoord2f(0, 0);
			glVertex2i(800, 0);

			glTexCoord2f((480f / 512f), 0);
			glVertex2i(1280, 0);

			glTexCoord2f((480f / 512f), (288f / 512f));
			glVertex2i(1280, 288);

			glTexCoord2f(0, (288f / 512f));
			glVertex2i(800, 288);
		}
		glEnd();
		glPopMatrix();

	}


}
