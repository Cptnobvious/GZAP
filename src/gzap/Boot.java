package gzap;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import world.Tile;

public class Boot {

	private static TextureHandler texturehandler;
	
	private static Tile[][] fakechunk = new Tile[21][21];


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
		//glEnable(GL_BLEND);
		//glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		setupTextureHandler();
		
		for (int x = 0; x < 21; x++){
			for (int y = 0; y < 21; y++){
				if (x == 5 || x == 7){
					fakechunk[x][y] = new Tile(3);
				} else if (x == 6){
					fakechunk[x][y] = new Tile(2);
				} else {
					fakechunk[x][y] = new Tile(0);
				}
			}
		}
		
		while (!Display.isCloseRequested()){
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			glColor4f(1f, 1f, 1f, 1f);

			
			for (int x = 0; x < 21; x++){
				for (int y = 0; y < 21; y++){
					fakechunk[x][y].draw((x * Standards.TILE_SIZE) + 64, (y * Standards.TILE_SIZE) + 64);
				}
			}
			
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


