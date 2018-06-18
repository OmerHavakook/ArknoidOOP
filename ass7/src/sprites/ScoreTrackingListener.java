package sprites;

import collisionsystem.Block;
import collisionsystem.Counter;
import collisionsystem.HitListener;

/**
 * A ScoreTrackingListener class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * Basic constructor.
     */
    public ScoreTrackingListener() {
        this.currentScore = new Counter();
    }

    /**
     * Constructor given Score.
     *
     * @param count
     *            the ScoreCounter.
     */
    public ScoreTrackingListener(int count) {
        this.currentScore = new Counter(count);
    }

    /**
     * Constructor Given Counter.
     *
     * @param scoreCounter
     *            the score Counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * returns current score.
     *
     * @return this.currentScore.
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }

    /**
     * notify when hit is occured, add points to current score.
     *
     * @param beingHit
     *            the block that is being hit.
     * @param hitter
     *            the ball that hits beingHit Block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHits() == 0) {
            this.currentScore.increase(10);
        }

        this.currentScore.increase(5);

    }

}
