package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * A KeyPressStoppableAnimation class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor ks;
    private String stopKey;
    private Animation animation;
    private boolean running;
    private boolean isAlreadyPressed;
    private boolean ig;

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
    public KeyPressStoppableAnimation(Animation animation, KeyboardSensor sensor, String key) {
        this.ks = sensor;
        this.stopKey = key;
        this.animation = animation;
        this.running = false;
        this.isAlreadyPressed = true;
        this.ig = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.isAlreadyPressed) {
            this.ig = this.ks.isPressed(this.stopKey);
            this.isAlreadyPressed = false;
        }

        this.animation.doOneFrame(d, dt);

        if (this.ks.isPressed(this.stopKey)) {
            if (!ig) {
                this.running = true;
            }
        } else {
            ig = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.running;
    }

}
