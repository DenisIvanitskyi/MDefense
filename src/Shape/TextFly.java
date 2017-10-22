package Shape;

import Window.Window;
import processing.core.*;

public class TextFly extends Text {
	public PVector velocity;
	public PVector color;
	public int alpha;
	public TextFly(Window window) {
		super(window);
		velocity = new PVector(0,-1);
		alpha = 255;
		color = new PVector(255,255,255);
	}
	
	@Override
	public void update()
	{
		position = PVector.add(position, velocity);
		foreground = window.app.color(color.x,color.y,color.z,alpha-=5);
		if(alpha <= 0)
			this.isDelete = true;
	}
}
