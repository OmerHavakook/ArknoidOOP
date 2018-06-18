package collisionsystem;

import gamefiles.GameLevel;
import invanders.Alien;
import invanders.AlienGroup;
import sprites.Ball;

/**
 * A BlockRemover class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter blocksRemained;
    private AlienGroup alienGroup;

    /**
     * Basic Constructor.
     *
     * @param game
     *            the Game to add.
     * @param blockNum
     *            Counter of Blocks that removed from game.
     * @param alienGroup
     *            Counter of BLocks Remained.
     */
    public BlockRemover(GameLevel game, Counter blockNum, AlienGroup alienGroup) {
        this.game = game;
        this.blocksRemained = blockNum;
        this.alienGroup = alienGroup;
    }

    /**
     * removes a Block from game when block HitPoints reaches 0.
     *
     * @param beingHit
     *            the vlock veing hit.
     * @param hitter
     *            the ball that hits this block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit instanceof Alien) {
            if (hitter.ballFromAlien()) {
                return;
            }
            this.blocksRemained.decrease(1);
            this.alienGroup.updateMatrix((Alien) beingHit);
        }
        if (beingHit.getHits() == 0) {
            this.game.removeBall(hitter);
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
        }
    }
}
