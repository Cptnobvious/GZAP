package entities.inanimate;

import static org.lwjgl.opengl.GL11.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import util.Color4F;
import util.TexInfo;
import entities.AbstractEntity;
import gzap.Boot;
import gzap.GameRegistry;
import gzap.Standards;

public abstract class AbstractInanimateEntity extends AbstractEntity{

	private int orientation = Standards.NORTH;
	protected TexInfo texinfo = null;
	protected boolean isSolid = false;
	protected boolean initialized = false;
	protected int EntID = -1;
	
	public AbstractInanimateEntity(int x, int y, int z, TexInfo texinfo) {
		super(x, y, z);
		this.texinfo = texinfo;
	}
	
	public abstract void update();

	public int getEntID(){
		return EntID;
	}
	
	public void setSolid(boolean solid){
		this.isSolid = solid;
	}
	
	public boolean getSolid(){
		return this.isSolid;
	}
	
	public void setOrientation(int direction){
		this.orientation = direction;
	}
	
	public int getOrientation(){
		return this.orientation;
	}
	
	@Override
	public void draw(int x, int y) {
		
		TexInfo currentTexInfo = texinfo;
		Color4F color = currentTexInfo.getColor4F();
		
		glColor4f(1f, 1f, 1f, 1f);
		glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		
		
		Boot.getTexHandler().bindTexture("entities");
		
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
			break;
		case Standards.WEST:
			rotYOffset = Standards.TILE_SIZE;
			break;
		}
		
		//This push and pop prevent rotate from having a field day
		glPushMatrix();
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

	public abstract void getMouseEvent(int button);
	public abstract void save(FileOutputStream out) throws IOException;
	public abstract void load(FileInputStream in) throws IOException;
	
}
