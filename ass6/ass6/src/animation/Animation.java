package animation;

import biuoop.DrawSurface;

/**
 * A Animation interface.
 *
 */
public interface Animation {
    /**
     * draws one frame.
     *
     * @param d
     *            the drawsurface to drawon.
     * @param dt TODO
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * detrmine if animation should stop.
     *
     * @return true if should stop false otherwise.
     */
    boolean shouldStop();
}
