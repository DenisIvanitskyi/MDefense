package Shape;

import Items.Item;
import processing.core.*;
import processing.opengl.PShader;

public class Particle extends Item {

	public PVector velocity;
	public float speed = 0.5f;
	public float timerDelete;
	public Particle()
	{
		super();
		velocity = new PVector(window.app.random(-3,3),window.app.random(-3,3));
		texture = window.app.loadImage("game/particles.png");
		drawTexture(0,0,texture.width,texture.height);
		timerDelete = 30 + window.app.random(-10,10);
		this.hitMouse = "Smoke";
		this.name = "Smoke";
		positionRotate = new PVector(localTexture.width / 2,localTexture.height / 2);
	}
	
	
	@Override
	public void update()
	{
		position = PVector.add(position,PVector.mult(velocity, speed));
		//size = PVector.sub(size, new PVector(1,1));
		timerDelete--;
		if(timerDelete <= 0)
			isDelete = true;
		//rotate += 0.1f;
	}
	
	@Override
	public void render(PApplet app)
	{
		super.render(app);
	}
}
