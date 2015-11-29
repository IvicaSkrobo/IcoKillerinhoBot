package killerinhobotgame;



/**
 * Created by Ico on 18.11.2015.. practice by following the tutorials
 */
import java.awt.Rectangle;
public class Enemy {


    private int power, speedX, centerX, centerY;
    private Background bg = StartingClass.getBg1();
    private Mech mech = StartingClass.getMech();

    public Rectangle r = new Rectangle(0, 0, 0, 0);

    public int health = 5;

    private int movementSpeed;


    public void update() {
        //System.out.println("helliboy update metoda speedx ="+speedX);

        follow();
        centerX += speedX;
        speedX = bg.getSpeedX() * 5 + movementSpeed;
        r.setBounds(centerX - 25, centerY - 25, 50, 60);

        if (r.intersects(Mech.yellowRed)) {
            checkCollision();
        }
    }


    private void checkCollision() {
        if (r.intersects(Mech.rect) || r.intersects(Mech.rect2) || r.intersects(Mech.rect3) || r.intersects(Mech.rect4))
        {
            System.out.println("collision");
    }

}

    public void follow() {
        if (centerX < -95 || centerX > 810) {
            movementSpeed = 0;
        } else if (Math.abs(mech.getCenterX() - centerX) < 5) {
            movementSpeed = 0;
        } else {
            if (mech.getCenterX() >= centerX) {
                movementSpeed = 1;
            } else {
                movementSpeed = -1;
            }
        }
    }
    public void die() {
    }

    public void attack() {
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public Background getBg() {
        return bg;
    }

    public void setBg(Background bg) {
        this.bg = bg;
    }
}
