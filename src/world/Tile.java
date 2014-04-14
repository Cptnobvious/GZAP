package world;

import gzap.Standards;
import gzap.TextureHandler;

import org.lwjgl.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Tile {
	
	private int type;
	
	public Tile(int type){
		this.type = type;
	}
	
	public Tile(){
		type = 0;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public void draw(int x, int y){
		
		glColor4f(1f, 1f, 1f, 1f);
		
		Texture temp = TextureHandler.terrain;
		temp.bind();
		
		float xloc = 0;
		float yloc = 0;
		float top;
		float bottom;
		float left;
		float right;
		
		switch (type){
		case 0:
			xloc = 2;
			yloc = 0;
			break;
		case 1:
			xloc = 3;
			yloc = 0;
			break;
		case 2:
			xloc = 0;
			yloc = 0;
			break;
		case 3:
			xloc = 1;
			yloc = 0;
			break;
		default:
			xloc = yloc = 1;
		}
		
		top = (yloc * Standards.TILE_SIZE_ON_TEX_F) / Standards.TEX_SIZE_F;
		bottom = top + (Standards.TILE_SIZE_ON_TEX_F / Standards.TEX_SIZE_F);
		left = (xloc * Standards.TILE_SIZE_ON_TEX_F) / Standards.TEX_SIZE_F;
		right = left + (Standards.TILE_SIZE_ON_TEX_F / Standards.TEX_SIZE_F);
		
		//This push and pop prevent rotate from having a field day
		glPushMatrix();
		
		glTranslatef(((float)x * Standards.TILE_SIZE), ((float)y * Standards.TILE_SIZE), 0f);
		glRotatef(45.0f, 0.0f, 0.0f, 1.0f);
		
		//TODO rotate then translate
		
		
		glBegin(GL_QUADS);
		{
			glTexCoord2f(left, top);
			glVertex2i(0, 0);
			
			glTexCoord2f(right, top);
			glVertex2i(0 + Standards.TILE_SIZE, 0);

			glTexCoord2f(right, bottom);
			glVertex2i(0 + Standards.TILE_SIZE, 0 + Standards.TILE_SIZE);
			
			glTexCoord2f(left, bottom);
			glVertex2i(0, 0 + Standards.TILE_SIZE);
		}
		glEnd();
		glPopMatrix();
		
		//glEnd();
		//glRotatef(-0.1f, 0.0f, 0.0f, -1.0f);
	}
	
}
