package collisionsystem;

/**
 * HitNotifier interface.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl
     *            the hl to add.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl
     *            the hl to remove.
     */
    void removeHitListener(HitListener hl);
}
