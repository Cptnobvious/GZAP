package gzap;

import static org.lwjgl.opengl.GL11.*;

import java.io.*;

import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.lwjgl.input.Keyboard;

import entities.living.mobs.Zombie;
import entities.living.player.Player;
import gui.GuiHandler;
import util.TextWriter;
import world.Map;

public class Boot {

	private static TextureHandler texturehandler;
	
	private static Player player;
	private static Map worldObj;
	private static TextWriter textwriter = new TextWriter();
	private static GuiHandler guihandler = new GuiHandler();
	private static GameRegistry registry = new GameRegistry();
	private static NPCList npclist = new NPCList();
	private static GameTimer timer = new GameTimer();
	private static int tick = 0;
	private static final int TICKRATE = 50;
	

	public static void main(String[] args) {
		try{
			Display.setDisplayMode(new DisplayMode(Standards.W_WIDTH, Standards.W_HEIGHT));
			Display.setTitle("Generic Zombie Apocalypse");
			Display.create();
		} catch (LWJGLException e){
			e.printStackTrace();
		}

		
		//TODO add a check to see if the save directory already exists
		File dir = new File(Standards.WORLD_SAVE_LOCATION);
		dir.mkdirs();
		dir = new File("data");
		dir.mkdir();
		
		//TODO put this initialization code in its own block somewhere
		registry.initializeRegistry();
		
		//Initialization code OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Standards.W_WIDTH, Standards.W_HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		setupTextureHandler();
		
		player = new Player(14, 14, 0, 100);
		worldObj = new Map();
		worldObj.Generate(0, 0);
		timer.init();
		
		while (!Display.isCloseRequested()){
			
			int deltaTime = timer.getDelta();
			
			tick += deltaTime;
			
			draw();
			worldObj.update();
			input();
			
			if (tick > TICKRATE){
				npclist.update(deltaTime);
				tick = 0;
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
		npclist.draw();
		player.draw(13 * Standards.TILE_SIZE, 13 * Standards.TILE_SIZE);
		guihandler.draw();
		
	}
	
	public static boolean input(){
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
			break;
		case 'o':
			worldObj.saveChunk(0,0);
			break;
		case 'i':
			worldObj.loadChunk(0,0);
			break;
		default:
			break;
		}
		
		guihandler.mouseInput();
		
		return true;
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
	
	public static TextWriter getTextWriter(){
		return textwriter;
	}
	
	public static GameRegistry getGameRegistry(){
		return registry;
	}
	
	public static TextureHandler getTexHandler(){
		return texturehandler;
	}
	
	public static GuiHandler getGUIHandler(){
		return guihandler;
	}
	
	public static NPCList getNPCList(){
		return npclist;
	}

	public static void shutdownGracefully(){
		texturehandler.destroy();
		Display.destroy();
	}

}


