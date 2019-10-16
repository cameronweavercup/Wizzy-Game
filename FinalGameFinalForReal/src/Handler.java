import java.awt.*;
import java.util.LinkedList;

// One class to rule them all!
// This class will allow us to control all objects more efficiently
// Runs through a loop and updates all objects in game
public class Handler {

    // LinkedList to add ALL of our game objects to
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    // Setting all movement bools to false by default
    private boolean up = false,
                  down = false,
                 right = false,
                  left = false;


    public void tick(){ // To update - runs through all objects and stores them in the temp obj and "ticks" them
        for(int i = 0; i < object.size(); i ++){
            GameObject tempObject = object.get(i);
            tempObject.tick();

        }
    }

    public void render(Graphics g){ // To draw - runs through all objects and stores them in the temp obj and draws them
        for(int i = 0; i < object.size(); i ++){
            GameObject tempObject = object.get(i);
            tempObject.render(g);

        }

    }

    // Behaviors - allows us to add/remove objects from our LinkedList
    public void addObject(GameObject tempObject){
        object.add(tempObject);
    }

    public void removeObject(GameObject tempObject){
        object.remove(tempObject);
    }

    // Accessors and Muties
    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
}
