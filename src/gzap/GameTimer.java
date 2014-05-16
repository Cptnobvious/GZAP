package gzap;

import org.lwjgl.Sys;

public class GameTimer {

	long lastFrame = 0;
	
	public void init(){
		lastFrame = getTime();
	}
	
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}


	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

}
