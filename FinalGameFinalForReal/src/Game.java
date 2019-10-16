
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

// Main class and all functionality like the game loop and rendering

@SuppressWarnings("serial") // Not serializing this class so we can suppress the warning
public class Game extends Canvas implements Runnable { // Runnable allows threads to be created and run simultaneously within the program
    // Have to use next line if you're going to serialize/deserialize objects, but we aren't in this game
    // private static final long serialVersionUID = 1L;

    // So we can tell the game loop to start/stop
    private boolean isRunning = false;

    private Thread thread;
    private Handler handler;
    private BufferedImage level = null;
    private BufferedImage sprite_sheet = null;
    private BufferedImage floor = null;
    private Camera camera;
    private SpriteSheet ss;



    public int ammo = 200;

    public int hp = 250;

    // Constructor
    public Game(){

        // Listens for keys
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
                // CHEAT
            @Override // Health restored to max when enter is pressed
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    if(hp < 250 ){

                       hp = (250 - hp) + hp;
                    }
                } // Starts game when "r" is pressed - UGLY SOLUTION I HATE IT
                if(e.getKeyCode() == KeyEvent.VK_R){
                    start();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        }
        );

            // Game windows
        new Window(1000, 563, "Wizzy", this);
        // Instantiating things needed to run the game
        handler = new Handler();
        camera = new Camera(0,0);
        this.addKeyListener(new KeyInput(handler)); // Listening for key inputs
        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("\\level.png"); // Loading in level image
        sprite_sheet = loader.loadImage("sprite_sheetv2.png");
        ss = new SpriteSheet(sprite_sheet);
        // Loading floor
        floor = ss.grabImage(4, 2, 32, 32);
        this.addMouseListener(new MouseInput(handler, camera, this, ss));
        loadLevel(level);
        JOptionPane.showMessageDialog(null, "The Object of Wizzy is to either kill all spiders or exit the grotto!");
        JOptionPane.showMessageDialog(null, String.format("Crates: refill ammo %s Beer: restores health %s Shroom: provides power boost", "\n", "\n", "\n"));
        JOptionPane.showMessageDialog(null, "First, click the window. Press R to start the thread. Press \"Enter\" to insta-heal!");

    }

    // Starts thread
    private void start(){
        // We want the thread to run when started so setting the boolean to true
        isRunning = true;
        // Creates new thread object
        thread = new Thread(this);
        thread.start();
    }

    // Stops thread
    private void stop(){
        isRunning = false;
        try {
            thread.join();  // thread.join() will make sure that the thread is terminated before the next instruction is executed
        } catch (InterruptedException e) {// This can fail so we put it in a try catch
            e.printStackTrace();
        }
    }

    // Notch game loop - This loop came from Notch, the creator of Minecraft
    // Game loop cycles through and updates our game 60 times a second and should run at 1000+ fps
    public void run() {

        this.requestFocus();
        long lastTime = System.nanoTime();
        double ns = 10000000;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                //updates++
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
                //updates;
            }
        }
        stop();
    }

    // Updates everything (objects) in the game - 60x/sec
    public void tick(){

        for(int i = 0; i < handler.object.size(); i++){
            // If the player obj is updated the camera that follows the player is updated as well
            if(handler.object.get(i).getId() == ID.Player){
                camera.tick(handler.object.get(i));
            }
        }
    handler.tick();
    }

    // Renders everything in the game - 1000x+/sec
    public void render(){

        // Preloading frames to show on viewable JFrame
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D graphics2D = (Graphics2D) g;

        ///////////////// Drawing Section //////////////////

        // Moves the camera around
        graphics2D.translate(-camera.getX(), -camera.getY());

        // Goes through every tile in the game and draws the floor
        for(int xx = 0; xx < 30 * 72; xx += 32){
            for(int yy = 0; yy < 30 * 72; yy +=32){
                g.drawImage(floor, xx, yy, null);
            }
        }

        // This draws all of the sprites on top of the floor
        handler.render(g);

        // This makes it so the health/ammo label is in the top left of the screen
        // wherever the character goes. Otherwise it stays at 0,0.
        graphics2D.translate(camera.getX(), camera.getY());

        // Health and Ammo label
        g.setColor(Color.gray);

        g.fillRect(5, 5, 250, 32);
        g.setColor(Color.green);
        g.fillRect(5, 5, hp , 32);
        g.setColor(Color.black);
        g.drawRect(5, 5, 250, 32);

        g.setColor(Color.black);

        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        if(ammo >= 0)
        g.drawString("Ammo : " + ammo, 5, 50);
        // End of Health and Ammo label

        // To show on the screen and prevent a negative ammo count(even though it can still go negative)
        if(ammo < 0)
        g.drawString("Ammo : 0" , 5, 50);
        ///////////////// End Draw Section //////////////////

        g.dispose();
        bs.show();
    }

    // Load the level
    public void loadLevel(BufferedImage image){
        int w = image.getWidth(), h = image.getHeight();  // Each block is based on the size of the sprite
        for(int xx = 0; xx < w; xx++){  // Width
            for(int yy = 0; yy < h; yy++){  // Height
                int pixel = image.getRGB(xx, yy);  // Goes through each pixel -- complicated java color stuff, shifting the bits and using AND to get the last 8 bits for
                int red = (pixel >> 16) & 0xff;  // For red values    --         accurate colors, see https://oscarliang.com/what-s-the-use-of-and-0xff-in-programming-c-plus-p/
                int green = (pixel >> 8) & 0xff; // For green values  --         and https://stackoverflow.com/questions/11380062/what-does-value-0xff-do-in-java and
                int blue = (pixel) & 0xff;  // For blue values        --         https://stackoverflow.com/questions/4801366/convert-rgb-values-to-integer  for details

                // Adding objects to the level based on pixel RGB values
                if(red == 72 && green == 0 && blue == 255 ){
                    handler.addObject(new Block(xx*32, yy*32, ID.Block, ss));
                }
                if(red == 255 && green == 0 && blue == 0){
                    handler.addObject(new Wizard(xx*32, yy*32, ID.Player, handler, this, ss));
                }
                if(green == 255 && red == 0 && blue == 33){
                    handler.addObject(new Enemy(xx*32, yy*32, ID.Enemy, handler, ss));
                }
                if(red == 255 && green == 0 && blue == 220){
                    handler.addObject(new Loot(xx*32, yy*32, ID.Loot, ss));
                }
                if(red == 255 && green == 255 && blue == 0){
                    handler.addObject(new Beer(xx*32, yy*32, ID.Beer, ss));
                }
                if(red == 250 && green == 100 && blue == 0 ){
                    handler.addObject(new Shroom(xx*32, yy*32, ID.Shroom, ss));
                }
                if(red == 255 && green == 255 && blue == 255 ){
                    handler.addObject(new Exit(xx*32, yy*32, ID.Exit, ss));
                }
            }
        }

    }

    // Main method
    public static void main(String[] args){
        // Create new instance of the splashscreen
        new SplashScreen();
        // Create new instance of the game
        new Game();
    }
}


