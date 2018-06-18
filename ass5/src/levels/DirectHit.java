package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import collisionsystem.Block;
import collisionsystem.Velocity;
import gamefiles.AnimationRunner;
import gamefiles.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.Background;
import sprites.Sprite;

/**
 * A DirectHit class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class DirectHit implements LevelInformation {

    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Background background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * Basic constructor.
     *
     * @param initialBallVelocities
     *            list of ball velo.
     * @param paddleSpeed
     *            paddle speed.
     * @param paddleWidth
     *            paddle width.
     * @param levelName
     *            leve name.
     * @param background
     *            level background.
     * @param blocks
     *            blocks list.
     * @param numberOfBlocksToRemove
     *            number of block to remove.
     */
    public DirectHit() {
        this.numberOfBalls = 1;
        this.setInitialBallVelocities();
        this.paddleSpeed = 7;
        this.paddleWidth = 70;
        this.levelName = "Direct Hit";
        this.background = new Background(1);
        this.setBlock();
        this.numberOfBlocksToRemove = 1;

    }

    /**
     * set the velocities of the balss in level.
     */
    public void setInitialBallVelocities() {
        this.initialBallVelocities = new ArrayList<Velocity>(1);
        this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(60, 4));
    }

    /**
     * set the blockes in this level.
     */
    public void setBlock() {
        this.blocks = new ArrayList<Block>(1);
        Rectangle rectangle = new Rectangle(new Point((GameLevel.WIDTH / 2) - 15, 180), 30, 30);
        Block b = new Block(rectangle, Color.RED, 1);
        this.blocks.add(b);
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
