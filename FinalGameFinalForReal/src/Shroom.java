import java.awt.*;
import java.awt.image.BufferedImage;
public class Shroom extends GameObject {

    private BufferedImage shroomImage;




    public Shroom(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id, ss);
        // Loading specfied image from sprite sheet
        shroomImage = ss.grabImage(2, 1, 33, 32);
    }

    @Override // Didn't override this because it's a "static" object that never updates, just disappears
    public void tick() {

    }

    @Override // Overridden to draw the exit sprite
    public void render(Graphics g) {
        g.drawImage(shroomImage, x, y, null);
    }

    @Override // How big to draw hitbox
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }


}

