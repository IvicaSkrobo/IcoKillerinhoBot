package killerinhobotgame;
/**
 * This program has been created by following
 * tutorial from http://www.kilobolt.com/tutorials.html ,
 * <p/>
 * Very well written free tutorial for learning how to create a Java based platformer
 * recommended *****
 */

import killerinhobotgame.framework.Animation;


import java.applet.Applet;
import java.awt.*;
import java.lang.String;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class StartingClass extends Applet implements Runnable, KeyListener {


    enum GameState {
        Running, Dead
    }

    GameState state = GameState.Running;
    private static Mech mech;
    public static Helliboy hb, hb2;
    public static int score = 0;
    private Font font = new Font(null, Font.BOLD, 30);
    private Image image, currentSprite,
            character, character2, character3, characterDown, characterJumped, background, helliboy, helliboy2, helliboy3, helliboy4, helliboy5;

    public static Image tiledirt,/*tileocean,*/
            tilegrassTop, tilegrassLeft, tilegrassRight, tilegrassBot;


    private Animation anim, hanim;//anim for main character, hanim for helliboys
    private Graphics second;
    private URL base;
    private static Background bg1, bg2;

    private ArrayList<Tile> tilearray = new ArrayList<Tile>();

    @Override
    public void init() {

        setSize(800, 480);
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle("Q-Bot Alpha");
        try {
            base = StartingClass.class.getResource("/data/");
        } catch (Exception e) {
            //handle exception
        }
        System.out.println(base);

        //character image
        character = getImage(base, "../data/character.png");
        character2 = getImage(base, "../data/character2.png");
        character3 = getImage(base, "../data/character3.png");
        //background image
        background = getImage(base, "../data/background.png");
        //charachter down
        characterDown = getImage(base, "../data/down.png");
        //character jumped
        characterJumped = getImage(base, "../data/jumped.png");


        helliboy = getImage(base, "../data/heliboy.png");
        helliboy2 = getImage(base, "../data/heliboy2.png");
        helliboy3 = getImage(base, "../data/heliboy3.png");
        helliboy4 = getImage(base, "../data/heliboy4.png");
        helliboy5 = getImage(base, "../data/heliboy5.png");

        tiledirt = getImage(base, "../data/tiledirt.png");
        // tileocean = getImage(base,"../data/tileocean.png");
        tilegrassTop = getImage(base, "../data/tilegrasstop.png");
        tilegrassBot = getImage(base, "../data/tilegrassbot.png");
        tilegrassLeft = getImage(base, "../data/tilegrassleft.png");
        tilegrassRight = getImage(base, "../data/tilegrassright.png");


        anim = new Animation();
        anim.addFrame(character, 1250);
        anim.addFrame(character2, 50);
        anim.addFrame(character3, 50);
        anim.addFrame(character2, 50);

        hanim = new Animation();
        hanim.addFrame(helliboy, 100);
        hanim.addFrame(helliboy2, 100);
        hanim.addFrame(helliboy3, 100);
        hanim.addFrame(helliboy4, 100);
        hanim.addFrame(helliboy5, 100);
        hanim.addFrame(helliboy4, 100);
        hanim.addFrame(helliboy3, 100);
        hanim.addFrame(helliboy2, 100);

        currentSprite = anim.getImage();


    }

    @Override
    public void start() {
        state = GameState.Running;


        bg1 = new Background(0, 0);
        bg2 = new Background(2160, 0);
        mech = new Mech();

        // Initialize Tiles

        try {
            loadMap("C:/Users/Ico/IdeaProjects/IcoKillerinhoBot/out/production/IcoKillerinhoBot/data/map1.txt");
        } catch (IOException e) {


            e.printStackTrace();
        }


        hb = new Helliboy(340, 360);
        hb2 = new Helliboy(700, 360);
        Thread thread = new Thread(this);


        thread.start();


    }

    private void loadMap(String filename) throws IOException {

        ArrayList lines = new ArrayList();
        int width = 0;
        int height;


        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while (true) {
            String line = reader.readLine();
            // end of lines
            if (line == null) {
                reader.close();
                break;
            }
            if (!line.startsWith("!")) {
                lines.add(line);
                width = Math.max(width, line.length());
            }
        }

        height = lines.size();
        //12 za j isprobaj
        for (int j = 0; j < height; j++) {
            String line = (String) lines.get(j);
            for (int i = 0; i < width; i++) {

                if (i < line.length()) {
                    char ch = line.charAt(i);
                    Tile t = new Tile(i, j, Character.getNumericValue(ch));
                    tilearray.add(t);
                }
            }
        }
    }


    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void run() {

        if (state == GameState.Running) while (true) {

            mech.update();
            if (mech.isJumped()) {
                currentSprite = characterJumped;
            } else if (!mech.isJumped() && !mech.isDucked()) {
                currentSprite = anim.getImage();
            }

            ArrayList projectiles = mech.getProjectiles();
            for (int i = 0; i < projectiles.size(); i++) {
                Projectile p = (Projectile) projectiles.get(i);
                if (p.isVisible() == true) {
                    p.update();
                } else {
                    projectiles.remove(i);
                }
            }

            updateTiles();

            hb.update();
            hb2.update();
            bg1.update();

            bg2.update();

            animate();

            repaint();

            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if (mech.getCenterY() > 500) {
                state = GameState.Dead;
            }
        }


    }

    public void animate() {
        anim.update(10);
        hanim.update(50);
    }

    @Override
    public void update(Graphics g) {

        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());

            second = image.getGraphics();
        }
        second.setColor(getBackground());
        second.fillRect(0, 0, getWidth(), getHeight());
        second.setColor(getForeground());
        paint(second);

        g.drawImage(image, 0, 0, this);

    }

    @Override
    public void paint(Graphics g) {

        if (state == GameState.Running) {
            g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
            g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
            paintTiles(g);
            ArrayList projectiles = mech.getProjectiles();
            for (int i = 0; i < projectiles.size(); i++) {
                Projectile p = (Projectile) projectiles.get(i);
                g.setColor(Color.YELLOW);
                g.fillRect(p.getX(), p.getY(), 10, 5);
            }


            g.drawImage(currentSprite, mech.getCenterX() - 61, mech.getCenterY() - 63, this);
            g.drawImage(hanim.getImage(), hb.getCenterX() - 48, hb.getCenterY() - 48, this);
            g.drawImage(hanim.getImage(), hb2.getCenterX() - 38, hb2.getCenterY() - 48, this);

            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString(Integer.toString(score), 740, 30);

        } else if (state == GameState.Dead) {
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, 800, 480);
            g.setColor(Color.WHITE);
            g.drawString("GG", 360, 240);
        }

    }

    private void updateTiles() {

        for (int i = 0; i < tilearray.size(); i++) {
            Tile t = (Tile) tilearray.get(i);
            t.update();
        }
    }

    private void paintTiles(Graphics g) {

        for (int i = 0; i < tilearray.size(); i++) {
            Tile t = (Tile) tilearray.get(i);
            g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);

        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:

                currentSprite = characterDown;
                if (!mech.isJumped()) {
                    mech.setDucked(true);
                    mech.setSpeedX(0);
                }
                break;
            case KeyEvent.VK_LEFT:

                mech.moveLeft();
                mech.setMovingLeft(true);
                break;
            case KeyEvent.VK_RIGHT:

                mech.moveRight();
                mech.setMovingRight(true);
                break;
            case KeyEvent.VK_SPACE:

                mech.jump();
                break;
            case KeyEvent.VK_A:
                if (!mech.isDucked() && !mech.isJumped()) {
                    mech.shoot();
                    mech.setReadyToFire(false);

                }
                break;
            case KeyEvent.VK_ENTER:
                if (state == GameState.Dead) {
                    Restart();
                }
                break;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                currentSprite = anim.getImage();
                mech.setDucked(false);
                break;
            case KeyEvent.VK_LEFT:
                mech.stop();
                mech.stopLeft();
                break;
            case KeyEvent.VK_RIGHT:
                mech.stop();
                mech.stopRight();
                break;
            case KeyEvent.VK_SPACE:
                break;
            case KeyEvent.VK_A:
                mech.setReadyToFire(true);
                break;
            case KeyEvent.VK_ENTER:
                if (state == GameState.Dead) {
                    Restart();
                }
                break;


        }
    }


    public void Restart() {

        score = 0;

        bg1 = new Background(0, 0);
        bg2 = new Background(2160, 0);
        mech = new Mech();


        tilearray = new ArrayList<Tile>();

        start();


        System.out.println(score);



    }

    public static Background getBg1() {

        return bg1;
    }

    public static Background getBg2() {
        return bg2;
    }

    public static Mech getMech() {
        return mech;
    }

}

