package collisionsystem;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamefiles.GameLevel;
import geometry.Boundary;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Sprite;

/**
 * A Paddle class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Paddle implements Sprite, Collidable {

    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleRec;
    private Boundary bound;
    private double rectDivision;
    private Color paddleColor;
    private Velocity velocity;
    private static final int MIDLLEX = GameLevel.WIDTH / 2;
    private static final int MIDLLEY = GameLevel.HEIGHT - Block.FIXEDSIZE;

    /**
     * construct the paddle.
     *
     * @param p
     *            the location point.
     * @param width
     *            the width of the paddle.
     * @param height
     *            the height of the paddle.
     * @param c
     *            the color.
     * @param keyboard
     *            the keyboard sensor from biuoop.
     * @param bound
     *            the bound of the paddle.
     */
    public Paddle(Point p, double width, double height, Color c, KeyboardSensor keyboard, Boundary bound) {
        this.paddleColor = c;
        this.keyboard = keyboard;
        this.velocity = new Velocity(8, 0);
        this.paddleRec = new Rectangle(p, width, height);
        this.rectDivision = width / 5.0;
        this.bound = bound;
    }

    /**
     * Constructor with keyboardsensor,speed, width,left boarder,rightboarder.
     *
     * @param keyboard
     *            keyboard sensor.
     * @param paddleSpeed
     *            speed.
     * @param paddleWidth
     *            paddle width.
     * @param leftBoarder
     *            left boarder.
     * @param rightBoarder
     *            right boarder.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, int paddleSpeed, int paddleWidth, Boundary bound) {
        this.paddleRec = new Rectangle(new Point(MIDLLEX - paddleWidth / 2, MIDLLEY), paddleWidth, Block.FIXEDSIZE);
        this.keyboard = keyboard;
        this.velocity = new Velocity(paddleSpeed, 0);
        this.bound = bound;
        this.rectDivision = paddleWidth / 5.0;
        this.paddleColor = Color.yellow;
    }

    /**
     * move the paddle 5 pixels left.
     */
    public void moveLeft() {
        paddleRec.setX(paddleRec.getUpperLeft().getX() - this.velocity.getDX());
    }

    /**
     * move the paddle 5 pixels right.
     */
    public void moveRight() {
        paddleRec.setX(paddleRec.getUpperLeft().getX() + this.velocity.getDX());
    }

    /**
     * move the paddle right or left after time and press.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)
                && this.paddleRec.getBottomLeft().getX() > this.bound.getLeftBound()) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                && this.paddleRec.getBottomRight().getX() < this.bound.getRightBound()) {
            this.moveRight();
        }
    }

    /**
     * draw the paddle.
     * 
     * @param d
     *            the draw face.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.paddleColor);
        d.fillRectangle((int) paddleRec.getUpperLeft().getX(), (int) paddleRec.getUpperLeft().getY(),
                (int) paddleRec.getWidth(), (int) paddleRec.getHeight());
    }

    /**
     * @return the shape of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddleRec;
    }

    /**
     * Calculate the new velocity after hit in the paddle.
     * 
     * @param hitter
     *            the ball that hits this block.
     * @param collisionPoint
     *            the collision point in the paddle.
     * @param currentVelocity
     *            the velocity of the ball.
     * @return new velocity for ball.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        double angle = (double) Math.toDegrees(Math.atan2(-currentVelocity.getDY(), currentVelocity.getDX()));
        double speed = Math.sqrt(
                currentVelocity.getDX() * currentVelocity.getDX() + currentVelocity.getDY() * currentVelocity.getDY());
        if (collisionPoint.equals(paddleRec.getUpperLeft()) || collisionPoint.equals(paddleRec.getUpperRight())
                || collisionPoint.equals(paddleRec.getBottomLeft())
                || collisionPoint.equals(paddleRec.getBottomRight())) {
            return new Velocity(-1 * (currentVelocity.getDX()), -1 * (currentVelocity.getDY()));
        }
        if (paddleRec.getUp().isOnLine(collisionPoint)) {
            if (collisionPoint.getX() <= paddleRec.getUpperLeft().getX() + rectDivision) {
                return Velocity.fromAngleAndSpeed(300, speed);
            } else if (collisionPoint.getX() <= paddleRec.getUpperLeft().getX() + 2 * rectDivision) {
                return Velocity.fromAngleAndSpeed(330, speed);
            } else if (collisionPoint.getX() <= paddleRec.getUpperLeft().getX() + 3 * rectDivision) {
                return Velocity.fromAngleAndSpeed(360, speed);
            } else if (collisionPoint.getX() <= paddleRec.getUpperLeft().getX() + 4 * rectDivision) {
                return Velocity.fromAngleAndSpeed(30, speed);
            } else {
                return Velocity.fromAngleAndSpeed(60, speed);
            }
        } else if (paddleRec.getLeft().isOnLine(collisionPoint) || paddleRec.getRight().isOnLine(collisionPoint)) {
            return new Velocity(-1 * (currentVelocity.getDX()), (currentVelocity.getDY()));
        } else {
            angle = (double) Math.toDegrees(Math.atan2(-currentVelocity.getDY(), -currentVelocity.getDX()));
            return Velocity.fromAngleAndSpeed(angle, speed);
        }
    }

    /**
     * add the paddle to game.
     * 
     * @param g
     *            the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * @return the bound of the paddle.
     */
    public Boundary getBound() {
        return bound;
    }

    /**
     * put paddle to middle of Game Window.
     */
    public void middle() {
        this.getCollisionRectangle().setUpperLeft(new Point(MIDLLEX - this.paddleRec.getWidth() / 2, MIDLLEY));
    }

}
