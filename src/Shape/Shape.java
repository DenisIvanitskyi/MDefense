package Shape;

import Window.Window;
import processing.core.*;


public class Shape {
	
	public boolean isDelete = false;
	public boolean visible = true;
	public PVector position, size;
	public Window window;
	public Shape parent;
	public float rotate = 0;
	public PVector positionRotate;
	
	public Shape(Window window)
	{
		this.window = window;
		position = new PVector();
		size = new PVector();
		positionRotate = new PVector();
	}
	
	public PVector size(){ return parent == null ? size : PVector.add(parent.size, size); }
	public PVector position(){ return parent == null ? position : PVector.add(parent.position(),position); }
	public boolean visible(){ return parent == null ? visible : parent.visible && visible;}
	
	public void mousePressed(){}
	public void mouseReleased(){}
	public void mouseMoved(){}
	public void mouseDragged(){}
	public void keyPressed(int key){}
	public void keyReleased(int key){}
	public void mouseOver(boolean value){}
	public void deleteShape(){}
	public boolean get(float x,float y){ return false; }
	public void update(){}
	public void render(PApplet app){}
}
