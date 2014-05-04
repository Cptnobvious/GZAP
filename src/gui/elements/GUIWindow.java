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
	private int id;
	private String title = "Unnamed Window";
	
	
	public GUIWindow(int id, String name){
		this.ScreenX = 0;
		this.ScreenY = 0;
		this.Width = 200;
		this.Height = 100;
		this.title = name;
		this.id = id;
	}
	
	public GUIWindow(int id){
		this.ScreenX = 0;
		this.ScreenY = 0;
		this.Width = 200;
		this.Height = 100;
		this.id = id;
	}

	
	public void drawForeground(){
		
	}
	
	public void drawBackground(){
		
		Boot.getTexHandler().bindTexture("null");
		
		glColor4f(0.3f, 0.3f, 0.3f, 1f);
		
		glPushMatrix();
		
		
		glBegin(GL_QUADS);
		{
			glVertex2i(ScreenX, ScreenY + GrabBarHeight);
			
			glVertex2i(ScreenX + Width, ScreenY + GrabBarHeight);

			glVertex2i(ScreenX + Width, ScreenY + Height + GrabBarHeight);
			
			glVertex2i(ScreenX, ScreenY + Height + GrabBarHeight);
		}
		glEnd();
		glPopMatrix();
		
		
		Boot.getTextWriter().drawString(ScreenX, ScreenY + 1, title, new Color4F(0f, 0f, 0f, 1f));
	}
	
	public void drawGrabBar(){
		Boot.getTexHandler().bindTexture("null");
		
		glColor4f(0.7f, 0.7f, 0.7f, 1f);
		
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
		
		if (ScreenX + dx > 0 && ScreenX + Width + dx < 800){
			ScreenX += dx;
		}
		if (ScreenY + dy > 0 && ScreenY + Height + dy < Standards.W_HEIGHT){
			ScreenY += dy;
		}
	}
	
	public void rightClick(int x, int y){
		
	}
	
	
	public int getID(){
		return this.id;
	}
}
