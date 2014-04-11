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

public class Boot {
	
	public static final int W_WIDTH = 1280;
	public static final int W_HEIGHT = 800;

	public static void main(String[] args) {
		try{
			Display.setDisplayMode(new DisplayMode(W_WIDTH, W_HEIGHT));
			Display.setTitle("Transparency Test");
			Display.create();
		} catch (LWJGLException e){
			e.printStackTrace();
		}

		//Initialization code OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, W_WIDTH, W_HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		while (!Display.isCloseRequested()){
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			
			Display.update();
			Display.sync(60);
		}
		
		
		//Remember to release the texture when done
		shutdownGracefully();
	}
	

	public static void shutdownGracefully(){
		Display.destroy();
	}
	
}


