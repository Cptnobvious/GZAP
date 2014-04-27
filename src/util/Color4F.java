package util;

public class Color4F {

	private float red;
	private float green;
	private float blue;
	private float alpha;
	
	
	public Color4F(){
		this.red = 1f;
		this.green = 1f;
		this.blue = 1f;
		this.alpha = 1f;
	}
	
	public Color4F(float red, float green, float blue, float alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}


	public float getRed() {
		return red;
	}


	public void setRed(float red) {
		this.red = red;
	}


	public float getGreen() {
		return green;
	}


	public void setGreen(float green) {
		this.green = green;
	}


	public float getBlue() {
		return blue;
	}


	public void setBlue(float blue) {
		this.blue = blue;
	}


	public float getAlpha() {
		return alpha;
	}


	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	
	
	
}
