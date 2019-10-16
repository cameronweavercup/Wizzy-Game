import javax.swing.*;
import java.awt.*;

// Splash screen to load before game starts
public class SplashScreen extends JWindow {
    private int height, width;
    private Dimension d;

    // The dimensions of the splash screen
    public SplashScreen(){
        width = 1169;
        height = 800;
        d = new Dimension(width, height);

        this.setSize(d);
        // Setting location of splash screen so it's centered
        this.setLocation(262, 60);
        this.getContentPane().add(new JLabel("", new ImageIcon("res\\splash.png"), SwingConstants.CENTER));

        this.setVisible(true);
        try { // We want to have the splash screen display for 6 seconds
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // So we don't see the window, just the picture
        this.setVisible(false);
        // Throwing the window away
        this.dispose();
    }
}
