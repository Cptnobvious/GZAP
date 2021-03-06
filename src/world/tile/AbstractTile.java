package world.tile;

import util.Color4F;
import util.TexInfo;
import gzap.Boot;
import gzap.Standards;
import static org.lwjgl.opengl.GL11.*;

public abstract class AbstractTile {
	
	//private TileType type;
	protected int id = 0;
	protected boolean isSolid;
	protected int subTiles;
	
	public AbstractTile(int uniqueID){
		id = uniqueID;
		isSolid = false;
		subTiles = 0;
	}
	
	public AbstractTile(int uniqueID, int subTiles){
		id = uniqueID;
		isSolid = false;
		this.subTiles = subTiles;
	}
	
	public int getSubTiles(){
		return subTiles;
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
	
	public abstract TileEntity getTileEntity(Tile parent);
	public abstract TexInfo getTexInfo(int data);
	
	public void draw(int x, int y, int orientation, int data){
		
		TexInfo currentTexInfo = getTexInfo(data);
		Color4F color = currentTexInfo.getColor4F();
		
		glColor4f(1f, 1f, 1f, 1f);
		glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		
		
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
		
		switch (orientation){
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
		glTranslatef(x + rotXOffset, y + rotYOffset, 0f);
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
