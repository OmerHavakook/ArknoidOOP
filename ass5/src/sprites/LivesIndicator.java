package sprites;

import biuoop.DrawSurface;
import collisionsystem.Counter;

/**
 * A LivesIndicator class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class LivesIndicator implements Sprite {

    private Counter lives;

    /**
     * Basic Constructor.
     *
     * @param lives
     *            the lives counter.
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }

    /**
     * draws the score on given drawsurface.
     *
     * @param d
     *            the drawsurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        String scorestring = "Lives: " + String.valueOf(this.lives.getValue());
        d.drawText(0, 12, scorestring, 15);
    }

    /**
     * norifies obj that time passed.
     * 
     * @param dt
     *            the speed change dt.
     */
    @Override
    public void timePassed() {
    }

}
