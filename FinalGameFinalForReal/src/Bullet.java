/*
Class Name: Bullet
Purpose: To act as the bullets that will be propelled from our player
Inheritence: Child of GameObject
 */

import java.awt.*;

public class Bullet extends GameObject {

    // Fields
    private Handler handler;

    // Constructor
    public Bullet(int x, int y, ID id, Handler handler, int mx, int my, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        velX = (mx - x) / 50;
        velY = (my - y) / 50;
    }

    //Overriding the tick method in GameObject
    @Override
    public void tick() {
        x += velX;
        y += velY;

        // Loop through game objects and determine if the bullet will hit a wall
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Block){
                // If it does, then remove that object from our linked list
                if(getBounds().intersects(tempObject.getBounds()) || this.velX == 0 && this.velY == 0 ){ // The OR part is if the bullets are stationary
                    handler.removeObject(this);                                                // in the middle of the screen
                }
           }
        }
    }

    // Override the render method in GameObject to draw the bullet
    // We made our bullets black ovals
    @Override
    public void render(Graphics g) {
    g.setColor(Color.black);
    g.fillOval(x, y, 8, 8);
    }

    // Override the getBounds method in GameObject for the hitbox
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 8, 8);
    }

}
