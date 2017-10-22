package Items;

public class Item_Cobblestone extends Item {
	public Item_Cobblestone()
	{
		super();
		texture = window.app.loadImage("game/items.png");
		drawTexture(0 * 16,1 * 16,16,16);
	}
}
