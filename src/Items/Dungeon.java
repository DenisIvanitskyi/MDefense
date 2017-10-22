package Items;

public class Dungeon extends Item {
	
	public boolean start = false;
	public int timeNextLevel = 0;
	public float timeSpawn = 0;
	public float maxTimeSpawn = 100;
	
	public Item spawnPoint;
	
	public Dungeon()
	{
		super();
		drawTexture(1 * 16,4 * 16,16,16);
		name = "Dung @Spawner@";
	}
	
	@Override
	public void update()
	{
		if(!start)
			return;
		timeSpawn += 0.1f;
		if(timeSpawn >= maxTimeSpawn)
		{
			if(timeNextLevel == 0)
			{
				Mob_Zombi zombi = new Mob_Zombi();
				zombi.position = spawnPoint.position;
				window.add(zombi);
			}
			
			
			if(timeNextLevel >= 1)
				timeNextLevel = 0;
			timeSpawn = 0;
		}
	}
}
