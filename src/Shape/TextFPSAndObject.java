package Shape;

import Window.Window;
import processing.core.*;

public class TextFPSAndObject extends Text{

	public TextFPSAndObject(Window window) {
		super(window);
		position = new PVector(10,10);
		foreground = 240;
		size = 12;
	}
	
	@Override
	public void update()
	{
		text = "FPS: " + window.app.frameRate + "\nObjects: " + window.GetShapes().size();
	}

}
