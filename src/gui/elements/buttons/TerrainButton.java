package gui.elements.buttons;

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
import gzap.GameRegistry;
import gzap.Standards;
import world.tile.TileTexInfo;

public class TerrainButton extends GUIButton {

	int TexX;
	int TexY;
	
	public TerrainButton(int x, int y, int TerrainID, int metadata) {
		super(x, y, Standards.TILE_SIZE, Standards.TILE_SIZE, TerrainID);
		TexX = GameRegistry.getTile(TerrainID).getTexInfo(metadata).getSpriteSheetX();//TileTexInfo.getInfoByID(TerrainID).getSpriteSheetX();
		TexY = GameRegistry.getTile(TerrainID).getTexInfo(metadata).getSpriteSheetY();
	}
	
	@Override
	public void draw(int wX, int wY){
		
		int ScreenX = wX + x;
		int ScreenY = wY + y;
		
		Boot.getTexHandler().bindTexture("terrain");
		
		glColor4f(1f, 1f, 1f, 1f);

		float xloc = (float)TexX;
		float yloc = (float)TexY;
		float top;
		float bottom;
		float left;
		float right;
		
		top = (yloc * Standards.TILE_SIZE_ON_TEX_F) / Standards.TEX_SIZE_F;
		bottom = top + (Standards.TILE_SIZE_ON_TEX_F / Standards.TEX_SIZE_F);
		left = (xloc * Standards.TILE_SIZE_ON_TEX_F) / Standards.TEX_SIZE_F;
		right = left + (Standards.TILE_SIZE_ON_TEX_F / Standards.TEX_SIZE_F);
		

		glPushMatrix();
		
		
		glBegin(GL_QUADS);
		{
			glTexCoord2f(left, top);
			glVertex2i(ScreenX, ScreenY);
			
			glTexCoord2f(right, top);
			glVertex2i(ScreenX + Standards.TILE_SIZE, ScreenY);

			glTexCoord2f(right, bottom);
			glVertex2i(ScreenX + Standards.TILE_SIZE, ScreenY + Standards.TILE_SIZE);
			
			glTexCoord2f(left, bottom);
			glVertex2i(ScreenX, ScreenY + Standards.TILE_SIZE);
		}
		glEnd();
		glPopMatrix();
	}
	
}
