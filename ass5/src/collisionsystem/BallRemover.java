package collisionsystem;

import gamefiles.GameLevel;
import sprites.Ball;

/**
 * A BallRemover class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class BallRemover implements HitListener {

    private GameLevel game;

    /**
     * Basic Constructor.
     *
     * @param game
     *            the game this listener is related to.
     * @param removeBall
     *            counter of balls remover.
     */
    public BallRemover(GameLevel game) {
        this.game = game;
    }

    /**
     * this is activated when hit is ocuured.
     *
     * @param beingHit
     *            the object that is being hit.
     * @param hitter
     *            the hiiting object.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.game.getBallsRemain().decrease(1);
    }

}
