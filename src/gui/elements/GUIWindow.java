package gui.elements;

import static org.lwjgl.opengl.GL11.*;
import util.Color4F;
import gzap.Boot;
import gzap.Standards;

public abstract class GUIWindow {
	
	private int ScreenX;
	private int ScreenY;
	private int Width;
	private int Height;
	private int GrabBarHeight = 12;
	private String title = "Unnamed Window";
	
	public GUIWindow(){
		this.ScreenX = 0;
		this.ScreenY = 0;
		this.Width = 200;
		this.Height = 10;
	}

	
	public abstract void drawBackground();
	public abstract void drawForeground();
	
	public void drawGrabBar(){
		Boot.getTexHandler().bindTexture("null");
		
		glColor4f(0.8f, 0.8f, 0.8f, 1f);
		
		glPushMatrix();
		
		
		glBegin(GL_QUADS);
		{
			glVertex2i(ScreenX, ScreenY);
			
			glVertex2i(ScreenX + Width, ScreenY);

			glVertex2i(ScreenX + Width, ScreenY + GrabBarHeight);
			
			glVertex2i(ScreenX, ScreenY + GrabBarHeight);
		}
		glEnd();
		glPopMatrix();
		
		
		Boot.getTextWriter().drawString(ScreenX, ScreenY + 1, title, new Color4F(0f, 0f, 0f, 1f));
	}
	
	public void draw(){
		drawGrabBar();
		drawBackground();
		drawForeground();
	}
	
	public void addText(int x, int y, String text){
		Boot.getTextWriter().drawString(x + ScreenX, y + ScreenY + GrabBarHeight, text);
	}
	
	public void move(int dx, int dy){
		dy = dy * -1;
		
		if (ScreenX + dx > 0 && ScreenX + Width + dx < Standards.W_WIDTH){
			ScreenX += dx;
		}
		if (ScreenY + dy > 0 && ScreenY + Height + dy < Standards.W_HEIGHT){
			ScreenY += dy;
		}
	}
	
}
