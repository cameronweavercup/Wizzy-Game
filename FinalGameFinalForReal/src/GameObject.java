import java.awt.*;


// The basic structure of all objects
public abstract class GameObject {


    // Bool to see if the shroom upgrade has been picked up
    // We put this in gameObj instead of wizard because temp objects can only see the gameObj class
    private boolean hasUpgrade = false;


    // The location of the object
    protected int x;
    protected int y;

    // The speed and direction of the object
    protected float velX = 0, velY = 0;

    // An identifier for the object type
    protected ID id;

    // The artwork
    protected SpriteSheet ss;


    // Constructor
    public GameObject(int x, int y, ID id, SpriteSheet ss)
    {
        setX(x);
        setY(y);
        setId(id);
        this.ss = ss;
    }

    // Every object needs to update
    public abstract void tick();
    // Every object needs to be drawn
    public abstract void render(Graphics g);
    // Every object has a hitbox
    public abstract Rectangle getBounds();



    public boolean isHasUpgrade() {
        return hasUpgrade;
    }

    public void setHasUpgrade(boolean hasUpgrade) {
        this.hasUpgrade = hasUpgrade;
    }

    // Accessors and Muties
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
