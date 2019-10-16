// Deprecated packages but they work on Cam's machine so we're keeping them
/*import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;*/

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Wizard extends GameObject implements Sound {

    Handler handler;

    Game game;

    private BufferedImage wizard = null;

    // Bool to check if character is dead
    private boolean isDead = false;

    // Constructor
    public Wizard(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {
        super(x, y, id, ss);
        playSound();
        this.handler = handler;
        this.game = game;
        wizard = ss.grabImage(1, 2, 32, 32);


    }

    @Override
    public void tick() {
        // Adds the direction and speed to former position for the next render
        x += velX;
        y += velY;

         collision(); // Handles collision, defined lower down in this class

        // See if you lost the game by checking if the player is dead
        checkIfLost();

        // Character Movement
        // If up is pressed subtract 5 from Y moving sprite up
        if(handler.isUp()){
            velY = -5;
        } // If up is released and down isn't being pressed there is no change, otherwise if down IS being pressed
        else if(!handler.isDown()){// setting it to 0 would cause lag - the same applies to the other directions
            velY = 0;
        }
        if(handler.isDown()){
            velY = 5;
        }
        else if(!handler.isUp()){
            velY = 0;
        }
        if(handler.isRight()){
            velX = 5;
        }
        else if (!handler.isLeft()){
            velX = 0;
        }
        if(handler.isLeft()){
            velX = -5;
        }
        else if(!handler.isRight()){
            velX = 0;
        }

    }
    // Function to see if you lost the game by checking if the player is dead
    private void checkIfLost(){
        if(isDead){

            JOptionPane.showMessageDialog(game, "You have died!", "", JOptionPane.ERROR_MESSAGE);

            System.exit(0);

        }
    }

    private void collision(){ // Handles collision detection to make object sprites disappear
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Block){
                if(getBounds().intersects(tempObject.getBounds())){
                    x += velX * -1;
                    y += velY * -1;
                }
            }
            if(tempObject.getId() == ID.Loot){
                if(getBounds().intersects(tempObject.getBounds())){
                    game.ammo += 20;
                    handler.removeObject(tempObject);
                }
            }
            if(tempObject.getId() == ID.Shroom){
                if(getBounds().intersects(tempObject.getBounds())){
                    setHasUpgrade(true);
                    handler.removeObject(tempObject);

                }
            }
            if(tempObject.getId() == ID.Exit){
                if(getBounds().intersects(tempObject.getBounds())){
                    JOptionPane.showMessageDialog(null, "Congrats! You have won the game!");
                    System.exit(0);
                }
            }
            if(tempObject.getId() == ID.Beer){
                if(getBounds().intersects(tempObject.getBounds())) {
                    if (game.hp <= 220) {
                        game.hp += 30;
                        handler.removeObject(tempObject);
                    }
                }
            }

            if(tempObject.getId() == ID.Enemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    game.hp --;
                    // Cams smart mofo code
                    if(game.hp == 0){
                        handler.removeObject(this);

                        this.isDead = true;
                    }

                }
            }

        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(wizard, x, y, null);

    }

    // The hitbox of the character and also the bounding box, this is the line that gets checked for collisions
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }




    public void playSound(){
        try {  // This audio code is WORKING! and it came from Dr. Jody Strausser himself
            // Open an audio input stream.
            File soundFile = new File("src\\slayer.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            try {
                clip.open(audioIn);
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }



/////////////// NON-FUNCTIONAL AUDIO CODE ///////////////
   /* @Override
    public void playSound() {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;

        ContinuousAudioDataStream loop = null;

        try {
            InputStream test = new FileInputStream("src\\slayer.wav");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MGP.start(loop);*/

        /*  // NON-WORKING audio stuff, tried like 5+ different things
        try {
            Clip clip;
            // Open an audio input stream.
            URL url = this.getClass().getClassLoader().getResource("slayer.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            try {
                clip = AudioSystem.getClip();
                clip.open(audioIn);
                System.out.println("pass");
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
            // Open audio clip and load samples from the audio input stream.

        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        catch (IOException e) {

            e.printStackTrace();
        }
        */

    }



