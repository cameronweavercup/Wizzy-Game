import java.awt.*;
import java.awt.image.BufferedImage;

public class Loot extends GameObject {

    private BufferedImage loot_image;

    // Constructor
    public Loot(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id, ss);
        // Loading specfied image from sprite sheet
        loot_image = ss.grabImage(5, 2, 32, 32);
    }

    @Override // Didn't override this because it's a "static" object that never updates, just disappears
    public void tick() {

    }

    @Override // Overridden to draw loot (crate) sprite
    public void render(Graphics g) {
        g.drawImage(loot_image, x, y, null);

    }

    @Override // How big to draw lootbox
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

}
