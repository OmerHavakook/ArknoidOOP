package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import collisionsystem.Block;
import collisionsystem.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.Background;
import sprites.Sprite;

/**
 * A Green3 class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Green3 implements LevelInformation {

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
    public Green3() {
        this.numberOfBalls = 2;
        this.setInitialBallVelocities();
        this.paddleSpeed = 4;
        this.paddleWidth = 70;
        this.levelName = "Green 3";
        this.background = new Background(3);
        this.setBlock();
        this.numberOfBlocksToRemove = 40;

    }

    /**
     * set the velocities of the balss in level.
     */
    public void setInitialBallVelocities() {
        this.initialBallVelocities = new ArrayList<Velocity>();
        int angle = 330;
        for (int i = 0; i < 2; i++) {
            this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(angle, 4));
            angle += 60;
        }
    }

    /**
     * set the blockes in this level.
     */
    public void setBlock() {
        this.blocks = new ArrayList<Block>();
        int x = 280, y = 150, size = 50;
        Color c = Color.RED;
        int count = 10;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < count; j++) {
                if (i == 0) {
                    c = Color.GRAY;
                } else if (i == 0) {
                    c = Color.BLUE;
                } else if (i == 1) {
                    c = Color.YELLOW;
                } else if (i == 2) {
                    c = Color.orange;
                } else if (i == 3) {
                    c = Color.RED;
                } else if (i == 4) {
                    c = Color.MAGENTA;
                }
                Rectangle rectangle = new Rectangle(new Point(x, y), 50, 30);
                Block b = new Block(rectangle, c, 1);
                this.blocks.add(b);
                x += 50;
            }
            y += 30;
            x = 280 + size;
            size += 50;
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
