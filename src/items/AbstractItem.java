package items;

import static org.lwjgl.opengl.GL11.*;
import gzap.Boot;
import gzap.Standards;
import util.Color4F;
import util.TexInfo;

public abstract class AbstractItem {

	String name;
	protected TexInfo texinfo;
	private int id;
	protected int damageDealt = 0;
	
	public AbstractItem(int id, String name, TexInfo texinfo){
		this.name = name;
		this.texinfo = texinfo;
		
		this.id = id;
	}

	public int getID(){
		return id;
	}
	
	public int getDamageDealt(){
		return damageDealt;
	}
	
	public TexInfo getTexInfo(){
		return texinfo;
	}
	
	public void draw(int x, int y) {
		
		TexInfo currentTexInfo = texinfo;
		Color4F color = currentTexInfo.getColor4F();
		
		glColor4f(1f, 1f, 1f, 1f);
		glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		
		
		Boot.getTexHandler().bindTexture("items");
		
		float xloc = 0;
		float yloc = 0;
		float top;
		float bottom;
		float left;
		float right;
		
		xloc = currentTexInfo.getSpriteSheetX();
		yloc = currentTexInfo.getSpriteSheetY();
		
		top = (yloc * Standards.TILE_SIZE_ON_TEX_F) / Standards.TEX_SIZE_F;
		bottom = top + (Standards.TILE_SIZE_ON_TEX_F / Standards.TEX_SIZE_F);
		left = (xloc * Standards.TILE_SIZE_ON_TEX_F) / Standards.TEX_SIZE_F;
		right = left + (Standards.TILE_SIZE_ON_TEX_F / Standards.TEX_SIZE_F);
		
		
		//This push and pop prevent rotate from having a field day
		glPushMatrix();
		glTranslatef(x, y, 0f);
		glRotatef(0.0f, 0.0f, 0.0f, 1.0f);
		
		
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
