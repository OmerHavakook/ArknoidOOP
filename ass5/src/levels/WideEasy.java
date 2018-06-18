package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import collisionsystem.Block;
import collisionsystem.Velocity;
import gamefiles.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.Background;
import sprites.Sprite;

/**
 * A WideEasy class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class WideEasy implements LevelInformation {

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
    public WideEasy() {
        this.numberOfBalls = 10;
        this.setInitialBallVelocities();
        this.paddleSpeed = 3;
        this.paddleWidth = 500;
        this.levelName = "Wide Easy";
        this.background = new Background(2);
        this.setBlock();
        this.numberOfBlocksToRemove = 15;

    }

    /**
     * set the velocities of the balss in level.
     */
    public void setInitialBallVelocities() {
        this.initialBallVelocities = new ArrayList<Velocity>();
        int angle = 300;
        for (int i = 0; i < 10; i++) {
            this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(angle, 4));
            angle += 20;
        }
    }

    /**
     * set the blockes in this level.
     */
    public void setBlock() {
        this.blocks = new ArrayList<Block>();
        int x = 20;
        Color c = Color.RED;
        for (int i = 0; i < 15; i++) {
            if (i == 2) {
                c = Color.BLUE;
            } else if (i == 4) {
                c = Color.GREEN;
            } else if (i == 7) {
                c = Color.yellow;
            } else if (i == 9) {
                c = Color.orange;
            } else if (i == 11) {
                c = Color.PINK;
            } else if (i == 13) {
                c = Color.BLUE;
            }
            Rectangle rectangle = new Rectangle(new Point(x, 300), 50, 30);
            Block b = new Block(rectangle, c, 1);
            this.blocks.add(b);
            x += 50;
        }
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
