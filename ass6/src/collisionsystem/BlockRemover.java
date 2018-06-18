package collisionsystem;

import gamefiles.GameLevel;
import sprites.Ball;

/**
 * A BlockRemover class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter removedBlocks;
    private Counter blocksRemained;


    /**
     * Basic Constructor.
     *
     * @param game           the Game to add.
     * @param removedBlocks  Counter of Blocks that removed from game.
     * @param blocksRemained Counter of BLocks Remained.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks, Counter blocksRemained) {
        this.game = game;
        this.removedBlocks = removedBlocks;
        this.blocksRemained = blocksRemained;
    }

    /**
     * removes a Block from game when block HitPoints reaches 0.
     *
     * @param beingHit the vlock veing hit.
     * @param hitter   the ball that hits this block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHits() == 0) {
            beingHit.removeFromGame(game);
            beingHit.removeHitListener(this);
            this.removedBlocks.increase(1);
            this.blocksRemained.decrease(1);
        }
    }
}
