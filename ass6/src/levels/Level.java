package levels;

import java.util.List;

import collisionsystem.Block;
import collisionsystem.Velocity;
import sprites.Background;
import sprites.Sprite;

/**
 * Level class.
 *
 */
public class Level implements LevelInformation {

    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Background background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * constructor.
     *
     * @param initialBallVelocities
     *            list.
     * @param paddleSpeed
     *            int.
     * @param paddleWidth
     *            int.
     * @param levelName
     *            string.
     * @param background
     *            Background.
     * @param blocks
     *            list.
     * @param numberOfBlocksToRemove
     *            int.
     */
    public Level(List<Velocity> initialBallVelocities, int paddleSpeed, int paddleWidth, String levelName,
            Background background, List<Block> blocks, int numberOfBlocksToRemove) {
        this.numberOfBalls = initialBallVelocities.size();
        this.initialBallVelocities = initialBallVelocities;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.background = background;
        this.blocks = blocks;
        this.numberOfBlocksToRemove = numberOfBlocksToRemove;

    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

}
