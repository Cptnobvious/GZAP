package entities.living.player;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2i;
import entities.living.AbstractMob;
import gzap.Boot;
import gzap.Standards;

public class Player extends AbstractMob{

	public Player(int x, int y, int z, int health) {
		super(x, y, z, health);
	}


	@Override
	public void draw(int x, int y) {

		//TODO fix this, it's due to a quirk in the drawing code that offsets the draw by one
		x = x - Standards.TILE_SIZE;
		y = y - Standards.TILE_SIZE;
		
		Boot.getTexHandler().bindTexture("player");

		float rotation = this.getOrientation() * 90;
		float rotXOffset = 0;
		float rotYOffset = 0;
		
		switch (this.getOrientation()){
		case Standards.NORTH:
			break;
		case Standards.SOUTH:
			rotXOffset = Standards.TILE_SIZE;
			rotYOffset = Standards.TILE_SIZE;
			break;
		case Standards.EAST:
			rotXOffset = Standards.TILE_SIZE;
			//rotYOffset = Standards.TILE_SIZE;
			break;
		case Standards.WEST:
			rotYOffset = Standards.TILE_SIZE;
			break;
		}
		
		glPushMatrix();

		//glTranslatef(((float)x * Standards.TILE_SIZE), ((float)y * Standards.TILE_SIZE), 0f);
		glTranslatef((float)x + rotXOffset, (float)y + rotYOffset, 0f);
		glRotatef(rotation, 0.0f, 0.0f, 1.0f);


		glBegin(GL_QUADS);
		{
			glTexCoord2f(0 ,0);
			glVertex2i(0, 0);

			glTexCoord2f(1, 0);
			glVertex2i(0 + Standards.TILE_SIZE, 0);

			glTexCoord2f(1, 1);
			glVertex2i(0 + Standards.TILE_SIZE, 0 + Standards.TILE_SIZE);

			glTexCoord2f(0, 1);
			glVertex2i(0, 0 + Standards.TILE_SIZE);
		}
		glEnd();
		glPopMatrix();

	}
	
}
