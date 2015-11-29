package killerinhobotgame;

import java.util.ArrayList;
import java.awt.Rectangle;

public class Mech {

//private kako bi se varijable klase mogle zvati samo sa metodama ove klase

    final int JUMPSPEED = -15;
    final int MOVESPEED = 5;

    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    private int centerX = 100;
    private int centerY = 377;
    private boolean jumped = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean ducked = false;
    private boolean readyToFire = true;

    private static Background bg1 = StartingClass.getBg1();
    private static Background bg2 = StartingClass.getBg2();


    private int speedX = 0;
    private int speedY = 0;
    public static Rectangle rect = new Rectangle(0,0,0,0);
    public static Rectangle rect2 = new Rectangle(0,0,0,0);
    public static Rectangle rect3 = new Rectangle(0,0,0,0);
    public static Rectangle rect4 = new Rectangle(0,0,0,0);
    public static Rectangle yellowRed = new Rectangle(0,0,0,0);
    public static Rectangle footleft = new Rectangle(0,0,0,0);
    public static Rectangle footright = new Rectangle(0,0,0,0);




    public void update() {

        //movement of character and scrolling of the background
        if (speedX < 0) {
            centerX += speedX;

        }
        if (speedX == 0 || speedX < 0) {

            bg1.setSpeedX(0);
            bg2.setSpeedX(0);

        }
        if (centerX <= 200 && speedX > 0) {

            centerX += speedX;
        }
        if (speedX > 0 && centerX > 200) {
            bg1.setSpeedX(-MOVESPEED / 5);
            bg2.setSpeedX(-MOVESPEED / 5);
        }


        //Updates Y Position
        //centerY += speedY; //privid skoka
        centerY += speedY;


        //jumping, provjerava skok i namjesta brzinu skoka/pada



            speedY += 1; //speedY = -15 u slucaju skoka, pa se povecava -15,-14-13...13,14,15

            if (speedY>3) {  // u slucaju malog pomaka robota u +y smjeru , nema registracije skoka
                jumped =true;
            }


        //prevents going beyond x coordinate of 0
        if (centerX + speedX <= 60) {
            centerX = 61;

        }
//center of the robot for 34 left and 63 top
        rect.setRect(centerX - 34, centerY - 63, 68, 63);
        //63 bot from Y of rect1
        rect2.setRect(rect.getX(), rect.getY() + 63, 68, 64);

        rect3.setRect(rect.getX() -26, rect.getY()+32,26,20);
        rect4.setRect(rect.getX()+68,rect.getY()+32,26,20);
        yellowRed.setRect(centerX-110,centerY-110,180,180); //180*180, one tiles is 40*40 thats 25 tiles
        footleft.setRect(centerX - 50, centerY + 20, 50, 15);
        footright.setRect(centerX , centerY + 20, 50, 15);

    }


    //movement
    public void moveRight() {

        if (ducked == false) {

            speedX = MOVESPEED;
        }
    }

    public void moveLeft() {
        if (ducked == false) {


            speedX = -MOVESPEED;
        }
    }

    public void stopRight() {

        setMovingRight(false);
        stop();
    }

    public void stopLeft() {
        setMovingLeft (false);
        stop();
    }


    public void stop() {
        if(!isMovingRight() && !isMovingLeft()) {
            speedX = 0;
        }
        if(!isMovingRight() && isMovingLeft()){
            moveLeft();
        }
        if(isMovingRight() && !isMovingLeft()){
            moveRight();
        }
        }

    public void jump() {
        if (jumped == false) {
            speedY = JUMPSPEED ;
            jumped = true;
        }

    }

    //projectile shoot
    public void shoot() {
    if (readyToFire){
        Projectile p = new Projectile(centerX + 50,centerY-25);
        projectiles.add(p);
    }
}


    //getters


    public boolean isReadyToFire() {
        return readyToFire;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public boolean isJumped() {
        return jumped;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    //SETTERS


    public void setReadyToFire(boolean readyToFire) {
        this.readyToFire = readyToFire;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setJumped(boolean jumped) {
        this.jumped = jumped;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean isDucked() {
        return ducked;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public void setDucked(boolean ducked) {
        this.ducked = ducked;
    }


    public ArrayList getProjectiles () {
        return projectiles;
    }
}









