import java.awt.*;
import java.awt.image.BufferedImage;
public class Exit extends GameObject {

    private BufferedImage exitImage;

   // Constructor
    public Exit(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id, ss);
        // Loading specfied image from sprite sheet
        exitImage = ss.grabImage(3, 1, 32, 32);
    }

    @Override // Didn't override this because it's a "static" object that never updates, just disappears
    public void tick() {

    }

    @Override // Overridden to draw the exit sprite
    public void render(Graphics g) {
        g.drawImage(exitImage, x, y, null);
    }

    @Override // How big to draw hitbox
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}

