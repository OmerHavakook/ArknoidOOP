package collisionsystem;

import sprites.Ball;

/**
 * HitListener interface.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit. The hitter
     * parameter is the Ball that's doing the hitting.
     *
     * @param beingHit
     *            the object that is being hit.
     * @param hitter
     *            the hiiting object.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
