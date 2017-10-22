package Window;


import Items.*;
import Shape.*;
import net.azurewebsites.denisdev.MainGame;
import processing.core.*;

public class GameWindow extends Window {
    public static PFont font;
    public static PFont font_info;
    private Item mouseOverItem;
    private PImage background;
    private Empty_Widget hitMouseView;
    private HideMask mask;

    public static Empty_Wood_Widget viewWoodWidget;
    private Text textCounterKillZombi;
    private Text textLoose;
    
    public static Item_Player player;
    private static Sprite pointer;
    private float stepSpawnMobs = 500;
    private float indexSpawnMobs = 0;
    public boolean playGame = true;


    public GameWindow() {
        super(1.5f);

        this.makeBakcground(app.loadImage("game/background.png"));
        //font = app.createFont("font/minecraft.ttf", 50);
        font = app.createFont("font/minecraft_info.ttf", 50);
        Item.staticWindow = this;
        Item.staticTexture = app.loadImage("game/items.png");
        Mob.staticMobTexture = app.loadImage("game/mobs.png");
        Mob.init();

        add(new TextFPSAndObject(this));

        generateMap(30, 19);

        hitMouseView = new Empty_Widget(this);
        hitMouseView.position = new PVector(10, MainGame.N_HEIGHT / scale - (hitMouseView.texture.height + 5));
        add(hitMouseView);
        add(hitMouseView.viewText);

        viewWoodWidget = new Empty_Wood_Widget(this);
        viewWoodWidget.position = new PVector(MainGame.N_WIDTH / scale - (viewWoodWidget.localTexture.width + 10), 10);
        add(viewWoodWidget);

        textCounterKillZombi = new Text(this);
        textCounterKillZombi.parent = viewWoodWidget;
        textCounterKillZombi.position = new PVector(5, 19 / 2 + 1);
        textCounterKillZombi.align = new PVector(PConstants.LEFT, PConstants.CENTER);
        textCounterKillZombi.text = "Hello world";
        textCounterKillZombi.size = 11;
        add(textCounterKillZombi);

        player = new Item_Player();
        player.position = new PVector(15 * 16 + 30, 10 * 16 + 50);
        add(player);

        Item tableHelp = new Item_Table();
        tableHelp.position = new PVector(16 * 16 + 30, 16 + 50);
        this.add(tableHelp);


        pointer = new Sprite(this);
        pointer.texture = app.loadImage("ui/arr.png");
        pointer.drawTexture(0, 0, 13, 13);
        //add(pointer);
        
        mask = new HideMask(this);
        mask.size = new PVector(size.x,size.y);
        mask.speed = -3;
        mask.begin = true;
        mask.alpha = 255;
        add(mask);
        
        textLoose = new Text(this);
        textLoose.position = new PVector(size.x / 2,size.y / 2);
        textLoose.align = new PVector(PConstants.CENTER,PConstants.CENTER);
        textLoose.foreground = app.color(250,30,30);
        textLoose.visible = false;
        
        Input_Widget input = new Input_Widget(this);
		input.position = new PVector(0,200);
		input.size = new PVector(100,100);
		input.text.position = new PVector(5,5);
		input.text.font = app.createFont("font/minecraft_info.ttf",50);
		input.showPosition = new PVector(0,200);
		input.hidePosition = new PVector(-input.size.x,200);
		add(input);
        
        add(textLoose);
    }
    private void generateMap(int x_count, int y_count) {
        float blockSizeX = MainGame.N_WIDTH / scale / x_count;
        float blockSizeY = MainGame.N_HEIGHT / scale / y_count;
        Item baseItem = null;
        for (int y = 0; y < y_count; y++)
            for (int x = 0; x < x_count; x++) {
                if (x == 0 || x == x_count - 1)
                    baseItem = new Item_Cobblestone();
                else if (x == x_count / 2 || y == 0 || y == y_count - 1) {
                    baseItem = new Item_Send();
                } else
                    baseItem = new Item_Grass();


                baseItem.position = new PVector(x * baseItem.localTexture.width + 30, y * baseItem.localTexture.height + 50);
                this.add(baseItem);
            }
    }

    private void makeBakcground(PImage texture) {
        int maxX = (int) ((MainGame.N_WIDTH / scale) / texture.width) + 1;
        int maxY = (int) ((MainGame.N_HEIGHT / scale) / texture.height) + 1;
        background = app.createImage((int) (MainGame.N_WIDTH / scale), (int) (MainGame.N_HEIGHT / scale), PConstants.ARGB);
        for (int y = 0; y < maxY; y++)
            for (int x = 0; x < maxX; x++) {
                background.copy(texture, 0, 0, texture.width, texture.height, (x * texture.width), (y * texture.height),
                        texture.width, texture.height);
            }
    }

    @Override
    public void update() {
        if (app.keyCode == PConstants.ESC)
            MainGame.currentWindow = new MenuWindow();

        pointer.position = mouse();
        pointer.position.x -= pointer.localTexture.width / 2;
        pointer.position.y -= pointer.localTexture.height / 2;
        app.cursor(pointer.localTexture);

        super.update();
        Shape shape = get(app.mouseX, app.mouseY);
        if (shape != null && shape instanceof Item && mouseOverItem != shape) {
            if (mouseOverItem != null)
                mouseOverItem.debugRectangle = false;
            mouseOverItem = (Item) shape;
            mouseOverItem.debugRectangle = true;
            if (mouseOverItem instanceof Item_Player)
                hitMouseView.viewText.text = "You HP: " + player.hp;
            else
                hitMouseView.viewText.text = "This is " + mouseOverItem.name;
        }

        //if(textCounterKillZombi.text != "Kill: " + player.countKillMobs)
        {
            textCounterKillZombi.text = "Kill:       " + String.format("%1$05d", player.countKillMobs);
        }

        indexSpawnMobs += 1 + (player.countKillMobs * 0.1f);
        if (indexSpawnMobs >= (stepSpawnMobs / 2) && app.frameRate >= 30) {
            int random_number = (int) app.random(0, 100);
            Mob mob;

            if (random_number <= 30) {
                mob = new Mob_Creeper();
            } else {
                mob = new Mob_Zombi();
            }
            mob.setPosition(new PVector((app.random(-1, 1) > 0 ? 1 : 28) * 16 + 30, ((int) app.random(1, 18)) * 16 + 50));
            add(mob);
            indexSpawnMobs = 0;
        }


        if (player.hp <= 0) {
            app.saveStrings("setting.txt", new String[]{String.valueOf(player.countKillMobs)});
            textLoose.size = 35;
            textLoose.visible = true;
            MainGame.
            currentWindow = new MenuWindow();
        }
    }

    @Override
    protected void render(PApplet app) {
        app.image(background, 0, 0, MainGame.LastSizeWindow.x, MainGame.LastSizeWindow.y);
        super.render(app);
    }
}
