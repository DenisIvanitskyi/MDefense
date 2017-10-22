package Shape;

import Window.Window;
import processing.core.*;

public class Empty_Widget extends Sprite {
	
	public Text viewText;
	public Empty_Widget(Window window) {
		super(window);
		this.texture = window.app.loadImage("ui/empty_widget.png");
		size.x = 50;
		drawTexture(0,0,texture.width,texture.height);
		
		
		viewText = new Text(window);
		viewText.parent = this;
		viewText.size = 10;
		viewText.text = "None";
		viewText.align = new PVector(PConstants.LEFT,PConstants.CENTER);
		viewText.position = new PVector(5,texture.height / 2);
	}
	
}
