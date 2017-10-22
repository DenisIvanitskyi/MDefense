package Items;

import Shape.Sprite;
import Window.*;
import processing.core.*;

public class Item extends Sprite {
	
	public static Window staticWindow;
	public static PImage staticTexture;
	public boolean debugRectangle = false;
	
	public float hp = 100;
	public String name = "none";
	public String hitMouse = "Some block!";
	
	public Item() {
		super(staticWindow);
		texture = staticTexture;
	}
	
	public void initRotate(){	positionRotate = new PVector(localTexture.width / 2,localTexture.height / 2); }
	public boolean collision(Item item)
	{
		return (position.x < item.position.x + item.sizeCopy.x + item.size.x &&
				position.x + sizeCopy.x + size.x > item.position.x &&
				position.y < item.position.y + item.sizeCopy.y + item.size.y &&
				sizeCopy.y + size.y + position.y > item.position.y);
	}
	
	@Override
	public void mouseOver(boolean value)
	{
		/*if(value)
		{
			((GameWindow)staticWindow).hitMouseView.viewText.text = hitMouse;
		}
		else
		{
			((GameWindow)staticWindow).hitMouseView.viewText.text = "None";
		}*/
	}
	@Override
	public void render(PApplet app)
	{
		super.render(app);
		if(debugRectangle)
		{
			app.noFill();
			app.stroke(app.color(0,245,0));
			app.rect(position().x, position().y, localTexture.width, localTexture.height);
		}
	}
}
