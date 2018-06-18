package animation;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamefiles.GameLevel;
import sprites.SpriteCollection;

/**
 * A EndScreen class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class EndScreen implements Animation {

    private SpriteCollection gameScreen;
    private Boolean stop;
    private GameLevel gameLevel;
    private KeyboardSensor keyboard;
    private Image image1 = null;
    private Image image2 = null;

    /**
     * @param gameScreen
     *            the game screen.
     * @param gameLevel
     *            the game level.
     * @param k
     *            the keyboard sensor.
     */
    public EndScreen(SpriteCollection gameScreen, GameLevel gameLevel, KeyboardSensor k) {
        this.gameScreen = gameScreen;
        this.stop = false;
        this.gameLevel = gameLevel;
        this.keyboard = k;
        try {
            image1 = ImageIO
                    .read(ClassLoader.getSystemClassLoader().getResourceAsStream("background_images/Game_Over_1.jpg"));
            image2 = ImageIO
                    .read(ClassLoader.getSystemClassLoader().getResourceAsStream("background_images/you_win.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * draw a message when the game is done.
     *
     * @param d
     *            the drawsurface to draw on.
     * @param dt
     *            double.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        int currentScore = this.gameLevel.getScore().getValue();
        if (this.gameLevel.getLives().getValue() == 0) {
            d.drawImage(0, 0, image1);
            d.setColor(Color.WHITE);
            d.drawText(GameLevel.WIDTH - 350, GameLevel.HEIGHT / 2 + 150, " " + currentScore, 50);
        } else {
            d.drawImage(0, 0, image2);
            d.setColor(Color.WHITE);
            d.drawText(GameLevel.WIDTH - 350, GameLevel.HEIGHT / 2 + 150, " " + currentScore, 50);
        }
    }

    /**
     * @return this.stop
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
