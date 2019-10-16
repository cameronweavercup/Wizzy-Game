// This class follows the character around keeping him in the middle of the screen unless he's near a boundary

public class Camera {
    private float x, y;

    // Camera position
    public Camera(float x, float y){
        this.x = x;
        this.y = y;
    }

    // Overriding tick method using "magic numbers" from Dr. G (Google)
    public void tick(GameObject object){

        x += ((object.getX() - x - 1000/2)) * 0.05f;
        y += ((object.getY() - y - 563/2)) * 0.05f;

        if(x <= 0) x = 0;
        if(x >= 1032 + 20) x = 1032 + 20;
        if(y <= 0) y = 0;
        if(y >= 563 + 60) y = 563 + 60;

    }

   // Accessors and Muties
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
