package gamefiles;

import java.util.ArrayList;
import java.util.Collections;
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
import collisionsystem.HitListener;
import collisionsystem.Paddle;
import geometry.Boundary;
import geometry.Point;
import geometry.Rectangle;
import invanders.Alien;
import invanders.AlienGroup;
import levels.LevelInformation;
import sprites.ScoreTrackingListener;
import sprites.Ball;
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
    private Counter blocksRemained;
    private Counter score;
    private Counter lives;
    private Paddle gamePaddle;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private LevelInformation levelInformation;
    private List<Alien> alienList;
    private List<Block> blockList;
    private int speed;
    private AlienGroup alienGroup;
    private List<Ball> listOfBalls;
    private long last;

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
     * @param speed the speed.
     * @param numOfLevel the num of level.
     */
    public GameLevel(LevelInformation level, KeyboardSensor ks, AnimationRunner ar, Counter lives, Counter score,
            int speed, Counter numOfLevel) {
        this.setSprites();
        this.setEnvironment();
        this.levelInformation = level;
        this.blocksRemained = new Counter(level.numberOfBlocksToRemove());
        this.runner = ar;
        this.keyboard = ks;
        this.lives = lives;
        this.score = score;
        this.level = level;
        this.alienList = levelInformation.aliens();
        this.blockList = levelInformation.blocks();
        this.speed = speed;
        this.listOfBalls = new ArrayList<>();
        this.last = 0;

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
        createAlienGroup();
        createBlocks();
        createFrameBlocks();
        createPaddle();
    }

    /**
     * /** Run the game -- start the animation loop.
     */
    public void playOneTurn() {
        this.gamePaddle.middle();
        this.runner.run(new CountdownAnimation(3, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * this method reset the game.
     */
    public void resetGame() {
        if (this.gamePaddle != null) {
            for (Ball b : listOfBalls) {
                this.removeSprite(b);
            }
            this.gamePaddle.clearBalls();
            this.removeCollidable(this.gamePaddle);
            this.removeSprite(this.gamePaddle);
            this.lives.decrease(1);
            this.listOfBalls.clear();
            this.running = false;
        }
        this.alienGroup.resetGroup(this.speed);
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        long current = System.currentTimeMillis();
        if (current - last >= 500 && this.blocksRemained.getValue() > 0) {
            this.alienGroup.shoot(this.getEnvironment());
            this.last = System.currentTimeMillis();
        }
        if (this.blocksRemained.getValue() == 0) {
            this.removeSprite(gamePaddle);
            this.removeCollidable(gamePaddle);
            this.running = false;
        }
        if (alienGroup.touchTheSheild()) {
            resetGame();
        }
        System.out.println(this.sprites.getListOfSprites().size());
        this.sprites.notifyAllTimePassed(dt);
        this.sprites.drawAllOn(d);
        if (this.keyboard.isPressed("p")) {
            PauseScreen pause = new PauseScreen(this.keyboard);
            this.runner.run(new KeyPressStoppableAnimation(pause, this.keyboard, "space"));
        }
    }

    /**
     * create new blocks and add them to game.
     */
    public void createBlocks() {
        HitListener br;
        ScoreTrackingListener stl;
        stl = new ScoreTrackingListener(this.score);
        HitListener list = new BallRemover(this);
        ScoreIndicator si = new ScoreIndicator(stl.getCurrentScore());
        LivesIndicator li = new LivesIndicator(this.lives);
        addSprite(si);
        addSprite(li);
        br = new BlockRemover(this, this.blocksRemained, alienGroup);
        for (int i = 0; i < this.alienList.size(); i++) {
            this.alienList.get(i).addToGame(this);
            this.alienList.get(i).addHitListener(br);
            this.alienList.get(i).addHitListener(stl);
            this.alienList.get(i).addHitListener(list);
        }
        Collections.reverse(this.alienList);
        for (int i = 0; i < this.blockList.size(); i++) {
            this.blockList.get(i).addToGame(this);
            this.blockList.get(i).addHitListener(list);
            this.blockList.get(i).addHitListener(br);
        }
    }

    /**
     * create new frame blocks and add them to game.
     */
    public void createFrameBlocks() {
        BallRemover br = new BallRemover(this);
        Block upperBound = new Block(new Rectangle(new Point(0, 0), 800, 0));
        upperBound.addToGame(this);
        upperBound.addHitListener(br);
        Block downBound = new Block(new Rectangle(new Point(0, 600), 800, 0));
        downBound.addToGame(this);
        downBound.addHitListener(br);

    }

    /**
     * create new padlle and add this to game.
     *
     */
    public void createPaddle() {
        int paddleSpeed = this.level.paddleSpeed();
        int paddleWidth = this.level.paddleWidth();
        this.gamePaddle = new Paddle(this.keyboard, paddleSpeed, paddleWidth, new Boundary(0, 600, 0, 800), this,
                this.getEnvironment());
        this.gamePaddle.addToGame(this);
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
     * this method remove a ball from this.listOfBalls.
     *
     * @param b
     *            - as the ball to remove
     */
    public void removeBall(Ball b) {
        this.listOfBalls.remove(b);
    }

    /**
     * this method add a ball to this.listOfBalls.
     *
     * @param b
     *            - as the ball to add
     */
    public void addBall(Ball b) {
        this.listOfBalls.add(b);
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

    /**
     * create alien group.
     */
    public void createAlienGroup() {
        this.alienGroup = new AlienGroup(520, this.speed, this.alienList, this);
        this.alienGroup.addToGame(this);
    }

}
