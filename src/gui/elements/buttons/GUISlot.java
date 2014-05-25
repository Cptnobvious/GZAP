package gui.elements.buttons;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glVertex2i;
import interfaces.Inventory;
import gui.elements.GUIIcon;
import gzap.Boot;
import gzap.Standards;


public class GUISlot{

	protected int x;
	protected int y;
	private int InventorySlot;
	protected boolean hover = false;
	private int itemID;
	protected Inventory parent;
	private GUIIcon item;
	
	public GUISlot(int x, int y, int invslot, Inventory parent) {
		this.x = x;
		this.y = y;
		this.InventorySlot = invslot;
		this.parent = parent;
		
		if (parent.getItemInSlot(invslot) != null){
			itemID = parent.getItemInSlot(invslot).getBase().getID();
		} else {
			itemID = -1;
		}
	}
	
	public int getItemID(){
		return itemID;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setHover(boolean hover){
		this.hover = hover;
	}
	
	public int onClick(int wX, int wY, int mouseX, int mouseY){
		if (isOnSlot(wX, wY, mouseX, mouseY)){
			return InventorySlot;
		}
		
		return -1;
	}
	
	public boolean isOnSlot(int wX, int wY, int mouseX, int mouseY){
		mouseY = Standards.W_HEIGHT - mouseY - 1;
		
		if ((mouseX > wX + x) && (mouseX < wX + x + Standards.TILE_SIZE)){
			if ((mouseY > wY + y) && (mouseY < wY + y + Standards.TILE_SIZE)){
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
			glColor4f(0.0f, 0.0f, 0.7f, 0.1f);
		}
			
		if (parent.getItemInSlot(InventorySlot) == null){
			item = null;
		} else {
			item = new GUIIcon(0, 0, parent.getItemInSlot(InventorySlot).getBase().getTexInfo());
		}
		
		
		glPushMatrix();
		
		
		glBegin(GL_QUADS);
		{
			glVertex2i(ScreenX, ScreenY);
			
			glVertex2i(ScreenX + Standards.TILE_SIZE, ScreenY);

			glVertex2i(ScreenX + Standards.TILE_SIZE, ScreenY + Standards.TILE_SIZE);
			
			glVertex2i(ScreenX, ScreenY + Standards.TILE_SIZE);
		}
		glEnd();
		glPopMatrix();
		
		if (item != null){
			item.draw(ScreenX, ScreenY);
		} 
		
		
	}
}
