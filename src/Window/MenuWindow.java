package Window;

import Shape.*;
import net.azurewebsites.denisdev.MainGame;
import processing.core.*;

public class MenuWindow extends Window  {
	
	private PImage background;
	public HideMask mask;
	public MenuWindow()
	{
		super(1.5f);
		makeBackground(app.loadImage("ui/background_menu.png"));

		mask = new HideMask(this);
		mask.size = new PVector(size.x,size.y);

		Text text_title = new Text(this);
		text_title.font = app.createFont("font/minecraft_info.ttf",50);
		text_title.text = "Total Kills: " + (GameWindow.player == null ? app.loadStrings("setting.txt")[0] : GameWindow.player.countKillMobs);
		text_title.size = 30;
		text_title.align = new PVector(PConstants.CENTER,PConstants.CENTER);
		text_title.position = new PVector(size.x / 2,size.y / 2 - 100);
		add(text_title);
		
		Button newGameButton = new Button(this);
		newGameButton.font = text_title.font;
		newGameButton.drawTexture(0, 20, 200, 20);
		newGameButton.text = "New Game";
		newGameButton.position = new PVector(size.x / 2 - newGameButton.localTexture.width / 2, size.y / 2);
		newGameButton.setOnClickListener( (e) -> { mask.begin = true; });
		add(newGameButton);
		
		Button newExit = new Button(this);
		newExit.font = text_title.font;
		newExit.drawTexture(0,20,200,20);
		newExit.text = "Exit";
		newExit.position = new PVector(size.x / 2 - newExit.localTexture.width / 2,size.y / 2 + newExit.sizeCopy.y + 10);
		newExit.setOnClickListener( (e) -> {app.exit();});
		add(newExit);

		
		
		
		add(mask);


	}
	
	private void makeBackground(PImage texture)
	{
		int maxX = (int) (size.x / texture.width) + 1;
		int maxY = (int) (size.y / texture.height) + 1;
		background = app.createImage((int)size.x,(int)size.y, app.RGB);
		for(int y = 0;y < maxY;y++)
			for(int x = 0;x < maxX;x++)
			{
				background.copy(texture,0,0,texture.width,texture.height,(x * texture.width),(y * texture.height),texture.width,texture.height);
			}
	}
	
	
	@Override
	public void render(PApplet app)
	{
		app.image(background,0,0,MainGame.LastSizeWindow.x,MainGame.LastSizeWindow.y);
		super.render(app);
	}
}
