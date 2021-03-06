package gzap;

import static org.lwjgl.opengl.GL11.*;

import java.io.*;

import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.lwjgl.input.Keyboard;

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

	private static char previousCommand = 0;
	private static int moveRate = 2;
	private static int moveTick = 0;


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


		Keyboard.enableRepeatEvents(true);

		while (!Display.isCloseRequested()){

			int deltaTime = timer.getDelta();

			tick += deltaTime;

			draw();
			worldObj.update();
			guihandler.mouseInput();


			input(deltaTime);



			if (tick >= TICKRATE){
				guihandler.updateWindows();
				player._update(deltaTime);
				npclist.update(deltaTime);
				tick -= TICKRATE;
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

	public static boolean input(int delta){

		char key = 0;
		int direction = -1;

		/*
		if (Keyboard.next()){
			if (!Keyboard.isRepeatEvent()){
				key = 0;
			} else {
				key = Keyboard.getEventCharacter();
			}
		}
		 */

		if (Keyboard.isKeyDown(Keyboard.KEY_W)){
			direction = Standards.NORTH;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)){
			direction = Standards.SOUTH;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_D)){
			direction = Standards.EAST;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_A)){
			direction = Standards.WEST;
		} else {


			//key = Keyboard.getEventCharacter();
			//previousCommand = key;

			if (Keyboard.next()){
				key = Keyboard.getEventCharacter();
			}

			switch (key){
			case 'e':
				break;
			case 'o':
				//worldObj.saveChunk(0,0);
				System.out.println("You pressed o");
				break;
			case 'i':
				//worldObj.loadChunk(0,0);
				break;
			default:
				break;
			}

		}

		if (moveTick <= 0){
			if (direction != -1){
				player.move(direction);
				moveTick = 150;
			}
		} else {
			moveTick = moveTick - delta;
		}


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


