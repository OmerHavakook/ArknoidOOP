package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * A PauseScreen class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class PauseScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * pause the screen.
     *
     * @param k
     *            the keyborad sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * when the animation should stop.
     *
     * @return boolean.
     */
    public boolean shouldStop() {
        return this.stop;
    }

}
