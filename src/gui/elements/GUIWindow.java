package gui.elements;

public abstract class GUIWindow {
	
	private int ScreenX;
	private int ScreenY;
	private int Width;
	private int Height;
	
	public GUIWindow(){
		this.ScreenX = 0;
		this.ScreenY = 0;
		this.Width = 10;
		this.Height = 10;
	}

	
	public abstract void drawBackground();
	public abstract void drawForeground();
	
}
