package world;

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
		
		top = (yloc * 16) / 64;
		bottom = top + (16f / 64f);
		left = (xloc * 16) / 64;
		right = left + (16f / 64f);
		
		
		glBegin(GL_QUADS);
		{
			glTexCoord2f(left, top);
			glVertex2i(x, y);
			
			glTexCoord2f(right, top);
			glVertex2i(x + 16, y);

			glTexCoord2f(right, bottom);
			glVertex2i(x + 16, y + 16);
			
			glTexCoord2f(left, bottom);
			glVertex2i(x, y + 16);
		}
		glEnd();
	}
	
}
