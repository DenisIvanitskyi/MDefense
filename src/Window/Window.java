package Window;

import java.util.ArrayList;

import Shape.Shape;
import net.azurewebsites.denisdev.MainGame;
import processing.core.*;

public class Window{
	public PApplet app;
	public float scale;
	public PVector size;
	private ArrayList<Shape> list_shape;
	
	public Window(float scale)
	{
		this.app = MainGame.mainGame;
		list_shape = new ArrayList<Shape>();
		size = new PVector(MainGame.N_WIDTH / scale, MainGame.N_HEIGHT / scale);
		this.scale = scale;
	}
	public ArrayList<Shape> GetShapes(){ return list_shape; }
	public void add(Shape shape)
	{
		list_shape.add(shape);
	}
	
	public Shape get(float x,float y)
	{
		Shape shape;
		float localX,localY;
		for(int i = list_shape.size() - 1; i>-1;i--)
		{
			shape = list_shape.get(i);
			localX = x /  scale;
			localY = y /  scale;
			if(shape.get(localX, localY) && shape.visible())
				return shape;
		}
			
		return null;
	}
	
	public PVector mouse()
	{
		return new PVector(MainGame.mainGame.mouseX / scale,MainGame.mainGame.mouseY / scale);
	}
	
	
	public void update()
	{
		for(int i = 0;i<list_shape.size();i++)
			list_shape.get(i).update();
		
		for(int i = 0;i<list_shape.size();i++)
			if(list_shape.get(i).isDelete)
			{
				list_shape.get(i).deleteShape();
				list_shape.remove(i);
			}
				
	}
	
	protected void render(PApplet app)
	{
		app.scale(scale);
		for(Shape shape : list_shape)
		{
			if(shape.visible)
			{
				app.pushMatrix();
				app.translate(shape.position().x + shape.positionRotate.x,shape.position().y + shape.positionRotate.y);
				app.rotate(shape.rotate);
				app.translate(-(shape.position().x + shape.positionRotate.x),-(shape.position().y + shape.positionRotate.y));
				shape.render(app);
				app.popMatrix();
			}
		}
	}
	public void draw(PApplet app)
	{
		update();
		render(app);
	}
}
