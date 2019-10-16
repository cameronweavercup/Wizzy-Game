

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.Random;

public class Enemy extends GameObject {
    Handler handler;
    Random r = new Random();
    int choose = 0;
    int hp = 100;


    private BufferedImage enemy_image = null;
    public static int enemyCount = 18; // Number of enemies in the game

    // Constructor
    public Enemy(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss );
        this.handler =  handler;
        this.ss = ss;
        enemy_image = ss.grabImage(6, 1, 32, 32);

    }

    @Override // Turning static points into velocity for movement
    public void tick() {
            x += velX;
            y += velY;

            // If an enemy has a collision with a block it goes a different direction
        choose = r.nextInt(10);
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Block){
                if(getBoundsBig().intersects(tempObject.getBounds())){
                            //was * 5  * -1
                        x += (velX * 2 * -1);
                        y += (velY * 2 * -1);
                        velX *= -1;
                        velY *= -1;

                }
                    // 10% chance to go a completely random direction
                else if(choose == 0){
                    velX = (r.nextInt(3 - -3) + -3);
                    velY = (r.nextInt(3 - -3) + -3);
                }
            }       // If a bullet collides with the enemy, subtract 50 HP and the bullet goes away. Enemies can take 2 hits
                if(tempObject.getId() == ID.Bullet){
                    if(getBounds().intersects(tempObject.getBounds())){
                    hp -= 50;
                    handler.removeObject(tempObject);

                }

                }
        }

        if(hp <= 0){
          checkIfGameWon(); // Works fine w/3, bugs otherwise
            enemyCount--; // if enemy HP is 0, decrements enemy count
           checkIfGameWon();
            handler.removeObject(this); // if HP IS 0, removes this enemy object from the game
          checkIfGameWon();


        //  JOptionPane.showMessageDialog(null, this.enemyCount); - used this for debugging

        }
    }



    public void checkIfGameWon(){ // Self explanatory
        if(enemyCount == 0  ){
            JOptionPane.showMessageDialog(null, "Congrats! You have won the game!");
            System.exit(0);
        }
    }


    @Override
    public void render(Graphics g) {

        g.drawImage(enemy_image, x, y, null);
        /* // This is to show the bigBounds boxes
        g.setColor(Color.yellow);
        g.fillRect(x, y, 32, 32);


        Graphics2D graphics2D = (Graphics2D) g;
        g.setColor(Color.black);
        graphics2D.draw(getBoundsBig());
        */
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 48, 48);
    }

    public Rectangle getBoundsBig() {
        return new Rectangle(x - 16, y - 16, 84, 84);
    }


}
