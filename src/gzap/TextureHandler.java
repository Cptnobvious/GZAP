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
	public static Texture entities;
	
	public static Texture player;
	public static Texture debug;
	public static Texture text;
	public static String sCurrentlyBound = "null";
	public static Texture currentlyBound;
	public static Texture panes;
	
	public static Texture zed;
	
	public static Texture debugMenu;
	public static Texture constructionMenu;
	
	public TextureHandler(){
		
	}
	
	public void init(){
		terrain = loadTexture("terrain");
		entities = loadTexture("entities");
		
		player = loadTexture("player");
		debug = loadTexture("debug_mapcenter");
		text = loadTexture("text");
		panes = loadTexture("panes");
		
		zed = loadTexture("zed");
		
		constructionMenu = loadTexture("constructionmenu");
		debugMenu = loadTexture("debugmenu");
	}
	
	public void destroy(){
		terrain.release();
		entities.release();
		
		player.release();
		debug.release();
		text.release();
		panes.release();
		
		zed.release();
		
		constructionMenu.release();
		debugMenu.release();
	}
	
	public void bindTexture(String key){
		if (!key.equals(sCurrentlyBound) || currentlyBound == null){
			
			sCurrentlyBound = key;
			glEnable(GL_TEXTURE_2D);
			
			switch (key){
			case "null":
				glDisable(GL_TEXTURE_2D);
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
			case "constructionMenu":
				constructionMenu.bind();
				currentlyBound = constructionMenu;
				break;
			case "debugmenu":
				debugMenu.bind();
				currentlyBound = debugMenu;
				break;
			case "panes":
				panes.bind();
				currentlyBound = panes;
				break;
			case "zed":
				zed.bind();
				currentlyBound = zed;
				break;
			case "entities":
				entities.bind();
				currentlyBound = entities;
				break;
			default:
				//TODO make a proper error handler
				System.out.println("Tried to bind a texture that doesn't exist");
				System.out.println(key);
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
