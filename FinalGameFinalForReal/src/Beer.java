import java.awt.*;
import java.awt.image.BufferedImage;
public class Beer extends GameObject {  // Inheritance!

    private BufferedImage beerImage;

    // Constructor
    public Beer(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id, ss);
        // Loading specfied image from sprite sheet
        beerImage = ss.grabImage(1, 1, 32, 32);
    }

    @Override // Didn't override this because it's a "static" object that never updates, just disappears
    public void tick() {

    }

    @Override // Overridden to draw the Beer sprite
    public void render(Graphics g) {
        g.drawImage(beerImage, x, y, null);
    }

    @Override // How big to draw the hitbox
    public Rectangle getBounds() {
            return new Rectangle(x, y, 32, 32);
        }
    }

