package gamefiles;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import collisionsystem.Counter;
import sprites.SpriteCollection;

/**
 * A CountdownAnimation class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Counter countDown;

    /**
     * constractor.
     *
     * @param numOfSeconds
     *            the num of second is take.
     * @param countFrom
     *            how many to count.
     * @param gameScreen
     *            the game screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.countDown = new Counter(countFrom);
    }

    /**
     * @param d
     *            the drawsurface to drawon.
     * @param dt
     *            the speed change dt.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        Sleeper sleep = new Sleeper();
        int i = this.countDown.getValue() + 1;
        if (i == 0) {
            this.stop = true;
            return;
        }
        if (i != 0) {
            d.setColor(Color.WHITE);
            d.drawText(GameLevel.WIDTH / 2, GameLevel.HEIGHT / 2, String.valueOf(i - 1), 70);
        }
        sleep.sleepFor(1000 / ((int) this.numOfSeconds / this.countFrom));
        this.countDown.decrease(1);
    }

    /**
     * when the animation shuold stop.
     *
     * @return this.stop.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
