package gzap;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;
import org.lwjgl.*;

import debug.Dummy;
import world.Chunk;

public class Boot {

	private static TextureHandler texturehandler;
	
	private static Chunk testChunk;
	private static Dummy testDummy;


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
		
		
		testChunk = new Chunk();
		testDummy = new Dummy();
		
		while (!Display.isCloseRequested()){
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			glColor4f(1f, 1f, 1f, 1f);

			testChunk.draw(0, 0, 0, 0, 11, 11);
			testDummy.draw(6 * Standards.TILE_SIZE, 6 * Standards.TILE_SIZE);
			
			
			Display.update();
			Display.sync(60);
		}


		//Remember to release the texture when done
		shutdownGracefully();
	}


	public static void setupTextureHandler(){
		texturehandler = new TextureHandler();
		texturehandler.init();
	}
	
	public static TextureHandler getTexHandler(){
		return texturehandler;
	}

	public static void shutdownGracefully(){
		texturehandler.destroy();
		Display.destroy();
	}

}


