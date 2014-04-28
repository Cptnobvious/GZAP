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
		case 'c':
		case 'C':
			offset = 2;
			break;
		case 'd':
		case 'D':
			offset = 3;
			break;
		case 'e':
		case 'E':
			offset = 4;
			break;
		case 'f':
		case 'F':
			offset = 5;
			break;
		case 'g':
		case 'G':
			offset = 6;
			break;
		case 'h':
		case 'H':
			offset = 7;
			break;
		case 'i':
		case 'I':
			offset = 8;
			break;
		case 'j':
		case 'J':
			offset = 9;
			break;
		case 'k':
		case 'K':
			offset = 10;
			break;
		case 'l':
		case 'L':
			offset = 11;
			break;
		case 'm':
		case 'M':
			offset = 12;
			break;
		case 'n':
		case 'N':
			offset = 13;
			break;
		case 'o':
		case 'O':
			offset = 14;
			break;
		case 'p':
		case 'P':
			offset = 15;
			break;
		case 'q':
		case 'Q':
			offset = 16;
			break;
		case 'r':
		case 'R':
			offset = 17;
			break;
		case 's':
		case 'S':
			offset = 18;
			break;
		case 't':
		case 'T':
			offset = 19;
			break;
		case 'u':
		case 'U':
			offset = 20;
			break;
		case 'v':
		case 'V':
			offset = 21;
			break;
		case 'w':
		case 'W':
			offset = 22;
			break;
		case 'x':
		case 'X':
			offset = 23;
			break;
		case 'y':
		case 'Y':
			offset = 24;
			break;
		case 'z':
		case 'Z':
			offset = 25;
			break;
		case '1':
			offset = 26;
			break;
		case '2':
			offset = 27;
			break;
		case '3':
			offset = 28;
			break;
		case '4':
			offset = 29;
			break;
		case '5':
			offset = 30;
			break;
		case '6':
			offset = 31;
			break;
		case '7':
			offset = 32;
			break;
		case '8':
			offset = 33;
			break;
		case '9':
			offset = 34;
			break;
		case '0':
			offset = 35;
			break;
		case '.':
			offset = 36;
			break;
		case '!':
			offset = 37;
			break;
		case '?':
			offset = 38;
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
			
			if (c != ' '){
				drawLetter(ScreenX + (x * 9), ScreenY, c);
			}
		}
		
	}
}
