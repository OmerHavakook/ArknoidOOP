package sprites;

import biuoop.DrawSurface;

/**
 * A Sprite interface.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d
     *            the draw surface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     *
     * @param dt
     *            double.
     */
    void timePassed(double dt);
}
