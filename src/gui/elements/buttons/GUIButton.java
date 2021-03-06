package gui.elements.buttons;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glVertex2i;
import gzap.Boot;
import gzap.Standards;

public class GUIButton {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	private int buttonID;
	protected boolean hover = false;
	private String name = "";
	
	public GUIButton(int x, int y, int width, int height, int buttonID){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.buttonID = buttonID;
	}
	
	public GUIButton(int x, int y, int width, int height, int buttonID, String name){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.buttonID = buttonID;
		this.name = name;
	}
	
	public void setHover(boolean hover){
		this.hover = hover;
	}
	
	public int onClick(int wX, int wY, int mouseX, int mouseY){
		if (isOnButton(wX, wY, mouseX, mouseY)){
			return buttonID;
		}
		
		return -1;
	}
	
	public boolean isOnButton(int wX, int wY, int mouseX, int mouseY){
		mouseY = Standards.W_HEIGHT - mouseY - 1;
		
		if ((mouseX > wX + x) && (mouseX < wX + x + width)){
			if ((mouseY > wY + y) && (mouseY < wY + y + height)){
				return true;
			}
		}
		
		return false;
	}
	
	public void draw(int wX, int wY){
		
		int ScreenX = wX + x;
		int ScreenY = wY + y;
		
		Boot.getTexHandler().bindTexture("null");
		
		if (this.hover){
			glColor4f(0.9f, 0.9f, 0.9f, 1f);
		} else {
			glColor4f(0.3f, 0.3f, 0.7f, 1f);
		}
			
		glPushMatrix();
		
		
		glBegin(GL_QUADS);
		{
			glVertex2i(ScreenX, ScreenY);
			
			glVertex2i(ScreenX + width, ScreenY);

			glVertex2i(ScreenX + width, ScreenY + height);
			
			glVertex2i(ScreenX, ScreenY + height);
		}
		glEnd();
		glPopMatrix();
		
		Boot.getTextWriter().drawString(wX + x, wY + y, name);
	}
}
