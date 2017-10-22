package Items;

import processing.core.PConstants;
import Shape.*;

public class Item_Player extends Item{
	
	public float Attaka = 0;
	public int countKillMobs = 0;
	
	public Item_Player()
	{
		super();
		texture = window.app.loadImage("game/player.png");
		drawTexture(0,0,texture.width,texture.height);
		initRotate();
	}
	
	
	@Override
	public void keyPressed(int key)
	{
		if(key == PConstants.UP)
			position.y -= localTexture.height;
		else if(key == PConstants.DOWN)
			position.y += localTexture.height;
	}
	@Override
	public void keyReleased(int key)
	{
		Mob mob;
		for(int i = 0;i<Mob.mobsList.size();i++)
		{
			mob = Mob.mobsList.get(i);
			if(Math.abs(mob.position.y - this.position.y) <= 5)
			{
				if(mob.indexDeleteString >= mob.colorText.now_text.length()-1)
				{
					mob.isDelete = true;
					mob.colorText.deleteShape();
					for(int e = 0;e<10;e++)
					{
						Particle p = new Particle();
						p.position = mob.position;
						window.add(p);
					}
					return;
				}
				else if(mob.colorText.now_text.charAt(mob.indexDeleteString) == window.app.key)
				{
					Text text = mob.colorText.textList.get(mob.indexDeleteString++);
					text.foreground = window.app.color(50,245,50);
					return;
				}
				else
				{
					for(int d = mob.indexDeleteString;d>=0;d--)
					{
						mob.colorText.textList.get(d).foreground = 250;
					}
					mob.indexDeleteString = 0;
				}
			}
		}
	}
}
