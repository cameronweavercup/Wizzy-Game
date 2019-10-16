import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Extending mouse adapter so we don't have to implement unneeded methods
public class MouseInput extends MouseAdapter {
    private Handler handler;
    private Camera camera;
    private Game game;
    private SpriteSheet ss;


    // Constructor
    public MouseInput(Handler handler, Camera camera, Game game, SpriteSheet ss){
        this.handler = handler;
        this.camera = camera;
        this.game = game;
        this.ss = ss;

    }

    // Sets X and Y for bullet velocity
    public void mousePressed(MouseEvent e){
        int mx = (int) (e.getX() + camera.getX());
        int my = (int) (e.getY() + camera.getY());


        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Player && game.ammo >= 1){ // Greater than 1 so it can't shoot if < 1 bullet

                    if(tempObject.isHasUpgrade()) // If the mushroom is picked up shoots 3 bullets
                    {

                    handler.addObject(new Bullet(tempObject.getX() + 14, tempObject.getY() + 14, ID.Bullet, handler, mx, my, ss));
                    handler.addObject(new Bullet(tempObject.getX() + 16, tempObject.getY() + 24, ID.Bullet, handler, mx, my, ss));
                    handler.addObject(new Bullet(tempObject.getX() + 18, tempObject.getY() + 32, ID.Bullet, handler, mx, my, ss));


                    game.ammo = game.ammo - 3;
                }

                else { // Default shooting amount, only one bullet
                    handler.addObject(new Bullet(tempObject.getX() + 16, tempObject.getY() + 24, ID.Bullet, handler, mx, my, ss));

                    game.ammo--;
                }

            }
        }
    }
}


