package Shape;

import Window.GameWindow;
import Window.Window;
import processing.core.*;

public class Text extends Shape {
	public String text = "";
	public PFont font;
	public float size;
	public int foreground;
	public PVector align;
	public Text(Window window) {
		super(window);
		foreground = 255;
		align = new PVector(window.app.LEFT,window.app.TOP);
		size = 14;
		font = GameWindow.font;
	}
	
	
	
	@Override
	public void render(PApplet app)
	{
		app.fill(this.foreground);
		app.textFont(font, size);
		app.textAlign((int)align.x,(int)align.y);
		app.text(text, position().x, position().y);
	}

}
