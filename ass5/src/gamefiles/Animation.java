package gamefiles;

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
     * @param dt
     *            dt speed change.
     */
    void doOneFrame(DrawSurface d);

    /**
     * detrmine if animation should stop.
     *
     * @return true if should stop false otherwise.
     */
    boolean shouldStop();
}
