package gzap;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.lwjgl.input.Keyboard;

import debug.SimpleConstruction;
import entities.living.player.Player;
import world.Map;
import world.TileTexInfo;

public class Boot {

	private static TextureHandler texturehandler;
	
	private static Player player;
	private static Map worldObj;
	private static SimpleConstruction debug = new SimpleConstruction();


	public static void main(String[] args) {
		try{
			Display.setDisplayMode(new DisplayMode(Standards.W_WIDTH, Standards.W_HEIGHT));
			Display.setTitle("Generic Zombie Apocalypse");
			Display.create();
		} catch (LWJGLException e){
			e.printStackTrace();
		}

		
		//Initialization code OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Standards.W_WIDTH, Standards.W_HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		setupTextureHandler();
		
		
		//testChunk = new Chunk();
		player = new Player(0, 0, 0, 100);
		worldObj = new Map();
		
		while (!Display.isCloseRequested()){
			
			draw();
			input();
			
			if (worldObj.getTileAtCoords(0, 0).getTexInfo() == TileTexInfo.SIDEWALK){
				System.out.println("Sidwalk at 0");
			}
			
			Display.update();
			Display.sync(60);
		}


		//Remember to release the texture when done
		shutdownGracefully();
	}
	
	public static void draw(){
		
		glClear(GL_COLOR_BUFFER_BIT);
		
		glColor4f(1f, 1f, 1f, 1f);

		int xStart = player.getX() - 12;
		int yStart = player.getY() - 12;
		
		worldObj.draw(0, 0, xStart, yStart);
		
		player.draw(13 * Standards.TILE_SIZE, 13 * Standards.TILE_SIZE);
		
	}
	
	public static void input(){
		char key = 0;
		
		if (Keyboard.next()){
			key = Keyboard.getEventCharacter();
		}
		
		switch (key){
		case 'w':
			player.move(Standards.NORTH);
			break;
		case 's':
			player.move(Standards.SOUTH);
			break;
		case 'd':
			player.move(Standards.EAST);
			break;
		case 'a':
			player.move(Standards.WEST);
			break;
		case 'e':
			debug.muck();
			break;
		default:
			break;
		}
	}


	public static void setupTextureHandler(){
		texturehandler = new TextureHandler();
		texturehandler.init();
	}
	
	public static Map getWorldObj(){
		return worldObj;
	}	
	
	public static Player getPlayer(){
		return player;
	}
	
	public static TextureHandler getTexHandler(){
		return texturehandler;
	}

	public static void shutdownGracefully(){
		texturehandler.destroy();
		Display.destroy();
	}

}


