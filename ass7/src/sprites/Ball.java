package sprites;

import biuoop.DrawSurface;
import collisionsystem.CollisionInfo;
import collisionsystem.Velocity;
import gamefiles.GameLevel;
import gamefiles.GameEnvironment;
import geometry.Boundary;
import geometry.Line;
import geometry.Point;

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
    private boolean check;

    /**
     * Construct a ball given point center int radius and color.
     *
     * @param center
     *            the center point of the ball.
     * @param r
     *            the radius of the ball.
     * @param color
     *            the color of the ball.
     * @param check boolean.
     * @param ge GameEnvironment.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment ge, boolean check) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.check = check;
        this.game = ge;
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
     * @param check boolean.
     * @param ge GameEnvironment.
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment ge, boolean check) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.check = check;
        this.game = ge;
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
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
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

    @Override
    public void timePassed(double dt) {
        this.moveOneStep(dt);
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
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * removes this from given game.
     *
     * @param g
     *            the game to remove from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * find the near collision.
     *
     * @param collisionPoint
     *            Point.
     * @return Point.
     */
    public Point findNearHitPoint(Point collisionPoint) {
        double x;
        double y;
        if (this.vel.getDX() > 0) {
            x = collisionPoint.getX() - 2;
        } else {
            x = collisionPoint.getX() + 2;
        }
        if (this.vel.getDY() > 0) {
            y = collisionPoint.getY() - 2;
        } else {
            y = collisionPoint.getY() + 2;
        }
        return new Point(x, y);
    }

    /**
     * change the location of the ball by checking the boundaries by one step.
     *
     * @param dt
     *            double.
     */
    public void moveOneStep(double dt) {
        Velocity velo = new Velocity(this.vel.getDX(), this.vel.getDY());
        Point pointAfterStep = velo.applyToPoint(this.center, dt);
        Line trajectory = new Line(Math.round(this.center.getX()), Math.round(this.center.getY()),
                Math.round(pointAfterStep.getX()), Math.round(pointAfterStep.getY()));
        if (this.game.getClosestCollision(trajectory) != null) {
            CollisionInfo closeColl = this.game.getClosestCollision(trajectory);
            this.center = findNearHitPoint(closeColl.collisionPoint());
            this.setVelocity(closeColl.collisionObject().hit(this, closeColl.collisionPoint(), this.vel));
        } else {
            this.center = trajectory.end();
        }
    }

    /**
     * @return true or false.
     */
    public boolean ballFromAlien() {
        return check;
    }

}
