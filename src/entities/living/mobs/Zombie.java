package entities.living.mobs;

import static org.lwjgl.opengl.GL11.*;
import entities.living.AbstractMob;
import gzap.Boot;
import gzap.Standards;

public class Zombie extends AbstractMob{

	public Zombie(int x, int y, int z) {
		super(x, y, z, 100);
	}

	@Override
	public void draw(int x, int y) {
		glColor4f(1f, 1f, 1f, 1f);

		//TODO fix this, it's due to a quirk in the drawing code that offsets the draw by one
		x = x - Standards.TILE_SIZE;
		y = y - Standards.TILE_SIZE;
		
		Boot.getTexHandler().bindTexture("zed");

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
			break;
		case Standards.WEST:
			rotYOffset = Standards.TILE_SIZE;
			break;
		}
		
		glPushMatrix();

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
