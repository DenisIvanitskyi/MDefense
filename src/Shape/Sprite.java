package Shape;

import Window.Window;
import processing.core.*;

public class Sprite extends Shape {

	public PImage texture;
	public PVector positionCopy, sizeCopy;
	public PImage localTexture;
	
	public Sprite(Window window) {
		super(window);
	}
	
	public void drawTexture(float x,float y,float w,float h)
	{
		positionCopy = new PVector(x, y);
		sizeCopy = new PVector(w, h);
		localTexture = window.app.createImage((int)(sizeCopy.x + size.x),(int)(sizeCopy.x + size.y),window.app.ARGB);
		getTexture();
	}
	public void getTexture()
	{
		localTexture.copy(this.texture,(int)positionCopy.x,(int)positionCopy.y,
				(int)sizeCopy.x,(int)sizeCopy.y,
				0,0,
				(int)(sizeCopy.x + size.x),(int)(sizeCopy.y + size.y));
	}
	@Override
	public boolean get(float x,float y)
	{
		float newX = x - position().x;
		float newY = y - position().y;
		if(newX >= 0 && newY >= 0)
			return window.app.alpha(localTexture.get((int)newX, (int)newY)) > 0;
		else
			return false;
	}
	@Override
	public void render(PApplet app)
	{
		app.image(localTexture,
				(int)position().x,(int)position().y,
				(int)(localTexture.width),(int)(localTexture.height));
	}
}
