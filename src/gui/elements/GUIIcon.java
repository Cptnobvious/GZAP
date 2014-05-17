package gui.elements;

import static org.lwjgl.opengl.GL11.*;
import gzap.Boot;
import gzap.Standards;
import util.Color4F;
import util.TexInfo;

public class GUIIcon {

	TexInfo texInfo;
	protected int iconX;
	protected int iconY;
	
	public GUIIcon(int iconX, int iconY, TexInfo texInfo){
		this.iconX = iconX;
		this.iconY = iconY;
		this.texInfo = texInfo;
	}
	
	public void draw(int x, int y){
		draw(x, y, texInfo);
	}
	
	public void draw(int x, int y, TexInfo texinfo){
		draw(x, y, texinfo, 0);
	}
	
	public void draw(int wX, int wY, TexInfo texinfo, int orientation){
		int screenX = wX + iconX;
		int screenY = wY + iconY;
		
		TexInfo currentTexInfo = texinfo;
		Color4F color = currentTexInfo.getColor4F();
		
		glColor4f(1f, 1f, 1f, 1f);
		glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		
		
		Boot.getTexHandler().bindTexture(currentTexInfo.getTextureName());
		
		float xloc = 0;
		float yloc = 0;
		float top;
		float bottom;
		float left;
		float right;
		float rotation = orientation * 90;
		
		xloc = currentTexInfo.getSpriteSheetX();
		yloc = currentTexInfo.getSpriteSheetY();
		
		top = (yloc * Standards.TILE_SIZE_ON_TEX_F) / Standards.TEX_SIZE_F;
		bottom = top + (Standards.TILE_SIZE_ON_TEX_F / Standards.TEX_SIZE_F);
		left = (xloc * Standards.TILE_SIZE_ON_TEX_F) / Standards.TEX_SIZE_F;
		right = left + (Standards.TILE_SIZE_ON_TEX_F / Standards.TEX_SIZE_F);
		
		float rotXOffset = 0;
		float rotYOffset = 0;
		
		switch (orientation){
		case Standards.NORTH:
			break;
		case Standards.SOUTH:
			rotXOffset = Standards.TILE_SIZE;
			rotYOffset = Standards.TILE_SIZE;
			break;
		case Standards.EAST:
			rotXOffset = Standards.TILE_SIZE;
			break;
		case Standards.WEST:
			rotYOffset = Standards.TILE_SIZE;
			break;
		}
		
		//This push and pop prevent rotate from having a field day
		glPushMatrix();
		//glTranslatef((float)screenX + rotXOffset, (float)screenY + rotYOffset, 0f);
		glRotatef(rotation, 0.0f, 0.0f, 1.0f);
		
		
		glBegin(GL_QUADS);
		{
			glTexCoord2f(left, top);
			glVertex2i(screenX, screenY);
			
			glTexCoord2f(right, top);
			glVertex2i(screenX + Standards.TILE_SIZE, screenY);

			glTexCoord2f(right, bottom);
			glVertex2i(screenX + Standards.TILE_SIZE, screenY + Standards.TILE_SIZE);
			
			glTexCoord2f(left, bottom);
			glVertex2i(screenX, screenY + Standards.TILE_SIZE);
		}
		glEnd();
		glPopMatrix();
		
	}
	
}
