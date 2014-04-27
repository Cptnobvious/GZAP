package util;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2i;
import gzap.Boot;
import gzap.Standards;

public class TextWriter {

	public void init(){
		
	}
	
	
	private float getLetterLocation(char c){
		float offset;
		float xloc;
		xloc = 5f / 512f;
		
		switch(c){
		default:
		case 'a':
		case 'A':
			offset = 0;
			break;
		case 'b':
		case 'B':
			offset = 1;
			break;
		}
		
		return (xloc * offset);
	}
	
	public void drawLetter(int ScreenX, int ScreenY, char c){
		
		float charlength = 5f / 512f;
		float height = 5f / 8f;
		float start = getLetterLocation(c);
		float end = start + charlength;
		
		Boot.getTexHandler().bindTexture("text");
		
		glColor4f(1f, 1f, 1f, 1f);
		
		glPushMatrix();
		
		glTranslatef(ScreenX, ScreenY, 0f);
		glRotatef(0.0f, 0.0f, 0.0f, 1.0f);
		
		
		glBegin(GL_QUADS);
		{
			glTexCoord2f(start, 0);
			glVertex2i(0, 0);
			
			glTexCoord2f(end, 0);
			glVertex2i(10, 0);

			glTexCoord2f(end, height);
			glVertex2i(10, 10);
			
			glTexCoord2f(start, height);
			glVertex2i(0, 10);
		}
		glEnd();
		glPopMatrix();
	}
	
	public void drawString(int ScreenX, int ScreenY, String text){
		
		for (int x = 0; x < text.length(); x++){
			char c = text.charAt(x);
			drawLetter(ScreenX + (x * 10), ScreenY, c);
		}
		
	}
}
