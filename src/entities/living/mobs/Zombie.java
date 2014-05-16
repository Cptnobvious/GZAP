package entities.living.mobs;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.Random;

import util.PointMath;
import entities.living.AbstractMob;
import gzap.Boot;
import gzap.Standards;

public class Zombie extends AbstractMob{

	private AbstractMob target = null;
	private boolean hasTarget = false;

	public Zombie(int x, int y, int z) {
		super(x, y, z, 100);
	}

	@Override
	public void update(){
		Random rand = new Random(System.currentTimeMillis());

		if (!hasTarget){
			ArrayList<AbstractMob> targetslist = Boot.getNPCList().findNearMobs(xLoc, yLoc, 6, getID());

			if (targetslist != null){
				AbstractMob target = findBestTarget(targetslist);
				if (target != null){
					hasTarget = true;
					this.target = target;
				}
			} else {
				if (rand.nextInt(4) == 0){
					move(rand.nextInt(4));
				}
			}
		} else {
			double distance = PointMath.distance2Points(target.getX(), target.getY(), this.getX(), this.getY());

			if (distance < 8){
				move(goTowardsTargetDirection());
			} else {
				hasTarget = false;
				this.target = null;
			}
		}

	}

	private int goTowardsTargetDirection(){

		if (target.getX() == this.getX()){
			int yDistance = target.getY() - this.getY();
			if (yDistance <= 0){
				return Standards.NORTH;
			} else {
				return Standards.SOUTH;
			}
		} else if (target.getY() == this.getY()){
			int xDistance = target.getX() - this.getX();
			if (xDistance <= 0){
				return Standards.WEST;
			} else {
				return Standards.EAST;
			}
		} else {
			int xDistance = target.getX() - this.getX();
			int yDistance = target.getY() - this.getY();

			if (xDistance <= yDistance){
				if (yDistance <= 0){
					return Standards.NORTH;
				} else {
					return Standards.SOUTH;
				}
			} else {
				if (xDistance <= 0){
					return Standards.WEST;
				} else {
					return Standards.EAST;
				}
			}
		}
	}

	private AbstractMob findBestTarget(ArrayList<AbstractMob> list){
		AbstractMob best = null;

		for (int i = 0; i < list.size(); i++){
			if (!(list.get(i) instanceof Zombie)){
				best = list.get(i);
				break;
			}
		}

		return best;
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
