package gzap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class TextureHandler {

	public static Texture terrain;
	public static Texture player;
	public static Texture debug;
	public static Texture text;
	public static String sCurrentlyBound = "null";
	public static Texture currentlyBound;
	
	public TextureHandler(){
		
	}
	
	public void init(){
		terrain = loadTexture("terrain");
		player = loadTexture("player");
		debug = loadTexture("debug_mapcenter");
		text = loadTexture("text");
	}
	
	public void destroy(){
		terrain.release();
		player.release();
		debug.release();
		text.release();
	}
	
	public void bindTexture(String key){
		if (!key.equals(sCurrentlyBound)){
			
			sCurrentlyBound = key;
			
			switch (key){
			case "null":
				glBindTexture(GL_TEXTURE_2D, 0);
				currentlyBound = null;
				break;
			case "terrain":
				terrain.bind();
				currentlyBound = terrain;
				break;
			case "player":
				player.bind();
				currentlyBound = player;
				break;
			case "debug":
				debug.bind();
				currentlyBound = debug;
				break;
			case "text":
				text.bind();
				currentlyBound = text;
				break;
			default:
				//TODO make a proper error handler
				System.out.println("Tried to bind a texture that doesn't exist");
				Boot.shutdownGracefully();
			}
			
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			
		}
	}
	
	public Texture getCurrentlyBound(){
		return currentlyBound;
	}
	
	private Texture loadTexture(String key){
		try {
			return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + key + ".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
