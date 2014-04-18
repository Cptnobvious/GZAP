package world;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import gzap.Boot;
import gzap.Standards;
import static org.lwjgl.opengl.GL11.*;

public class Tile {
	
	//private TileType type;
	private int basetype = 0;
	private TileTexInfo texInfo;
	private int orientation;
	
	public Tile(TileTexInfo texInfo){
		this.texInfo = texInfo;
		this.orientation = Standards.NORTH;
	}
	
	public Tile(int basetype){
		init(basetype);
		this.orientation = Standards.NORTH;
	}
	
	public Tile(){
		this.texInfo = TileTexInfo.LAWN;
		this.orientation = Standards.NORTH;
	}
	
	public void init(int basetype){
		switch (basetype){
		default:
		case 0:
			texInfo = TileTexInfo.ROAD;
			break;
		case 1:
			texInfo = TileTexInfo.ROAD_STRIPE;
			break;
		case 2:
			texInfo = TileTexInfo.SIDEWALK;
			break;
		case 3:
			texInfo = TileTexInfo.LAWN;
			break;
		case 4:
			texInfo = TileTexInfo.LAWN_MUD;
			break;
		}
		
		this.basetype = basetype;
	}
	
	public void changeTile(){
		
	}
	
	public int getBaseType(){
		return this.basetype;
	}
	
	public void setBaseType(int newType){
		this.basetype = newType;
	}
	
	public TileTexInfo getTexInfo(){
		return this.texInfo;
	}
	
	public void setTexInfo(TileTexInfo texInfo){
		this.texInfo = texInfo;
	}
	
	public void save(FileOutputStream out) throws IOException{
		out.write(basetype);
	}
	
	public void load(FileInputStream in) throws IOException{
		int c = in.read();
		
		//TODO offload this onto the enum with some kind of "get tiletexinfo(int number)" function
		
		init(c);
	}
	
	public void draw(int x, int y){
		
		glColor4f(1f, 1f, 1f, 1f);
		
		Boot.getTexHandler().bindTexture("terrain");
		
		float xloc = 0;
		float yloc = 0;
		float top;
		float bottom;
		float left;
		float right;
		float rotation = orientation * 90;
		
		xloc = texInfo.getSpriteSheetX();
		yloc = texInfo.getSpriteSheetY();
		
		top = (yloc * Standards.TILE_SIZE_ON_TEX_F) / Standards.TEX_SIZE_F;
		bottom = top + (Standards.TILE_SIZE_ON_TEX_F / Standards.TEX_SIZE_F);
		left = (xloc * Standards.TILE_SIZE_ON_TEX_F) / Standards.TEX_SIZE_F;
		right = left + (Standards.TILE_SIZE_ON_TEX_F / Standards.TEX_SIZE_F);
		
		//This push and pop prevent rotate from having a field day
		glPushMatrix();
		
		//glTranslatef(((float)x * Standards.TILE_SIZE), ((float)y * Standards.TILE_SIZE), 0f);
		glTranslatef((float)x, (float)y, 0f);
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
