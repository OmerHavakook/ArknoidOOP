import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * A Game class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private biuoop.Sleeper sleeper;
    private int width;
    private int height;

    /**
     * Construct the game object.
     *
     * @param width
     *            the width of the screen.
     * @param height
     *            the height of the screen.
     */
    public Game(int width, int height) {
        this.setSprites();
        this.setEnvironment();
        this.width = width;
        this.height = height;
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
        this.gui = new GUI("arknoid", this.width, this.height);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        this.sleeper = new Sleeper();
        createBlocks();
        createFrameBlocks();
        createBalls();
        createPaddle(keyboard);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        int framesPerSecond = 100;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * create new blocks and add them to game.
     */
    public void createBlocks() {
        java.awt.Color randCo = null;
        int numOfBlocks = 12;
        double x = 0;
        double y = 0;
        int hit = 2;
        for (int i = 0; i < 6; i++) {
            randCo = getColor(i);
            for (int j = 0; j < numOfBlocks; j++) {
                if (i > 0) {
                    hit = 1;
                }
                Block b = new Block(new Rectangle(new Point(this.width - 25 + x, 100 + y), (this.width - 50) / 15,
                        (this.height - 50) / 24), randCo, hit);
                b.addToGame(this);
                x -= (this.width - 50) / 15;
            }
            numOfBlocks--;
            x = 0;
            y += (this.height - 50) / 24;
        }
    }

    /**
     * create new frame blocks and add them to game.
     */
    public void createFrameBlocks() {
        int size = 25;
        Color color = new Color(224, 224, 224);
        Block b1 = new Block(new Rectangle(new Point(0, 0), this.width, size), color, 2);
        b1.addToGame(this);
        Block b2 = new Block(new Rectangle(new Point(size, this.height - size), this.width - size, size), color, 2);
        b2.addToGame(this);
        Block b3 = new Block(new Rectangle(new Point(0, size), size, this.height - size), color, 2);
        b3.addToGame(this);
        Block b4 = new Block(new Rectangle(new Point(this.width - size, size), size, this.height - size), color, 2);
        b4.addToGame(this);
    }

    /**
     * create new padlle and add this to game.
     * @param keyboard the keyboard sensor.
     */
    public void createPaddle(biuoop.KeyboardSensor keyboard) {
        Paddle paddle = new Paddle(new Point(this.width * 4.5 / 12, 0.915 * this.height), 140.00, 25.00, Color.BLACK,
                keyboard, new Boundary(25, 575, 25, 775));
        paddle.addToGame(this);
    }

    /**
     * create new balls and add them to game.
     */
    public void createBalls() {
        Ball ball1 = new Ball(this.width * 3 / 4, this.height * 10 / 12, 4, Color.BLACK);
        ball1.setVelocity(4, 5);
        ball1.setEnvironment(this.environment);
        ball1.addToGame(this);
        Ball ball2 = new Ball(this.width * 3 / 4, this.height * 10 / 12, 5, Color.BLACK);
        ball2.setVelocity(2, 1);
        ball2.setEnvironment(this.environment);
        ball2.addToGame(this);
    }

    /**
     * return color to the line of blocks.
     *
     * @param i
     *            the number line of blocks.
     * @return color.
     */
    private Color getColor(int i) {
        if (i == 0) {
            return new Color(102, 178, 255);
        }
        if (i == 1) {
            return new Color(255, 255, 102);
        }
        if (i == 2) {
            return new Color(102, 178, 255);
        }
        if (i == 3) {
            return new Color(255, 255, 102);
        }
        if (i == 4) {
            return new Color(102, 178, 255);
        }
        if (i == 5) {
            return new Color(255, 255, 102);
        }
        return null;
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

}
