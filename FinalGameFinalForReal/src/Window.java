import javax.swing.*;
import java.awt.*;

// Class we call to create the game window

// Creates a new window
public class Window {

    // Constructor
    public Window(int width, int height, String title, Game game){
        // Create JFrame with title passed in
        JFrame frame = new JFrame(title);
        // Setting the game window size
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        // Adding instance of game to the JFrame
        frame.add(game);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
