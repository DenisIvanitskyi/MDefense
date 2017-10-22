package Items;

import java.util.ArrayList;

import Shape.*;
import Window.GameWindow;
import processing.core.*;

public class Mob extends Item {
	public static ArrayList<Mob> mobsList = new ArrayList<Mob>();
	public static PImage staticMobTexture;
	public float HP = 100f;
	public float Protected = 0f;
	public float Speed = 0.1f;
	public float Attaka = 10;
	public float vectorMoved = 1;
	public ColorText colorText;
	public int indexDeleteString = 0;
	
	public static String[] charms;
	
	public Mob()
	{
		super();
		this.texture = staticMobTexture;
		drawTexture(0,0,texture.width,texture.height);
		colorText = new ColorText(staticWindow);
		colorText.parent = this;
		colorText.setText(charms[(int)window.app.random(0,charms.length-1)]);
		colorText.position = new PVector(-window.app.textWidth(colorText.now_text) / 2,-14);
		mobsList.add(this);
	}
	
	public static void init()
	{
		charms = staticWindow.app.loadStrings("game/charms.txt");
	}
	public void setPosition(PVector pos)
	{
		position.x = pos.x + 2;
		position.y = pos.y + 1;
		if(position.x > window.app.width / window.scale / 2)
			vectorMoved *= -1;
	}
	@Override
	public void deleteShape()
	{
		if(mobsList.contains(this))
			mobsList.remove(this);
		colorText.isDelete = true;
		GameWindow.player.countKillMobs++;
		TextFly textFly = new TextFly(window);
		textFly.velocity = new PVector(0,0.5f);
		textFly.parent = GameWindow.viewWoodWidget;
		textFly.position = new PVector(GameWindow.viewWoodWidget.localTexture.width / 2,20);
		textFly.foreground = window.app.color(50,50,200);
		textFly.text = "+1";
		window.add(textFly);
	}
	@Override
	public void update()
	{
		position.x += Speed * vectorMoved;
		for(Shape shape : window.GetShapes())
		{
			if(shape instanceof Item_Send && collision((Item)shape))
			{
				position.x -= (localTexture.width + Speed) *  vectorMoved;
				GameWindow.player.hp -= Attaka;
				TextFly textFly = new TextFly(window);
				textFly.text = "-" + Attaka + " HP";
				textFly.color = new PVector(255,0,0);
				textFly.position = position.copy();
				textFly.position.y -= localTexture.height / 2 - 3;
				window.add(textFly);
				break;
			}
		}
	}
	
}
