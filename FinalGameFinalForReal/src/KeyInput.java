import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// A class for user key inputs - left handed friendly ;)
public class KeyInput extends KeyAdapter {

    // Reference for the handler created in the Game class
    Handler handler;

    public KeyInput(Handler handler){
        this.handler = handler;
    }



    // When a key is pressed down
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // Goes through all of the objects
        for (int i = 0; i < handler.object.size(); i++) {
            // if the object is the player object and a directional key is pressed, sets the boolean to true
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Player){
                if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){handler.setUp(true);}
                if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){handler.setLeft(true);}
                if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){handler.setDown(true);}
                if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){handler.setRight(true);}

        }
    } }
    // When the key is released
    public void keyReleased(KeyEvent e){

            int key = e.getKeyCode();
             // Goes through all of the objects
            for (int i = 0; i < handler.object.size(); i++) {
                // if the object is the player object and a directional key is released, sets the boolean to false
                GameObject tempObject = handler.object.get(i);
                if(tempObject.getId() == ID.Player){
                    if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){handler.setUp(false);}
                    if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){handler.setLeft(false);}
                    if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){handler.setDown(false);}
                    if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){handler.setRight(false);}

                }
        }
    }
}
