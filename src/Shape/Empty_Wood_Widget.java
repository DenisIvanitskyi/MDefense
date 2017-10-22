package Shape;

import Window.Window;

public class Empty_Wood_Widget extends Sprite {
	public Empty_Wood_Widget(Window window)
	{
		super(window);
		texture = window.app.loadImage("ui/woodwidget.png");
		drawTexture(0,0,108,19);
	}
}
