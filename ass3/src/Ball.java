import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * A Ball class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Ball implements Sprite {

    private Point center;
    private int radius;
    private Color color;
    private Velocity vel;
    private Boundary bound;
    private GameEnvironment game;

    /**
     * Construct a ball given point center int radius and color.
     *
     * @param center
     *            the center point of the ball.
     * @param r
     *            the radius of the ball.
     * @param color
     *            the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Construct a ball given x coordinate y coordinate int radius and color.
     *
     * @param x
     *            the x coordinate of the center point of the ball.
     * @param y
     *            the y coordinate of the center point of the ball.
     * @param r
     *            the radius of the ball.
     * @param color
     *            the color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * @return the x coordinate of the center.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return the y coordinate of the center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * set gameEnvrionment to the ball.
     *
     * @param g
     *            gameEnvrionment.
     */
    public void setEnvironment(GameEnvironment g) {
        this.game = g;
    }
    /**
     * @return the gameEnvrionment of the ball.
     */
    public GameEnvironment getGameEnvrionment() {
        return this.game;
    }
    /**
     * draw ball by given draw face.
     *
     * @param surface
     *            DrawFace to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * set the velocity of ball by size.
     */
    public void setVelocityBySize() {
        Random rand = new Random();
        if (this.radius >= 5) {
            this.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(360), 5));
        } else {
            this.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(360), (2 * Math.PI) / this.radius));
        }
    }

    /**
     * after time it call to move one step.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * set the ball boundary by given Boundary param.
     *
     * @param b
     *            Boundary to set.
     */
    public void setBoundary(Boundary b) {
        this.bound = b;
    }

    /**
     * set the ball boundary by given 4 coordinates.
     *
     * @param u
     *            the up boundary.
     * @param d
     *            the down boundary.
     * @param l
     *            the left boundary.
     * @param r
     *            the right boundary.
     */
    public void setBoundary(int u, int d, int l, int r) {
        this.bound = new Boundary(u, d, l, r);
    }

    /**
     * @return the boundary of the ball.
     */
    public Boundary getBoundary() {
        return this.bound;
    }

    /**
     * set the ball velocity by given Velocity param.
     *
     * @param v
     *            velocity to set.
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
    }

    /**
     * set the ball velocity by given 2 vectors.
     *
     * @param dx
     *            the x vector of velocity.
     * @param dy
     *            the y vector of velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.vel = new Velocity(dx, dy);
    }

    /**
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.vel;
    }

    /**
     * change the dy direction of the ball.
     */
    public void changeHorizontal() {
        this.setVelocity(this.vel.getDX(), -this.vel.getDY());
    }

    /**
     * change the dx direction of the ball.
     */
    public void changeVertical() {
        this.setVelocity(-this.vel.getDX(), this.vel.getDY());
    }

    /**
     * add the ball to the game.
     *
     * @param g
     *            the game parameter.
     */
    public void addToGame(Game g) {
        g.addSprite((Sprite) this);
    }

    /**
     * change the location of the ball by checking the boundaries by one step.
     */
    public void moveOneStep() {
        Point pointAfterStep = this.vel.applyToPoint(this.center);
        pointAfterStep = this.vel.applyToPoint(pointAfterStep);
        Line trajectory = new Line(Math.round(this.center.getX()), Math.round(this.center.getY()),
                Math.round(pointAfterStep.getX()), Math.round(pointAfterStep.getY()));
        if (this.game.getClosestCollision(trajectory) != null) {
            CollisionInfo closeColl = this.game.getClosestCollision(trajectory);
            this.setVelocity(closeColl.collisionObject().hit(closeColl.collisionPoint(), this.vel));
        }
        this.center = this.vel.applyToPoint(this.center);
    }

}
