/*
Class Name: Block
Purpose: To act as the blocks that will create the map and structures within the map
Inheritence: Child of GameObject
 */
import java.awt.*;
import java.awt.image.BufferedImage;

// This class is for the walls that act as boundaries in the game
public class Block extends GameObject {

    private BufferedImage block_image;


    // Constructor
    public Block(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id, ss);
    block_image = ss.grabImage(6, 2, 32, 32);
    }

    // Nothing will be changing about our map so there is no need to implement tick
    @Override
    public void tick() {

    }

    // Override the render method in GameObject
    @Override
    public void render(Graphics g) {
        g.drawImage(block_image, x, y, null);
        /*
        g.setColor(Color.black);
        g.fillRect(x, y, 32, 32);
        */
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    // We used to test hitboxes using a bigger box
    public Rectangle getBoundsBig() {
        return new Rectangle(x , y , 72, 72);
    }
}
