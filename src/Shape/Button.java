package Shape;

import Window.Window;
import processing.core.*;

public class Button extends Sprite {
	public String text = "";
	public int foreground = 250;
	public static PImage staticTexture;
	public PFont font;
	public float size;
	private OnClickListener onClickListener;

	public Button(Window window) {
		super(window);
		this.texture = staticTexture;
		size = 12;
	}

	public void setOnClickListener(OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}

	@Override
	public void mouseReleased() {
		if(onClickListener != null) onClickListener.onClick(this);
	}

	@Override
	public void mouseOver(boolean value)
	{
		if(value)
			drawTexture(0,40,200,20);
		else
			drawTexture(0,20,200,20);
		
	}
	@Override
	public void render(PApplet app)
	{
		super.render(app);
		app.fill(foreground);
		app.textFont(font, size);
		app.textAlign(PConstants.CENTER,PConstants.CENTER);
		app.text(text,position().x + sizeCopy.x / 2,position().y + sizeCopy.y / 2);
	}

	public interface OnClickListener {
		void onClick(Button bt);
	}
}
