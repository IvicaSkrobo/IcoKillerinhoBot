package killerinhobotgame;

/**
 * Created by Ico on 23.11.2015..
 */

import java.awt.Rectangle;
import java.awt.Image;

public class Tile {

    private Rectangle r;
    private int tileX, tileY, speedX, type;
    public Image tileImage;

    private Background bg = StartingClass.getBg1();
    private Mech mech = StartingClass.getMech();

    public Tile(int x, int y, int typeInt) {

        tileX = x * 40;
        tileY = y * 40;

        type = typeInt;

        r = new Rectangle();

        switch (type) {
            case 8:
                tileImage = StartingClass.tilegrassTop;
                break;
            case 5:
                tileImage = StartingClass.tiledirt;
                break;

            case 4:
                tileImage = StartingClass.tilegrassLeft;
                break;
            case 6:
                tileImage = StartingClass.tilegrassRight;
                break;
            case 2:
                tileImage = StartingClass.tilegrassBot;
                break;
            default:
                type = 0;
                break;
        }



    }


    public void update() {

        speedX = bg.getSpeedX() * 5;
        tileX += speedX;

        r.setBounds(tileX, tileY, 40, 40);

        if (r.intersects(Mech.yellowRed) && type != 0) {
            checkVerticalCollision(mech.rect, mech.rect2);
            checkSideCollision(mech.rect3, mech.rect4, mech.footleft, mech.footright);
        }

    }

    public int getTileX() {
        return tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public Image getTileImage() {
        return tileImage;
    }

    public void setTileImage(Image tileImage) {
        this.tileImage = tileImage;
    }


    public void checkVerticalCollision(Rectangle rtop, Rectangle rbot) {
        if (rtop.intersects(r)) {
        }

        if (rbot.intersects(r) && type==8) {
            mech.setJumped(false);
            mech.setSpeedY(0);
            mech.setCenterY(tileY-63);

        }


    }
    public void checkSideCollision(Rectangle rect3,Rectangle rect4,Rectangle leftfoot,Rectangle rightfoot){

        if(type!=5 && type!=2 && type !=0) {
            if (rect3.intersects(r)) {
                mech.setCenterX(tileX + 102);
                mech.setSpeedX(0);
            }
            else if (leftfoot.intersects(r)) {
                mech.setCenterX(tileX +90);
                mech.setSpeedY(0);
            }
            if (rect4.intersects(r)) {
                mech.setCenterX(tileX-62);
                mech.setSpeedX(0);

            }
            else if(rightfoot.intersects(r)){
                mech.setCenterX(tileX -45);
                mech.setSpeedX(0);

            }
        }
    }
}