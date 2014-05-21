package entities.living.player;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;
import gui.elements.GUIPane;
import gui.elements.buttons.GUISlot;
import gzap.Boot;

public class PlayerPane extends GUIPane{

	public PlayerPane(){
		super("playerpane");
		allowsMapInteraction = true;
		linksTo = Boot.getPlayer();

		int j = 0;
		int k = 0;
		int l = -1;
		
		for (int i = 0; i < linksTo.getInventorySize(); i++){
			if (i % 4 == 0 && i != 0){
				k = 0;
				j++;
			}
			if (i % 12 == 0){
				l++;
			}


			addSlot(new GUISlot((k * 35) + 7, (j * 35) + (l * 5) + 7, i, linksTo));


			k++;
		}
	}


	@Override
	public void drawForeground(){
		drawHPBar();
	}

	
	public void drawHPBar(){
		Boot.getTexHandler().bindTexture("null");
		
		int top = 320;
		int left = 1000;
		int right = 1250;
		int bottom = top + 20;
		
		
		glPushMatrix();
		
		right = left + 250;
		
		glColor4f(0.2f, 0.2f, 0.2f, 1f);
		
		glBegin(GL_QUADS);
		{
			glVertex2i(left, top);
			
			glVertex2i(right, top);

			glVertex2i(right, bottom);
			
			glVertex2i(left, bottom);
		}
		
		right = (int) (left + ((double)Boot.getPlayer().getHealth() / (double)100) * 250);
		glColor4f(1f, 0.2f, 0.2f, 1f);
		
		glBegin(GL_QUADS);
		{
			glVertex2i(left, top);
			
			glVertex2i(right, top);

			glVertex2i(right, bottom);
			
			glVertex2i(left, bottom);
		}
		
		
		glEnd();
		glPopMatrix();
	}
}
