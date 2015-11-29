package killerinhobotgame;

/**
 * Created by Ico on 18.11.2015..
 */
public class Background {

    int bgX;
    int bgY;
    int speedX;


public Background (int x, int y) {
    bgX = x;
    bgY = y;
    speedX = 0;

}

    public void update() {
        //System.out.println("backgroudn update metoda speedx ="+speedX);
        bgX +=speedX;
        if(bgX <=-2160){
            bgX+=4320;
        }

    }

    public int getBgX() {
        return bgX;
    }

    public void setBgX(int bgX) {
        this.bgX = bgX;
    }

    public int getBgY() {
        return bgY;
    }

    public void setBgY(int bgY) {
        this.bgY = bgY;
    }

    public int getSpeedX() {

        return speedX;

    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;

    }
}
