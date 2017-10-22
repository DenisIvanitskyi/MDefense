package Shape;

import Window.*;
import net.azurewebsites.denisdev.MainGame;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * Created by student #6 on 16.05.2017.
 */
public class HideMask extends Shape {
    public int alpha;
    public int color;
    public boolean begin;
    public int speed = 3;

    public HideMask(Window window) {
        super(window);
        color = window.app.color(0, 255);
        begin = false;
        alpha = 0;
    }

    @Override
    public void update() {
        if (begin) alpha += speed;

        if(alpha > 255)
            MainGame.currentWindow = getWindow();
        else if(alpha < 0)
        	isDelete = true;
    }
    private Window getWindow()
    {
    	return MainGame.currentWindow instanceof MenuWindow ? new GameWindow() : new MenuWindow();
    }
    @Override
    public void render(PApplet app) {
    	app.noStroke();
        app.fill(color,alpha);
        PVector position = position();
        app.rect(position.x, position.y, size.x, size.y);
    }
}
