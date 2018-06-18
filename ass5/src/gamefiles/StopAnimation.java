package gamefiles;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * A StopAnimation class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class StopAnimation implements Animation {

    private KeyboardSensor ks;
    private String stopKey;
    private AnimationRunner run;
    private Animation animation;
    private boolean running;
    private boolean isAlreadyPressed;

    /**
     * Basic Constructor.
     *
     * @param sensor
     *            the keyboardsensor.
     * @param key
     *            key to stop.
     * @param animation
     *            animation this decorates.
     */
    public StopAnimation(AnimationRunner ar, KeyboardSensor sensor, String key, Animation animation) {
        this.ks = sensor;
        this.stopKey = key;
        this.animation = animation;
        this.running = true;
        this.isAlreadyPressed = true;
        this.run = ar;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.ks.isPressed(this.stopKey)) {
            this.isAlreadyPressed = false;
        }
        if (this.ks.isPressed(this.stopKey) && !this.isAlreadyPressed) {
            running = false;
            this.run.getGui().close();
        }
        animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

}
