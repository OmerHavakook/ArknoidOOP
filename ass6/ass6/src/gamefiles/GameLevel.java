package gamefiles;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collisionsystem.BallRemover;
import collisionsystem.Block;
import collisionsystem.BlockRemover;
import collisionsystem.Collidable;
import collisionsystem.Counter;
import collisionsystem.Paddle;
import collisionsystem.Velocity;
import geometry.Boundary;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import sprites.ScoreTrackingListener;
import sprites.Ball;
import sprites.LevelNameIndicator;
import sprites.LivesIndicator;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;

/**
 * A GameLevel class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class GameLevel implements Animation {
    private LevelInformation level;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter removedBlocks;
    private Counter ballsRemain;
    private Counter blocksRemained;
    private Counter score;
    private Counter lives;
    private Paddle gamePaddle;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    /**
     * constructor.
     *
     * @param level
     *            the level.
     * @param ks
     *            the keyborad sensor.
     * @param ar
     *            the animation runner.
     * @param lives
     *            conter of lives.
     * @param score
     *            counter of score.
     */
    public GameLevel(LevelInformation level, KeyboardSensor ks, AnimationRunner ar, Counter lives, Counter score) {
        this.setSprites();
        this.setEnvironment();
        this.removedBlocks = new Counter();
        this.blocksRemained = new Counter();
        this.ballsRemain = new Counter();
        this.runner = ar;
        this.keyboard = ks;
        this.lives = lives;
        this.score = score;
        this.level = level;
    }

    /**
     * add collidable to the game environment.
     *
     * @param c
     *            the collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add Sprite to the list.
     *
     * @param s
     *            the Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add
     * them to the game.
     */
    public void initialize() {
        Sprite background = this.level.getBackground();
        this.sprites.addSprite(background);
        Block headline = new Block(new Rectangle(new Point(0, 0), 800, 15));
        this.sprites.addSprite(headline);
        createBlocks();
        createFrameBlocks();
        createPaddle();
    }

    /**
     * /** Run the game -- start the animation loop.
     */
    public void playOneTurn() {
        createBallsOnTopOfPaddle();
        this.gamePaddle.middle();
        this.runner.run(new CountdownAnimation(3, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if (this.blocksRemained.getValue() == 0) {
            this.score.increase(100);
            System.out.println(this.score.getValue());
            this.running = false;
        }
        if (this.ballsRemain.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            PauseScreen pause = new PauseScreen(this.keyboard);
            this.runner.run(new KeyPressStoppableAnimation(pause, this.keyboard, "space"));
        }
    }

    /**
     * create new blocks and add them to game.
     */
    public void createBlocks() {
        BlockRemover bh = new BlockRemover(this, this.removedBlocks, this.blocksRemained);
        ScoreTrackingListener stl = new ScoreTrackingListener(this.score);
        ScoreIndicator si = new ScoreIndicator(stl.getCurrentScore());
        LivesIndicator li = new LivesIndicator(this.lives);
        LevelNameIndicator lni = new LevelNameIndicator(this.level);
        this.score = stl.getCurrentScore();
        addSprite(si);
        addSprite(li);
        addSprite(lni);
        List<Block> blocksLevel = this.level.blocks();
        for (Block block : blocksLevel) {
            block.addToGame(this);
            block.addHitListener(bh);
            block.addHitListener(stl);
        }
        this.blocksRemained.increase(this.level.blocks().size());
    }

    /**
     * create new frame blocks and add them to game.
     */
    public void createFrameBlocks() {
        BallRemover br = new BallRemover(this);
        Block upperBound = new Block(new Rectangle(new Point(0, 15), WIDTH - Block.FIXEDSIZE, Block.FIXEDSIZE));
        upperBound.addToGame(this);
        Block leftBound = new Block(new Rectangle(new Point(0, 15), Block.FIXEDSIZE, HEIGHT));
        leftBound.addToGame(this);
        Block downBound = new Block(new Rectangle(new Point(0, HEIGHT), WIDTH, Block.FIXEDSIZE));
        downBound.addToGame(this);
        downBound.addHitListener(br);
        Block rightBound = new Block(new Rectangle(new Point(WIDTH - Block.FIXEDSIZE, 15), Block.FIXEDSIZE, HEIGHT));
        rightBound.addToGame(this);
    }

    /**
     * create new padlle and add this to game.
     *
     */
    public void createPaddle() {
        int paddleSpeed = this.level.paddleSpeed();
        int paddleWidth = this.level.paddleWidth();
        this.gamePaddle = new Paddle(this.keyboard, paddleSpeed, paddleWidth, new Boundary(25, 575, 25, 775));
        this.gamePaddle.addToGame(this);
    }

    /**
     * create the ball on top of paddle.
     */
    private void createBallsOnTopOfPaddle() {
        List<Velocity> initialBallVelocities = this.level.initialBallVelocities();
        int numOfBalls = this.level.numberOfBalls();
        double heightOfPaddle = this.gamePaddle.getCollisionRectangle().getHeight();
        for (Velocity ballVelocity : initialBallVelocities) {
            Ball ball = new Ball(new Point(WIDTH / 2 + 5, HEIGHT - heightOfPaddle - 5), 5, Color.white);
            ball.setVelocity(ballVelocity);
            ball.setEnvironment(this.environment);
            ball.addToGame(this);
        }
        this.ballsRemain.increase(numOfBalls);
    }

    /**
     * create new balls and add them to game.
     */
    public void createBalls() {

    }

    /**
     * @return the sprite collection.
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * set the sprite collection.
     */
    public void setSprites() {
        this.sprites = new SpriteCollection();
    }

    /**
     * @return the game environment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * set the game environment.
     */
    public void setEnvironment() {
        this.environment = new GameEnvironment();
    }

    /**
     * removes a given collidable c from enviroment collidable list.
     *
     * @param c
     *            the collidable to remove.
     * @return true if collidable found, false otherwise.
     */
    public boolean removeCollidable(Collidable c) {
        List<Collidable> collidables = this.environment.getListOfCollidable();
        if (collidables.contains(c)) {
            collidables.remove(c);
            return true;
        } else {
            return false;
        }

    }

    /**
     * removes a given sprite s from sprites list.
     *
     * @param s
     *            the Sprite to remove.
     * @return true if sprite found, false otherwise.
     */
    public boolean removeSprite(Sprite s) {
        ArrayList<Sprite> listOfSprites = (ArrayList<Sprite>) this.sprites.getListOfSprites();
        if (listOfSprites.contains(s)) {
            listOfSprites.remove(s);
            return true;
        } else {
            return false;
        }

    }

    /**
     * @return the ballsRemain
     */
    public Counter getBallsRemain() {
        return ballsRemain;
    }

    /**
     * Score getter.
     *
     * @return this.score;
     */
    public Counter getScore() {
        return this.score;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * @return current lives.
     */
    public Counter getLives() {
        return this.lives;
    }

    /**
     * @return blockesRemained.
     */
    public Counter getBlocksRemained() {
        return this.blocksRemained;
    }

}
