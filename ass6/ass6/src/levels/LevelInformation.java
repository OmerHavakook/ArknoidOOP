package levels;

import java.util.List;

import collisionsystem.Block;
import collisionsystem.Velocity;
import sprites.Sprite;

/**
 * a LevelInformation interface.
 *
 */
public interface LevelInformation {
    /**
     * return the number of balls.
     *
     * @return int.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return List of velocity.
     */
    List<Velocity> initialBallVelocities();

    /**
     * return the paddle speed.
     *
     * @return int.
     */
    int paddleSpeed();

    /**
     * return the paddle width.
     *
     * @return int.
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return String.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return Sprite.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color
     * and location.
     *
     * @return List<Block>.
     */
    List<Block> blocks();

    /**
     * Number of levels that should be removed.
     *
     * @return int.
     */
    int numberOfBlocksToRemove();
}
