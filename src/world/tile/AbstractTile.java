package world.tile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import gzap.Boot;
import gzap.Standards;
import static org.lwjgl.opengl.GL11.*;

public abstract class AbstractTile {
	
	//private TileType type;
	protected int id = 0;
	protected boolean isSolid;
	protected int orientation;
	protected int subTiles;
	
	public AbstractTile(int uniqueID){
		id = uniqueID;
		isSolid = false;
		subTiles = 0;
		orientation = Standards.NORTH;
	}
	
	public AbstractTile(int uniqueID, int subTiles){
		id = uniqueID;
		isSolid = false;
		this.subTiles = subTiles;
		orientation = Standards.NORTH;
	}
	
	public int getSubTiles(){
		return subTiles;
	}
	
	public void setOrientation(int direction){
		this.orientation = direction;
	}
	
	public int getOrientation(){
		return this.orientation;
	}
	
	public boolean getSolid(){
		return isSolid;
	}
	
	public int getBaseType(){
		return this.id;
	}
	
	public void setBaseType(int newType){
		this.id = newType;
	}
	
	public abstract TileTexInfo getTexInfo(int data);
	
	public void draw(int x, int y, int data){
		
		TileTexInfo currentTexInfo = getTexInfo(data);
		
		glColor4f(1f, 1f, 1f, 1f);
		glColor4f(currentTexInfo.getColor4F().getRed(), currentTexInfo.getColor4F().getGreen(),
				currentTexInfo.getColor4F().getBlue(), currentTexInfo.getColor4F().getAlpha());
		
		
		Boot.getTexHandler().bindTexture("terrain");
		
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
		
		//This push and pop prevent rotate from having a field day
		glPushMatrix();
		
		//glTranslatef(((float)x * Standards.TILE_SIZE), ((float)y * Standards.TILE_SIZE), 0f);
		glTranslatef((float)x + rotXOffset, (float)y + rotYOffset, 0f);
		glRotatef(rotation, 0.0f, 0.0f, 1.0f);
		
		
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
		
	}
	
}
