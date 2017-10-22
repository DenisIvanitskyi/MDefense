package net.azurewebsites.denisdev;

import Window.*;
import processing.core.*;

import java.lang.Thread.State;
import java.util.ArrayList;

import Shape.*;

public class MainGame extends PApplet{
	public static MainGame mainGame;
	public static Window currentWindow;
	public static int N_WIDTH = 800;
	public static int N_HEIGHT = 600;
	public static Shape LastShape;
	public static PVector LastSizeWindow;
	public static Thread threadUpdate;
	public static boolean threadRun = false;
	
 	public static void main(String[] args) 
	{
		main("net.azurewebsites.denisdev.MainGame");
	}

	public void settings()
	{
		N_WIDTH = 800;
		N_HEIGHT = 600;
		mainGame = this;
		size(N_WIDTH,N_HEIGHT,P2D);
		//this.fullScreen(OPENGL);
		LastSizeWindow = new PVector(N_WIDTH,N_HEIGHT);
	}
	
	public void setup()
	{
		
		Button.staticTexture = loadImage("ui/buttons.png");
		
		Window window = new MenuWindow();
		currentWindow = window;
		surface.setResizable(false);
	}
	
	public void draw()
	{
		background(250);
		currentWindow.draw(this);
		
		
		if(this.sketchWidth() != LastSizeWindow.x || this.sketchHeight() != LastSizeWindow.y)
		{
			currentWindow.scale = (1.5f * (this.sketchWidth() + this.sketchHeight()) / (800 + 600));
			LastSizeWindow = new PVector(sketchWidth(),sketchHeight());
		}
		
	}
	
	
	
	public void mousePressed()
	{
		LastShape = currentWindow.get(mouseX, mouseY);
		if(LastShape != null)
			LastShape.mousePressed();
	}
	public void mouseReleased()
	{
		LastShape = currentWindow.get(mouseX, mouseY);
		if(LastShape != null)
			LastShape.mouseReleased();
	}
	public void mouseMoved()
	{
		Shape shape = currentWindow.get(mouseX, mouseY);
		if(shape != null)
			shape.mouseMoved();
		if(LastShape != null && shape != LastShape)
		{
			LastShape.mouseOver(false);
			LastShape = shape;
		}
		else if(shape != null)
		{
			shape.mouseOver(true);
			LastShape = shape;
		}
			
		
	}
	public void mouseDragged()
	{
		LastShape = currentWindow.get(mouseX, mouseY);
		if(LastShape != null)
			LastShape.mouseDragged();
	}
	public void keyPressed()
	{
		for(Shape shape : currentWindow.GetShapes())
			shape.keyPressed(keyCode);
	}
	public void keyReleased()
	{
		ArrayList<Shape> arrList = currentWindow.GetShapes();
		for(int i = 0;i< arrList.size();i++)
			arrList.get(i).keyReleased(keyCode);
	}

	
	
}
