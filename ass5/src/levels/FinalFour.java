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
 * A FinalFour class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class FinalFour implements LevelInformation {

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
    public FinalFour() {
        this.numberOfBalls = 3;
        this.setInitialBallVelocities();
        this.paddleSpeed = 6;
        this.paddleWidth = 70;
        this.levelName = "Final Four";
        this.background = new Background(4);
        this.setBlock();
        this.numberOfBlocksToRemove = 105;

    }

    /**
     * set the velocities of the balss in level.
     */
    public void setInitialBallVelocities() {
        this.initialBallVelocities = new ArrayList<Velocity>();
        int angle = 330;
        for (int i = 0; i < 3; i++) {
            this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(angle, 4));
            angle += 30;
        }
    }

    /**
     * set the blockes in this level.
     */
    public void setBlock() {
        this.blocks = new ArrayList<Block>();
        int x = 20, y = 100;
        Color c = Color.RED;
        int count = 15;
        for (int i = 0; i < 7; i++) {
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
                } else if (i == 5) {
                    c = Color.green;
                } else if (i == 6) {
                    c = Color.white;
                }
                Rectangle rectangle = new Rectangle(new Point(x, y), 50, 30);
                Block b = new Block(rectangle, c, 1);
                this.blocks.add(b);
                x += 50;
            }
            y += 30;
            x = 20;
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
