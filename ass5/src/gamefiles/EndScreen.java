package gamefiles;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
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
    }

    /**
     * draw a message when the game is done.
     *
     * @param d
     *            the drawsurface to draw on.
     * @param dt
     *            the speed change dt.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        int currentScore = this.gameLevel.getScore().getValue();
        this.gameScreen.drawAllOn(d);
        if (this.gameLevel.getLives().getValue() == 0) {
            d.setColor(Color.gray);
            d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
            d.setColor(Color.black);
            d.drawText(GameLevel.WIDTH / 3, GameLevel.HEIGHT / 2, "Game Over. Your score is " + currentScore, 20);
        } else {
            d.setColor(Color.gray);
            d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
            d.setColor(Color.black);
            d.drawText(GameLevel.WIDTH / 3, GameLevel.HEIGHT / 2, "You Win! Your score is " + currentScore, 20);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
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
