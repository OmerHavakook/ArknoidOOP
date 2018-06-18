package collisionsystem;

import geometry.Point;

/**
 * A Velocity class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     * Construct a Velocity given dx coordinate dy coordinate.
     *
     * @param dx
     *            the x coordinate of the vector.
     * @param dy
     *            the y coordinate of the vector.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * create a Velocity given angle and speed.
     *
     * @param angle
     *            the angle of the velocity vector.
     * @param speed
     *            the size of of the velocity vector.
     * @return the new velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * @return the dx of the velocity.
     */
    public double getDX() {
        return this.dx;
    }

    /**
     * @return the dy of the velocity.
     */
    public double getDY() {
        return this.dy;
    }

    /**
     * set the dx.
     * 
     * @param dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * set the dy.
     * 
     * @param dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position
     * (x+dx, y+dy).
     *
     * @param p
     *            point of the location.
     * @return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}
