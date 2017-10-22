package Shape;

import Window.*;
import processing.core.*;

public class Input_Widget extends Shape {
	
	public Text text;
	private int index = 0;
	public boolean show = false;
	public PVector target, showPosition, hidePosition;
	
	public Input_Widget(Window win)
	{
		super(win);
		
		text = new Text(win);
		text.parent = this;
		text.align = new PVector(PConstants.LEFT,PConstants.TOP);
		target = hidePosition;
		win.add(text);
	}
	
	@Override
	public void update()
	{
		if(target != null)
		{
			float dist = target.x - position.x;
			position.x += dist * 0.1f;
		}
	}
	@Override
	public void keyPressed(int keyCode)
	{
		
		if(keyCode == PConstants.RIGHT)
			target = showPosition;
		else if(keyCode == PConstants.LEFT)
			target = hidePosition;
		
		if(target == hidePosition)
			return;
		if(keyCode == PConstants.BACKSPACE)
			text.text = text.text.substring(0, text.text.length() - 2);
		else if(keyCode == PConstants.ENTER)
		{
			text.position.y = 5;
			text.text += '\n';
			index++;
		}
		else
			text.text += window.app.key;
	}
	
	@Override
	public void render(PApplet app)
	{
		PVector pos = position();
		app.fill(150,100);
		app.rect(pos.x,pos.y,size.x,size.y,3);
		super.render(app);
	}
}
