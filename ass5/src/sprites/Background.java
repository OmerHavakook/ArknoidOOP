
package sprites;

import java.awt.Color;
import biuoop.DrawSurface;
import gamefiles.GameLevel;

/**
 * A Background class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Background implements Sprite {

    private int levelNum;
    int checker = 0;
    int x = 30, y = 30;

    /**
     * Basic Constructor.
     */
    public Background(int ln) {
        this.levelNum = ln;
    }

    /**
     * draw this on give drawsurface.
     *
     * @param d
     *            the drawsurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (this.levelNum == 1) {
            this.draw1(d);
        } else if (this.levelNum == 2) {
            this.draw2(d);
        } else if (this.levelNum == 3) {
            this.draw3(d);
        } else {
            this.draw4(d);
        }
    }

    /**
     * notify this obj that timepass.
     *
     * @param dt
     *            the speed change dt.
     */
    @Override
    public void timePassed() {
        if (this.levelNum == 1) {
            this.checker += 1;
        }
        return;
    }

    /**
     * draw level 1.
     * 
     * @param d
     *            the draw surface.
     */
    public void draw1(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle((int) 0, (int) 0, (int) GameLevel.WIDTH, (int) GameLevel.HEIGHT);
        d.setColor(Color.BLUE);
        d.drawCircle(GameLevel.WIDTH / 2, (int) (GameLevel.HEIGHT * 0.3 + GameLevel.HEIGHT / 30), 40);
        d.drawCircle(GameLevel.WIDTH / 2, (int) (GameLevel.HEIGHT * 0.3 + GameLevel.HEIGHT / 30), 60);
        d.drawCircle(GameLevel.WIDTH / 2, (int) (GameLevel.HEIGHT * 0.3 + GameLevel.HEIGHT / 30), 80);
        d.drawCircle(GameLevel.WIDTH / 2, (int) (GameLevel.HEIGHT * 0.3 + GameLevel.HEIGHT / 30), 100);
        d.drawLine(GameLevel.WIDTH / 2 - 120, (int) (GameLevel.HEIGHT * 0.3 + GameLevel.HEIGHT / 30),
                GameLevel.WIDTH / 2 + 120, (int) (GameLevel.HEIGHT * 0.3 + GameLevel.HEIGHT / 30));
        d.drawLine(GameLevel.WIDTH / 2, (int) (GameLevel.HEIGHT * 0.3 + GameLevel.HEIGHT / 30) - 120,
                GameLevel.WIDTH / 2, (int) (GameLevel.HEIGHT * 0.3 + GameLevel.HEIGHT / 30) + 120);
    }

    /**
     * draw level 2.
     * 
     * @param d
     *            the draw surface.
     */
    public void draw2(DrawSurface d) {
        d.setColor(new Color(255, 255, 153));
        for (int i = 0; i < 90; i++) {
            d.drawLine((int) (GameLevel.WIDTH * 0.2), (int) (0.2 * GameLevel.HEIGHT), GameLevel.WIDTH * 6 / 7 - i * 10,
                    (int) (0.4 * GameLevel.HEIGHT));
        }

        d.setColor(new Color(255, 178, 172));
        d.fillCircle((int) (0.2 * GameLevel.WIDTH), (int) (0.2 * GameLevel.HEIGHT), 70);
        d.setColor(new Color(255, 255, 102));
        d.fillCircle((int) (0.2 * GameLevel.WIDTH), (int) (0.2 * GameLevel.HEIGHT), 60);
        d.setColor(new Color(255, 255, 153));
        d.fillCircle((int) (0.2 * GameLevel.WIDTH), (int) (0.2 * GameLevel.HEIGHT), 50);
    }

    /**
     * draw level 3.
     * 
     * @param d
     *            the draw surface.
     */
    public void draw3(DrawSurface d) {
        d.setColor(new Color(224, 224, 224));
        d.fillRectangle((int) 0, (int) 0, (int) GameLevel.WIDTH, (int) GameLevel.HEIGHT);
        d.setColor(new Color(32, 32, 32));
        d.fillRectangle(100, 430, 98, 170);
        d.setColor(new Color(224, 224, 224));
        int x = 0;
        int y = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(105 + x, 437 + y, 12, 22);
                x += 19;
            }
            x = 0;
            y += 30;
        }
        d.setColor(new Color(50, 50, 50));
        d.fillRectangle(140, 390, 20, 40);
        d.setColor(new Color(70, 70, 70));
        d.fillRectangle(145, 240, 10, 150);
        d.setColor(new Color(255, 255, 0));

        d.fillCircle(150, 240, 10);
        d.setColor(Color.red);
        d.fillCircle(150, 240, 7);
        d.setColor(new Color(224, 224, 224));
        d.fillCircle(150, 240, 3);
    }

    /**
     * draw level 4.
     * 
     * @param d
     *            the draw surface.
     */
    public void draw4(DrawSurface d) {
        d.setColor(new Color(0, 128, 255));
        d.fillRectangle((int) 0, (int) 0, (int) GameLevel.WIDTH, (int) GameLevel.HEIGHT);
        d.setColor(new Color(224, 224, 224));
        d.fillCircle(95, 395, 22);
        d.fillCircle(115, 410, 24);
        d.fillCircle(550, 470, 20);
        d.fillCircle(570, 485, 22);

        int x = 0;
        for (int i = 0; i < 10; i++) {
            d.drawLine(550 + x, 470, 520 + x, 600);
            d.drawLine(95 + x, 395, 65 + x, 600);
            x += 9;
        }

        d.setColor(new Color(192, 192, 192));
        d.fillCircle(125, 385, 25);
        d.fillCircle(580, 460, 25);

        d.setColor(new Color(160, 160, 160));
        d.fillCircle(145, 410, 27);
        d.fillCircle(168, 390, 28);
        d.fillCircle(600, 485, 27);
        d.fillCircle(622, 465, 28);

    }

}
